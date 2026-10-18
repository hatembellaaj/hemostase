import { useEffect, useMemo, useState } from 'react';
import { Link, useNavigate, useParams, useSearchParams } from 'react-router-dom';
import { api } from '../api';
import { useApp, useToast } from '../state';
import { Chart, MISSING, type AggResult, type ChartType } from '../stats/chart';
import type { EntityDef, FieldDef } from '../types';

const DIMENSION = ['enum', 'boolean', 'integer', 'float', 'date', 'datetime'];
const NUMERIC = ['integer', 'float'];
const clean = (l: string) => l.replace(/^\s*\d+\s*-\s*/, '');

interface Kpi {
  label: string;
  value: number;
  of?: number;
  format: 'int' | 'years' | 'share';
}
interface PresetChart {
  id: string;
  title: string;
  type: ChartType;
  stacked?: boolean;
  horizontal?: boolean;
  data: AggResult;
}

export default function Stats() {
  const { slug = '' } = useParams();
  const { entities, entity } = useApp();
  const ent = entity(slug);
  const nav = useNavigate();
  const [params, setParams] = useSearchParams();
  const tab = params.get('tab') === 'explore' ? 'explore' : 'overview';

  if (!ent) return <div className="page">Registre inconnu.</div>;

  const setTab = (t: string) => {
    const next = new URLSearchParams(params);
    if (t === 'overview') next.delete('tab');
    else next.set('tab', t);
    setParams(next);
  };

  return (
    <div className="page">
      <header className="page-head">
        <div>
          <p className="crumbs">
            <Link to={`/fiches/${slug}`}>{ent.title}</Link>
          </p>
          <h1>Statistiques</h1>
        </div>
        <div className="head-actions">
          <label className="field-label" htmlFor="reg">
            Registre
          </label>
          <select id="reg" className="select-inline" value={slug} onChange={(e) => nav(`/stats/${e.target.value}${tab === 'explore' ? '?tab=explore' : ''}`)}>
            {entities.map((e) => (
              <option key={e.slug} value={e.slug}>
                {e.title}
              </option>
            ))}
          </select>
        </div>
      </header>
      <div className="tabs" role="tablist">
        <button role="tab" aria-selected={tab === 'overview'} className={tab === 'overview' ? 'tab-on' : ''} onClick={() => setTab('overview')}>
          Vue d'ensemble
        </button>
        <button role="tab" aria-selected={tab === 'explore'} className={tab === 'explore' ? 'tab-on' : ''} onClick={() => setTab('explore')}>
          Explorateur
        </button>
      </div>
      {tab === 'overview' ? <Overview key={slug} ent={ent} /> : <Explorer key={slug} ent={ent} />}
    </div>
  );
}

/* ------------------------------------------------------------------ */
function Overview({ ent }: { ent: EntityDef }) {
  const toast = useToast();
  const [data, setData] = useState<{ total: number; kpis: Kpi[]; charts: PresetChart[] } | null>(null);
  useEffect(() => {
    api<typeof data>(`/api/stats/${ent.slug}/overview`)
      .then(({ data }) => setData(data))
      .catch((e) => toast(e.message, 'error'));
  }, [ent.slug, toast]);

  if (!data) return <p className="muted">Calcul des statistiques…</p>;
  if (!data.total)
    return (
      <div className="empty panel">
        <p>Ce registre ne contient encore aucune fiche : les statistiques apparaîtront dès la première saisie.</p>
        <Link to={`/fiches/${ent.slug}/new`} className="btn btn-primary">
          Créer une fiche
        </Link>
      </div>
    );

  return (
    <>
      <section className="kpis" aria-label="Chiffres clés">
        {data.kpis.map((k) => (
          <div key={k.label} className="kpi">
            <p className="kpi-value num">
              {k.format === 'share' && k.of ? `${Math.round((k.value / k.of) * 100)} %` : k.format === 'years' ? `${k.value.toLocaleString('fr-FR')} ans` : k.value.toLocaleString('fr-FR')}
            </p>
            <p className="kpi-label">
              {k.label}
              {k.format === 'share' && k.of ? <span className="muted"> ({k.value} sur {k.of})</span> : null}
            </p>
          </div>
        ))}
      </section>
      <section className="chart-grid">
        {data.charts.map((c) => (
          <article key={c.id} className={`chart-card ${c.horizontal || c.data.categories.length > 8 ? 'chart-card-wide' : ''}`}>
            <h2>{c.title}</h2>
            <Chart data={c.data} type={c.type} stacked={c.stacked} horizontal={c.horizontal} title={c.title} height={c.type === 'pie' ? 220 : 260} />
          </article>
        ))}
      </section>
    </>
  );
}

