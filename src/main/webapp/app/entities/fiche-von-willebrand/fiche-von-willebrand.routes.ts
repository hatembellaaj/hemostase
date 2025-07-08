import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FicheVonWillebrandResolve from './route/fiche-von-willebrand-routing-resolve.service';

const ficheVonWillebrandRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/fiche-von-willebrand.component').then(m => m.FicheVonWillebrandComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/fiche-von-willebrand-detail.component').then(m => m.FicheVonWillebrandDetailComponent),
    resolve: {
      ficheVonWillebrand: FicheVonWillebrandResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/fiche-von-willebrand-update.component').then(m => m.FicheVonWillebrandUpdateComponent),
    resolve: {
      ficheVonWillebrand: FicheVonWillebrandResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/fiche-von-willebrand-update.component').then(m => m.FicheVonWillebrandUpdateComponent),
    resolve: {
      ficheVonWillebrand: FicheVonWillebrandResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default ficheVonWillebrandRoute;
