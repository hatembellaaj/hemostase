import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { FicheFacteurXIIIService } from '../service/fiche-facteur-xiii.service';
import { IFicheFacteurXIII } from '../fiche-facteur-xiii.model';
import { FicheFacteurXIIIFormService } from './fiche-facteur-xiii-form.service';

import { FicheFacteurXIIIUpdateComponent } from './fiche-facteur-xiii-update.component';

describe('FicheFacteurXIII Management Update Component', () => {
  let comp: FicheFacteurXIIIUpdateComponent;
  let fixture: ComponentFixture<FicheFacteurXIIIUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheFacteurXIIIFormService: FicheFacteurXIIIFormService;
  let ficheFacteurXIIIService: FicheFacteurXIIIService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheFacteurXIIIUpdateComponent],
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
      .overrideTemplate(FicheFacteurXIIIUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheFacteurXIIIUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheFacteurXIIIFormService = TestBed.inject(FicheFacteurXIIIFormService);
    ficheFacteurXIIIService = TestBed.inject(FicheFacteurXIIIService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ficheFacteurXIII: IFicheFacteurXIII = { id: 456 };

      activatedRoute.data = of({ ficheFacteurXIII });
      comp.ngOnInit();

      expect(comp.ficheFacteurXIII).toEqual(ficheFacteurXIII);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurXIII>>();
      const ficheFacteurXIII = { id: 123 };
      jest.spyOn(ficheFacteurXIIIFormService, 'getFicheFacteurXIII').mockReturnValue(ficheFacteurXIII);
      jest.spyOn(ficheFacteurXIIIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurXIII });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurXIII }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurXIIIFormService.getFicheFacteurXIII).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheFacteurXIIIService.update).toHaveBeenCalledWith(expect.objectContaining(ficheFacteurXIII));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurXIII>>();
      const ficheFacteurXIII = { id: 123 };
      jest.spyOn(ficheFacteurXIIIFormService, 'getFicheFacteurXIII').mockReturnValue({ id: null });
      jest.spyOn(ficheFacteurXIIIService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurXIII: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurXIII }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurXIIIFormService.getFicheFacteurXIII).toHaveBeenCalled();
      expect(ficheFacteurXIIIService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurXIII>>();
      const ficheFacteurXIII = { id: 123 };
      jest.spyOn(ficheFacteurXIIIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurXIII });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheFacteurXIIIService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
