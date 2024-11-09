import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IFicheHemophilie } from '../fiche-hemophilie.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../fiche-hemophilie.test-samples';

import { FicheHemophilieService, RestFicheHemophilie } from './fiche-hemophilie.service';

const requireRestSample: RestFicheHemophilie = {
  ...sampleWithRequiredData,
  dateTestConfirmation: sampleWithRequiredData.dateTestConfirmation?.format(DATE_FORMAT),
  debutProphylaxie: sampleWithRequiredData.debutProphylaxie?.format(DATE_FORMAT),
  dateRecuperationFAH: sampleWithRequiredData.dateRecuperationFAH?.format(DATE_FORMAT),
  dateDebutHemlibra: sampleWithRequiredData.dateDebutHemlibra?.format(DATE_FORMAT),
  dateDecouverteVHC: sampleWithRequiredData.dateDecouverteVHC?.format(DATE_FORMAT),
};

describe('FicheHemophilie Service', () => {
  let service: FicheHemophilieService;
  let httpMock: HttpTestingController;
  let expectedResult: IFicheHemophilie | IFicheHemophilie[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(FicheHemophilieService);
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

    it('should create a FicheHemophilie', () => {
      const ficheHemophilie = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(ficheHemophilie).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FicheHemophilie', () => {
      const ficheHemophilie = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(ficheHemophilie).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FicheHemophilie', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FicheHemophilie', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FicheHemophilie', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFicheHemophilieToCollectionIfMissing', () => {
      it('should add a FicheHemophilie to an empty array', () => {
        const ficheHemophilie: IFicheHemophilie = sampleWithRequiredData;
        expectedResult = service.addFicheHemophilieToCollectionIfMissing([], ficheHemophilie);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheHemophilie);
      });

      it('should not add a FicheHemophilie to an array that contains it', () => {
        const ficheHemophilie: IFicheHemophilie = sampleWithRequiredData;
        const ficheHemophilieCollection: IFicheHemophilie[] = [
          {
            ...ficheHemophilie,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFicheHemophilieToCollectionIfMissing(ficheHemophilieCollection, ficheHemophilie);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FicheHemophilie to an array that doesn't contain it", () => {
        const ficheHemophilie: IFicheHemophilie = sampleWithRequiredData;
        const ficheHemophilieCollection: IFicheHemophilie[] = [sampleWithPartialData];
        expectedResult = service.addFicheHemophilieToCollectionIfMissing(ficheHemophilieCollection, ficheHemophilie);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheHemophilie);
      });

      it('should add only unique FicheHemophilie to an array', () => {
        const ficheHemophilieArray: IFicheHemophilie[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const ficheHemophilieCollection: IFicheHemophilie[] = [sampleWithRequiredData];
        expectedResult = service.addFicheHemophilieToCollectionIfMissing(ficheHemophilieCollection, ...ficheHemophilieArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ficheHemophilie: IFicheHemophilie = sampleWithRequiredData;
        const ficheHemophilie2: IFicheHemophilie = sampleWithPartialData;
        expectedResult = service.addFicheHemophilieToCollectionIfMissing([], ficheHemophilie, ficheHemophilie2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheHemophilie);
        expect(expectedResult).toContain(ficheHemophilie2);
      });

      it('should accept null and undefined values', () => {
        const ficheHemophilie: IFicheHemophilie = sampleWithRequiredData;
        expectedResult = service.addFicheHemophilieToCollectionIfMissing([], null, ficheHemophilie, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheHemophilie);
      });

      it('should return initial array if no FicheHemophilie is added', () => {
        const ficheHemophilieCollection: IFicheHemophilie[] = [sampleWithRequiredData];
        expectedResult = service.addFicheHemophilieToCollectionIfMissing(ficheHemophilieCollection, undefined, null);
        expect(expectedResult).toEqual(ficheHemophilieCollection);
      });
    });

    describe('compareFicheHemophilie', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFicheHemophilie(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFicheHemophilie(entity1, entity2);
        const compareResult2 = service.compareFicheHemophilie(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFicheHemophilie(entity1, entity2);
        const compareResult2 = service.compareFicheHemophilie(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFicheHemophilie(entity1, entity2);
        const compareResult2 = service.compareFicheHemophilie(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
