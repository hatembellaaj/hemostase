"""
Ligne de commande. Exemples :

  python -m hqa.cli audit
  python -m hqa.cli split [--freeze]
  python -m hqa.cli annotation-template --subset test
  python -m hqa.cli run --mode B0 --subset dev
  python -m hqa.cli run --mode B3 --subset dev            # exige un LLM configuré ET autorisé
  python -m hqa.cli evaluate --runs <run_id> [<run_id>...] --annotations data/derived/annotation_test.csv
  python -m hqa.cli synthetic                               # expérience artificielle complète (validation logicielle)
  python -m hqa.cli synthetic --llm --modes B3              # vrai modèle, données artificielles seulement
  python -m hqa.cli review-assignment --experts A B --conditions B0 B3
"""
from __future__ import annotations

import argparse
import json
import sys
import uuid
from datetime import datetime, timezone
from pathlib import Path

from .agents import Agents, Prompts
from .cases import build_cases, same_name_groups
from .config import ROOT, load_config
from .dictionary import load_dictionary
from .exports import NOT_RUN, metrics_tables, write_manifest, write_results
from .llm import LLMNotConfigured, RegistryDataNotAuthorized, make_client
from .metrics import correction_metrics, cost_metrics, detection_metrics, normalization_metrics, paired_bootstrap, system_decisions
from .opportunities import agreement, build_opportunities, read_annotations, write_annotation_file
from .pipelines import MODES, RunContext, run_case
from .privacy import Pseudonymizer
from .profiling import profile, profile_markdown
from .rules import RuleEngine
from .schemas import CaseResult
from .split import load_split, make_split, save_split, subset
from .workbook import read_workbook, sha256_file


def _setup(cfg: dict, workbook: str | None = None):
    d = load_dictionary(cfg["paths"]["fields"], cfg["paths"]["rules"])
    path = workbook or cfg["paths"]["workbook"]
    before = sha256_file(path)
    wb = read_workbook(path, cfg["paths"]["sheet"], {c: f.field_id for c, f in d.by_col().items()})
    pseudo = Pseudonymizer(cfg["paths"]["private_dir"], cfg["privacy"]["study_id_prefix"])
    cases = build_cases(wb, d, pseudo)
    groups = same_name_groups(cases)
    same = {s: g for g in groups for s in g}
    if sha256_file(path) != before:
        raise RuntimeError("Fichier source modifié pendant le traitement")
    return d, wb, cases, same


def _run_id(mode: str) -> str:
    return f"{datetime.now(timezone.utc):%Y%m%dT%H%M%S}-{mode}-{uuid.uuid4().hex[:6]}"


# ------------------------------------------------------------------------------------------------- commandes
def cmd_audit(args, cfg):
    d, wb, cases, same = _setup(cfg, args.workbook)
    p = profile(wb)
    out = Path(cfg["paths"]["runs_dir"]) / "audit"
    out.mkdir(parents=True, exist_ok=True)
    (out / "profile.json").write_text(json.dumps(p, indent=1, ensure_ascii=False, default=str), encoding="utf-8")
    md = profile_markdown(p)
    flags = sum(len(c.residual_flags) for c in cases)
    md += (f"\n\n## Confidentialité\n\n- Dossiers (lignes) : {len(cases)} ; identifiants d'étude attribués (table de correspondance "
           f"hors exports : {cfg['paths']['private_dir']}).\n- Groupes de lignes partageant une même clé de nom : {len(set(map(tuple, same.values())))} "
           f"({len(same)} lignes) — non fusionnés.\n- Champs texte expurgés : {sum(len(c.redacted_text) for c in cases)} ; "
           f"cellules signalées pour identifiants résiduels possibles : {flags} (revue humaine requise).\n"
           "- Pseudonymisation ≠ anonymisation : dates, lieux et combinaisons cliniques rares restent des quasi-identifiants.\n")
    (out / "profile.md").write_text(md, encoding="utf-8")
    print(f"Profil agrégé -> {out / 'profile.md'}")


