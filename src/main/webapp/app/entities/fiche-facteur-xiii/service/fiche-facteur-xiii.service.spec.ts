import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IFicheFacteurXIII } from '../fiche-facteur-xiii.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../fiche-facteur-xiii.test-samples';

import { FicheFacteurXIIIService, RestFicheFacteurXIII } from './fiche-facteur-xiii.service';

const requireRestSample: RestFicheFacteurXIII = {
  ...sampleWithRequiredData,
  dateNaissance: sampleWithRequiredData.dateNaissance?.format(DATE_FORMAT),
  dateEnregistrementRegistre: sampleWithRequiredData.dateEnregistrementRegistre?.format(DATE_FORMAT),
  dateDeces: sampleWithRequiredData.dateDeces?.format(DATE_FORMAT),
};

describe('FicheFacteurXIII Service', () => {
  let service: FicheFacteurXIIIService;
  let httpMock: HttpTestingController;
  let expectedResult: IFicheFacteurXIII | IFicheFacteurXIII[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(FicheFacteurXIIIService);
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

    it('should create a FicheFacteurXIII', () => {
      const ficheFacteurXIII = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(ficheFacteurXIII).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FicheFacteurXIII', () => {
      const ficheFacteurXIII = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(ficheFacteurXIII).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FicheFacteurXIII', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FicheFacteurXIII', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FicheFacteurXIII', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFicheFacteurXIIIToCollectionIfMissing', () => {
      it('should add a FicheFacteurXIII to an empty array', () => {
        const ficheFacteurXIII: IFicheFacteurXIII = sampleWithRequiredData;
        expectedResult = service.addFicheFacteurXIIIToCollectionIfMissing([], ficheFacteurXIII);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheFacteurXIII);
      });

      it('should not add a FicheFacteurXIII to an array that contains it', () => {
        const ficheFacteurXIII: IFicheFacteurXIII = sampleWithRequiredData;
        const ficheFacteurXIIICollection: IFicheFacteurXIII[] = [
          {
            ...ficheFacteurXIII,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFicheFacteurXIIIToCollectionIfMissing(ficheFacteurXIIICollection, ficheFacteurXIII);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FicheFacteurXIII to an array that doesn't contain it", () => {
        const ficheFacteurXIII: IFicheFacteurXIII = sampleWithRequiredData;
        const ficheFacteurXIIICollection: IFicheFacteurXIII[] = [sampleWithPartialData];
        expectedResult = service.addFicheFacteurXIIIToCollectionIfMissing(ficheFacteurXIIICollection, ficheFacteurXIII);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheFacteurXIII);
      });

      it('should add only unique FicheFacteurXIII to an array', () => {
        const ficheFacteurXIIIArray: IFicheFacteurXIII[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const ficheFacteurXIIICollection: IFicheFacteurXIII[] = [sampleWithRequiredData];
        expectedResult = service.addFicheFacteurXIIIToCollectionIfMissing(ficheFacteurXIIICollection, ...ficheFacteurXIIIArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ficheFacteurXIII: IFicheFacteurXIII = sampleWithRequiredData;
        const ficheFacteurXIII2: IFicheFacteurXIII = sampleWithPartialData;
        expectedResult = service.addFicheFacteurXIIIToCollectionIfMissing([], ficheFacteurXIII, ficheFacteurXIII2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheFacteurXIII);
        expect(expectedResult).toContain(ficheFacteurXIII2);
      });

      it('should accept null and undefined values', () => {
        const ficheFacteurXIII: IFicheFacteurXIII = sampleWithRequiredData;
        expectedResult = service.addFicheFacteurXIIIToCollectionIfMissing([], null, ficheFacteurXIII, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheFacteurXIII);
      });

      it('should return initial array if no FicheFacteurXIII is added', () => {
        const ficheFacteurXIIICollection: IFicheFacteurXIII[] = [sampleWithRequiredData];
        expectedResult = service.addFicheFacteurXIIIToCollectionIfMissing(ficheFacteurXIIICollection, undefined, null);
        expect(expectedResult).toEqual(ficheFacteurXIIICollection);
      });
    });

    describe('compareFicheFacteurXIII', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFicheFacteurXIII(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFicheFacteurXIII(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurXIII(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFicheFacteurXIII(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurXIII(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFicheFacteurXIII(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurXIII(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
