import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { FicheThrombopathieService } from '../service/fiche-thrombopathie.service';
import { IFicheThrombopathie } from '../fiche-thrombopathie.model';
import { FicheThrombopathieFormService } from './fiche-thrombopathie-form.service';

import { FicheThrombopathieUpdateComponent } from './fiche-thrombopathie-update.component';

describe('FicheThrombopathie Management Update Component', () => {
  let comp: FicheThrombopathieUpdateComponent;
  let fixture: ComponentFixture<FicheThrombopathieUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheThrombopathieFormService: FicheThrombopathieFormService;
  let ficheThrombopathieService: FicheThrombopathieService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheThrombopathieUpdateComponent],
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
      .overrideTemplate(FicheThrombopathieUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheThrombopathieUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheThrombopathieFormService = TestBed.inject(FicheThrombopathieFormService);
    ficheThrombopathieService = TestBed.inject(FicheThrombopathieService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should update editForm', () => {
      const ficheThrombopathie: IFicheThrombopathie = { id: 456 };

      activatedRoute.data = of({ ficheThrombopathie });
      comp.ngOnInit();

      expect(comp.ficheThrombopathie).toEqual(ficheThrombopathie);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheThrombopathie>>();
      const ficheThrombopathie = { id: 123 };
      jest.spyOn(ficheThrombopathieFormService, 'getFicheThrombopathie').mockReturnValue(ficheThrombopathie);
      jest.spyOn(ficheThrombopathieService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheThrombopathie });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheThrombopathie }));
      saveSubject.complete();

      // THEN
      expect(ficheThrombopathieFormService.getFicheThrombopathie).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheThrombopathieService.update).toHaveBeenCalledWith(expect.objectContaining(ficheThrombopathie));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheThrombopathie>>();
      const ficheThrombopathie = { id: 123 };
      jest.spyOn(ficheThrombopathieFormService, 'getFicheThrombopathie').mockReturnValue({ id: null });
      jest.spyOn(ficheThrombopathieService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheThrombopathie: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheThrombopathie }));
      saveSubject.complete();

      // THEN
      expect(ficheThrombopathieFormService.getFicheThrombopathie).toHaveBeenCalled();
      expect(ficheThrombopathieService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheThrombopathie>>();
      const ficheThrombopathie = { id: 123 };
      jest.spyOn(ficheThrombopathieService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheThrombopathie });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheThrombopathieService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });
});