def cmd_split(args, cfg):
    d, wb, cases, _ = _setup(cfg)
    path = Path(cfg["paths"]["derived_dir"]) / "split.json"
    path.parent.mkdir(parents=True, exist_ok=True)
    if args.freeze:
        s = load_split(path)
        s["frozen"] = True
        s["frozen_on"] = datetime.now(timezone.utc).isoformat()
        path.write_text(json.dumps(s, indent=1), encoding="utf-8")
        print("Découpage figé.")
        return
    s = make_split(cases, cfg["split"]["seed"], cfg["split"]["n_dev"], wb.sha256)
    save_split(s, path)
    print(f"dev {s['n_dev']} / test {s['n_test']} (groupes multi-lignes : {s['n_groups_multi_row']}) -> {path}")


def cmd_annotation_template(args, cfg):
    d, wb, cases, same = _setup(cfg)
    s = load_split(Path(cfg["paths"]["derived_dir"]) / "split.json")
    sel = subset(cases, s, args.subset) if args.subset != "all" else cases
    opps = build_opportunities(sel, d, same)
    if args.sample_cells_per_case:
        # échantillon stratifié reproductible des opportunités de cellule (A et C) ; toutes les relations (B)
        # et questions d'identité restent annotées. L'échantillon est tiré AVANT toute exécution des systèmes.
        import random

        rng = random.Random(cfg["split"]["seed"] + 7)
        keep = set()
        for sid in sorted({o["study_id"] for o in opps}):
            fields = sorted({o["target"] for o in opps if o["study_id"] == sid and o["family"] == "A_normalisation"})
            keep |= {(sid, f) for f in rng.sample(fields, min(args.sample_cells_per_case, len(fields)))}
        opps = [o for o in opps if o["family"] == "B_coherence" or o["target"] == "identity" or (o["study_id"], o["target"]) in keep]
    path = Path(cfg["paths"]["derived_dir"]) / f"annotation_{args.subset}.csv"
    if path.exists() and not args.overwrite:
        sys.exit(f"{path} existe déjà (annotations en cours ?) : --overwrite pour remplacer")
    write_annotation_file(opps, path)
    fam = {}
    for o in opps:
        fam[o["family"]] = fam.get(o["family"], 0) + 1
    print(f"{len(opps)} opportunités {fam} -> {path} (aucune sortie de système n'y figure)")


def _active_statuses(cfg, args) -> list[str]:
    statuses = list(cfg["rules"]["active_statuses"])
    if getattr(args, "enable_hypothesis_rules", False):
        statuses += ["hypothesis", "proposed_reference"]
    return statuses


def cmd_run(args, cfg):
    if args.mode not in MODES:
        sys.exit(f"mode inconnu : {args.mode}")
    d, wb, cases, same = _setup(cfg, args.workbook)
    statuses = _active_statuses(cfg, args)
    cfg["rules"]["active_statuses"] = statuses
    engine = RuleEngine(d, statuses, {"same_name": same})
    run_id = _run_id(args.mode)
    split = None
    if args.subset != "all":
        split = load_split(Path(cfg["paths"]["derived_dir"]) / "split.json")
        if args.subset == "test" and not split.get("frozen"):
            sys.exit("Le découpage doit être figé (split --freeze) avant toute exécution sur le test")
        cases = subset(cases, split, args.subset)
    agents, prompts, llm_info = None, None, None
    notes = []
    if "hypothesis" in statuses:
        notes.append("Règles à l'état d'hypothèse ou de seuil proposé ACTIVÉES : sorties de développement, pas des résultats cliniques.")
    if args.mode != "B0":
        if getattr(args, "fake_llm", False):
            # FAUX MODÈLE : vérifie que l'infrastructure fonctionne. Sorties étiquetées simulées, exclues des métriques.
            from .llm import FakeLLM

            client = FakeLLM(lambda s, u: '{"proposals": [], "candidates": [], "verdicts": [], "action": "final"}')
            notes.append("FAUX MODÈLE (FakeLLM) : aucune sortie de modèle réelle ; résultats exclus des résultats scientifiques.")
        else:
            # vrai modèle sur données artificielles : réservé à cmd_synthetic, qui fournit l'empreinte du classeur généré
            artificial = getattr(args, "artificial_sha256", None)
            if artificial and artificial != wb.sha256:
                sys.exit("Classeur différent du classeur artificiel généré : envoi au modèle refusé")
            try:
                client = make_client(cfg, artificial_data=bool(artificial))
            except (LLMNotConfigured, RegistryDataNotAuthorized) as e:
                sys.exit(f"Mode {args.mode} non exécuté : {e}")
            if artificial:
                client.raw_log_path = Path(cfg["paths"]["runs_dir"]) / run_id / "raw_llm_outputs.jsonl"
                client.raw_log_path.parent.mkdir(parents=True, exist_ok=True)
                notes.append("Vrai modèle sur données ENTIÈREMENT ARTIFICIELLES : validation logicielle et mesure des coûts, "
                             "pas une performance sur le registre.")
        prompts = Prompts(cfg["paths"]["prompts"], args.prompt_version)
        agents = Agents(client, prompts, d, cfg["llm"]["max_output_tokens"], cfg["privacy"]["llm_date_generalization"])
        llm_info = {k: cfg["llm"].get(k) for k in ("base_url", "model", "temperature", "seed", "max_output_tokens")} | {"prompt_version": args.prompt_version}
    ctx = RunContext(run_id, args.mode, d, engine, cfg["limits"], agents, args.coordinator)
    results: list[CaseResult] = [run_case(ctx, c) for c in cases]
    run_dir = Path(cfg["paths"]["runs_dir"]) / run_id
    write_results(run_dir, results, ctx.log, d)
    write_manifest(run_dir, run_id=run_id, mode=args.mode, cfg=cfg, d=d, prompts_hashes=prompts.hashes if prompts else None,
                   workbook_sha=wb.sha256, split=split, subset=args.subset, llm_info=llm_info, results=results, notes=notes, root=ROOT)
    n_anom = sum(len(r.anomalies) for r in results)
    print(f"{run_id} : {len(results)} dossiers, {n_anom} anomalies candidates -> {run_dir}")
    return run_id


