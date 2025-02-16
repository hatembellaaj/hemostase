import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFicheThrombopathie } from '../fiche-thrombopathie.model';
import { FicheThrombopathieService } from '../service/fiche-thrombopathie.service';

const ficheThrombopathieResolve = (route: ActivatedRouteSnapshot): Observable<null | IFicheThrombopathie> => {
  const id = route.params.id;
  if (id) {
    return inject(FicheThrombopathieService)
      .find(id)
      .pipe(
        mergeMap((ficheThrombopathie: HttpResponse<IFicheThrombopathie>) => {
          if (ficheThrombopathie.body) {
            return of(ficheThrombopathie.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default ficheThrombopathieResolve;
