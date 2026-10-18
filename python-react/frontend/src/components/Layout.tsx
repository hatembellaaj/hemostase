import { useEffect, useState } from 'react';
import { NavLink, Outlet, useLocation } from 'react-router-dom';
import { api } from '../api';
import { useApp } from '../state';

export default function Layout() {
  const { entities, account, isAdmin, logout } = useApp();
  const [counts, setCounts] = useState<Record<string, number>>({});
  const [open, setOpen] = useState(false);
  const loc = useLocation();

  useEffect(() => {
    api<{ slug: string; count: number }[]>('/api/stats')
      .then(({ data }) => setCounts(Object.fromEntries(data.map((s) => [s.slug, s.count]))))
      .catch(() => {});
    setOpen(false);
  }, [loc.pathname]);

  return (
    <div className="shell">
      <a href="#main" className="skip">
        Aller au contenu
      </a>
      <header className="topbar">
        <button className="menu-btn" aria-expanded={open} aria-controls="sidebar" onClick={() => setOpen((o) => !o)}>
          Menu
        </button>
        <NavLink to="/" className="brand">
          <svg viewBox="0 0 32 32" aria-hidden="true">
            <path d="M16 3c5 7 9 11.5 9 16.5A9 9 0 0 1 7 19.5C7 14.5 11 10 16 3z" />
          </svg>
          Registre hémostase
        </NavLink>
      </header>
      <aside id="sidebar" className={`sidebar ${open ? 'sidebar-open' : ''}`}>
        <NavLink to="/" className="brand brand-side">
          <svg viewBox="0 0 32 32" aria-hidden="true">
            <path d="M16 3c5 7 9 11.5 9 16.5A9 9 0 0 1 7 19.5C7 14.5 11 10 16 3z" />
          </svg>
          Registre hémostase
        </NavLink>
        <nav aria-label="Registres">
          <p className="nav-group">Registres</p>
          {entities.map((e) => (
            <NavLink key={e.slug} to={`/fiches/${e.slug}`} className="nav-item">
              <span>{e.title}</span>
              {counts[e.slug] !== undefined && <span className="nav-count">{counts[e.slug]}</span>}
            </NavLink>
          ))}
          <p className="nav-group">Analyse</p>
          <NavLink to={`/stats/${loc.pathname.match(/^\/(?:fiches|stats)\/([^/]+)/)?.[1] ?? entities[0]?.slug ?? 'hemophilie'}`} className={({ isActive }) => `nav-item ${isActive || loc.pathname.startsWith('/stats') ? 'active' : ''}`}>
            Statistiques
          </NavLink>
          {isAdmin && (
            <>
              <p className="nav-group">Administration</p>
              <NavLink to="/admin/users" className="nav-item">
                Utilisateurs
              </NavLink>
              <a href="/api/docs" className="nav-item" target="_blank" rel="noreferrer">
                Documentation de l'API
              </a>
            </>
          )}
        </nav>
        <div className="sidebar-foot">
          <NavLink to="/account" className="who">
            {account?.firstName || account?.login}
          </NavLink>
          <button className="link-btn" onClick={logout}>
            Se déconnecter
          </button>
        </div>
      </aside>
      <main id="main" className="main">
        <Outlet />
      </main>
    </div>
  );
}
