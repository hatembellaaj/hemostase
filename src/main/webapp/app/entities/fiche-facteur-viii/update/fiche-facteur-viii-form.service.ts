import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFicheFacteurVIII, NewFicheFacteurVIII } from '../fiche-facteur-viii.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFicheFacteurVIII for edit and NewFicheFacteurVIIIFormGroupInput for create.
 */
type FicheFacteurVIIIFormGroupInput = IFicheFacteurVIII | PartialWithRequiredKeyOf<NewFicheFacteurVIII>;

type FicheFacteurVIIIFormDefaults = Pick<
  NewFicheFacteurVIII,
  | 'id'
  | 'decouverteFortuite'
  | 'enqueteFamilialeBool'
  | 'explorationSyndromeHemorragique'
  | 'techniqueChromogenique'
  | 'techniqueSolubiliteCaillot'
  | 'mutationPresence'
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
  | 'ruptureKystesHemorragiques'
  | 'hemorragiesPostChirurgicales'
  | 'hemoperitone'
  | 'accidentsThrombotiques'
  | 'hemorragieGrave'
  | 'inhibiteurNeutralisant'
  | 'inhibiteurNonNeutralisant'
  | 'hemorragieSnc'
  | 'hemorragieAutreQueSnc'
  | 'hiv'
  | 'autreInfection'
  | 'evenementThromboembolique'
  | 'cancer'
  | 'covid19'
  | 'inconnue'
  | 'autreEvenement'
>;

