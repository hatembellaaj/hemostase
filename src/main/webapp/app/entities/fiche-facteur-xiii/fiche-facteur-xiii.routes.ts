import { Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ASC } from 'app/config/navigation.constants';
import FicheFacteurXIIIResolve from './route/fiche-facteur-xiii-routing-resolve.service';

const ficheFacteurXIIIRoute: Routes = [
  {
    path: '',
    loadComponent: () => import('./list/fiche-facteur-xiii.component').then(m => m.FicheFacteurXIIIComponent),
    data: {
      defaultSort: `id,${ASC}`,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    loadComponent: () => import('./detail/fiche-facteur-xiii-detail.component').then(m => m.FicheFacteurXIIIDetailComponent),
    resolve: {
      ficheFacteurXIII: FicheFacteurXIIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    loadComponent: () => import('./update/fiche-facteur-xiii-update.component').then(m => m.FicheFacteurXIIIUpdateComponent),
    resolve: {
      ficheFacteurXIII: FicheFacteurXIIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    loadComponent: () => import('./update/fiche-facteur-xiii-update.component').then(m => m.FicheFacteurXIIIUpdateComponent),
    resolve: {
      ficheFacteurXIII: FicheFacteurXIIIResolve,
    },
    canActivate: [UserRouteAccessService],
  },
];

export default ficheFacteurXIIIRoute;
