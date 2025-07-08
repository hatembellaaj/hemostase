import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFicheFacteurXI, NewFicheFacteurXI } from '../fiche-facteur-xi.model';

export type PartialUpdateFicheFacteurXI = Partial<IFicheFacteurXI> & Pick<IFicheFacteurXI, 'id'>;

type RestOf<T extends IFicheFacteurXI | NewFicheFacteurXI> = Omit<
  T,
  'dateNaissance' | 'dateEnregistrementRegistre' | 'datePremiereConsultation' | 'dateDeces'
> & {
  dateNaissance?: string | null;
  dateEnregistrementRegistre?: string | null;
  datePremiereConsultation?: string | null;
  dateDeces?: string | null;
};

export type RestFicheFacteurXI = RestOf<IFicheFacteurXI>;

export type NewRestFicheFacteurXI = RestOf<NewFicheFacteurXI>;

export type PartialUpdateRestFicheFacteurXI = RestOf<PartialUpdateFicheFacteurXI>;

export type EntityResponseType = HttpResponse<IFicheFacteurXI>;
export type EntityArrayResponseType = HttpResponse<IFicheFacteurXI[]>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurXIService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fiche-facteur-xis');

  create(ficheFacteurXI: NewFicheFacteurXI): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurXI);
    return this.http
      .post<RestFicheFacteurXI>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(ficheFacteurXI: IFicheFacteurXI): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurXI);
    return this.http
      .put<RestFicheFacteurXI>(`${this.resourceUrl}/${this.getFicheFacteurXIIdentifier(ficheFacteurXI)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(ficheFacteurXI: PartialUpdateFicheFacteurXI): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurXI);
    return this.http
      .patch<RestFicheFacteurXI>(`${this.resourceUrl}/${this.getFicheFacteurXIIdentifier(ficheFacteurXI)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFicheFacteurXI>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFicheFacteurXI[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFicheFacteurXIIdentifier(ficheFacteurXI: Pick<IFicheFacteurXI, 'id'>): number {
    return ficheFacteurXI.id;
  }

  compareFicheFacteurXI(o1: Pick<IFicheFacteurXI, 'id'> | null, o2: Pick<IFicheFacteurXI, 'id'> | null): boolean {
    return o1 && o2 ? this.getFicheFacteurXIIdentifier(o1) === this.getFicheFacteurXIIdentifier(o2) : o1 === o2;
  }

  addFicheFacteurXIToCollectionIfMissing<Type extends Pick<IFicheFacteurXI, 'id'>>(
    ficheFacteurXICollection: Type[],
    ...ficheFacteurXISToCheck: (Type | null | undefined)[]
  ): Type[] {
    const ficheFacteurXIS: Type[] = ficheFacteurXISToCheck.filter(isPresent);
    if (ficheFacteurXIS.length > 0) {
      const ficheFacteurXICollectionIdentifiers = ficheFacteurXICollection.map(ficheFacteurXIItem =>
        this.getFicheFacteurXIIdentifier(ficheFacteurXIItem),
      );
      const ficheFacteurXISToAdd = ficheFacteurXIS.filter(ficheFacteurXIItem => {
        const ficheFacteurXIIdentifier = this.getFicheFacteurXIIdentifier(ficheFacteurXIItem);
        if (ficheFacteurXICollectionIdentifiers.includes(ficheFacteurXIIdentifier)) {
          return false;
        }
        ficheFacteurXICollectionIdentifiers.push(ficheFacteurXIIdentifier);
        return true;
      });
      return [...ficheFacteurXISToAdd, ...ficheFacteurXICollection];
    }
    return ficheFacteurXICollection;
  }

  protected convertDateFromClient<T extends IFicheFacteurXI | NewFicheFacteurXI | PartialUpdateFicheFacteurXI>(
    ficheFacteurXI: T,
  ): RestOf<T> {
    return {
      ...ficheFacteurXI,
      dateNaissance: ficheFacteurXI.dateNaissance?.format(DATE_FORMAT) ?? null,
      dateEnregistrementRegistre: ficheFacteurXI.dateEnregistrementRegistre?.format(DATE_FORMAT) ?? null,
      datePremiereConsultation: ficheFacteurXI.datePremiereConsultation?.format(DATE_FORMAT) ?? null,
      dateDeces: ficheFacteurXI.dateDeces?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFicheFacteurXI: RestFicheFacteurXI): IFicheFacteurXI {
    return {
      ...restFicheFacteurXI,
      dateNaissance: restFicheFacteurXI.dateNaissance ? dayjs(restFicheFacteurXI.dateNaissance) : undefined,
      dateEnregistrementRegistre: restFicheFacteurXI.dateEnregistrementRegistre
        ? dayjs(restFicheFacteurXI.dateEnregistrementRegistre)
        : undefined,
      datePremiereConsultation: restFicheFacteurXI.datePremiereConsultation
        ? dayjs(restFicheFacteurXI.datePremiereConsultation)
        : undefined,
      dateDeces: restFicheFacteurXI.dateDeces ? dayjs(restFicheFacteurXI.dateDeces) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFicheFacteurXI>): HttpResponse<IFicheFacteurXI> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFicheFacteurXI[]>): HttpResponse<IFicheFacteurXI[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
