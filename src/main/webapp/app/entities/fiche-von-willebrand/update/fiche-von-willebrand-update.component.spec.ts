import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { FicheVonWillebrandService } from '../service/fiche-von-willebrand.service';
import { IFicheVonWillebrand } from '../fiche-von-willebrand.model';
import { FicheVonWillebrandFormService } from './fiche-von-willebrand-form.service';

import { FicheVonWillebrandUpdateComponent } from './fiche-von-willebrand-update.component';

describe('FicheVonWillebrand Management Update Component', () => {
  let comp: FicheVonWillebrandUpdateComponent;
  let fixture: ComponentFixture<FicheVonWillebrandUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheVonWillebrandFormService: FicheVonWillebrandFormService;
  let ficheVonWillebrandService: FicheVonWillebrandService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheVonWillebrandUpdateComponent],
      providers: [
        provideHttpClient(),
        FormBuilder,
        {
          provide: ActivatedRoute,
          useValue: {
            params: from([{}]),
          },
        },
      ],
    })
      .overrideTemplate(FicheVonWillebrandUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheVonWillebrandUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheVonWillebrandFormService = TestBed.inject(FicheVonWillebrandFormService);
    ficheVonWillebrandService = TestBed.inject(FicheVonWillebrandService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ficheVonWillebrand: IFicheVonWillebrand = { id: 456 };

      activatedRoute.data = of({ ficheVonWillebrand });
      comp.ngOnInit();

      expect(comp.ficheVonWillebrand).toEqual(ficheVonWillebrand);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheVonWillebrand>>();
      const ficheVonWillebrand = { id: 123 };
      jest.spyOn(ficheVonWillebrandFormService, 'getFicheVonWillebrand').mockReturnValue(ficheVonWillebrand);
      jest.spyOn(ficheVonWillebrandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheVonWillebrand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheVonWillebrand }));
      saveSubject.complete();

      // THEN
      expect(ficheVonWillebrandFormService.getFicheVonWillebrand).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheVonWillebrandService.update).toHaveBeenCalledWith(expect.objectContaining(ficheVonWillebrand));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheVonWillebrand>>();
      const ficheVonWillebrand = { id: 123 };
      jest.spyOn(ficheVonWillebrandFormService, 'getFicheVonWillebrand').mockReturnValue({ id: null });
      jest.spyOn(ficheVonWillebrandService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheVonWillebrand: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheVonWillebrand }));
      saveSubject.complete();

      // THEN
      expect(ficheVonWillebrandFormService.getFicheVonWillebrand).toHaveBeenCalled();
      expect(ficheVonWillebrandService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheVonWillebrand>>();
      const ficheVonWillebrand = { id: 123 };
      jest.spyOn(ficheVonWillebrandService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheVonWillebrand });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheVonWillebrandService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