def _load_results(run_dir: Path) -> list[CaseResult]:
    with open(run_dir / "case_results.jsonl", encoding="utf-8") as fh:
        return [CaseResult.model_validate_json(l) for l in fh]


def evaluate_runs(cfg, run_ids: list[str], annotations_path: str, out_dir: Path, artificial: bool = False) -> dict:
    ann = read_annotations(annotations_path)
    reference = {o: a["adjudicated_label"] for o, a in ann.items() if a["adjudicated_label"]}
    family_of = {o: a["family"] for o, a in ann.items()}
    ref_values = {}
    for o, a in ann.items():
        if o.startswith("NORM") and a.get("adjudicated_expected_value"):
            ref_values[o] = json.loads(a["adjudicated_expected_value"])
    systems, per_case = {}, {}
    for rid in run_ids:
        run_dir = Path(cfg["paths"]["runs_dir"]) / rid
        man = json.loads((run_dir / "manifest.json").read_text(encoding="utf-8"))
        res = _load_results(run_dir)
        name = man["mode"] + (" [SIMULÉ]" if man["simulated_outputs"] else "")
        dec = system_decisions(res, set(family_of))
        systems[name] = {
            "run_id": rid, "simulated": man["simulated_outputs"],
            "detection": detection_metrics(dec["decisions"], reference, family_of),
            "normalization": normalization_metrics(res, ref_values),
            "corrections": correction_metrics(res, ref_values),
            "cost": cost_metrics(res, cfg["llm"].get("price_per_1k_input"), cfg["llm"].get("price_per_1k_output")),
            "n_alerts_outside_opportunities": len(dec["outside"]), "n_alerts_rejected_by_verifier": len(dec["rejected"]),
        }
        per_case[name] = dec["decisions"]
    case_ids = sorted({o.split(":")[1] for o in reference})

    def f1(sysname, ids):
        sub = {o: l for o, l in reference.items() if o.split(":")[1] in set(ids)}
        # rééchantillonnage avec remise : un dossier tiré deux fois compte deux fois
        weights = {}
        for i in ids:
            weights[i] = weights.get(i, 0) + 1
        tp = fp = fn = 0
        for o, l in reference.items():
            w = weights.get(o.split(":")[1], 0)
            if not w or o.startswith("NORM") or l == "unresolved":
                continue
            d_ = per_case[sysname].get(o)
            if d_ in (None, "failure"):
                fn += w * (l == "confirmed_anomaly")
                continue
            pos = l == "confirmed_anomaly"
            tp += w * (d_ == "flag" and pos)
            fp += w * (d_ == "flag" and not pos)
            fn += w * (d_ != "flag" and pos)
        if not tp + fp or not tp + fn:
            return None
        p, r = tp / (tp + fp), tp / (tp + fn)
        return 2 * p * r / (p + r) if p + r else 0.0

    ev = cfg["evaluation"]
    boot = paired_bootstrap(case_ids, f1, list(systems), ev["bootstrap_samples"], ev["bootstrap_seed"], ev["confidence"]) if case_ids else NOT_RUN
    metrics = {"artificial_experiment": artificial, "annotations": str(annotations_path), "inter_annotator": agreement(ann),
               "n_reference_labels": len(reference), "systems": systems,
               "bootstrap_f1_global": boot, "note": "Métriques uniquement sur opportunités annotées ; NA = non calculable."}
    out_dir.mkdir(parents=True, exist_ok=True)
    (out_dir / "metrics.json").write_text(json.dumps(metrics, indent=1, ensure_ascii=False, default=str), encoding="utf-8")
    metrics_tables(metrics, out_dir)
    return metrics


