import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams, useSearchParams } from 'react-router-dom';
import { api, download } from '../api';
import { displayValue } from '../logic';
import { useApp, useToast } from '../state';
import type { Fiche } from '../types';

const PAGE_SIZE = 20;

export default function FicheList() {
  const { slug = '' } = useParams();
  const { entity } = useApp();
  const ent = entity(slug);
  const nav = useNavigate();
  const toast = useToast();
  const [params, setParams] = useSearchParams();
  const page = Number(params.get('page') ?? 0);
  const sort = params.get('sort') ?? 'id,desc';
  const q = params.get('q') ?? '';
  const [search, setSearch] = useState(q);
  const [rows, setRows] = useState<Fiche[]>([]);
  const [total, setTotal] = useState(0);
  const [loading, setLoading] = useState(true);

  useEffect(() => setSearch(q), [q, slug]);

  useEffect(() => {
    if (!ent) return;
    setLoading(true);
    const qs = new URLSearchParams({ page: String(page), size: String(PAGE_SIZE), sort });
    if (q) qs.set('q', q);
    api<Fiche[]>(`/api/fiches/${slug}?${qs}`)
      .then(({ data, headers }) => {
        setRows(data);
        setTotal(Number(headers.get('X-Total-Count') ?? 0));
      })
      .catch((e) => toast(e.message, 'error'))
      .finally(() => setLoading(false));
  }, [slug, page, sort, q, ent, toast]);

  if (!ent) return <div className="page">Registre inconnu.</div>;

  const cols = ent.listColumns.map((n) => ent.fields.find((f) => f.name === n)!).filter(Boolean);
  const pages = Math.max(1, Math.ceil(total / PAGE_SIZE));
  const update = (patch: Record<string, string | null>) => {
    const next = new URLSearchParams(params);
    Object.entries(patch).forEach(([k, v]) => (v === null || v === '' ? next.delete(k) : next.set(k, v)));
    setParams(next);
  };
  const toggleSort = (name: string) => {
    const [cur, dir] = sort.split(',');
    update({ sort: `${name},${cur === name && dir === 'asc' ? 'desc' : 'asc'}`, page: null });
  };
  const sortMark = (name: string) => {
    const [cur, dir] = sort.split(',');
    return cur === name ? (dir === 'asc' ? 'ascending' : 'descending') : 'none';
  };

  return (
    <div className="page">
      <header className="page-head">
        <div>
          <h1>{ent.title}</h1>
          <p className="muted">
            {total} fiche{total > 1 ? 's' : ''}
            {q && ` correspondant à « ${q} »`}
          </p>
        </div>
        <div className="head-actions">
          <button
            className="btn btn-quiet"
            onClick={() => download(`/api/fiches/${slug}/export.csv${q ? `?q=${encodeURIComponent(q)}` : ''}`, `${ent.table}.csv`).catch((e) => toast(e.message, 'error'))}
          >
            Exporter en CSV
          </button>
          <Link to={`/fiches/${slug}/new`} className="btn btn-primary">
            Nouvelle fiche
          </Link>
        </div>
      </header>

      <form
        className="searchbar"
        role="search"
        onSubmit={(e) => {
          e.preventDefault();
          update({ q: search.trim(), page: null });
        }}
      >
        <label htmlFor="search" className="sr-only">
          Rechercher
        </label>
        <input id="search" type="search" placeholder="Nom, prénom, numéro de dossier, téléphone…" value={search} onChange={(e) => setSearch(e.target.value)} />
        <button className="btn btn-quiet">Rechercher</button>
      </form>

      <div className="table-wrap">
        <table className="table">
          <thead>
            <tr>
              <th aria-sort={sortMark('id')}>
                <button onClick={() => toggleSort('id')}>N°</button>
              </th>
              {cols.map((c) => (
                <th key={c.name} aria-sort={sortMark(c.name)}>
                  <button onClick={() => toggleSort(c.name)}>{c.label}</button>
                </th>
              ))}
            </tr>
          </thead>
          <tbody>
            {rows.map((r) => (
              <tr key={r.id} onClick={() => nav(`/fiches/${slug}/${r.id}`)} tabIndex={0} onKeyDown={(e) => e.key === 'Enter' && nav(`/fiches/${slug}/${r.id}`)}>
                <td className="num">{r.id}</td>
                {cols.map((c) => (
                  <td key={c.name} className={c.type === 'integer' || c.type === 'float' ? 'num' : undefined}>
                    {displayValue(c, r[c.name])}
                  </td>
                ))}
              </tr>
            ))}
          </tbody>
        </table>
        {!loading && rows.length === 0 && (
          <div className="empty">
            <p>{q ? 'Aucune fiche ne correspond à cette recherche.' : 'Ce registre ne contient encore aucune fiche.'}</p>
            <Link to={`/fiches/${slug}/new`} className="btn btn-primary">
              Créer la première fiche
            </Link>
          </div>
        )}
      </div>

      {pages > 1 && (
        <nav className="pager" aria-label="Pagination">
          <button className="btn btn-quiet" disabled={page === 0} onClick={() => update({ page: String(page - 1) })}>
            Précédente
          </button>
          <span>
            Page {page + 1} sur {pages}
          </span>
          <button className="btn btn-quiet" disabled={page >= pages - 1} onClick={() => update({ page: String(page + 1) })}>
            Suivante
          </button>
        </nav>
      )}
    </div>
  );
}
