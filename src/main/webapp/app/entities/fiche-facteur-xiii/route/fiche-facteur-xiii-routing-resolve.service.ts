import { inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { EMPTY, Observable, of } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IFicheFacteurXIII } from '../fiche-facteur-xiii.model';
import { FicheFacteurXIIIService } from '../service/fiche-facteur-xiii.service';

const ficheFacteurXIIIResolve = (route: ActivatedRouteSnapshot): Observable<null | IFicheFacteurXIII> => {
  const id = route.params.id;
  if (id) {
    return inject(FicheFacteurXIIIService)
      .find(id)
      .pipe(
        mergeMap((ficheFacteurXIII: HttpResponse<IFicheFacteurXIII>) => {
          if (ficheFacteurXIII.body) {
            return of(ficheFacteurXIII.body);
          }
          inject(Router).navigate(['404']);
          return EMPTY;
        }),
      );
  }
  return of(null);
};

export default ficheFacteurXIIIResolve;
