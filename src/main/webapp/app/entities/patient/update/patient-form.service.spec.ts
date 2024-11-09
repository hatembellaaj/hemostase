import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../patient.test-samples';

import { PatientFormService } from './patient-form.service';

describe('Patient Form Service', () => {
  let service: PatientFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PatientFormService);
  });

  describe('Service methods', () => {
    describe('createPatientFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createPatientFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            prenom: expect.any(Object),
            nom: expect.any(Object),
            nomJeuneFille: expect.any(Object),
            profession: expect.any(Object),
            sexe: expect.any(Object),
            dateNaissance: expect.any(Object),
            ageActuel: expect.any(Object),
            origine: expect.any(Object),
            autreOrigine: expect.any(Object),
            adresse: expect.any(Object),
            telephone: expect.any(Object),
            etatCivil: expect.any(Object),
            couvertureSociale: expect.any(Object),
            prenomPere: expect.any(Object),
            professionPere: expect.any(Object),
            nomPrenomMere: expect.any(Object),
            professionMere: expect.any(Object),
            diagnostic: expect.any(Object),
            autreDiagnostic: expect.any(Object),
            anneeDiagnostic: expect.any(Object),
            consentementRegistre: expect.any(Object),
            dateEnregistrementRegistre: expect.any(Object),
          }),
        );
      });

      it('passing IPatient should create a new form with FormGroup', () => {
        const formGroup = service.createPatientFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            prenom: expect.any(Object),
            nom: expect.any(Object),
            nomJeuneFille: expect.any(Object),
            profession: expect.any(Object),
            sexe: expect.any(Object),
            dateNaissance: expect.any(Object),
            ageActuel: expect.any(Object),
            origine: expect.any(Object),
            autreOrigine: expect.any(Object),
            adresse: expect.any(Object),
            telephone: expect.any(Object),
            etatCivil: expect.any(Object),
            couvertureSociale: expect.any(Object),
            prenomPere: expect.any(Object),
            professionPere: expect.any(Object),
            nomPrenomMere: expect.any(Object),
            professionMere: expect.any(Object),
            diagnostic: expect.any(Object),
            autreDiagnostic: expect.any(Object),
            anneeDiagnostic: expect.any(Object),
            consentementRegistre: expect.any(Object),
            dateEnregistrementRegistre: expect.any(Object),
          }),
        );
      });
    });

    describe('getPatient', () => {
      it('should return NewPatient for default Patient initial value', () => {
        const formGroup = service.createPatientFormGroup(sampleWithNewData);

        const patient = service.getPatient(formGroup) as any;

        expect(patient).toMatchObject(sampleWithNewData);
      });

      it('should return NewPatient for empty Patient initial value', () => {
        const formGroup = service.createPatientFormGroup();

        const patient = service.getPatient(formGroup) as any;

        expect(patient).toMatchObject({});
      });

      it('should return IPatient', () => {
        const formGroup = service.createPatientFormGroup(sampleWithRequiredData);

        const patient = service.getPatient(formGroup) as any;

        expect(patient).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IPatient should not enable id FormControl', () => {
        const formGroup = service.createPatientFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewPatient should disable id FormControl', () => {
        const formGroup = service.createPatientFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
