import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FicheFacteurXVIIIResolve from './route/fiche-facteur-xviii-routing-resolve.service';

const ficheFacteurXVIIIRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/fiche-facteur-xviii.component').then(m => m.FicheFacteurXVIIIComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/fiche-facteur-xviii-detail.component').then(m => m.FicheFacteurXVIIIDetailComponent),
    resolve: {
      ficheFacteurXVIII: FicheFacteurXVIIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/fiche-facteur-xviii-update.component').then(m => m.FicheFacteurXVIIIUpdateComponent),
    resolve: {
      ficheFacteurXVIII: FicheFacteurXVIIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/fiche-facteur-xviii-update.component').then(m => m.FicheFacteurXVIIIUpdateComponent),
    resolve: {
      ficheFacteurXVIII: FicheFacteurXVIIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default ficheFacteurXVIIIRoute;
