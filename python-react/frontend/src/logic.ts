import type { Computed, Condition, EntityDef, FieldDef, Fiche } from './types';

const empty = (v: unknown) => v === null || v === undefined || v === '';

export function isVisible(cond: Condition | undefined, values: Fiche): boolean {
  if (!cond) return true;
  if ('all' in cond) return cond.all.every((c) => isVisible(c, values));
  if ('any' in cond) return cond.any.some((c) => isVisible(c, values));
  const v = values[cond.field];
  switch (cond.op) {
    case 'truthy':
      return !empty(v) && v !== false;
    case 'falsy':
      return empty(v) || v === false;
    case 'filled':
      return !empty(v);
    case 'eq':
      return v === cond.value || (typeof cond.value === 'number' && Number(v) === cond.value);
    case 'ne':
      return v !== cond.value;
    case 'gt':
      return !empty(v) && Number(v) > Number(cond.value);
    case 'gte':
      return !empty(v) && Number(v) >= Number(cond.value);
    case 'lt':
      return !empty(v) && Number(v) < Number(cond.value);
    case 'lte':
      return !empty(v) && Number(v) <= Number(cond.value);
  }
  return true;
}

export function ageFrom(iso: unknown): number | null {
  if (typeof iso !== 'string' || !/^\d{4}-\d{2}-\d{2}/.test(iso)) return null;
  const [y, m, d] = iso.slice(0, 10).split('-').map(Number);
  const now = new Date();
  let age = now.getFullYear() - y;
  if (now.getMonth() + 1 < m || (now.getMonth() + 1 === m && now.getDate() < d)) age--;
  return age >= 0 ? age : null;
}

/** Calculs automatiques repris des formulaires Angular d'origine (âge, ratios VWF). */
export function applyComputed(computed: Computed[], values: Fiche): Fiche {
  const out = { ...values };
  for (const c of computed) {
    if (c.fn === 'age') {
      const a = ageFrom(out[c.args[0]]);
      if (!empty(out[c.args[0]])) out[c.field] = a;
    } else if (c.fn === 'ratio') {
      const num = Number(out[c.args[0]]);
      const den = Number(out[c.args[1]]);
      if (num && den) out[c.field] = Math.round((num / den) * 100) / 100;
    }
  }
  return out;
}

export function isComputed(ent: EntityDef, name: string) {
  return ent.computed.some((c) => c.field === name);
}

export function displayValue(f: FieldDef, v: unknown): string {
  if (empty(v)) return '';
  if (f.type === 'boolean') return v ? 'Oui' : 'Non';
  if (f.type === 'enum') return f.options?.find((o) => o.value === v)?.label ?? String(v);
  if (f.type === 'date' && typeof v === 'string') {
    const [y, m, d] = v.slice(0, 10).split('-');
    return `${d}/${m}/${y}`;
  }
  if (f.type === 'float' && typeof v === 'number') return v.toLocaleString('fr-FR', { maximumFractionDigits: 3 });
  return String(v);
}

export function isFilled(f: FieldDef, v: unknown) {
  if (f.type === 'boolean') return v === true;
  return !empty(v);
}
