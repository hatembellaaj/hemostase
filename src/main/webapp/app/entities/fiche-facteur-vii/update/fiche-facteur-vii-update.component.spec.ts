import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { FicheFacteurVIIService } from '../service/fiche-facteur-vii.service';
import { IFicheFacteurVII } from '../fiche-facteur-vii.model';
import { FicheFacteurVIIFormService } from './fiche-facteur-vii-form.service';

import { FicheFacteurVIIUpdateComponent } from './fiche-facteur-vii-update.component';

describe('FicheFacteurVII Management Update Component', () => {
  let comp: FicheFacteurVIIUpdateComponent;
  let fixture: ComponentFixture<FicheFacteurVIIUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheFacteurVIIFormService: FicheFacteurVIIFormService;
  let ficheFacteurVIIService: FicheFacteurVIIService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheFacteurVIIUpdateComponent],
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
      .overrideTemplate(FicheFacteurVIIUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheFacteurVIIUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheFacteurVIIFormService = TestBed.inject(FicheFacteurVIIFormService);
    ficheFacteurVIIService = TestBed.inject(FicheFacteurVIIService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ficheFacteurVII: IFicheFacteurVII = { id: 456 };

      activatedRoute.data = of({ ficheFacteurVII });
      comp.ngOnInit();

      expect(comp.ficheFacteurVII).toEqual(ficheFacteurVII);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurVII>>();
      const ficheFacteurVII = { id: 123 };
      jest.spyOn(ficheFacteurVIIFormService, 'getFicheFacteurVII').mockReturnValue(ficheFacteurVII);
      jest.spyOn(ficheFacteurVIIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurVII });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurVII }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurVIIFormService.getFicheFacteurVII).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheFacteurVIIService.update).toHaveBeenCalledWith(expect.objectContaining(ficheFacteurVII));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurVII>>();
      const ficheFacteurVII = { id: 123 };
      jest.spyOn(ficheFacteurVIIFormService, 'getFicheFacteurVII').mockReturnValue({ id: null });
      jest.spyOn(ficheFacteurVIIService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurVII: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheFacteurVII }));
      saveSubject.complete();

      // THEN
      expect(ficheFacteurVIIFormService.getFicheFacteurVII).toHaveBeenCalled();
      expect(ficheFacteurVIIService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheFacteurVII>>();
      const ficheFacteurVII = { id: 123 };
      jest.spyOn(ficheFacteurVIIService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheFacteurVII });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheFacteurVIIService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
