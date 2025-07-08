import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IFicheVonWillebrand } from '../fiche-von-willebrand.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../fiche-von-willebrand.test-samples';

import { FicheVonWillebrandService, RestFicheVonWillebrand } from './fiche-von-willebrand.service';

const requireRestSample: RestFicheVonWillebrand = {
  ...sampleWithRequiredData,
  dateNaissance: sampleWithRequiredData.dateNaissance?.format(DATE_FORMAT),
  dateEnregistrementRegistre: sampleWithRequiredData.dateEnregistrementRegistre?.format(DATE_FORMAT),
  datePremiereConsultation: sampleWithRequiredData.datePremiereConsultation?.format(DATE_FORMAT),
  dateTestConfirmation: sampleWithRequiredData.dateTestConfirmation?.format(DATE_FORMAT),
  dateDeces: sampleWithRequiredData.dateDeces?.format(DATE_FORMAT),
};

describe('FicheVonWillebrand Service', () => {
  let service: FicheVonWillebrandService;
  let httpMock: HttpTestingController;
  let expectedResult: IFicheVonWillebrand | IFicheVonWillebrand[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(FicheVonWillebrandService);
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

    it('should create a FicheVonWillebrand', () => {
      const ficheVonWillebrand = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(ficheVonWillebrand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FicheVonWillebrand', () => {
      const ficheVonWillebrand = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(ficheVonWillebrand).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FicheVonWillebrand', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FicheVonWillebrand', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FicheVonWillebrand', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFicheVonWillebrandToCollectionIfMissing', () => {
      it('should add a FicheVonWillebrand to an empty array', () => {
        const ficheVonWillebrand: IFicheVonWillebrand = sampleWithRequiredData;
        expectedResult = service.addFicheVonWillebrandToCollectionIfMissing([], ficheVonWillebrand);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheVonWillebrand);
      });

      it('should not add a FicheVonWillebrand to an array that contains it', () => {
        const ficheVonWillebrand: IFicheVonWillebrand = sampleWithRequiredData;
        const ficheVonWillebrandCollection: IFicheVonWillebrand[] = [
          {
            ...ficheVonWillebrand,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFicheVonWillebrandToCollectionIfMissing(ficheVonWillebrandCollection, ficheVonWillebrand);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FicheVonWillebrand to an array that doesn't contain it", () => {
        const ficheVonWillebrand: IFicheVonWillebrand = sampleWithRequiredData;
        const ficheVonWillebrandCollection: IFicheVonWillebrand[] = [sampleWithPartialData];
        expectedResult = service.addFicheVonWillebrandToCollectionIfMissing(ficheVonWillebrandCollection, ficheVonWillebrand);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheVonWillebrand);
      });

      it('should add only unique FicheVonWillebrand to an array', () => {
        const ficheVonWillebrandArray: IFicheVonWillebrand[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const ficheVonWillebrandCollection: IFicheVonWillebrand[] = [sampleWithRequiredData];
        expectedResult = service.addFicheVonWillebrandToCollectionIfMissing(ficheVonWillebrandCollection, ...ficheVonWillebrandArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ficheVonWillebrand: IFicheVonWillebrand = sampleWithRequiredData;
        const ficheVonWillebrand2: IFicheVonWillebrand = sampleWithPartialData;
        expectedResult = service.addFicheVonWillebrandToCollectionIfMissing([], ficheVonWillebrand, ficheVonWillebrand2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheVonWillebrand);
        expect(expectedResult).toContain(ficheVonWillebrand2);
      });

      it('should accept null and undefined values', () => {
        const ficheVonWillebrand: IFicheVonWillebrand = sampleWithRequiredData;
        expectedResult = service.addFicheVonWillebrandToCollectionIfMissing([], null, ficheVonWillebrand, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheVonWillebrand);
      });

      it('should return initial array if no FicheVonWillebrand is added', () => {
        const ficheVonWillebrandCollection: IFicheVonWillebrand[] = [sampleWithRequiredData];
        expectedResult = service.addFicheVonWillebrandToCollectionIfMissing(ficheVonWillebrandCollection, undefined, null);
        expect(expectedResult).toEqual(ficheVonWillebrandCollection);
      });
    });

    describe('compareFicheVonWillebrand', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFicheVonWillebrand(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFicheVonWillebrand(entity1, entity2);
        const compareResult2 = service.compareFicheVonWillebrand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFicheVonWillebrand(entity1, entity2);
        const compareResult2 = service.compareFicheVonWillebrand(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFicheVonWillebrand(entity1, entity2);
        const compareResult2 = service.compareFicheVonWillebrand(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
