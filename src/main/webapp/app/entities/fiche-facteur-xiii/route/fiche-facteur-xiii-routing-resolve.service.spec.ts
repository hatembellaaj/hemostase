import { TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { ActivatedRoute, ActivatedRouteSnapshot, Router, convertToParamMap } from '@angular/router';
import { of } from 'rxjs';

import { IFicheFacteurXIII } from '../fiche-facteur-xiii.model';
import { FicheFacteurXIIIService } from '../service/fiche-facteur-xiii.service';

import ficheFacteurXIIIResolve from './fiche-facteur-xiii-routing-resolve.service';

describe('FicheFacteurXIII routing resolve service', () => {
  let mockRouter: Router;
  let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
  let service: FicheFacteurXIIIService;
  let resultFicheFacteurXIII: IFicheFacteurXIII | null | undefined;

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
    service = TestBed.inject(FicheFacteurXIIIService);
    resultFicheFacteurXIII = undefined;
  });

  describe('resolve', () => {
    it('should return IFicheFacteurXIII returned by find', () => {
      // GIVEN
      service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        ficheFacteurXIIIResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFicheFacteurXIII = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultFicheFacteurXIII).toEqual({ id: 123 });
    });

    it('should return null if id is not provided', () => {
      // GIVEN
      service.find = jest.fn();
      mockActivatedRouteSnapshot.params = {};

      // WHEN
      TestBed.runInInjectionContext(() => {
        ficheFacteurXIIIResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFicheFacteurXIII = result;
          },
        });
      });

      // THEN
      expect(service.find).not.toHaveBeenCalled();
      expect(resultFicheFacteurXIII).toEqual(null);
    });

    it('should route to 404 page if data not found in server', () => {
      // GIVEN
      jest.spyOn(service, 'find').mockReturnValue(of(new HttpResponse<IFicheFacteurXIII>({ body: null })));
      mockActivatedRouteSnapshot.params = { id: 123 };

      // WHEN
      TestBed.runInInjectionContext(() => {
        ficheFacteurXIIIResolve(mockActivatedRouteSnapshot).subscribe({
          next(result) {
            resultFicheFacteurXIII = result;
          },
        });
      });

      // THEN
      expect(service.find).toHaveBeenCalledWith(123);
      expect(resultFicheFacteurXIII).toEqual(undefined);
      expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
    });
  });
});
