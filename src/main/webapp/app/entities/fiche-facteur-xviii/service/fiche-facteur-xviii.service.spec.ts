import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IFicheFacteurXVIII } from '../fiche-facteur-xviii.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../fiche-facteur-xviii.test-samples';

import { FicheFacteurXVIIIService, RestFicheFacteurXVIII } from './fiche-facteur-xviii.service';

const requireRestSample: RestFicheFacteurXVIII = {
  ...sampleWithRequiredData,
  dateNaissance: sampleWithRequiredData.dateNaissance?.format(DATE_FORMAT),
  dateEnregistrementRegistre: sampleWithRequiredData.dateEnregistrementRegistre?.format(DATE_FORMAT),
  datePremiereConsultation: sampleWithRequiredData.datePremiereConsultation?.format(DATE_FORMAT),
  dateDeces: sampleWithRequiredData.dateDeces?.format(DATE_FORMAT),
};

describe('FicheFacteurXVIII Service', () => {
  let service: FicheFacteurXVIIIService;
  let httpMock: HttpTestingController;
  let expectedResult: IFicheFacteurXVIII | IFicheFacteurXVIII[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(FicheFacteurXVIIIService);
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

    it('should create a FicheFacteurXVIII', () => {
      const ficheFacteurXVIII = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(ficheFacteurXVIII).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FicheFacteurXVIII', () => {
      const ficheFacteurXVIII = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(ficheFacteurXVIII).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FicheFacteurXVIII', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FicheFacteurXVIII', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FicheFacteurXVIII', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFicheFacteurXVIIIToCollectionIfMissing', () => {
      it('should add a FicheFacteurXVIII to an empty array', () => {
        const ficheFacteurXVIII: IFicheFacteurXVIII = sampleWithRequiredData;
        expectedResult = service.addFicheFacteurXVIIIToCollectionIfMissing([], ficheFacteurXVIII);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheFacteurXVIII);
      });

      it('should not add a FicheFacteurXVIII to an array that contains it', () => {
        const ficheFacteurXVIII: IFicheFacteurXVIII = sampleWithRequiredData;
        const ficheFacteurXVIIICollection: IFicheFacteurXVIII[] = [
          {
            ...ficheFacteurXVIII,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFicheFacteurXVIIIToCollectionIfMissing(ficheFacteurXVIIICollection, ficheFacteurXVIII);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FicheFacteurXVIII to an array that doesn't contain it", () => {
        const ficheFacteurXVIII: IFicheFacteurXVIII = sampleWithRequiredData;
        const ficheFacteurXVIIICollection: IFicheFacteurXVIII[] = [sampleWithPartialData];
        expectedResult = service.addFicheFacteurXVIIIToCollectionIfMissing(ficheFacteurXVIIICollection, ficheFacteurXVIII);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheFacteurXVIII);
      });

      it('should add only unique FicheFacteurXVIII to an array', () => {
        const ficheFacteurXVIIIArray: IFicheFacteurXVIII[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const ficheFacteurXVIIICollection: IFicheFacteurXVIII[] = [sampleWithRequiredData];
        expectedResult = service.addFicheFacteurXVIIIToCollectionIfMissing(ficheFacteurXVIIICollection, ...ficheFacteurXVIIIArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ficheFacteurXVIII: IFicheFacteurXVIII = sampleWithRequiredData;
        const ficheFacteurXVIII2: IFicheFacteurXVIII = sampleWithPartialData;
        expectedResult = service.addFicheFacteurXVIIIToCollectionIfMissing([], ficheFacteurXVIII, ficheFacteurXVIII2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheFacteurXVIII);
        expect(expectedResult).toContain(ficheFacteurXVIII2);
      });

      it('should accept null and undefined values', () => {
        const ficheFacteurXVIII: IFicheFacteurXVIII = sampleWithRequiredData;
        expectedResult = service.addFicheFacteurXVIIIToCollectionIfMissing([], null, ficheFacteurXVIII, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheFacteurXVIII);
      });

      it('should return initial array if no FicheFacteurXVIII is added', () => {
        const ficheFacteurXVIIICollection: IFicheFacteurXVIII[] = [sampleWithRequiredData];
        expectedResult = service.addFicheFacteurXVIIIToCollectionIfMissing(ficheFacteurXVIIICollection, undefined, null);
        expect(expectedResult).toEqual(ficheFacteurXVIIICollection);
      });
    });

    describe('compareFicheFacteurXVIII', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFicheFacteurXVIII(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFicheFacteurXVIII(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurXVIII(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFicheFacteurXVIII(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurXVIII(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFicheFacteurXVIII(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurXVIII(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
