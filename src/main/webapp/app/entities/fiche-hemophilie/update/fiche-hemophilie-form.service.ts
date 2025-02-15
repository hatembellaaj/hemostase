import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFicheHemophilie, NewFicheHemophilie } from '../fiche-hemophilie.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFicheHemophilie for edit and NewFicheHemophilieFormGroupInput for create.
 */
type FicheHemophilieFormGroupInput = IFicheHemophilie | PartialWithRequiredKeyOf<NewFicheHemophilie>;

type FicheHemophilieFormDefaults = Pick<
  NewFicheHemophilie,
  | 'id'
  | 'cutaneoMuqueux'
  | 'hemarthrose'
  | 'hematomePostTraumatique'
  | 'plaiePostTraumatique'
  | 'hemorragiePostCirconcision'
  | 'hemorragiePostChirurgie'
  | 'hemorragieCerebrale'
  | 'hemorragieDigestive'
  | 'inversionIntron22'
  | 'synovite'
  | 'arthropathieHemophiliqueChronique'
  | 'pseudotumeurs'
  | 'fractures'
  | 'coudeDroit'
  | 'coudeGauche'
  | 'genouDroit'
  | 'genouGauche'
  | 'chevilleDroite'
  | 'chevilleGauche'
  | 'epauleDroite'
  | 'epauleGauche'
  | 'poignetDroit'
  | 'poignetGauche'
  | 'hancheDroite'
  | 'hancheGauche'
  | 'testRecuperationFAH'
  | 'antiFAHNonNeutralisant'
  | 'antiFAHNegatif'
  | 'augmenterDoseFacteur'
  | 'novoseven'
  | 'feiba'
  | 'corticotherapie'
  | 'immurel'
  | 'iti'
  | 'prophylaxieHemlibra'
  | 'activiteSportive'
  | 'decede'
>;

