import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IFicheVonWillebrand } from '../fiche-von-willebrand.model';
import { FicheVonWillebrandService } from '../service/fiche-von-willebrand.service';

import ficheVonWillebrandResolve from './fiche-von-willebrand-routing-resolve.service';

describe('FicheVonWillebrand routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: FicheVonWillebrandService;
  let resultFicheVonWillebrand: IFicheVonWillebrand | null | undefined;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [
        provideHttpClient(),
        {
          provide: ActivatedRoute,
          useValue: {
            snapshot: {
              paramMap: convertToParamMap({}),
            },
          },
        },
      ],
    });
    mockRouter = TestBed.inject(Router);
    jest.spyOn(mockRouter, 'navigate').mockImplementation(() => Promise.resolve(true));
    mockActivatedRouteSnapshot = TestBed.inject(ActivatedRoute).snapshot;
    service = TestBed.inject(FicheVonWillebrandService);
    resultFicheVonWillebrand = undefined;
  });

  describe('resolve', () => {
    it('should return IFicheVonWillebrand returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        ficheVonWillebrandResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFicheVonWillebrand = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultFicheVonWillebrand).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        ficheVonWillebrandResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFicheVonWillebrand = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultFicheVonWillebrand).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IFicheVonWillebrand>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        ficheVonWillebrandResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFicheVonWillebrand = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultFicheVonWillebrand).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
