import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FicheFacteurVIIIResolve from './route/fiche-facteur-viii-routing-resolve.service';

const ficheFacteurVIIIRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/fiche-facteur-viii.component').then(m => m.FicheFacteurVIIIComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/fiche-facteur-viii-detail.component').then(m => m.FicheFacteurVIIIDetailComponent),
    resolve: {
      ficheFacteurVIII: FicheFacteurVIIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/fiche-facteur-viii-update.component').then(m => m.FicheFacteurVIIIUpdateComponent),
    resolve: {
      ficheFacteurVIII: FicheFacteurVIIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/fiche-facteur-viii-update.component').then(m => m.FicheFacteurVIIIUpdateComponent),
    resolve: {
      ficheFacteurVIII: FicheFacteurVIIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default ficheFacteurVIIIRoute;
