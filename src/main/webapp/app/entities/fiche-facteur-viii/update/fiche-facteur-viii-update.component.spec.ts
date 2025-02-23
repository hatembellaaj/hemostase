import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { FicheFacteurVIIIService } from '../service/fiche-facteur-viii.service';
import { IFicheFacteurVIII } from '../fiche-facteur-viii.model';
import { FicheFacteurVIIIFormService } from './fiche-facteur-viii-form.service';

import { FicheFacteurVIIIUpdateComponent } from './fiche-facteur-viii-update.component';

describe('FicheFacteurVIII Management Update Component', () => {
  let comp: FicheFacteurVIIIUpdateComponent;
  let fixture: ComponentFixture<FicheFacteurVIIIUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheFacteurVIIIFormService: FicheFacteurVIIIFormService;
  let ficheFacteurVIIIService: FicheFacteurVIIIService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheFacteurVIIIUpdateComponent],
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
      .overrideTemplate(FicheFacteurVIIIUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheFacteurVIIIUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheFacteurVIIIFormService = TestBed.inject(FicheFacteurVIIIFormService);
    ficheFacteurVIIIService = TestBed.inject(FicheFacteurVIIIService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ficheFacteurVIII: IFicheFacteurVIII = { id: 456 };

      activatedRoute.data = of({ ficheFacteurVIII });
      comp.ngOnInit();

      expect(comp.ficheFacteurVIII).toEqual(ficheFacteurVIII);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurVIII>>();
      const ficheFacteurVIII = { id: 123 };
      jest.spyOn(ficheFacteurVIIIFormService, 'getFicheFacteurVIII').mockReturnValue(ficheFacteurVIII);
      jest.spyOn(ficheFacteurVIIIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurVIII });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurVIII }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurVIIIFormService.getFicheFacteurVIII).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheFacteurVIIIService.update).toHaveBeenCalledWith(expect.objectContaining(ficheFacteurVIII));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurVIII>>();
      const ficheFacteurVIII = { id: 123 };
      jest.spyOn(ficheFacteurVIIIFormService, 'getFicheFacteurVIII').mockReturnValue({ id: null });
      jest.spyOn(ficheFacteurVIIIService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurVIII: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurVIII }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurVIIIFormService.getFicheFacteurVIII).toHaveBeenCalled();
      expect(ficheFacteurVIIIService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurVIII>>();
      const ficheFacteurVIII = { id: 123 };
      jest.spyOn(ficheFacteurVIIIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurVIII });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheFacteurVIIIService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
