import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFicheFacteurXVIII, NewFicheFacteurXVIII } from '../fiche-facteur-xviii.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFicheFacteurXVIII for edit and NewFicheFacteurXVIIIFormGroupInput for create.
 */
type FicheFacteurXVIIIFormGroupInput = IFicheFacteurXVIII | PartialWithRequiredKeyOf<NewFicheFacteurXVIII>;

type FicheFacteurXVIIIFormDefaults = Pick<
  NewFicheFacteurXVIII,
  | 'id'
  | 'enqueteFamiliale'
  | 'circHemorragique'
  | 'decouverteFortuitePreop'
  | 'symptomeEcchymose'
  | 'symptomeEpistaxis'
  | 'symptomeGingivorragies'
  | 'symptomeMetrorragies'
  | 'symptomeMelena'
  | 'symptomeHemoptysie'
  | 'symptomePostAmygdalectomie'
  | 'symptomePostAvulsionDentaire'
  | 'symptomeChuteCordon'
  | 'symptomeAutre'
  | 'mutationPresente'
  | 'epistaxis'
  | 'gingivorragies'
  | 'ecchymose'
  | 'menorragie'
  | 'hematemese'
  | 'rectorragie'
  | 'hematurie'
  | 'hematomeSousCutane'
  | 'hematomeMusculaire'
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

