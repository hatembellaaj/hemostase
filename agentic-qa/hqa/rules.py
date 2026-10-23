"""
Moteur de règles déterministes (mode B0 et outils des agents).

Chaque évaluation produit un CheckOutcome : pass, fail (-> anomalie candidate), not_evaluable (raison
explicite : information insuffisante, précision trop faible, définition manquante…), inactive.
"""
from __future__ import annotations

from dataclasses import dataclass
from datetime import date, datetime

from .cases import Case, safe_value
from .dictionary import Dictionary, RuleDef
from .parsers import date_bounds, duration_months_exact
from .schemas import CandidateAnomaly, Category, Evidence, NormalizedValue, ProposedAction, ValueState, VerificationState


@dataclass
class CheckOutcome:
    rule_id: str
    study_id: str
    fields: list[str]
    result: str  # pass | fail | not_evaluable | inactive
    reason: str = ""
    subtype: str = ""
    category: str = ""


def _ev(case: Case, d: Dictionary, fid: str, note: str | None = None) -> Evidence:
    return Evidence(ref=case.cells[fid].ref, quoted_value=safe_value(case, d, fid), note=note)


def _code(n: NormalizedValue | None):
    return n.value if n and n.state == ValueState.OBSERVED and n.value_type == "code" else None


def _filled(n: NormalizedValue | None) -> bool:
    return bool(n) and n.state not in (ValueState.EMPTY, ValueState.NOT_APPLICABLE)


