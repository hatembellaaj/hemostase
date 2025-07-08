import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { FicheFacteurXIService } from '../service/fiche-facteur-xi.service';
import { IFicheFacteurXI } from '../fiche-facteur-xi.model';
import { FicheFacteurXIFormService } from './fiche-facteur-xi-form.service';

import { FicheFacteurXIUpdateComponent } from './fiche-facteur-xi-update.component';

describe('FicheFacteurXI Management Update Component', () => {
  let comp: FicheFacteurXIUpdateComponent;
  let fixture: ComponentFixture<FicheFacteurXIUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheFacteurXIFormService: FicheFacteurXIFormService;
  let ficheFacteurXIService: FicheFacteurXIService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheFacteurXIUpdateComponent],
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
      .overrideTemplate(FicheFacteurXIUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheFacteurXIUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheFacteurXIFormService = TestBed.inject(FicheFacteurXIFormService);
    ficheFacteurXIService = TestBed.inject(FicheFacteurXIService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ficheFacteurXI: IFicheFacteurXI = { id: 456 };

      activatedRoute.data = of({ ficheFacteurXI });
      comp.ngOnInit();

      expect(comp.ficheFacteurXI).toEqual(ficheFacteurXI);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurXI>>();
      const ficheFacteurXI = { id: 123 };
      jest.spyOn(ficheFacteurXIFormService, 'getFicheFacteurXI').mockReturnValue(ficheFacteurXI);
      jest.spyOn(ficheFacteurXIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurXI });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurXI }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurXIFormService.getFicheFacteurXI).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheFacteurXIService.update).toHaveBeenCalledWith(expect.objectContaining(ficheFacteurXI));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurXI>>();
      const ficheFacteurXI = { id: 123 };
      jest.spyOn(ficheFacteurXIFormService, 'getFicheFacteurXI').mockReturnValue({ id: null });
      jest.spyOn(ficheFacteurXIService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurXI: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurXI }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurXIFormService.getFicheFacteurXI).toHaveBeenCalled();
      expect(ficheFacteurXIService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurXI>>();
      const ficheFacteurXI = { id: 123 };
      jest.spyOn(ficheFacteurXIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurXI });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheFacteurXIService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
