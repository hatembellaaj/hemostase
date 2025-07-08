import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFicheFacteurXVIII, NewFicheFacteurXVIII } from '../fiche-facteur-xviii.model';

export type PartialUpdateFicheFacteurXVIII = Partial<IFicheFacteurXVIII> & Pick<IFicheFacteurXVIII, 'id'>;

type RestOf<T extends IFicheFacteurXVIII | NewFicheFacteurXVIII> = Omit<
  T,
  'dateNaissance' | 'dateEnregistrementRegistre' | 'datePremiereConsultation' | 'dateDeces'
> & {
  dateNaissance?: string | null;
  dateEnregistrementRegistre?: string | null;
  datePremiereConsultation?: string | null;
  dateDeces?: string | null;
};

export type RestFicheFacteurXVIII = RestOf<IFicheFacteurXVIII>;

export type NewRestFicheFacteurXVIII = RestOf<NewFicheFacteurXVIII>;

export type PartialUpdateRestFicheFacteurXVIII = RestOf<PartialUpdateFicheFacteurXVIII>;

export type EntityResponseType = HttpResponse<IFicheFacteurXVIII>;
export type EntityArrayResponseType = HttpResponse<IFicheFacteurXVIII[]>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurXVIIIService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fiche-facteur-xviiis');

  create(ficheFacteurXVIII: NewFicheFacteurXVIII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurXVIII);
    return this.http
      .post<RestFicheFacteurXVIII>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(ficheFacteurXVIII: IFicheFacteurXVIII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurXVIII);
    return this.http
      .put<RestFicheFacteurXVIII>(`${this.resourceUrl}/${this.getFicheFacteurXVIIIIdentifier(ficheFacteurXVIII)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(ficheFacteurXVIII: PartialUpdateFicheFacteurXVIII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurXVIII);
    return this.http
      .patch<RestFicheFacteurXVIII>(`${this.resourceUrl}/${this.getFicheFacteurXVIIIIdentifier(ficheFacteurXVIII)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFicheFacteurXVIII>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFicheFacteurXVIII[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFicheFacteurXVIIIIdentifier(ficheFacteurXVIII: Pick<IFicheFacteurXVIII, 'id'>): number {
    return ficheFacteurXVIII.id;
  }

  compareFicheFacteurXVIII(o1: Pick<IFicheFacteurXVIII, 'id'> | null, o2: Pick<IFicheFacteurXVIII, 'id'> | null): boolean {
    return o1 && o2 ? this.getFicheFacteurXVIIIIdentifier(o1) === this.getFicheFacteurXVIIIIdentifier(o2) : o1 === o2;
  }

  addFicheFacteurXVIIIToCollectionIfMissing<Type extends Pick<IFicheFacteurXVIII, 'id'>>(
    ficheFacteurXVIIICollection: Type[],
    ...ficheFacteurXVIIISToCheck: (Type | null | undefined)[]
  ): Type[] {
    const ficheFacteurXVIIIS: Type[] = ficheFacteurXVIIISToCheck.filter(isPresent);
    if (ficheFacteurXVIIIS.length > 0) {
      const ficheFacteurXVIIICollectionIdentifiers = ficheFacteurXVIIICollection.map(ficheFacteurXVIIIItem =>
        this.getFicheFacteurXVIIIIdentifier(ficheFacteurXVIIIItem),
      );
      const ficheFacteurXVIIISToAdd = ficheFacteurXVIIIS.filter(ficheFacteurXVIIIItem => {
        const ficheFacteurXVIIIIdentifier = this.getFicheFacteurXVIIIIdentifier(ficheFacteurXVIIIItem);
        if (ficheFacteurXVIIICollectionIdentifiers.includes(ficheFacteurXVIIIIdentifier)) {
          return false;
        }
        ficheFacteurXVIIICollectionIdentifiers.push(ficheFacteurXVIIIIdentifier);
        return true;
      });
      return [...ficheFacteurXVIIISToAdd, ...ficheFacteurXVIIICollection];
    }
    return ficheFacteurXVIIICollection;
  }

  protected convertDateFromClient<T extends IFicheFacteurXVIII | NewFicheFacteurXVIII | PartialUpdateFicheFacteurXVIII>(
    ficheFacteurXVIII: T,
  ): RestOf<T> {
    return {
      ...ficheFacteurXVIII,
      dateNaissance: ficheFacteurXVIII.dateNaissance?.format(DATE_FORMAT) ?? null,
      dateEnregistrementRegistre: ficheFacteurXVIII.dateEnregistrementRegistre?.format(DATE_FORMAT) ?? null,
      datePremiereConsultation: ficheFacteurXVIII.datePremiereConsultation?.format(DATE_FORMAT) ?? null,
      dateDeces: ficheFacteurXVIII.dateDeces?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFicheFacteurXVIII: RestFicheFacteurXVIII): IFicheFacteurXVIII {
    return {
      ...restFicheFacteurXVIII,
      dateNaissance: restFicheFacteurXVIII.dateNaissance ? dayjs(restFicheFacteurXVIII.dateNaissance) : undefined,
      dateEnregistrementRegistre: restFicheFacteurXVIII.dateEnregistrementRegistre
        ? dayjs(restFicheFacteurXVIII.dateEnregistrementRegistre)
        : undefined,
      datePremiereConsultation: restFicheFacteurXVIII.datePremiereConsultation
        ? dayjs(restFicheFacteurXVIII.datePremiereConsultation)
        : undefined,
      dateDeces: restFicheFacteurXVIII.dateDeces ? dayjs(restFicheFacteurXVIII.dateDeces) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFicheFacteurXVIII>): HttpResponse<IFicheFacteurXVIII> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFicheFacteurXVIII[]>): HttpResponse<IFicheFacteurXVIII[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
