import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFicheFacteurXIII, NewFicheFacteurXIII } from '../fiche-facteur-xiii.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFicheFacteurXIII for edit and NewFicheFacteurXIIIFormGroupInput for create.
 */
type FicheFacteurXIIIFormGroupInput = IFicheFacteurXIII | PartialWithRequiredKeyOf<NewFicheFacteurXIII>;

type FicheFacteurXIIIFormDefaults = Pick<
  NewFicheFacteurXIII,
  | 'id'
  | 'enqueteFamiliale'
  | 'decouverteFortuite'
  | 'explorationSyndromeHemorragique'
  | 'hemorragieChuteCordon'
  | 'metroragieMenarche'
  | 'hemorragieCerebraleNN'
  | 'hemorragiePostPartum'
  | 'techniqueChromogenique'
  | 'techniqueSolubiliteCaillot'
  | 'confirmationMoleculaire'
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
  | 'ruptureKystesHemorragiques'
  | 'hemorragiesPostChirurgicales'
  | 'hemoperitone'
  | 'accidentsThrombotiques'
  | 'graveDeglobulisation'
  | 'graveEtatDeChoc'
  | 'graveParLocalisation'
  | 'inhibiteurNeutralisant'
  | 'inhibiteurNonNeutralisant'
  | 'thrombose'
  | 'reactionAllergique'
  | 'alloimmunisation'
  | 'causeDecesSNC'
  | 'causeDecesAutreHemorragie'
  | 'causeDecesVIH'
  | 'causeDecesAutreInfection'
  | 'causeDecesThromboembolique'
  | 'causeDecesCancer'
  | 'causeDecesCovid19'
  | 'causeDecesInconnue'
>;