type FicheFacteurVIIIFormGroupContent = {
  id: FormControl<IFicheFacteurVIII['id'] | NewFicheFacteurVIII['id']>;
  dossierNumber: FormControl<IFicheFacteurVIII['dossierNumber']>;
  ordreNumber: FormControl<IFicheFacteurVIII['ordreNumber']>;
  indexNumber: FormControl<IFicheFacteurVIII['indexNumber']>;
  prenom: FormControl<IFicheFacteurVIII['prenom']>;
  nom: FormControl<IFicheFacteurVIII['nom']>;
  nomJeuneFille: FormControl<IFicheFacteurVIII['nomJeuneFille']>;
  profession: FormControl<IFicheFacteurVIII['profession']>;
  etatCivil: FormControl<IFicheFacteurVIII['etatCivil']>;
  serviceClinique: FormControl<IFicheFacteurVIII['serviceClinique']>;
  autreServiceClinique: FormControl<IFicheFacteurVIII['autreServiceClinique']>;
  couvertureSociale: FormControl<IFicheFacteurVIII['couvertureSociale']>;
  sexe: FormControl<IFicheFacteurVIII['sexe']>;
  dateNaissance: FormControl<IFicheFacteurVIII['dateNaissance']>;
  ageActuel: FormControl<IFicheFacteurVIII['ageActuel']>;
  origine: FormControl<IFicheFacteurVIII['origine']>;
  autreOrigine: FormControl<IFicheFacteurVIII['autreOrigine']>;
  adresse: FormControl<IFicheFacteurVIII['adresse']>;
  telephone: FormControl<IFicheFacteurVIII['telephone']>;
  prenomPere: FormControl<IFicheFacteurVIII['prenomPere']>;
  professionPere: FormControl<IFicheFacteurVIII['professionPere']>;
  nomPrenomMere: FormControl<IFicheFacteurVIII['nomPrenomMere']>;
  professionMere: FormControl<IFicheFacteurVIII['professionMere']>;
  medecinTraitant: FormControl<IFicheFacteurVIII['medecinTraitant']>;
  serviceOrigine: FormControl<IFicheFacteurVIII['serviceOrigine']>;
  biologisteResponsableDg: FormControl<IFicheFacteurVIII['biologisteResponsableDg']>;
  diagnostic: FormControl<IFicheFacteurVIII['diagnostic']>;
  autreDiagnostic: FormControl<IFicheFacteurVIII['autreDiagnostic']>;
  anneeDiagnostic: FormControl<IFicheFacteurVIII['anneeDiagnostic']>;
  consentementRegistre: FormControl<IFicheFacteurVIII['consentementRegistre']>;
  dateEnregistrementRegistre: FormControl<IFicheFacteurVIII['dateEnregistrementRegistre']>;
  parentsConsanguins: FormControl<IFicheFacteurVIII['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFicheFacteurVIII['degreParenteConsanguins']>;
  casSimilaires: FormControl<IFicheFacteurVIII['casSimilaires']>;
  nombreCas: FormControl<IFicheFacteurVIII['nombreCas']>;
  cas1Numero: FormControl<IFicheFacteurVIII['cas1Numero']>;
  lienParenteCas1: FormControl<IFicheFacteurVIII['lienParenteCas1']>;
  degreParenteCas1: FormControl<IFicheFacteurVIII['degreParenteCas1']>;
  typeHemorragieCas1: FormControl<IFicheFacteurVIII['typeHemorragieCas1']>;
  cas2Numero: FormControl<IFicheFacteurVIII['cas2Numero']>;
  lienParenteCas2: FormControl<IFicheFacteurVIII['lienParenteCas2']>;
  degreParenteCas2: FormControl<IFicheFacteurVIII['degreParenteCas2']>;
  typeHemorragieCas2: FormControl<IFicheFacteurVIII['typeHemorragieCas2']>;
  cas3Numero: FormControl<IFicheFacteurVIII['cas3Numero']>;
  lienParenteCas3: FormControl<IFicheFacteurVIII['lienParenteCas3']>;
  degreParenteCas3: FormControl<IFicheFacteurVIII['degreParenteCas3']>;
  typeHemorragieCas3: FormControl<IFicheFacteurVIII['typeHemorragieCas3']>;
  decesSyndromeHemorragique: FormControl<IFicheFacteurVIII['decesSyndromeHemorragique']>;
  nombreDeces: FormControl<IFicheFacteurVIII['nombreDeces']>;
  deces1Numero: FormControl<IFicheFacteurVIII['deces1Numero']>;
  lienParenteDeces1: FormControl<IFicheFacteurVIII['lienParenteDeces1']>;
  causeDeces1: FormControl<IFicheFacteurVIII['causeDeces1']>;
  deces2Numero: FormControl<IFicheFacteurVIII['deces2Numero']>;
  lienParenteDeces2: FormControl<IFicheFacteurVIII['lienParenteDeces2']>;
  causeDeces2: FormControl<IFicheFacteurVIII['causeDeces2']>;
  deces3Numero: FormControl<IFicheFacteurVIII['deces3Numero']>;
  lienParenteDeces3: FormControl<IFicheFacteurVIII['lienParenteDeces3']>;
  causeDeces3: FormControl<IFicheFacteurVIII['causeDeces3']>;
  enqueteFamiliale: FormControl<IFicheFacteurVIII['enqueteFamiliale']>;
  decouverteFortuite: FormControl<IFicheFacteurVIII['decouverteFortuite']>;
  enqueteFamilialeBool: FormControl<IFicheFacteurVIII['enqueteFamilialeBool']>;
  explorationSyndromeHemorragique: FormControl<IFicheFacteurVIII['explorationSyndromeHemorragique']>;
  typeExplorationSyndromeHemorragique: FormControl<IFicheFacteurVIII['typeExplorationSyndromeHemorragique']>;
  datePremierSigneClinique: FormControl<IFicheFacteurVIII['datePremierSigneClinique']>;
  ageDiagnosticJour: FormControl<IFicheFacteurVIII['ageDiagnosticJour']>;
  ageDiagnosticMois: FormControl<IFicheFacteurVIII['ageDiagnosticMois']>;
  ageDiagnosticAnnee: FormControl<IFicheFacteurVIII['ageDiagnosticAnnee']>;
  tp: FormControl<IFicheFacteurVIII['tp']>;
  tpMT: FormControl<IFicheFacteurVIII['tpMT']>;
  tca: FormControl<IFicheFacteurVIII['tca']>;
  tcaMT: FormControl<IFicheFacteurVIII['tcaMT']>;
  fibrinogene: FormControl<IFicheFacteurVIII['fibrinogene']>;
  ts: FormControl<IFicheFacteurVIII['ts']>;
  tsFait: FormControl<IFicheFacteurVIII['tsFait']>;
  toPfa: FormControl<IFicheFacteurVIII['toPfa']>;
  toPfa200ColEpi: FormControl<IFicheFacteurVIII['toPfa200ColEpi']>;
  toPfa200ColAdp: FormControl<IFicheFacteurVIII['toPfa200ColAdp']>;
  tauxDosageFacteurXIII: FormControl<IFicheFacteurVIII['tauxDosageFacteurXIII']>;
  hb: FormControl<IFicheFacteurVIII['hb']>;
  ht: FormControl<IFicheFacteurVIII['ht']>;
  plaquettes: FormControl<IFicheFacteurVIII['plaquettes']>;
  techniqueChromogenique: FormControl<IFicheFacteurVIII['techniqueChromogenique']>;
  tauxTechniqueChromogenique: FormControl<IFicheFacteurVIII['tauxTechniqueChromogenique']>;
  techniqueSolubiliteCaillot: FormControl<IFicheFacteurVIII['techniqueSolubiliteCaillot']>;
  tauxTechniqueSolubiliteCaillot: FormControl<IFicheFacteurVIII['tauxTechniqueSolubiliteCaillot']>;
  confirmationMoleculaire: FormControl<IFicheFacteurVIII['confirmationMoleculaire']>;
  mutationPresence: FormControl<IFicheFacteurVIII['mutationPresence']>;
  mutationType: FormControl<IFicheFacteurVIII['mutationType']>;
  epistaxis: FormControl<IFicheFacteurVIII['epistaxis']>;
  frequenceEpistaxis: FormControl<IFicheFacteurVIII['frequenceEpistaxis']>;
  gingivorragies: FormControl<IFicheFacteurVIII['gingivorragies']>;
  typeGingivorragies: FormControl<IFicheFacteurVIII['typeGingivorragies']>;
  frequenceGingivorragies: FormControl<IFicheFacteurVIII['frequenceGingivorragies']>;
  ecchymose: FormControl<IFicheFacteurVIII['ecchymose']>;
  typeEcchymose: FormControl<IFicheFacteurVIII['typeEcchymose']>;
  frequenceEcchymose: FormControl<IFicheFacteurVIII['frequenceEcchymose']>;
  menorragie: FormControl<IFicheFacteurVIII['menorragie']>;
  frequenceMenorragie: FormControl<IFicheFacteurVIII['frequenceMenorragie']>;
  hematemese: FormControl<IFicheFacteurVIII['hematemese']>;
  frequenceHematemese: FormControl<IFicheFacteurVIII['frequenceHematemese']>;
  rectorragie: FormControl<IFicheFacteurVIII['rectorragie']>;
  frequenceRectorragie: FormControl<IFicheFacteurVIII['frequenceRectorragie']>;
  hematurie: FormControl<IFicheFacteurVIII['hematurie']>;
  frequenceHematurie: FormControl<IFicheFacteurVIII['frequenceHematurie']>;
  hematomeSousCutane: FormControl<IFicheFacteurVIII['hematomeSousCutane']>;
  typeHematomeSousCutane: FormControl<IFicheFacteurVIII['typeHematomeSousCutane']>;
  frequenceHematomeSousCutane: FormControl<IFicheFacteurVIII['frequenceHematomeSousCutane']>;
  hematomeMusculaire: FormControl<IFicheFacteurVIII['hematomeMusculaire']>;
  siegeHematomeMusculaire: FormControl<IFicheFacteurVIII['siegeHematomeMusculaire']>;
  typeHematomeMusculaire: FormControl<IFicheFacteurVIII['typeHematomeMusculaire']>;
  frequenceHematomeMusculaire: FormControl<IFicheFacteurVIII['frequenceHematomeMusculaire']>;
  hemarthrose: FormControl<IFicheFacteurVIII['hemarthrose']>;
  typeHemarthrose: FormControl<IFicheFacteurVIII['typeHemarthrose']>;
  frequenceHemarthrose: FormControl<IFicheFacteurVIII['frequenceHemarthrose']>;
  saignementSNC: FormControl<IFicheFacteurVIII['saignementSNC']>;
  typeSaignementSNC: FormControl<IFicheFacteurVIII['typeSaignementSNC']>;
  frequenceSaignementSNC: FormControl<IFicheFacteurVIII['frequenceSaignementSNC']>;
  ruptureKystesHemorragiques: FormControl<IFicheFacteurVIII['ruptureKystesHemorragiques']>;
  hemorragiesPostChirurgicales: FormControl<IFicheFacteurVIII['hemorragiesPostChirurgicales']>;
  hemoperitone: FormControl<IFicheFacteurVIII['hemoperitone']>;
  accidentsThrombotiques: FormControl<IFicheFacteurVIII['accidentsThrombotiques']>;
  typeAccidentsThrombotiques: FormControl<IFicheFacteurVIII['typeAccidentsThrombotiques']>;
  hemorragieGrave: FormControl<IFicheFacteurVIII['hemorragieGrave']>;
  typeHemorragieGrave: FormControl<IFicheFacteurVIII['typeHemorragieGrave']>;
  chirurgiePrepare: FormControl<IFicheFacteurVIII['chirurgiePrepare']>;
  suiteOperatoireHemorragique: FormControl<IFicheFacteurVIII['suiteOperatoireHemorragique']>;
  circoncisionPrepare: FormControl<IFicheFacteurVIII['circoncisionPrepare']>;
  suiteOperatoireCirconcision: FormControl<IFicheFacteurVIII['suiteOperatoireCirconcision']>;
  ageCirconcision: FormControl<IFicheFacteurVIII['ageCirconcision']>;
  facteurVIIIRecombinant: FormControl<IFicheFacteurVIII['facteurVIIIRecombinant']>;
  plasmaFraisCongele: FormControl<IFicheFacteurVIII['plasmaFraisCongele']>;
  cryoprecipite: FormControl<IFicheFacteurVIII['cryoprecipite']>;
  culotsPlaquettaires: FormControl<IFicheFacteurVIII['culotsPlaquettaires']>;
  dicynone: FormControl<IFicheFacteurVIII['dicynone']>;
  exacyl: FormControl<IFicheFacteurVIII['exacyl']>;
  traitementMartial: FormControl<IFicheFacteurVIII['traitementMartial']>;
  traitementOestroprogestatif: FormControl<IFicheFacteurVIII['traitementOestroprogestatif']>;
  transfusionCGR: FormControl<IFicheFacteurVIII['transfusionCGR']>;
  inhibiteurs: FormControl<IFicheFacteurVIII['inhibiteurs']>;
  inhibiteurNeutralisant: FormControl<IFicheFacteurVIII['inhibiteurNeutralisant']>;
  titreUB: FormControl<IFicheFacteurVIII['titreUB']>;
  inhibiteurNonNeutralisant: FormControl<IFicheFacteurVIII['inhibiteurNonNeutralisant']>;
  infectionsVirales: FormControl<IFicheFacteurVIII['infectionsVirales']>;
  thrombose: FormControl<IFicheFacteurVIII['thrombose']>;
  reactionAllergique: FormControl<IFicheFacteurVIII['reactionAllergique']>;
  alloImmunisation: FormControl<IFicheFacteurVIII['alloImmunisation']>;
  adhesionTherapeutique: FormControl<IFicheFacteurVIII['adhesionTherapeutique']>;
  causeNonAdhesion: FormControl<IFicheFacteurVIII['causeNonAdhesion']>;
  vieSociale: FormControl<IFicheFacteurVIII['vieSociale']>;
  mariage: FormControl<IFicheFacteurVIII['mariage']>;
  enfants: FormControl<IFicheFacteurVIII['enfants']>;
  activitePhysique: FormControl<IFicheFacteurVIII['activitePhysique']>;
  typeActivitePhysique: FormControl<IFicheFacteurVIII['typeActivitePhysique']>;
  sequelles: FormControl<IFicheFacteurVIII['sequelles']>;
  typeSequelles: FormControl<IFicheFacteurVIII['typeSequelles']>;
  patientDecede: FormControl<IFicheFacteurVIII['patientDecede']>;
  dateDeces: FormControl<IFicheFacteurVIII['dateDeces']>;
  ageDeces: FormControl<IFicheFacteurVIII['ageDeces']>;
  hemorragieSnc: FormControl<IFicheFacteurVIII['hemorragieSnc']>;
  hemorragieAutreQueSnc: FormControl<IFicheFacteurVIII['hemorragieAutreQueSnc']>;
  hiv: FormControl<IFicheFacteurVIII['hiv']>;
  autreInfection: FormControl<IFicheFacteurVIII['autreInfection']>;
  evenementThromboembolique: FormControl<IFicheFacteurVIII['evenementThromboembolique']>;
  cancer: FormControl<IFicheFacteurVIII['cancer']>;
  covid19: FormControl<IFicheFacteurVIII['covid19']>;
  inconnue: FormControl<IFicheFacteurVIII['inconnue']>;
  autreEvenement: FormControl<IFicheFacteurVIII['autreEvenement']>;
  autreEvenementDetails: FormControl<IFicheFacteurVIII['autreEvenementDetails']>;
};

export type FicheFacteurVIIIFormGroup = FormGroup<FicheFacteurVIIIFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurVIIIFormService {
  createFicheFacteurVIIIFormGroup(ficheFacteurVIII: FicheFacteurVIIIFormGroupInput = { id: null }): FicheFacteurVIIIFormGroup {
    const ficheFacteurVIIIRawValue = {
      ...this.getFormDefaults(),
      ...ficheFacteurVIII,
    };
    return new FormGroup<FicheFacteurVIIIFormGroupContent>({
      id: new FormControl(
        { value: ficheFacteurVIIIRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dossierNumber: new FormControl(ficheFacteurVIIIRawValue.dossierNumber),
      ordreNumber: new FormControl(ficheFacteurVIIIRawValue.ordreNumber, {
        validators: [Validators.maxLength(25)],
      }),
      indexNumber: new FormControl(ficheFacteurVIIIRawValue.indexNumber, {
        validators: [Validators.maxLength(25)],
      }),
      prenom: new FormControl(ficheFacteurVIIIRawValue.prenom, {
        validators: [Validators.maxLength(25)],
      }),
      nom: new FormControl(ficheFacteurVIIIRawValue.nom, {
        validators: [Validators.maxLength(25)],
      }),
      nomJeuneFille: new FormControl(ficheFacteurVIIIRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(25)],
      }),
      profession: new FormControl(ficheFacteurVIIIRawValue.profession, {
        validators: [Validators.maxLength(25)],
      }),
      etatCivil: new FormControl(ficheFacteurVIIIRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      serviceClinique: new FormControl(ficheFacteurVIIIRawValue.serviceClinique, {
        validators: [Validators.required],
      }),
      autreServiceClinique: new FormControl(ficheFacteurVIIIRawValue.autreServiceClinique, {
        validators: [Validators.maxLength(255)],
      }),
      couvertureSociale: new FormControl(ficheFacteurVIIIRawValue.couvertureSociale, {
        validators: [Validators.required],
      }),
      sexe: new FormControl(ficheFacteurVIIIRawValue.sexe, {
        validators: [Validators.required],
      }),
      dateNaissance: new FormControl(ficheFacteurVIIIRawValue.dateNaissance),
      ageActuel: new FormControl(ficheFacteurVIIIRawValue.ageActuel),
      origine: new FormControl(ficheFacteurVIIIRawValue.origine),
      autreOrigine: new FormControl(ficheFacteurVIIIRawValue.autreOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      adresse: new FormControl(ficheFacteurVIIIRawValue.adresse, {
        validators: [Validators.maxLength(25)],
      }),
      telephone: new FormControl(ficheFacteurVIIIRawValue.telephone, {
        validators: [Validators.maxLength(20)],
      }),
      prenomPere: new FormControl(ficheFacteurVIIIRawValue.prenomPere, {
        validators: [Validators.maxLength(25)],
      }),
      professionPere: new FormControl(ficheFacteurVIIIRawValue.professionPere, {
        validators: [Validators.maxLength(25)],
      }),
      nomPrenomMere: new FormControl(ficheFacteurVIIIRawValue.nomPrenomMere, {
        validators: [Validators.maxLength(25)],
      }),
      professionMere: new FormControl(ficheFacteurVIIIRawValue.professionMere, {
        validators: [Validators.maxLength(25)],
      }),
      medecinTraitant: new FormControl(ficheFacteurVIIIRawValue.medecinTraitant, {
        validators: [Validators.maxLength(25)],
      }),
      serviceOrigine: new FormControl(ficheFacteurVIIIRawValue.serviceOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      biologisteResponsableDg: new FormControl(ficheFacteurVIIIRawValue.biologisteResponsableDg, {
        validators: [Validators.maxLength(25)],
      }),
      diagnostic: new FormControl(ficheFacteurVIIIRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(ficheFacteurVIIIRawValue.autreDiagnostic, {
        validators: [Validators.maxLength(25)],
      }),
      anneeDiagnostic: new FormControl(ficheFacteurVIIIRawValue.anneeDiagnostic),
      consentementRegistre: new FormControl(ficheFacteurVIIIRawValue.consentementRegistre),
      dateEnregistrementRegistre: new FormControl(ficheFacteurVIIIRawValue.dateEnregistrementRegistre),
      parentsConsanguins: new FormControl(ficheFacteurVIIIRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheFacteurVIIIRawValue.degreParenteConsanguins),
      casSimilaires: new FormControl(ficheFacteurVIIIRawValue.casSimilaires),
      nombreCas: new FormControl(ficheFacteurVIIIRawValue.nombreCas),
      cas1Numero: new FormControl(ficheFacteurVIIIRawValue.cas1Numero),
      lienParenteCas1: new FormControl(ficheFacteurVIIIRawValue.lienParenteCas1),
      degreParenteCas1: new FormControl(ficheFacteurVIIIRawValue.degreParenteCas1, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas1: new FormControl(ficheFacteurVIIIRawValue.typeHemorragieCas1, {
        validators: [Validators.maxLength(255)],
      }),
      cas2Numero: new FormControl(ficheFacteurVIIIRawValue.cas2Numero),
      lienParenteCas2: new FormControl(ficheFacteurVIIIRawValue.lienParenteCas2),
      degreParenteCas2: new FormControl(ficheFacteurVIIIRawValue.degreParenteCas2, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas2: new FormControl(ficheFacteurVIIIRawValue.typeHemorragieCas2, {
        validators: [Validators.maxLength(255)],
      }),
      cas3Numero: new FormControl(ficheFacteurVIIIRawValue.cas3Numero),
      lienParenteCas3: new FormControl(ficheFacteurVIIIRawValue.lienParenteCas3),
      degreParenteCas3: new FormControl(ficheFacteurVIIIRawValue.degreParenteCas3, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas3: new FormControl(ficheFacteurVIIIRawValue.typeHemorragieCas3, {
        validators: [Validators.maxLength(255)],
      }),
      decesSyndromeHemorragique: new FormControl(ficheFacteurVIIIRawValue.decesSyndromeHemorragique),
      nombreDeces: new FormControl(ficheFacteurVIIIRawValue.nombreDeces),
      deces1Numero: new FormControl(ficheFacteurVIIIRawValue.deces1Numero),
      lienParenteDeces1: new FormControl(ficheFacteurVIIIRawValue.lienParenteDeces1),
      causeDeces1: new FormControl(ficheFacteurVIIIRawValue.causeDeces1, {
        validators: [Validators.maxLength(255)],
      }),
      deces2Numero: new FormControl(ficheFacteurVIIIRawValue.deces2Numero),
      lienParenteDeces2: new FormControl(ficheFacteurVIIIRawValue.lienParenteDeces2),
      causeDeces2: new FormControl(ficheFacteurVIIIRawValue.causeDeces2, {
        validators: [Validators.maxLength(255)],
      }),
      deces3Numero: new FormControl(ficheFacteurVIIIRawValue.deces3Numero),
      lienParenteDeces3: new FormControl(ficheFacteurVIIIRawValue.lienParenteDeces3),
      causeDeces3: new FormControl(ficheFacteurVIIIRawValue.causeDeces3, {
        validators: [Validators.maxLength(255)],
      }),
      enqueteFamiliale: new FormControl(ficheFacteurVIIIRawValue.enqueteFamiliale),
      decouverteFortuite: new FormControl(ficheFacteurVIIIRawValue.decouverteFortuite),
      enqueteFamilialeBool: new FormControl(ficheFacteurVIIIRawValue.enqueteFamilialeBool),
      explorationSyndromeHemorragique: new FormControl(ficheFacteurVIIIRawValue.explorationSyndromeHemorragique),
      typeExplorationSyndromeHemorragique: new FormControl(ficheFacteurVIIIRawValue.typeExplorationSyndromeHemorragique),
      datePremierSigneClinique: new FormControl(ficheFacteurVIIIRawValue.datePremierSigneClinique),
      ageDiagnosticJour: new FormControl(ficheFacteurVIIIRawValue.ageDiagnosticJour),
      ageDiagnosticMois: new FormControl(ficheFacteurVIIIRawValue.ageDiagnosticMois),
      ageDiagnosticAnnee: new FormControl(ficheFacteurVIIIRawValue.ageDiagnosticAnnee),
      tp: new FormControl(ficheFacteurVIIIRawValue.tp),
      tpMT: new FormControl(ficheFacteurVIIIRawValue.tpMT),
      tca: new FormControl(ficheFacteurVIIIRawValue.tca),
      tcaMT: new FormControl(ficheFacteurVIIIRawValue.tcaMT),
      fibrinogene: new FormControl(ficheFacteurVIIIRawValue.fibrinogene),
      ts: new FormControl(ficheFacteurVIIIRawValue.ts),
      tsFait: new FormControl(ficheFacteurVIIIRawValue.tsFait),
      toPfa: new FormControl(ficheFacteurVIIIRawValue.toPfa),
      toPfa200ColEpi: new FormControl(ficheFacteurVIIIRawValue.toPfa200ColEpi),
      toPfa200ColAdp: new FormControl(ficheFacteurVIIIRawValue.toPfa200ColAdp),
      tauxDosageFacteurXIII: new FormControl(ficheFacteurVIIIRawValue.tauxDosageFacteurXIII),
      hb: new FormControl(ficheFacteurVIIIRawValue.hb),
      ht: new FormControl(ficheFacteurVIIIRawValue.ht),
      plaquettes: new FormControl(ficheFacteurVIIIRawValue.plaquettes),
      techniqueChromogenique: new FormControl(ficheFacteurVIIIRawValue.techniqueChromogenique),
      tauxTechniqueChromogenique: new FormControl(ficheFacteurVIIIRawValue.tauxTechniqueChromogenique),
      techniqueSolubiliteCaillot: new FormControl(ficheFacteurVIIIRawValue.techniqueSolubiliteCaillot),
      tauxTechniqueSolubiliteCaillot: new FormControl(ficheFacteurVIIIRawValue.tauxTechniqueSolubiliteCaillot),
      confirmationMoleculaire: new FormControl(ficheFacteurVIIIRawValue.confirmationMoleculaire),
      mutationPresence: new FormControl(ficheFacteurVIIIRawValue.mutationPresence),
      mutationType: new FormControl(ficheFacteurVIIIRawValue.mutationType),
      epistaxis: new FormControl(ficheFacteurVIIIRawValue.epistaxis),
      frequenceEpistaxis: new FormControl(ficheFacteurVIIIRawValue.frequenceEpistaxis),
      gingivorragies: new FormControl(ficheFacteurVIIIRawValue.gingivorragies),
      typeGingivorragies: new FormControl(ficheFacteurVIIIRawValue.typeGingivorragies),
      frequenceGingivorragies: new FormControl(ficheFacteurVIIIRawValue.frequenceGingivorragies),
      ecchymose: new FormControl(ficheFacteurVIIIRawValue.ecchymose),
      typeEcchymose: new FormControl(ficheFacteurVIIIRawValue.typeEcchymose),
      frequenceEcchymose: new FormControl(ficheFacteurVIIIRawValue.frequenceEcchymose),
      menorragie: new FormControl(ficheFacteurVIIIRawValue.menorragie),
      frequenceMenorragie: new FormControl(ficheFacteurVIIIRawValue.frequenceMenorragie),
      hematemese: new FormControl(ficheFacteurVIIIRawValue.hematemese),
      frequenceHematemese: new FormControl(ficheFacteurVIIIRawValue.frequenceHematemese),
      rectorragie: new FormControl(ficheFacteurVIIIRawValue.rectorragie),
      frequenceRectorragie: new FormControl(ficheFacteurVIIIRawValue.frequenceRectorragie),
      hematurie: new FormControl(ficheFacteurVIIIRawValue.hematurie),
      frequenceHematurie: new FormControl(ficheFacteurVIIIRawValue.frequenceHematurie),
      hematomeSousCutane: new FormControl(ficheFacteurVIIIRawValue.hematomeSousCutane),
      typeHematomeSousCutane: new FormControl(ficheFacteurVIIIRawValue.typeHematomeSousCutane),
      frequenceHematomeSousCutane: new FormControl(ficheFacteurVIIIRawValue.frequenceHematomeSousCutane),
      hematomeMusculaire: new FormControl(ficheFacteurVIIIRawValue.hematomeMusculaire),
      siegeHematomeMusculaire: new FormControl(ficheFacteurVIIIRawValue.siegeHematomeMusculaire, {
        validators: [Validators.maxLength(255)],
      }),
      typeHematomeMusculaire: new FormControl(ficheFacteurVIIIRawValue.typeHematomeMusculaire),
      frequenceHematomeMusculaire: new FormControl(ficheFacteurVIIIRawValue.frequenceHematomeMusculaire),
      hemarthrose: new FormControl(ficheFacteurVIIIRawValue.hemarthrose),
      typeHemarthrose: new FormControl(ficheFacteurVIIIRawValue.typeHemarthrose),
      frequenceHemarthrose: new FormControl(ficheFacteurVIIIRawValue.frequenceHemarthrose),
      saignementSNC: new FormControl(ficheFacteurVIIIRawValue.saignementSNC),
      typeSaignementSNC: new FormControl(ficheFacteurVIIIRawValue.typeSaignementSNC),
      frequenceSaignementSNC: new FormControl(ficheFacteurVIIIRawValue.frequenceSaignementSNC),
      ruptureKystesHemorragiques: new FormControl(ficheFacteurVIIIRawValue.ruptureKystesHemorragiques),
      hemorragiesPostChirurgicales: new FormControl(ficheFacteurVIIIRawValue.hemorragiesPostChirurgicales),
      hemoperitone: new FormControl(ficheFacteurVIIIRawValue.hemoperitone),
      accidentsThrombotiques: new FormControl(ficheFacteurVIIIRawValue.accidentsThrombotiques),
      typeAccidentsThrombotiques: new FormControl(ficheFacteurVIIIRawValue.typeAccidentsThrombotiques),
      hemorragieGrave: new FormControl(ficheFacteurVIIIRawValue.hemorragieGrave),
      typeHemorragieGrave: new FormControl(ficheFacteurVIIIRawValue.typeHemorragieGrave),
      chirurgiePrepare: new FormControl(ficheFacteurVIIIRawValue.chirurgiePrepare),
      suiteOperatoireHemorragique: new FormControl(ficheFacteurVIIIRawValue.suiteOperatoireHemorragique),
      circoncisionPrepare: new FormControl(ficheFacteurVIIIRawValue.circoncisionPrepare),
      suiteOperatoireCirconcision: new FormControl(ficheFacteurVIIIRawValue.suiteOperatoireCirconcision),
      ageCirconcision: new FormControl(ficheFacteurVIIIRawValue.ageCirconcision),
      facteurVIIIRecombinant: new FormControl(ficheFacteurVIIIRawValue.facteurVIIIRecombinant),
      plasmaFraisCongele: new FormControl(ficheFacteurVIIIRawValue.plasmaFraisCongele),
      cryoprecipite: new FormControl(ficheFacteurVIIIRawValue.cryoprecipite),
      culotsPlaquettaires: new FormControl(ficheFacteurVIIIRawValue.culotsPlaquettaires),
      dicynone: new FormControl(ficheFacteurVIIIRawValue.dicynone),
      exacyl: new FormControl(ficheFacteurVIIIRawValue.exacyl),
      traitementMartial: new FormControl(ficheFacteurVIIIRawValue.traitementMartial),
      traitementOestroprogestatif: new FormControl(ficheFacteurVIIIRawValue.traitementOestroprogestatif),
      transfusionCGR: new FormControl(ficheFacteurVIIIRawValue.transfusionCGR),
      inhibiteurs: new FormControl(ficheFacteurVIIIRawValue.inhibiteurs),
      inhibiteurNeutralisant: new FormControl(ficheFacteurVIIIRawValue.inhibiteurNeutralisant),
      titreUB: new FormControl(ficheFacteurVIIIRawValue.titreUB),
      inhibiteurNonNeutralisant: new FormControl(ficheFacteurVIIIRawValue.inhibiteurNonNeutralisant),
      infectionsVirales: new FormControl(ficheFacteurVIIIRawValue.infectionsVirales),
      thrombose: new FormControl(ficheFacteurVIIIRawValue.thrombose),
      reactionAllergique: new FormControl(ficheFacteurVIIIRawValue.reactionAllergique),
      alloImmunisation: new FormControl(ficheFacteurVIIIRawValue.alloImmunisation),
      adhesionTherapeutique: new FormControl(ficheFacteurVIIIRawValue.adhesionTherapeutique),
      causeNonAdhesion: new FormControl(ficheFacteurVIIIRawValue.causeNonAdhesion, {
        validators: [Validators.maxLength(255)],
      }),
      vieSociale: new FormControl(ficheFacteurVIIIRawValue.vieSociale),
      mariage: new FormControl(ficheFacteurVIIIRawValue.mariage),
      enfants: new FormControl(ficheFacteurVIIIRawValue.enfants),
      activitePhysique: new FormControl(ficheFacteurVIIIRawValue.activitePhysique),
      typeActivitePhysique: new FormControl(ficheFacteurVIIIRawValue.typeActivitePhysique, {
        validators: [Validators.maxLength(255)],
      }),
      sequelles: new FormControl(ficheFacteurVIIIRawValue.sequelles),
      typeSequelles: new FormControl(ficheFacteurVIIIRawValue.typeSequelles, {
        validators: [Validators.maxLength(255)],
      }),
      patientDecede: new FormControl(ficheFacteurVIIIRawValue.patientDecede),
      dateDeces: new FormControl(ficheFacteurVIIIRawValue.dateDeces),
      ageDeces: new FormControl(ficheFacteurVIIIRawValue.ageDeces),
      hemorragieSnc: new FormControl(ficheFacteurVIIIRawValue.hemorragieSnc),
      hemorragieAutreQueSnc: new FormControl(ficheFacteurVIIIRawValue.hemorragieAutreQueSnc),
      hiv: new FormControl(ficheFacteurVIIIRawValue.hiv),
      autreInfection: new FormControl(ficheFacteurVIIIRawValue.autreInfection),
      evenementThromboembolique: new FormControl(ficheFacteurVIIIRawValue.evenementThromboembolique),
      cancer: new FormControl(ficheFacteurVIIIRawValue.cancer),
      covid19: new FormControl(ficheFacteurVIIIRawValue.covid19),
      inconnue: new FormControl(ficheFacteurVIIIRawValue.inconnue),
      autreEvenement: new FormControl(ficheFacteurVIIIRawValue.autreEvenement),
      autreEvenementDetails: new FormControl(ficheFacteurVIIIRawValue.autreEvenementDetails, {
        validators: [Validators.maxLength(255)],
      }),
    });
  }

  getFicheFacteurVIII(form: FicheFacteurVIIIFormGroup): IFicheFacteurVIII | NewFicheFacteurVIII {
    return form.getRawValue() as IFicheFacteurVIII | NewFicheFacteurVIII;
  }

  resetForm(form: FicheFacteurVIIIFormGroup, ficheFacteurVIII: FicheFacteurVIIIFormGroupInput): void {
    const ficheFacteurVIIIRawValue = { ...this.getFormDefaults(), ...ficheFacteurVIII };
    form.reset(
      {
        ...ficheFacteurVIIIRawValue,
        id: { value: ficheFacteurVIIIRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheFacteurVIIIFormDefaults {
    return {
      id: null,
      decouverteFortuite: false,
      enqueteFamilialeBool: false,
      explorationSyndromeHemorragique: false,
      techniqueChromogenique: false,
      techniqueSolubiliteCaillot: false,
      mutationPresence: false,
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
      ruptureKystesHemorragiques: false,
      hemorragiesPostChirurgicales: false,
      hemoperitone: false,
      accidentsThrombotiques: false,
      hemorragieGrave: false,
      inhibiteurNeutralisant: false,
      inhibiteurNonNeutralisant: false,
      hemorragieSnc: false,
      hemorragieAutreQueSnc: false,
      hiv: false,
      autreInfection: false,
      evenementThromboembolique: false,
      cancer: false,
      covid19: false,
      inconnue: false,
      autreEvenement: false,
    };
  }
}
