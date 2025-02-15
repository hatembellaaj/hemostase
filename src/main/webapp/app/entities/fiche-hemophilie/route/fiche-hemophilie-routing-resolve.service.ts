import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFicheHemophilie } from '../fiche-hemophilie.model';
import { FicheHemophilieService } from '../service/fiche-hemophilie.service';

const ficheHemophilieResolve = (route: ActivatedRouteSnapshot): Observable<null | IFicheHemophilie> => {
  const id = route.params.id;
  if (id) {
    return inject(FicheHemophilieService)
      .find(id)
      .pipe(
        mergeMap((ficheHemophilie: HttpResponse<IFicheHemophilie>) => {
          if (ficheHemophilie.body) {
            return of(ficheHemophilie.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default ficheHemophilieResolve;
