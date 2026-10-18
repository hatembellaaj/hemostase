import { useEffect, useMemo, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { api } from '../api';
import SectionRail from '../components/SectionRail';
import { displayValue, isFilled, isVisible } from '../logic';
import { useApp, useToast } from '../state';
import type { Fiche } from '../types';

export default function FicheDetail() {
  const { slug = '', id } = useParams();
  const { entity } = useApp();
  const ent = entity(slug);
  const nav = useNavigate();
  const toast = useToast();
  const [fiche, setFiche] = useState<Fiche | null>(null);
  const [showEmpty, setShowEmpty] = useState(false);
  const [confirm, setConfirm] = useState(false);

  useEffect(() => {
    api<Fiche>(`/api/fiches/${slug}/${id}`)
      .then(({ data }) => setFiche(data))
      .catch((e) => toast(e.message, 'error'));
  }, [slug, id, toast]);

  const sections = useMemo(() => {
    if (!ent || !fiche) return [];
    return ent.sections.map((title) => {
      const all = ent.fields.filter((f) => f.section === title && isVisible(f.visibleIf, fiche));
      const filled = all.filter((f) => isFilled(f, fiche[f.name]));
      return { title, fields: showEmpty ? all : filled, filled: filled.length, total: all.length };
    });
  }, [ent, fiche, showEmpty]);

  if (!ent) return <div className="page">Registre inconnu.</div>;
  if (!fiche) return <div className="page muted">Chargement de la fiche…</div>;

  const remove = async () => {
    try {
      await api(`/api/fiches/${slug}/${id}`, { method: 'DELETE' });
      toast('Fiche supprimée');
      nav(`/fiches/${slug}`);
    } catch (e) {
      toast((e as Error).message, 'error');
    }
  };

  const who = [fiche.nom, fiche.prenom].filter(Boolean).join(' ');

  return (
    <div className="page">
      <header className="page-head">
        <div>
          <p className="crumbs">
            <Link to={`/fiches/${slug}`}>{ent.title}</Link>
          </p>
          <h1>
            Fiche n° {fiche.id}
            {who && ` — ${who}`}
          </h1>
          {fiche.dossierNumber ? <p className="muted">Dossier {String(fiche.dossierNumber)}</p> : null}
        </div>
        <div className="head-actions">
          <label className="toggle">
            <input type="checkbox" checked={showEmpty} onChange={(e) => setShowEmpty(e.target.checked)} />
            Afficher les champs vides
          </label>
          <button className="btn btn-danger-quiet" onClick={() => setConfirm(true)}>
            Supprimer
          </button>
          <Link to={`/fiches/${slug}/${id}/edit`} className="btn btn-primary">
            Modifier
          </Link>
        </div>
      </header>

      {confirm && (
        <div className="confirm" role="alertdialog" aria-labelledby="cf-t">
          <p id="cf-t">La fiche n° {fiche.id} sera supprimée définitivement.</p>
          <button className="btn btn-quiet" onClick={() => setConfirm(false)} autoFocus>
            Garder la fiche
          </button>
          <button className="btn btn-danger" onClick={remove}>
            Supprimer la fiche
          </button>
        </div>
      )}

      <div className="form-layout">
        <SectionRail items={sections} />
        <div className="form-sections">
          {sections.map((s, i) => (
            <section key={s.title} id={`sec-${i}`} className="form-section" aria-labelledby={`sec-${i}-h`}>
              <h2 id={`sec-${i}-h`}>{s.title}</h2>
              {s.fields.length === 0 ? (
                <p className="muted">Rien de renseigné dans cette section.</p>
              ) : (
                <dl className="dl">
                  {s.fields.map((f) => (
                    <div key={f.name} className={f.widget === 'multicheck' ? 'dl-wide' : undefined}>
                      <dt>{f.label}</dt>
                      <dd className={f.type === 'integer' || f.type === 'float' ? 'num' : undefined}>{displayValue(f, fiche[f.name]) || <span className="muted">—</span>}</dd>
                    </div>
                  ))}
                </dl>
              )}
            </section>
          ))}
        </div>
      </div>
    </div>
  );
}
