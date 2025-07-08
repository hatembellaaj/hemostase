import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFicheFacteurXI } from '../fiche-facteur-xi.model';
import { FicheFacteurXIService } from '../service/fiche-facteur-xi.service';

const ficheFacteurXIResolve = (route: ActivatedRouteSnapshot): Observable<null | IFicheFacteurXI> => {
  const id = route.params.id;
  if (id) {
    return inject(FicheFacteurXIService)
      .find(id)
      .pipe(
        mergeMap((ficheFacteurXI: HttpResponse<IFicheFacteurXI>) => {
          if (ficheFacteurXI.body) {
            return of(ficheFacteurXI.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default ficheFacteurXIResolve;