def cmd_evaluate(args, cfg):
    out = Path(cfg["paths"]["runs_dir"]) / f"eval-{datetime.now(timezone.utc):%Y%m%dT%H%M%S}"
    m = evaluate_runs(cfg, args.runs, args.annotations, out)
    if not m["n_reference_labels"]:
        print("Aucune étiquette adjudiquée : métriques non calculées (NA).")
    print(f"-> {out}")


def cmd_synthetic(args, cfg):
    """Expérience artificielle : génération, B0, (option) modes LLM simulés, évaluation contre la vérité terrain."""
    from .synthetic import generate, reference_from_truth, write_reference

    out = ROOT / "examples"
    d = load_dictionary(cfg["paths"]["fields"], cfg["paths"]["rules"])
    g = generate(d, args.rows, args.seed, out)
    syn_cfg = json.loads(json.dumps(cfg))
    syn_cfg["paths"]["private_dir"] = str(Path(cfg["paths"]["runs_dir"]) / "synthetic_private")
    syn_cfg["paths"]["derived_dir"] = str(Path(cfg["paths"]["runs_dir"]) / "synthetic_derived")
    Path(syn_cfg["paths"]["derived_dir"]).mkdir(parents=True, exist_ok=True)
    ns = argparse.Namespace(mode="B0", subset="all", workbook=g["workbook"], enable_hypothesis_rules=True, coordinator="policy", prompt_version="v1")
    rid = cmd_run(ns, json.loads(json.dumps(syn_cfg)))
    run_ids = [rid]
    simulate, real = getattr(args, "simulate_llm", False), getattr(args, "llm", False)
    if simulate and real:
        sys.exit("--simulate-llm et --llm sont exclusifs")
    if simulate or real:
        llm_args = {"fake_llm": True} if simulate else {"artificial_sha256": sha256_file(g["workbook"])}
        for mode in getattr(args, "modes", None) or ["B1", "B2", "B3", "B3-noverif"]:
            ns_m = argparse.Namespace(**{**vars(ns), "mode": mode, **llm_args})
            run_ids.append(cmd_run(ns_m, json.loads(json.dumps(syn_cfg))))
    d2, wb, cases, same = _setup(syn_cfg, g["workbook"])
    opps = build_opportunities(cases, d2, same)
    row_to_sid = {c.excel_row: c.study_id for c in cases}
    ref = reference_from_truth(opps, g["truth"], row_to_sid)
    ref_path = Path(syn_cfg["paths"]["derived_dir"]) / "synthetic_reference.csv"
    write_reference(ref, ref_path)
    m = evaluate_runs(syn_cfg, run_ids, str(ref_path), Path(cfg["paths"]["runs_dir"]) / f"synthetic-eval-{rid}", artificial=True)
    # résumé compact ; le détail complet est dans metrics.json du dossier d'évaluation
    for name, sy in m["systems"].items():
        g, c = sy["detection"]["global"], sy["cost"]
        print(f"{name:<16} positifs {g['n_positive_reference']}  tp {g['tp']}  fp {g['fp']}  fn {g['fn']}  "
              f"sans décision (panne) {g['n_no_decision_failure']}  | appels {c['llm_calls_total']}  "
              f"tokens {c['tokens_in']}+{c['tokens_out']}  latence méd./max {c['latency_median_s']}/{c['latency_max_s']} s  "
              f"invalides {c['invalid_outputs']}  états {c['statuses']}")
    print(f"détail -> {Path(cfg['paths']['runs_dir']) / f'synthetic-eval-{rid}'}")


