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
  {
    path: 'fiche-von-willebrand',
    data: { pageTitle: 'FicheVonWillebrands' },
    loadChildren: () => import('./fiche-von-willebrand/fiche-von-willebrand.routes'),
  },
  {
    path: 'fiche-facteur-xi',
    data: { pageTitle: 'FicheFacteurXIS' },
    loadChildren: () => import('./fiche-facteur-xi/fiche-facteur-xi.routes'),
  },
  {
    path: 'fiche-facteur-xiii',
    data: { pageTitle: 'FicheFacteurXIIIS' },
    loadChildren: () => import('./fiche-facteur-xiii/fiche-facteur-xiii.routes'),
  },
  {
    path: 'fiche-facteur-xviii',
    data: { pageTitle: 'FicheFacteurXVIIIS' },
    loadChildren: () => import('./fiche-facteur-xviii/fiche-facteur-xviii.routes'),
  },
  /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
];

export default routes;