type FicheHemophilieFormGroupContent = {
  id: FormControl<IFicheHemophilie['id'] | NewFicheHemophilie['id']>;
  dossierNumber: FormControl<IFicheHemophilie['dossierNumber']>;
  ordreNumber: FormControl<IFicheHemophilie['ordreNumber']>;
  indexNumber: FormControl<IFicheHemophilie['indexNumber']>;
  prenom: FormControl<IFicheHemophilie['prenom']>;
  nom: FormControl<IFicheHemophilie['nom']>;
  nomJeuneFille: FormControl<IFicheHemophilie['nomJeuneFille']>;
  profession: FormControl<IFicheHemophilie['profession']>;
  etatCivil: FormControl<IFicheHemophilie['etatCivil']>;
  serviceClinique: FormControl<IFicheHemophilie['serviceClinique']>;
  autreServiceClinique: FormControl<IFicheHemophilie['autreServiceClinique']>;
  couvertureSociale: FormControl<IFicheHemophilie['couvertureSociale']>;
  sexe: FormControl<IFicheHemophilie['sexe']>;
  dateNaissance: FormControl<IFicheHemophilie['dateNaissance']>;
  ageActuel: FormControl<IFicheHemophilie['ageActuel']>;
  origine: FormControl<IFicheHemophilie['origine']>;
  autreOrigine: FormControl<IFicheHemophilie['autreOrigine']>;
  adresse: FormControl<IFicheHemophilie['adresse']>;
  telephone: FormControl<IFicheHemophilie['telephone']>;
  prenomPere: FormControl<IFicheHemophilie['prenomPere']>;
  professionPere: FormControl<IFicheHemophilie['professionPere']>;
  nomPrenomMere: FormControl<IFicheHemophilie['nomPrenomMere']>;
  professionMere: FormControl<IFicheHemophilie['professionMere']>;
  medecinTraitant: FormControl<IFicheHemophilie['medecinTraitant']>;
  serviceOrigine: FormControl<IFicheHemophilie['serviceOrigine']>;
  biologisteResponsableDg: FormControl<IFicheHemophilie['biologisteResponsableDg']>;
  diagnostic: FormControl<IFicheHemophilie['diagnostic']>;
  autreDiagnostic: FormControl<IFicheHemophilie['autreDiagnostic']>;
  anneeDiagnostic: FormControl<IFicheHemophilie['anneeDiagnostic']>;
  consentementRegistre: FormControl<IFicheHemophilie['consentementRegistre']>;
  dateEnregistrementRegistre: FormControl<IFicheHemophilie['dateEnregistrementRegistre']>;
  parentsConsanguins: FormControl<IFicheHemophilie['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFicheHemophilie['degreParenteConsanguins']>;
  casSimilaires: FormControl<IFicheHemophilie['casSimilaires']>;
  nombreCas: FormControl<IFicheHemophilie['nombreCas']>;
  degreParenteCasSimilaires: FormControl<IFicheHemophilie['degreParenteCasSimilaires']>;
  decesSyndromeHemorragique: FormControl<IFicheHemophilie['decesSyndromeHemorragique']>;
  nombreDeces: FormControl<IFicheHemophilie['nombreDeces']>;
  formeHemophilie: FormControl<IFicheHemophilie['formeHemophilie']>;
  fratrieNbFreres: FormControl<IFicheHemophilie['fratrieNbFreres']>;
  fratrieNbSoeurs: FormControl<IFicheHemophilie['fratrieNbSoeurs']>;
  circonstancesDecouverte: FormControl<IFicheHemophilie['circonstancesDecouverte']>;
  cutaneoMuqueux: FormControl<IFicheHemophilie['cutaneoMuqueux']>;
  hemarthrose: FormControl<IFicheHemophilie['hemarthrose']>;
  hematomePostTraumatique: FormControl<IFicheHemophilie['hematomePostTraumatique']>;
  plaiePostTraumatique: FormControl<IFicheHemophilie['plaiePostTraumatique']>;
  hemorragiePostCirconcision: FormControl<IFicheHemophilie['hemorragiePostCirconcision']>;
  hemorragiePostChirurgie: FormControl<IFicheHemophilie['hemorragiePostChirurgie']>;
  hemorragieCerebrale: FormControl<IFicheHemophilie['hemorragieCerebrale']>;
  hemorragieDigestive: FormControl<IFicheHemophilie['hemorragieDigestive']>;
  datePremierSigneClinique: FormControl<IFicheHemophilie['datePremierSigneClinique']>;
  ageDiagnosticJours: FormControl<IFicheHemophilie['ageDiagnosticJours']>;
  ageDiagnosticMois: FormControl<IFicheHemophilie['ageDiagnosticMois']>;
  ageDiagnosticAnnees: FormControl<IFicheHemophilie['ageDiagnosticAnnees']>;
  dateTestConfirmation: FormControl<IFicheHemophilie['dateTestConfirmation']>;
  hb: FormControl<IFicheHemophilie['hb']>;
  ht: FormControl<IFicheHemophilie['ht']>;
  plaquettes: FormControl<IFicheHemophilie['plaquettes']>;
  tp: FormControl<IFicheHemophilie['tp']>;
  fibrinogene: FormControl<IFicheHemophilie['fibrinogene']>;
  tcaMT: FormControl<IFicheHemophilie['tcaMT']>;
  tcaMTTotal: FormControl<IFicheHemophilie['tcaMTTotal']>;
  tcaMT2h: FormControl<IFicheHemophilie['tcaMT2h']>;
  tcaTemoin2h: FormControl<IFicheHemophilie['tcaTemoin2h']>;
  ts: FormControl<IFicheHemophilie['ts']>;
  pfa: FormControl<IFicheHemophilie['pfa']>;
  dosageFacteurVIII: FormControl<IFicheHemophilie['dosageFacteurVIII']>;
  dosageFacteurIX: FormControl<IFicheHemophilie['dosageFacteurIX']>;
  dosageFacteurVWActivite: FormControl<IFicheHemophilie['dosageFacteurVWActivite']>;
  dosageFacteurVWAntigene: FormControl<IFicheHemophilie['dosageFacteurVWAntigene']>;
  formeClinique: FormControl<IFicheHemophilie['formeClinique']>;
  severite: FormControl<IFicheHemophilie['severite']>;
  explorationMoleculaire: FormControl<IFicheHemophilie['explorationMoleculaire']>;
  inversionIntron22: FormControl<IFicheHemophilie['inversionIntron22']>;
  autreExplorationMoleculaire: FormControl<IFicheHemophilie['autreExplorationMoleculaire']>;
  hemarthroseMC: FormControl<IFicheHemophilie['hemarthroseMC']>;
  hemarthroseSiege: FormControl<IFicheHemophilie['hemarthroseSiege']>;
  hemarthroseType: FormControl<IFicheHemophilie['hemarthroseType']>;
  hemarthroseFrequenceAn: FormControl<IFicheHemophilie['hemarthroseFrequenceAn']>;
  hematomeSuperficiel: FormControl<IFicheHemophilie['hematomeSuperficiel']>;
  hematomeSuperficielType: FormControl<IFicheHemophilie['hematomeSuperficielType']>;
  hematomeSuperficielSiege: FormControl<IFicheHemophilie['hematomeSuperficielSiege']>;
  hematomePsoas: FormControl<IFicheHemophilie['hematomePsoas']>;
  hematomePsoasType: FormControl<IFicheHemophilie['hematomePsoasType']>;
  hematomePsoasRecidive: FormControl<IFicheHemophilie['hematomePsoasRecidive']>;
  hemorragiesCutaneoMuqueuses: FormControl<IFicheHemophilie['hemorragiesCutaneoMuqueuses']>;
  hemorragiesCutaneoMuqueusesTypes: FormControl<IFicheHemophilie['hemorragiesCutaneoMuqueusesTypes']>;
  hemorragiesCutaneoMuqueusesFrequenceAn: FormControl<IFicheHemophilie['hemorragiesCutaneoMuqueusesFrequenceAn']>;
  hemorragieVisceres: FormControl<IFicheHemophilie['hemorragieVisceres']>;
  hemorragieVisceresType: FormControl<IFicheHemophilie['hemorragieVisceresType']>;
  hemorragieVisceresExploration: FormControl<IFicheHemophilie['hemorragieVisceresExploration']>;
  hemorragieVisceresExamen: FormControl<IFicheHemophilie['hemorragieVisceresExamen']>;
  saignementSNC: FormControl<IFicheHemophilie['saignementSNC']>;
  saignementSNCEvolution: FormControl<IFicheHemophilie['saignementSNCEvolution']>;
  circoncision: FormControl<IFicheHemophilie['circoncision']>;
  circoncisionPreparation: FormControl<IFicheHemophilie['circoncisionPreparation']>;
  circoncisionSuiteOperatoire: FormControl<IFicheHemophilie['circoncisionSuiteOperatoire']>;
  circoncisionAge: FormControl<IFicheHemophilie['circoncisionAge']>;
  avulsionDentaire: FormControl<IFicheHemophilie['avulsionDentaire']>;
  avulsionDentairePreparation: FormControl<IFicheHemophilie['avulsionDentairePreparation']>;
  interventionChirurgicale: FormControl<IFicheHemophilie['interventionChirurgicale']>;
  interventionChirurgicaleType: FormControl<IFicheHemophilie['interventionChirurgicaleType']>;
  accidentsHemorragiquesGraves: FormControl<IFicheHemophilie['accidentsHemorragiquesGraves']>;
  accidentsHemorragiquesGravesTypes: FormControl<IFicheHemophilie['accidentsHemorragiquesGravesTypes']>;
  priseEnChargeTherapeutique: FormControl<IFicheHemophilie['priseEnChargeTherapeutique']>;
  causePriseEnCharge: FormControl<IFicheHemophilie['causePriseEnCharge']>;
  prophylaxieDoseFrequence: FormControl<IFicheHemophilie['prophylaxieDoseFrequence']>;
  dateDebutProphylaxie: FormControl<IFicheHemophilie['dateDebutProphylaxie']>;
  injectionType: FormControl<IFicheHemophilie['injectionType']>;
  modaliteTraitement: FormControl<IFicheHemophilie['modaliteTraitement']>;
  typeTraitementSubstitutif: FormControl<IFicheHemophilie['typeTraitementSubstitutif']>;
  agePremiereSubstitution: FormControl<IFicheHemophilie['agePremiereSubstitution']>;
  antecedentsTraitementPSL: FormControl<IFicheHemophilie['antecedentsTraitementPSL']>;
  plasmaFraisCongele: FormControl<IFicheHemophilie['plasmaFraisCongele']>;
  cryoprecipite: FormControl<IFicheHemophilie['cryoprecipite']>;
  orthopediques: FormControl<IFicheHemophilie['orthopediques']>;
  synovite: FormControl<IFicheHemophilie['synovite']>;
  arthropathieHemophiliqueChronique: FormControl<IFicheHemophilie['arthropathieHemophiliqueChronique']>;
  pseudotumeurs: FormControl<IFicheHemophilie['pseudotumeurs']>;
  fractures: FormControl<IFicheHemophilie['fractures']>;
  coudeDroit: FormControl<IFicheHemophilie['coudeDroit']>;
  coudeGauche: FormControl<IFicheHemophilie['coudeGauche']>;
  genouDroit: FormControl<IFicheHemophilie['genouDroit']>;
  genouGauche: FormControl<IFicheHemophilie['genouGauche']>;
  chevilleDroite: FormControl<IFicheHemophilie['chevilleDroite']>;
  chevilleGauche: FormControl<IFicheHemophilie['chevilleGauche']>;
  epauleDroite: FormControl<IFicheHemophilie['epauleDroite']>;
  epauleGauche: FormControl<IFicheHemophilie['epauleGauche']>;
  poignetDroit: FormControl<IFicheHemophilie['poignetDroit']>;
  poignetGauche: FormControl<IFicheHemophilie['poignetGauche']>;
  hancheDroite: FormControl<IFicheHemophilie['hancheDroite']>;
  hancheGauche: FormControl<IFicheHemophilie['hancheGauche']>;
  pecTherapeutique: FormControl<IFicheHemophilie['pecTherapeutique']>;
  inhibiteurs: FormControl<IFicheHemophilie['inhibiteurs']>;
  tauxInhibiteur: FormControl<IFicheHemophilie['tauxInhibiteur']>;
  testRecuperationFAH: FormControl<IFicheHemophilie['testRecuperationFAH']>;
  dateTestRecuperation: FormControl<IFicheHemophilie['dateTestRecuperation']>;
  tauxRecuperation: FormControl<IFicheHemophilie['tauxRecuperation']>;
  antiFAHNonNeutralisant: FormControl<IFicheHemophilie['antiFAHNonNeutralisant']>;
  antiFAHNegatif: FormControl<IFicheHemophilie['antiFAHNegatif']>;
  rechercheACC: FormControl<IFicheHemophilie['rechercheACC']>;
  augmenterDoseFacteur: FormControl<IFicheHemophilie['augmenterDoseFacteur']>;
  novoseven: FormControl<IFicheHemophilie['novoseven']>;
  feiba: FormControl<IFicheHemophilie['feiba']>;
  corticotherapie: FormControl<IFicheHemophilie['corticotherapie']>;
  immurel: FormControl<IFicheHemophilie['immurel']>;
  iti: FormControl<IFicheHemophilie['iti']>;
  posologieITI: FormControl<IFicheHemophilie['posologieITI']>;
  dureeITI: FormControl<IFicheHemophilie['dureeITI']>;
  observanceITI: FormControl<IFicheHemophilie['observanceITI']>;
  causeNonObservance: FormControl<IFicheHemophilie['causeNonObservance']>;
  evolutionInhibiteur: FormControl<IFicheHemophilie['evolutionInhibiteur']>;
  prophylaxieHemlibra: FormControl<IFicheHemophilie['prophylaxieHemlibra']>;
  dateDebutHemlibra: FormControl<IFicheHemophilie['dateDebutHemlibra']>;
  schemaTherapeutique: FormControl<IFicheHemophilie['schemaTherapeutique']>;
  efficaciteHemlibra: FormControl<IFicheHemophilie['efficaciteHemlibra']>;
  serologieHBC: FormControl<IFicheHemophilie['serologieHBC']>;
  serologieHVC: FormControl<IFicheHemophilie['serologieHVC']>;
  dateDecouverteVHC: FormControl<IFicheHemophilie['dateDecouverteVHC']>;
  chargeViraleVHC: FormControl<IFicheHemophilie['chargeViraleVHC']>;
  traitementVHC: FormControl<IFicheHemophilie['traitementVHC']>;
  evolutionVHC: FormControl<IFicheHemophilie['evolutionVHC']>;
  serologieHIV: FormControl<IFicheHemophilie['serologieHIV']>;
  vieSociale: FormControl<IFicheHemophilie['vieSociale']>;
  marie: FormControl<IFicheHemophilie['marie']>;
  enfants: FormControl<IFicheHemophilie['enfants']>;
  handicap: FormControl<IFicheHemophilie['handicap']>;
  typeHandicap: FormControl<IFicheHemophilie['typeHandicap']>;
  activiteSportive: FormControl<IFicheHemophilie['activiteSportive']>;
  typeActiviteSportive: FormControl<IFicheHemophilie['typeActiviteSportive']>;
  decede: FormControl<IFicheHemophilie['decede']>;
  causeDeces: FormControl<IFicheHemophilie['causeDeces']>;
  dateDeces: FormControl<IFicheHemophilie['dateDeces']>;
};

export type FicheHemophilieFormGroup = FormGroup<FicheHemophilieFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheHemophilieFormService {
  createFicheHemophilieFormGroup(ficheHemophilie: FicheHemophilieFormGroupInput = { id: null }): FicheHemophilieFormGroup {
    const ficheHemophilieRawValue = {
      ...this.getFormDefaults(),
      ...ficheHemophilie,
    };
    return new FormGroup<FicheHemophilieFormGroupContent>({
      id: new FormControl(
        { value: ficheHemophilieRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dossierNumber: new FormControl(ficheHemophilieRawValue.dossierNumber),
      ordreNumber: new FormControl(ficheHemophilieRawValue.ordreNumber, {
        validators: [Validators.maxLength(25)],
      }),
      indexNumber: new FormControl(ficheHemophilieRawValue.indexNumber, {
        validators: [Validators.maxLength(25)],
      }),
      prenom: new FormControl(ficheHemophilieRawValue.prenom, {
        validators: [Validators.maxLength(25)],
      }),
      nom: new FormControl(ficheHemophilieRawValue.nom, {
        validators: [Validators.maxLength(25)],
      }),
      nomJeuneFille: new FormControl(ficheHemophilieRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(25)],
      }),
      profession: new FormControl(ficheHemophilieRawValue.profession, {
        validators: [Validators.maxLength(25)],
      }),
      etatCivil: new FormControl(ficheHemophilieRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      serviceClinique: new FormControl(ficheHemophilieRawValue.serviceClinique, {
        validators: [Validators.required],
      }),
      autreServiceClinique: new FormControl(ficheHemophilieRawValue.autreServiceClinique, {
        validators: [Validators.maxLength(255)],
      }),
      couvertureSociale: new FormControl(ficheHemophilieRawValue.couvertureSociale, {
        validators: [Validators.required],
      }),
      sexe: new FormControl(ficheHemophilieRawValue.sexe, {
        validators: [Validators.required],
      }),
      dateNaissance: new FormControl(ficheHemophilieRawValue.dateNaissance),
      ageActuel: new FormControl(ficheHemophilieRawValue.ageActuel),
      origine: new FormControl(ficheHemophilieRawValue.origine),
      autreOrigine: new FormControl(ficheHemophilieRawValue.autreOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      adresse: new FormControl(ficheHemophilieRawValue.adresse, {
        validators: [Validators.maxLength(25)],
      }),
      telephone: new FormControl(ficheHemophilieRawValue.telephone, {
        validators: [Validators.maxLength(20)],
      }),
      prenomPere: new FormControl(ficheHemophilieRawValue.prenomPere, {
        validators: [Validators.maxLength(25)],
      }),
      professionPere: new FormControl(ficheHemophilieRawValue.professionPere, {
        validators: [Validators.maxLength(25)],
      }),
      nomPrenomMere: new FormControl(ficheHemophilieRawValue.nomPrenomMere, {
        validators: [Validators.maxLength(25)],
      }),
      professionMere: new FormControl(ficheHemophilieRawValue.professionMere, {
        validators: [Validators.maxLength(25)],
      }),
      medecinTraitant: new FormControl(ficheHemophilieRawValue.medecinTraitant, {
        validators: [Validators.maxLength(25)],
      }),
      serviceOrigine: new FormControl(ficheHemophilieRawValue.serviceOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      biologisteResponsableDg: new FormControl(ficheHemophilieRawValue.biologisteResponsableDg, {
        validators: [Validators.maxLength(25)],
      }),
      diagnostic: new FormControl(ficheHemophilieRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(ficheHemophilieRawValue.autreDiagnostic, {
        validators: [Validators.maxLength(25)],
      }),
      anneeDiagnostic: new FormControl(ficheHemophilieRawValue.anneeDiagnostic),
      consentementRegistre: new FormControl(ficheHemophilieRawValue.consentementRegistre),
      dateEnregistrementRegistre: new FormControl(ficheHemophilieRawValue.dateEnregistrementRegistre),
      parentsConsanguins: new FormControl(ficheHemophilieRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheHemophilieRawValue.degreParenteConsanguins, {
        validators: [Validators.maxLength(25)],
      }),
      casSimilaires: new FormControl(ficheHemophilieRawValue.casSimilaires),
      nombreCas: new FormControl(ficheHemophilieRawValue.nombreCas),
      degreParenteCasSimilaires: new FormControl(ficheHemophilieRawValue.degreParenteCasSimilaires),
      decesSyndromeHemorragique: new FormControl(ficheHemophilieRawValue.decesSyndromeHemorragique),
      nombreDeces: new FormControl(ficheHemophilieRawValue.nombreDeces),
      formeHemophilie: new FormControl(ficheHemophilieRawValue.formeHemophilie),
      fratrieNbFreres: new FormControl(ficheHemophilieRawValue.fratrieNbFreres),
      fratrieNbSoeurs: new FormControl(ficheHemophilieRawValue.fratrieNbSoeurs),
      circonstancesDecouverte: new FormControl(ficheHemophilieRawValue.circonstancesDecouverte, {
        validators: [Validators.required],
      }),
      cutaneoMuqueux: new FormControl(ficheHemophilieRawValue.cutaneoMuqueux),
      hemarthrose: new FormControl(ficheHemophilieRawValue.hemarthrose),
      hematomePostTraumatique: new FormControl(ficheHemophilieRawValue.hematomePostTraumatique),
      plaiePostTraumatique: new FormControl(ficheHemophilieRawValue.plaiePostTraumatique),
      hemorragiePostCirconcision: new FormControl(ficheHemophilieRawValue.hemorragiePostCirconcision),
      hemorragiePostChirurgie: new FormControl(ficheHemophilieRawValue.hemorragiePostChirurgie),
      hemorragieCerebrale: new FormControl(ficheHemophilieRawValue.hemorragieCerebrale),
      hemorragieDigestive: new FormControl(ficheHemophilieRawValue.hemorragieDigestive),
      datePremierSigneClinique: new FormControl(ficheHemophilieRawValue.datePremierSigneClinique),
      ageDiagnosticJours: new FormControl(ficheHemophilieRawValue.ageDiagnosticJours),
      ageDiagnosticMois: new FormControl(ficheHemophilieRawValue.ageDiagnosticMois),
      ageDiagnosticAnnees: new FormControl(ficheHemophilieRawValue.ageDiagnosticAnnees),
      dateTestConfirmation: new FormControl(ficheHemophilieRawValue.dateTestConfirmation),
      hb: new FormControl(ficheHemophilieRawValue.hb),
      ht: new FormControl(ficheHemophilieRawValue.ht),
      plaquettes: new FormControl(ficheHemophilieRawValue.plaquettes),
      tp: new FormControl(ficheHemophilieRawValue.tp),
      fibrinogene: new FormControl(ficheHemophilieRawValue.fibrinogene),
      tcaMT: new FormControl(ficheHemophilieRawValue.tcaMT),
      tcaMTTotal: new FormControl(ficheHemophilieRawValue.tcaMTTotal),
      tcaMT2h: new FormControl(ficheHemophilieRawValue.tcaMT2h),
      tcaTemoin2h: new FormControl(ficheHemophilieRawValue.tcaTemoin2h),
      ts: new FormControl(ficheHemophilieRawValue.ts),
      pfa: new FormControl(ficheHemophilieRawValue.pfa),
      dosageFacteurVIII: new FormControl(ficheHemophilieRawValue.dosageFacteurVIII),
      dosageFacteurIX: new FormControl(ficheHemophilieRawValue.dosageFacteurIX),
      dosageFacteurVWActivite: new FormControl(ficheHemophilieRawValue.dosageFacteurVWActivite),
      dosageFacteurVWAntigene: new FormControl(ficheHemophilieRawValue.dosageFacteurVWAntigene),
      formeClinique: new FormControl(ficheHemophilieRawValue.formeClinique, {
        validators: [Validators.required],
      }),
      severite: new FormControl(ficheHemophilieRawValue.severite, {
        validators: [Validators.required],
      }),
      explorationMoleculaire: new FormControl(ficheHemophilieRawValue.explorationMoleculaire),
      inversionIntron22: new FormControl(ficheHemophilieRawValue.inversionIntron22),
      autreExplorationMoleculaire: new FormControl(ficheHemophilieRawValue.autreExplorationMoleculaire, {
        validators: [Validators.maxLength(25)],
      }),
      hemarthroseMC: new FormControl(ficheHemophilieRawValue.hemarthroseMC),
      hemarthroseSiege: new FormControl(ficheHemophilieRawValue.hemarthroseSiege, {
        validators: [Validators.maxLength(25)],
      }),
      hemarthroseType: new FormControl(ficheHemophilieRawValue.hemarthroseType),
      hemarthroseFrequenceAn: new FormControl(ficheHemophilieRawValue.hemarthroseFrequenceAn),
      hematomeSuperficiel: new FormControl(ficheHemophilieRawValue.hematomeSuperficiel),
      hematomeSuperficielType: new FormControl(ficheHemophilieRawValue.hematomeSuperficielType),
      hematomeSuperficielSiege: new FormControl(ficheHemophilieRawValue.hematomeSuperficielSiege, {
        validators: [Validators.maxLength(25)],
      }),
      hematomePsoas: new FormControl(ficheHemophilieRawValue.hematomePsoas),
      hematomePsoasType: new FormControl(ficheHemophilieRawValue.hematomePsoasType),
      hematomePsoasRecidive: new FormControl(ficheHemophilieRawValue.hematomePsoasRecidive),
      hemorragiesCutaneoMuqueuses: new FormControl(ficheHemophilieRawValue.hemorragiesCutaneoMuqueuses),
      hemorragiesCutaneoMuqueusesTypes: new FormControl(ficheHemophilieRawValue.hemorragiesCutaneoMuqueusesTypes),
      hemorragiesCutaneoMuqueusesFrequenceAn: new FormControl(ficheHemophilieRawValue.hemorragiesCutaneoMuqueusesFrequenceAn),
      hemorragieVisceres: new FormControl(ficheHemophilieRawValue.hemorragieVisceres),
      hemorragieVisceresType: new FormControl(ficheHemophilieRawValue.hemorragieVisceresType),
      hemorragieVisceresExploration: new FormControl(ficheHemophilieRawValue.hemorragieVisceresExploration),
      hemorragieVisceresExamen: new FormControl(ficheHemophilieRawValue.hemorragieVisceresExamen, {
        validators: [Validators.maxLength(25)],
      }),
      saignementSNC: new FormControl(ficheHemophilieRawValue.saignementSNC),
      saignementSNCEvolution: new FormControl(ficheHemophilieRawValue.saignementSNCEvolution),
      circoncision: new FormControl(ficheHemophilieRawValue.circoncision),
      circoncisionPreparation: new FormControl(ficheHemophilieRawValue.circoncisionPreparation),
      circoncisionSuiteOperatoire: new FormControl(ficheHemophilieRawValue.circoncisionSuiteOperatoire),
      circoncisionAge: new FormControl(ficheHemophilieRawValue.circoncisionAge),
      avulsionDentaire: new FormControl(ficheHemophilieRawValue.avulsionDentaire),
      avulsionDentairePreparation: new FormControl(ficheHemophilieRawValue.avulsionDentairePreparation),
      interventionChirurgicale: new FormControl(ficheHemophilieRawValue.interventionChirurgicale),
      interventionChirurgicaleType: new FormControl(ficheHemophilieRawValue.interventionChirurgicaleType, {
        validators: [Validators.maxLength(25)],
      }),
      accidentsHemorragiquesGraves: new FormControl(ficheHemophilieRawValue.accidentsHemorragiquesGraves),
      accidentsHemorragiquesGravesTypes: new FormControl(ficheHemophilieRawValue.accidentsHemorragiquesGravesTypes),
      priseEnChargeTherapeutique: new FormControl(ficheHemophilieRawValue.priseEnChargeTherapeutique),
      causePriseEnCharge: new FormControl(ficheHemophilieRawValue.causePriseEnCharge, {
        validators: [Validators.maxLength(25)],
      }),
      prophylaxieDoseFrequence: new FormControl(ficheHemophilieRawValue.prophylaxieDoseFrequence, {
        validators: [Validators.maxLength(25)],
      }),
      dateDebutProphylaxie: new FormControl(ficheHemophilieRawValue.dateDebutProphylaxie),
      injectionType: new FormControl(ficheHemophilieRawValue.injectionType),
      modaliteTraitement: new FormControl(ficheHemophilieRawValue.modaliteTraitement),
      typeTraitementSubstitutif: new FormControl(ficheHemophilieRawValue.typeTraitementSubstitutif, {
        validators: [Validators.maxLength(25)],
      }),
      agePremiereSubstitution: new FormControl(ficheHemophilieRawValue.agePremiereSubstitution),
      antecedentsTraitementPSL: new FormControl(ficheHemophilieRawValue.antecedentsTraitementPSL),
      plasmaFraisCongele: new FormControl(ficheHemophilieRawValue.plasmaFraisCongele),
      cryoprecipite: new FormControl(ficheHemophilieRawValue.cryoprecipite),
      orthopediques: new FormControl(ficheHemophilieRawValue.orthopediques),
      synovite: new FormControl(ficheHemophilieRawValue.synovite),
      arthropathieHemophiliqueChronique: new FormControl(ficheHemophilieRawValue.arthropathieHemophiliqueChronique),
      pseudotumeurs: new FormControl(ficheHemophilieRawValue.pseudotumeurs),
      fractures: new FormControl(ficheHemophilieRawValue.fractures),
      coudeDroit: new FormControl(ficheHemophilieRawValue.coudeDroit),
      coudeGauche: new FormControl(ficheHemophilieRawValue.coudeGauche),
      genouDroit: new FormControl(ficheHemophilieRawValue.genouDroit),
      genouGauche: new FormControl(ficheHemophilieRawValue.genouGauche),
      chevilleDroite: new FormControl(ficheHemophilieRawValue.chevilleDroite),
      chevilleGauche: new FormControl(ficheHemophilieRawValue.chevilleGauche),
      epauleDroite: new FormControl(ficheHemophilieRawValue.epauleDroite),
      epauleGauche: new FormControl(ficheHemophilieRawValue.epauleGauche),
      poignetDroit: new FormControl(ficheHemophilieRawValue.poignetDroit),
      poignetGauche: new FormControl(ficheHemophilieRawValue.poignetGauche),
      hancheDroite: new FormControl(ficheHemophilieRawValue.hancheDroite),
      hancheGauche: new FormControl(ficheHemophilieRawValue.hancheGauche),
      pecTherapeutique: new FormControl(ficheHemophilieRawValue.pecTherapeutique),
      inhibiteurs: new FormControl(ficheHemophilieRawValue.inhibiteurs),
      tauxInhibiteur: new FormControl(ficheHemophilieRawValue.tauxInhibiteur),
      testRecuperationFAH: new FormControl(ficheHemophilieRawValue.testRecuperationFAH),
      dateTestRecuperation: new FormControl(ficheHemophilieRawValue.dateTestRecuperation),
      tauxRecuperation: new FormControl(ficheHemophilieRawValue.tauxRecuperation),
      antiFAHNonNeutralisant: new FormControl(ficheHemophilieRawValue.antiFAHNonNeutralisant),
      antiFAHNegatif: new FormControl(ficheHemophilieRawValue.antiFAHNegatif),
      rechercheACC: new FormControl(ficheHemophilieRawValue.rechercheACC),
      augmenterDoseFacteur: new FormControl(ficheHemophilieRawValue.augmenterDoseFacteur),
      novoseven: new FormControl(ficheHemophilieRawValue.novoseven),
      feiba: new FormControl(ficheHemophilieRawValue.feiba),
      corticotherapie: new FormControl(ficheHemophilieRawValue.corticotherapie),
      immurel: new FormControl(ficheHemophilieRawValue.immurel),
      iti: new FormControl(ficheHemophilieRawValue.iti),
      posologieITI: new FormControl(ficheHemophilieRawValue.posologieITI, {
        validators: [Validators.maxLength(25)],
      }),
      dureeITI: new FormControl(ficheHemophilieRawValue.dureeITI, {
        validators: [Validators.maxLength(25)],
      }),
      observanceITI: new FormControl(ficheHemophilieRawValue.observanceITI),
      causeNonObservance: new FormControl(ficheHemophilieRawValue.causeNonObservance, {
        validators: [Validators.maxLength(25)],
      }),
      evolutionInhibiteur: new FormControl(ficheHemophilieRawValue.evolutionInhibiteur),
      prophylaxieHemlibra: new FormControl(ficheHemophilieRawValue.prophylaxieHemlibra),
      dateDebutHemlibra: new FormControl(ficheHemophilieRawValue.dateDebutHemlibra),
      schemaTherapeutique: new FormControl(ficheHemophilieRawValue.schemaTherapeutique),
      efficaciteHemlibra: new FormControl(ficheHemophilieRawValue.efficaciteHemlibra),
      serologieHBC: new FormControl(ficheHemophilieRawValue.serologieHBC),
      serologieHVC: new FormControl(ficheHemophilieRawValue.serologieHVC),
      dateDecouverteVHC: new FormControl(ficheHemophilieRawValue.dateDecouverteVHC),
      chargeViraleVHC: new FormControl(ficheHemophilieRawValue.chargeViraleVHC, {
        validators: [Validators.maxLength(25)],
      }),
      traitementVHC: new FormControl(ficheHemophilieRawValue.traitementVHC),
      evolutionVHC: new FormControl(ficheHemophilieRawValue.evolutionVHC),
      serologieHIV: new FormControl(ficheHemophilieRawValue.serologieHIV),
      vieSociale: new FormControl(ficheHemophilieRawValue.vieSociale),
      marie: new FormControl(ficheHemophilieRawValue.marie),
      enfants: new FormControl(ficheHemophilieRawValue.enfants),
      handicap: new FormControl(ficheHemophilieRawValue.handicap),
      typeHandicap: new FormControl(ficheHemophilieRawValue.typeHandicap, {
        validators: [Validators.maxLength(25)],
      }),
      activiteSportive: new FormControl(ficheHemophilieRawValue.activiteSportive),
      typeActiviteSportive: new FormControl(ficheHemophilieRawValue.typeActiviteSportive, {
        validators: [Validators.maxLength(25)],
      }),
      decede: new FormControl(ficheHemophilieRawValue.decede),
      causeDeces: new FormControl(ficheHemophilieRawValue.causeDeces, {
        validators: [Validators.maxLength(25)],
      }),
      dateDeces: new FormControl(ficheHemophilieRawValue.dateDeces),
    });
  }

  getFicheHemophilie(form: FicheHemophilieFormGroup): IFicheHemophilie | NewFicheHemophilie {
    return form.getRawValue() as IFicheHemophilie | NewFicheHemophilie;
  }

  resetForm(form: FicheHemophilieFormGroup, ficheHemophilie: FicheHemophilieFormGroupInput): void {
    const ficheHemophilieRawValue = { ...this.getFormDefaults(), ...ficheHemophilie };
    form.reset(
      {
        ...ficheHemophilieRawValue,
        id: { value: ficheHemophilieRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheHemophilieFormDefaults {
    return {
      id: null,
      cutaneoMuqueux: false,
      hemarthrose: false,
      hematomePostTraumatique: false,
      plaiePostTraumatique: false,
      hemorragiePostCirconcision: false,
      hemorragiePostChirurgie: false,
      hemorragieCerebrale: false,
      hemorragieDigestive: false,
      inversionIntron22: false,
      synovite: false,
      arthropathieHemophiliqueChronique: false,
      pseudotumeurs: false,
      fractures: false,
      coudeDroit: false,
      coudeGauche: false,
      genouDroit: false,
      genouGauche: false,
      chevilleDroite: false,
      chevilleGauche: false,
      epauleDroite: false,
      epauleGauche: false,
      poignetDroit: false,
      poignetGauche: false,
      hancheDroite: false,
      hancheGauche: false,
      testRecuperationFAH: false,
      antiFAHNonNeutralisant: false,
      antiFAHNegatif: false,
      augmenterDoseFacteur: false,
      novoseven: false,
      feiba: false,
      corticotherapie: false,
      immurel: false,
      iti: false,
      prophylaxieHemlibra: false,
      activiteSportive: false,
      decede: false,
    };
  }
}