def cmd_review_assignment(args, cfg):
    """Revue chronométrée équilibrée : chaque dossier est vu par un expert dans UNE seule condition ;
    chaque expert voit chaque condition sur des dossiers différents (carré latin simple)."""
    import random

    d, wb, cases, _ = _setup(cfg)
    s = load_split(Path(cfg["paths"]["derived_dir"]) / "split.json")
    ids = list(s["test"])
    random.Random(cfg["split"]["seed"] + 1).shuffle(ids)
    rows = []
    E, C = args.experts, args.conditions
    for i, sid in enumerate(ids):
        e = E[i % len(E)]
        c = C[(i // len(E) + E.index(e)) % len(C)]
        rows.append({"study_id": sid, "expert": e, "condition": c, "start": "", "end": "", "decision_notes": ""})
    path = Path(cfg["paths"]["derived_dir"]) / "timed_review_assignment.csv"
    from .exports import write_csv

    write_csv(rows, path)
    print(f"{len(rows)} affectations -> {path}")


def main(argv=None):
    ap = argparse.ArgumentParser(prog="hqa", description="Contrôle qualité hybride du registre d'hémophilie")
    ap.add_argument("--config", default=None)
    sub = ap.add_subparsers(dest="cmd", required=True)
    a = sub.add_parser("audit")
    a.add_argument("--workbook", default=None)
    s = sub.add_parser("split")
    s.add_argument("--freeze", action="store_true")
    t = sub.add_parser("annotation-template")
    t.add_argument("--subset", choices=["dev", "test", "all"], default="test")
    t.add_argument("--overwrite", action="store_true")
    t.add_argument("--sample-cells-per-case", type=int, default=0, help="échantillon de cellules par dossier (0 = toutes)")
    r = sub.add_parser("run")
    r.add_argument("--mode", required=True, choices=MODES)
    r.add_argument("--subset", choices=["dev", "test", "all"], default="dev")
    r.add_argument("--workbook", default=None)
    r.add_argument("--enable-hypothesis-rules", action="store_true", help="développement uniquement")
    r.add_argument("--coordinator", choices=["policy", "llm"], default="policy")
    r.add_argument("--prompt-version", default="v1")
    r.add_argument("--fake-llm", action="store_true", help="faux modèle pour tester l'infrastructure (sorties SIMULÉES)")
    e = sub.add_parser("evaluate")
    e.add_argument("--runs", nargs="+", required=True)
    e.add_argument("--annotations", required=True)
    y = sub.add_parser("synthetic")
    y.add_argument("--rows", type=int, default=30)
    y.add_argument("--seed", type=int, default=42)
    y.add_argument("--simulate-llm", action="store_true", help="exécute aussi B1-B3 avec un FAUX modèle (infrastructure uniquement)")
    y.add_argument("--llm", action="store_true",
                   help="exécute aussi B1-B3 avec le VRAI modèle configuré, sur les données artificielles uniquement")
    y.add_argument("--modes", nargs="+", choices=[m for m in MODES if m != "B0"], default=None,
                   help="modes LLM à exécuter (défaut : B1 B2 B3 B3-noverif)")
    v = sub.add_parser("review-assignment")
    v.add_argument("--experts", nargs="+", required=True)
    v.add_argument("--conditions", nargs="+", required=True)
    args = ap.parse_args(argv)
    cfg = load_config(args.config)
    {"audit": cmd_audit, "split": cmd_split, "annotation-template": cmd_annotation_template, "run": cmd_run,
     "evaluate": cmd_evaluate, "synthetic": cmd_synthetic, "review-assignment": cmd_review_assignment}[args.cmd](args, cfg)


if __name__ == "__main__":
    main()
