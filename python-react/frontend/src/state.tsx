import { createContext, useCallback, useContext, useEffect, useState, type ReactNode } from 'react';
import { api, getToken, setToken, setUnauthorizedHandler } from './api';
import type { Account, EntityDef } from './types';

interface AppState {
  account: Account | null;
  entities: EntityDef[];
  loading: boolean;
  isAdmin: boolean;
  login: (username: string, password: string, remember: boolean) => Promise<void>;
  logout: () => void;
  entity: (slug: string) => EntityDef | undefined;
}

const Ctx = createContext<AppState | null>(null);

export function AppProvider({ children }: { children: ReactNode }) {
  const [account, setAccount] = useState<Account | null>(null);
  const [entities, setEntities] = useState<EntityDef[]>([]);
  const [loading, setLoading] = useState(true);

  const logout = useCallback(() => {
    setToken(null);
    setAccount(null);
  }, []);

  const load = useCallback(async () => {
    if (!getToken()) {
      setLoading(false);
      return;
    }
    try {
      const [acc, schema] = await Promise.all([
        api<Account>('/api/account'),
        api<{ entities: EntityDef[] }>('/api/schema'),
      ]);
      setAccount(acc.data);
      setEntities(schema.data.entities);
    } catch {
      setToken(null);
      setAccount(null);
    } finally {
      setLoading(false);
    }
  }, []);

  useEffect(() => {
    setUnauthorizedHandler(logout);
    load();
  }, [load, logout]);

  const login = async (username: string, password: string, remember: boolean) => {
    const { data } = await api<{ id_token: string }>('/api/authenticate', {
      method: 'POST',
      body: JSON.stringify({ username, password, rememberMe: remember }),
    });
    setToken(data.id_token, remember);
    setLoading(true);
    await load();
  };

  const value: AppState = {
    account,
    entities,
    loading,
    isAdmin: !!account?.authorities.includes('ROLE_ADMIN'),
    login,
    logout,
    entity: (slug) => entities.find((e) => e.slug === slug),
  };
  return <Ctx.Provider value={value}>{children}</Ctx.Provider>;
}

export function useApp() {
  const v = useContext(Ctx);
  if (!v) throw new Error('AppProvider manquant');
  return v;
}

type Toast = { id: number; text: string; kind: 'ok' | 'error' };
const ToastCtx = createContext<(text: string, kind?: Toast['kind']) => void>(() => {});

export function ToastProvider({ children }: { children: ReactNode }) {
  const [toasts, setToasts] = useState<Toast[]>([]);
  const push = useCallback((text: string, kind: Toast['kind'] = 'ok') => {
    const id = Date.now() + Math.random();
    setToasts((t) => [...t, { id, text, kind }]);
    setTimeout(() => setToasts((t) => t.filter((x) => x.id !== id)), 4000);
  }, []);
  return (
    <ToastCtx.Provider value={push}>
      {children}
      <div className="toasts" role="status" aria-live="polite">
        {toasts.map((t) => (
          <div key={t.id} className={`toast toast-${t.kind}`}>
            {t.text}
          </div>
        ))}
      </div>
    </ToastCtx.Provider>
  );
}

export const useToast = () => useContext(ToastCtx);
