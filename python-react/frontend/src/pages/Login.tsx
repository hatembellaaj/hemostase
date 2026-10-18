import { useState, type FormEvent } from 'react';
import { useApp } from '../state';

export default function Login() {
  const { login } = useApp();
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [remember, setRemember] = useState(false);
  const [error, setError] = useState('');
  const [busy, setBusy] = useState(false);

  const submit = async (e: FormEvent) => {
    e.preventDefault();
    setBusy(true);
    setError('');
    try {
      await login(username, password, remember);
    } catch (err) {
      setError((err as Error).message);
      setBusy(false);
    }
  };

  return (
    <div className="login">
      <div className="login-art" aria-hidden="true">
        <svg viewBox="0 0 400 400">
          <path d="M200 40c62 86 112 142 112 204a112 112 0 0 1-224 0C88 182 138 126 200 40z" />
        </svg>
        <p>Hémophilie, Willebrand, thrombopathies et déficits en facteurs rares : un seul registre pour le suivi des patients.</p>
      </div>
      <form className="login-form" onSubmit={submit}>
        <h1>Connexion au registre</h1>
        <div className="field">
          <label className="field-label" htmlFor="u">
            Identifiant
          </label>
          <input id="u" autoComplete="username" value={username} onChange={(e) => setUsername(e.target.value)} required autoFocus />
        </div>
        <div className="field">
          <label className="field-label" htmlFor="p">
            Mot de passe
          </label>
          <input id="p" type="password" autoComplete="current-password" value={password} onChange={(e) => setPassword(e.target.value)} required />
        </div>
        <div className="field field-check">
          <input id="r" type="checkbox" checked={remember} onChange={(e) => setRemember(e.target.checked)} />
          <label htmlFor="r" className="field-label">
            Rester connecté sur cet appareil
          </label>
        </div>
        {error && <p className="form-error">{error}</p>}
        <button className="btn btn-primary" disabled={busy}>
          {busy ? 'Connexion…' : 'Se connecter'}
        </button>
      </form>
    </div>
  );
}
