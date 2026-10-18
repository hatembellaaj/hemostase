import { useState, type FormEvent } from 'react';
import { api } from '../api';
import { useApp, useToast } from '../state';

export default function Account() {
  const { account } = useApp();
  const toast = useToast();
  const [cur, setCur] = useState('');
  const [next, setNext] = useState('');
  const [again, setAgain] = useState('');

  const submit = async (e: FormEvent) => {
    e.preventDefault();
    if (next !== again) {
      toast('Les deux nouveaux mots de passe sont différents', 'error');
      return;
    }
    try {
      await api('/api/account/change-password', { method: 'POST', body: JSON.stringify({ currentPassword: cur, newPassword: next }) });
      toast('Mot de passe modifié');
      setCur('');
      setNext('');
      setAgain('');
    } catch (err) {
      toast((err as Error).message, 'error');
    }
  };

  return (
    <div className="page">
      <header className="page-head">
        <div>
          <h1>Mon compte</h1>
          <p className="muted">
            Connecté en tant que {account?.login}
            {account?.email ? ` (${account.email})` : ''}
          </p>
        </div>
      </header>
      <form className="panel panel-narrow" onSubmit={submit}>
        <h2>Changer de mot de passe</h2>
        {(
          [
            ['Mot de passe actuel', cur, setCur, 'current-password'],
            ['Nouveau mot de passe', next, setNext, 'new-password'],
            ['Confirmer le nouveau mot de passe', again, setAgain, 'new-password'],
          ] as const
        ).map(([l, v, s, ac], i) => (
          <div className="field" key={l}>
            <label className="field-label" htmlFor={`pw${i}`}>
              {l}
            </label>
            <input id={`pw${i}`} type="password" autoComplete={ac} required minLength={i ? 4 : undefined} value={v} onChange={(e) => s(e.target.value)} />
          </div>
        ))}
        <div className="panel-actions">
          <button className="btn btn-primary">Changer le mot de passe</button>
        </div>
      </form>
    </div>
  );
}
