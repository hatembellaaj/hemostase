import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFicheFacteurXI, NewFicheFacteurXI } from '../fiche-facteur-xi.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFicheFacteurXI for edit and NewFicheFacteurXIFormGroupInput for create.
 */
type FicheFacteurXIFormGroupInput = IFicheFacteurXI | PartialWithRequiredKeyOf<NewFicheFacteurXI>;

type FicheFacteurXIFormDefaults = Pick<
  NewFicheFacteurXI,
  | 'id'
  | 'symptomeEcchymose'
  | 'symptomeEpistaxis'
  | 'symptomeGingivorragies'
  | 'symptomeMetrorragies'
  | 'symptomeMelena'
  | 'symptomeHemoptysie'
  | 'symptomeHemorragiePostAmygdalectomie'
  | 'symptomeHemorragiePostAvulsionDentaire'
  | 'symptomeHemorragieChuteCordon'
  | 'symptomeAutre'
  | 'mutationPresente'
  | 'epistaxis'
  | 'gingivorragies'
  | 'ecchymose'
  | 'menorragie'
  | 'hematemese'
  | 'rectorragie'
  | 'hematurie'
  | 'hemorragiePostTraumatique'
  | 'hematomeSousCutane'
  | 'hematomeMusculaire'
  | 'hematomeTissusMousProfonds'
  | 'hemarthrose'
  | 'saignementSNC'
  | 'saignementChuteCordon'
  | 'retardCicatrisationPlaies'
  | 'autresManifestations'
  | 'faussesCouchesSpontaneesRecurrence'
  | 'accidentsThrombotiques'
  | 'thromboseVeineuse'
  | 'thromboseArterielle'
  | 'hemoperitoine'
  | 'ruptureKystesHemorragiques'
  | 'hemorragieGrave'
  | 'graviteDeglobulisation'
  | 'graviteEtatDeChoc'
  | 'graviteParLocalisation'
  | 'thrombose'
  | 'reactionAllergique'
>;

