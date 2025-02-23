import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FicheFacteurVIIResolve from './route/fiche-facteur-vii-routing-resolve.service';

const ficheFacteurVIIRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/fiche-facteur-vii.component').then(m => m.FicheFacteurVIIComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/fiche-facteur-vii-detail.component').then(m => m.FicheFacteurVIIDetailComponent),
    resolve: {
      ficheFacteurVII: FicheFacteurVIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/fiche-facteur-vii-update.component').then(m => m.FicheFacteurVIIUpdateComponent),
    resolve: {
      ficheFacteurVII: FicheFacteurVIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/fiche-facteur-vii-update.component').then(m => m.FicheFacteurVIIUpdateComponent),
    resolve: {
      ficheFacteurVII: FicheFacteurVIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default ficheFacteurVIIRoute;
