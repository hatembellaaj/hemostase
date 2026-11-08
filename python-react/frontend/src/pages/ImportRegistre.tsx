import { useCallback, useEffect, useState, type ChangeEvent } from 'react';
import { ApiError, api, getToken } from '../api';
import { useToast } from '../state';

interface Registre {
  slug: string;
  title: string;
  disponible: boolean;
  nonCodees: number;
}

interface Alerte {
  ligne: string | null;
  patient: string | null;
  champ: string | null;
  'valeur Excel': string | null;
  problème: string | null;
  'valeur retenue': string | null;
}

interface Apercu {
  ligne: number;
  code: string;
  nom: string | null;
  prenom: string | null;
  dateNaissance: string | null;
  formeClinique: string | null;
  severite: string | null;
  dejaPresente: boolean;
}

interface Preview {
  valide: boolean;
  erreurs: string[];
  nbFiches: number;
  nbNouvelles: number;
  nbDejaPresentes: number;
  alertes: Alerte[];
  apercu: Apercu[];
}

const RAW = { 'Content-Type': 'application/octet-stream' };

/** POST qui renvoie un fichier à enregistrer (classeur de contrôle, correspondance). */
async function postDownload(path: string, body: Blob | null, filename: string) {
  const res = await fetch(path, { method: 'POST', body, headers: { ...RAW, Authorization: `Bearer ${getToken()}` } });
  if (!res.ok) {
    let msg = `Erreur ${res.status}`;
    try {
      const d = (await res.json()).detail;
      msg = typeof d === 'string' ? d : (d?.message ?? msg);
    } catch {
      /* corps vide */
    }
    throw new ApiError(res.status, msg);
  }
  const url = URL.createObjectURL(await res.blob());
  const a = document.createElement('a');
  a.href = url;
  a.download = filename;
  a.click();
  URL.revokeObjectURL(url);
}

