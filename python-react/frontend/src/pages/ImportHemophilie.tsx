import { useState, type ChangeEvent } from 'react';
import { ApiError, api, getToken } from '../api';
import { useToast } from '../state';

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

const BASE = '/api/admin/import/hemophilie';
const RAW = { 'Content-Type': 'application/octet-stream' };

export default function ImportHemophilie() {
  const toast = useToast();
  const [file, setFile] = useState<File | null>(null);
  const [preview, setPreview] = useState<Preview | null>(null);
  const [busy, setBusy] = useState(false);
  const [error, setError] = useState<string | null>(null);

  const check = async (f: File) => {
    setBusy(true);
    setError(null);
    setPreview(null);
    try {
      const { data } = await api<Preview>(`${BASE}/preview`, { method: 'POST', body: f, headers: RAW });
      setPreview(data);
    } catch (err) {
      setError((err as Error).message);
    } finally {
      setBusy(false);
    }
  };

  const choose = (e: ChangeEvent<HTMLInputElement>) => {
    const f = e.target.files?.[0] ?? null;
    setFile(f);
    if (f) check(f);
  };

  const downloadReport = async () => {
    if (!file) return;
    const res = await fetch(`${BASE}/report`, { method: 'POST', body: file, headers: { ...RAW, Authorization: `Bearer ${getToken()}` } });
    if (!res.ok) {
      toast('Classeur de contrôle indisponible', 'error');
      return;
    }
    const url = URL.createObjectURL(await res.blob());
    const a = document.createElement('a');
    a.href = url;
    a.download = 'controle_import_hemophilie.xlsx';
    a.click();
    URL.revokeObjectURL(url);
  };

  const runImport = async () => {
    if (!file || !preview) return;
    if (!window.confirm(`Importer ${preview.nbNouvelles} fiche(s) d'hémophilie dans le registre ?`)) return;
    setBusy(true);
    try {
      const { data } = await api<{ importees: number; ignoreesDejaPresentes: number }>(BASE, { method: 'POST', body: file, headers: RAW });
      toast(`${data.importees} fiche(s) importée(s)${data.ignoreesDejaPresentes ? `, ${data.ignoreesDejaPresentes} déjà présente(s)` : ''}`);
      await check(file);
    } catch (err) {
      const msg = err instanceof ApiError ? err.message : 'Import impossible';
      toast(msg, 'error');
      setBusy(false);
    }
  };

  return (
    <div className="page">
      <header className="page-head">
        <div>
          <h1>Importer le registre de l'hémophilie</h1>
          <p className="muted">
            Classeur Excel du registre (feuille de 165 colonnes codées). Le fichier est d'abord contrôlé : rien n'est enregistré avant
            votre confirmation, et une fiche déjà présente (même nom, prénom et date de naissance) n'est jamais importée deux fois.
          </p>
        </div>
      </header>

      <section className="panel">
        <div className="field">
          <label className="field-label" htmlFor="import_file">
            Fichier Excel (.xlsx)
          </label>
          <input id="import_file" type="file" accept=".xlsx,application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" onChange={choose} disabled={busy} />
        </div>
        {busy && <p className="muted">Contrôle du fichier…</p>}
        {error && <p className="form-error">{error}</p>}
      </section>

      {preview && (
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
              <p className="muted">Corrigez le fichier puis choisissez-le à nouveau. Aucune fiche ne sera importée tant qu'une erreur subsiste.</p>
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
            <button className="btn btn-quiet" onClick={downloadReport} disabled={busy}>
              Télécharger le classeur de contrôle
            </button>
            <button className="btn btn-primary" onClick={runImport} disabled={busy || !preview.valide || preview.nbNouvelles === 0}>
              {preview.nbNouvelles ? `Importer ${preview.nbNouvelles} fiche(s)` : 'Rien à importer'}
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
                  <th>Nom</th>
                  <th>Prénom</th>
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
