import { useEffect, useMemo, useState, type FormEvent } from 'react';
import { Link, useBlocker, useNavigate, useParams } from 'react-router-dom';
import { api, ApiError } from '../api';
import FieldInput from '../components/FieldInput';
import SectionRail from '../components/SectionRail';
import { applyComputed, isComputed, isFilled, isVisible } from '../logic';
import { useApp, useToast } from '../state';
import type { Fiche } from '../types';

export default function FicheForm() {
  const { slug = '', id } = useParams();
  const { entity } = useApp();
  const ent = entity(slug);
  const nav = useNavigate();
  const toast = useToast();
  const [values, setValues] = useState<Fiche>({});
  const [errors, setErrors] = useState<Record<string, string>>({});
  const [loading, setLoading] = useState(!!id);
  const [saving, setSaving] = useState(false);
  const [dirty, setDirty] = useState(false);

  useEffect(() => {
    if (!id) return;
    api<Fiche>(`/api/fiches/${slug}/${id}`)
      .then(({ data }) => setValues(data))
      .catch((e) => toast(e.message, 'error'))
      .finally(() => setLoading(false));
  }, [slug, id, toast]);

  const blocker = useBlocker(({ currentLocation, nextLocation }) => dirty && !saving && currentLocation.pathname !== nextLocation.pathname);
  useEffect(() => {
    if (blocker.state === 'blocked') {
      if (window.confirm('Des modifications ne sont pas enregistrées. Quitter quand même ?')) blocker.proceed();
      else blocker.reset();
    }
  }, [blocker]);

  const sections = useMemo(() => {
    if (!ent) return [];
    return ent.sections.map((title) => {
      const fields = ent.fields.filter((f) => f.section === title && isVisible(f.visibleIf, values));
      return { title, fields, filled: fields.filter((f) => isFilled(f, values[f.name])).length, total: fields.length };
    });
  }, [ent, values]);

  if (!ent) return <div className="page">Registre inconnu.</div>;
  if (loading) return <div className="page muted">Chargement de la fiche…</div>;

  const set = (name: string, v: unknown) => {
    setDirty(true);
    setValues((cur) => applyComputed(ent.computed, { ...cur, [name]: v }));
    if (errors[name]) setErrors(({ [name]: _, ...rest }) => rest);
  };

  const save = async (e: FormEvent) => {
    e.preventDefault();
    setSaving(true);
    try {
      const { data } = await api<Fiche>(id ? `/api/fiches/${slug}/${id}` : `/api/fiches/${slug}`, {
        method: id ? 'PUT' : 'POST',
        body: JSON.stringify(values),
      });
      setDirty(false);
      toast(id ? 'Fiche enregistrée' : 'Fiche créée');
      setTimeout(() => nav(`/fiches/${slug}/${data.id}`), 0);
    } catch (err) {
      setSaving(false);
      if (err instanceof ApiError && Object.keys(err.fieldErrors).length) {
        setErrors(err.fieldErrors);
        const first = ent.fields.find((f) => err.fieldErrors[f.name]);
        toast(`${Object.keys(err.fieldErrors).length} champ(s) à corriger`, 'error');
        if (first) document.getElementById(`f_${first.name}`)?.focus();
      } else toast((err as Error).message, 'error');
    }
  };

  const who = [values.nom, values.prenom].filter(Boolean).join(' ');

  return (
    <form className="page page-form" onSubmit={save} noValidate>
      <header className="page-head">
        <div>
          <p className="crumbs">
            <Link to={`/fiches/${slug}`}>{ent.title}</Link>
          </p>
          <h1>{id ? `Modifier la fiche n° ${id}${who ? ` — ${who}` : ''}` : 'Nouvelle fiche'}</h1>
        </div>
      </header>
      <div className="form-layout">
        <SectionRail items={sections} />
        <div className="form-sections">
          {sections.map((s, i) => (
            <section key={s.title} id={`sec-${i}`} className="form-section" aria-labelledby={`sec-${i}-h`}>
              <h2 id={`sec-${i}-h`}>{s.title}</h2>
              {s.fields.length === 0 ? (
                <p className="muted">Les champs de cette section apparaissent selon les réponses précédentes.</p>
              ) : (
                <div className="grid">
                  {s.fields.map((f) => (
                    <FieldInput
                      key={f.name}
                      field={f}
                      value={values[f.name]}
                      onChange={(v) => set(f.name, v)}
                      error={errors[f.name]}
                      readOnly={isComputed(ent, f.name)}
                    />
                  ))}
                </div>
              )}
            </section>
          ))}
        </div>
      </div>
      <div className="actionbar">
        <span className="muted">{dirty ? 'Modifications non enregistrées' : 'Aucune modification'}</span>
        <button type="button" className="btn btn-quiet" onClick={() => nav(-1)}>
          Annuler
        </button>
        <button className="btn btn-primary" disabled={saving}>
          {saving ? 'Enregistrement…' : id ? 'Enregistrer' : 'Créer la fiche'}
        </button>
      </div>
    </form>
  );
}
