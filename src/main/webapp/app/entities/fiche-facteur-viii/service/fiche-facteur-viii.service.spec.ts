import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IFicheFacteurVIII } from '../fiche-facteur-viii.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../fiche-facteur-viii.test-samples';

import { FicheFacteurVIIIService, RestFicheFacteurVIII } from './fiche-facteur-viii.service';

const requireRestSample: RestFicheFacteurVIII = {
  ...sampleWithRequiredData,
  dateNaissance: sampleWithRequiredData.dateNaissance?.format(DATE_FORMAT),
  dateEnregistrementRegistre: sampleWithRequiredData.dateEnregistrementRegistre?.format(DATE_FORMAT),
  datePremierSigneClinique: sampleWithRequiredData.datePremierSigneClinique?.format(DATE_FORMAT),
  dateDeces: sampleWithRequiredData.dateDeces?.format(DATE_FORMAT),
};

describe('FicheFacteurVIII Service', () => {
  let service: FicheFacteurVIIIService;
  let httpMock: HttpTestingController;
  let expectedResult: IFicheFacteurVIII | IFicheFacteurVIII[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(FicheFacteurVIIIService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  describe('Service methods', () => {
    it('should find an element', () => {
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.find(123).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should create a FicheFacteurVIII', () => {
      const ficheFacteurVIII = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(ficheFacteurVIII).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FicheFacteurVIII', () => {
      const ficheFacteurVIII = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(ficheFacteurVIII).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FicheFacteurVIII', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FicheFacteurVIII', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FicheFacteurVIII', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFicheFacteurVIIIToCollectionIfMissing', () => {
      it('should add a FicheFacteurVIII to an empty array', () => {
        const ficheFacteurVIII: IFicheFacteurVIII = sampleWithRequiredData;
        expectedResult = service.addFicheFacteurVIIIToCollectionIfMissing([], ficheFacteurVIII);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheFacteurVIII);
      });

      it('should not add a FicheFacteurVIII to an array that contains it', () => {
        const ficheFacteurVIII: IFicheFacteurVIII = sampleWithRequiredData;
        const ficheFacteurVIIICollection: IFicheFacteurVIII[] = [
          {
            ...ficheFacteurVIII,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFicheFacteurVIIIToCollectionIfMissing(ficheFacteurVIIICollection, ficheFacteurVIII);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FicheFacteurVIII to an array that doesn't contain it", () => {
        const ficheFacteurVIII: IFicheFacteurVIII = sampleWithRequiredData;
        const ficheFacteurVIIICollection: IFicheFacteurVIII[] = [sampleWithPartialData];
        expectedResult = service.addFicheFacteurVIIIToCollectionIfMissing(ficheFacteurVIIICollection, ficheFacteurVIII);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheFacteurVIII);
      });

      it('should add only unique FicheFacteurVIII to an array', () => {
        const ficheFacteurVIIIArray: IFicheFacteurVIII[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const ficheFacteurVIIICollection: IFicheFacteurVIII[] = [sampleWithRequiredData];
        expectedResult = service.addFicheFacteurVIIIToCollectionIfMissing(ficheFacteurVIIICollection, ...ficheFacteurVIIIArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ficheFacteurVIII: IFicheFacteurVIII = sampleWithRequiredData;
        const ficheFacteurVIII2: IFicheFacteurVIII = sampleWithPartialData;
        expectedResult = service.addFicheFacteurVIIIToCollectionIfMissing([], ficheFacteurVIII, ficheFacteurVIII2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheFacteurVIII);
        expect(expectedResult).toContain(ficheFacteurVIII2);
      });

      it('should accept null and undefined values', () => {
        const ficheFacteurVIII: IFicheFacteurVIII = sampleWithRequiredData;
        expectedResult = service.addFicheFacteurVIIIToCollectionIfMissing([], null, ficheFacteurVIII, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheFacteurVIII);
      });

      it('should return initial array if no FicheFacteurVIII is added', () => {
        const ficheFacteurVIIICollection: IFicheFacteurVIII[] = [sampleWithRequiredData];
        expectedResult = service.addFicheFacteurVIIIToCollectionIfMissing(ficheFacteurVIIICollection, undefined, null);
        expect(expectedResult).toEqual(ficheFacteurVIIICollection);
      });
    });

    describe('compareFicheFacteurVIII', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFicheFacteurVIII(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFicheFacteurVIII(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurVIII(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFicheFacteurVIII(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurVIII(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFicheFacteurVIII(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurVIII(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
