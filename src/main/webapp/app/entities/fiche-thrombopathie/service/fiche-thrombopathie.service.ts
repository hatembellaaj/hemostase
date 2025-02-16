import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable, map } from 'rxjs';

import dayjs from 'dayjs/esm';

import { isPresent } from 'app/core/util/operators';
import { DATE_FORMAT } from 'app/config/input.constants';
import { ApplicationConfigService } from 'app/core/config/application-config.service';
import { createRequestOption } from 'app/core/request/request-util';
import { IFicheThrombopathie, NewFicheThrombopathie } from '../fiche-thrombopathie.model';

export type PartialUpdateFicheThrombopathie = Partial<IFicheThrombopathie> & Pick<IFicheThrombopathie, 'id'>;

type RestOf<T extends IFicheThrombopathie | NewFicheThrombopathie> = Omit<T, 'dateNaissance' | 'dateEnregistrementRegistre'> & {
  dateNaissance?: string | null;
  dateEnregistrementRegistre?: string | null;
};

export type RestFicheThrombopathie = RestOf<IFicheThrombopathie>;

export type NewRestFicheThrombopathie = RestOf<NewFicheThrombopathie>;

export type PartialUpdateRestFicheThrombopathie = RestOf<PartialUpdateFicheThrombopathie>;

export type EntityResponseType = HttpResponse<IFicheThrombopathie>;
export type EntityArrayResponseType = HttpResponse<IFicheThrombopathie[]>;

@Injectable({ providedIn: 'root' })
export class FicheThrombopathieService {
  protected readonly http = inject(HttpClient);
  protected readonly applicationConfigService = inject(ApplicationConfigService);

  protected resourceUrl = this.applicationConfigService.getEndpointFor('api/fiche-thrombopathies');

  create(ficheThrombopathie: NewFicheThrombopathie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheThrombopathie);
    return this.http
      .post<RestFicheThrombopathie>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  update(ficheThrombopathie: IFicheThrombopathie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheThrombopathie);
    return this.http
      .put<RestFicheThrombopathie>(`${this.resourceUrl}/${this.getFicheThrombopathieIdentifier(ficheThrombopathie)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  partialUpdate(ficheThrombopathie: PartialUpdateFicheThrombopathie): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(ficheThrombopathie);
    return this.http
      .patch<RestFicheThrombopathie>(`${this.resourceUrl}/${this.getFicheThrombopathieIdentifier(ficheThrombopathie)}`, copy, {
        observe: 'response',
      })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<RestFicheThrombopathie>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map(res => this.convertResponseFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<RestFicheThrombopathie[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map(res => this.convertResponseArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  getFicheThrombopathieIdentifier(ficheThrombopathie: Pick<IFicheThrombopathie, 'id'>): number {
    return ficheThrombopathie.id;
  }

  compareFicheThrombopathie(o1: Pick<IFicheThrombopathie, 'id'> | null, o2: Pick<IFicheThrombopathie, 'id'> | null): boolean {
    return o1 && o2 ? this.getFicheThrombopathieIdentifier(o1) === this.getFicheThrombopathieIdentifier(o2) : o1 === o2;
  }

  addFicheThrombopathieToCollectionIfMissing<Type extends Pick<IFicheThrombopathie, 'id'>>(
    ficheThrombopathieCollection: Type[],
    ...ficheThrombopathiesToCheck: (Type | null | undefined)[]
  ): Type[] {
    const ficheThrombopathies: Type[] = ficheThrombopathiesToCheck.filter(isPresent);
    if (ficheThrombopathies.length > 0) {
      const ficheThrombopathieCollectionIdentifiers = ficheThrombopathieCollection.map(ficheThrombopathieItem =>
        this.getFicheThrombopathieIdentifier(ficheThrombopathieItem),
      );
      const ficheThrombopathiesToAdd = ficheThrombopathies.filter(ficheThrombopathieItem => {
        const ficheThrombopathieIdentifier = this.getFicheThrombopathieIdentifier(ficheThrombopathieItem);
        if (ficheThrombopathieCollectionIdentifiers.includes(ficheThrombopathieIdentifier)) {
          return false;
        }
        ficheThrombopathieCollectionIdentifiers.push(ficheThrombopathieIdentifier);
        return true;
      });
      return [...ficheThrombopathiesToAdd, ...ficheThrombopathieCollection];
    }
    return ficheThrombopathieCollection;
  }

  protected convertDateFromClient<T extends IFicheThrombopathie | NewFicheThrombopathie | PartialUpdateFicheThrombopathie>(
    ficheThrombopathie: T,
  ): RestOf<T> {
    return {
      ...ficheThrombopathie,
      dateNaissance: ficheThrombopathie.dateNaissance?.format(DATE_FORMAT) ?? null,
      dateEnregistrementRegistre: ficheThrombopathie.dateEnregistrementRegistre?.format(DATE_FORMAT) ?? null,
    };
  }

  protected convertDateFromServer(restFicheThrombopathie: RestFicheThrombopathie): IFicheThrombopathie {
    return {
      ...restFicheThrombopathie,
      dateNaissance: restFicheThrombopathie.dateNaissance ? dayjs(restFicheThrombopathie.dateNaissance) : undefined,
      dateEnregistrementRegistre: restFicheThrombopathie.dateEnregistrementRegistre
        ? dayjs(restFicheThrombopathie.dateEnregistrementRegistre)
        : undefined,
    };
  }

  protected convertResponseFromServer(res: HttpResponse<RestFicheThrombopathie>): HttpResponse<IFicheThrombopathie> {
    return res.clone({
      body: res.body ? this.convertDateFromServer(res.body) : null,
    });
  }

  protected convertResponseArrayFromServer(res: HttpResponse<RestFicheThrombopathie[]>): HttpResponse<IFicheThrombopathie[]> {
    return res.clone({
      body: res.body ? res.body.map(item => this.convertDateFromServer(item)) : null,
    });
  }
}
