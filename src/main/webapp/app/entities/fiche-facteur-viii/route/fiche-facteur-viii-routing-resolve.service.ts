import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFicheFacteurVIII } from '../fiche-facteur-viii.model';
import { FicheFacteurVIIIService } from '../service/fiche-facteur-viii.service';

const ficheFacteurVIIIResolve = (route: ActivatedRouteSnapshot): Observable<null | IFicheFacteurVIII> => {
  const id = route.params.id;
  if (id) {
    return inject(FicheFacteurVIIIService)
      .find(id)
      .pipe(
        mergeMap((ficheFacteurVIII: HttpResponse<IFicheFacteurVIII>) => {
          if (ficheFacteurVIII.body) {
            return of(ficheFacteurVIII.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default ficheFacteurVIIIResolve;