type FicheFacteurXVIIIFormGroupContent = {
  id: FormControl<IFicheFacteurXVIII['id'] | NewFicheFacteurXVIII['id']>;
  dossierNumber: FormControl<IFicheFacteurXVIII['dossierNumber']>;
  ordreNumber: FormControl<IFicheFacteurXVIII['ordreNumber']>;
  indexNumber: FormControl<IFicheFacteurXVIII['indexNumber']>;
  prenom: FormControl<IFicheFacteurXVIII['prenom']>;
  nom: FormControl<IFicheFacteurXVIII['nom']>;
  nomJeuneFille: FormControl<IFicheFacteurXVIII['nomJeuneFille']>;
  profession: FormControl<IFicheFacteurXVIII['profession']>;
  etatCivil: FormControl<IFicheFacteurXVIII['etatCivil']>;
  serviceClinique: FormControl<IFicheFacteurXVIII['serviceClinique']>;
  autreServiceClinique: FormControl<IFicheFacteurXVIII['autreServiceClinique']>;
  couvertureSociale: FormControl<IFicheFacteurXVIII['couvertureSociale']>;
  sexe: FormControl<IFicheFacteurXVIII['sexe']>;
  dateNaissance: FormControl<IFicheFacteurXVIII['dateNaissance']>;
  ageActuel: FormControl<IFicheFacteurXVIII['ageActuel']>;
  origine: FormControl<IFicheFacteurXVIII['origine']>;
  autreOrigine: FormControl<IFicheFacteurXVIII['autreOrigine']>;
  adresse: FormControl<IFicheFacteurXVIII['adresse']>;
  telephone: FormControl<IFicheFacteurXVIII['telephone']>;
  prenomPere: FormControl<IFicheFacteurXVIII['prenomPere']>;
  professionPere: FormControl<IFicheFacteurXVIII['professionPere']>;
  nomPrenomMere: FormControl<IFicheFacteurXVIII['nomPrenomMere']>;
  professionMere: FormControl<IFicheFacteurXVIII['professionMere']>;
  medecinTraitant: FormControl<IFicheFacteurXVIII['medecinTraitant']>;
  serviceOrigine: FormControl<IFicheFacteurXVIII['serviceOrigine']>;
  biologisteResponsableDg: FormControl<IFicheFacteurXVIII['biologisteResponsableDg']>;
  diagnostic: FormControl<IFicheFacteurXVIII['diagnostic']>;
  autreDiagnostic: FormControl<IFicheFacteurXVIII['autreDiagnostic']>;
  anneeDiagnostic: FormControl<IFicheFacteurXVIII['anneeDiagnostic']>;
  consentementRegistre: FormControl<IFicheFacteurXVIII['consentementRegistre']>;
  dateEnregistrementRegistre: FormControl<IFicheFacteurXVIII['dateEnregistrementRegistre']>;
  parentsConsanguins: FormControl<IFicheFacteurXVIII['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFicheFacteurXVIII['degreParenteConsanguins']>;
  casSimilaires: FormControl<IFicheFacteurXVIII['casSimilaires']>;
  nombreCas: FormControl<IFicheFacteurXVIII['nombreCas']>;
  cas1Numero: FormControl<IFicheFacteurXVIII['cas1Numero']>;
  lienParenteCas1: FormControl<IFicheFacteurXVIII['lienParenteCas1']>;
  degreParenteCas1: FormControl<IFicheFacteurXVIII['degreParenteCas1']>;
  typeHemorragieCas1: FormControl<IFicheFacteurXVIII['typeHemorragieCas1']>;
  cas2Numero: FormControl<IFicheFacteurXVIII['cas2Numero']>;
  lienParenteCas2: FormControl<IFicheFacteurXVIII['lienParenteCas2']>;
  degreParenteCas2: FormControl<IFicheFacteurXVIII['degreParenteCas2']>;
  typeHemorragieCas2: FormControl<IFicheFacteurXVIII['typeHemorragieCas2']>;
  cas3Numero: FormControl<IFicheFacteurXVIII['cas3Numero']>;
  lienParenteCas3: FormControl<IFicheFacteurXVIII['lienParenteCas3']>;
  degreParenteCas3: FormControl<IFicheFacteurXVIII['degreParenteCas3']>;
  typeHemorragieCas3: FormControl<IFicheFacteurXVIII['typeHemorragieCas3']>;
  decesSyndromeHemorragique: FormControl<IFicheFacteurXVIII['decesSyndromeHemorragique']>;
  nombreDeces: FormControl<IFicheFacteurXVIII['nombreDeces']>;
  deces1Numero: FormControl<IFicheFacteurXVIII['deces1Numero']>;
  lienParenteDeces1: FormControl<IFicheFacteurXVIII['lienParenteDeces1']>;
  causeDeces1: FormControl<IFicheFacteurXVIII['causeDeces1']>;
  deces2Numero: FormControl<IFicheFacteurXVIII['deces2Numero']>;
  lienParenteDeces2: FormControl<IFicheFacteurXVIII['lienParenteDeces2']>;
  causeDeces2: FormControl<IFicheFacteurXVIII['causeDeces2']>;
  deces3Numero: FormControl<IFicheFacteurXVIII['deces3Numero']>;
  lienParenteDeces3: FormControl<IFicheFacteurXVIII['lienParenteDeces3']>;
  causeDeces3: FormControl<IFicheFacteurXVIII['causeDeces3']>;
  enqueteFamiliale: FormControl<IFicheFacteurXVIII['enqueteFamiliale']>;
  circHemorragique: FormControl<IFicheFacteurXVIII['circHemorragique']>;
  precisionsCircHemorragique: FormControl<IFicheFacteurXVIII['precisionsCircHemorragique']>;
  decouverteFortuitePreop: FormControl<IFicheFacteurXVIII['decouverteFortuitePreop']>;
  precisionsDecouverteFortuite: FormControl<IFicheFacteurXVIII['precisionsDecouverteFortuite']>;
  jamaisSaignement: FormControl<IFicheFacteurXVIII['jamaisSaignement']>;
  agePremierSigneJours: FormControl<IFicheFacteurXVIII['agePremierSigneJours']>;
  agePremierSigneMois: FormControl<IFicheFacteurXVIII['agePremierSigneMois']>;
  agePremierSigneAnnees: FormControl<IFicheFacteurXVIII['agePremierSigneAnnees']>;
  symptomeEcchymose: FormControl<IFicheFacteurXVIII['symptomeEcchymose']>;
  symptomeEpistaxis: FormControl<IFicheFacteurXVIII['symptomeEpistaxis']>;
  symptomeGingivorragies: FormControl<IFicheFacteurXVIII['symptomeGingivorragies']>;
  symptomeMetrorragies: FormControl<IFicheFacteurXVIII['symptomeMetrorragies']>;
  symptomeMelena: FormControl<IFicheFacteurXVIII['symptomeMelena']>;
  symptomeHemoptysie: FormControl<IFicheFacteurXVIII['symptomeHemoptysie']>;
  symptomePostAmygdalectomie: FormControl<IFicheFacteurXVIII['symptomePostAmygdalectomie']>;
  symptomePostAvulsionDentaire: FormControl<IFicheFacteurXVIII['symptomePostAvulsionDentaire']>;
  symptomeChuteCordon: FormControl<IFicheFacteurXVIII['symptomeChuteCordon']>;
  symptomeAutre: FormControl<IFicheFacteurXVIII['symptomeAutre']>;
  symptomeAutreDescription: FormControl<IFicheFacteurXVIII['symptomeAutreDescription']>;
  datePremiereConsultation: FormControl<IFicheFacteurXVIII['datePremiereConsultation']>;
  agePremiereConsultationJours: FormControl<IFicheFacteurXVIII['agePremiereConsultationJours']>;
  agePremiereConsultationMois: FormControl<IFicheFacteurXVIII['agePremiereConsultationMois']>;
  agePremiereConsultationAnnees: FormControl<IFicheFacteurXVIII['agePremiereConsultationAnnees']>;
  ageDiagnosticJours: FormControl<IFicheFacteurXVIII['ageDiagnosticJours']>;
  ageDiagnosticMois: FormControl<IFicheFacteurXVIII['ageDiagnosticMois']>;
  ageDiagnosticAnnees: FormControl<IFicheFacteurXVIII['ageDiagnosticAnnees']>;
  hb: FormControl<IFicheFacteurXVIII['hb']>;
  ht: FormControl<IFicheFacteurXVIII['ht']>;
  plaquettes: FormControl<IFicheFacteurXVIII['plaquettes']>;
  colEpi: FormControl<IFicheFacteurXVIII['colEpi']>;
  colAdp: FormControl<IFicheFacteurXVIII['colAdp']>;
  tp: FormControl<IFicheFacteurXVIII['tp']>;
  tpMPlusT: FormControl<IFicheFacteurXVIII['tpMPlusT']>;
  fibrinogene: FormControl<IFicheFacteurXVIII['fibrinogene']>;
  tcaMT: FormControl<IFicheFacteurXVIII['tcaMT']>;
  tcaMPlusT: FormControl<IFicheFacteurXVIII['tcaMPlusT']>;
  tcaTemoin: FormControl<IFicheFacteurXVIII['tcaTemoin']>;
  facteurDeficitaireAutre: FormControl<IFicheFacteurXVIII['facteurDeficitaireAutre']>;
  tauxFacteurDeficitaireAutre: FormControl<IFicheFacteurXVIII['tauxFacteurDeficitaireAutre']>;
  facteurSupplementaire1: FormControl<IFicheFacteurXVIII['facteurSupplementaire1']>;
  tauxSupplementaire1: FormControl<IFicheFacteurXVIII['tauxSupplementaire1']>;
  facteurSupplementaire2: FormControl<IFicheFacteurXVIII['facteurSupplementaire2']>;
  tauxSupplementaire2: FormControl<IFicheFacteurXVIII['tauxSupplementaire2']>;
  facteurSupplementaire3: FormControl<IFicheFacteurXVIII['facteurSupplementaire3']>;
  tauxSupplementaire3: FormControl<IFicheFacteurXVIII['tauxSupplementaire3']>;
  confirmationMoleculaire: FormControl<IFicheFacteurXVIII['confirmationMoleculaire']>;
  mutationPresente: FormControl<IFicheFacteurXVIII['mutationPresente']>;
  mutationNom: FormControl<IFicheFacteurXVIII['mutationNom']>;
  mutationType: FormControl<IFicheFacteurXVIII['mutationType']>;
  mutation2Nom: FormControl<IFicheFacteurXVIII['mutation2Nom']>;
  serologieVHB: FormControl<IFicheFacteurXVIII['serologieVHB']>;
  serologieVHC: FormControl<IFicheFacteurXVIII['serologieVHC']>;
  serologieVIH: FormControl<IFicheFacteurXVIII['serologieVIH']>;
  epistaxis: FormControl<IFicheFacteurXVIII['epistaxis']>;
  frequenceEpistaxis: FormControl<IFicheFacteurXVIII['frequenceEpistaxis']>;
  gingivorragies: FormControl<IFicheFacteurXVIII['gingivorragies']>;
  typeGingivorragies: FormControl<IFicheFacteurXVIII['typeGingivorragies']>;
  frequenceGingivorragies: FormControl<IFicheFacteurXVIII['frequenceGingivorragies']>;
  ecchymose: FormControl<IFicheFacteurXVIII['ecchymose']>;
  typeEcchymose: FormControl<IFicheFacteurXVIII['typeEcchymose']>;
  frequenceEcchymose: FormControl<IFicheFacteurXVIII['frequenceEcchymose']>;
  menorragie: FormControl<IFicheFacteurXVIII['menorragie']>;
  frequenceMenorragie: FormControl<IFicheFacteurXVIII['frequenceMenorragie']>;
  hematemese: FormControl<IFicheFacteurXVIII['hematemese']>;
  frequenceHematemese: FormControl<IFicheFacteurXVIII['frequenceHematemese']>;
  rectorragie: FormControl<IFicheFacteurXVIII['rectorragie']>;
  frequenceRectorragie: FormControl<IFicheFacteurXVIII['frequenceRectorragie']>;
  hematurie: FormControl<IFicheFacteurXVIII['hematurie']>;
  frequenceHematurie: FormControl<IFicheFacteurXVIII['frequenceHematurie']>;
  hematomeSousCutane: FormControl<IFicheFacteurXVIII['hematomeSousCutane']>;
  typeHematomeSousCutane: FormControl<IFicheFacteurXVIII['typeHematomeSousCutane']>;
  frequenceHematomeSousCutane: FormControl<IFicheFacteurXVIII['frequenceHematomeSousCutane']>;
  hematomeMusculaire: FormControl<IFicheFacteurXVIII['hematomeMusculaire']>;
  siegeHematomeMusculaire: FormControl<IFicheFacteurXVIII['siegeHematomeMusculaire']>;
  typeHematomeMusculaire: FormControl<IFicheFacteurXVIII['typeHematomeMusculaire']>;
  frequenceHematomeMusculaire: FormControl<IFicheFacteurXVIII['frequenceHematomeMusculaire']>;
  hemarthrose: FormControl<IFicheFacteurXVIII['hemarthrose']>;
  typeHemarthrose: FormControl<IFicheFacteurXVIII['typeHemarthrose']>;
  frequenceHemarthrose: FormControl<IFicheFacteurXVIII['frequenceHemarthrose']>;
  saignementSNC: FormControl<IFicheFacteurXVIII['saignementSNC']>;
  typeSaignementSNC: FormControl<IFicheFacteurXVIII['typeSaignementSNC']>;
  frequenceSaignementSNC: FormControl<IFicheFacteurXVIII['frequenceSaignementSNC']>;
  saignementChuteCordon: FormControl<IFicheFacteurXVIII['saignementChuteCordon']>;
  frequenceSaignementChuteCordon: FormControl<IFicheFacteurXVIII['frequenceSaignementChuteCordon']>;
  retardCicatrisationPlaies: FormControl<IFicheFacteurXVIII['retardCicatrisationPlaies']>;
  frequenceRetardCicatrisationPlaies: FormControl<IFicheFacteurXVIII['frequenceRetardCicatrisationPlaies']>;
  autresManifestations: FormControl<IFicheFacteurXVIII['autresManifestations']>;
  faussesCouchesSpontaneesRecurrence: FormControl<IFicheFacteurXVIII['faussesCouchesSpontaneesRecurrence']>;
  frequenceFaussesCouches: FormControl<IFicheFacteurXVIII['frequenceFaussesCouches']>;
  accidentsThrombotiques: FormControl<IFicheFacteurXVIII['accidentsThrombotiques']>;
  thromboseVeineuse: FormControl<IFicheFacteurXVIII['thromboseVeineuse']>;
  thromboseArterielle: FormControl<IFicheFacteurXVIII['thromboseArterielle']>;
  frequenceAccidentsThrombotiques: FormControl<IFicheFacteurXVIII['frequenceAccidentsThrombotiques']>;
  hemoperitoine: FormControl<IFicheFacteurXVIII['hemoperitoine']>;
  frequenceHemoperitoine: FormControl<IFicheFacteurXVIII['frequenceHemoperitoine']>;
  ruptureKystesHemorragiques: FormControl<IFicheFacteurXVIII['ruptureKystesHemorragiques']>;
  frequenceRuptureKystes: FormControl<IFicheFacteurXVIII['frequenceRuptureKystes']>;
  hemorragieGrave: FormControl<IFicheFacteurXVIII['hemorragieGrave']>;
  frequenceHemorragieGrave: FormControl<IFicheFacteurXVIII['frequenceHemorragieGrave']>;
  graviteDeglobulisation: FormControl<IFicheFacteurXVIII['graviteDeglobulisation']>;
  graviteEtatDeChoc: FormControl<IFicheFacteurXVIII['graviteEtatDeChoc']>;
  graviteParLocalisation: FormControl<IFicheFacteurXVIII['graviteParLocalisation']>;
  siegeGraviteLocalisation: FormControl<IFicheFacteurXVIII['siegeGraviteLocalisation']>;
  chirurgiePrepare: FormControl<IFicheFacteurXVIII['chirurgiePrepare']>;
  chirurgieSuiteHemorragique: FormControl<IFicheFacteurXVIII['chirurgieSuiteHemorragique']>;
  circoncisionPrepare: FormControl<IFicheFacteurXVIII['circoncisionPrepare']>;
  circoncisionSuiteHemorragique: FormControl<IFicheFacteurXVIII['circoncisionSuiteHemorragique']>;
  ageCirconcision: FormControl<IFicheFacteurXVIII['ageCirconcision']>;
  traitementPFC: FormControl<IFicheFacteurXVIII['traitementPFC']>;
  traitementCryoprecipite: FormControl<IFicheFacteurXVIII['traitementCryoprecipite']>;
  traitementConcentreFacteurXI: FormControl<IFicheFacteurXVIII['traitementConcentreFacteurXI']>;
  traitementCulotsPlaquettaires: FormControl<IFicheFacteurXVIII['traitementCulotsPlaquettaires']>;
  traitementDicynone: FormControl<IFicheFacteurXVIII['traitementDicynone']>;
  traitementExacyl: FormControl<IFicheFacteurXVIII['traitementExacyl']>;
  traitementMartial: FormControl<IFicheFacteurXVIII['traitementMartial']>;
  traitementOestroprogestatif: FormControl<IFicheFacteurXVIII['traitementOestroprogestatif']>;
  transfusionCGR: FormControl<IFicheFacteurXVIII['transfusionCGR']>;
  alloImmunisation: FormControl<IFicheFacteurXVIII['alloImmunisation']>;
  inhibiteurNeutralisant: FormControl<IFicheFacteurXVIII['inhibiteurNeutralisant']>;
  titreInhibiteurUB: FormControl<IFicheFacteurXVIII['titreInhibiteurUB']>;
  inhibiteurNonNeutralisant: FormControl<IFicheFacteurXVIII['inhibiteurNonNeutralisant']>;
  infectionVIH: FormControl<IFicheFacteurXVIII['infectionVIH']>;
  infectionVHC: FormControl<IFicheFacteurXVIII['infectionVHC']>;
  infectionVHB: FormControl<IFicheFacteurXVIII['infectionVHB']>;
  thrombose: FormControl<IFicheFacteurXVIII['thrombose']>;
  reactionAllergique: FormControl<IFicheFacteurXVIII['reactionAllergique']>;
  autresComplications: FormControl<IFicheFacteurXVIII['autresComplications']>;
  adherenceTherapeutique: FormControl<IFicheFacteurXVIII['adherenceTherapeutique']>;
  causeNonAdherence: FormControl<IFicheFacteurXVIII['causeNonAdherence']>;
  statutSocial: FormControl<IFicheFacteurXVIII['statutSocial']>;
  marie: FormControl<IFicheFacteurXVIII['marie']>;
  enfants: FormControl<IFicheFacteurXVIII['enfants']>;
  activitePhysique: FormControl<IFicheFacteurXVIII['activitePhysique']>;
  typeActivitePhysique: FormControl<IFicheFacteurXVIII['typeActivitePhysique']>;
  sequelles: FormControl<IFicheFacteurXVIII['sequelles']>;
  typeSequelles: FormControl<IFicheFacteurXVIII['typeSequelles']>;
  patientDecede: FormControl<IFicheFacteurXVIII['patientDecede']>;
  dateDeces: FormControl<IFicheFacteurXVIII['dateDeces']>;
  ageDeces: FormControl<IFicheFacteurXVIII['ageDeces']>;
  causeDeces: FormControl<IFicheFacteurXVIII['causeDeces']>;
};

export type FicheFacteurXVIIIFormGroup = FormGroup<FicheFacteurXVIIIFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurXVIIIFormService {
  createFicheFacteurXVIIIFormGroup(ficheFacteurXVIII: FicheFacteurXVIIIFormGroupInput = { id: null }): FicheFacteurXVIIIFormGroup {
    const ficheFacteurXVIIIRawValue = {
      ...this.getFormDefaults(),
      ...ficheFacteurXVIII,
    };
    return new FormGroup<FicheFacteurXVIIIFormGroupContent>({
      id: new FormControl(
        { value: ficheFacteurXVIIIRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dossierNumber: new FormControl(ficheFacteurXVIIIRawValue.dossierNumber),
      ordreNumber: new FormControl(ficheFacteurXVIIIRawValue.ordreNumber, {
        validators: [Validators.maxLength(25)],
      }),
      indexNumber: new FormControl(ficheFacteurXVIIIRawValue.indexNumber, {
        validators: [Validators.maxLength(25)],
      }),
      prenom: new FormControl(ficheFacteurXVIIIRawValue.prenom, {
        validators: [Validators.maxLength(25)],
      }),
      nom: new FormControl(ficheFacteurXVIIIRawValue.nom, {
        validators: [Validators.maxLength(25)],
      }),
      nomJeuneFille: new FormControl(ficheFacteurXVIIIRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(25)],
      }),
      profession: new FormControl(ficheFacteurXVIIIRawValue.profession, {
        validators: [Validators.maxLength(25)],
      }),
      etatCivil: new FormControl(ficheFacteurXVIIIRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      serviceClinique: new FormControl(ficheFacteurXVIIIRawValue.serviceClinique, {
        validators: [Validators.required],
      }),
      autreServiceClinique: new FormControl(ficheFacteurXVIIIRawValue.autreServiceClinique, {
        validators: [Validators.maxLength(255)],
      }),
      couvertureSociale: new FormControl(ficheFacteurXVIIIRawValue.couvertureSociale, {
        validators: [Validators.required],
      }),
      sexe: new FormControl(ficheFacteurXVIIIRawValue.sexe, {
        validators: [Validators.required],
      }),
      dateNaissance: new FormControl(ficheFacteurXVIIIRawValue.dateNaissance),
      ageActuel: new FormControl(ficheFacteurXVIIIRawValue.ageActuel),
      origine: new FormControl(ficheFacteurXVIIIRawValue.origine),
      autreOrigine: new FormControl(ficheFacteurXVIIIRawValue.autreOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      adresse: new FormControl(ficheFacteurXVIIIRawValue.adresse, {
        validators: [Validators.maxLength(25)],
      }),
      telephone: new FormControl(ficheFacteurXVIIIRawValue.telephone, {
        validators: [Validators.maxLength(20)],
      }),
      prenomPere: new FormControl(ficheFacteurXVIIIRawValue.prenomPere, {
        validators: [Validators.maxLength(25)],
      }),
      professionPere: new FormControl(ficheFacteurXVIIIRawValue.professionPere, {
        validators: [Validators.maxLength(25)],
      }),
      nomPrenomMere: new FormControl(ficheFacteurXVIIIRawValue.nomPrenomMere, {
        validators: [Validators.maxLength(25)],
      }),
      professionMere: new FormControl(ficheFacteurXVIIIRawValue.professionMere, {
        validators: [Validators.maxLength(25)],
      }),
      medecinTraitant: new FormControl(ficheFacteurXVIIIRawValue.medecinTraitant, {
        validators: [Validators.maxLength(25)],
      }),
      serviceOrigine: new FormControl(ficheFacteurXVIIIRawValue.serviceOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      biologisteResponsableDg: new FormControl(ficheFacteurXVIIIRawValue.biologisteResponsableDg, {
        validators: [Validators.maxLength(25)],
      }),
      diagnostic: new FormControl(ficheFacteurXVIIIRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(ficheFacteurXVIIIRawValue.autreDiagnostic, {
        validators: [Validators.maxLength(25)],
      }),
      anneeDiagnostic: new FormControl(ficheFacteurXVIIIRawValue.anneeDiagnostic),
      consentementRegistre: new FormControl(ficheFacteurXVIIIRawValue.consentementRegistre),
      dateEnregistrementRegistre: new FormControl(ficheFacteurXVIIIRawValue.dateEnregistrementRegistre),
      parentsConsanguins: new FormControl(ficheFacteurXVIIIRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheFacteurXVIIIRawValue.degreParenteConsanguins),
      casSimilaires: new FormControl(ficheFacteurXVIIIRawValue.casSimilaires),
      nombreCas: new FormControl(ficheFacteurXVIIIRawValue.nombreCas),
      cas1Numero: new FormControl(ficheFacteurXVIIIRawValue.cas1Numero),
      lienParenteCas1: new FormControl(ficheFacteurXVIIIRawValue.lienParenteCas1),
      degreParenteCas1: new FormControl(ficheFacteurXVIIIRawValue.degreParenteCas1, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas1: new FormControl(ficheFacteurXVIIIRawValue.typeHemorragieCas1, {
        validators: [Validators.maxLength(255)],
      }),
      cas2Numero: new FormControl(ficheFacteurXVIIIRawValue.cas2Numero),
      lienParenteCas2: new FormControl(ficheFacteurXVIIIRawValue.lienParenteCas2),
      degreParenteCas2: new FormControl(ficheFacteurXVIIIRawValue.degreParenteCas2, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas2: new FormControl(ficheFacteurXVIIIRawValue.typeHemorragieCas2, {
        validators: [Validators.maxLength(255)],
      }),
      cas3Numero: new FormControl(ficheFacteurXVIIIRawValue.cas3Numero),
      lienParenteCas3: new FormControl(ficheFacteurXVIIIRawValue.lienParenteCas3),
      degreParenteCas3: new FormControl(ficheFacteurXVIIIRawValue.degreParenteCas3, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas3: new FormControl(ficheFacteurXVIIIRawValue.typeHemorragieCas3, {
        validators: [Validators.maxLength(255)],
      }),
      decesSyndromeHemorragique: new FormControl(ficheFacteurXVIIIRawValue.decesSyndromeHemorragique),
      nombreDeces: new FormControl(ficheFacteurXVIIIRawValue.nombreDeces),
      deces1Numero: new FormControl(ficheFacteurXVIIIRawValue.deces1Numero),
      lienParenteDeces1: new FormControl(ficheFacteurXVIIIRawValue.lienParenteDeces1),
      causeDeces1: new FormControl(ficheFacteurXVIIIRawValue.causeDeces1, {
        validators: [Validators.maxLength(255)],
      }),
      deces2Numero: new FormControl(ficheFacteurXVIIIRawValue.deces2Numero),
      lienParenteDeces2: new FormControl(ficheFacteurXVIIIRawValue.lienParenteDeces2),
      causeDeces2: new FormControl(ficheFacteurXVIIIRawValue.causeDeces2, {
        validators: [Validators.maxLength(255)],
      }),
      deces3Numero: new FormControl(ficheFacteurXVIIIRawValue.deces3Numero),
      lienParenteDeces3: new FormControl(ficheFacteurXVIIIRawValue.lienParenteDeces3),
      causeDeces3: new FormControl(ficheFacteurXVIIIRawValue.causeDeces3, {
        validators: [Validators.maxLength(255)],
      }),
      enqueteFamiliale: new FormControl(ficheFacteurXVIIIRawValue.enqueteFamiliale),
      circHemorragique: new FormControl(ficheFacteurXVIIIRawValue.circHemorragique),
      precisionsCircHemorragique: new FormControl(ficheFacteurXVIIIRawValue.precisionsCircHemorragique),
      decouverteFortuitePreop: new FormControl(ficheFacteurXVIIIRawValue.decouverteFortuitePreop),
      precisionsDecouverteFortuite: new FormControl(ficheFacteurXVIIIRawValue.precisionsDecouverteFortuite),
      jamaisSaignement: new FormControl(ficheFacteurXVIIIRawValue.jamaisSaignement),
      agePremierSigneJours: new FormControl(ficheFacteurXVIIIRawValue.agePremierSigneJours),
      agePremierSigneMois: new FormControl(ficheFacteurXVIIIRawValue.agePremierSigneMois),
      agePremierSigneAnnees: new FormControl(ficheFacteurXVIIIRawValue.agePremierSigneAnnees),
      symptomeEcchymose: new FormControl(ficheFacteurXVIIIRawValue.symptomeEcchymose),
      symptomeEpistaxis: new FormControl(ficheFacteurXVIIIRawValue.symptomeEpistaxis),
      symptomeGingivorragies: new FormControl(ficheFacteurXVIIIRawValue.symptomeGingivorragies),
      symptomeMetrorragies: new FormControl(ficheFacteurXVIIIRawValue.symptomeMetrorragies),
      symptomeMelena: new FormControl(ficheFacteurXVIIIRawValue.symptomeMelena),
      symptomeHemoptysie: new FormControl(ficheFacteurXVIIIRawValue.symptomeHemoptysie),
      symptomePostAmygdalectomie: new FormControl(ficheFacteurXVIIIRawValue.symptomePostAmygdalectomie),
      symptomePostAvulsionDentaire: new FormControl(ficheFacteurXVIIIRawValue.symptomePostAvulsionDentaire),
      symptomeChuteCordon: new FormControl(ficheFacteurXVIIIRawValue.symptomeChuteCordon),
      symptomeAutre: new FormControl(ficheFacteurXVIIIRawValue.symptomeAutre),
      symptomeAutreDescription: new FormControl(ficheFacteurXVIIIRawValue.symptomeAutreDescription),
      datePremiereConsultation: new FormControl(ficheFacteurXVIIIRawValue.datePremiereConsultation),
      agePremiereConsultationJours: new FormControl(ficheFacteurXVIIIRawValue.agePremiereConsultationJours),
      agePremiereConsultationMois: new FormControl(ficheFacteurXVIIIRawValue.agePremiereConsultationMois),
      agePremiereConsultationAnnees: new FormControl(ficheFacteurXVIIIRawValue.agePremiereConsultationAnnees),
      ageDiagnosticJours: new FormControl(ficheFacteurXVIIIRawValue.ageDiagnosticJours),
      ageDiagnosticMois: new FormControl(ficheFacteurXVIIIRawValue.ageDiagnosticMois),
      ageDiagnosticAnnees: new FormControl(ficheFacteurXVIIIRawValue.ageDiagnosticAnnees),
      hb: new FormControl(ficheFacteurXVIIIRawValue.hb),
      ht: new FormControl(ficheFacteurXVIIIRawValue.ht),
      plaquettes: new FormControl(ficheFacteurXVIIIRawValue.plaquettes),
      colEpi: new FormControl(ficheFacteurXVIIIRawValue.colEpi),
      colAdp: new FormControl(ficheFacteurXVIIIRawValue.colAdp),
      tp: new FormControl(ficheFacteurXVIIIRawValue.tp),
      tpMPlusT: new FormControl(ficheFacteurXVIIIRawValue.tpMPlusT),
      fibrinogene: new FormControl(ficheFacteurXVIIIRawValue.fibrinogene),
      tcaMT: new FormControl(ficheFacteurXVIIIRawValue.tcaMT),
      tcaMPlusT: new FormControl(ficheFacteurXVIIIRawValue.tcaMPlusT),
      tcaTemoin: new FormControl(ficheFacteurXVIIIRawValue.tcaTemoin),
      facteurDeficitaireAutre: new FormControl(ficheFacteurXVIIIRawValue.facteurDeficitaireAutre),
      tauxFacteurDeficitaireAutre: new FormControl(ficheFacteurXVIIIRawValue.tauxFacteurDeficitaireAutre),
      facteurSupplementaire1: new FormControl(ficheFacteurXVIIIRawValue.facteurSupplementaire1),
      tauxSupplementaire1: new FormControl(ficheFacteurXVIIIRawValue.tauxSupplementaire1),
      facteurSupplementaire2: new FormControl(ficheFacteurXVIIIRawValue.facteurSupplementaire2),
      tauxSupplementaire2: new FormControl(ficheFacteurXVIIIRawValue.tauxSupplementaire2),
      facteurSupplementaire3: new FormControl(ficheFacteurXVIIIRawValue.facteurSupplementaire3),
      tauxSupplementaire3: new FormControl(ficheFacteurXVIIIRawValue.tauxSupplementaire3),
      confirmationMoleculaire: new FormControl(ficheFacteurXVIIIRawValue.confirmationMoleculaire),
      mutationPresente: new FormControl(ficheFacteurXVIIIRawValue.mutationPresente),
      mutationNom: new FormControl(ficheFacteurXVIIIRawValue.mutationNom),
      mutationType: new FormControl(ficheFacteurXVIIIRawValue.mutationType),
      mutation2Nom: new FormControl(ficheFacteurXVIIIRawValue.mutation2Nom),
      serologieVHB: new FormControl(ficheFacteurXVIIIRawValue.serologieVHB),
      serologieVHC: new FormControl(ficheFacteurXVIIIRawValue.serologieVHC),
      serologieVIH: new FormControl(ficheFacteurXVIIIRawValue.serologieVIH),
      epistaxis: new FormControl(ficheFacteurXVIIIRawValue.epistaxis),
      frequenceEpistaxis: new FormControl(ficheFacteurXVIIIRawValue.frequenceEpistaxis),
      gingivorragies: new FormControl(ficheFacteurXVIIIRawValue.gingivorragies),
      typeGingivorragies: new FormControl(ficheFacteurXVIIIRawValue.typeGingivorragies),
      frequenceGingivorragies: new FormControl(ficheFacteurXVIIIRawValue.frequenceGingivorragies),
      ecchymose: new FormControl(ficheFacteurXVIIIRawValue.ecchymose),
      typeEcchymose: new FormControl(ficheFacteurXVIIIRawValue.typeEcchymose),
      frequenceEcchymose: new FormControl(ficheFacteurXVIIIRawValue.frequenceEcchymose),
      menorragie: new FormControl(ficheFacteurXVIIIRawValue.menorragie),
      frequenceMenorragie: new FormControl(ficheFacteurXVIIIRawValue.frequenceMenorragie),
      hematemese: new FormControl(ficheFacteurXVIIIRawValue.hematemese),
      frequenceHematemese: new FormControl(ficheFacteurXVIIIRawValue.frequenceHematemese),
      rectorragie: new FormControl(ficheFacteurXVIIIRawValue.rectorragie),
      frequenceRectorragie: new FormControl(ficheFacteurXVIIIRawValue.frequenceRectorragie),
      hematurie: new FormControl(ficheFacteurXVIIIRawValue.hematurie),
      frequenceHematurie: new FormControl(ficheFacteurXVIIIRawValue.frequenceHematurie),
      hematomeSousCutane: new FormControl(ficheFacteurXVIIIRawValue.hematomeSousCutane),
      typeHematomeSousCutane: new FormControl(ficheFacteurXVIIIRawValue.typeHematomeSousCutane),
      frequenceHematomeSousCutane: new FormControl(ficheFacteurXVIIIRawValue.frequenceHematomeSousCutane),
      hematomeMusculaire: new FormControl(ficheFacteurXVIIIRawValue.hematomeMusculaire),
      siegeHematomeMusculaire: new FormControl(ficheFacteurXVIIIRawValue.siegeHematomeMusculaire, {
        validators: [Validators.maxLength(255)],
      }),
      typeHematomeMusculaire: new FormControl(ficheFacteurXVIIIRawValue.typeHematomeMusculaire),
      frequenceHematomeMusculaire: new FormControl(ficheFacteurXVIIIRawValue.frequenceHematomeMusculaire),
      hemarthrose: new FormControl(ficheFacteurXVIIIRawValue.hemarthrose),
      typeHemarthrose: new FormControl(ficheFacteurXVIIIRawValue.typeHemarthrose),
      frequenceHemarthrose: new FormControl(ficheFacteurXVIIIRawValue.frequenceHemarthrose),
      saignementSNC: new FormControl(ficheFacteurXVIIIRawValue.saignementSNC),
      typeSaignementSNC: new FormControl(ficheFacteurXVIIIRawValue.typeSaignementSNC),
      frequenceSaignementSNC: new FormControl(ficheFacteurXVIIIRawValue.frequenceSaignementSNC),
      saignementChuteCordon: new FormControl(ficheFacteurXVIIIRawValue.saignementChuteCordon),
      frequenceSaignementChuteCordon: new FormControl(ficheFacteurXVIIIRawValue.frequenceSaignementChuteCordon),
      retardCicatrisationPlaies: new FormControl(ficheFacteurXVIIIRawValue.retardCicatrisationPlaies),
      frequenceRetardCicatrisationPlaies: new FormControl(ficheFacteurXVIIIRawValue.frequenceRetardCicatrisationPlaies),
      autresManifestations: new FormControl(ficheFacteurXVIIIRawValue.autresManifestations),
      faussesCouchesSpontaneesRecurrence: new FormControl(ficheFacteurXVIIIRawValue.faussesCouchesSpontaneesRecurrence),
      frequenceFaussesCouches: new FormControl(ficheFacteurXVIIIRawValue.frequenceFaussesCouches),
      accidentsThrombotiques: new FormControl(ficheFacteurXVIIIRawValue.accidentsThrombotiques),
      thromboseVeineuse: new FormControl(ficheFacteurXVIIIRawValue.thromboseVeineuse),
      thromboseArterielle: new FormControl(ficheFacteurXVIIIRawValue.thromboseArterielle),
      frequenceAccidentsThrombotiques: new FormControl(ficheFacteurXVIIIRawValue.frequenceAccidentsThrombotiques),
      hemoperitoine: new FormControl(ficheFacteurXVIIIRawValue.hemoperitoine),
      frequenceHemoperitoine: new FormControl(ficheFacteurXVIIIRawValue.frequenceHemoperitoine),
      ruptureKystesHemorragiques: new FormControl(ficheFacteurXVIIIRawValue.ruptureKystesHemorragiques),
      frequenceRuptureKystes: new FormControl(ficheFacteurXVIIIRawValue.frequenceRuptureKystes),
      hemorragieGrave: new FormControl(ficheFacteurXVIIIRawValue.hemorragieGrave),
      frequenceHemorragieGrave: new FormControl(ficheFacteurXVIIIRawValue.frequenceHemorragieGrave),
      graviteDeglobulisation: new FormControl(ficheFacteurXVIIIRawValue.graviteDeglobulisation),
      graviteEtatDeChoc: new FormControl(ficheFacteurXVIIIRawValue.graviteEtatDeChoc),
      graviteParLocalisation: new FormControl(ficheFacteurXVIIIRawValue.graviteParLocalisation),
      siegeGraviteLocalisation: new FormControl(ficheFacteurXVIIIRawValue.siegeGraviteLocalisation, {
        validators: [Validators.maxLength(255)],
      }),
      chirurgiePrepare: new FormControl(ficheFacteurXVIIIRawValue.chirurgiePrepare),
      chirurgieSuiteHemorragique: new FormControl(ficheFacteurXVIIIRawValue.chirurgieSuiteHemorragique),
      circoncisionPrepare: new FormControl(ficheFacteurXVIIIRawValue.circoncisionPrepare),
      circoncisionSuiteHemorragique: new FormControl(ficheFacteurXVIIIRawValue.circoncisionSuiteHemorragique),
      ageCirconcision: new FormControl(ficheFacteurXVIIIRawValue.ageCirconcision),
      traitementPFC: new FormControl(ficheFacteurXVIIIRawValue.traitementPFC),
      traitementCryoprecipite: new FormControl(ficheFacteurXVIIIRawValue.traitementCryoprecipite),
      traitementConcentreFacteurXI: new FormControl(ficheFacteurXVIIIRawValue.traitementConcentreFacteurXI),
      traitementCulotsPlaquettaires: new FormControl(ficheFacteurXVIIIRawValue.traitementCulotsPlaquettaires),
      traitementDicynone: new FormControl(ficheFacteurXVIIIRawValue.traitementDicynone),
      traitementExacyl: new FormControl(ficheFacteurXVIIIRawValue.traitementExacyl),
      traitementMartial: new FormControl(ficheFacteurXVIIIRawValue.traitementMartial),
      traitementOestroprogestatif: new FormControl(ficheFacteurXVIIIRawValue.traitementOestroprogestatif),
      transfusionCGR: new FormControl(ficheFacteurXVIIIRawValue.transfusionCGR),
      alloImmunisation: new FormControl(ficheFacteurXVIIIRawValue.alloImmunisation),
      inhibiteurNeutralisant: new FormControl(ficheFacteurXVIIIRawValue.inhibiteurNeutralisant),
      titreInhibiteurUB: new FormControl(ficheFacteurXVIIIRawValue.titreInhibiteurUB),
      inhibiteurNonNeutralisant: new FormControl(ficheFacteurXVIIIRawValue.inhibiteurNonNeutralisant),
      infectionVIH: new FormControl(ficheFacteurXVIIIRawValue.infectionVIH),
      infectionVHC: new FormControl(ficheFacteurXVIIIRawValue.infectionVHC),
      infectionVHB: new FormControl(ficheFacteurXVIIIRawValue.infectionVHB),
      thrombose: new FormControl(ficheFacteurXVIIIRawValue.thrombose),
      reactionAllergique: new FormControl(ficheFacteurXVIIIRawValue.reactionAllergique),
      autresComplications: new FormControl(ficheFacteurXVIIIRawValue.autresComplications, {
        validators: [Validators.maxLength(255)],
      }),
      adherenceTherapeutique: new FormControl(ficheFacteurXVIIIRawValue.adherenceTherapeutique),
      causeNonAdherence: new FormControl(ficheFacteurXVIIIRawValue.causeNonAdherence, {
        validators: [Validators.maxLength(255)],
      }),
      statutSocial: new FormControl(ficheFacteurXVIIIRawValue.statutSocial),
      marie: new FormControl(ficheFacteurXVIIIRawValue.marie),
      enfants: new FormControl(ficheFacteurXVIIIRawValue.enfants),
      activitePhysique: new FormControl(ficheFacteurXVIIIRawValue.activitePhysique),
      typeActivitePhysique: new FormControl(ficheFacteurXVIIIRawValue.typeActivitePhysique, {
        validators: [Validators.maxLength(255)],
      }),
      sequelles: new FormControl(ficheFacteurXVIIIRawValue.sequelles),
      typeSequelles: new FormControl(ficheFacteurXVIIIRawValue.typeSequelles, {
        validators: [Validators.maxLength(255)],
      }),
      patientDecede: new FormControl(ficheFacteurXVIIIRawValue.patientDecede),
      dateDeces: new FormControl(ficheFacteurXVIIIRawValue.dateDeces),
      ageDeces: new FormControl(ficheFacteurXVIIIRawValue.ageDeces),
      causeDeces: new FormControl(ficheFacteurXVIIIRawValue.causeDeces),
    });
  }

  getFicheFacteurXVIII(form: FicheFacteurXVIIIFormGroup): IFicheFacteurXVIII | NewFicheFacteurXVIII {
    return form.getRawValue() as IFicheFacteurXVIII | NewFicheFacteurXVIII;
  }

  resetForm(form: FicheFacteurXVIIIFormGroup, ficheFacteurXVIII: FicheFacteurXVIIIFormGroupInput): void {
    const ficheFacteurXVIIIRawValue = { ...this.getFormDefaults(), ...ficheFacteurXVIII };
    form.reset(
      {
        ...ficheFacteurXVIIIRawValue,
        id: { value: ficheFacteurXVIIIRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheFacteurXVIIIFormDefaults {
    return {
      id: null,
      enqueteFamiliale: false,
      circHemorragique: false,
      decouverteFortuitePreop: false,
      symptomeEcchymose: false,
      symptomeEpistaxis: false,
      symptomeGingivorragies: false,
      symptomeMetrorragies: false,
      symptomeMelena: false,
      symptomeHemoptysie: false,
      symptomePostAmygdalectomie: false,
      symptomePostAvulsionDentaire: false,
      symptomeChuteCordon: false,
      symptomeAutre: false,
      mutationPresente: false,
      epistaxis: false,
      gingivorragies: false,
      ecchymose: false,
      menorragie: false,
      hematemese: false,
      rectorragie: false,
      hematurie: false,
      hematomeSousCutane: false,
      hematomeMusculaire: false,
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
