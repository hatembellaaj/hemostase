import { useEffect, useState } from 'react';

export interface RailItem {
  title: string;
  filled: number;
  total: number;
}

/** Colonne des sections : navigation + jauge de remplissage de chaque section. */
export default function SectionRail({ items }: { items: RailItem[] }) {
  const [active, setActive] = useState(0);

  useEffect(() => {
    const els = items.map((_, i) => document.getElementById(`sec-${i}`)).filter(Boolean) as HTMLElement[];
    const io = new IntersectionObserver(
      (entries) => {
        const vis = entries.filter((e) => e.isIntersecting).sort((a, b) => a.boundingClientRect.top - b.boundingClientRect.top)[0];
        if (vis) setActive(Number(vis.target.id.slice(4)));
      },
      { rootMargin: '-15% 0px -70% 0px' },
    );
    els.forEach((el) => io.observe(el));
    return () => io.disconnect();
  }, [items.length]); // eslint-disable-line react-hooks/exhaustive-deps

  return (
    <nav className="rail" aria-label="Sections de la fiche">
      <ol>
        {items.map((it, i) => (
          <li key={it.title}>
            <a
              href={`#sec-${i}`}
              className={i === active ? 'rail-active' : ''}
              aria-current={i === active ? 'true' : undefined}
              onClick={(e) => {
                e.preventDefault();
                document.getElementById(`sec-${i}`)?.scrollIntoView({ behavior: 'smooth', block: 'start' });
                setActive(i);
              }}
            >
              <span className="rail-title">{it.title}</span>
              <span className="rail-gauge" aria-hidden="true">
                <span style={{ width: `${it.total ? (it.filled / it.total) * 100 : 0}%` }} />
              </span>
              <span className="rail-count">
                {it.filled}/{it.total}
              </span>
            </a>
          </li>
        ))}
      </ol>
    </nav>
  );
}
