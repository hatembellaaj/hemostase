import { useMemo, useState } from 'react';
import {
  Bar,
  BarChart,
  CartesianGrid,
  Cell,
  Legend,
  Line,
  LineChart,
  Pie,
  PieChart,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from 'recharts';

/* ------------------------------------------------------------------ */
/* Types renvoyés par /api/stats/...                                    */
/* ------------------------------------------------------------------ */
export interface AggKey {
  key: string | number;
  label: string;
}
export interface AggResult {
  x: { field?: string; label: string; bin?: number | string | null };
  measure: string;
  categories: AggKey[];
  series: AggKey[];
  cells: Record<string, Record<string, number | null>>;
  total: number;
}
export type ChartType = 'bar' | 'line' | 'pie';

/* Palette catégorielle validée (ordre fixe, jamais recyclé) */
export const SERIES = ['#2a78d6', '#eb6834', '#1baf7a', '#eda100', '#e87ba4', '#008300', '#6250d6', '#e34948'];
const MISSING_COLOR = '#b9c1cd';
const OTHER_COLOR = '#8a93a3';
const MAX_SERIES = 8;
const MAX_SLICES = 6;
export const MISSING = '__missing__';

const fmt = (v: number | null | undefined, digits = 1) =>
  v === null || v === undefined || Number.isNaN(v) ? '—' : v.toLocaleString('fr-FR', { maximumFractionDigits: digits });

interface Prepared {
  rows: Record<string, string | number | null>[];
  series: { id: string; label: string; color: string }[];
}

/** Données du serveur -> lignes Recharts, séries colorées (couleur liée à la valeur, pas au rang). */
function prepare(d: AggResult, percent: boolean, isCount: boolean): Prepared {
  let series = d.series.map((s, i) => ({
    key: String(s.key),
    id: `s${i}`,
    label: s.label,
    color: s.key === MISSING ? MISSING_COLOR : SERIES[i % SERIES.length],
  }));
  // au-delà de 8 séries : regroupement dans « Autres »
  let fold: string[] = [];
  const real = series.filter((s) => s.key !== MISSING);
  if (real.length > MAX_SERIES) {
    fold = real.slice(MAX_SERIES - 1).map((s) => s.key);
    series = [...real.slice(0, MAX_SERIES - 1), { key: '__other__', id: 'sOther', label: 'Autres', color: OTHER_COLOR }, ...series.filter((s) => s.key === MISSING)];
  }
  const rows = d.categories.map((c) => {
    const cell = d.cells[String(c.key)] ?? {};
    const row: Record<string, string | number | null> = { label: c.label };
    let rowTotal = 0;
    for (const s of series) {
      const v = s.key === '__other__' ? fold.reduce((n, k) => n + (cell[k] ?? 0), 0) : cell[s.key] ?? null;
      row[s.id] = v;
      if (typeof v === 'number') rowTotal += v;
    }
    row.__total = rowTotal;
    return row;
  });
  if (percent && isCount) {
    const grand = rows.reduce((n, r) => n + (r.__total as number), 0);
    for (const r of rows) {
      const base = series.length > 1 ? (r.__total as number) : grand;
      for (const s of series) if (typeof r[s.id] === 'number') r[s.id] = base ? ((r[s.id] as number) / base) * 100 : 0;
      r.__total = series.length > 1 ? 100 : (r[series[0].id] as number);
    }
  }
  return { rows, series };
}

function TooltipBox({ active, payload, label, unit, digits }: any) {
  if (!active || !payload?.length) return null;
  return (
    <div className="chart-tip">
      <p className="chart-tip-title">{label ?? payload[0]?.payload?.label}</p>
      {payload.map((p: any) => (
        <p key={p.dataKey ?? p.name} className="chart-tip-row">
          <span className="swatch" style={{ background: p.color ?? p.payload?.fill }} aria-hidden="true" />
          <span>{p.name}</span>
          <strong>
            {fmt(p.value, digits)}
            {unit}
          </strong>
        </p>
      ))}
    </div>
  );
}

interface ChartProps {
  data: AggResult;
  type: ChartType;
  stacked?: boolean;
  horizontal?: boolean;
  percent?: boolean;
  height?: number;
  title?: string;
}

export function Chart({ data, type, stacked, horizontal, percent = false, height = 300, title }: ChartProps) {
  const [showTable, setShowTable] = useState(false);
  const isCount = data.measure === 'Nombre de fiches';
  const { rows, series } = useMemo(() => prepare(data, percent, isCount), [data, percent, isCount]);
  const unit = percent && isCount ? ' %' : '';
  const digits = isCount && !percent ? 0 : 1;
  const longLabels = rows.some((r) => String(r.label).length > 14) || rows.length > 10;
  const horiz = type === 'bar' && (horizontal || longLabels);
  const empty = rows.length === 0 || rows.every((r) => !r.__total);
  const axis = { fontSize: 12, fill: '#5b6577' };

  let body: React.ReactNode;
  if (empty) {
    body = <p className="chart-empty">Aucune fiche ne correspond à ces critères.</p>;
  } else if (type === 'pie') {
    // une seule série ; au-delà de 6 parts : « Autres »
    const sid = series[0].id;
    let slices = rows.map((r, i) => ({
      label: String(r.label),
      value: (r[sid] as number) ?? 0,
      fill: r.label === 'Non renseigné' ? MISSING_COLOR : SERIES[i % SERIES.length],
    })).filter((s) => s.value > 0);
    if (slices.length > MAX_SLICES + 1) {
      const keep = [...slices].sort((a, b) => b.value - a.value).slice(0, MAX_SLICES);
      const rest = slices.filter((s) => !keep.includes(s));
      slices = [...slices.filter((s) => keep.includes(s)), { label: 'Autres', value: rest.reduce((n, s) => n + s.value, 0), fill: OTHER_COLOR }];
    }
    const sum = slices.reduce((n, s) => n + s.value, 0);
    body = (
      <div className="pie-wrap">
        <ResponsiveContainer width="100%" height={height}>
          <PieChart>
            <Pie data={slices} dataKey="value" nameKey="label" innerRadius="55%" outerRadius="85%" paddingAngle={0} stroke="#ffffff" strokeWidth={2} isAnimationActive={false}>
              {slices.map((s) => (
                <Cell key={s.label} fill={s.fill} />
              ))}
            </Pie>
            <Tooltip content={<TooltipBox unit={unit} digits={digits} />} />
          </PieChart>
        </ResponsiveContainer>
        <ul className="pie-legend">
          {slices.map((s) => (
            <li key={s.label}>
              <span className="swatch" style={{ background: s.fill }} aria-hidden="true" />
              <span className="pie-legend-label">{s.label}</span>
              <span className="num">{fmt(s.value, digits)}{unit}</span>
              <span className="num muted">{fmt((s.value / sum) * 100, 0)} %</span>
            </li>
          ))}
        </ul>
      </div>
    );
  } else if (type === 'line') {
    body = (
      <ResponsiveContainer width="100%" height={height}>
        <LineChart data={rows} margin={{ top: 8, right: 16, bottom: 4, left: 0 }}>
          <CartesianGrid vertical={false} stroke="#e8ebf0" />
          <XAxis dataKey="label" tick={axis} tickLine={false} axisLine={{ stroke: '#dce1e8' }} interval="preserveStartEnd" />
          <YAxis tick={axis} tickLine={false} axisLine={false} width={44} unit={unit} />
          <Tooltip content={<TooltipBox unit={unit} digits={digits} />} cursor={{ stroke: '#b9c1cd', strokeDasharray: '3 3' }} />
          {series.length > 1 && <Legend iconType="plainline" wrapperStyle={{ fontSize: 12 }} formatter={(v) => <span style={{ color: '#1c2433' }}>{v}</span>} />}
          {series.map((s) => (
            <Line key={s.id} type="monotone" dataKey={s.id} name={s.label} stroke={s.color} strokeWidth={2} dot={{ r: 4, strokeWidth: 2, stroke: '#fff', fill: s.color }} activeDot={{ r: 6 }} connectNulls isAnimationActive={false} />
          ))}
        </LineChart>
      </ResponsiveContainer>
    );
  } else {
    const h = horiz ? Math.max(height, rows.length * (stacked || series.length === 1 ? 30 : 18 * series.length) + 60) : height;
    body = (
      <ResponsiveContainer width="100%" height={h}>
        <BarChart data={rows} layout={horiz ? 'vertical' : 'horizontal'} margin={{ top: 8, right: 16, bottom: 4, left: 0 }} barGap={2} barCategoryGap="20%">
          <CartesianGrid vertical={horiz} horizontal={!horiz} stroke="#e8ebf0" />
          {horiz ? (
            <>
              <XAxis type="number" tick={axis} tickLine={false} axisLine={false} unit={unit} />
              <YAxis type="category" dataKey="label" tick={axis} tickLine={false} axisLine={{ stroke: '#dce1e8' }} width={170} />
            </>
          ) : (
            <>
              <XAxis dataKey="label" tick={axis} tickLine={false} axisLine={{ stroke: '#dce1e8' }} interval={0} />
              <YAxis tick={axis} tickLine={false} axisLine={false} width={44} unit={unit} />
            </>
          )}
          <Tooltip content={<TooltipBox unit={unit} digits={digits} />} cursor={{ fill: 'rgba(28,36,51,0.05)' }} />
          {series.length > 1 && <Legend iconType="square" wrapperStyle={{ fontSize: 12 }} formatter={(v) => <span style={{ color: '#1c2433' }}>{v}</span>} />}
          {series.map((s, i) => {
            const end = !stacked || i === series.length - 1;
            const radius: [number, number, number, number] = end ? (horiz ? [0, 4, 4, 0] : [4, 4, 0, 0]) : [0, 0, 0, 0];
            return (
              <Bar key={s.id} dataKey={s.id} name={s.label} fill={s.color} stackId={stacked ? 'a' : undefined} radius={radius} maxBarSize={44} stroke="#ffffff" strokeWidth={stacked ? 1 : 0} isAnimationActive={false} />
            );
          })}
        </BarChart>
      </ResponsiveContainer>
    );
  }

  const csv = () => {
    const head = [data.x.label, ...series.map((s) => s.label), ...(series.length > 1 ? ['Total'] : [])];
    const lines = rows.map((r) => [r.label, ...series.map((s) => r[s.id] ?? ''), ...(series.length > 1 ? [r.__total] : [])]);
    const text = [head, ...lines].map((l) => l.map((v) => `"${String(v ?? '').replace(/"/g, '""')}"`).join(';')).join('\n');
    const url = URL.createObjectURL(new Blob(['﻿' + text], { type: 'text/csv;charset=utf-8' }));
    const a = document.createElement('a');
    a.href = url;
    a.download = `${(title ?? data.x.label).replace(/[^\p{L}\p{N}]+/gu, '_')}.csv`;
    a.click();
    URL.revokeObjectURL(url);
  };

  return (
    <div className="chart">
      <div role="img" aria-label={`${title ?? data.x.label} : ${data.measure}`}>{body}</div>
      {!empty && (
        <div className="chart-foot">
          <span className="muted">
            {data.measure}
            {unit && (series.length > 1 ? ', en % de chaque catégorie' : ', en % du total')}, sur {data.total} fiche{data.total > 1 ? 's' : ''}
          </span>
          <button className="link-btn" onClick={() => setShowTable((v) => !v)} aria-expanded={showTable}>
            {showTable ? 'Masquer le tableau' : 'Voir le tableau'}
          </button>
          <button className="link-btn" onClick={csv}>
            Exporter en CSV
          </button>
        </div>
      )}
      {showTable && !empty && (
        <div className="table-wrap chart-table">
          <table className="table table-static">
            <thead>
              <tr>
                <th>{data.x.label}</th>
                {series.map((s) => (
                  <th key={s.id} className="num">
                    {s.label}
                  </th>
                ))}
                {series.length > 1 && <th className="num">Total</th>}
              </tr>
            </thead>
            <tbody>
              {rows.map((r) => (
                <tr key={String(r.label)}>
                  <td>{r.label}</td>
                  {series.map((s) => (
                    <td key={s.id} className="num">
                      {fmt(r[s.id] as number, digits)}
                      {r[s.id] !== null && r[s.id] !== undefined ? unit : ''}
                    </td>
                  ))}
                  {series.length > 1 && <td className="num">{fmt(r.__total as number, digits)}{unit}</td>}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}