export default function ImportRegistre() {
  const toast = useToast();
  const [registres, setRegistres] = useState<Registre[]>([]);
  const [slug, setSlug] = useState('');
  const [file, setFile] = useState<File | null>(null);
  const [preview, setPreview] = useState<Preview | null>(null);
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const loadRegistres = useCallback(() => {
    api<Registre[]>('/api/admin/import').then(({ data }) => {
      setRegistres(data);
      setSlug((s) => s || data.find((r) => r.disponible)?.slug || '');
    });
  }, []);
  useEffect(loadRegistres, [loadRegistres]);

  const reg = registres.find((r) => r.slug === slug);
  const base = `/api/admin/import/${slug}`;

  const check = async (f: File, target = slug) => {
    setBusy(true);
    setError(null);
    setPreview(null);
    try {
      const { data } = await api<Preview>(`/api/admin/import/${target}/preview`, { method: 'POST', body: f, headers: RAW });
      setPreview(data);
    } catch (err) {
      setError((err as Error).message);
    } finally {
      setBusy(false);
    }
  };

  const chooseRegistre = (e: ChangeEvent<HTMLSelectElement>) => {
    setSlug(e.target.value);
    setPreview(null);
    setError(null);
    const r = registres.find((x) => x.slug === e.target.value);
    if (file && r?.disponible) check(file, r.slug);
  };

  const chooseFile = (e: ChangeEvent<HTMLInputElement>) => {
    const f = e.target.files?.[0] ?? null;
    setFile(f);
    if (f) check(f);
  };

  const download = async (path: string, body: Blob | null, filename: string) => {
    try {
      await postDownload(path, body, filename);
    } catch (err) {
      toast((err as Error).message, 'error');
    }
  };

  const runImport = async () => {
    if (!file || !preview || !reg) return;
    if (!window.confirm(`Importer ${preview.nbNouvelles} fiche(s) dans le registre « ${reg.title} » ? Les noms et prénoms seront remplacés par leur code.`)) return;
    setBusy(true);
    try {
      const { data } = await api<{ importees: number; ignoreesDejaPresentes: number }>(base, { method: 'POST', body: file, headers: RAW });
      toast(`${data.importees} fiche(s) importée(s) dans « ${reg.title} »${data.ignoreesDejaPresentes ? `, ${data.ignoreesDejaPresentes} déjà présente(s)` : ''}`);
      await check(file);
      loadRegistres();
    } catch (err) {
      toast(err instanceof ApiError ? err.message : 'Import impossible', 'error');
      setBusy(false);
    }
  };

  const codeExisting = async () => {
    if (!reg) return;
    if (!window.confirm(`Remplacer les noms et prénoms de ${reg.nonCodees} fiche(s) du registre « ${reg.title} » par leur code ?\n\nLe classeur de correspondance va être téléchargé : conservez-le hors du serveur, c'est la seule trace des noms.`)) return;
    setBusy(true);
    try {
      await postDownload(`${base}/code-existing`, null, `correspondance_existantes_${slug}.xlsx`);
      toast(`Noms codés dans « ${reg.title} » ; correspondance téléchargée`);
      loadRegistres();
    } catch (err) {
      toast((err as Error).message, 'error');
    } finally {
      setBusy(false);
    }
  };

  return (
    <div className="page">
      <header className="page-head">
        <div>
          <h1>Importer un registre</h1>
          <p className="muted">
            Choisissez le registre, puis son classeur Excel. Le fichier est d'abord contrôlé : rien n'est enregistré avant votre
            confirmation. Les noms et prénoms sont remplacés par un code (ex. HEM-3F9A21C4E7) ; la correspondance code ↔ identité
            n'est jamais enregistrée sur le serveur et se télécharge à part.
          </p>
        </div>
      </header>

      <section className="panel">
        <div className="grid">
          <div className="field">
            <label className="field-label" htmlFor="import_registre">
              Registre concerné
            </label>
            <select id="import_registre" value={slug} onChange={chooseRegistre} disabled={busy}>
              {registres.map((r) => (
                <option key={r.slug} value={r.slug}>
                  {r.title}
                  {r.disponible ? '' : ' (pas encore de format d’import)'}
                </option>
              ))}
            </select>
          </div>
          <div className="field">
            <label className="field-label" htmlFor="import_file">
              Fichier Excel (.xlsx)
            </label>
            <input
              id="import_file"
              type="file"
              accept=".xlsx,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
              onChange={chooseFile}
              disabled={busy || !reg?.disponible}
            />
          </div>
        </div>
        {reg && !reg.disponible && (
          <p className="muted">Aucun convertisseur Excel n'existe encore pour « {reg.title} » : l'import n'est possible que pour l'hémophilie.</p>
        )}
        {busy && <p className="muted">Traitement en cours…</p>}
        {error && <p className="form-error">{error}</p>}
      </section>

      {reg && reg.nonCodees > 0 && (
        <section className="panel">
          <h2>Fiches existantes aux noms en clair</h2>
          <p className="muted">
            {reg.nonCodees} fiche(s) du registre « {reg.title} » portent encore un nom ou un prénom en clair (saisie manuelle ou import
            antérieur). Vous pouvez les coder avec la même méthode ; le classeur de correspondance sera téléchargé.
          </p>
          <div className="panel-actions">
            <button className="btn btn-danger-quiet" onClick={codeExisting} disabled={busy}>
              Coder les {reg.nonCodees} fiche(s) existante(s)
            </button>
          </div>
        </section>
      )}

      {preview && reg && (
        <>
          <section className="kpis" aria-label="Résultat du contrôle">
            {(
              [
                [preview.nbFiches, 'fiches dans le fichier'],
                [preview.nbNouvelles, 'à importer'],
                [preview.nbDejaPresentes, 'déjà présentes'],
                [preview.alertes.length, 'alertes de conversion'],
              ] as const
            ).map(([v, l]) => (
              <div key={l} className="kpi">
                <p className="kpi-value num">{v}</p>
                <p className="kpi-label">{l}</p>
              </div>
            ))}
          </section>

          {preview.erreurs.length > 0 && (
            <section className="panel">
              <h2>Import impossible</h2>
              <p className="muted">Vérifiez que le fichier correspond bien au registre « {reg.title} », corrigez-le puis choisissez-le à nouveau.</p>
              <ul>
                {preview.erreurs.map((e) => (
                  <li key={e} className="form-error">
                    {e}
                  </li>
                ))}
              </ul>
            </section>
          )}

          <div className="actionbar">
            <button className="btn btn-quiet" onClick={() => file && download(`${base}/report`, file, `controle_import_${slug}.xlsx`)} disabled={busy}>
              Classeur de contrôle
            </button>
            <button className="btn btn-quiet" onClick={() => file && download(`${base}/correspondence`, file, `correspondance_${slug}.xlsx`)} disabled={busy || !preview.valide}>
              Correspondance code ↔ identité
            </button>
            <button className="btn btn-primary" onClick={runImport} disabled={busy || !preview.valide || preview.nbNouvelles === 0}>
              {preview.nbNouvelles ? `Importer ${preview.nbNouvelles} fiche(s) dans « ${reg.title} »` : 'Rien à importer'}
            </button>
          </div>

          {preview.alertes.length > 0 && (
            <details className="panel">
              <summary>
                {preview.alertes.length} alerte(s) de conversion : valeurs à vérifier, les fiches sont importées avec la valeur retenue
              </summary>
              <div className="table-wrap">
                <table className="table table-static">
                  <thead>
                    <tr>
                      <th>Ligne</th>
                      <th>Patient</th>
                      <th>Champ</th>
                      <th>Valeur Excel</th>
                      <th>Problème</th>
                      <th>Valeur retenue</th>
                    </tr>
                  </thead>
                  <tbody>
                    {preview.alertes.map((a, i) => (
                      <tr key={i}>
                        <td className="num">{a.ligne}</td>
                        <td>{a.patient}</td>
                        <td>{a.champ}</td>
                        <td>{a['valeur Excel']}</td>
                        <td>{a.problème}</td>
                        <td>{a['valeur retenue']}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            </details>
          )}

          <div className="table-wrap">
            <table className="table table-static">
              <thead>
                <tr>
                  <th>Ligne</th>
                  <th>Code enregistré</th>
                  <th>Nom (fichier)</th>
                  <th>Prénom (fichier)</th>
                  <th>Naissance</th>
                  <th>Forme</th>
                  <th>Sévérité</th>
                  <th>Statut</th>
                </tr>
              </thead>
              <tbody>
                {preview.apercu.map((a) => (
                  <tr key={a.ligne}>
                    <td className="num">{a.ligne}</td>
                    <td>
                      <code>{a.code}</code>
                    </td>
                    <td>{a.nom}</td>
                    <td>{a.prenom}</td>
                    <td className="num">{a.dateNaissance ? new Date(a.dateNaissance).toLocaleDateString('fr-FR') : ''}</td>
                    <td>{a.formeClinique}</td>
                    <td>{a.severite}</td>
                    <td>{a.dejaPresente ? <span className="muted">Déjà présente</span> : 'Nouvelle'}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </>
      )}
    </div>
  );
}