/* ------------------------------------------------------------------ */
interface Filter {
  field: string;
  op: 'in' | 'between';
  value: (string | number | null)[];
}

function FieldSelect({ ent, value, onChange, types, id, allowEmpty, emptyLabel, exclude = [] }: {
  ent: EntityDef;
  value: string;
  onChange: (v: string) => void;
  types: string[];
  id: string;
  allowEmpty?: boolean;
  emptyLabel?: string;
  exclude?: string[];
}) {
  const groups = useMemo(() => {
    const g = new Map<string, FieldDef[]>();
    for (const f of ent.fields) {
      if (!types.includes(f.type) || exclude.includes(f.name)) continue;
      if (!g.has(f.section)) g.set(f.section, []);
      g.get(f.section)!.push(f);
    }
    return [...g.entries()];
  }, [ent, types, exclude]);
  return (
    <select id={id} value={value} onChange={(e) => onChange(e.target.value)}>
      {allowEmpty && <option value="">{emptyLabel ?? '—'}</option>}
      {groups.map(([section, fs]) => (
        <optgroup key={section} label={section}>
          {fs.map((f) => (
            <option key={f.name} value={f.name}>
              {clean(f.label)}
            </option>
          ))}
        </optgroup>
      ))}
    </select>
  );
}

function Explorer({ ent }: { ent: EntityDef }) {
  const toast = useToast();
  const [params, setParams] = useSearchParams();
  const fields = useMemo(() => Object.fromEntries(ent.fields.map((f) => [f.name, f])), [ent]);
  const firstEnum = ent.fields.find((f) => f.type === 'enum' && f.section !== 'Identification')?.name ?? 'sexe';

  // --- état dans l'URL : un graphique se partage par simple lien
  const get = (k: string, d = '') => params.get(k) ?? d;
  const type = get('type', 'bar') as ChartType;
  const mode = get('mode', 'field'); // field | fields
  const x = fields[get('x')] ? get('x') : firstEnum;
  const multi = get('fields').split(',').filter((n) => fields[n] && ['enum', 'boolean'].includes(fields[n].type));
  const series = type === 'pie' ? '' : fields[get('series')] ? get('series') : '';
  const bin = get('bin');
  const measure = get('m', 'count');
  const percent = get('pct') === '1';
  const stacked = get('stack') === '1';
  const missing = get('missing', '1') === '1';
  const filters: Filter[] = (() => {
    try {
      return (JSON.parse(get('f', '[]')) as Filter[]).filter((f) => fields[f.field]);
    } catch {
      return [];
    }
  })();

  const set = (patch: Record<string, string | null>) => {
    const next = new URLSearchParams(params);
    next.set('tab', 'explore');
    Object.entries(patch).forEach(([k, v]) => (v === null || v === '' ? next.delete(k) : next.set(k, v)));
    setParams(next, { replace: true });
  };
  const setFilters = (fs: Filter[]) => set({ f: fs.length ? JSON.stringify(fs) : null });

  const xField = fields[x];
  const numericX = mode === 'field' && (NUMERIC.includes(xField?.type) || ['date', 'datetime'].includes(xField?.type));
  const [m, mField] = measure.split(':');
  const spec = useMemo(() => {
    const s: Record<string, unknown> = {
      measure: m === 'count' || !fields[mField] ? { op: 'count' } : { op: m, field: mField },
      filters: filters.map((f) => ({ field: f.field, op: f.op, value: f.value })),
      includeMissing: missing,
    };
    if (mode === 'fields') s.fields = multi;
    else {
      s.x = x;
      if (NUMERIC.includes(xField?.type)) s.xBin = bin && Number(bin) > 0 ? Number(bin) : 'auto';
    }
    if (series) s.series = series;
    return s;
  }, [mode, multi.join(), x, bin, series, measure, JSON.stringify(filters), missing]); // eslint-disable-line react-hooks/exhaustive-deps

  const [data, setData] = useState<AggResult | null>(null);
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    if (mode === 'fields' && multi.length < 1) {
      setData(null);
      return;
    }
    setLoading(true);
    const t = setTimeout(() => {
      api<AggResult>(`/api/stats/${ent.slug}/aggregate`, { method: 'POST', body: JSON.stringify(spec) })
        .then(({ data }) => setData(data))
        .catch((e) => toast(e.message, 'error'))
        .finally(() => setLoading(false));
    }, 150);
    return () => clearTimeout(t);
  }, [ent.slug, spec, toast]); // eslint-disable-line react-hooks/exhaustive-deps

  const title =
    (m === 'count' ? 'Nombre de fiches' : `${{ avg: 'Moyenne', min: 'Minimum', max: 'Maximum', sum: 'Somme' }[m]} de ${clean(fields[mField]?.label ?? '')}`) +
    (mode === 'fields' ? ' pour chaque champ' : ` par ${clean(xField?.label ?? '')}`) +
    (series ? `, réparti par ${clean(fields[series].label)}` : '');

  return (
    <div className="explorer">
      <form className="controls" onSubmit={(e) => e.preventDefault()} aria-label="Paramètres du graphique">
        <fieldset className="control">
          <legend className="field-label">Graphique</legend>
          <div className="segmented segmented-sm">
            {(
              [
                ['bar', 'Histogramme'],
                ['line', 'Courbe'],
                ['pie', 'Camembert'],
              ] as const
            ).map(([v, l]) => (
              <label key={v} className={type === v ? 'seg-on' : ''}>
                <input type="radio" name="ctype" checked={type === v} onChange={() => set({ type: v, ...(v === 'pie' ? { series: null, pct: null } : {}) })} />
                {l}
              </label>
            ))}
          </div>
        </fieldset>

        <div className="control">
          <span className="field-label" id="lbl-x">
            Axe des catégories
          </span>
          <div className="segmented segmented-sm" role="radiogroup" aria-labelledby="lbl-x">
            <label className={mode === 'field' ? 'seg-on' : ''}>
              <input type="radio" name="mode" checked={mode === 'field'} onChange={() => set({ mode: null })} />
              Un champ
            </label>
            <label className={mode === 'fields' ? 'seg-on' : ''}>
              <input type="radio" name="mode" checked={mode === 'fields'} onChange={() => set({ mode: 'fields' })} />
              Plusieurs champs oui/non
            </label>
          </div>
          {mode === 'field' ? (
            <FieldSelect ent={ent} id="c-x" value={x} onChange={(v) => set({ x: v, bin: null })} types={DIMENSION} />
          ) : (
            <MultiFieldPicker ent={ent} value={multi} onChange={(v) => set({ fields: v.join(',') })} />
          )}
        </div>

        {numericX && NUMERIC.includes(xField.type) && (
          <div className="control control-sm">
            <label className="field-label" htmlFor="c-bin">
              Largeur des classes
            </label>
            <input id="c-bin" type="number" min={0} step="any" placeholder="Automatique" value={bin} onChange={(e) => set({ bin: e.target.value })} />
          </div>
        )}
        {numericX && ['date', 'datetime'].includes(xField.type) && <p className="control-note muted">Regroupement par année</p>}

        {type !== 'pie' && (
          <div className="control">
            <label className="field-label" htmlFor="c-series">
              Répartir par
            </label>
            <FieldSelect ent={ent} id="c-series" value={series} onChange={(v) => set({ series: v || null })} types={['enum', 'boolean']} allowEmpty emptyLabel="Aucune répartition" exclude={[x]} />
          </div>
        )}

        <div className="control">
          <label className="field-label" htmlFor="c-m">
            Mesure
          </label>
          <div className="row-2">
            <select id="c-m" value={m} onChange={(e) => set({ m: e.target.value === 'count' ? null : `${e.target.value}:${mField || ent.fields.find((f) => NUMERIC.includes(f.type))?.name}`, pct: null })}>
              <option value="count">Nombre de fiches</option>
              <option value="avg">Moyenne de…</option>
              <option value="min">Minimum de…</option>
              <option value="max">Maximum de…</option>
            </select>
            {m !== 'count' && <FieldSelect ent={ent} id="c-mf" value={mField} onChange={(v) => set({ m: `${m}:${v}` })} types={NUMERIC} />}
          </div>
        </div>

        <div className="control control-checks">
          {m === 'count' && type !== 'pie' && (
            <label className="inline-check">
              <input type="checkbox" checked={percent} onChange={(e) => set({ pct: e.target.checked ? '1' : null })} />
              En pourcentage
            </label>
          )}
          {type === 'bar' && series && (
            <label className="inline-check">
              <input type="checkbox" checked={stacked} onChange={(e) => set({ stack: e.target.checked ? '1' : null })} />
              Barres empilées
            </label>
          )}
          <label className="inline-check">
            <input type="checkbox" checked={missing} onChange={(e) => set({ missing: e.target.checked ? null : '0' })} />
            Inclure « Non renseigné »
          </label>
        </div>
      </form>

      <Filters ent={ent} filters={filters} onChange={setFilters} />

      <article className="chart-card chart-card-main" aria-busy={loading}>
        <h2>{title}</h2>
        {filters.length > 0 && (
          <p className="muted filter-summary">
            Filtré sur : {filters.map((f) => clean(fields[f.field].label)).join(', ')}
          </p>
        )}
        {mode === 'fields' && multi.length === 0 ? (
          <p className="chart-empty">Choisissez au moins un champ à comparer.</p>
        ) : data ? (
          <Chart data={data} type={type} stacked={stacked} percent={percent} height={360} title={title} />
        ) : (
          <p className="muted">Calcul…</p>
        )}
      </article>
    </div>
  );
}

