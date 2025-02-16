import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FicheThrombopathieResolve from './route/fiche-thrombopathie-routing-resolve.service';

const ficheThrombopathieRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/fiche-thrombopathie.component').then(m => m.FicheThrombopathieComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/fiche-thrombopathie-detail.component').then(m => m.FicheThrombopathieDetailComponent),
    resolve: {
      ficheThrombopathie: FicheThrombopathieResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/fiche-thrombopathie-update.component').then(m => m.FicheThrombopathieUpdateComponent),
    resolve: {
      ficheThrombopathie: FicheThrombopathieResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/fiche-thrombopathie-update.component').then(m => m.FicheThrombopathieUpdateComponent),
    resolve: {
      ficheThrombopathie: FicheThrombopathieResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default ficheThrombopathieRoute;
