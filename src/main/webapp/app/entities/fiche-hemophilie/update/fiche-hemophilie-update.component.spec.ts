import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Subject, from, of } from 'rxjs';

import { IPatient } from 'app/entities/patient/patient.model';
import { PatientService } from 'app/entities/patient/service/patient.service';
import { FicheHemophilieService } from '../service/fiche-hemophilie.service';
import { IFicheHemophilie } from '../fiche-hemophilie.model';
import { FicheHemophilieFormService } from './fiche-hemophilie-form.service';

import { FicheHemophilieUpdateComponent } from './fiche-hemophilie-update.component';

describe('FicheHemophilie Management Update Component', () => {
  let comp: FicheHemophilieUpdateComponent;
  let fixture: ComponentFixture<FicheHemophilieUpdateComponent>;
  let activatedRoute: ActivatedRoute;
  let ficheHemophilieFormService: FicheHemophilieFormService;
  let ficheHemophilieService: FicheHemophilieService;
  let patientService: PatientService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheHemophilieUpdateComponent],
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
      .overrideTemplate(FicheHemophilieUpdateComponent, '')
      .compileComponents();

    fixture = TestBed.createComponent(FicheHemophilieUpdateComponent);
    activatedRoute = TestBed.inject(ActivatedRoute);
    ficheHemophilieFormService = TestBed.inject(FicheHemophilieFormService);
    ficheHemophilieService = TestBed.inject(FicheHemophilieService);
    patientService = TestBed.inject(PatientService);

    comp = fixture.componentInstance;
  });

  describe('ngOnInit', () => {
    it('Should call Patient query and add missing value', () => {
      const ficheHemophilie: IFicheHemophilie = { id: 456 };
      const patient: IPatient = { id: 16113 };
      ficheHemophilie.patient = patient;

      const patientCollection: IPatient[] = [{ id: 28395 }];
      jest.spyOn(patientService, 'query').mockReturnValue(of(new HttpResponse({ body: patientCollection })));
      const additionalPatients = [patient];
      const expectedCollection: IPatient[] = [...additionalPatients, ...patientCollection];
      jest.spyOn(patientService, 'addPatientToCollectionIfMissing').mockReturnValue(expectedCollection);

      activatedRoute.data = of({ ficheHemophilie });
      comp.ngOnInit();

      expect(patientService.query).toHaveBeenCalled();
      expect(patientService.addPatientToCollectionIfMissing).toHaveBeenCalledWith(
        patientCollection,
        ...additionalPatients.map(expect.objectContaining),
      );
      expect(comp.patientsSharedCollection).toEqual(expectedCollection);
    });

    it('Should update editForm', () => {
      const ficheHemophilie: IFicheHemophilie = { id: 456 };
      const patient: IPatient = { id: 29550 };
      ficheHemophilie.patient = patient;

      activatedRoute.data = of({ ficheHemophilie });
      comp.ngOnInit();

      expect(comp.patientsSharedCollection).toContain(patient);
      expect(comp.ficheHemophilie).toEqual(ficheHemophilie);
    });
  });

  describe('save', () => {
    it('Should call update service on save for existing entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheHemophilie>>();
      const ficheHemophilie = { id: 123 };
      jest.spyOn(ficheHemophilieFormService, 'getFicheHemophilie').mockReturnValue(ficheHemophilie);
      jest.spyOn(ficheHemophilieService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheHemophilie });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheHemophilie }));
      saveSubject.complete();

      // THEN
      expect(ficheHemophilieFormService.getFicheHemophilie).toHaveBeenCalled();
      expect(comp.previousState).toHaveBeenCalled();
      expect(ficheHemophilieService.update).toHaveBeenCalledWith(expect.objectContaining(ficheHemophilie));
      expect(comp.isSaving).toEqual(false);
    });

    it('Should call create service on save for new entity', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheHemophilie>>();
      const ficheHemophilie = { id: 123 };
      jest.spyOn(ficheHemophilieFormService, 'getFicheHemophilie').mockReturnValue({ id: null });
      jest.spyOn(ficheHemophilieService, 'create').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheHemophilie: null });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.next(new HttpResponse({ body: ficheHemophilie }));
      saveSubject.complete();

      // THEN
      expect(ficheHemophilieFormService.getFicheHemophilie).toHaveBeenCalled();
      expect(ficheHemophilieService.create).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).toHaveBeenCalled();
    });

    it('Should set isSaving to false on error', () => {
      // GIVEN
      const saveSubject = new Subject<HttpResponse<IFicheHemophilie>>();
      const ficheHemophilie = { id: 123 };
      jest.spyOn(ficheHemophilieService, 'update').mockReturnValue(saveSubject);
      jest.spyOn(comp, 'previousState');
      activatedRoute.data = of({ ficheHemophilie });
      comp.ngOnInit();

      // WHEN
      comp.save();
      expect(comp.isSaving).toEqual(true);
      saveSubject.error('This is an error!');

      // THEN
      expect(ficheHemophilieService.update).toHaveBeenCalled();
      expect(comp.isSaving).toEqual(false);
      expect(comp.previousState).not.toHaveBeenCalled();
    });
  });

  describe('Compare relationships', () => {
    describe('comparePatient', () => {
      it('Should forward to patientService', () => {
        const entity = { id: 123 };
        const entity2 = { id: 456 };
        jest.spyOn(patientService, 'comparePatient');
        comp.comparePatient(entity, entity2);
        expect(patientService.comparePatient).toHaveBeenCalledWith(entity, entity2);
      });
    });
  });
});
