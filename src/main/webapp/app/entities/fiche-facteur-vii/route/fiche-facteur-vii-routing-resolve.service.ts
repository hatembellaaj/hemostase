import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFicheFacteurVII } from '../fiche-facteur-vii.model';
import { FicheFacteurVIIService } from '../service/fiche-facteur-vii.service';

const ficheFacteurVIIResolve = (route: ActivatedRouteSnapshot): Observable<null | IFicheFacteurVII> => {
  const id = route.params.id;
  if (id) {
    return inject(FicheFacteurVIIService)
      .find(id)
      .pipe(
        mergeMap((ficheFacteurVII: HttpResponse<IFicheFacteurVII>) => {
          if (ficheFacteurVII.body) {
            return of(ficheFacteurVII.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default ficheFacteurVIIResolve;
