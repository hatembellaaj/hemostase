import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFicheFacteurXIII, NewFicheFacteurXIII } from '../fiche-facteur-xiii.model';

export type PartialUpdateFicheFacteurXIII = Partial<IFicheFacteurXIII> & Pick<IFicheFacteurXIII, 'id'>;

type RestOf<T extends IFicheFacteurXIII | NewFicheFacteurXIII> = Omit<T, 'dateNaissance' | 'dateEnregistrementRegistre' | 'dateDeces'> & {
  dateNaissance?: string | null;
  dateEnregistrementRegistre?: string | null;
  dateDeces?: string | null;
};

export type RestFicheFacteurXIII = RestOf<IFicheFacteurXIII>;

export type NewRestFicheFacteurXIII = RestOf<NewFicheFacteurXIII>;

export type PartialUpdateRestFicheFacteurXIII = RestOf<PartialUpdateFicheFacteurXIII>;

export type EntityResponseType = HttpResponse<IFicheFacteurXIII>;
export type EntityArrayResponseType = HttpResponse<IFicheFacteurXIII[]>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurXIIIService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fiche-facteur-xiiis');

  create(ficheFacteurXIII: NewFicheFacteurXIII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurXIII);
    return this.http
      .post<RestFicheFacteurXIII>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(ficheFacteurXIII: IFicheFacteurXIII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurXIII);
    return this.http
      .put<RestFicheFacteurXIII>(`${this.resourceUrl}/${this.getFicheFacteurXIIIIdentifier(ficheFacteurXIII)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(ficheFacteurXIII: PartialUpdateFicheFacteurXIII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurXIII);
    return this.http
      .patch<RestFicheFacteurXIII>(`${this.resourceUrl}/${this.getFicheFacteurXIIIIdentifier(ficheFacteurXIII)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFicheFacteurXIII>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFicheFacteurXIII[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFicheFacteurXIIIIdentifier(ficheFacteurXIII: Pick<IFicheFacteurXIII, 'id'>): number {
    return ficheFacteurXIII.id;
  }

  compareFicheFacteurXIII(o1: Pick<IFicheFacteurXIII, 'id'> | null, o2: Pick<IFicheFacteurXIII, 'id'> | null): boolean {
    return o1 && o2 ? this.getFicheFacteurXIIIIdentifier(o1) === this.getFicheFacteurXIIIIdentifier(o2) : o1 === o2;
  }

  addFicheFacteurXIIIToCollectionIfMissing<Type extends Pick<IFicheFacteurXIII, 'id'>>(
    ficheFacteurXIIICollection: Type[],
    ...ficheFacteurXIIISToCheck: (Type | null | undefined)[]
  ): Type[] {
    const ficheFacteurXIIIS: Type[] = ficheFacteurXIIISToCheck.filter(isPresent);
    if (ficheFacteurXIIIS.length > 0) {
      const ficheFacteurXIIICollectionIdentifiers = ficheFacteurXIIICollection.map(ficheFacteurXIIIItem =>
        this.getFicheFacteurXIIIIdentifier(ficheFacteurXIIIItem),
      );
      const ficheFacteurXIIISToAdd = ficheFacteurXIIIS.filter(ficheFacteurXIIIItem => {
        const ficheFacteurXIIIIdentifier = this.getFicheFacteurXIIIIdentifier(ficheFacteurXIIIItem);
        if (ficheFacteurXIIICollectionIdentifiers.includes(ficheFacteurXIIIIdentifier)) {
          return false;
        }
        ficheFacteurXIIICollectionIdentifiers.push(ficheFacteurXIIIIdentifier);
        return true;
      });
      return [...ficheFacteurXIIISToAdd, ...ficheFacteurXIIICollection];
    }
    return ficheFacteurXIIICollection;
  }

  protected convertDateFromClient<T extends IFicheFacteurXIII | NewFicheFacteurXIII | PartialUpdateFicheFacteurXIII>(
    ficheFacteurXIII: T,
  ): RestOf<T> {
    return {
      ...ficheFacteurXIII,
      dateNaissance: ficheFacteurXIII.dateNaissance?.format(DATE_FORMAT) ?? null,
      dateEnregistrementRegistre: ficheFacteurXIII.dateEnregistrementRegistre?.format(DATE_FORMAT) ?? null,
      dateDeces: ficheFacteurXIII.dateDeces?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFicheFacteurXIII: RestFicheFacteurXIII): IFicheFacteurXIII {
    return {
      ...restFicheFacteurXIII,
      dateNaissance: restFicheFacteurXIII.dateNaissance ? dayjs(restFicheFacteurXIII.dateNaissance) : undefined,
      dateEnregistrementRegistre: restFicheFacteurXIII.dateEnregistrementRegistre
        ? dayjs(restFicheFacteurXIII.dateEnregistrementRegistre)
        : undefined,
      dateDeces: restFicheFacteurXIII.dateDeces ? dayjs(restFicheFacteurXIII.dateDeces) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFicheFacteurXIII>): HttpResponse<IFicheFacteurXIII> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFicheFacteurXIII[]>): HttpResponse<IFicheFacteurXIII[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