class RuleEngine:
    def __init__(self, d: Dictionary, active_statuses: list[str], dataset_context: dict | None = None):
        self.d = d
        self.active = {r.id: r for r in d.active_rules(active_statuses)}
        self.ctx = dataset_context or {}
        self._n = 0

    # -------------------------------------------------------------------------
    def run(self, case: Case, norm: dict[str, NormalizedValue], produced_by: str = "b0_rules"):
        anomalies, outcomes = [], []
        for rule in self.d.rules.values():
            if not rule.deterministic:
                continue
            if rule.id not in self.active:
                outcomes.append(CheckOutcome(rule.id, case.study_id, rule.fields, "inactive", f"statut {rule.status} non actif"))
                continue
            fn = getattr(self, f"_r_{rule.id.split('_')[0]}", None)
            if fn is None:
                outcomes.append(CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "règle sans implémentation"))
                continue
            for res in fn(rule, case, norm):
                outcomes.append(res[0])
                if res[0].result == "fail":
                    anomalies.append(self._anomaly(rule, case, res[0], res[1], res[2], res[3], produced_by))
        return anomalies, outcomes

    def _anomaly(self, rule: RuleDef, case, out: CheckOutcome, evidence, justification, action, produced_by):
        self._n += 1
        return CandidateAnomaly(
            anomaly_id=f"{produced_by}-{case.study_id}-{rule.id}-{'-'.join(out.fields)}",
            study_id=case.study_id, check_id=f"{rule.id}:{'+'.join(out.fields)}", rule_id=rule.id, rule_version=rule.version,
            rule_status=rule.status, fields=out.fields, category=Category(out.category or rule.family), subtype=out.subtype,
            evidence=evidence, justification=justification, action=action, produced_by=produced_by,
            verification=VerificationState.DETERMINISTIC,
        )

    # ------------------------------------------------------------------ S01 / S02 / S03
    def _r_S01(self, rule, case, norm):
        for fid, n in norm.items():
            if n.state == ValueState.UNRESOLVED and "code_non_defini" not in n.issues:
                out = CheckOutcome(rule.id, case.study_id, [fid], "fail", subtype="parse_failure:" + (n.issues[0] if n.issues else "?"),
                                   category="C_incertitude")
                yield out, [_ev(case, self.d, fid)], f"Format non reconnu pour « {self.d.fields[fid].contextual_name} » : {n.justification}.", \
                    ProposedAction(kind="review", detail="Préciser la valeur ou confirmer un format autorisé")
            elif n.state not in (ValueState.EMPTY,):
                yield CheckOutcome(rule.id, case.study_id, [fid], "pass"), None, None, None

    def _r_S02(self, rule, case, norm):
        for fid, n in norm.items():
            if self.d.fields[fid].type != "code" or n.state == ValueState.EMPTY:
                continue
            if "code_non_defini" in n.issues:
                out = CheckOutcome(rule.id, case.study_id, [fid], "fail", subtype="undefined_code", category="C_incertitude")
                yield out, [_ev(case, self.d, fid)], f"Code {n.value} non documenté pour « {self.d.fields[fid].contextual_name} ».", \
                    ProposedAction(kind="confirm_definition", detail="Documenter ce code ou corriger la saisie")
            else:
                yield CheckOutcome(rule.id, case.study_id, [fid], "pass"), None, None, None

    def _r_S03(self, rule, case, norm):
        for fid in rule.applies_to or []:
            n = norm.get(fid)
            if not n or n.state != ValueState.OBSERVED:
                continue
            if "unite_absente" in n.issues:
                out = CheckOutcome(rule.id, case.study_id, [fid], "fail", subtype="unit_missing", category="C_incertitude")
                yield out, [_ev(case, self.d, fid)], "Valeur numérique sans unité ; unité non fixée par le dictionnaire.", \
                    ProposedAction(kind="confirm_definition", detail="Fixer l'unité du champ dans le dictionnaire")
            else:
                yield CheckOutcome(rule.id, case.study_id, [fid], "pass"), None, None, None

    def _r_S04(self, rule, case, norm):
        group = self.ctx.get("same_name", {}).get(case.study_id)
        if group:
            out = CheckOutcome(rule.id, case.study_id, ["patient_name"], "fail", subtype="identity_question", category="C_incertitude")
            others = ", ".join(s for s in group if s != case.study_id)
            yield out, [_ev(case, self.d, "patient_name", note=f"même clé de nom que {others}")], \
                f"Clé de nom partagée avec {others} (dates de naissance différentes ou non vérifiées) : identité à confirmer, aucune fusion.", \
                ProposedAction(kind="review", detail="Confirmer s'il s'agit de personnes distinctes (fratrie, homonymie) ou de la même personne")
        else:
            yield CheckOutcome(rule.id, case.study_id, ["patient_name"], "pass"), None, None, None

    # ------------------------------------------------------------------ dépendances
    def _dependency(self, rule, case, norm, filled_fields):
        req = rule.requires
        present = [f for f in filled_fields if _filled(norm.get(f)) and norm[f].state != ValueState.UNKNOWN]
        if not present:
            return
        parent = norm.get(req["field"])
        fields = present + [req["field"]]
        if not parent or parent.state == ValueState.EMPTY:
            yield CheckOutcome(rule.id, case.study_id, fields, "not_evaluable", "champ conditionnant vide"), None, None, None
            return
        pc = _code(parent)
        if pc is None:
            yield CheckOutcome(rule.id, case.study_id, fields, "not_evaluable", "champ conditionnant non interprétable"), None, None, None
            return
        if pc == req["code"]:
            yield CheckOutcome(rule.id, case.study_id, fields, "pass"), None, None, None
            return
        out = CheckOutcome(rule.id, case.study_id, fields, "fail", subtype="dependency_violation")
        ev = [_ev(case, self.d, f) for f in fields]
        yield out, ev, (f"« {', '.join(self.d.fields[f].contextual_name for f in present)} » renseigné alors que "
                        f"« {self.d.fields[req['field']].contextual_name} » = {pc} (attendu : {req['code']})."), \
            ProposedAction(kind="review", detail="Vérifier lequel des deux champs est à corriger")

    def _r_H01(self, rule, case, norm):
        yield from self._dependency(rule, case, norm, [rule.when_filled])

    _r_H02 = _r_H01
    _r_H03 = _r_H01

    def _r_H04(self, rule, case, norm):
        yield from self._dependency(rule, case, norm, rule.when_filled_any)

    # ------------------------------------------------------------------ croisements
    def _r_H05(self, rule, case, norm):
        anyf, right, left = rule.fields
        a, r, l = (_code(norm.get(f)) for f in rule.fields)
        if a is None or r is None or l is None:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "au moins un des trois champs absent ou non codé"), None, None, None
            return
        expected = 1 if 1 in (r, l) else 2
        if a == expected:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "pass"), None, None, None
        else:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "fail", subtype="synthesis_mismatch"), \
                [_ev(case, self.d, f) for f in rule.fields], f"Synthèse « coude » = {a} alors que droit = {r}, gauche = {l}.", \
                ProposedAction(kind="review", detail="Vérifier la colonne de synthèse et les colonnes droite/gauche")

    def _r_H06(self, rule, case, norm):
        h = _code(norm.get(rule.fields[0]))
        sides = [(f, _code(norm.get(f))) for f in rule.fields[1:]]
        hit = [f for f, c in sides if c == 1]
        if h is None:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "hémarthrose non codée"), None, None, None
            return
        if h == 2 and hit:
            fields = [rule.fields[0]] + hit
            yield CheckOutcome(rule.id, case.study_id, fields, "fail", subtype="dependency_violation"), \
                [_ev(case, self.d, f) for f in fields], "Hémarthrose = non alors qu'une articulation est marquée atteinte.", \
                ProposedAction(kind="review", detail="Vérifier l'antécédent d'hémarthrose")
        else:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "pass"), None, None, None

    def _r_H07(self, rule, case, norm):
        t, m = norm.get(rule.fields[0]), norm.get(rule.fields[1])
        if not (t and m and t.state == ValueState.OBSERVED and m.state == ValueState.OBSERVED):
            if _filled(t) and _filled(m):
                yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "une des deux valeurs non interprétable"), None, None, None
            return
        exact = duration_months_exact(t.value)
        if exact is None:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "durée en jours : pas de conversion exacte en mois"), None, None, None
            return
        if abs(exact - m.value) <= rule.params.get("tolerance_months", 0):
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "pass"), None, None, None
        else:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "fail", subtype="value_mismatch"), \
                [_ev(case, self.d, f) for f in rule.fields], f"Âge au diagnostic : texte = {exact:g} mois, colonne « mois » = {m.value:g}.", \
                ProposedAction(kind="review", detail="Vérifier l'une des deux saisies (pas de correction automatique)")

    def _r_H08(self, rule, case, norm):
        pc, r = norm.get(rule.fields[0]), norm.get(rule.fields[1])
        if not (pc and r and pc.state == ValueState.OBSERVED and r.state == ValueState.OBSERVED):
            if _filled(pc) and _filled(r):
                yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "valeur non interprétable"), None, None, None
            return
        if pc.value["denominator"] == 0:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "témoin nul"), None, None, None
            return
        q = pc.value["numerator"] / pc.value["denominator"]
        unit = 10 ** -(r.numeric_precision or 0)
        # troncature ou arrondi à la précision saisie : |q - r| < une unité de la dernière décimale
        ok = abs(q - r.value) < unit - 1e-12 or round(q, r.numeric_precision or 0) == r.value
        if ok:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "pass"), None, None, None
        else:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "fail", subtype="arithmetic_mismatch"), \
                [_ev(case, self.d, f) for f in rule.fields], \
                f"Patient/témoin = {q:.4f} ; ratio saisi = {r.value:g} (écart {q - r.value:+.3f} > une unité de la dernière décimale).", \
                ProposedAction(kind="review", detail="Vérifier le ratio ou les temps saisis")

    def _r_H09(self, rule, case, norm):
        a, b = norm.get(rule.fields[0]), norm.get(rule.fields[1])
        if not (a and b and a.state == ValueState.OBSERVED and b.state == ValueState.OBSERVED):
            return
        ma, mb = duration_months_exact(a.value), duration_months_exact(b.value)
        if ma is None or mb is None:
            if not a.value.get("days") and not b.value.get("days"):
                yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "conversion impossible"), None, None, None
                return
            # avec des jours : comparaison possible seulement si l'écart dépasse l'incertitude de conversion (1 mois)
            da = a.value.get("years", 0) * 365.25 + a.value.get("months", 0) * 30.44 + a.value.get("days", 0)
            db = b.value.get("years", 0) * 365.25 + b.value.get("months", 0) * 30.44 + b.value.get("days", 0)
            if abs(da - db) <= 31:
                yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "écart inférieur à l'incertitude de conversion"), None, None, None
                return
            ma, mb = da / 30.44, db / 30.44
        if ma <= mb:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "pass"), None, None, None
        else:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "fail", subtype="temporal_order"), \
                [_ev(case, self.d, f) for f in rule.fields], f"Premiers signes ({ma:g} mois) après le diagnostic ({mb:g} mois).", \
                ProposedAction(kind="review", detail="Vérifier les deux âges")

    def _r_H10(self, rule, case, norm):
        x, b = norm.get(rule.fields[0]), norm.get(rule.fields[1])
        if not (x and b and x.state == ValueState.OBSERVED and b.state == ValueState.OBSERVED):
            return
        x_lo, x_hi = date_bounds(x.value)
        b_lo, b_hi = date_bounds(b.value)
        if x_hi < b_lo:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "fail", subtype="temporal_order"), \
                [_ev(case, self.d, f) for f in rule.fields], "DDN antérieure à la date de naissance (intervalles de précision disjoints).", \
                ProposedAction(kind="review", detail="Vérifier les deux dates")
        elif x_lo >= b_hi:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "pass"), None, None, None
        else:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "précision insuffisante (intervalles qui se chevauchent)"), None, None, None

    def _r_H11(self, rule, case, norm):
        ref = rule.params.get("reference_date")
        a, b = norm.get(rule.fields[0]), norm.get(rule.fields[1])
        if not (a and b and a.state == ValueState.OBSERVED and b.state == ValueState.OBSERVED):
            return
        if not ref:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "not_evaluable", "date de référence de l'âge inconnue"), None, None, None
            return
        ref_d = date.fromisoformat(str(ref))
        lo, hi = date_bounds(b.value)
        age_max = ref_d.year - lo.year - ((ref_d.month, ref_d.day) < (lo.month, lo.day))
        age_min = ref_d.year - hi.year - ((ref_d.month, ref_d.day) < (hi.month, hi.day))
        if age_min <= a.value <= age_max:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "pass"), None, None, None
        else:
            yield CheckOutcome(rule.id, case.study_id, rule.fields, "fail", subtype="value_mismatch"), \
                [_ev(case, self.d, f) for f in rule.fields], f"Âge saisi {a.value:g} hors de l'intervalle [{age_min}, {age_max}] à la date de référence {ref}.", \
                ProposedAction(kind="review", detail="Vérifier l'âge ou la date de naissance")

    # ------------------------------------------------------------------ seuils cliniques
    def _r_K01(self, rule, case, norm):
        sev, form = _code(norm.get("severity")), _code(norm.get("clinical_form"))
        if sev is None or form is None:
            return
        lvl_f = {1: "fviii_level", 2: "fix_level"}.get(form)
        lvl = norm.get(lvl_f) if lvl_f else None
        fields = ["severity", "clinical_form", lvl_f] if lvl_f else ["severity", "clinical_form"]
        if not lvl or lvl.state != ValueState.OBSERVED:
            yield CheckOutcome(rule.id, case.study_id, fields, "not_evaluable", "taux du facteur déficitaire absent ou non interprétable"), None, None, None
            return
        p = rule.params
        v, comp = lvl.value, lvl.comparator.value if lvl.comparator else "="

        def klass(x):
            return 1 if x < p["severe_below"] else 2 if x <= p["moderate_max"] else 3 if x < p["mild_below"] else None

        cls = None
        if comp in ("<", "<=") and v <= p["severe_below"]:
            cls = 1
        elif comp == "=":
            # intervalle compatible avec la précision saisie (« 1 » = [0,5 ; 1,5[) : classe seulement si non ambiguë
            half = 0.5 * 10 ** -(lvl.numeric_precision or 0)
            lo_c, hi_c = klass(v - half), klass(v + half - 1e-9)
            if lo_c == hi_c:
                cls = lo_c
            else:
                yield CheckOutcome(rule.id, case.study_id, fields, "not_evaluable",
                                   "valeur à la limite d'une classe compte tenu de la précision saisie"), None, None, None
                return
        if cls is None:
            yield CheckOutcome(rule.id, case.study_id, fields, "not_evaluable", "taux hors des classes ou censure non concluante"), None, None, None
        elif cls == sev:
            yield CheckOutcome(rule.id, case.study_id, fields, "pass"), None, None, None
        else:
            yield CheckOutcome(rule.id, case.study_id, fields, "fail", subtype="threshold_mismatch"), \
                [_ev(case, self.d, f) for f in fields], f"Sévérité codée {sev} ; taux {comp}{v:g} correspondrait à la classe {cls} (seuils ISTH, à valider).", \
                ProposedAction(kind="review", detail="Vérifier la sévérité, le taux, son unité et la date du dosage")

    def _r_K02(self, rule, case, norm):
        resp, titer = _code(norm.get("inhibitor_responder")), norm.get("inhibitor_titer")
        if resp is None or not titer or titer.state != ValueState.OBSERVED:
            return
        hi = titer.value >= rule.params["high_responder_min_bu"]
        expected = 2 if hi else 1
        fields = ["inhibitor_responder", "inhibitor_titer"]
        if expected == resp:
            yield CheckOutcome(rule.id, case.study_id, fields, "pass"), None, None, None
        else:
            yield CheckOutcome(rule.id, case.study_id, fields, "fail", subtype="threshold_mismatch"), \
                [_ev(case, self.d, f) for f in fields], f"Répondeur codé {resp} ; titre {titer.value:g} (seuil 5 UB, à valider).", \
                ProposedAction(kind="review", detail="Vérifier le statut répondeur et l'unité du titre")
