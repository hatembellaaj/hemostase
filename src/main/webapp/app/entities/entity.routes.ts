import { Routes } from '@angular/router';

const routes: Routes = [
  {
    path: 'authority',
    data: { pageTitle: 'Authorities' },
    loadChildren: () => import('./admin/authority/authority.routes'),
  },
  {
    path: 'patient',
    data: { pageTitle: 'Patients' },
    loadChildren: () => import('./patient/patient.routes'),
  },
  {
    path: 'fiche-hemophilie',
    data: { pageTitle: 'FicheHemophilies' },
    loadChildren: () => import('./fiche-hemophilie/fiche-hemophilie.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
