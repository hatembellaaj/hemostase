import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFicheFacteurVII, NewFicheFacteurVII } from '../fiche-facteur-vii.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFicheFacteurVII for edit and NewFicheFacteurVIIFormGroupInput for create.
 */
type FicheFacteurVIIFormGroupInput = IFicheFacteurVII | PartialWithRequiredKeyOf<NewFicheFacteurVII>;

type FicheFacteurVIIFormDefaults = Pick<
  NewFicheFacteurVII,
  | 'id'
  | 'decouverteFortuite'
  | 'enqueteFamilialeBool'
  | 'explorationSyndromeHemorragique'
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

type FicheFacteurVIIFormGroupContent = {
  id: FormControl<IFicheFacteurVII['id'] | NewFicheFacteurVII['id']>;
  dossierNumber: FormControl<IFicheFacteurVII['dossierNumber']>;
  ordreNumber: FormControl<IFicheFacteurVII['ordreNumber']>;
  indexNumber: FormControl<IFicheFacteurVII['indexNumber']>;
  prenom: FormControl<IFicheFacteurVII['prenom']>;
  nom: FormControl<IFicheFacteurVII['nom']>;
  nomJeuneFille: FormControl<IFicheFacteurVII['nomJeuneFille']>;
  profession: FormControl<IFicheFacteurVII['profession']>;
  etatCivil: FormControl<IFicheFacteurVII['etatCivil']>;
  serviceClinique: FormControl<IFicheFacteurVII['serviceClinique']>;
  autreServiceClinique: FormControl<IFicheFacteurVII['autreServiceClinique']>;
  couvertureSociale: FormControl<IFicheFacteurVII['couvertureSociale']>;
  sexe: FormControl<IFicheFacteurVII['sexe']>;
  dateNaissance: FormControl<IFicheFacteurVII['dateNaissance']>;
  ageActuel: FormControl<IFicheFacteurVII['ageActuel']>;
  origine: FormControl<IFicheFacteurVII['origine']>;
  autreOrigine: FormControl<IFicheFacteurVII['autreOrigine']>;
  adresse: FormControl<IFicheFacteurVII['adresse']>;
  telephone: FormControl<IFicheFacteurVII['telephone']>;
  prenomPere: FormControl<IFicheFacteurVII['prenomPere']>;
  professionPere: FormControl<IFicheFacteurVII['professionPere']>;
  nomPrenomMere: FormControl<IFicheFacteurVII['nomPrenomMere']>;
  professionMere: FormControl<IFicheFacteurVII['professionMere']>;
  medecinTraitant: FormControl<IFicheFacteurVII['medecinTraitant']>;
  serviceOrigine: FormControl<IFicheFacteurVII['serviceOrigine']>;
  biologisteResponsableDg: FormControl<IFicheFacteurVII['biologisteResponsableDg']>;
  diagnostic: FormControl<IFicheFacteurVII['diagnostic']>;
  autreDiagnostic: FormControl<IFicheFacteurVII['autreDiagnostic']>;
  anneeDiagnostic: FormControl<IFicheFacteurVII['anneeDiagnostic']>;
  consentementRegistre: FormControl<IFicheFacteurVII['consentementRegistre']>;
  dateEnregistrementRegistre: FormControl<IFicheFacteurVII['dateEnregistrementRegistre']>;
  parentsConsanguins: FormControl<IFicheFacteurVII['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFicheFacteurVII['degreParenteConsanguins']>;
  casSimilaires: FormControl<IFicheFacteurVII['casSimilaires']>;
  nombreCas: FormControl<IFicheFacteurVII['nombreCas']>;
  cas1Numero: FormControl<IFicheFacteurVII['cas1Numero']>;
  lienParenteCas1: FormControl<IFicheFacteurVII['lienParenteCas1']>;
  degreParenteCas1: FormControl<IFicheFacteurVII['degreParenteCas1']>;
  typeHemorragieCas1: FormControl<IFicheFacteurVII['typeHemorragieCas1']>;
  cas2Numero: FormControl<IFicheFacteurVII['cas2Numero']>;
  lienParenteCas2: FormControl<IFicheFacteurVII['lienParenteCas2']>;
  degreParenteCas2: FormControl<IFicheFacteurVII['degreParenteCas2']>;
  typeHemorragieCas2: FormControl<IFicheFacteurVII['typeHemorragieCas2']>;
  cas3Numero: FormControl<IFicheFacteurVII['cas3Numero']>;
  lienParenteCas3: FormControl<IFicheFacteurVII['lienParenteCas3']>;
  degreParenteCas3: FormControl<IFicheFacteurVII['degreParenteCas3']>;
  typeHemorragieCas3: FormControl<IFicheFacteurVII['typeHemorragieCas3']>;
  decesSyndromeHemorragique: FormControl<IFicheFacteurVII['decesSyndromeHemorragique']>;
  nombreDeces: FormControl<IFicheFacteurVII['nombreDeces']>;
  deces1Numero: FormControl<IFicheFacteurVII['deces1Numero']>;
  lienParenteDeces1: FormControl<IFicheFacteurVII['lienParenteDeces1']>;
  causeDeces1: FormControl<IFicheFacteurVII['causeDeces1']>;
  deces2Numero: FormControl<IFicheFacteurVII['deces2Numero']>;
  lienParenteDeces2: FormControl<IFicheFacteurVII['lienParenteDeces2']>;
  causeDeces2: FormControl<IFicheFacteurVII['causeDeces2']>;
  deces3Numero: FormControl<IFicheFacteurVII['deces3Numero']>;
  lienParenteDeces3: FormControl<IFicheFacteurVII['lienParenteDeces3']>;
  causeDeces3: FormControl<IFicheFacteurVII['causeDeces3']>;
  enqueteFamiliale: FormControl<IFicheFacteurVII['enqueteFamiliale']>;
  decouverteFortuite: FormControl<IFicheFacteurVII['decouverteFortuite']>;
  enqueteFamilialeBool: FormControl<IFicheFacteurVII['enqueteFamilialeBool']>;
  explorationSyndromeHemorragique: FormControl<IFicheFacteurVII['explorationSyndromeHemorragique']>;
  typeExplorationSyndromeHemorragique: FormControl<IFicheFacteurVII['typeExplorationSyndromeHemorragique']>;
  datePremierSigneClinique: FormControl<IFicheFacteurVII['datePremierSigneClinique']>;
  ageDiagnosticJour: FormControl<IFicheFacteurVII['ageDiagnosticJour']>;
  ageDiagnosticMois: FormControl<IFicheFacteurVII['ageDiagnosticMois']>;
  ageDiagnosticAnnee: FormControl<IFicheFacteurVII['ageDiagnosticAnnee']>;
  hb: FormControl<IFicheFacteurVII['hb']>;
  ht: FormControl<IFicheFacteurVII['ht']>;
  plaquettes: FormControl<IFicheFacteurVII['plaquettes']>;
  toPfa200ColEpi: FormControl<IFicheFacteurVII['toPfa200ColEpi']>;
  toPfa200ColAdp: FormControl<IFicheFacteurVII['toPfa200ColAdp']>;
  tp: FormControl<IFicheFacteurVII['tp']>;
  tpMT: FormControl<IFicheFacteurVII['tpMT']>;
  fibrinogene: FormControl<IFicheFacteurVII['fibrinogene']>;
  tcaMT: FormControl<IFicheFacteurVII['tcaMT']>;
  facteurVII: FormControl<IFicheFacteurVII['facteurVII']>;
  dosageAutresFacteurs: FormControl<IFicheFacteurVII['dosageAutresFacteurs']>;
  confirmationMoleculaire: FormControl<IFicheFacteurVII['confirmationMoleculaire']>;
  mutationPresence: FormControl<IFicheFacteurVII['mutationPresence']>;
  mutationType: FormControl<IFicheFacteurVII['mutationType']>;
  epistaxis: FormControl<IFicheFacteurVII['epistaxis']>;
  frequenceEpistaxis: FormControl<IFicheFacteurVII['frequenceEpistaxis']>;
  gingivorragies: FormControl<IFicheFacteurVII['gingivorragies']>;
  typeGingivorragies: FormControl<IFicheFacteurVII['typeGingivorragies']>;
  frequenceGingivorragies: FormControl<IFicheFacteurVII['frequenceGingivorragies']>;
  ecchymose: FormControl<IFicheFacteurVII['ecchymose']>;
  typeEcchymose: FormControl<IFicheFacteurVII['typeEcchymose']>;
  frequenceEcchymose: FormControl<IFicheFacteurVII['frequenceEcchymose']>;
  menorragie: FormControl<IFicheFacteurVII['menorragie']>;
  frequenceMenorragie: FormControl<IFicheFacteurVII['frequenceMenorragie']>;
  hematemese: FormControl<IFicheFacteurVII['hematemese']>;
  frequenceHematemese: FormControl<IFicheFacteurVII['frequenceHematemese']>;
  rectorragie: FormControl<IFicheFacteurVII['rectorragie']>;
  frequenceRectorragie: FormControl<IFicheFacteurVII['frequenceRectorragie']>;
  hematurie: FormControl<IFicheFacteurVII['hematurie']>;
  frequenceHematurie: FormControl<IFicheFacteurVII['frequenceHematurie']>;
  hematomeSousCutane: FormControl<IFicheFacteurVII['hematomeSousCutane']>;
  typeHematomeSousCutane: FormControl<IFicheFacteurVII['typeHematomeSousCutane']>;
  frequenceHematomeSousCutane: FormControl<IFicheFacteurVII['frequenceHematomeSousCutane']>;
  hematomeMusculaire: FormControl<IFicheFacteurVII['hematomeMusculaire']>;
  siegeHematomeMusculaire: FormControl<IFicheFacteurVII['siegeHematomeMusculaire']>;
  typeHematomeMusculaire: FormControl<IFicheFacteurVII['typeHematomeMusculaire']>;
  frequenceHematomeMusculaire: FormControl<IFicheFacteurVII['frequenceHematomeMusculaire']>;
  hemarthrose: FormControl<IFicheFacteurVII['hemarthrose']>;
  typeHemarthrose: FormControl<IFicheFacteurVII['typeHemarthrose']>;
  frequenceHemarthrose: FormControl<IFicheFacteurVII['frequenceHemarthrose']>;
  saignementSNC: FormControl<IFicheFacteurVII['saignementSNC']>;
  typeSaignementSNC: FormControl<IFicheFacteurVII['typeSaignementSNC']>;
  frequenceSaignementSNC: FormControl<IFicheFacteurVII['frequenceSaignementSNC']>;
  ruptureKystesHemorragiques: FormControl<IFicheFacteurVII['ruptureKystesHemorragiques']>;
  hemorragiesPostChirurgicales: FormControl<IFicheFacteurVII['hemorragiesPostChirurgicales']>;
  hemoperitone: FormControl<IFicheFacteurVII['hemoperitone']>;
  accidentsThrombotiques: FormControl<IFicheFacteurVII['accidentsThrombotiques']>;
  typeAccidentsThrombotiques: FormControl<IFicheFacteurVII['typeAccidentsThrombotiques']>;
  hemorragieGrave: FormControl<IFicheFacteurVII['hemorragieGrave']>;
  typeHemorragieGrave: FormControl<IFicheFacteurVII['typeHemorragieGrave']>;
  chirurgiePrepare: FormControl<IFicheFacteurVII['chirurgiePrepare']>;
  suiteOperatoireHemorragique: FormControl<IFicheFacteurVII['suiteOperatoireHemorragique']>;
  circoncisionPrepare: FormControl<IFicheFacteurVII['circoncisionPrepare']>;
  suiteOperatoireCirconcision: FormControl<IFicheFacteurVII['suiteOperatoireCirconcision']>;
  ageCirconcision: FormControl<IFicheFacteurVII['ageCirconcision']>;
  facteurVIIRecombinant: FormControl<IFicheFacteurVII['facteurVIIRecombinant']>;
  plasmaFraisCongele: FormControl<IFicheFacteurVII['plasmaFraisCongele']>;
  cryoprecipite: FormControl<IFicheFacteurVII['cryoprecipite']>;
  culotsPlaquettaires: FormControl<IFicheFacteurVII['culotsPlaquettaires']>;
  dicynone: FormControl<IFicheFacteurVII['dicynone']>;
  exacyl: FormControl<IFicheFacteurVII['exacyl']>;
  traitementMartial: FormControl<IFicheFacteurVII['traitementMartial']>;
  traitementOestroprogestatif: FormControl<IFicheFacteurVII['traitementOestroprogestatif']>;
  transfusionCGR: FormControl<IFicheFacteurVII['transfusionCGR']>;
  inhibiteurs: FormControl<IFicheFacteurVII['inhibiteurs']>;
  inhibiteurNeutralisant: FormControl<IFicheFacteurVII['inhibiteurNeutralisant']>;
  titreUB: FormControl<IFicheFacteurVII['titreUB']>;
  inhibiteurNonNeutralisant: FormControl<IFicheFacteurVII['inhibiteurNonNeutralisant']>;
  infectionsVirales: FormControl<IFicheFacteurVII['infectionsVirales']>;
  thrombose: FormControl<IFicheFacteurVII['thrombose']>;
  reactionAllergique: FormControl<IFicheFacteurVII['reactionAllergique']>;
  alloImmunisation: FormControl<IFicheFacteurVII['alloImmunisation']>;
  adhesionTherapeutique: FormControl<IFicheFacteurVII['adhesionTherapeutique']>;
  causeNonAdhesion: FormControl<IFicheFacteurVII['causeNonAdhesion']>;
  vieSociale: FormControl<IFicheFacteurVII['vieSociale']>;
  mariage: FormControl<IFicheFacteurVII['mariage']>;
  enfants: FormControl<IFicheFacteurVII['enfants']>;
  activitePhysique: FormControl<IFicheFacteurVII['activitePhysique']>;
  typeActivitePhysique: FormControl<IFicheFacteurVII['typeActivitePhysique']>;
  sequelles: FormControl<IFicheFacteurVII['sequelles']>;
  typeSequelles: FormControl<IFicheFacteurVII['typeSequelles']>;
  patientDecede: FormControl<IFicheFacteurVII['patientDecede']>;
  dateDeces: FormControl<IFicheFacteurVII['dateDeces']>;
  ageDeces: FormControl<IFicheFacteurVII['ageDeces']>;
  hemorragieSnc: FormControl<IFicheFacteurVII['hemorragieSnc']>;
  hemorragieAutreQueSnc: FormControl<IFicheFacteurVII['hemorragieAutreQueSnc']>;
  hiv: FormControl<IFicheFacteurVII['hiv']>;
  autreInfection: FormControl<IFicheFacteurVII['autreInfection']>;
  evenementThromboembolique: FormControl<IFicheFacteurVII['evenementThromboembolique']>;
  cancer: FormControl<IFicheFacteurVII['cancer']>;
  covid19: FormControl<IFicheFacteurVII['covid19']>;
  inconnue: FormControl<IFicheFacteurVII['inconnue']>;
  autreEvenement: FormControl<IFicheFacteurVII['autreEvenement']>;
  autreEvenementDetails: FormControl<IFicheFacteurVII['autreEvenementDetails']>;
};

export type FicheFacteurVIIFormGroup = FormGroup<FicheFacteurVIIFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurVIIFormService {
  createFicheFacteurVIIFormGroup(ficheFacteurVII: FicheFacteurVIIFormGroupInput = { id: null }): FicheFacteurVIIFormGroup {
    const ficheFacteurVIIRawValue = {
      ...this.getFormDefaults(),
      ...ficheFacteurVII,
    };
    return new FormGroup<FicheFacteurVIIFormGroupContent>({
      id: new FormControl(
        { value: ficheFacteurVIIRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dossierNumber: new FormControl(ficheFacteurVIIRawValue.dossierNumber),
      ordreNumber: new FormControl(ficheFacteurVIIRawValue.ordreNumber, {
        validators: [Validators.maxLength(25)],
      }),
      indexNumber: new FormControl(ficheFacteurVIIRawValue.indexNumber, {
        validators: [Validators.maxLength(25)],
      }),
      prenom: new FormControl(ficheFacteurVIIRawValue.prenom, {
        validators: [Validators.maxLength(25)],
      }),
      nom: new FormControl(ficheFacteurVIIRawValue.nom, {
        validators: [Validators.maxLength(25)],
      }),
      nomJeuneFille: new FormControl(ficheFacteurVIIRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(25)],
      }),
      profession: new FormControl(ficheFacteurVIIRawValue.profession, {
        validators: [Validators.maxLength(25)],
      }),
      etatCivil: new FormControl(ficheFacteurVIIRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      serviceClinique: new FormControl(ficheFacteurVIIRawValue.serviceClinique, {
        validators: [Validators.required],
      }),
      autreServiceClinique: new FormControl(ficheFacteurVIIRawValue.autreServiceClinique, {
        validators: [Validators.maxLength(255)],
      }),
      couvertureSociale: new FormControl(ficheFacteurVIIRawValue.couvertureSociale, {
        validators: [Validators.required],
      }),
      sexe: new FormControl(ficheFacteurVIIRawValue.sexe, {
        validators: [Validators.required],
      }),
      dateNaissance: new FormControl(ficheFacteurVIIRawValue.dateNaissance),
      ageActuel: new FormControl(ficheFacteurVIIRawValue.ageActuel),
      origine: new FormControl(ficheFacteurVIIRawValue.origine),
      autreOrigine: new FormControl(ficheFacteurVIIRawValue.autreOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      adresse: new FormControl(ficheFacteurVIIRawValue.adresse, {
        validators: [Validators.maxLength(25)],
      }),
      telephone: new FormControl(ficheFacteurVIIRawValue.telephone, {
        validators: [Validators.maxLength(20)],
      }),
      prenomPere: new FormControl(ficheFacteurVIIRawValue.prenomPere, {
        validators: [Validators.maxLength(25)],
      }),
      professionPere: new FormControl(ficheFacteurVIIRawValue.professionPere, {
        validators: [Validators.maxLength(25)],
      }),
      nomPrenomMere: new FormControl(ficheFacteurVIIRawValue.nomPrenomMere, {
        validators: [Validators.maxLength(25)],
      }),
      professionMere: new FormControl(ficheFacteurVIIRawValue.professionMere, {
        validators: [Validators.maxLength(25)],
      }),
      medecinTraitant: new FormControl(ficheFacteurVIIRawValue.medecinTraitant, {
        validators: [Validators.maxLength(25)],
      }),
      serviceOrigine: new FormControl(ficheFacteurVIIRawValue.serviceOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      biologisteResponsableDg: new FormControl(ficheFacteurVIIRawValue.biologisteResponsableDg, {
        validators: [Validators.maxLength(25)],
      }),
      diagnostic: new FormControl(ficheFacteurVIIRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(ficheFacteurVIIRawValue.autreDiagnostic, {
        validators: [Validators.maxLength(25)],
      }),
      anneeDiagnostic: new FormControl(ficheFacteurVIIRawValue.anneeDiagnostic),
      consentementRegistre: new FormControl(ficheFacteurVIIRawValue.consentementRegistre),
      dateEnregistrementRegistre: new FormControl(ficheFacteurVIIRawValue.dateEnregistrementRegistre),
      parentsConsanguins: new FormControl(ficheFacteurVIIRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheFacteurVIIRawValue.degreParenteConsanguins),
      casSimilaires: new FormControl(ficheFacteurVIIRawValue.casSimilaires),
      nombreCas: new FormControl(ficheFacteurVIIRawValue.nombreCas),
      cas1Numero: new FormControl(ficheFacteurVIIRawValue.cas1Numero),
      lienParenteCas1: new FormControl(ficheFacteurVIIRawValue.lienParenteCas1),
      degreParenteCas1: new FormControl(ficheFacteurVIIRawValue.degreParenteCas1, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas1: new FormControl(ficheFacteurVIIRawValue.typeHemorragieCas1, {
        validators: [Validators.maxLength(255)],
      }),
      cas2Numero: new FormControl(ficheFacteurVIIRawValue.cas2Numero),
      lienParenteCas2: new FormControl(ficheFacteurVIIRawValue.lienParenteCas2),
      degreParenteCas2: new FormControl(ficheFacteurVIIRawValue.degreParenteCas2, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas2: new FormControl(ficheFacteurVIIRawValue.typeHemorragieCas2, {
        validators: [Validators.maxLength(255)],
      }),
      cas3Numero: new FormControl(ficheFacteurVIIRawValue.cas3Numero),
      lienParenteCas3: new FormControl(ficheFacteurVIIRawValue.lienParenteCas3),
      degreParenteCas3: new FormControl(ficheFacteurVIIRawValue.degreParenteCas3, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas3: new FormControl(ficheFacteurVIIRawValue.typeHemorragieCas3, {
        validators: [Validators.maxLength(255)],
      }),
      decesSyndromeHemorragique: new FormControl(ficheFacteurVIIRawValue.decesSyndromeHemorragique),
      nombreDeces: new FormControl(ficheFacteurVIIRawValue.nombreDeces),
      deces1Numero: new FormControl(ficheFacteurVIIRawValue.deces1Numero),
      lienParenteDeces1: new FormControl(ficheFacteurVIIRawValue.lienParenteDeces1),
      causeDeces1: new FormControl(ficheFacteurVIIRawValue.causeDeces1, {
        validators: [Validators.maxLength(255)],
      }),
      deces2Numero: new FormControl(ficheFacteurVIIRawValue.deces2Numero),
      lienParenteDeces2: new FormControl(ficheFacteurVIIRawValue.lienParenteDeces2),
      causeDeces2: new FormControl(ficheFacteurVIIRawValue.causeDeces2, {
        validators: [Validators.maxLength(255)],
      }),
      deces3Numero: new FormControl(ficheFacteurVIIRawValue.deces3Numero),
      lienParenteDeces3: new FormControl(ficheFacteurVIIRawValue.lienParenteDeces3),
      causeDeces3: new FormControl(ficheFacteurVIIRawValue.causeDeces3, {
        validators: [Validators.maxLength(255)],
      }),
      enqueteFamiliale: new FormControl(ficheFacteurVIIRawValue.enqueteFamiliale),
      decouverteFortuite: new FormControl(ficheFacteurVIIRawValue.decouverteFortuite),
      enqueteFamilialeBool: new FormControl(ficheFacteurVIIRawValue.enqueteFamilialeBool),
      explorationSyndromeHemorragique: new FormControl(ficheFacteurVIIRawValue.explorationSyndromeHemorragique),
      typeExplorationSyndromeHemorragique: new FormControl(ficheFacteurVIIRawValue.typeExplorationSyndromeHemorragique),
      datePremierSigneClinique: new FormControl(ficheFacteurVIIRawValue.datePremierSigneClinique),
      ageDiagnosticJour: new FormControl(ficheFacteurVIIRawValue.ageDiagnosticJour),
      ageDiagnosticMois: new FormControl(ficheFacteurVIIRawValue.ageDiagnosticMois),
      ageDiagnosticAnnee: new FormControl(ficheFacteurVIIRawValue.ageDiagnosticAnnee),
      hb: new FormControl(ficheFacteurVIIRawValue.hb),
      ht: new FormControl(ficheFacteurVIIRawValue.ht),
      plaquettes: new FormControl(ficheFacteurVIIRawValue.plaquettes),
      toPfa200ColEpi: new FormControl(ficheFacteurVIIRawValue.toPfa200ColEpi),
      toPfa200ColAdp: new FormControl(ficheFacteurVIIRawValue.toPfa200ColAdp),
      tp: new FormControl(ficheFacteurVIIRawValue.tp),
      tpMT: new FormControl(ficheFacteurVIIRawValue.tpMT),
      fibrinogene: new FormControl(ficheFacteurVIIRawValue.fibrinogene),
      tcaMT: new FormControl(ficheFacteurVIIRawValue.tcaMT),
      facteurVII: new FormControl(ficheFacteurVIIRawValue.facteurVII),
      dosageAutresFacteurs: new FormControl(ficheFacteurVIIRawValue.dosageAutresFacteurs, {
        validators: [Validators.maxLength(255)],
      }),
      confirmationMoleculaire: new FormControl(ficheFacteurVIIRawValue.confirmationMoleculaire),
      mutationPresence: new FormControl(ficheFacteurVIIRawValue.mutationPresence),
      mutationType: new FormControl(ficheFacteurVIIRawValue.mutationType),
      epistaxis: new FormControl(ficheFacteurVIIRawValue.epistaxis),
      frequenceEpistaxis: new FormControl(ficheFacteurVIIRawValue.frequenceEpistaxis),
      gingivorragies: new FormControl(ficheFacteurVIIRawValue.gingivorragies),
      typeGingivorragies: new FormControl(ficheFacteurVIIRawValue.typeGingivorragies),
      frequenceGingivorragies: new FormControl(ficheFacteurVIIRawValue.frequenceGingivorragies),
      ecchymose: new FormControl(ficheFacteurVIIRawValue.ecchymose),
      typeEcchymose: new FormControl(ficheFacteurVIIRawValue.typeEcchymose),
      frequenceEcchymose: new FormControl(ficheFacteurVIIRawValue.frequenceEcchymose),
      menorragie: new FormControl(ficheFacteurVIIRawValue.menorragie),
      frequenceMenorragie: new FormControl(ficheFacteurVIIRawValue.frequenceMenorragie),
      hematemese: new FormControl(ficheFacteurVIIRawValue.hematemese),
      frequenceHematemese: new FormControl(ficheFacteurVIIRawValue.frequenceHematemese),
      rectorragie: new FormControl(ficheFacteurVIIRawValue.rectorragie),
      frequenceRectorragie: new FormControl(ficheFacteurVIIRawValue.frequenceRectorragie),
      hematurie: new FormControl(ficheFacteurVIIRawValue.hematurie),
      frequenceHematurie: new FormControl(ficheFacteurVIIRawValue.frequenceHematurie),
      hematomeSousCutane: new FormControl(ficheFacteurVIIRawValue.hematomeSousCutane),
      typeHematomeSousCutane: new FormControl(ficheFacteurVIIRawValue.typeHematomeSousCutane),
      frequenceHematomeSousCutane: new FormControl(ficheFacteurVIIRawValue.frequenceHematomeSousCutane),
      hematomeMusculaire: new FormControl(ficheFacteurVIIRawValue.hematomeMusculaire),
      siegeHematomeMusculaire: new FormControl(ficheFacteurVIIRawValue.siegeHematomeMusculaire, {
        validators: [Validators.maxLength(255)],
      }),
      typeHematomeMusculaire: new FormControl(ficheFacteurVIIRawValue.typeHematomeMusculaire),
      frequenceHematomeMusculaire: new FormControl(ficheFacteurVIIRawValue.frequenceHematomeMusculaire),
      hemarthrose: new FormControl(ficheFacteurVIIRawValue.hemarthrose),
      typeHemarthrose: new FormControl(ficheFacteurVIIRawValue.typeHemarthrose),
      frequenceHemarthrose: new FormControl(ficheFacteurVIIRawValue.frequenceHemarthrose),
      saignementSNC: new FormControl(ficheFacteurVIIRawValue.saignementSNC),
      typeSaignementSNC: new FormControl(ficheFacteurVIIRawValue.typeSaignementSNC),
      frequenceSaignementSNC: new FormControl(ficheFacteurVIIRawValue.frequenceSaignementSNC),
      ruptureKystesHemorragiques: new FormControl(ficheFacteurVIIRawValue.ruptureKystesHemorragiques),
      hemorragiesPostChirurgicales: new FormControl(ficheFacteurVIIRawValue.hemorragiesPostChirurgicales),
      hemoperitone: new FormControl(ficheFacteurVIIRawValue.hemoperitone),
      accidentsThrombotiques: new FormControl(ficheFacteurVIIRawValue.accidentsThrombotiques),
      typeAccidentsThrombotiques: new FormControl(ficheFacteurVIIRawValue.typeAccidentsThrombotiques),
      hemorragieGrave: new FormControl(ficheFacteurVIIRawValue.hemorragieGrave),
      typeHemorragieGrave: new FormControl(ficheFacteurVIIRawValue.typeHemorragieGrave),
      chirurgiePrepare: new FormControl(ficheFacteurVIIRawValue.chirurgiePrepare),
      suiteOperatoireHemorragique: new FormControl(ficheFacteurVIIRawValue.suiteOperatoireHemorragique),
      circoncisionPrepare: new FormControl(ficheFacteurVIIRawValue.circoncisionPrepare),
      suiteOperatoireCirconcision: new FormControl(ficheFacteurVIIRawValue.suiteOperatoireCirconcision),
      ageCirconcision: new FormControl(ficheFacteurVIIRawValue.ageCirconcision),
      facteurVIIRecombinant: new FormControl(ficheFacteurVIIRawValue.facteurVIIRecombinant),
      plasmaFraisCongele: new FormControl(ficheFacteurVIIRawValue.plasmaFraisCongele),
      cryoprecipite: new FormControl(ficheFacteurVIIRawValue.cryoprecipite),
      culotsPlaquettaires: new FormControl(ficheFacteurVIIRawValue.culotsPlaquettaires),
      dicynone: new FormControl(ficheFacteurVIIRawValue.dicynone),
      exacyl: new FormControl(ficheFacteurVIIRawValue.exacyl),
      traitementMartial: new FormControl(ficheFacteurVIIRawValue.traitementMartial),
      traitementOestroprogestatif: new FormControl(ficheFacteurVIIRawValue.traitementOestroprogestatif),
      transfusionCGR: new FormControl(ficheFacteurVIIRawValue.transfusionCGR),
      inhibiteurs: new FormControl(ficheFacteurVIIRawValue.inhibiteurs),
      inhibiteurNeutralisant: new FormControl(ficheFacteurVIIRawValue.inhibiteurNeutralisant),
      titreUB: new FormControl(ficheFacteurVIIRawValue.titreUB),
      inhibiteurNonNeutralisant: new FormControl(ficheFacteurVIIRawValue.inhibiteurNonNeutralisant),
      infectionsVirales: new FormControl(ficheFacteurVIIRawValue.infectionsVirales),
      thrombose: new FormControl(ficheFacteurVIIRawValue.thrombose),
      reactionAllergique: new FormControl(ficheFacteurVIIRawValue.reactionAllergique),
      alloImmunisation: new FormControl(ficheFacteurVIIRawValue.alloImmunisation),
      adhesionTherapeutique: new FormControl(ficheFacteurVIIRawValue.adhesionTherapeutique),
      causeNonAdhesion: new FormControl(ficheFacteurVIIRawValue.causeNonAdhesion, {
        validators: [Validators.maxLength(255)],
      }),
      vieSociale: new FormControl(ficheFacteurVIIRawValue.vieSociale),
      mariage: new FormControl(ficheFacteurVIIRawValue.mariage),
      enfants: new FormControl(ficheFacteurVIIRawValue.enfants),
      activitePhysique: new FormControl(ficheFacteurVIIRawValue.activitePhysique),
      typeActivitePhysique: new FormControl(ficheFacteurVIIRawValue.typeActivitePhysique, {
        validators: [Validators.maxLength(255)],
      }),
      sequelles: new FormControl(ficheFacteurVIIRawValue.sequelles),
      typeSequelles: new FormControl(ficheFacteurVIIRawValue.typeSequelles, {
        validators: [Validators.maxLength(255)],
      }),
      patientDecede: new FormControl(ficheFacteurVIIRawValue.patientDecede),
      dateDeces: new FormControl(ficheFacteurVIIRawValue.dateDeces),
      ageDeces: new FormControl(ficheFacteurVIIRawValue.ageDeces),
      hemorragieSnc: new FormControl(ficheFacteurVIIRawValue.hemorragieSnc),
      hemorragieAutreQueSnc: new FormControl(ficheFacteurVIIRawValue.hemorragieAutreQueSnc),
      hiv: new FormControl(ficheFacteurVIIRawValue.hiv),
      autreInfection: new FormControl(ficheFacteurVIIRawValue.autreInfection),
      evenementThromboembolique: new FormControl(ficheFacteurVIIRawValue.evenementThromboembolique),
      cancer: new FormControl(ficheFacteurVIIRawValue.cancer),
      covid19: new FormControl(ficheFacteurVIIRawValue.covid19),
      inconnue: new FormControl(ficheFacteurVIIRawValue.inconnue),
      autreEvenement: new FormControl(ficheFacteurVIIRawValue.autreEvenement),
      autreEvenementDetails: new FormControl(ficheFacteurVIIRawValue.autreEvenementDetails, {
        validators: [Validators.maxLength(255)],
      }),
    });
  }

  getFicheFacteurVII(form: FicheFacteurVIIFormGroup): IFicheFacteurVII | NewFicheFacteurVII {
    return form.getRawValue() as IFicheFacteurVII | NewFicheFacteurVII;
  }

  resetForm(form: FicheFacteurVIIFormGroup, ficheFacteurVII: FicheFacteurVIIFormGroupInput): void {
    const ficheFacteurVIIRawValue = { ...this.getFormDefaults(), ...ficheFacteurVII };
    form.reset(
      {
        ...ficheFacteurVIIRawValue,
        id: { value: ficheFacteurVIIRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheFacteurVIIFormDefaults {
    return {
      id: null,
      decouverteFortuite: false,
      enqueteFamilialeBool: false,
      explorationSyndromeHemorragique: false,
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
