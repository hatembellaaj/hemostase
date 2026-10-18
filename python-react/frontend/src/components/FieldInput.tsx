import type { FieldDef } from '../types';

interface Props {
  field: FieldDef;
  value: unknown;
  onChange: (v: unknown) => void;
  error?: string;
  readOnly?: boolean;
}

export default function FieldInput({ field: f, value, onChange, error, readOnly }: Props) {
  const id = `f_${f.name}`;
  const describedBy = error ? `${id}_err` : undefined;
  const common = { id, 'aria-invalid': !!error, 'aria-describedby': describedBy, disabled: readOnly };
  const label = (
    <label htmlFor={id} className="field-label">
      {f.label}
      {f.required && <span className="req" aria-label="obligatoire">*</span>}
    </label>
  );
  const err = error && (
    <p className="field-error" id={describedBy}>
      {error}
    </p>
  );

  if (f.type === 'boolean') {
    return (
      <div className="field field-check">
        <input type="checkbox" {...common} checked={value === true} onChange={(e) => onChange(e.target.checked)} />
        {label}
        {err}
      </div>
    );
  }

  if (f.widget === 'multicheck' && f.choices) {
    const current = typeof value === 'string' && value ? value.split(',').map((s) => s.trim()) : [];
    const toggle = (c: string, on: boolean) => {
      const next = on ? [...current, c] : current.filter((x) => x !== c);
      onChange(next.join(', '));
    };
    return (
      <fieldset className="field field-wide">
        <legend className="field-label">{f.label}</legend>
        <div className="chips">
          {f.choices.map((c) => (
            <label key={c} className={`chip ${current.includes(c) ? 'chip-on' : ''}`}>
              <input type="checkbox" checked={current.includes(c)} onChange={(e) => toggle(c, e.target.checked)} disabled={readOnly} />
              {c}
            </label>
          ))}
        </div>
        {err}
      </fieldset>
    );
  }

  const shortOptions = !!f.options && f.options.length <= 4 && f.options.reduce((n, o) => n + o.label.length, 0) <= 18;
  if (f.type === 'enum' && (f.widget === 'radio' || (shortOptions && !f.required))) {
    return (
      <fieldset className="field">
        <legend className="field-label">
          {f.label}
          {f.required && <span className="req">*</span>}
        </legend>
        <div className="segmented" role="radiogroup" aria-describedby={describedBy}>
          {f.options!.map((o) => (
            <label key={o.value} className={value === o.value ? 'seg-on' : ''}>
              <input
                type="radio"
                name={id}
                value={o.value}
                checked={value === o.value}
                onChange={() => onChange(o.value)}
                onClick={() => value === o.value && !f.required && onChange(null)}
                disabled={readOnly}
              />
              {o.label}
            </label>
          ))}
        </div>
        {err}
      </fieldset>
    );
  }

  if (f.type === 'enum') {
    return (
      <div className="field">
        {label}
        <select {...common} value={(value as string) ?? ''} onChange={(e) => onChange(e.target.value || null)}>
          <option value="">{f.required ? 'Choisir…' : '—'}</option>
          {f.options!.map((o) => (
            <option key={o.value} value={o.value}>
              {o.label}
            </option>
          ))}
        </select>
        {err}
      </div>
    );
  }

  const str = value === null || value === undefined ? '' : String(value);
  if (f.widget === 'textarea' || (f.type === 'string' && (f.maxLength ?? 0) > 255)) {
    return (
      <div className="field field-wide">
        {label}
        <textarea {...common} rows={3} maxLength={f.maxLength} value={str} onChange={(e) => onChange(e.target.value)} />
        {err}
      </div>
    );
  }

  const type = f.type === 'date' ? 'date' : f.type === 'integer' || f.type === 'float' ? 'number' : 'text';
  return (
    <div className="field">
      {label}
      <input
        {...common}
        type={type}
        inputMode={f.type === 'float' ? 'decimal' : f.type === 'integer' ? 'numeric' : undefined}
        step={f.type === 'float' ? 'any' : f.type === 'integer' ? 1 : undefined}
        min={f.min}
        max={f.max}
        maxLength={f.maxLength}
        value={type === 'date' ? str.slice(0, 10) : str}
        readOnly={readOnly}
        className={type === 'number' ? 'num' : undefined}
        onChange={(e) => onChange(e.target.value)}
      />
      {err}
    </div>
  );
}
