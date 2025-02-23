import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFicheFacteurVII, NewFicheFacteurVII } from '../fiche-facteur-vii.model';

export type PartialUpdateFicheFacteurVII = Partial<IFicheFacteurVII> & Pick<IFicheFacteurVII, 'id'>;

type RestOf<T extends IFicheFacteurVII | NewFicheFacteurVII> = Omit<
  T,
  'dateNaissance' | 'dateEnregistrementRegistre' | 'datePremierSigneClinique' | 'dateDeces'
> & {
  dateNaissance?: string | null;
  dateEnregistrementRegistre?: string | null;
  datePremierSigneClinique?: string | null;
  dateDeces?: string | null;
};

export type RestFicheFacteurVII = RestOf<IFicheFacteurVII>;

export type NewRestFicheFacteurVII = RestOf<NewFicheFacteurVII>;

export type PartialUpdateRestFicheFacteurVII = RestOf<PartialUpdateFicheFacteurVII>;

export type EntityResponseType = HttpResponse<IFicheFacteurVII>;
export type EntityArrayResponseType = HttpResponse<IFicheFacteurVII[]>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurVIIService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fiche-facteur-viis');

  create(ficheFacteurVII: NewFicheFacteurVII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurVII);
    return this.http
      .post<RestFicheFacteurVII>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(ficheFacteurVII: IFicheFacteurVII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurVII);
    return this.http
      .put<RestFicheFacteurVII>(`${this.resourceUrl}/${this.getFicheFacteurVIIIdentifier(ficheFacteurVII)}`, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(ficheFacteurVII: PartialUpdateFicheFacteurVII): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheFacteurVII);
    return this.http
      .patch<RestFicheFacteurVII>(`${this.resourceUrl}/${this.getFicheFacteurVIIIdentifier(ficheFacteurVII)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFicheFacteurVII>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFicheFacteurVII[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFicheFacteurVIIIdentifier(ficheFacteurVII: Pick<IFicheFacteurVII, 'id'>): number {
    return ficheFacteurVII.id;
  }

  compareFicheFacteurVII(o1: Pick<IFicheFacteurVII, 'id'> | null, o2: Pick<IFicheFacteurVII, 'id'> | null): boolean {
    return o1 && o2 ? this.getFicheFacteurVIIIdentifier(o1) === this.getFicheFacteurVIIIdentifier(o2) : o1 === o2;
  }

  addFicheFacteurVIIToCollectionIfMissing<Type extends Pick<IFicheFacteurVII, 'id'>>(
    ficheFacteurVIICollection: Type[],
    ...ficheFacteurVIISToCheck: (Type | null | undefined)[]
  ): Type[] {
    const ficheFacteurVIIS: Type[] = ficheFacteurVIISToCheck.filter(isPresent);
    if (ficheFacteurVIIS.length > 0) {
      const ficheFacteurVIICollectionIdentifiers = ficheFacteurVIICollection.map(ficheFacteurVIIItem =>
        this.getFicheFacteurVIIIdentifier(ficheFacteurVIIItem),
      );
      const ficheFacteurVIISToAdd = ficheFacteurVIIS.filter(ficheFacteurVIIItem => {
        const ficheFacteurVIIIdentifier = this.getFicheFacteurVIIIdentifier(ficheFacteurVIIItem);
        if (ficheFacteurVIICollectionIdentifiers.includes(ficheFacteurVIIIdentifier)) {
          return false;
        }
        ficheFacteurVIICollectionIdentifiers.push(ficheFacteurVIIIdentifier);
        return true;
      });
      return [...ficheFacteurVIISToAdd, ...ficheFacteurVIICollection];
    }
    return ficheFacteurVIICollection;
  }

  protected convertDateFromClient<T extends IFicheFacteurVII | NewFicheFacteurVII | PartialUpdateFicheFacteurVII>(
    ficheFacteurVII: T,
  ): RestOf<T> {
    return {
      ...ficheFacteurVII,
      dateNaissance: ficheFacteurVII.dateNaissance?.format(DATE_FORMAT) ?? null,
      dateEnregistrementRegistre: ficheFacteurVII.dateEnregistrementRegistre?.format(DATE_FORMAT) ?? null,
      datePremierSigneClinique: ficheFacteurVII.datePremierSigneClinique?.format(DATE_FORMAT) ?? null,
      dateDeces: ficheFacteurVII.dateDeces?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFicheFacteurVII: RestFicheFacteurVII): IFicheFacteurVII {
    return {
      ...restFicheFacteurVII,
      dateNaissance: restFicheFacteurVII.dateNaissance ? dayjs(restFicheFacteurVII.dateNaissance) : undefined,
      dateEnregistrementRegistre: restFicheFacteurVII.dateEnregistrementRegistre
        ? dayjs(restFicheFacteurVII.dateEnregistrementRegistre)
        : undefined,
      datePremierSigneClinique: restFicheFacteurVII.datePremierSigneClinique
        ? dayjs(restFicheFacteurVII.datePremierSigneClinique)
        : undefined,
      dateDeces: restFicheFacteurVII.dateDeces ? dayjs(restFicheFacteurVII.dateDeces) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFicheFacteurVII>): HttpResponse<IFicheFacteurVII> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFicheFacteurVII[]>): HttpResponse<IFicheFacteurVII[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