function MultiFieldPicker({ ent, value, onChange }: { ent: EntityDef; value: string[]; onChange: (v: string[]) => void }) {
  const [open, setOpen] = useState(value.length === 0);
  const candidates = ent.fields.filter(
    (f) => f.type === 'boolean' || (f.type === 'enum' && (f.options ?? []).some((o) => ['OUI', 'FAITE', 'FAIT', 'POSITIVE'].includes(o.value))),
  );
  return (
    <div className="multi-picker">
      <button type="button" className="btn btn-quiet btn-block" aria-expanded={open} onClick={() => setOpen((o) => !o)}>
        {value.length ? `${value.length} champ${value.length > 1 ? 's' : ''} sélectionné${value.length > 1 ? 's' : ''}` : 'Choisir les champs'}
      </button>
      {open && (
        <div className="multi-list">
          {[...new Set(candidates.map((f) => f.section))].map((section) => (
            <fieldset key={section}>
              <legend>{section}</legend>
              {candidates
                .filter((f) => f.section === section)
                .map((f) => (
                  <label key={f.name} className="inline-check">
                    <input type="checkbox" checked={value.includes(f.name)} onChange={(e) => onChange(e.target.checked ? [...value, f.name] : value.filter((n) => n !== f.name))} />
                    {clean(f.label)}
                  </label>
                ))}
            </fieldset>
          ))}
        </div>
      )}
    </div>
  );
}

