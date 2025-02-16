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
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
