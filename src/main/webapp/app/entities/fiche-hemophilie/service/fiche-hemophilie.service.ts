import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFicheHemophilie, NewFicheHemophilie } from '../fiche-hemophilie.model';

export type PartialUpdateFicheHemophilie = Partial<IFicheHemophilie> & Pick<IFicheHemophilie, 'id'>;

type RestOf<T extends IFicheHemophilie | NewFicheHemophilie> = Omit<T, 'dateNaissance' | 'dateEnregistrementRegistre'> & {
  dateNaissance?: string | null;
  dateEnregistrementRegistre?: string | null;
};

export type RestFicheHemophilie = RestOf<IFicheHemophilie>;

export type NewRestFicheHemophilie = RestOf<NewFicheHemophilie>;

export type PartialUpdateRestFicheHemophilie = RestOf<PartialUpdateFicheHemophilie>;

export type EntityResponseType = HttpResponse<IFicheHemophilie>;
export type EntityArrayResponseType = HttpResponse<IFicheHemophilie[]>;

@Injectable({ providedIn: 'root' })
export class FicheHemophilieService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fiche-hemophilies');

  create(ficheHemophilie: NewFicheHemophilie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheHemophilie);
    return this.http
      .post<RestFicheHemophilie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(ficheHemophilie: IFicheHemophilie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheHemophilie);
    return this.http
      .put<RestFicheHemophilie>(`${this.resourceUrl}/${this.getFicheHemophilieIdentifier(ficheHemophilie)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(ficheHemophilie: PartialUpdateFicheHemophilie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheHemophilie);
    return this.http
      .patch<RestFicheHemophilie>(`${this.resourceUrl}/${this.getFicheHemophilieIdentifier(ficheHemophilie)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFicheHemophilie>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFicheHemophilie[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFicheHemophilieIdentifier(ficheHemophilie: Pick<IFicheHemophilie, 'id'>): number {
    return ficheHemophilie.id;
  }

  compareFicheHemophilie(o1: Pick<IFicheHemophilie, 'id'> | null, o2: Pick<IFicheHemophilie, 'id'> | null): boolean {
    return o1 && o2 ? this.getFicheHemophilieIdentifier(o1) === this.getFicheHemophilieIdentifier(o2) : o1 === o2;
  }

  addFicheHemophilieToCollectionIfMissing<Type extends Pick<IFicheHemophilie, 'id'>>(
    ficheHemophilieCollection: Type[],
    ...ficheHemophiliesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const ficheHemophilies: Type[] = ficheHemophiliesToCheck.filter(isPresent);
    if (ficheHemophilies.length > 0) {
      const ficheHemophilieCollectionIdentifiers = ficheHemophilieCollection.map(ficheHemophilieItem =>
        this.getFicheHemophilieIdentifier(ficheHemophilieItem),
      );
      const ficheHemophiliesToAdd = ficheHemophilies.filter(ficheHemophilieItem => {
        const ficheHemophilieIdentifier = this.getFicheHemophilieIdentifier(ficheHemophilieItem);
        if (ficheHemophilieCollectionIdentifiers.includes(ficheHemophilieIdentifier)) {
          return false;
        }
        ficheHemophilieCollectionIdentifiers.push(ficheHemophilieIdentifier);
        return true;
      });
      return [...ficheHemophiliesToAdd, ...ficheHemophilieCollection];
    }
    return ficheHemophilieCollection;
  }

  protected convertDateFromClient<T extends IFicheHemophilie | NewFicheHemophilie | PartialUpdateFicheHemophilie>(
    ficheHemophilie: T,
  ): RestOf<T> {
    return {
      ...ficheHemophilie,
      dateNaissance: ficheHemophilie.dateNaissance?.format(DATE_FORMAT) ?? null,
      dateEnregistrementRegistre: ficheHemophilie.dateEnregistrementRegistre?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFicheHemophilie: RestFicheHemophilie): IFicheHemophilie {
    return {
      ...restFicheHemophilie,
      dateNaissance: restFicheHemophilie.dateNaissance ? dayjs(restFicheHemophilie.dateNaissance) : undefined,
      dateEnregistrementRegistre: restFicheHemophilie.dateEnregistrementRegistre
        ? dayjs(restFicheHemophilie.dateEnregistrementRegistre)
        : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFicheHemophilie>): HttpResponse<IFicheHemophilie> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFicheHemophilie[]>): HttpResponse<IFicheHemophilie[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
