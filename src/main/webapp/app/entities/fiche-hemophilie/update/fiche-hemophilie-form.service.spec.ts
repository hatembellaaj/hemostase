import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../fiche-hemophilie.test-samples';

import { FicheHemophilieFormService } from './fiche-hemophilie-form.service';

describe('FicheHemophilie Form Service', () => {
  let service: FicheHemophilieFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FicheHemophilieFormService);
  });

  describe('Service methods', () => {
    describe('createFicheHemophilieFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFicheHemophilieFormGroup();

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
            medecinTraitant: expect.any(Object),
            serviceOrigine: expect.any(Object),
            biologisteResponsableDg: expect.any(Object),
            diagnostic: expect.any(Object),
            autreDiagnostic: expect.any(Object),
            anneeDiagnostic: expect.any(Object),
            consentementRegistre: expect.any(Object),
            dateEnregistrementRegistre: expect.any(Object),
            dossierNumber: expect.any(Object),
            ordreNumber: expect.any(Object),
            indexNumber: expect.any(Object),
            degreParenteConsanguins: expect.any(Object),
            degreParenteCasSimilaires: expect.any(Object),
            typeHemorragiesCutaneoMuqueuses: expect.any(Object),
            circonstanceHematomeSuperficiel: expect.any(Object),
            siegeHematomeSuperficiel: expect.any(Object),
            circonstanceHematomePsoas: expect.any(Object),
            circonstanceAccidentsHemorragiquesGraves: expect.any(Object),
            typeInterventionChirurgicale: expect.any(Object),
            causePriseEnCharge: expect.any(Object),
            posologieITI: expect.any(Object),
            dureeITI: expect.any(Object),
            causeNonObservanceITI: expect.any(Object),
            typeHandicap: expect.any(Object),
            typeActiviteSportive: expect.any(Object),
            causeDateDeces: expect.any(Object),
          }),
        );
      });

      it('passing IFicheHemophilie should create a new form with FormGroup', () => {
        const formGroup = service.createFicheHemophilieFormGroup(sampleWithRequiredData);

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
            medecinTraitant: expect.any(Object),
            serviceOrigine: expect.any(Object),
            biologisteResponsableDg: expect.any(Object),
            diagnostic: expect.any(Object),
            autreDiagnostic: expect.any(Object),
            anneeDiagnostic: expect.any(Object),
            consentementRegistre: expect.any(Object),
            dateEnregistrementRegistre: expect.any(Object),
            dossierNumber: expect.any(Object),
            ordreNumber: expect.any(Object),
            indexNumber: expect.any(Object),
            degreParenteConsanguins: expect.any(Object),
            degreParenteCasSimilaires: expect.any(Object),
            typeHemorragiesCutaneoMuqueuses: expect.any(Object),
            circonstanceHematomeSuperficiel: expect.any(Object),
            siegeHematomeSuperficiel: expect.any(Object),
            circonstanceHematomePsoas: expect.any(Object),
            circonstanceAccidentsHemorragiquesGraves: expect.any(Object),
            typeInterventionChirurgicale: expect.any(Object),
            causePriseEnCharge: expect.any(Object),
            posologieITI: expect.any(Object),
            dureeITI: expect.any(Object),
            causeNonObservanceITI: expect.any(Object),
            typeHandicap: expect.any(Object),
            typeActiviteSportive: expect.any(Object),
            causeDateDeces: expect.any(Object),
          }),
        );
      });
    });

    describe('getFicheHemophilie', () => {
      it('should return NewFicheHemophilie for default FicheHemophilie initial value', () => {
        const formGroup = service.createFicheHemophilieFormGroup(sampleWithNewData);

        const ficheHemophilie = service.getFicheHemophilie(formGroup) as any;

        expect(ficheHemophilie).toMatchObject(sampleWithNewData);
      });

      it('should return NewFicheHemophilie for empty FicheHemophilie initial value', () => {
        const formGroup = service.createFicheHemophilieFormGroup();

        const ficheHemophilie = service.getFicheHemophilie(formGroup) as any;

        expect(ficheHemophilie).toMatchObject({});
      });

      it('should return IFicheHemophilie', () => {
        const formGroup = service.createFicheHemophilieFormGroup(sampleWithRequiredData);

        const ficheHemophilie = service.getFicheHemophilie(formGroup) as any;

        expect(ficheHemophilie).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFicheHemophilie should not enable id FormControl', () => {
        const formGroup = service.createFicheHemophilieFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFicheHemophilie should disable id FormControl', () => {
        const formGroup = service.createFicheHemophilieFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
