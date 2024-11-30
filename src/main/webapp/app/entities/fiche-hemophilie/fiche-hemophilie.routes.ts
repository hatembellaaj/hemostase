import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FicheHemophilieResolve from './route/fiche-hemophilie-routing-resolve.service';

const ficheHemophilieRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/fiche-hemophilie.component').then(m => m.FicheHemophilieComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/fiche-hemophilie-detail.component').then(m => m.FicheHemophilieDetailComponent),
    resolve: {
      ficheHemophilie: FicheHemophilieResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/fiche-hemophilie-update.component').then(m => m.FicheHemophilieUpdateComponent),
    resolve: {
      ficheHemophilie: FicheHemophilieResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/fiche-hemophilie-update.component').then(m => m.FicheHemophilieUpdateComponent),
    resolve: {
      ficheHemophilie: FicheHemophilieResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default ficheHemophilieRoute;
