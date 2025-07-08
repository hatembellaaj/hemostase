import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { FicheFacteurXVIIIService } from '../service/fiche-facteur-xviii.service';
import { IFicheFacteurXVIII } from '../fiche-facteur-xviii.model';
import { FicheFacteurXVIIIFormService } from './fiche-facteur-xviii-form.service';

import { FicheFacteurXVIIIUpdateComponent } from './fiche-facteur-xviii-update.component';

describe('FicheFacteurXVIII Management Update Component', () => {
  let comp: FicheFacteurXVIIIUpdateComponent;
  let fixture: ComponentFixture<FicheFacteurXVIIIUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheFacteurXVIIIFormService: FicheFacteurXVIIIFormService;
  let ficheFacteurXVIIIService: FicheFacteurXVIIIService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheFacteurXVIIIUpdateComponent],
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
      .overrideTemplate(FicheFacteurXVIIIUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheFacteurXVIIIUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheFacteurXVIIIFormService = TestBed.inject(FicheFacteurXVIIIFormService);
    ficheFacteurXVIIIService = TestBed.inject(FicheFacteurXVIIIService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ficheFacteurXVIII: IFicheFacteurXVIII = { id: 456 };

      activatedRoute.data = of({ ficheFacteurXVIII });
      comp.ngOnInit();

      expect(comp.ficheFacteurXVIII).toEqual(ficheFacteurXVIII);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurXVIII>>();
      const ficheFacteurXVIII = { id: 123 };
      jest.spyOn(ficheFacteurXVIIIFormService, 'getFicheFacteurXVIII').mockReturnValue(ficheFacteurXVIII);
      jest.spyOn(ficheFacteurXVIIIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurXVIII });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurXVIII }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurXVIIIFormService.getFicheFacteurXVIII).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheFacteurXVIIIService.update).toHaveBeenCalledWith(expect.objectContaining(ficheFacteurXVIII));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurXVIII>>();
      const ficheFacteurXVIII = { id: 123 };
      jest.spyOn(ficheFacteurXVIIIFormService, 'getFicheFacteurXVIII').mockReturnValue({ id: null });
      jest.spyOn(ficheFacteurXVIIIService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurXVIII: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurXVIII }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurXVIIIFormService.getFicheFacteurXVIII).toHaveBeenCalled();
      expect(ficheFacteurXVIIIService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurXVIII>>();
      const ficheFacteurXVIII = { id: 123 };
      jest.spyOn(ficheFacteurXVIIIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurXVIII });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheFacteurXVIIIService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