type FicheFacteurXIIIFormGroupContent = {
  id: FormControl<IFicheFacteurXIII['id'] | NewFicheFacteurXIII['id']>;
  dossierNumber: FormControl<IFicheFacteurXIII['dossierNumber']>;
  ordreNumber: FormControl<IFicheFacteurXIII['ordreNumber']>;
  indexNumber: FormControl<IFicheFacteurXIII['indexNumber']>;
  prenom: FormControl<IFicheFacteurXIII['prenom']>;
  nom: FormControl<IFicheFacteurXIII['nom']>;
  nomJeuneFille: FormControl<IFicheFacteurXIII['nomJeuneFille']>;
  profession: FormControl<IFicheFacteurXIII['profession']>;
  etatCivil: FormControl<IFicheFacteurXIII['etatCivil']>;
  serviceClinique: FormControl<IFicheFacteurXIII['serviceClinique']>;
  autreServiceClinique: FormControl<IFicheFacteurXIII['autreServiceClinique']>;
  couvertureSociale: FormControl<IFicheFacteurXIII['couvertureSociale']>;
  sexe: FormControl<IFicheFacteurXIII['sexe']>;
  dateNaissance: FormControl<IFicheFacteurXIII['dateNaissance']>;
  ageActuel: FormControl<IFicheFacteurXIII['ageActuel']>;
  origine: FormControl<IFicheFacteurXIII['origine']>;
  autreOrigine: FormControl<IFicheFacteurXIII['autreOrigine']>;
  adresse: FormControl<IFicheFacteurXIII['adresse']>;
  telephone: FormControl<IFicheFacteurXIII['telephone']>;
  prenomPere: FormControl<IFicheFacteurXIII['prenomPere']>;
  professionPere: FormControl<IFicheFacteurXIII['professionPere']>;
  nomPrenomMere: FormControl<IFicheFacteurXIII['nomPrenomMere']>;
  professionMere: FormControl<IFicheFacteurXIII['professionMere']>;
  medecinTraitant: FormControl<IFicheFacteurXIII['medecinTraitant']>;
  serviceOrigine: FormControl<IFicheFacteurXIII['serviceOrigine']>;
  biologisteResponsableDg: FormControl<IFicheFacteurXIII['biologisteResponsableDg']>;
  diagnostic: FormControl<IFicheFacteurXIII['diagnostic']>;
  autreDiagnostic: FormControl<IFicheFacteurXIII['autreDiagnostic']>;
  anneeDiagnostic: FormControl<IFicheFacteurXIII['anneeDiagnostic']>;
  consentementRegistre: FormControl<IFicheFacteurXIII['consentementRegistre']>;
  dateEnregistrementRegistre: FormControl<IFicheFacteurXIII['dateEnregistrementRegistre']>;
  parentsConsanguins: FormControl<IFicheFacteurXIII['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFicheFacteurXIII['degreParenteConsanguins']>;
  casSimilaires: FormControl<IFicheFacteurXIII['casSimilaires']>;
  nombreCas: FormControl<IFicheFacteurXIII['nombreCas']>;
  cas1Numero: FormControl<IFicheFacteurXIII['cas1Numero']>;
  lienParenteCas1: FormControl<IFicheFacteurXIII['lienParenteCas1']>;
  degreParenteCas1: FormControl<IFicheFacteurXIII['degreParenteCas1']>;
  typeHemorragieCas1: FormControl<IFicheFacteurXIII['typeHemorragieCas1']>;
  cas2Numero: FormControl<IFicheFacteurXIII['cas2Numero']>;
  lienParenteCas2: FormControl<IFicheFacteurXIII['lienParenteCas2']>;
  degreParenteCas2: FormControl<IFicheFacteurXIII['degreParenteCas2']>;
  typeHemorragieCas2: FormControl<IFicheFacteurXIII['typeHemorragieCas2']>;
  cas3Numero: FormControl<IFicheFacteurXIII['cas3Numero']>;
  lienParenteCas3: FormControl<IFicheFacteurXIII['lienParenteCas3']>;
  degreParenteCas3: FormControl<IFicheFacteurXIII['degreParenteCas3']>;
  typeHemorragieCas3: FormControl<IFicheFacteurXIII['typeHemorragieCas3']>;
  decesSyndromeHemorragique: FormControl<IFicheFacteurXIII['decesSyndromeHemorragique']>;
  nombreDeces: FormControl<IFicheFacteurXIII['nombreDeces']>;
  deces1Numero: FormControl<IFicheFacteurXIII['deces1Numero']>;
  lienParenteDeces1: FormControl<IFicheFacteurXIII['lienParenteDeces1']>;
  causeDeces1: FormControl<IFicheFacteurXIII['causeDeces1']>;
  deces2Numero: FormControl<IFicheFacteurXIII['deces2Numero']>;
  lienParenteDeces2: FormControl<IFicheFacteurXIII['lienParenteDeces2']>;
  causeDeces2: FormControl<IFicheFacteurXIII['causeDeces2']>;
  deces3Numero: FormControl<IFicheFacteurXIII['deces3Numero']>;
  lienParenteDeces3: FormControl<IFicheFacteurXIII['lienParenteDeces3']>;
  causeDeces3: FormControl<IFicheFacteurXIII['causeDeces3']>;
  enqueteFamiliale: FormControl<IFicheFacteurXIII['enqueteFamiliale']>;
  decouverteFortuite: FormControl<IFicheFacteurXIII['decouverteFortuite']>;
  explorationSyndromeHemorragique: FormControl<IFicheFacteurXIII['explorationSyndromeHemorragique']>;
  hemorragieChuteCordon: FormControl<IFicheFacteurXIII['hemorragieChuteCordon']>;
  metroragieMenarche: FormControl<IFicheFacteurXIII['metroragieMenarche']>;
  hemorragieCerebraleNN: FormControl<IFicheFacteurXIII['hemorragieCerebraleNN']>;
  hemorragiePostPartum: FormControl<IFicheFacteurXIII['hemorragiePostPartum']>;
  autresCirconstances: FormControl<IFicheFacteurXIII['autresCirconstances']>;
  datePremierSigneCliniqueEvocateurJour: FormControl<IFicheFacteurXIII['datePremierSigneCliniqueEvocateurJour']>;
  datePremierSigneCliniqueEvocateurMois: FormControl<IFicheFacteurXIII['datePremierSigneCliniqueEvocateurMois']>;
  datePremierSigneCliniqueEvocateurAnnee: FormControl<IFicheFacteurXIII['datePremierSigneCliniqueEvocateurAnnee']>;
  ageDiagnosticJour: FormControl<IFicheFacteurXIII['ageDiagnosticJour']>;
  ageDiagnosticMois: FormControl<IFicheFacteurXIII['ageDiagnosticMois']>;
  ageDiagnosticAnnee: FormControl<IFicheFacteurXIII['ageDiagnosticAnnee']>;
  hb: FormControl<IFicheFacteurXIII['hb']>;
  ht: FormControl<IFicheFacteurXIII['ht']>;
  plaquettes: FormControl<IFicheFacteurXIII['plaquettes']>;
  tp: FormControl<IFicheFacteurXIII['tp']>;
  tpMT: FormControl<IFicheFacteurXIII['tpMT']>;
  tca: FormControl<IFicheFacteurXIII['tca']>;
  tcaMT: FormControl<IFicheFacteurXIII['tcaMT']>;
  fibrinogene: FormControl<IFicheFacteurXIII['fibrinogene']>;
  ts: FormControl<IFicheFacteurXIII['ts']>;
  toPfa200: FormControl<IFicheFacteurXIII['toPfa200']>;
  colEpi: FormControl<IFicheFacteurXIII['colEpi']>;
  colAdp: FormControl<IFicheFacteurXIII['colAdp']>;
  facteurXIII: FormControl<IFicheFacteurXIII['facteurXIII']>;
  techniqueChromogenique: FormControl<IFicheFacteurXIII['techniqueChromogenique']>;
  tauxChromogenique: FormControl<IFicheFacteurXIII['tauxChromogenique']>;
  techniqueSolubiliteCaillot: FormControl<IFicheFacteurXIII['techniqueSolubiliteCaillot']>;
  tauxSolubiliteCaillot: FormControl<IFicheFacteurXIII['tauxSolubiliteCaillot']>;
  confirmationMoleculaire: FormControl<IFicheFacteurXIII['confirmationMoleculaire']>;
  mutationPresente: FormControl<IFicheFacteurXIII['mutationPresente']>;
  mutationNom: FormControl<IFicheFacteurXIII['mutationNom']>;
  mutationType: FormControl<IFicheFacteurXIII['mutationType']>;
  mutation2Nom: FormControl<IFicheFacteurXIII['mutation2Nom']>;
  saignementChuteCordon: FormControl<IFicheFacteurXIII['saignementChuteCordon']>;
  cicatricesKeloides: FormControl<IFicheFacteurXIII['cicatricesKeloides']>;
  retardCicatrisation: FormControl<IFicheFacteurXIII['retardCicatrisation']>;
  epistaxis: FormControl<IFicheFacteurXIII['epistaxis']>;
  frequenceEpistaxis: FormControl<IFicheFacteurXIII['frequenceEpistaxis']>;
  gingivorragies: FormControl<IFicheFacteurXIII['gingivorragies']>;
  typeGingivorragies: FormControl<IFicheFacteurXIII['typeGingivorragies']>;
  frequenceGingivorragies: FormControl<IFicheFacteurXIII['frequenceGingivorragies']>;
  ecchymose: FormControl<IFicheFacteurXIII['ecchymose']>;
  typeEcchymose: FormControl<IFicheFacteurXIII['typeEcchymose']>;
  frequenceEcchymose: FormControl<IFicheFacteurXIII['frequenceEcchymose']>;
  menorragie: FormControl<IFicheFacteurXIII['menorragie']>;
  frequenceMenorragie: FormControl<IFicheFacteurXIII['frequenceMenorragie']>;
  hematemese: FormControl<IFicheFacteurXIII['hematemese']>;
  frequenceHematemese: FormControl<IFicheFacteurXIII['frequenceHematemese']>;
  rectorragie: FormControl<IFicheFacteurXIII['rectorragie']>;
  frequenceRectorragie: FormControl<IFicheFacteurXIII['frequenceRectorragie']>;
  hematurie: FormControl<IFicheFacteurXIII['hematurie']>;
  frequenceHematurie: FormControl<IFicheFacteurXIII['frequenceHematurie']>;
  hematomeSousCutane: FormControl<IFicheFacteurXIII['hematomeSousCutane']>;
  typeHematomeSousCutane: FormControl<IFicheFacteurXIII['typeHematomeSousCutane']>;
  frequenceHematomeSousCutane: FormControl<IFicheFacteurXIII['frequenceHematomeSousCutane']>;
  hematomeMusculaire: FormControl<IFicheFacteurXIII['hematomeMusculaire']>;
  siegeHematomeMusculaire: FormControl<IFicheFacteurXIII['siegeHematomeMusculaire']>;
  typeHematomeMusculaire: FormControl<IFicheFacteurXIII['typeHematomeMusculaire']>;
  frequenceHematomeMusculaire: FormControl<IFicheFacteurXIII['frequenceHematomeMusculaire']>;
  hemarthrose: FormControl<IFicheFacteurXIII['hemarthrose']>;
  typeHemarthrose: FormControl<IFicheFacteurXIII['typeHemarthrose']>;
  frequenceHemarthrose: FormControl<IFicheFacteurXIII['frequenceHemarthrose']>;
  saignementSNC: FormControl<IFicheFacteurXIII['saignementSNC']>;
  typeSaignementSNC: FormControl<IFicheFacteurXIII['typeSaignementSNC']>;
  frequenceSaignementSNC: FormControl<IFicheFacteurXIII['frequenceSaignementSNC']>;
  ruptureKystesHemorragiques: FormControl<IFicheFacteurXIII['ruptureKystesHemorragiques']>;
  hemorragiesPostChirurgicales: FormControl<IFicheFacteurXIII['hemorragiesPostChirurgicales']>;
  hemoperitone: FormControl<IFicheFacteurXIII['hemoperitone']>;
  accidentsThrombotiques: FormControl<IFicheFacteurXIII['accidentsThrombotiques']>;
  typeAccidentsThrombotiques: FormControl<IFicheFacteurXIII['typeAccidentsThrombotiques']>;
  hemorragieGrave: FormControl<IFicheFacteurXIII['hemorragieGrave']>;
  graveDeglobulisation: FormControl<IFicheFacteurXIII['graveDeglobulisation']>;
  graveEtatDeChoc: FormControl<IFicheFacteurXIII['graveEtatDeChoc']>;
  graveParLocalisation: FormControl<IFicheFacteurXIII['graveParLocalisation']>;
  siegeGraviteLocalisation: FormControl<IFicheFacteurXIII['siegeGraviteLocalisation']>;
  chirurgiePatientPrepare: FormControl<IFicheFacteurXIII['chirurgiePatientPrepare']>;
  chirurgieSuiteHemorragique: FormControl<IFicheFacteurXIII['chirurgieSuiteHemorragique']>;
  circoncisionPatientPrepare: FormControl<IFicheFacteurXIII['circoncisionPatientPrepare']>;
  circoncisionSuiteHemorragique: FormControl<IFicheFacteurXIII['circoncisionSuiteHemorragique']>;
  ageCirconcision: FormControl<IFicheFacteurXIII['ageCirconcision']>;
  concentreFacteurXIII: FormControl<IFicheFacteurXIII['concentreFacteurXIII']>;
  plasmaFraisCongele: FormControl<IFicheFacteurXIII['plasmaFraisCongele']>;
  cryoprecipite: FormControl<IFicheFacteurXIII['cryoprecipite']>;
  culotsPlaquettaires: FormControl<IFicheFacteurXIII['culotsPlaquettaires']>;
  dicynone: FormControl<IFicheFacteurXIII['dicynone']>;
  exacyl: FormControl<IFicheFacteurXIII['exacyl']>;
  traitementMartial: FormControl<IFicheFacteurXIII['traitementMartial']>;
  traitementOestroprogestatif: FormControl<IFicheFacteurXIII['traitementOestroprogestatif']>;
  transfusionCGR: FormControl<IFicheFacteurXIII['transfusionCGR']>;
  inhibiteurs: FormControl<IFicheFacteurXIII['inhibiteurs']>;
  inhibiteurNeutralisant: FormControl<IFicheFacteurXIII['inhibiteurNeutralisant']>;
  titreInhibiteurNeutralisant: FormControl<IFicheFacteurXIII['titreInhibiteurNeutralisant']>;
  inhibiteurNonNeutralisant: FormControl<IFicheFacteurXIII['inhibiteurNonNeutralisant']>;
  infectionsVirales: FormControl<IFicheFacteurXIII['infectionsVirales']>;
  hepatiteB: FormControl<IFicheFacteurXIII['hepatiteB']>;
  hepatiteC: FormControl<IFicheFacteurXIII['hepatiteC']>;
  vih: FormControl<IFicheFacteurXIII['vih']>;
  thrombose: FormControl<IFicheFacteurXIII['thrombose']>;
  reactionAllergique: FormControl<IFicheFacteurXIII['reactionAllergique']>;
  alloimmunisation: FormControl<IFicheFacteurXIII['alloimmunisation']>;
  autresComplications: FormControl<IFicheFacteurXIII['autresComplications']>;
  adherenceTherapeutique: FormControl<IFicheFacteurXIII['adherenceTherapeutique']>;
  causeNonAdherence: FormControl<IFicheFacteurXIII['causeNonAdherence']>;
  vieSociale: FormControl<IFicheFacteurXIII['vieSociale']>;
  marie: FormControl<IFicheFacteurXIII['marie']>;
  enfants: FormControl<IFicheFacteurXIII['enfants']>;
  activitePhysique: FormControl<IFicheFacteurXIII['activitePhysique']>;
  typeActivitePhysique: FormControl<IFicheFacteurXIII['typeActivitePhysique']>;
  sequelles: FormControl<IFicheFacteurXIII['sequelles']>;
  typeSequelles: FormControl<IFicheFacteurXIII['typeSequelles']>;
  patientDecede: FormControl<IFicheFacteurXIII['patientDecede']>;
  dateDeces: FormControl<IFicheFacteurXIII['dateDeces']>;
  ageDeces: FormControl<IFicheFacteurXIII['ageDeces']>;
  causeDecesSNC: FormControl<IFicheFacteurXIII['causeDecesSNC']>;
  causeDecesAutreHemorragie: FormControl<IFicheFacteurXIII['causeDecesAutreHemorragie']>;
  causeDecesVIH: FormControl<IFicheFacteurXIII['causeDecesVIH']>;
  causeDecesAutreInfection: FormControl<IFicheFacteurXIII['causeDecesAutreInfection']>;
  causeDecesThromboembolique: FormControl<IFicheFacteurXIII['causeDecesThromboembolique']>;
  causeDecesCancer: FormControl<IFicheFacteurXIII['causeDecesCancer']>;
  causeDecesCovid19: FormControl<IFicheFacteurXIII['causeDecesCovid19']>;
  causeDecesInconnue: FormControl<IFicheFacteurXIII['causeDecesInconnue']>;
  autreCauseDeces: FormControl<IFicheFacteurXIII['autreCauseDeces']>;
};

export type FicheFacteurXIIIFormGroup = FormGroup<FicheFacteurXIIIFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheFacteurXIIIFormService {
  createFicheFacteurXIIIFormGroup(ficheFacteurXIII: FicheFacteurXIIIFormGroupInput = { id: null }): FicheFacteurXIIIFormGroup {
    const ficheFacteurXIIIRawValue = {
      ...this.getFormDefaults(),
      ...ficheFacteurXIII,
    };
    return new FormGroup<FicheFacteurXIIIFormGroupContent>({
      id: new FormControl(
        { value: ficheFacteurXIIIRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dossierNumber: new FormControl(ficheFacteurXIIIRawValue.dossierNumber),
      ordreNumber: new FormControl(ficheFacteurXIIIRawValue.ordreNumber, {
        validators: [Validators.maxLength(25)],
      }),
      indexNumber: new FormControl(ficheFacteurXIIIRawValue.indexNumber, {
        validators: [Validators.maxLength(25)],
      }),
      prenom: new FormControl(ficheFacteurXIIIRawValue.prenom, {
        validators: [Validators.maxLength(25)],
      }),
      nom: new FormControl(ficheFacteurXIIIRawValue.nom, {
        validators: [Validators.maxLength(25)],
      }),
      nomJeuneFille: new FormControl(ficheFacteurXIIIRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(25)],
      }),
      profession: new FormControl(ficheFacteurXIIIRawValue.profession, {
        validators: [Validators.maxLength(25)],
      }),
      etatCivil: new FormControl(ficheFacteurXIIIRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      serviceClinique: new FormControl(ficheFacteurXIIIRawValue.serviceClinique, {
        validators: [Validators.required],
      }),
      autreServiceClinique: new FormControl(ficheFacteurXIIIRawValue.autreServiceClinique, {
        validators: [Validators.maxLength(255)],
      }),
      couvertureSociale: new FormControl(ficheFacteurXIIIRawValue.couvertureSociale, {
        validators: [Validators.required],
      }),
      sexe: new FormControl(ficheFacteurXIIIRawValue.sexe, {
        validators: [Validators.required],
      }),
      dateNaissance: new FormControl(ficheFacteurXIIIRawValue.dateNaissance),
      ageActuel: new FormControl(ficheFacteurXIIIRawValue.ageActuel),
      origine: new FormControl(ficheFacteurXIIIRawValue.origine),
      autreOrigine: new FormControl(ficheFacteurXIIIRawValue.autreOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      adresse: new FormControl(ficheFacteurXIIIRawValue.adresse, {
        validators: [Validators.maxLength(25)],
      }),
      telephone: new FormControl(ficheFacteurXIIIRawValue.telephone, {
        validators: [Validators.maxLength(20)],
      }),
      prenomPere: new FormControl(ficheFacteurXIIIRawValue.prenomPere, {
        validators: [Validators.maxLength(25)],
      }),
      professionPere: new FormControl(ficheFacteurXIIIRawValue.professionPere, {
        validators: [Validators.maxLength(25)],
      }),
      nomPrenomMere: new FormControl(ficheFacteurXIIIRawValue.nomPrenomMere, {
        validators: [Validators.maxLength(25)],
      }),
      professionMere: new FormControl(ficheFacteurXIIIRawValue.professionMere, {
        validators: [Validators.maxLength(25)],
      }),
      medecinTraitant: new FormControl(ficheFacteurXIIIRawValue.medecinTraitant, {
        validators: [Validators.maxLength(25)],
      }),
      serviceOrigine: new FormControl(ficheFacteurXIIIRawValue.serviceOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      biologisteResponsableDg: new FormControl(ficheFacteurXIIIRawValue.biologisteResponsableDg, {
        validators: [Validators.maxLength(25)],
      }),
      diagnostic: new FormControl(ficheFacteurXIIIRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(ficheFacteurXIIIRawValue.autreDiagnostic, {
        validators: [Validators.maxLength(25)],
      }),
      anneeDiagnostic: new FormControl(ficheFacteurXIIIRawValue.anneeDiagnostic),
      consentementRegistre: new FormControl(ficheFacteurXIIIRawValue.consentementRegistre),
      dateEnregistrementRegistre: new FormControl(ficheFacteurXIIIRawValue.dateEnregistrementRegistre),
      parentsConsanguins: new FormControl(ficheFacteurXIIIRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheFacteurXIIIRawValue.degreParenteConsanguins),
      casSimilaires: new FormControl(ficheFacteurXIIIRawValue.casSimilaires),
      nombreCas: new FormControl(ficheFacteurXIIIRawValue.nombreCas),
      cas1Numero: new FormControl(ficheFacteurXIIIRawValue.cas1Numero),
      lienParenteCas1: new FormControl(ficheFacteurXIIIRawValue.lienParenteCas1),
      degreParenteCas1: new FormControl(ficheFacteurXIIIRawValue.degreParenteCas1, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas1: new FormControl(ficheFacteurXIIIRawValue.typeHemorragieCas1, {
        validators: [Validators.maxLength(255)],
      }),
      cas2Numero: new FormControl(ficheFacteurXIIIRawValue.cas2Numero),
      lienParenteCas2: new FormControl(ficheFacteurXIIIRawValue.lienParenteCas2),
      degreParenteCas2: new FormControl(ficheFacteurXIIIRawValue.degreParenteCas2, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas2: new FormControl(ficheFacteurXIIIRawValue.typeHemorragieCas2, {
        validators: [Validators.maxLength(255)],
      }),
      cas3Numero: new FormControl(ficheFacteurXIIIRawValue.cas3Numero),
      lienParenteCas3: new FormControl(ficheFacteurXIIIRawValue.lienParenteCas3),
      degreParenteCas3: new FormControl(ficheFacteurXIIIRawValue.degreParenteCas3, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas3: new FormControl(ficheFacteurXIIIRawValue.typeHemorragieCas3, {
        validators: [Validators.maxLength(255)],
      }),
      decesSyndromeHemorragique: new FormControl(ficheFacteurXIIIRawValue.decesSyndromeHemorragique),
      nombreDeces: new FormControl(ficheFacteurXIIIRawValue.nombreDeces),
      deces1Numero: new FormControl(ficheFacteurXIIIRawValue.deces1Numero),
      lienParenteDeces1: new FormControl(ficheFacteurXIIIRawValue.lienParenteDeces1),
      causeDeces1: new FormControl(ficheFacteurXIIIRawValue.causeDeces1, {
        validators: [Validators.maxLength(255)],
      }),
      deces2Numero: new FormControl(ficheFacteurXIIIRawValue.deces2Numero),
      lienParenteDeces2: new FormControl(ficheFacteurXIIIRawValue.lienParenteDeces2),
      causeDeces2: new FormControl(ficheFacteurXIIIRawValue.causeDeces2, {
        validators: [Validators.maxLength(255)],
      }),
      deces3Numero: new FormControl(ficheFacteurXIIIRawValue.deces3Numero),
      lienParenteDeces3: new FormControl(ficheFacteurXIIIRawValue.lienParenteDeces3),
      causeDeces3: new FormControl(ficheFacteurXIIIRawValue.causeDeces3, {
        validators: [Validators.maxLength(255)],
      }),
      enqueteFamiliale: new FormControl(ficheFacteurXIIIRawValue.enqueteFamiliale),
      decouverteFortuite: new FormControl(ficheFacteurXIIIRawValue.decouverteFortuite),
      explorationSyndromeHemorragique: new FormControl(ficheFacteurXIIIRawValue.explorationSyndromeHemorragique),
      hemorragieChuteCordon: new FormControl(ficheFacteurXIIIRawValue.hemorragieChuteCordon),
      metroragieMenarche: new FormControl(ficheFacteurXIIIRawValue.metroragieMenarche),
      hemorragieCerebraleNN: new FormControl(ficheFacteurXIIIRawValue.hemorragieCerebraleNN),
      hemorragiePostPartum: new FormControl(ficheFacteurXIIIRawValue.hemorragiePostPartum),
      autresCirconstances: new FormControl(ficheFacteurXIIIRawValue.autresCirconstances, {
        validators: [Validators.maxLength(255)],
      }),
      datePremierSigneCliniqueEvocateurJour: new FormControl(ficheFacteurXIIIRawValue.datePremierSigneCliniqueEvocateurJour),
      datePremierSigneCliniqueEvocateurMois: new FormControl(ficheFacteurXIIIRawValue.datePremierSigneCliniqueEvocateurMois),
      datePremierSigneCliniqueEvocateurAnnee: new FormControl(ficheFacteurXIIIRawValue.datePremierSigneCliniqueEvocateurAnnee),
      ageDiagnosticJour: new FormControl(ficheFacteurXIIIRawValue.ageDiagnosticJour),
      ageDiagnosticMois: new FormControl(ficheFacteurXIIIRawValue.ageDiagnosticMois),
      ageDiagnosticAnnee: new FormControl(ficheFacteurXIIIRawValue.ageDiagnosticAnnee),
      hb: new FormControl(ficheFacteurXIIIRawValue.hb),
      ht: new FormControl(ficheFacteurXIIIRawValue.ht),
      plaquettes: new FormControl(ficheFacteurXIIIRawValue.plaquettes),
      tp: new FormControl(ficheFacteurXIIIRawValue.tp),
      tpMT: new FormControl(ficheFacteurXIIIRawValue.tpMT),
      tca: new FormControl(ficheFacteurXIIIRawValue.tca),
      tcaMT: new FormControl(ficheFacteurXIIIRawValue.tcaMT),
      fibrinogene: new FormControl(ficheFacteurXIIIRawValue.fibrinogene),
      ts: new FormControl(ficheFacteurXIIIRawValue.ts),
      toPfa200: new FormControl(ficheFacteurXIIIRawValue.toPfa200),
      colEpi: new FormControl(ficheFacteurXIIIRawValue.colEpi),
      colAdp: new FormControl(ficheFacteurXIIIRawValue.colAdp),
      facteurXIII: new FormControl(ficheFacteurXIIIRawValue.facteurXIII),
      techniqueChromogenique: new FormControl(ficheFacteurXIIIRawValue.techniqueChromogenique),
      tauxChromogenique: new FormControl(ficheFacteurXIIIRawValue.tauxChromogenique),
      techniqueSolubiliteCaillot: new FormControl(ficheFacteurXIIIRawValue.techniqueSolubiliteCaillot),
      tauxSolubiliteCaillot: new FormControl(ficheFacteurXIIIRawValue.tauxSolubiliteCaillot),
      confirmationMoleculaire: new FormControl(ficheFacteurXIIIRawValue.confirmationMoleculaire),
      mutationPresente: new FormControl(ficheFacteurXIIIRawValue.mutationPresente),
      mutationNom: new FormControl(ficheFacteurXIIIRawValue.mutationNom, {
        validators: [Validators.maxLength(255)],
      }),
      mutationType: new FormControl(ficheFacteurXIIIRawValue.mutationType),
      mutation2Nom: new FormControl(ficheFacteurXIIIRawValue.mutation2Nom, {
        validators: [Validators.maxLength(255)],
      }),
      saignementChuteCordon: new FormControl(ficheFacteurXIIIRawValue.saignementChuteCordon),
      cicatricesKeloides: new FormControl(ficheFacteurXIIIRawValue.cicatricesKeloides),
      retardCicatrisation: new FormControl(ficheFacteurXIIIRawValue.retardCicatrisation),
      epistaxis: new FormControl(ficheFacteurXIIIRawValue.epistaxis),
      frequenceEpistaxis: new FormControl(ficheFacteurXIIIRawValue.frequenceEpistaxis),
      gingivorragies: new FormControl(ficheFacteurXIIIRawValue.gingivorragies),
      typeGingivorragies: new FormControl(ficheFacteurXIIIRawValue.typeGingivorragies),
      frequenceGingivorragies: new FormControl(ficheFacteurXIIIRawValue.frequenceGingivorragies),
      ecchymose: new FormControl(ficheFacteurXIIIRawValue.ecchymose),
      typeEcchymose: new FormControl(ficheFacteurXIIIRawValue.typeEcchymose),
      frequenceEcchymose: new FormControl(ficheFacteurXIIIRawValue.frequenceEcchymose),
      menorragie: new FormControl(ficheFacteurXIIIRawValue.menorragie),
      frequenceMenorragie: new FormControl(ficheFacteurXIIIRawValue.frequenceMenorragie),
      hematemese: new FormControl(ficheFacteurXIIIRawValue.hematemese),
      frequenceHematemese: new FormControl(ficheFacteurXIIIRawValue.frequenceHematemese),
      rectorragie: new FormControl(ficheFacteurXIIIRawValue.rectorragie),
      frequenceRectorragie: new FormControl(ficheFacteurXIIIRawValue.frequenceRectorragie),
      hematurie: new FormControl(ficheFacteurXIIIRawValue.hematurie),
      frequenceHematurie: new FormControl(ficheFacteurXIIIRawValue.frequenceHematurie),
      hematomeSousCutane: new FormControl(ficheFacteurXIIIRawValue.hematomeSousCutane),
      typeHematomeSousCutane: new FormControl(ficheFacteurXIIIRawValue.typeHematomeSousCutane),
      frequenceHematomeSousCutane: new FormControl(ficheFacteurXIIIRawValue.frequenceHematomeSousCutane),
      hematomeMusculaire: new FormControl(ficheFacteurXIIIRawValue.hematomeMusculaire),
      siegeHematomeMusculaire: new FormControl(ficheFacteurXIIIRawValue.siegeHematomeMusculaire, {
        validators: [Validators.maxLength(255)],
      }),
      typeHematomeMusculaire: new FormControl(ficheFacteurXIIIRawValue.typeHematomeMusculaire),
      frequenceHematomeMusculaire: new FormControl(ficheFacteurXIIIRawValue.frequenceHematomeMusculaire),
      hemarthrose: new FormControl(ficheFacteurXIIIRawValue.hemarthrose),
      typeHemarthrose: new FormControl(ficheFacteurXIIIRawValue.typeHemarthrose),
      frequenceHemarthrose: new FormControl(ficheFacteurXIIIRawValue.frequenceHemarthrose),
      saignementSNC: new FormControl(ficheFacteurXIIIRawValue.saignementSNC),
      typeSaignementSNC: new FormControl(ficheFacteurXIIIRawValue.typeSaignementSNC),
      frequenceSaignementSNC: new FormControl(ficheFacteurXIIIRawValue.frequenceSaignementSNC),
      ruptureKystesHemorragiques: new FormControl(ficheFacteurXIIIRawValue.ruptureKystesHemorragiques),
      hemorragiesPostChirurgicales: new FormControl(ficheFacteurXIIIRawValue.hemorragiesPostChirurgicales),
      hemoperitone: new FormControl(ficheFacteurXIIIRawValue.hemoperitone),
      accidentsThrombotiques: new FormControl(ficheFacteurXIIIRawValue.accidentsThrombotiques),
      typeAccidentsThrombotiques: new FormControl(ficheFacteurXIIIRawValue.typeAccidentsThrombotiques),
      hemorragieGrave: new FormControl(ficheFacteurXIIIRawValue.hemorragieGrave),
      graveDeglobulisation: new FormControl(ficheFacteurXIIIRawValue.graveDeglobulisation),
      graveEtatDeChoc: new FormControl(ficheFacteurXIIIRawValue.graveEtatDeChoc),
      graveParLocalisation: new FormControl(ficheFacteurXIIIRawValue.graveParLocalisation),
      siegeGraviteLocalisation: new FormControl(ficheFacteurXIIIRawValue.siegeGraviteLocalisation, {
        validators: [Validators.maxLength(255)],
      }),
      chirurgiePatientPrepare: new FormControl(ficheFacteurXIIIRawValue.chirurgiePatientPrepare),
      chirurgieSuiteHemorragique: new FormControl(ficheFacteurXIIIRawValue.chirurgieSuiteHemorragique),
      circoncisionPatientPrepare: new FormControl(ficheFacteurXIIIRawValue.circoncisionPatientPrepare),
      circoncisionSuiteHemorragique: new FormControl(ficheFacteurXIIIRawValue.circoncisionSuiteHemorragique),
      ageCirconcision: new FormControl(ficheFacteurXIIIRawValue.ageCirconcision),
      concentreFacteurXIII: new FormControl(ficheFacteurXIIIRawValue.concentreFacteurXIII),
      plasmaFraisCongele: new FormControl(ficheFacteurXIIIRawValue.plasmaFraisCongele),
      cryoprecipite: new FormControl(ficheFacteurXIIIRawValue.cryoprecipite),
      culotsPlaquettaires: new FormControl(ficheFacteurXIIIRawValue.culotsPlaquettaires),
      dicynone: new FormControl(ficheFacteurXIIIRawValue.dicynone),
      exacyl: new FormControl(ficheFacteurXIIIRawValue.exacyl),
      traitementMartial: new FormControl(ficheFacteurXIIIRawValue.traitementMartial),
      traitementOestroprogestatif: new FormControl(ficheFacteurXIIIRawValue.traitementOestroprogestatif),
      transfusionCGR: new FormControl(ficheFacteurXIIIRawValue.transfusionCGR),
      inhibiteurs: new FormControl(ficheFacteurXIIIRawValue.inhibiteurs),
      inhibiteurNeutralisant: new FormControl(ficheFacteurXIIIRawValue.inhibiteurNeutralisant),
      titreInhibiteurNeutralisant: new FormControl(ficheFacteurXIIIRawValue.titreInhibiteurNeutralisant),
      inhibiteurNonNeutralisant: new FormControl(ficheFacteurXIIIRawValue.inhibiteurNonNeutralisant),
      infectionsVirales: new FormControl(ficheFacteurXIIIRawValue.infectionsVirales),
      hepatiteB: new FormControl(ficheFacteurXIIIRawValue.hepatiteB),
      hepatiteC: new FormControl(ficheFacteurXIIIRawValue.hepatiteC),
      vih: new FormControl(ficheFacteurXIIIRawValue.vih),
      thrombose: new FormControl(ficheFacteurXIIIRawValue.thrombose),
      reactionAllergique: new FormControl(ficheFacteurXIIIRawValue.reactionAllergique),
      alloimmunisation: new FormControl(ficheFacteurXIIIRawValue.alloimmunisation),
      autresComplications: new FormControl(ficheFacteurXIIIRawValue.autresComplications, {
        validators: [Validators.maxLength(255)],
      }),
      adherenceTherapeutique: new FormControl(ficheFacteurXIIIRawValue.adherenceTherapeutique),
      causeNonAdherence: new FormControl(ficheFacteurXIIIRawValue.causeNonAdherence, {
        validators: [Validators.maxLength(255)],
      }),
      vieSociale: new FormControl(ficheFacteurXIIIRawValue.vieSociale),
      marie: new FormControl(ficheFacteurXIIIRawValue.marie),
      enfants: new FormControl(ficheFacteurXIIIRawValue.enfants),
      activitePhysique: new FormControl(ficheFacteurXIIIRawValue.activitePhysique),
      typeActivitePhysique: new FormControl(ficheFacteurXIIIRawValue.typeActivitePhysique, {
        validators: [Validators.maxLength(255)],
      }),
      sequelles: new FormControl(ficheFacteurXIIIRawValue.sequelles),
      typeSequelles: new FormControl(ficheFacteurXIIIRawValue.typeSequelles, {
        validators: [Validators.maxLength(255)],
      }),
      patientDecede: new FormControl(ficheFacteurXIIIRawValue.patientDecede),
      dateDeces: new FormControl(ficheFacteurXIIIRawValue.dateDeces),
      ageDeces: new FormControl(ficheFacteurXIIIRawValue.ageDeces),
      causeDecesSNC: new FormControl(ficheFacteurXIIIRawValue.causeDecesSNC),
      causeDecesAutreHemorragie: new FormControl(ficheFacteurXIIIRawValue.causeDecesAutreHemorragie),
      causeDecesVIH: new FormControl(ficheFacteurXIIIRawValue.causeDecesVIH),
      causeDecesAutreInfection: new FormControl(ficheFacteurXIIIRawValue.causeDecesAutreInfection),
      causeDecesThromboembolique: new FormControl(ficheFacteurXIIIRawValue.causeDecesThromboembolique),
      causeDecesCancer: new FormControl(ficheFacteurXIIIRawValue.causeDecesCancer),
      causeDecesCovid19: new FormControl(ficheFacteurXIIIRawValue.causeDecesCovid19),
      causeDecesInconnue: new FormControl(ficheFacteurXIIIRawValue.causeDecesInconnue),
      autreCauseDeces: new FormControl(ficheFacteurXIIIRawValue.autreCauseDeces, {
        validators: [Validators.maxLength(255)],
      }),
    });
  }

  getFicheFacteurXIII(form: FicheFacteurXIIIFormGroup): IFicheFacteurXIII | NewFicheFacteurXIII {
    return form.getRawValue() as IFicheFacteurXIII | NewFicheFacteurXIII;
  }

  resetForm(form: FicheFacteurXIIIFormGroup, ficheFacteurXIII: FicheFacteurXIIIFormGroupInput): void {
    const ficheFacteurXIIIRawValue = { ...this.getFormDefaults(), ...ficheFacteurXIII };
    form.reset(
      {
        ...ficheFacteurXIIIRawValue,
        id: { value: ficheFacteurXIIIRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheFacteurXIIIFormDefaults {
    return {
      id: null,
      enqueteFamiliale: false,
      decouverteFortuite: false,
      explorationSyndromeHemorragique: false,
      hemorragieChuteCordon: false,
      metroragieMenarche: false,
      hemorragieCerebraleNN: false,
      hemorragiePostPartum: false,
      techniqueChromogenique: false,
      techniqueSolubiliteCaillot: false,
      confirmationMoleculaire: false,
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
      ruptureKystesHemorragiques: false,
      hemorragiesPostChirurgicales: false,
      hemoperitone: false,
      accidentsThrombotiques: false,
      graveDeglobulisation: false,
      graveEtatDeChoc: false,
      graveParLocalisation: false,
      inhibiteurNeutralisant: false,
      inhibiteurNonNeutralisant: false,
      thrombose: false,
      reactionAllergique: false,
      alloimmunisation: false,
      causeDecesSNC: false,
      causeDecesAutreHemorragie: false,
      causeDecesVIH: false,
      causeDecesAutreInfection: false,
      causeDecesThromboembolique: false,
      causeDecesCancer: false,
      causeDecesCovid19: false,
      causeDecesInconnue: false,
    };
  }
}
