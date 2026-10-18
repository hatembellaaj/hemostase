import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { api } from '../api';
import { useApp } from '../state';

export default function Home() {
  const { account, entities } = useApp();
  const [stats, setStats] = useState<{ slug: string; title: string; count: number }[]>([]);
  useEffect(() => {
    api<typeof stats>('/api/stats').then(({ data }) => setStats(data));
  }, []);
  const total = stats.reduce((s, x) => s + x.count, 0);
  const max = Math.max(1, ...stats.map((s) => s.count));

  return (
    <div className="page">
      <header className="page-head">
        <div>
          <h1>Bonjour {account?.firstName || account?.login}</h1>
          <p className="muted">
            {total} fiches patient réparties sur {entities.length} registres.
          </p>
        </div>
      </header>
      <section className="registry-list" aria-label="Registres">
        {stats.map((s) => {
          const ent = entities.find((e) => e.slug === s.slug);
          return (
            <div key={s.slug} className="registry-row">
              <Link to={`/fiches/${s.slug}`} className="registry-name">
                {s.title}
              </Link>
              <div className="registry-bar" aria-hidden="true">
                <span style={{ width: `${(s.count / max) * 100}%` }} />
              </div>
              <span className="registry-count">{s.count}</span>
              <span className="registry-meta">{ent?.fields.length} champs</span>
              <Link to={`/fiches/${s.slug}/new`} className="btn btn-quiet">
                Nouvelle fiche
              </Link>
            </div>
          );
        })}
      </section>
    </div>
  );
}
