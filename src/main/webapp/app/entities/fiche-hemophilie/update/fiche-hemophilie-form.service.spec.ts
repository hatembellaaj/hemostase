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
            dossierNumber: expect.any(Object),
            ordreNumber: expect.any(Object),
            indexNumber: expect.any(Object),
            parentsConsanguins: expect.any(Object),
            degreParenteConsanguins: expect.any(Object),
            casSimilairesFamille: expect.any(Object),
            nbreCasSimilaires: expect.any(Object),
            degreParenteCasSimilaires: expect.any(Object),
            casDecesSyndromeHemorragique: expect.any(Object),
            nbreCasDeces: expect.any(Object),
            formeHemophilie: expect.any(Object),
            nbreFreres: expect.any(Object),
            nbreSoeurs: expect.any(Object),
            circonstanceDecouverte: expect.any(Object),
            dateTestConfirmation: expect.any(Object),
            hemogrammeHb: expect.any(Object),
            hemogrammeHt: expect.any(Object),
            plaquettes: expect.any(Object),
            tp: expect.any(Object),
            fibrinogene: expect.any(Object),
            tcaMT: expect.any(Object),
            tcaMT2h: expect.any(Object),
            tcaTemoin2h: expect.any(Object),
            ts: expect.any(Object),
            pfa: expect.any(Object),
            dosageFacteurVIII: expect.any(Object),
            dosageFacteurIX: expect.any(Object),
            dosageFacteurVWActivite: expect.any(Object),
            dosageFacteurVWAntigene: expect.any(Object),
            formeClinique: expect.any(Object),
            severite: expect.any(Object),
            explorationMoleculaire: expect.any(Object),
            hemarthrose: expect.any(Object),
            siegeHemarthrose: expect.any(Object),
            circonstanceHemarthrose: expect.any(Object),
            frequenceHemarthrose: expect.any(Object),
            hematomeSuperficiel: expect.any(Object),
            circonstanceHematomeSuperficiel: expect.any(Object),
            siegeHematomeSuperficiel: expect.any(Object),
            hematomePsoas: expect.any(Object),
            circonstanceHematomePsoas: expect.any(Object),
            recidiveHematomePsoas: expect.any(Object),
            hemorragiesCutaneoMuqueuses: expect.any(Object),
            typeHemorragiesCutaneoMuqueuses: expect.any(Object),
            frequenceHemorragiesCutaneoMuqueuses: expect.any(Object),
            hemorragieVisceres: expect.any(Object),
            typeHemorragieVisceres: expect.any(Object),
            explorationHemorragieVisceres: expect.any(Object),
            examenLesionHemorragieVisceres: expect.any(Object),
            saignementSNC: expect.any(Object),
            evolutionSNC: expect.any(Object),
            circoncision: expect.any(Object),
            typePreparationCirconcision: expect.any(Object),
            suiteOperatoireCirconcision: expect.any(Object),
            ageCirconcision: expect.any(Object),
            avulsionDentaire: expect.any(Object),
            typePreparationAvulsionDentaire: expect.any(Object),
            interventionChirurgicale: expect.any(Object),
            typeInterventionChirurgicale: expect.any(Object),
            accidentsHemorragiquesGraves: expect.any(Object),
            circonstanceAccidentsHemorragiquesGraves: expect.any(Object),
            priseEnCharge: expect.any(Object),
            causePriseEnCharge: expect.any(Object),
            doseProphylaxie: expect.any(Object),
            frequenceProphylaxie: expect.any(Object),
            debutProphylaxie: expect.any(Object),
            modaliteInjection: expect.any(Object),
            typeTraitementSubstitutif: expect.any(Object),
            age1ereSubstitution: expect.any(Object),
            antecedentsPSL: expect.any(Object),
            plasmaFraisCongele: expect.any(Object),
            cryoprecipite: expect.any(Object),
            complicationsOrthopediques: expect.any(Object),
            typeComplicationsOrthopediques: expect.any(Object),
            articulationCible: expect.any(Object),
            pecTherapeutique: expect.any(Object),
            complicationsInhibiteurs: expect.any(Object),
            tauxInhibiteur: expect.any(Object),
            testRecuperationFAH: expect.any(Object),
            dateRecuperationFAH: expect.any(Object),
            tauxRecuperation: expect.any(Object),
            antiFAHNonNeutralisant: expect.any(Object),
            rechercheACC: expect.any(Object),
            catTherapeutique: expect.any(Object),
            iti: expect.any(Object),
            posologieITI: expect.any(Object),
            dureeITI: expect.any(Object),
            observanceITI: expect.any(Object),
            causeNonObservanceITI: expect.any(Object),
            evolutionInhibiteur: expect.any(Object),
            prophylaxieHemlibra: expect.any(Object),
            dateDebutHemlibra: expect.any(Object),
            schemaTherapeutique: expect.any(Object),
            efficaciteHemlibra: expect.any(Object),
            serologieHBC: expect.any(Object),
            serologieHVC: expect.any(Object),
            dateDecouverteVHC: expect.any(Object),
            chargeViraleVHC: expect.any(Object),
            traitementVHC: expect.any(Object),
            evolutionVHC: expect.any(Object),
            serologieHIV: expect.any(Object),
            vieSociale: expect.any(Object),
            marie: expect.any(Object),
            enfants: expect.any(Object),
            handicap: expect.any(Object),
            typeHandicap: expect.any(Object),
            activiteSportive: expect.any(Object),
            typeActiviteSportive: expect.any(Object),
            decede: expect.any(Object),
            causeDateDeces: expect.any(Object),
            patient: expect.any(Object),
          }),
        );
      });

      it('passing IFicheHemophilie should create a new form with FormGroup', () => {
        const formGroup = service.createFicheHemophilieFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dossierNumber: expect.any(Object),
            ordreNumber: expect.any(Object),
            indexNumber: expect.any(Object),
            parentsConsanguins: expect.any(Object),
            degreParenteConsanguins: expect.any(Object),
            casSimilairesFamille: expect.any(Object),
            nbreCasSimilaires: expect.any(Object),
            degreParenteCasSimilaires: expect.any(Object),
            casDecesSyndromeHemorragique: expect.any(Object),
            nbreCasDeces: expect.any(Object),
            formeHemophilie: expect.any(Object),
            nbreFreres: expect.any(Object),
            nbreSoeurs: expect.any(Object),
            circonstanceDecouverte: expect.any(Object),
            dateTestConfirmation: expect.any(Object),
            hemogrammeHb: expect.any(Object),
            hemogrammeHt: expect.any(Object),
            plaquettes: expect.any(Object),
            tp: expect.any(Object),
            fibrinogene: expect.any(Object),
            tcaMT: expect.any(Object),
            tcaMT2h: expect.any(Object),
            tcaTemoin2h: expect.any(Object),
            ts: expect.any(Object),
            pfa: expect.any(Object),
            dosageFacteurVIII: expect.any(Object),
            dosageFacteurIX: expect.any(Object),
            dosageFacteurVWActivite: expect.any(Object),
            dosageFacteurVWAntigene: expect.any(Object),
            formeClinique: expect.any(Object),
            severite: expect.any(Object),
            explorationMoleculaire: expect.any(Object),
            hemarthrose: expect.any(Object),
            siegeHemarthrose: expect.any(Object),
            circonstanceHemarthrose: expect.any(Object),
            frequenceHemarthrose: expect.any(Object),
            hematomeSuperficiel: expect.any(Object),
            circonstanceHematomeSuperficiel: expect.any(Object),
            siegeHematomeSuperficiel: expect.any(Object),
            hematomePsoas: expect.any(Object),
            circonstanceHematomePsoas: expect.any(Object),
            recidiveHematomePsoas: expect.any(Object),
            hemorragiesCutaneoMuqueuses: expect.any(Object),
            typeHemorragiesCutaneoMuqueuses: expect.any(Object),
            frequenceHemorragiesCutaneoMuqueuses: expect.any(Object),
            hemorragieVisceres: expect.any(Object),
            typeHemorragieVisceres: expect.any(Object),
            explorationHemorragieVisceres: expect.any(Object),
            examenLesionHemorragieVisceres: expect.any(Object),
            saignementSNC: expect.any(Object),
            evolutionSNC: expect.any(Object),
            circoncision: expect.any(Object),
            typePreparationCirconcision: expect.any(Object),
            suiteOperatoireCirconcision: expect.any(Object),
            ageCirconcision: expect.any(Object),
            avulsionDentaire: expect.any(Object),
            typePreparationAvulsionDentaire: expect.any(Object),
            interventionChirurgicale: expect.any(Object),
            typeInterventionChirurgicale: expect.any(Object),
            accidentsHemorragiquesGraves: expect.any(Object),
            circonstanceAccidentsHemorragiquesGraves: expect.any(Object),
            priseEnCharge: expect.any(Object),
            causePriseEnCharge: expect.any(Object),
            doseProphylaxie: expect.any(Object),
            frequenceProphylaxie: expect.any(Object),
            debutProphylaxie: expect.any(Object),
            modaliteInjection: expect.any(Object),
            typeTraitementSubstitutif: expect.any(Object),
            age1ereSubstitution: expect.any(Object),
            antecedentsPSL: expect.any(Object),
            plasmaFraisCongele: expect.any(Object),
            cryoprecipite: expect.any(Object),
            complicationsOrthopediques: expect.any(Object),
            typeComplicationsOrthopediques: expect.any(Object),
            articulationCible: expect.any(Object),
            pecTherapeutique: expect.any(Object),
            complicationsInhibiteurs: expect.any(Object),
            tauxInhibiteur: expect.any(Object),
            testRecuperationFAH: expect.any(Object),
            dateRecuperationFAH: expect.any(Object),
            tauxRecuperation: expect.any(Object),
            antiFAHNonNeutralisant: expect.any(Object),
            rechercheACC: expect.any(Object),
            catTherapeutique: expect.any(Object),
            iti: expect.any(Object),
            posologieITI: expect.any(Object),
            dureeITI: expect.any(Object),
            observanceITI: expect.any(Object),
            causeNonObservanceITI: expect.any(Object),
            evolutionInhibiteur: expect.any(Object),
            prophylaxieHemlibra: expect.any(Object),
            dateDebutHemlibra: expect.any(Object),
            schemaTherapeutique: expect.any(Object),
            efficaciteHemlibra: expect.any(Object),
            serologieHBC: expect.any(Object),
            serologieHVC: expect.any(Object),
            dateDecouverteVHC: expect.any(Object),
            chargeViraleVHC: expect.any(Object),
            traitementVHC: expect.any(Object),
            evolutionVHC: expect.any(Object),
            serologieHIV: expect.any(Object),
            vieSociale: expect.any(Object),
            marie: expect.any(Object),
            enfants: expect.any(Object),
            handicap: expect.any(Object),
            typeHandicap: expect.any(Object),
            activiteSportive: expect.any(Object),
            typeActiviteSportive: expect.any(Object),
            decede: expect.any(Object),
            causeDateDeces: expect.any(Object),
            patient: expect.any(Object),
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
