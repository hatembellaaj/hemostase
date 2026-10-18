import { useCallback, useEffect, useState, type FormEvent } from 'react';
import { api } from '../api';
import { useApp, useToast } from '../state';

interface User {
  id?: number;
  login: string;
  firstName: string | null;
  lastName: string | null;
  email: string | null;
  activated: boolean;
  authorities: string[];
  password?: string;
}

const blank: User = { login: '', firstName: '', lastName: '', email: '', activated: true, authorities: ['ROLE_USER'], password: '' };
const ROLE_LABEL: Record<string, string> = { ROLE_ADMIN: 'Administrateur', ROLE_USER: 'Utilisateur' };

export default function Users() {
  const { account } = useApp();
  const toast = useToast();
  const [users, setUsers] = useState<User[]>([]);
  const [roles, setRoles] = useState<string[]>([]);
  const [edit, setEdit] = useState<User | null>(null);

  const load = useCallback(() => {
    api<User[]>('/api/admin/users?size=200').then(({ data }) => setUsers(data));
  }, []);
  useEffect(() => {
    load();
    api<string[]>('/api/admin/authorities').then(({ data }) => setRoles(data));
  }, [load]);

  const save = async (e: FormEvent) => {
    e.preventDefault();
    if (!edit) return;
    try {
      await api(edit.id ? `/api/admin/users/${edit.id}` : '/api/admin/users', {
        method: edit.id ? 'PUT' : 'POST',
        body: JSON.stringify(edit),
      });
      toast(edit.id ? 'Utilisateur enregistré' : 'Utilisateur créé');
      setEdit(null);
      load();
    } catch (err) {
      toast((err as Error).message, 'error');
    }
  };

  const remove = async (u: User) => {
    if (!window.confirm(`Supprimer le compte « ${u.login} » ?`)) return;
    try {
      await api(`/api/admin/users/${u.login}`, { method: 'DELETE' });
      toast('Utilisateur supprimé');
      load();
    } catch (err) {
      toast((err as Error).message, 'error');
    }
  };

  return (
    <div className="page">
      <header className="page-head">
        <div>
          <h1>Utilisateurs</h1>
          <p className="muted">Comptes autorisés à consulter et saisir les fiches.</p>
        </div>
        <button className="btn btn-primary" onClick={() => setEdit({ ...blank })}>
          Ajouter un utilisateur
        </button>
      </header>

      {edit && (
        <form className="panel" onSubmit={save}>
          <h2>{edit.id ? `Modifier ${edit.login}` : 'Nouvel utilisateur'}</h2>
          <div className="grid">
            {(
              [
                ['login', 'Identifiant'],
                ['firstName', 'Prénom'],
                ['lastName', 'Nom'],
                ['email', 'E-mail'],
              ] as const
            ).map(([k, l]) => (
              <div className="field" key={k}>
                <label className="field-label" htmlFor={`u_${k}`}>
                  {l}
                </label>
                <input id={`u_${k}`} value={edit[k] ?? ''} required={k === 'login'} onChange={(e) => setEdit({ ...edit, [k]: e.target.value })} />
              </div>
            ))}
            <div className="field">
              <label className="field-label" htmlFor="u_pwd">
                {edit.id ? 'Nouveau mot de passe (facultatif)' : 'Mot de passe'}
              </label>
              <input id="u_pwd" type="password" autoComplete="new-password" required={!edit.id} minLength={4} value={edit.password ?? ''} onChange={(e) => setEdit({ ...edit, password: e.target.value })} />
            </div>
            <fieldset className="field">
              <legend className="field-label">Rôles</legend>
              {roles.map((r) => (
                <label key={r} className="inline-check">
                  <input
                    type="checkbox"
                    checked={edit.authorities.includes(r)}
                    onChange={(e) => setEdit({ ...edit, authorities: e.target.checked ? [...edit.authorities, r] : edit.authorities.filter((x) => x !== r) })}
                  />
                  {ROLE_LABEL[r] ?? r}
                </label>
              ))}
            </fieldset>
            <div className="field field-check">
              <input id="u_act" type="checkbox" checked={edit.activated} onChange={(e) => setEdit({ ...edit, activated: e.target.checked })} />
              <label htmlFor="u_act" className="field-label">
                Compte actif
              </label>
            </div>
          </div>
          <div className="panel-actions">
            <button type="button" className="btn btn-quiet" onClick={() => setEdit(null)}>
              Annuler
            </button>
            <button className="btn btn-primary">{edit.id ? 'Enregistrer' : "Créer l'utilisateur"}</button>
          </div>
        </form>
      )}

      <div className="table-wrap">
        <table className="table table-static">
          <thead>
            <tr>
              <th>Identifiant</th>
              <th>Nom</th>
              <th>E-mail</th>
              <th>Rôles</th>
              <th>État</th>
              <th>
                <span className="sr-only">Actions</span>
              </th>
            </tr>
          </thead>
          <tbody>
            {users.map((u) => (
              <tr key={u.id}>
                <td>{u.login}</td>
                <td>{[u.firstName, u.lastName].filter(Boolean).join(' ')}</td>
                <td>{u.email}</td>
                <td>{u.authorities.map((r) => ROLE_LABEL[r] ?? r).join(', ')}</td>
                <td>{u.activated ? 'Actif' : <span className="muted">Désactivé</span>}</td>
                <td className="row-actions">
                  <button className="link-btn" onClick={() => setEdit({ ...u, password: '' })}>
                    Modifier
                  </button>
                  {u.login !== account?.login && (
                    <button className="link-btn danger" onClick={() => remove(u)}>
                      Supprimer
                    </button>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
