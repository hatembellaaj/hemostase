import { TestBed } from '@angular/core/testing';

import { sampleWithNewData, sampleWithRequiredData } from '../fiche-thrombopathie.test-samples';

import { FicheThrombopathieFormService } from './fiche-thrombopathie-form.service';

describe('FicheThrombopathie Form Service', () => {
  let service: FicheThrombopathieFormService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FicheThrombopathieFormService);
  });

  describe('Service methods', () => {
    describe('createFicheThrombopathieFormGroup', () => {
      it('should create a new form with FormControl', () => {
        const formGroup = service.createFicheThrombopathieFormGroup();

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
            degreParenteCasSimilaires: expect.any(Object),
            decesSyndromeHemorragique: expect.any(Object),
            nombreDeces: expect.any(Object),
            typeHemorragie: expect.any(Object),
            lienParenteDeces: expect.any(Object),
            enqueteFamiliale: expect.any(Object),
            antecedentsMedicaux: expect.any(Object),
            antecedentsChirurgicaux: expect.any(Object),
            typeChirurgie: expect.any(Object),
            complicationHemorragique: expect.any(Object),
            agePremierSigneHemorragique: expect.any(Object),
            typeSyndromeHemorragique: expect.any(Object),
            ageDiagnostic: expect.any(Object),
            decouverteFortuite: expect.any(Object),
            enqueteFamilialeBool: expect.any(Object),
            explorationSyndromeHemorragique: expect.any(Object),
            typeCirconstanceDiagnostic: expect.any(Object),
            eczema: expect.any(Object),
            syndromeDysmorphique: expect.any(Object),
            albinisme: expect.any(Object),
            arthrogrypose: expect.any(Object),
            gb: expect.any(Object),
            hb: expect.any(Object),
            vgm: expect.any(Object),
            tcmh: expect.any(Object),
            plaquettes: expect.any(Object),
            frottisSanguin: expect.any(Object),
            ts: expect.any(Object),
            toPfa: expect.any(Object),
            toPfa100ColEpi: expect.any(Object),
            toPfa100ColAdp: expect.any(Object),
            tp: expect.any(Object),
            tca: expect.any(Object),
            aggregometriePlaquettaire: expect.any(Object),
            resultatAgregometrie: expect.any(Object),
            cmf: expect.any(Object),
            resultatCmf: expect.any(Object),
            dosageFvwAg: expect.any(Object),
            dosageFvwRco: expect.any(Object),
            etudeGenetique: expect.any(Object),
            resultatEtudeGenetique: expect.any(Object),
            thrombasthenieGlanzmann: expect.any(Object),
            maladieBernardSoulier: expect.any(Object),
            autreThrombopathie: expect.any(Object),
            autreThrombopathieDetails: expect.any(Object),
            ecchymose: expect.any(Object),
            typeEcchymose: expect.any(Object),
            gingivorragies: expect.any(Object),
            typeGingivorragies: expect.any(Object),
            epistaxis: expect.any(Object),
            typeEpistaxis: expect.any(Object),
            hematome: expect.any(Object),
            typeHematome: expect.any(Object),
            siegelHematome: expect.any(Object),
            menoMetrorragies: expect.any(Object),
            frequenceMenoMetrorragies: expect.any(Object),
            severiteMenoMetrorragies: expect.any(Object),
            explorationMenoMetrorragies: expect.any(Object),
            typeLesionSousJacente: expect.any(Object),
            hemorragieVisceres: expect.any(Object),
            hemorragieDigestiveHaute: expect.any(Object),
            hemorragieDigestiveBasse: expect.any(Object),
            hematurie: expect.any(Object),
            explorationHemorragieVisceres: expect.any(Object),
            lesionSousJacenteVisceres: expect.any(Object),
            interventionChirurgicale: expect.any(Object),
            typeInterventionChirurgicale: expect.any(Object),
            hematomesCerebrales: expect.any(Object),
            edcHemorragique: expect.any(Object),
            transfusionCps: expect.any(Object),
            facteurVIIRecombinant: expect.any(Object),
            dicynone: expect.any(Object),
            exacyl: expect.any(Object),
            traitementOestroprogestatif: expect.any(Object),
            mechage: expect.any(Object),
            transfusionCgr: expect.any(Object),
            traitementMartial: expect.any(Object),
            thrombose: expect.any(Object),
            alloImmunisationHLA: expect.any(Object),
          }),
        );
      });

      it('passing IFicheThrombopathie should create a new form with FormGroup', () => {
        const formGroup = service.createFicheThrombopathieFormGroup(sampleWithRequiredData);

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
            degreParenteCasSimilaires: expect.any(Object),
            decesSyndromeHemorragique: expect.any(Object),
            nombreDeces: expect.any(Object),
            typeHemorragie: expect.any(Object),
            lienParenteDeces: expect.any(Object),
            enqueteFamiliale: expect.any(Object),
            antecedentsMedicaux: expect.any(Object),
            antecedentsChirurgicaux: expect.any(Object),
            typeChirurgie: expect.any(Object),
            complicationHemorragique: expect.any(Object),
            agePremierSigneHemorragique: expect.any(Object),
            typeSyndromeHemorragique: expect.any(Object),
            ageDiagnostic: expect.any(Object),
            decouverteFortuite: expect.any(Object),
            enqueteFamilialeBool: expect.any(Object),
            explorationSyndromeHemorragique: expect.any(Object),
            typeCirconstanceDiagnostic: expect.any(Object),
            eczema: expect.any(Object),
            syndromeDysmorphique: expect.any(Object),
            albinisme: expect.any(Object),
            arthrogrypose: expect.any(Object),
            gb: expect.any(Object),
            hb: expect.any(Object),
            vgm: expect.any(Object),
            tcmh: expect.any(Object),
            plaquettes: expect.any(Object),
            frottisSanguin: expect.any(Object),
            ts: expect.any(Object),
            toPfa: expect.any(Object),
            toPfa100ColEpi: expect.any(Object),
            toPfa100ColAdp: expect.any(Object),
            tp: expect.any(Object),
            tca: expect.any(Object),
            aggregometriePlaquettaire: expect.any(Object),
            resultatAgregometrie: expect.any(Object),
            cmf: expect.any(Object),
            resultatCmf: expect.any(Object),
            dosageFvwAg: expect.any(Object),
            dosageFvwRco: expect.any(Object),
            etudeGenetique: expect.any(Object),
            resultatEtudeGenetique: expect.any(Object),
            thrombasthenieGlanzmann: expect.any(Object),
            maladieBernardSoulier: expect.any(Object),
            autreThrombopathie: expect.any(Object),
            autreThrombopathieDetails: expect.any(Object),
            ecchymose: expect.any(Object),
            typeEcchymose: expect.any(Object),
            gingivorragies: expect.any(Object),
            typeGingivorragies: expect.any(Object),
            epistaxis: expect.any(Object),
            typeEpistaxis: expect.any(Object),
            hematome: expect.any(Object),
            typeHematome: expect.any(Object),
            siegelHematome: expect.any(Object),
            menoMetrorragies: expect.any(Object),
            frequenceMenoMetrorragies: expect.any(Object),
            severiteMenoMetrorragies: expect.any(Object),
            explorationMenoMetrorragies: expect.any(Object),
            typeLesionSousJacente: expect.any(Object),
            hemorragieVisceres: expect.any(Object),
            hemorragieDigestiveHaute: expect.any(Object),
            hemorragieDigestiveBasse: expect.any(Object),
            hematurie: expect.any(Object),
            explorationHemorragieVisceres: expect.any(Object),
            lesionSousJacenteVisceres: expect.any(Object),
            interventionChirurgicale: expect.any(Object),
            typeInterventionChirurgicale: expect.any(Object),
            hematomesCerebrales: expect.any(Object),
            edcHemorragique: expect.any(Object),
            transfusionCps: expect.any(Object),
            facteurVIIRecombinant: expect.any(Object),
            dicynone: expect.any(Object),
            exacyl: expect.any(Object),
            traitementOestroprogestatif: expect.any(Object),
            mechage: expect.any(Object),
            transfusionCgr: expect.any(Object),
            traitementMartial: expect.any(Object),
            thrombose: expect.any(Object),
            alloImmunisationHLA: expect.any(Object),
          }),
        );
      });
    });

    describe('getFicheThrombopathie', () => {
      it('should return NewFicheThrombopathie for default FicheThrombopathie initial value', () => {
        const formGroup = service.createFicheThrombopathieFormGroup(sampleWithNewData);

        const ficheThrombopathie = service.getFicheThrombopathie(formGroup) as any;

        expect(ficheThrombopathie).toMatchObject(sampleWithNewData);
      });

      it('should return NewFicheThrombopathie for empty FicheThrombopathie initial value', () => {
        const formGroup = service.createFicheThrombopathieFormGroup();

        const ficheThrombopathie = service.getFicheThrombopathie(formGroup) as any;

        expect(ficheThrombopathie).toMatchObject({});
      });

      it('should return IFicheThrombopathie', () => {
        const formGroup = service.createFicheThrombopathieFormGroup(sampleWithRequiredData);

        const ficheThrombopathie = service.getFicheThrombopathie(formGroup) as any;

        expect(ficheThrombopathie).toMatchObject(sampleWithRequiredData);
      });
    });

    describe('resetForm', () => {
      it('passing IFicheThrombopathie should not enable id FormControl', () => {
        const formGroup = service.createFicheThrombopathieFormGroup();
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, sampleWithRequiredData);

        expect(formGroup.controls.id.disabled).toBe(true);
      });

      it('passing NewFicheThrombopathie should disable id FormControl', () => {
        const formGroup = service.createFicheThrombopathieFormGroup(sampleWithRequiredData);
        expect(formGroup.controls.id.disabled).toBe(true);

        service.resetForm(formGroup, { id: null });

        expect(formGroup.controls.id.disabled).toBe(true);
      });
    });
  });
});
