import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFicheFacteurXVIII } from '../fiche-facteur-xviii.model';
import { FicheFacteurXVIIIService } from '../service/fiche-facteur-xviii.service';

const ficheFacteurXVIIIResolve = (route: ActivatedRouteSnapshot): Observable<null | IFicheFacteurXVIII> => {
  const id = route.params.id;
  if (id) {
    return inject(FicheFacteurXVIIIService)
      .find(id)
      .pipe(
        mergeMap((ficheFacteurXVIII: HttpResponse<IFicheFacteurXVIII>) => {
          if (ficheFacteurXVIII.body) {
            return of(ficheFacteurXVIII.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default ficheFacteurXVIIIResolve;
