import { TestBed } from '@angular/core/testing';
import { HttpTestingController, provideHttpClientTesting } from '@angular/common/http/testing';
import { provideHttpClient } from '@angular/common/http';

import { DATE_FORMAT } from 'app/config/input.constants';
import { IFicheThrombopathie } from '../fiche-thrombopathie.model';
import { sampleWithFullData, sampleWithNewData, sampleWithPartialData, sampleWithRequiredData } from '../fiche-thrombopathie.test-samples';

import { FicheThrombopathieService, RestFicheThrombopathie } from './fiche-thrombopathie.service';

const requireRestSample: RestFicheThrombopathie = {
  ...sampleWithRequiredData,
  dateNaissance: sampleWithRequiredData.dateNaissance?.format(DATE_FORMAT),
  dateEnregistrementRegistre: sampleWithRequiredData.dateEnregistrementRegistre?.format(DATE_FORMAT),
};

describe('FicheThrombopathie Service', () => {
  let service: FicheThrombopathieService;
  let httpMock: HttpTestingController;
  let expectedResult: IFicheThrombopathie | IFicheThrombopathie[] | boolean | null;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [provideHttpClient(), provideHttpClientTesting()],
    });
    expectedResult = null;
    service = TestBed.inject(FicheThrombopathieService);
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

    it('should create a FicheThrombopathie', () => {
      const ficheThrombopathie = { ...sampleWithNewData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.create(ficheThrombopathie).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'POST' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should update a FicheThrombopathie', () => {
      const ficheThrombopathie = { ...sampleWithRequiredData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.update(ficheThrombopathie).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PUT' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should partial update a FicheThrombopathie', () => {
      const patchObject = { ...sampleWithPartialData };
      const returnedFromService = { ...requireRestSample };
      const expected = { ...sampleWithRequiredData };

      service.partialUpdate(patchObject).subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'PATCH' });
      req.flush(returnedFromService);
      expect(expectedResult).toMatchObject(expected);
    });

    it('should return a list of FicheThrombopathie', () => {
      const returnedFromService = { ...requireRestSample };

      const expected = { ...sampleWithRequiredData };

      service.query().subscribe(resp => (expectedResult = resp.body));

      const req = httpMock.expectOne({ method: 'GET' });
      req.flush([returnedFromService]);
      httpMock.verify();
      expect(expectedResult).toMatchObject([expected]);
    });

    it('should delete a FicheThrombopathie', () => {
      const expected = true;

      service.delete(123).subscribe(resp => (expectedResult = resp.ok));

      const req = httpMock.expectOne({ method: 'DELETE' });
      req.flush({ status: 200 });
      expect(expectedResult).toBe(expected);
    });

    describe('addFicheThrombopathieToCollectionIfMissing', () => {
      it('should add a FicheThrombopathie to an empty array', () => {
        const ficheThrombopathie: IFicheThrombopathie = sampleWithRequiredData;
        expectedResult = service.addFicheThrombopathieToCollectionIfMissing([], ficheThrombopathie);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheThrombopathie);
      });

      it('should not add a FicheThrombopathie to an array that contains it', () => {
        const ficheThrombopathie: IFicheThrombopathie = sampleWithRequiredData;
        const ficheThrombopathieCollection: IFicheThrombopathie[] = [
          {
            ...ficheThrombopathie,
          },
          sampleWithPartialData,
        ];
        expectedResult = service.addFicheThrombopathieToCollectionIfMissing(ficheThrombopathieCollection, ficheThrombopathie);
        expect(expectedResult).toHaveLength(2);
      });

      it("should add a FicheThrombopathie to an array that doesn't contain it", () => {
        const ficheThrombopathie: IFicheThrombopathie = sampleWithRequiredData;
        const ficheThrombopathieCollection: IFicheThrombopathie[] = [sampleWithPartialData];
        expectedResult = service.addFicheThrombopathieToCollectionIfMissing(ficheThrombopathieCollection, ficheThrombopathie);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheThrombopathie);
      });

      it('should add only unique FicheThrombopathie to an array', () => {
        const ficheThrombopathieArray: IFicheThrombopathie[] = [sampleWithRequiredData, sampleWithPartialData, sampleWithFullData];
        const ficheThrombopathieCollection: IFicheThrombopathie[] = [sampleWithRequiredData];
        expectedResult = service.addFicheThrombopathieToCollectionIfMissing(ficheThrombopathieCollection, ...ficheThrombopathieArray);
        expect(expectedResult).toHaveLength(3);
      });

      it('should accept varargs', () => {
        const ficheThrombopathie: IFicheThrombopathie = sampleWithRequiredData;
        const ficheThrombopathie2: IFicheThrombopathie = sampleWithPartialData;
        expectedResult = service.addFicheThrombopathieToCollectionIfMissing([], ficheThrombopathie, ficheThrombopathie2);
        expect(expectedResult).toHaveLength(2);
        expect(expectedResult).toContain(ficheThrombopathie);
        expect(expectedResult).toContain(ficheThrombopathie2);
      });

      it('should accept null and undefined values', () => {
        const ficheThrombopathie: IFicheThrombopathie = sampleWithRequiredData;
        expectedResult = service.addFicheThrombopathieToCollectionIfMissing([], null, ficheThrombopathie, undefined);
        expect(expectedResult).toHaveLength(1);
        expect(expectedResult).toContain(ficheThrombopathie);
      });

      it('should return initial array if no FicheThrombopathie is added', () => {
        const ficheThrombopathieCollection: IFicheThrombopathie[] = [sampleWithRequiredData];
        expectedResult = service.addFicheThrombopathieToCollectionIfMissing(ficheThrombopathieCollection, undefined, null);
        expect(expectedResult).toEqual(ficheThrombopathieCollection);
      });
    });

    describe('compareFicheThrombopathie', () => {
      it('Should return true if both entities are null', () => {
        const entity1 = null;
        const entity2 = null;

        const compareResult = service.compareFicheThrombopathie(entity1, entity2);

        expect(compareResult).toEqual(true);
      });

      it('Should return false if one entity is null', () => {
        const entity1 = { id: 123 };
        const entity2 = null;

        const compareResult1 = service.compareFicheThrombopathie(entity1, entity2);
        const compareResult2 = service.compareFicheThrombopathie(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey differs', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 456 };

        const compareResult1 = service.compareFicheThrombopathie(entity1, entity2);
        const compareResult2 = service.compareFicheThrombopathie(entity2, entity1);

        expect(compareResult1).toEqual(false);
        expect(compareResult2).toEqual(false);
      });

      it('Should return false if primaryKey matches', () => {
        const entity1 = { id: 123 };
        const entity2 = { id: 123 };

        const compareResult1 = service.compareFicheThrombopathie(entity1, entity2);
        const compareResult2 = service.compareFicheThrombopathie(entity2, entity1);

        expect(compareResult1).toEqual(true);
        expect(compareResult2).toEqual(true);
      });
    });
  });

  afterEach(() => {
    httpMock.verify();
  });
});
