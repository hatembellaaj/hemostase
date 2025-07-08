import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFicheVonWillebrand, NewFicheVonWillebrand } from '../fiche-von-willebrand.model';

export type PartialUpdateFicheVonWillebrand = Partial<IFicheVonWillebrand> & Pick<IFicheVonWillebrand, 'id'>;

type RestOf<T extends IFicheVonWillebrand | NewFicheVonWillebrand> = Omit<
  T,
  'dateNaissance' | 'dateEnregistrementRegistre' | 'datePremiereConsultation' | 'dateTestConfirmation' | 'dateDeces'
> & {
  dateNaissance?: string | null;
  dateEnregistrementRegistre?: string | null;
  datePremiereConsultation?: string | null;
  dateTestConfirmation?: string | null;
  dateDeces?: string | null;
};

export type RestFicheVonWillebrand = RestOf<IFicheVonWillebrand>;

export type NewRestFicheVonWillebrand = RestOf<NewFicheVonWillebrand>;

export type PartialUpdateRestFicheVonWillebrand = RestOf<PartialUpdateFicheVonWillebrand>;

export type EntityResponseType = HttpResponse<IFicheVonWillebrand>;
export type EntityArrayResponseType = HttpResponse<IFicheVonWillebrand[]>;

@Injectable({ providedIn: 'root' })
export class FicheVonWillebrandService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fiche-von-willebrands');

  create(ficheVonWillebrand: NewFicheVonWillebrand): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheVonWillebrand);
    return this.http
      .post<RestFicheVonWillebrand>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(ficheVonWillebrand: IFicheVonWillebrand): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheVonWillebrand);
    return this.http
      .put<RestFicheVonWillebrand>(`${this.resourceUrl}/${this.getFicheVonWillebrandIdentifier(ficheVonWillebrand)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(ficheVonWillebrand: PartialUpdateFicheVonWillebrand): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheVonWillebrand);
    return this.http
      .patch<RestFicheVonWillebrand>(`${this.resourceUrl}/${this.getFicheVonWillebrandIdentifier(ficheVonWillebrand)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFicheVonWillebrand>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFicheVonWillebrand[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFicheVonWillebrandIdentifier(ficheVonWillebrand: Pick<IFicheVonWillebrand, 'id'>): number {
    return ficheVonWillebrand.id;
  }

  compareFicheVonWillebrand(o1: Pick<IFicheVonWillebrand, 'id'> | null, o2: Pick<IFicheVonWillebrand, 'id'> | null): boolean {
    return o1 && o2 ? this.getFicheVonWillebrandIdentifier(o1) === this.getFicheVonWillebrandIdentifier(o2) : o1 === o2;
  }

  addFicheVonWillebrandToCollectionIfMissing<Type extends Pick<IFicheVonWillebrand, 'id'>>(
    ficheVonWillebrandCollection: Type[],
    ...ficheVonWillebrandsToCheck: (Type | null | undefined)[]
  ): Type[] {
    const ficheVonWillebrands: Type[] = ficheVonWillebrandsToCheck.filter(isPresent);
    if (ficheVonWillebrands.length > 0) {
      const ficheVonWillebrandCollectionIdentifiers = ficheVonWillebrandCollection.map(ficheVonWillebrandItem =>
        this.getFicheVonWillebrandIdentifier(ficheVonWillebrandItem),
      );
      const ficheVonWillebrandsToAdd = ficheVonWillebrands.filter(ficheVonWillebrandItem => {
        const ficheVonWillebrandIdentifier = this.getFicheVonWillebrandIdentifier(ficheVonWillebrandItem);
        if (ficheVonWillebrandCollectionIdentifiers.includes(ficheVonWillebrandIdentifier)) {
          return false;
        }
        ficheVonWillebrandCollectionIdentifiers.push(ficheVonWillebrandIdentifier);
        return true;
      });
      return [...ficheVonWillebrandsToAdd, ...ficheVonWillebrandCollection];
    }
    return ficheVonWillebrandCollection;
  }

  protected convertDateFromClient<T extends IFicheVonWillebrand | NewFicheVonWillebrand | PartialUpdateFicheVonWillebrand>(
    ficheVonWillebrand: T,
  ): RestOf<T> {
    return {
      ...ficheVonWillebrand,
      dateNaissance: ficheVonWillebrand.dateNaissance?.format(DATE_FORMAT) ?? null,
      dateEnregistrementRegistre: ficheVonWillebrand.dateEnregistrementRegistre?.format(DATE_FORMAT) ?? null,
      datePremiereConsultation: ficheVonWillebrand.datePremiereConsultation?.format(DATE_FORMAT) ?? null,
      dateTestConfirmation: ficheVonWillebrand.dateTestConfirmation?.format(DATE_FORMAT) ?? null,
      dateDeces: ficheVonWillebrand.dateDeces?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFicheVonWillebrand: RestFicheVonWillebrand): IFicheVonWillebrand {
    return {
      ...restFicheVonWillebrand,
      dateNaissance: restFicheVonWillebrand.dateNaissance ? dayjs(restFicheVonWillebrand.dateNaissance) : undefined,
      dateEnregistrementRegistre: restFicheVonWillebrand.dateEnregistrementRegistre
        ? dayjs(restFicheVonWillebrand.dateEnregistrementRegistre)
        : undefined,
      datePremiereConsultation: restFicheVonWillebrand.datePremiereConsultation
        ? dayjs(restFicheVonWillebrand.datePremiereConsultation)
        : undefined,
      dateTestConfirmation: restFicheVonWillebrand.dateTestConfirmation ? dayjs(restFicheVonWillebrand.dateTestConfirmation) : undefined,
      dateDeces: restFicheVonWillebrand.dateDeces ? dayjs(restFicheVonWillebrand.dateDeces) : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFicheVonWillebrand>): HttpResponse<IFicheVonWillebrand> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFicheVonWillebrand[]>): HttpResponse<IFicheVonWillebrand[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
