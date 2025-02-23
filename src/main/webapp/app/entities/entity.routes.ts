import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'Authorities' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'fiche-hemophilie',
    data: { pageTitle: 'FicheHemophilies' },
    loadChildren: () => import('./fiche-hemophilie/fiche-hemophilie.routes'),
  },
  {
    path: 'fiche-thrombopathie',
    data: { pageTitle: 'FicheThrombopathies' },
    loadChildren: () => import('./fiche-thrombopathie/fiche-thrombopathie.routes'),
  },
  {
    path: 'fiche-facteur-vii',
    data: { pageTitle: 'FicheFacteurVIIS' },
    loadChildren: () => import('./fiche-facteur-vii/fiche-facteur-vii.routes'),
  },
  {
    path: 'fiche-facteur-viii',
    data: { pageTitle: 'FicheFacteurVIIIS' },
    loadChildren: () => import('./fiche-facteur-viii/fiche-facteur-viii.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
