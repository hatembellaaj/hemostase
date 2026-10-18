import React from 'react';
import ReactDOM from 'react-dom/client';
import { createBrowserRouter, Navigate, RouterProvider } from 'react-router-dom';
import '@fontsource/ibm-plex-sans/400.css';
import '@fontsource/ibm-plex-sans/500.css';
import '@fontsource/ibm-plex-sans/600.css';
import './styles.css';
import Layout from './components/Layout';
import Account from './pages/Account';
import FicheDetail from './pages/FicheDetail';
import FicheForm from './pages/FicheForm';
import FicheList from './pages/FicheList';
import Home from './pages/Home';
import Login from './pages/Login';
import Stats from './pages/Stats';
import Users from './pages/Users';
import { AppProvider, ToastProvider, useApp } from './state';

function Guard({ admin, children }: { admin?: boolean; children: React.ReactNode }) {
  const { account, loading, isAdmin } = useApp();
  if (loading) return <div className="boot">Chargement…</div>;
  if (!account) return <Login />;
  if (admin && !isAdmin) return <Navigate to="/" replace />;
  return <>{children}</>;
}

const router = createBrowserRouter([
  {
    path: '/',
    element: (
      <Guard>
        <Layout />
      </Guard>
    ),
    children: [
      { index: true, element: <Home /> },
      { path: 'fiches/:slug', element: <FicheList /> },
      { path: 'fiches/:slug/new', element: <FicheForm key="new" /> },
      { path: 'fiches/:slug/:id', element: <FicheDetail /> },
      { path: 'fiches/:slug/:id/edit', element: <FicheForm key="edit" /> },
      { path: 'stats', element: <Navigate to="/stats/hemophilie" replace /> },
      { path: 'stats/:slug', element: <Stats /> },
      { path: 'account', element: <Account /> },
      {
        path: 'admin/users',
        element: (
          <Guard admin>
            <Users />
          </Guard>
        ),
      },
      { path: '*', element: <Navigate to="/" replace /> },
    ],
  },
]);

ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <ToastProvider>
      <AppProvider>
        <RouterProvider router={router} />
      </AppProvider>
    </ToastProvider>
  </React.StrictMode>,
);
