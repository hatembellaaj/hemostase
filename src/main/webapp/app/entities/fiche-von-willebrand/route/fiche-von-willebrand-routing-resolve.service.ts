import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFicheVonWillebrand } from '../fiche-von-willebrand.model';
import { FicheVonWillebrandService } from '../service/fiche-von-willebrand.service';

const ficheVonWillebrandResolve = (route: ActivatedRouteSnapshot): Observable<null | IFicheVonWillebrand> => {
  const id = route.params.id;
  if (id) {
    return inject(FicheVonWillebrandService)
      .find(id)
      .pipe(
        mergeMap((ficheVonWillebrand: HttpResponse<IFicheVonWillebrand>) => {
          if (ficheVonWillebrand.body) {
            return of(ficheVonWillebrand.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default ficheVonWillebrandResolve;