type FicheFacteurXIFormGroupContent = {
  id: FormControl<IFicheFacteurXI['id'] | NewFicheFacteurXI['id']>;
  dossierNumber: FormControl<IFicheFacteurXI['dossierNumber']>;
  ordreNumber: FormControl<IFicheFacteurXI['ordreNumber']>;
  indexNumber: FormControl<IFicheFacteurXI['indexNumber']>;
  prenom: FormControl<IFicheFacteurXI['prenom']>;
  nom: FormControl<IFicheFacteurXI['nom']>;
  nomJeuneFille: FormControl<IFicheFacteurXI['nomJeuneFille']>;
  profession: FormControl<IFicheFacteurXI['profession']>;
  etatCivil: FormControl<IFicheFacteurXI['etatCivil']>;
  serviceClinique: FormControl<IFicheFacteurXI['serviceClinique']>;
  autreServiceClinique: FormControl<IFicheFacteurXI['autreServiceClinique']>;
  couvertureSociale: FormControl<IFicheFacteurXI['couvertureSociale']>;
  sexe: FormControl<IFicheFacteurXI['sexe']>;
  dateNaissance: FormControl<IFicheFacteurXI['dateNaissance']>;
  ageActuel: FormControl<IFicheFacteurXI['ageActuel']>;
  origine: FormControl<IFicheFacteurXI['origine']>;
  autreOrigine: FormControl<IFicheFacteurXI['autreOrigine']>;
  adresse: FormControl<IFicheFacteurXI['adresse']>;
  telephone: FormControl<IFicheFacteurXI['telephone']>;
  prenomPere: FormControl<IFicheFacteurXI['prenomPere']>;
  professionPere: FormControl<IFicheFacteurXI['professionPere']>;
  nomPrenomMere: FormControl<IFicheFacteurXI['nomPrenomMere']>;
  professionMere: FormControl<IFicheFacteurXI['professionMere']>;
  medecinTraitant: FormControl<IFicheFacteurXI['medecinTraitant']>;
  serviceOrigine: FormControl<IFicheFacteurXI['serviceOrigine']>;
  biologisteResponsableDg: FormControl<IFicheFacteurXI['biologisteResponsableDg']>;
  diagnostic: FormControl<IFicheFacteurXI['diagnostic']>;
  autreDiagnostic: FormControl<IFicheFacteurXI['autreDiagnostic']>;
  anneeDiagnostic: FormControl<IFicheFacteurXI['anneeDiagnostic']>;
  consentementRegistre: FormControl<IFicheFacteurXI['consentementRegistre']>;
  dateEnregistrementRegistre: FormControl<IFicheFacteurXI['dateEnregistrementRegistre']>;
  parentsConsanguins: FormControl<IFicheFacteurXI['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFicheFacteurXI['degreParenteConsanguins']>;
  casSimilaires: FormControl<IFicheFacteurXI['casSimilaires']>;
  nombreCas: FormControl<IFicheFacteurXI['nombreCas']>;
  cas1Numero: FormControl<IFicheFacteurXI['cas1Numero']>;
  lienParenteCas1: FormControl<IFicheFacteurXI['lienParenteCas1']>;
  degreParenteCas1: FormControl<IFicheFacteurXI['degreParenteCas1']>;
  typeHemorragieCas1: FormControl<IFicheFacteurXI['typeHemorragieCas1']>;
  cas2Numero: FormControl<IFicheFacteurXI['cas2Numero']>;
  lienParenteCas2: FormControl<IFicheFacteurXI['lienParenteCas2']>;
  degreParenteCas2: FormControl<IFicheFacteurXI['degreParenteCas2']>;
  typeHemorragieCas2: FormControl<IFicheFacteurXI['typeHemorragieCas2']>;
  cas3Numero: FormControl<IFicheFacteurXI['cas3Numero']>;
  lienParenteCas3: FormControl<IFicheFacteurXI['lienParenteCas3']>;
  degreParenteCas3: FormControl<IFicheFacteurXI['degreParenteCas3']>;
  typeHemorragieCas3: FormControl<IFicheFacteurXI['typeHemorragieCas3']>;
  decesSyndromeHemorragique: FormControl<IFicheFacteurXI['decesSyndromeHemorragique']>;
  nombreDeces: FormControl<IFicheFacteurXI['nombreDeces']>;
  deces1Numero: FormControl<IFicheFacteurXI['deces1Numero']>;
  lienParenteDeces1: FormControl<IFicheFacteurXI['lienParenteDeces1']>;
  causeDeces1: FormControl<IFicheFacteurXI['causeDeces1']>;
  deces2Numero: FormControl<IFicheFacteurXI['deces2Numero']>;
  lienParenteDeces2: FormControl<IFicheFacteurXI['lienParenteDeces2']>;
  causeDeces2: FormControl<IFicheFacteurXI['causeDeces2']>;
  deces3Numero: FormControl<IFicheFacteurXI['deces3Numero']>;
  lienParenteDeces3: FormControl<IFicheFacteurXI['lienParenteDeces3']>;
  causeDeces3: FormControl<IFicheFacteurXI['causeDeces3']>;
  enqueteFamiliale: FormControl<IFicheFacteurXI['enqueteFamiliale']>;
  syndromeHemorragique: FormControl<IFicheFacteurXI['syndromeHemorragique']>;
  precisionSyndromeHemorragique: FormControl<IFicheFacteurXI['precisionSyndromeHemorragique']>;
  decouverteFortuite: FormControl<IFicheFacteurXI['decouverteFortuite']>;
  precisionDecouverteFortuite: FormControl<IFicheFacteurXI['precisionDecouverteFortuite']>;
  jamaisSaigne: FormControl<IFicheFacteurXI['jamaisSaigne']>;
  agePremierSigneCliniqueJours: FormControl<IFicheFacteurXI['agePremierSigneCliniqueJours']>;
  agePremierSigneCliniqueMois: FormControl<IFicheFacteurXI['agePremierSigneCliniqueMois']>;
  agePremierSigneCliniqueAnnees: FormControl<IFicheFacteurXI['agePremierSigneCliniqueAnnees']>;
  symptomeEcchymose: FormControl<IFicheFacteurXI['symptomeEcchymose']>;
  symptomeEpistaxis: FormControl<IFicheFacteurXI['symptomeEpistaxis']>;
  symptomeGingivorragies: FormControl<IFicheFacteurXI['symptomeGingivorragies']>;
  symptomeMetrorragies: FormControl<IFicheFacteurXI['symptomeMetrorragies']>;
  symptomeMelena: FormControl<IFicheFacteurXI['symptomeMelena']>;
  symptomeHemoptysie: FormControl<IFicheFacteurXI['symptomeHemoptysie']>;
  symptomeHemorragiePostAmygdalectomie: FormControl<IFicheFacteurXI['symptomeHemorragiePostAmygdalectomie']>;
  symptomeHemorragiePostAvulsionDentaire: FormControl<IFicheFacteurXI['symptomeHemorragiePostAvulsionDentaire']>;
  symptomeHemorragieChuteCordon: FormControl<IFicheFacteurXI['symptomeHemorragieChuteCordon']>;
  symptomeAutre: FormControl<IFicheFacteurXI['symptomeAutre']>;
  symptomeAutreDescription: FormControl<IFicheFacteurXI['symptomeAutreDescription']>;
  datePremiereConsultation: FormControl<IFicheFacteurXI['datePremiereConsultation']>;
  agePremiereConsultationJours: FormControl<IFicheFacteurXI['agePremiereConsultationJours']>;
  agePremiereConsultationMois: FormControl<IFicheFacteurXI['agePremiereConsultationMois']>;
  agePremiereConsultationAnnees: FormControl<IFicheFacteurXI['agePremiereConsultationAnnees']>;
  ageDiagnosticJours: FormControl<IFicheFacteurXI['ageDiagnosticJours']>;
  ageDiagnosticMois: FormControl<IFicheFacteurXI['ageDiagnosticMois']>;
  ageDiagnosticAnnees: FormControl<IFicheFacteurXI['ageDiagnosticAnnees']>;
  hb: FormControl<IFicheFacteurXI['hb']>;
  hte: FormControl<IFicheFacteurXI['hte']>;
  plaquettes: FormControl<IFicheFacteurXI['plaquettes']>;
  pfaColEpi: FormControl<IFicheFacteurXI['pfaColEpi']>;
  pfaColADP: FormControl<IFicheFacteurXI['pfaColADP']>;
  tp: FormControl<IFicheFacteurXI['tp']>;
  tpMPlusT: FormControl<IFicheFacteurXI['tpMPlusT']>;
  fibrinogene: FormControl<IFicheFacteurXI['fibrinogene']>;
  tcaMT: FormControl<IFicheFacteurXI['tcaMT']>;
  tcaMPlusT: FormControl<IFicheFacteurXI['tcaMPlusT']>;
  tcaTemoin: FormControl<IFicheFacteurXI['tcaTemoin']>;
  facteurXI: FormControl<IFicheFacteurXI['facteurXI']>;
  autresFacteursDosageDescription: FormControl<IFicheFacteurXI['autresFacteursDosageDescription']>;
  autresFacteursTaux1: FormControl<IFicheFacteurXI['autresFacteursTaux1']>;
  autresFacteursTaux2: FormControl<IFicheFacteurXI['autresFacteursTaux2']>;
  autresFacteursTaux3: FormControl<IFicheFacteurXI['autresFacteursTaux3']>;
  confirmationMoleculaire: FormControl<IFicheFacteurXI['confirmationMoleculaire']>;
  mutationPresente: FormControl<IFicheFacteurXI['mutationPresente']>;
  mutationNom: FormControl<IFicheFacteurXI['mutationNom']>;
  mutationZygote: FormControl<IFicheFacteurXI['mutationZygote']>;
  mutation2Nom: FormControl<IFicheFacteurXI['mutation2Nom']>;
  serologieVHB: FormControl<IFicheFacteurXI['serologieVHB']>;
  serologieVHC: FormControl<IFicheFacteurXI['serologieVHC']>;
  serologieVIH: FormControl<IFicheFacteurXI['serologieVIH']>;
  epistaxis: FormControl<IFicheFacteurXI['epistaxis']>;
  frequenceEpistaxis: FormControl<IFicheFacteurXI['frequenceEpistaxis']>;
  gingivorragies: FormControl<IFicheFacteurXI['gingivorragies']>;
  typeGingivorragies: FormControl<IFicheFacteurXI['typeGingivorragies']>;
  frequenceGingivorragies: FormControl<IFicheFacteurXI['frequenceGingivorragies']>;
  ecchymose: FormControl<IFicheFacteurXI['ecchymose']>;
  typeEcchymose: FormControl<IFicheFacteurXI['typeEcchymose']>;
  frequenceEcchymose: FormControl<IFicheFacteurXI['frequenceEcchymose']>;
  menorragie: FormControl<IFicheFacteurXI['menorragie']>;
  frequenceMenorragie: FormControl<IFicheFacteurXI['frequenceMenorragie']>;
  hematemese: FormControl<IFicheFacteurXI['hematemese']>;
  frequenceHematemese: FormControl<IFicheFacteurXI['frequenceHematemese']>;
  rectorragie: FormControl<IFicheFacteurXI['rectorragie']>;
  frequenceRectorragie: FormControl<IFicheFacteurXI['frequenceRectorragie']>;
  hematurie: FormControl<IFicheFacteurXI['hematurie']>;
  frequenceHematurie: FormControl<IFicheFacteurXI['frequenceHematurie']>;
  hemorragiePostTraumatique: FormControl<IFicheFacteurXI['hemorragiePostTraumatique']>;
  frequenceHemorragiePostTraumatique: FormControl<IFicheFacteurXI['frequenceHemorragiePostTraumatique']>;
  hematomeSousCutane: FormControl<IFicheFacteurXI['hematomeSousCutane']>;
  typeHematomeSousCutane: FormControl<IFicheFacteurXI['typeHematomeSousCutane']>;
  frequenceHematomeSousCutane: FormControl<IFicheFacteurXI['frequenceHematomeSousCutane']>;
  hematomeMusculaire: FormControl<IFicheFacteurXI['hematomeMusculaire']>;
  typeHematomeMusculaire: FormControl<IFicheFacteurXI['typeHematomeMusculaire']>;
  frequenceHematomeMusculaire: FormControl<IFicheFacteurXI['frequenceHematomeMusculaire']>;
  hematomeTissusMousProfonds: FormControl<IFicheFacteurXI['hematomeTissusMousProfonds']>;
  typeHematomeTissusMousProfonds: FormControl<IFicheFacteurXI['typeHematomeTissusMousProfonds']>;
  frequenceHematomeTissusMousProfonds: FormControl<IFicheFacteurXI['frequenceHematomeTissusMousProfonds']>;
  hemarthrose: FormControl<IFicheFacteurXI['hemarthrose']>;
  typeHemarthrose: FormControl<IFicheFacteurXI['typeHemarthrose']>;
  frequenceHemarthrose: FormControl<IFicheFacteurXI['frequenceHemarthrose']>;
  saignementSNC: FormControl<IFicheFacteurXI['saignementSNC']>;
  typeSaignementSNC: FormControl<IFicheFacteurXI['typeSaignementSNC']>;
  frequenceSaignementSNC: FormControl<IFicheFacteurXI['frequenceSaignementSNC']>;
  saignementChuteCordon: FormControl<IFicheFacteurXI['saignementChuteCordon']>;
  frequenceSaignementChuteCordon: FormControl<IFicheFacteurXI['frequenceSaignementChuteCordon']>;
  retardCicatrisationPlaies: FormControl<IFicheFacteurXI['retardCicatrisationPlaies']>;
  frequenceRetardCicatrisationPlaies: FormControl<IFicheFacteurXI['frequenceRetardCicatrisationPlaies']>;
  autresManifestations: FormControl<IFicheFacteurXI['autresManifestations']>;
  faussesCouchesSpontaneesRecurrence: FormControl<IFicheFacteurXI['faussesCouchesSpontaneesRecurrence']>;
  frequenceFaussesCouches: FormControl<IFicheFacteurXI['frequenceFaussesCouches']>;
  accidentsThrombotiques: FormControl<IFicheFacteurXI['accidentsThrombotiques']>;
  thromboseVeineuse: FormControl<IFicheFacteurXI['thromboseVeineuse']>;
  thromboseArterielle: FormControl<IFicheFacteurXI['thromboseArterielle']>;
  frequenceAccidentsThrombotiques: FormControl<IFicheFacteurXI['frequenceAccidentsThrombotiques']>;
  hemoperitoine: FormControl<IFicheFacteurXI['hemoperitoine']>;
  frequenceHemoperitoine: FormControl<IFicheFacteurXI['frequenceHemoperitoine']>;
  ruptureKystesHemorragiques: FormControl<IFicheFacteurXI['ruptureKystesHemorragiques']>;
  frequenceRuptureKystes: FormControl<IFicheFacteurXI['frequenceRuptureKystes']>;
  hemorragieGrave: FormControl<IFicheFacteurXI['hemorragieGrave']>;
  frequenceHemorragieGrave: FormControl<IFicheFacteurXI['frequenceHemorragieGrave']>;
  graviteDeglobulisation: FormControl<IFicheFacteurXI['graviteDeglobulisation']>;
  graviteEtatDeChoc: FormControl<IFicheFacteurXI['graviteEtatDeChoc']>;
  graviteParLocalisation: FormControl<IFicheFacteurXI['graviteParLocalisation']>;
  siegeGraviteLocalisation: FormControl<IFicheFacteurXI['siegeGraviteLocalisation']>;
  chirurgiePrepare: FormControl<IFicheFacteurXI['chirurgiePrepare']>;
  chirurgieSuiteHemorragique: FormControl<IFicheFacteurXI['chirurgieSuiteHemorragique']>;
  circoncisionPrepare: FormControl<IFicheFacteurXI['circoncisionPrepare']>;
  circoncisionSuiteHemorragique: FormControl<IFicheFacteurXI['circoncisionSuiteHemorragique']>;
  ageCirconcision: FormControl<IFicheFacteurXI['ageCirconcision']>;
  traitementPFC: FormControl<IFicheFacteurXI['traitementPFC']>;
  traitementCryoprecipite: FormControl<IFicheFacteurXI['traitementCryoprecipite']>;
  traitementConcentreFacteurXI: FormControl<IFicheFacteurXI['traitementConcentreFacteurXI']>;
  traitementCulotsPlaquettaires: FormControl<IFicheFacteurXI['traitementCulotsPlaquettaires']>;
  traitementDicynone: FormControl<IFicheFacteurXI['traitementDicynone']>;
  traitementExacyl: FormControl<IFicheFacteurXI['traitementExacyl']>;
  traitementMartial: FormControl<IFicheFacteurXI['traitementMartial']>;
  traitementOestroprogestatif: FormControl<IFicheFacteurXI['traitementOestroprogestatif']>;
  transfusionCGR: FormControl<IFicheFacteurXI['transfusionCGR']>;
  alloImmunisation: FormControl<IFicheFacteurXI['alloImmunisation']>;
  inhibiteurNeutralisant: FormControl<IFicheFacteurXI['inhibiteurNeutralisant']>;
  titreInhibiteurUB: FormControl<IFicheFacteurXI['titreInhibiteurUB']>;
  inhibiteurNonNeutralisant: FormControl<IFicheFacteurXI['inhibiteurNonNeutralisant']>;
  infectionVIH: FormControl<IFicheFacteurXI['infectionVIH']>;
  infectionVHC: FormControl<IFicheFacteurXI['infectionVHC']>;
  infectionVHB: FormControl<IFicheFacteurXI['infectionVHB']>;
  thrombose: FormControl<IFicheFacteurXI['thrombose']>;
  reactionAllergique: FormControl<IFicheFacteurXI['reactionAllergique']>;
  autresComplications: FormControl<IFicheFacteurXI['autresComplications']>;
  adherenceTherapeutique: FormControl<IFicheFacteurXI['adherenceTherapeutique']>;
  causeNonAdherence: FormControl<IFicheFacteurXI['causeNonAdherence']>;
  statutSocial: FormControl<IFicheFacteurXI['statutSocial']>;
  marie: FormControl<IFicheFacteurXI['marie']>;
  enfants: FormControl<IFicheFacteurXI['enfants']>;
  activitePhysique: FormControl<IFicheFacteurXI['activitePhysique']>;
  typeActivitePhysique: FormControl<IFicheFacteurXI['typeActivitePhysique']>;
  sequelles: FormControl<IFicheFacteurXI['sequelles']>;
  typeSequelles: FormControl<IFicheFacteurXI['typeSequelles']>;
  patientDecede: FormControl<IFicheFacteurXI['patientDecede']>;
  dateDeces: FormControl<IFicheFacteurXI['dateDeces']>;
  ageDeces: FormControl<IFicheFacteurXI['ageDeces']>;
  causeDeces: FormControl<IFicheFacteurXI['causeDeces']>;
};

export type FicheFacteurXIFormGroup = FormGroup<FicheFacteurXIFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurXIFormService {
  createFicheFacteurXIFormGroup(ficheFacteurXI: FicheFacteurXIFormGroupInput = { id: null }): FicheFacteurXIFormGroup {
    const ficheFacteurXIRawValue = {
      ...this.getFormDefaults(),
      ...ficheFacteurXI,
    };
    return new FormGroup<FicheFacteurXIFormGroupContent>({
      id: new FormControl(
        { value: ficheFacteurXIRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dossierNumber: new FormControl(ficheFacteurXIRawValue.dossierNumber),
      ordreNumber: new FormControl(ficheFacteurXIRawValue.ordreNumber, {
        validators: [Validators.maxLength(25)],
      }),
      indexNumber: new FormControl(ficheFacteurXIRawValue.indexNumber, {
        validators: [Validators.maxLength(25)],
      }),
      prenom: new FormControl(ficheFacteurXIRawValue.prenom, {
        validators: [Validators.maxLength(25)],
      }),
      nom: new FormControl(ficheFacteurXIRawValue.nom, {
        validators: [Validators.maxLength(25)],
      }),
      nomJeuneFille: new FormControl(ficheFacteurXIRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(25)],
      }),
      profession: new FormControl(ficheFacteurXIRawValue.profession, {
        validators: [Validators.maxLength(25)],
      }),
      etatCivil: new FormControl(ficheFacteurXIRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      serviceClinique: new FormControl(ficheFacteurXIRawValue.serviceClinique, {
        validators: [Validators.required],
      }),
      autreServiceClinique: new FormControl(ficheFacteurXIRawValue.autreServiceClinique, {
        validators: [Validators.maxLength(255)],
      }),
      couvertureSociale: new FormControl(ficheFacteurXIRawValue.couvertureSociale, {
        validators: [Validators.required],
      }),
      sexe: new FormControl(ficheFacteurXIRawValue.sexe, {
        validators: [Validators.required],
      }),
      dateNaissance: new FormControl(ficheFacteurXIRawValue.dateNaissance),
      ageActuel: new FormControl(ficheFacteurXIRawValue.ageActuel),
      origine: new FormControl(ficheFacteurXIRawValue.origine),
      autreOrigine: new FormControl(ficheFacteurXIRawValue.autreOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      adresse: new FormControl(ficheFacteurXIRawValue.adresse, {
        validators: [Validators.maxLength(25)],
      }),
      telephone: new FormControl(ficheFacteurXIRawValue.telephone, {
        validators: [Validators.maxLength(20)],
      }),
      prenomPere: new FormControl(ficheFacteurXIRawValue.prenomPere, {
        validators: [Validators.maxLength(25)],
      }),
      professionPere: new FormControl(ficheFacteurXIRawValue.professionPere, {
        validators: [Validators.maxLength(25)],
      }),
      nomPrenomMere: new FormControl(ficheFacteurXIRawValue.nomPrenomMere, {
        validators: [Validators.maxLength(25)],
      }),
      professionMere: new FormControl(ficheFacteurXIRawValue.professionMere, {
        validators: [Validators.maxLength(25)],
      }),
      medecinTraitant: new FormControl(ficheFacteurXIRawValue.medecinTraitant, {
        validators: [Validators.maxLength(25)],
      }),
      serviceOrigine: new FormControl(ficheFacteurXIRawValue.serviceOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      biologisteResponsableDg: new FormControl(ficheFacteurXIRawValue.biologisteResponsableDg, {
        validators: [Validators.maxLength(25)],
      }),
      diagnostic: new FormControl(ficheFacteurXIRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(ficheFacteurXIRawValue.autreDiagnostic, {
        validators: [Validators.maxLength(25)],
      }),
      anneeDiagnostic: new FormControl(ficheFacteurXIRawValue.anneeDiagnostic),
      consentementRegistre: new FormControl(ficheFacteurXIRawValue.consentementRegistre),
      dateEnregistrementRegistre: new FormControl(ficheFacteurXIRawValue.dateEnregistrementRegistre),
      parentsConsanguins: new FormControl(ficheFacteurXIRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheFacteurXIRawValue.degreParenteConsanguins),
      casSimilaires: new FormControl(ficheFacteurXIRawValue.casSimilaires),
      nombreCas: new FormControl(ficheFacteurXIRawValue.nombreCas),
      cas1Numero: new FormControl(ficheFacteurXIRawValue.cas1Numero),
      lienParenteCas1: new FormControl(ficheFacteurXIRawValue.lienParenteCas1),
      degreParenteCas1: new FormControl(ficheFacteurXIRawValue.degreParenteCas1, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas1: new FormControl(ficheFacteurXIRawValue.typeHemorragieCas1, {
        validators: [Validators.maxLength(255)],
      }),
      cas2Numero: new FormControl(ficheFacteurXIRawValue.cas2Numero),
      lienParenteCas2: new FormControl(ficheFacteurXIRawValue.lienParenteCas2),
      degreParenteCas2: new FormControl(ficheFacteurXIRawValue.degreParenteCas2, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas2: new FormControl(ficheFacteurXIRawValue.typeHemorragieCas2, {
        validators: [Validators.maxLength(255)],
      }),
      cas3Numero: new FormControl(ficheFacteurXIRawValue.cas3Numero),
      lienParenteCas3: new FormControl(ficheFacteurXIRawValue.lienParenteCas3),
      degreParenteCas3: new FormControl(ficheFacteurXIRawValue.degreParenteCas3, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas3: new FormControl(ficheFacteurXIRawValue.typeHemorragieCas3, {
        validators: [Validators.maxLength(255)],
      }),
      decesSyndromeHemorragique: new FormControl(ficheFacteurXIRawValue.decesSyndromeHemorragique),
      nombreDeces: new FormControl(ficheFacteurXIRawValue.nombreDeces),
      deces1Numero: new FormControl(ficheFacteurXIRawValue.deces1Numero),
      lienParenteDeces1: new FormControl(ficheFacteurXIRawValue.lienParenteDeces1),
      causeDeces1: new FormControl(ficheFacteurXIRawValue.causeDeces1, {
        validators: [Validators.maxLength(255)],
      }),
      deces2Numero: new FormControl(ficheFacteurXIRawValue.deces2Numero),
      lienParenteDeces2: new FormControl(ficheFacteurXIRawValue.lienParenteDeces2),
      causeDeces2: new FormControl(ficheFacteurXIRawValue.causeDeces2, {
        validators: [Validators.maxLength(255)],
      }),
      deces3Numero: new FormControl(ficheFacteurXIRawValue.deces3Numero),
      lienParenteDeces3: new FormControl(ficheFacteurXIRawValue.lienParenteDeces3),
      causeDeces3: new FormControl(ficheFacteurXIRawValue.causeDeces3, {
        validators: [Validators.maxLength(255)],
      }),
      enqueteFamiliale: new FormControl(ficheFacteurXIRawValue.enqueteFamiliale),
      syndromeHemorragique: new FormControl(ficheFacteurXIRawValue.syndromeHemorragique),
      precisionSyndromeHemorragique: new FormControl(ficheFacteurXIRawValue.precisionSyndromeHemorragique),
      decouverteFortuite: new FormControl(ficheFacteurXIRawValue.decouverteFortuite),
      precisionDecouverteFortuite: new FormControl(ficheFacteurXIRawValue.precisionDecouverteFortuite),
      jamaisSaigne: new FormControl(ficheFacteurXIRawValue.jamaisSaigne),
      agePremierSigneCliniqueJours: new FormControl(ficheFacteurXIRawValue.agePremierSigneCliniqueJours),
      agePremierSigneCliniqueMois: new FormControl(ficheFacteurXIRawValue.agePremierSigneCliniqueMois),
      agePremierSigneCliniqueAnnees: new FormControl(ficheFacteurXIRawValue.agePremierSigneCliniqueAnnees),
      symptomeEcchymose: new FormControl(ficheFacteurXIRawValue.symptomeEcchymose),
      symptomeEpistaxis: new FormControl(ficheFacteurXIRawValue.symptomeEpistaxis),
      symptomeGingivorragies: new FormControl(ficheFacteurXIRawValue.symptomeGingivorragies),
      symptomeMetrorragies: new FormControl(ficheFacteurXIRawValue.symptomeMetrorragies),
      symptomeMelena: new FormControl(ficheFacteurXIRawValue.symptomeMelena),
      symptomeHemoptysie: new FormControl(ficheFacteurXIRawValue.symptomeHemoptysie),
      symptomeHemorragiePostAmygdalectomie: new FormControl(ficheFacteurXIRawValue.symptomeHemorragiePostAmygdalectomie),
      symptomeHemorragiePostAvulsionDentaire: new FormControl(ficheFacteurXIRawValue.symptomeHemorragiePostAvulsionDentaire),
      symptomeHemorragieChuteCordon: new FormControl(ficheFacteurXIRawValue.symptomeHemorragieChuteCordon),
      symptomeAutre: new FormControl(ficheFacteurXIRawValue.symptomeAutre),
      symptomeAutreDescription: new FormControl(ficheFacteurXIRawValue.symptomeAutreDescription),
      datePremiereConsultation: new FormControl(ficheFacteurXIRawValue.datePremiereConsultation),
      agePremiereConsultationJours: new FormControl(ficheFacteurXIRawValue.agePremiereConsultationJours),
      agePremiereConsultationMois: new FormControl(ficheFacteurXIRawValue.agePremiereConsultationMois),
      agePremiereConsultationAnnees: new FormControl(ficheFacteurXIRawValue.agePremiereConsultationAnnees),
      ageDiagnosticJours: new FormControl(ficheFacteurXIRawValue.ageDiagnosticJours),
      ageDiagnosticMois: new FormControl(ficheFacteurXIRawValue.ageDiagnosticMois),
      ageDiagnosticAnnees: new FormControl(ficheFacteurXIRawValue.ageDiagnosticAnnees),
      hb: new FormControl(ficheFacteurXIRawValue.hb),
      hte: new FormControl(ficheFacteurXIRawValue.hte),
      plaquettes: new FormControl(ficheFacteurXIRawValue.plaquettes),
      pfaColEpi: new FormControl(ficheFacteurXIRawValue.pfaColEpi),
      pfaColADP: new FormControl(ficheFacteurXIRawValue.pfaColADP),
      tp: new FormControl(ficheFacteurXIRawValue.tp),
      tpMPlusT: new FormControl(ficheFacteurXIRawValue.tpMPlusT),
      fibrinogene: new FormControl(ficheFacteurXIRawValue.fibrinogene),
      tcaMT: new FormControl(ficheFacteurXIRawValue.tcaMT),
      tcaMPlusT: new FormControl(ficheFacteurXIRawValue.tcaMPlusT),
      tcaTemoin: new FormControl(ficheFacteurXIRawValue.tcaTemoin),
      facteurXI: new FormControl(ficheFacteurXIRawValue.facteurXI),
      autresFacteursDosageDescription: new FormControl(ficheFacteurXIRawValue.autresFacteursDosageDescription),
      autresFacteursTaux1: new FormControl(ficheFacteurXIRawValue.autresFacteursTaux1),
      autresFacteursTaux2: new FormControl(ficheFacteurXIRawValue.autresFacteursTaux2),
      autresFacteursTaux3: new FormControl(ficheFacteurXIRawValue.autresFacteursTaux3),
      confirmationMoleculaire: new FormControl(ficheFacteurXIRawValue.confirmationMoleculaire),
      mutationPresente: new FormControl(ficheFacteurXIRawValue.mutationPresente),
      mutationNom: new FormControl(ficheFacteurXIRawValue.mutationNom),
      mutationZygote: new FormControl(ficheFacteurXIRawValue.mutationZygote),
      mutation2Nom: new FormControl(ficheFacteurXIRawValue.mutation2Nom),
      serologieVHB: new FormControl(ficheFacteurXIRawValue.serologieVHB),
      serologieVHC: new FormControl(ficheFacteurXIRawValue.serologieVHC),
      serologieVIH: new FormControl(ficheFacteurXIRawValue.serologieVIH),
      epistaxis: new FormControl(ficheFacteurXIRawValue.epistaxis),
      frequenceEpistaxis: new FormControl(ficheFacteurXIRawValue.frequenceEpistaxis),
      gingivorragies: new FormControl(ficheFacteurXIRawValue.gingivorragies),
      typeGingivorragies: new FormControl(ficheFacteurXIRawValue.typeGingivorragies),
      frequenceGingivorragies: new FormControl(ficheFacteurXIRawValue.frequenceGingivorragies),
      ecchymose: new FormControl(ficheFacteurXIRawValue.ecchymose),
      typeEcchymose: new FormControl(ficheFacteurXIRawValue.typeEcchymose),
      frequenceEcchymose: new FormControl(ficheFacteurXIRawValue.frequenceEcchymose),
      menorragie: new FormControl(ficheFacteurXIRawValue.menorragie),
      frequenceMenorragie: new FormControl(ficheFacteurXIRawValue.frequenceMenorragie),
      hematemese: new FormControl(ficheFacteurXIRawValue.hematemese),
      frequenceHematemese: new FormControl(ficheFacteurXIRawValue.frequenceHematemese),
      rectorragie: new FormControl(ficheFacteurXIRawValue.rectorragie),
      frequenceRectorragie: new FormControl(ficheFacteurXIRawValue.frequenceRectorragie),
      hematurie: new FormControl(ficheFacteurXIRawValue.hematurie),
      frequenceHematurie: new FormControl(ficheFacteurXIRawValue.frequenceHematurie),
      hemorragiePostTraumatique: new FormControl(ficheFacteurXIRawValue.hemorragiePostTraumatique),
      frequenceHemorragiePostTraumatique: new FormControl(ficheFacteurXIRawValue.frequenceHemorragiePostTraumatique),
      hematomeSousCutane: new FormControl(ficheFacteurXIRawValue.hematomeSousCutane),
      typeHematomeSousCutane: new FormControl(ficheFacteurXIRawValue.typeHematomeSousCutane),
      frequenceHematomeSousCutane: new FormControl(ficheFacteurXIRawValue.frequenceHematomeSousCutane),
      hematomeMusculaire: new FormControl(ficheFacteurXIRawValue.hematomeMusculaire),
      typeHematomeMusculaire: new FormControl(ficheFacteurXIRawValue.typeHematomeMusculaire),
      frequenceHematomeMusculaire: new FormControl(ficheFacteurXIRawValue.frequenceHematomeMusculaire),
      hematomeTissusMousProfonds: new FormControl(ficheFacteurXIRawValue.hematomeTissusMousProfonds),
      typeHematomeTissusMousProfonds: new FormControl(ficheFacteurXIRawValue.typeHematomeTissusMousProfonds),
      frequenceHematomeTissusMousProfonds: new FormControl(ficheFacteurXIRawValue.frequenceHematomeTissusMousProfonds),
      hemarthrose: new FormControl(ficheFacteurXIRawValue.hemarthrose),
      typeHemarthrose: new FormControl(ficheFacteurXIRawValue.typeHemarthrose),
      frequenceHemarthrose: new FormControl(ficheFacteurXIRawValue.frequenceHemarthrose),
      saignementSNC: new FormControl(ficheFacteurXIRawValue.saignementSNC),
      typeSaignementSNC: new FormControl(ficheFacteurXIRawValue.typeSaignementSNC),
      frequenceSaignementSNC: new FormControl(ficheFacteurXIRawValue.frequenceSaignementSNC),
      saignementChuteCordon: new FormControl(ficheFacteurXIRawValue.saignementChuteCordon),
      frequenceSaignementChuteCordon: new FormControl(ficheFacteurXIRawValue.frequenceSaignementChuteCordon),
      retardCicatrisationPlaies: new FormControl(ficheFacteurXIRawValue.retardCicatrisationPlaies),
      frequenceRetardCicatrisationPlaies: new FormControl(ficheFacteurXIRawValue.frequenceRetardCicatrisationPlaies),
      autresManifestations: new FormControl(ficheFacteurXIRawValue.autresManifestations),
      faussesCouchesSpontaneesRecurrence: new FormControl(ficheFacteurXIRawValue.faussesCouchesSpontaneesRecurrence),
      frequenceFaussesCouches: new FormControl(ficheFacteurXIRawValue.frequenceFaussesCouches),
      accidentsThrombotiques: new FormControl(ficheFacteurXIRawValue.accidentsThrombotiques),
      thromboseVeineuse: new FormControl(ficheFacteurXIRawValue.thromboseVeineuse),
      thromboseArterielle: new FormControl(ficheFacteurXIRawValue.thromboseArterielle),
      frequenceAccidentsThrombotiques: new FormControl(ficheFacteurXIRawValue.frequenceAccidentsThrombotiques),
      hemoperitoine: new FormControl(ficheFacteurXIRawValue.hemoperitoine),
      frequenceHemoperitoine: new FormControl(ficheFacteurXIRawValue.frequenceHemoperitoine),
      ruptureKystesHemorragiques: new FormControl(ficheFacteurXIRawValue.ruptureKystesHemorragiques),
      frequenceRuptureKystes: new FormControl(ficheFacteurXIRawValue.frequenceRuptureKystes),
      hemorragieGrave: new FormControl(ficheFacteurXIRawValue.hemorragieGrave),
      frequenceHemorragieGrave: new FormControl(ficheFacteurXIRawValue.frequenceHemorragieGrave),
      graviteDeglobulisation: new FormControl(ficheFacteurXIRawValue.graviteDeglobulisation),
      graviteEtatDeChoc: new FormControl(ficheFacteurXIRawValue.graviteEtatDeChoc),
      graviteParLocalisation: new FormControl(ficheFacteurXIRawValue.graviteParLocalisation),
      siegeGraviteLocalisation: new FormControl(ficheFacteurXIRawValue.siegeGraviteLocalisation, {
        validators: [Validators.maxLength(255)],
      }),
      chirurgiePrepare: new FormControl(ficheFacteurXIRawValue.chirurgiePrepare),
      chirurgieSuiteHemorragique: new FormControl(ficheFacteurXIRawValue.chirurgieSuiteHemorragique),
      circoncisionPrepare: new FormControl(ficheFacteurXIRawValue.circoncisionPrepare),
      circoncisionSuiteHemorragique: new FormControl(ficheFacteurXIRawValue.circoncisionSuiteHemorragique),
      ageCirconcision: new FormControl(ficheFacteurXIRawValue.ageCirconcision),
      traitementPFC: new FormControl(ficheFacteurXIRawValue.traitementPFC),
      traitementCryoprecipite: new FormControl(ficheFacteurXIRawValue.traitementCryoprecipite),
      traitementConcentreFacteurXI: new FormControl(ficheFacteurXIRawValue.traitementConcentreFacteurXI),
      traitementCulotsPlaquettaires: new FormControl(ficheFacteurXIRawValue.traitementCulotsPlaquettaires),
      traitementDicynone: new FormControl(ficheFacteurXIRawValue.traitementDicynone),
      traitementExacyl: new FormControl(ficheFacteurXIRawValue.traitementExacyl),
      traitementMartial: new FormControl(ficheFacteurXIRawValue.traitementMartial),
      traitementOestroprogestatif: new FormControl(ficheFacteurXIRawValue.traitementOestroprogestatif),
      transfusionCGR: new FormControl(ficheFacteurXIRawValue.transfusionCGR),
      alloImmunisation: new FormControl(ficheFacteurXIRawValue.alloImmunisation),
      inhibiteurNeutralisant: new FormControl(ficheFacteurXIRawValue.inhibiteurNeutralisant),
      titreInhibiteurUB: new FormControl(ficheFacteurXIRawValue.titreInhibiteurUB),
      inhibiteurNonNeutralisant: new FormControl(ficheFacteurXIRawValue.inhibiteurNonNeutralisant),
      infectionVIH: new FormControl(ficheFacteurXIRawValue.infectionVIH),
      infectionVHC: new FormControl(ficheFacteurXIRawValue.infectionVHC),
      infectionVHB: new FormControl(ficheFacteurXIRawValue.infectionVHB),
      thrombose: new FormControl(ficheFacteurXIRawValue.thrombose),
      reactionAllergique: new FormControl(ficheFacteurXIRawValue.reactionAllergique),
      autresComplications: new FormControl(ficheFacteurXIRawValue.autresComplications, {
        validators: [Validators.maxLength(255)],
      }),
      adherenceTherapeutique: new FormControl(ficheFacteurXIRawValue.adherenceTherapeutique),
      causeNonAdherence: new FormControl(ficheFacteurXIRawValue.causeNonAdherence, {
        validators: [Validators.maxLength(255)],
      }),
      statutSocial: new FormControl(ficheFacteurXIRawValue.statutSocial),
      marie: new FormControl(ficheFacteurXIRawValue.marie),
      enfants: new FormControl(ficheFacteurXIRawValue.enfants),
      activitePhysique: new FormControl(ficheFacteurXIRawValue.activitePhysique),
      typeActivitePhysique: new FormControl(ficheFacteurXIRawValue.typeActivitePhysique, {
        validators: [Validators.maxLength(255)],
      }),
      sequelles: new FormControl(ficheFacteurXIRawValue.sequelles),
      typeSequelles: new FormControl(ficheFacteurXIRawValue.typeSequelles, {
        validators: [Validators.maxLength(255)],
      }),
      patientDecede: new FormControl(ficheFacteurXIRawValue.patientDecede),
      dateDeces: new FormControl(ficheFacteurXIRawValue.dateDeces),
      ageDeces: new FormControl(ficheFacteurXIRawValue.ageDeces),
      causeDeces: new FormControl(ficheFacteurXIRawValue.causeDeces),
    });
  }

  getFicheFacteurXI(form: FicheFacteurXIFormGroup): IFicheFacteurXI | NewFicheFacteurXI {
    return form.getRawValue() as IFicheFacteurXI | NewFicheFacteurXI;
  }

  resetForm(form: FicheFacteurXIFormGroup, ficheFacteurXI: FicheFacteurXIFormGroupInput): void {
    const ficheFacteurXIRawValue = { ...this.getFormDefaults(), ...ficheFacteurXI };
    form.reset(
      {
        ...ficheFacteurXIRawValue,
        id: { value: ficheFacteurXIRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheFacteurXIFormDefaults {
    return {
      id: null,
      symptomeEcchymose: false,
      symptomeEpistaxis: false,
      symptomeGingivorragies: false,
      symptomeMetrorragies: false,
      symptomeMelena: false,
      symptomeHemoptysie: false,
      symptomeHemorragiePostAmygdalectomie: false,
      symptomeHemorragiePostAvulsionDentaire: false,
      symptomeHemorragieChuteCordon: false,
      symptomeAutre: false,
      mutationPresente: false,
      epistaxis: false,
      gingivorragies: false,
      ecchymose: false,
      menorragie: false,
      hematemese: false,
      rectorragie: false,
      hematurie: false,
      hemorragiePostTraumatique: false,
      hematomeSousCutane: false,
      hematomeMusculaire: false,
      hematomeTissusMousProfonds: false,
      hemarthrose: false,
      saignementSNC: false,
      saignementChuteCordon: false,
      retardCicatrisationPlaies: false,
      autresManifestations: false,
      faussesCouchesSpontaneesRecurrence: false,
      accidentsThrombotiques: false,
      thromboseVeineuse: false,
      thromboseArterielle: false,
      hemoperitoine: false,
      ruptureKystesHemorragiques: false,
      hemorragieGrave: false,
      graviteDeglobulisation: false,
      graviteEtatDeChoc: false,
      graviteParLocalisation: false,
      thrombose: false,
      reactionAllergique: false,
    };
  }
}