function Filters({ ent, filters, onChange }: { ent: EntityDef; filters: Filter[]; onChange: (f: Filter[]) => void }) {
  const fields = Object.fromEntries(ent.fields.map((f) => [f.name, f]));
  const add = () => {
    const f = ent.fields.find((x) => x.name === 'sexe') ?? ent.fields.find((x) => DIMENSION.includes(x.type))!;
    onChange([...filters, { field: f.name, op: f.type === 'enum' || f.type === 'boolean' ? 'in' : 'between', value: [] }]);
  };
  const update = (i: number, f: Filter) => onChange(filters.map((x, j) => (j === i ? f : x)));
  return (
    <section className="filters" aria-label="Filtres">
      {filters.map((flt, i) => {
        const f = fields[flt.field];
        const choices: { value: string; label: string }[] =
          f.type === 'enum' ? [...(f.options ?? []), { value: MISSING, label: 'Non renseigné' }] : f.type === 'boolean' ? [{ value: 'true', label: 'Oui' }, { value: 'false', label: 'Non' }, { value: MISSING, label: 'Non renseigné' }] : [];
        return (
          <div key={i} className="filter-row">
            <FieldSelect
              ent={ent}
              id={`flt-${i}`}
              value={flt.field}
              types={DIMENSION}
              onChange={(name) => {
                const nf = fields[name];
                update(i, { field: name, op: nf.type === 'enum' || nf.type === 'boolean' ? 'in' : 'between', value: [] });
              }}
            />
            {flt.op === 'in' ? (
              <div className="chips">
                {choices.map((c) => {
                  const on = flt.value.includes(c.value);
                  return (
                    <label key={c.value} className={`chip ${on ? 'chip-on' : ''}`}>
                      <input type="checkbox" checked={on} onChange={(e) => update(i, { ...flt, value: e.target.checked ? [...flt.value, c.value] : flt.value.filter((v) => v !== c.value) })} />
                      {c.label}
                    </label>
                  );
                })}
              </div>
            ) : (
              <div className="row-2 range">
                <input type="number" aria-label="Minimum" placeholder={['date', 'datetime'].includes(f.type) ? 'Année min.' : 'Min.'} value={flt.value[0] ?? ''} onChange={(e) => update(i, { ...flt, value: [e.target.value === '' ? null : Number(e.target.value), flt.value[1] ?? null] })} />
                <input type="number" aria-label="Maximum" placeholder={['date', 'datetime'].includes(f.type) ? 'Année max.' : 'Max.'} value={flt.value[1] ?? ''} onChange={(e) => update(i, { ...flt, value: [flt.value[0] ?? null, e.target.value === '' ? null : Number(e.target.value)] })} />
              </div>
            )}
            <button type="button" className="link-btn danger" onClick={() => onChange(filters.filter((_, j) => j !== i))}>
              Retirer
            </button>
          </div>
        );
      })}
      <button type="button" className="btn btn-quiet" onClick={add}>
        Ajouter un filtre
      </button>
    </section>
  );
}
