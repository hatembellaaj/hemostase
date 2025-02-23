import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFicheFacteurVIII, NewFicheFacteurVIII } from '../fiche-facteur-viii.model';

export type PartialUpdateFicheFacteurVIII = Partial<IFicheFacteurVIII> & Pick<IFicheFacteurVIII, 'id'>;

type RestOf<T extends IFicheFacteurVIII | NewFicheFacteurVIII> = Omit<
  T,
  'dateNaissance' | 'dateEnregistrementRegistre' | 'datePremierSigneClinique' | 'dateDeces'
> & {
  dateNaissance?: string | null;
  dateEnregistrementRegistre?: string | null;
  datePremierSigneClinique?: string | null;
  dateDeces?: string | null;
};

export type RestFicheFacteurVIII = RestOf<IFicheFacteurVIII>;

export type NewRestFicheFacteurVIII = RestOf<NewFicheFacteurVIII>;

export type PartialUpdateRestFicheFacteurVIII = RestOf<PartialUpdateFicheFacteurVIII>;

export type EntityResponseType = HttpResponse<IFicheFacteurVIII>;
export type EntityArrayResponseType = HttpResponse<IFicheFacteurVIII[]>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurVIIIService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fiche-facteur-viiis');

  create(ficheFacteurVIII: NewFicheFacteurVIII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurVIII);
    return this.http
      .post<RestFicheFacteurVIII>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(ficheFacteurVIII: IFicheFacteurVIII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurVIII);
    return this.http
      .put<RestFicheFacteurVIII>(`${this.resourceUrl}/${this.getFicheFacteurVIIIIdentifier(ficheFacteurVIII)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(ficheFacteurVIII: PartialUpdateFicheFacteurVIII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurVIII);
    return this.http
      .patch<RestFicheFacteurVIII>(`${this.resourceUrl}/${this.getFicheFacteurVIIIIdentifier(ficheFacteurVIII)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFicheFacteurVIII>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFicheFacteurVIII[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFicheFacteurVIIIIdentifier(ficheFacteurVIII: Pick<IFicheFacteurVIII, 'id'>): number {
    return ficheFacteurVIII.id;
  }

  compareFicheFacteurVIII(o1: Pick<IFicheFacteurVIII, 'id'> | null, o2: Pick<IFicheFacteurVIII, 'id'> | null): boolean {
    return o1 && o2 ? this.getFicheFacteurVIIIIdentifier(o1) === this.getFicheFacteurVIIIIdentifier(o2) : o1 === o2;
  }

  addFicheFacteurVIIIToCollectionIfMissing<Type extends Pick<IFicheFacteurVIII, 'id'>>(
    ficheFacteurVIIICollection: Type[],
    ...ficheFacteurVIIISToCheck: (Type | null | undefined)[]
  ): Type[] {
    const ficheFacteurVIIIS: Type[] = ficheFacteurVIIISToCheck.filter(isPresent);
    if (ficheFacteurVIIIS.length > 0) {
      const ficheFacteurVIIICollectionIdentifiers = ficheFacteurVIIICollection.map(ficheFacteurVIIIItem =>
        this.getFicheFacteurVIIIIdentifier(ficheFacteurVIIIItem),
      );
      const ficheFacteurVIIISToAdd = ficheFacteurVIIIS.filter(ficheFacteurVIIIItem => {
        const ficheFacteurVIIIIdentifier = this.getFicheFacteurVIIIIdentifier(ficheFacteurVIIIItem);
        if (ficheFacteurVIIICollectionIdentifiers.includes(ficheFacteurVIIIIdentifier)) {
          return false;
        }
        ficheFacteurVIIICollectionIdentifiers.push(ficheFacteurVIIIIdentifier);
        return true;
      });
      return [...ficheFacteurVIIISToAdd, ...ficheFacteurVIIICollection];
    }
    return ficheFacteurVIIICollection;
  }

  protected convertDateFromClient<T extends IFicheFacteurVIII | NewFicheFacteurVIII | PartialUpdateFicheFacteurVIII>(
    ficheFacteurVIII: T,
  ): RestOf<T> {
    return {
      ...ficheFacteurVIII,
      dateNaissance: ficheFacteurVIII.dateNaissance?.format(DATE_FORMAT) ?? null,
      dateEnregistrementRegistre: ficheFacteurVIII.dateEnregistrementRegistre?.format(DATE_FORMAT) ?? null,
      datePremierSigneClinique: ficheFacteurVIII.datePremierSigneClinique?.format(DATE_FORMAT) ?? null,
      dateDeces: ficheFacteurVIII.dateDeces?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFicheFacteurVIII: RestFicheFacteurVIII): IFicheFacteurVIII {
    return {
      ...restFicheFacteurVIII,
      dateNaissance: restFicheFacteurVIII.dateNaissance ? dayjs(restFicheFacteurVIII.dateNaissance) : undefined,
      dateEnregistrementRegistre: restFicheFacteurVIII.dateEnregistrementRegistre
        ? dayjs(restFicheFacteurVIII.dateEnregistrementRegistre)
        : undefined,
      datePremierSigneClinique: restFicheFacteurVIII.datePremierSigneClinique
        ? dayjs(restFicheFacteurVIII.datePremierSigneClinique)
        : undefined,
      dateDeces: restFicheFacteurVIII.dateDeces ? dayjs(restFicheFacteurVIII.dateDeces) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFicheFacteurVIII>): HttpResponse<IFicheFacteurVIII> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFicheFacteurVIII[]>): HttpResponse<IFicheFacteurVIII[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
