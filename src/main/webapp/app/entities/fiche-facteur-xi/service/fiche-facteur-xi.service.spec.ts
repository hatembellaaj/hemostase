import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IFicheFacteurXI } from '../fiche-facteur-xi.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../fiche-facteur-xi.test-samples';

import { FicheFacteurXIService, RestFicheFacteurXI } from './fiche-facteur-xi.service';

const requireRestSample: RestFicheFacteurXI = {
  ...sampleWithRequiredData,
  dateNaissance: sampleWithRequiredData.dateNaissance?.format(DATE_FORMAT),
  dateEnregistrementRegistre: sampleWithRequiredData.dateEnregistrementRegistre?.format(DATE_FORMAT),
  datePremiereConsultation: sampleWithRequiredData.datePremiereConsultation?.format(DATE_FORMAT),
  dateDeces: sampleWithRequiredData.dateDeces?.format(DATE_FORMAT),
};

describe('FicheFacteurXI Service', () => {
  let service: FicheFacteurXIService;
  let httpMock: HttpTestingController;
  let expectedResult: IFicheFacteurXI | IFicheFacteurXI[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(FicheFacteurXIService);
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

    it('should create a FicheFacteurXI', () => {
      const ficheFacteurXI = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(ficheFacteurXI).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FicheFacteurXI', () => {
      const ficheFacteurXI = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(ficheFacteurXI).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FicheFacteurXI', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FicheFacteurXI', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FicheFacteurXI', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFicheFacteurXIToCollectionIfMissing', () => {
      it('should add a FicheFacteurXI to an empty array', () => {
        const ficheFacteurXI: IFicheFacteurXI = sampleWithRequiredData;
        expectedResult = service.addFicheFacteurXIToCollectionIfMissing([], ficheFacteurXI);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheFacteurXI);
      });

      it('should not add a FicheFacteurXI to an array that contains it', () => {
        const ficheFacteurXI: IFicheFacteurXI = sampleWithRequiredData;
        const ficheFacteurXICollection: IFicheFacteurXI[] = [
          {
            ...ficheFacteurXI,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFicheFacteurXIToCollectionIfMissing(ficheFacteurXICollection, ficheFacteurXI);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FicheFacteurXI to an array that doesn't contain it", () => {
        const ficheFacteurXI: IFicheFacteurXI = sampleWithRequiredData;
        const ficheFacteurXICollection: IFicheFacteurXI[] = [sampleWithPartialData];
        expectedResult = service.addFicheFacteurXIToCollectionIfMissing(ficheFacteurXICollection, ficheFacteurXI);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheFacteurXI);
      });

      it('should add only unique FicheFacteurXI to an array', () => {
        const ficheFacteurXIArray: IFicheFacteurXI[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const ficheFacteurXICollection: IFicheFacteurXI[] = [sampleWithRequiredData];
        expectedResult = service.addFicheFacteurXIToCollectionIfMissing(ficheFacteurXICollection, ...ficheFacteurXIArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ficheFacteurXI: IFicheFacteurXI = sampleWithRequiredData;
        const ficheFacteurXI2: IFicheFacteurXI = sampleWithPartialData;
        expectedResult = service.addFicheFacteurXIToCollectionIfMissing([], ficheFacteurXI, ficheFacteurXI2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheFacteurXI);
        expect(expectedResult).toContain(ficheFacteurXI2);
      });

      it('should accept null and undefined values', () => {
        const ficheFacteurXI: IFicheFacteurXI = sampleWithRequiredData;
        expectedResult = service.addFicheFacteurXIToCollectionIfMissing([], null, ficheFacteurXI, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheFacteurXI);
      });

      it('should return initial array if no FicheFacteurXI is added', () => {
        const ficheFacteurXICollection: IFicheFacteurXI[] = [sampleWithRequiredData];
        expectedResult = service.addFicheFacteurXIToCollectionIfMissing(ficheFacteurXICollection, undefined, null);
        expect(expectedResult).toEqual(ficheFacteurXICollection);
      });
    });

    describe('compareFicheFacteurXI', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFicheFacteurXI(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFicheFacteurXI(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurXI(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFicheFacteurXI(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurXI(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFicheFacteurXI(entity1, entity2);
        const compareResult2 = service.compareFicheFacteurXI(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
