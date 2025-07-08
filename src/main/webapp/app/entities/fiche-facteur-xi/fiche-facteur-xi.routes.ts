import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FicheFacteurXIResolve from './route/fiche-facteur-xi-routing-resolve.service';

const ficheFacteurXIRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/fiche-facteur-xi.component').then(m => m.FicheFacteurXIComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/fiche-facteur-xi-detail.component').then(m => m.FicheFacteurXIDetailComponent),
    resolve: {
      ficheFacteurXI: FicheFacteurXIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/fiche-facteur-xi-update.component').then(m => m.FicheFacteurXIUpdateComponent),
    resolve: {
      ficheFacteurXI: FicheFacteurXIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/fiche-facteur-xi-update.component').then(m => m.FicheFacteurXIUpdateComponent),
    resolve: {
      ficheFacteurXI: FicheFacteurXIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default ficheFacteurXIRoute;
