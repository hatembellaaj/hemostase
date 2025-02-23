import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../fiche-facteur-vii.test-samples';

import { FicheFacteurVIIFormService } from './fiche-facteur-vii-form.service';

describe('FicheFacteurVII Form Service', () => {
  let service: FicheFacteurVIIFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FicheFacteurVIIFormService);
  });

  describe('Service methods', () => {
    describe('createFicheFacteurVIIFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFicheFacteurVIIFormGroup();

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dossierNumber: expect.any(Object),
            ordreNumber: expect.any(Object),
            indexNumber: expect.any(Object),
            prenom: expect.any(Object),
            nom: expect.any(Object),
            nomJeuneFille: expect.any(Object),
            profession: expect.any(Object),
            etatCivil: expect.any(Object),
            serviceClinique: expect.any(Object),
            autreServiceClinique: expect.any(Object),
            couvertureSociale: expect.any(Object),
            sexe: expect.any(Object),
            dateNaissance: expect.any(Object),
            ageActuel: expect.any(Object),
            origine: expect.any(Object),
            autreOrigine: expect.any(Object),
            adresse: expect.any(Object),
            telephone: expect.any(Object),
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
            parentsConsanguins: expect.any(Object),
            degreParenteConsanguins: expect.any(Object),
            casSimilaires: expect.any(Object),
            nombreCas: expect.any(Object),
            cas1Numero: expect.any(Object),
            lienParenteCas1: expect.any(Object),
            degreParenteCas1: expect.any(Object),
            typeHemorragieCas1: expect.any(Object),
            cas2Numero: expect.any(Object),
            lienParenteCas2: expect.any(Object),
            degreParenteCas2: expect.any(Object),
            typeHemorragieCas2: expect.any(Object),
            cas3Numero: expect.any(Object),
            lienParenteCas3: expect.any(Object),
            degreParenteCas3: expect.any(Object),
            typeHemorragieCas3: expect.any(Object),
            decesSyndromeHemorragique: expect.any(Object),
            nombreDeces: expect.any(Object),
            deces1Numero: expect.any(Object),
            lienParenteDeces1: expect.any(Object),
            causeDeces1: expect.any(Object),
            deces2Numero: expect.any(Object),
            lienParenteDeces2: expect.any(Object),
            causeDeces2: expect.any(Object),
            deces3Numero: expect.any(Object),
            lienParenteDeces3: expect.any(Object),
            causeDeces3: expect.any(Object),
            enqueteFamiliale: expect.any(Object),
            decouverteFortuite: expect.any(Object),
            enqueteFamilialeBool: expect.any(Object),
            explorationSyndromeHemorragique: expect.any(Object),
            typeExplorationSyndromeHemorragique: expect.any(Object),
            datePremierSigneClinique: expect.any(Object),
            ageDiagnosticJour: expect.any(Object),
            ageDiagnosticMois: expect.any(Object),
            ageDiagnosticAnnee: expect.any(Object),
            hb: expect.any(Object),
            ht: expect.any(Object),
            plaquettes: expect.any(Object),
            toPfa200ColEpi: expect.any(Object),
            toPfa200ColAdp: expect.any(Object),
            tp: expect.any(Object),
            tpMT: expect.any(Object),
            fibrinogene: expect.any(Object),
            tcaMT: expect.any(Object),
            facteurVII: expect.any(Object),
            dosageAutresFacteurs: expect.any(Object),
            confirmationMoleculaire: expect.any(Object),
            mutationPresence: expect.any(Object),
            mutationType: expect.any(Object),
            epistaxis: expect.any(Object),
            frequenceEpistaxis: expect.any(Object),
            gingivorragies: expect.any(Object),
            typeGingivorragies: expect.any(Object),
            frequenceGingivorragies: expect.any(Object),
            ecchymose: expect.any(Object),
            typeEcchymose: expect.any(Object),
            frequenceEcchymose: expect.any(Object),
            menorragie: expect.any(Object),
            frequenceMenorragie: expect.any(Object),
            hematemese: expect.any(Object),
            frequenceHematemese: expect.any(Object),
            rectorragie: expect.any(Object),
            frequenceRectorragie: expect.any(Object),
            hematurie: expect.any(Object),
            frequenceHematurie: expect.any(Object),
            hematomeSousCutane: expect.any(Object),
            typeHematomeSousCutane: expect.any(Object),
            frequenceHematomeSousCutane: expect.any(Object),
            hematomeMusculaire: expect.any(Object),
            siegeHematomeMusculaire: expect.any(Object),
            typeHematomeMusculaire: expect.any(Object),
            frequenceHematomeMusculaire: expect.any(Object),
            hemarthrose: expect.any(Object),
            typeHemarthrose: expect.any(Object),
            frequenceHemarthrose: expect.any(Object),
            saignementSNC: expect.any(Object),
            typeSaignementSNC: expect.any(Object),
            frequenceSaignementSNC: expect.any(Object),
            ruptureKystesHemorragiques: expect.any(Object),
            hemorragiesPostChirurgicales: expect.any(Object),
            hemoperitone: expect.any(Object),
            accidentsThrombotiques: expect.any(Object),
            typeAccidentsThrombotiques: expect.any(Object),
            hemorragieGrave: expect.any(Object),
            typeHemorragieGrave: expect.any(Object),
            chirurgiePrepare: expect.any(Object),
            suiteOperatoireHemorragique: expect.any(Object),
            circoncisionPrepare: expect.any(Object),
            suiteOperatoireCirconcision: expect.any(Object),
            ageCirconcision: expect.any(Object),
            facteurVIIRecombinant: expect.any(Object),
            plasmaFraisCongele: expect.any(Object),
            cryoprecipite: expect.any(Object),
            culotsPlaquettaires: expect.any(Object),
            dicynone: expect.any(Object),
            exacyl: expect.any(Object),
            traitementMartial: expect.any(Object),
            traitementOestroprogestatif: expect.any(Object),
            transfusionCGR: expect.any(Object),
            inhibiteurs: expect.any(Object),
            inhibiteurNeutralisant: expect.any(Object),
            titreUB: expect.any(Object),
            inhibiteurNonNeutralisant: expect.any(Object),
            infectionsVirales: expect.any(Object),
            thrombose: expect.any(Object),
            reactionAllergique: expect.any(Object),
            alloImmunisation: expect.any(Object),
            adhesionTherapeutique: expect.any(Object),
            causeNonAdhesion: expect.any(Object),
            vieSociale: expect.any(Object),
            mariage: expect.any(Object),
            enfants: expect.any(Object),
            activitePhysique: expect.any(Object),
            typeActivitePhysique: expect.any(Object),
            sequelles: expect.any(Object),
            typeSequelles: expect.any(Object),
            patientDecede: expect.any(Object),
            dateDeces: expect.any(Object),
            ageDeces: expect.any(Object),
            hemorragieSnc: expect.any(Object),
            hemorragieAutreQueSnc: expect.any(Object),
            hiv: expect.any(Object),
            autreInfection: expect.any(Object),
            evenementThromboembolique: expect.any(Object),
            cancer: expect.any(Object),
            covid19: expect.any(Object),
            inconnue: expect.any(Object),
            autreEvenement: expect.any(Object),
            autreEvenementDetails: expect.any(Object),
          }),
        );
      });

      it('passing IFicheFacteurVII should create a new form with FormGroup', () => {
        const formGroup = service.createFicheFacteurVIIFormGroup(sampleWithRequiredData);

        expect(formGroup.controls).toEqual(
          expect.objectContaining({
            id: expect.any(Object),
            dossierNumber: expect.any(Object),
            ordreNumber: expect.any(Object),
            indexNumber: expect.any(Object),
            prenom: expect.any(Object),
            nom: expect.any(Object),
            nomJeuneFille: expect.any(Object),
            profession: expect.any(Object),
            etatCivil: expect.any(Object),
            serviceClinique: expect.any(Object),
            autreServiceClinique: expect.any(Object),
            couvertureSociale: expect.any(Object),
            sexe: expect.any(Object),
            dateNaissance: expect.any(Object),
            ageActuel: expect.any(Object),
            origine: expect.any(Object),
            autreOrigine: expect.any(Object),
            adresse: expect.any(Object),
            telephone: expect.any(Object),
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
            parentsConsanguins: expect.any(Object),
            degreParenteConsanguins: expect.any(Object),
            casSimilaires: expect.any(Object),
            nombreCas: expect.any(Object),
            cas1Numero: expect.any(Object),
            lienParenteCas1: expect.any(Object),
            degreParenteCas1: expect.any(Object),
            typeHemorragieCas1: expect.any(Object),
            cas2Numero: expect.any(Object),
            lienParenteCas2: expect.any(Object),
            degreParenteCas2: expect.any(Object),
            typeHemorragieCas2: expect.any(Object),
            cas3Numero: expect.any(Object),
            lienParenteCas3: expect.any(Object),
            degreParenteCas3: expect.any(Object),
            typeHemorragieCas3: expect.any(Object),
            decesSyndromeHemorragique: expect.any(Object),
            nombreDeces: expect.any(Object),
            deces1Numero: expect.any(Object),
            lienParenteDeces1: expect.any(Object),
            causeDeces1: expect.any(Object),
            deces2Numero: expect.any(Object),
            lienParenteDeces2: expect.any(Object),
            causeDeces2: expect.any(Object),
            deces3Numero: expect.any(Object),
            lienParenteDeces3: expect.any(Object),
            causeDeces3: expect.any(Object),
            enqueteFamiliale: expect.any(Object),
            decouverteFortuite: expect.any(Object),
            enqueteFamilialeBool: expect.any(Object),
            explorationSyndromeHemorragique: expect.any(Object),
            typeExplorationSyndromeHemorragique: expect.any(Object),
            datePremierSigneClinique: expect.any(Object),
            ageDiagnosticJour: expect.any(Object),
            ageDiagnosticMois: expect.any(Object),
            ageDiagnosticAnnee: expect.any(Object),
            hb: expect.any(Object),
            ht: expect.any(Object),
            plaquettes: expect.any(Object),
            toPfa200ColEpi: expect.any(Object),
            toPfa200ColAdp: expect.any(Object),
            tp: expect.any(Object),
            tpMT: expect.any(Object),
            fibrinogene: expect.any(Object),
            tcaMT: expect.any(Object),
            facteurVII: expect.any(Object),
            dosageAutresFacteurs: expect.any(Object),
            confirmationMoleculaire: expect.any(Object),
            mutationPresence: expect.any(Object),
            mutationType: expect.any(Object),
            epistaxis: expect.any(Object),
            frequenceEpistaxis: expect.any(Object),
            gingivorragies: expect.any(Object),
            typeGingivorragies: expect.any(Object),
            frequenceGingivorragies: expect.any(Object),
            ecchymose: expect.any(Object),
            typeEcchymose: expect.any(Object),
            frequenceEcchymose: expect.any(Object),
            menorragie: expect.any(Object),
            frequenceMenorragie: expect.any(Object),
            hematemese: expect.any(Object),
            frequenceHematemese: expect.any(Object),
            rectorragie: expect.any(Object),
            frequenceRectorragie: expect.any(Object),
            hematurie: expect.any(Object),
            frequenceHematurie: expect.any(Object),
            hematomeSousCutane: expect.any(Object),
            typeHematomeSousCutane: expect.any(Object),
            frequenceHematomeSousCutane: expect.any(Object),
            hematomeMusculaire: expect.any(Object),
            siegeHematomeMusculaire: expect.any(Object),
            typeHematomeMusculaire: expect.any(Object),
            frequenceHematomeMusculaire: expect.any(Object),
            hemarthrose: expect.any(Object),
            typeHemarthrose: expect.any(Object),
            frequenceHemarthrose: expect.any(Object),
            saignementSNC: expect.any(Object),
            typeSaignementSNC: expect.any(Object),
            frequenceSaignementSNC: expect.any(Object),
            ruptureKystesHemorragiques: expect.any(Object),
            hemorragiesPostChirurgicales: expect.any(Object),
            hemoperitone: expect.any(Object),
            accidentsThrombotiques: expect.any(Object),
            typeAccidentsThrombotiques: expect.any(Object),
            hemorragieGrave: expect.any(Object),
            typeHemorragieGrave: expect.any(Object),
            chirurgiePrepare: expect.any(Object),
            suiteOperatoireHemorragique: expect.any(Object),
            circoncisionPrepare: expect.any(Object),
            suiteOperatoireCirconcision: expect.any(Object),
            ageCirconcision: expect.any(Object),
            facteurVIIRecombinant: expect.any(Object),
            plasmaFraisCongele: expect.any(Object),
            cryoprecipite: expect.any(Object),
            culotsPlaquettaires: expect.any(Object),
            dicynone: expect.any(Object),
            exacyl: expect.any(Object),
            traitementMartial: expect.any(Object),
            traitementOestroprogestatif: expect.any(Object),
            transfusionCGR: expect.any(Object),
            inhibiteurs: expect.any(Object),
            inhibiteurNeutralisant: expect.any(Object),
            titreUB: expect.any(Object),
            inhibiteurNonNeutralisant: expect.any(Object),
            infectionsVirales: expect.any(Object),
            thrombose: expect.any(Object),
            reactionAllergique: expect.any(Object),
            alloImmunisation: expect.any(Object),
            adhesionTherapeutique: expect.any(Object),
            causeNonAdhesion: expect.any(Object),
            vieSociale: expect.any(Object),
            mariage: expect.any(Object),
            enfants: expect.any(Object),
            activitePhysique: expect.any(Object),
            typeActivitePhysique: expect.any(Object),
            sequelles: expect.any(Object),
            typeSequelles: expect.any(Object),
            patientDecede: expect.any(Object),
            dateDeces: expect.any(Object),
            ageDeces: expect.any(Object),
            hemorragieSnc: expect.any(Object),
            hemorragieAutreQueSnc: expect.any(Object),
            hiv: expect.any(Object),
            autreInfection: expect.any(Object),
            evenementThromboembolique: expect.any(Object),
            cancer: expect.any(Object),
            covid19: expect.any(Object),
            inconnue: expect.any(Object),
            autreEvenement: expect.any(Object),
            autreEvenementDetails: expect.any(Object),
          }),
        );
      });
    });

    describe('getFicheFacteurVII', () => {
      it('should return NewFicheFacteurVII for default FicheFacteurVII initial value', () => {
        const formGroup = service.createFicheFacteurVIIFormGroup(sampleWithNewData);

        const ficheFacteurVII = service.getFicheFacteurVII(formGroup) as any;

        expect(ficheFacteurVII).toMatchObject(sampleWithNewData);
      });

      it('should return NewFicheFacteurVII for empty FicheFacteurVII initial value', () => {
        const formGroup = service.createFicheFacteurVIIFormGroup();

        const ficheFacteurVII = service.getFicheFacteurVII(formGroup) as any;

        expect(ficheFacteurVII).toMatchObject({});
      });

      it('should return IFicheFacteurVII', () => {
        const formGroup = service.createFicheFacteurVIIFormGroup(sampleWithRequiredData);

        const ficheFacteurVII = service.getFicheFacteurVII(formGroup) as any;

        expect(ficheFacteurVII).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFicheFacteurVII should not enable id FormControl', () => {
        const formGroup = service.createFicheFacteurVIIFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFicheFacteurVII should disable id FormControl', () => {
        const formGroup = service.createFicheFacteurVIIFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
