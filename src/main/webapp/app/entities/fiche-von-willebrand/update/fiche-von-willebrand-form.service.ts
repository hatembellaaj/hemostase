import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFicheVonWillebrand, NewFicheVonWillebrand } from '../fiche-von-willebrand.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFicheVonWillebrand for edit and NewFicheVonWillebrandFormGroupInput for create.
 */
type FicheVonWillebrandFormGroupInput = IFicheVonWillebrand | PartialWithRequiredKeyOf<NewFicheVonWillebrand>;

type FicheVonWillebrandFormDefaults = Pick<
  NewFicheVonWillebrand,
  | 'id'
  | 'mutationPresente'
  | 'ecchymosesSpontanees'
  | 'ecchymosesProvoquees'
  | 'scoreHighamFait'
  | 'hematomeSpontane'
  | 'hematomeProvoque'
  | 'hematomeSousCutane'
  | 'hematomeMusculaire'
  | 'hematomeTissusMous'
  | 'hemarthroseSpontane'
  | 'hemarthroseProvoque'
  | 'avulsionPrepare'
  | 'hemorragieVisceresSpontane'
  | 'hemorragieVisceresProvoque'
  | 'explorationVisceres'
  | 'graveDeglobulisation'
  | 'graveEtatDeChoc'
  | 'graveParLocalisation'
  | 'voieOrale'
  | 'voieIV'
  | 'voieLocale'
  | 'anemieSevere'
  | 'thrombose'
  | 'reactionAllergique'
  | 'autreComplication'
>;

type FicheVonWillebrandFormGroupContent = {
  id: FormControl<IFicheVonWillebrand['id'] | NewFicheVonWillebrand['id']>;
  dossierNumber: FormControl<IFicheVonWillebrand['dossierNumber']>;
  ordreNumber: FormControl<IFicheVonWillebrand['ordreNumber']>;
  indexNumber: FormControl<IFicheVonWillebrand['indexNumber']>;
  prenom: FormControl<IFicheVonWillebrand['prenom']>;
  nom: FormControl<IFicheVonWillebrand['nom']>;
  nomJeuneFille: FormControl<IFicheVonWillebrand['nomJeuneFille']>;
  profession: FormControl<IFicheVonWillebrand['profession']>;
  etatCivil: FormControl<IFicheVonWillebrand['etatCivil']>;
  serviceClinique: FormControl<IFicheVonWillebrand['serviceClinique']>;
  autreServiceClinique: FormControl<IFicheVonWillebrand['autreServiceClinique']>;
  couvertureSociale: FormControl<IFicheVonWillebrand['couvertureSociale']>;
  sexe: FormControl<IFicheVonWillebrand['sexe']>;
  dateNaissance: FormControl<IFicheVonWillebrand['dateNaissance']>;
  ageActuel: FormControl<IFicheVonWillebrand['ageActuel']>;
  origine: FormControl<IFicheVonWillebrand['origine']>;
  autreOrigine: FormControl<IFicheVonWillebrand['autreOrigine']>;
  adresse: FormControl<IFicheVonWillebrand['adresse']>;
  telephone: FormControl<IFicheVonWillebrand['telephone']>;
  prenomPere: FormControl<IFicheVonWillebrand['prenomPere']>;
  professionPere: FormControl<IFicheVonWillebrand['professionPere']>;
  nomPrenomMere: FormControl<IFicheVonWillebrand['nomPrenomMere']>;
  professionMere: FormControl<IFicheVonWillebrand['professionMere']>;
  medecinTraitant: FormControl<IFicheVonWillebrand['medecinTraitant']>;
  serviceOrigine: FormControl<IFicheVonWillebrand['serviceOrigine']>;
  biologisteResponsableDg: FormControl<IFicheVonWillebrand['biologisteResponsableDg']>;
  diagnostic: FormControl<IFicheVonWillebrand['diagnostic']>;
  autreDiagnostic: FormControl<IFicheVonWillebrand['autreDiagnostic']>;
  anneeDiagnostic: FormControl<IFicheVonWillebrand['anneeDiagnostic']>;
  dateEnregistrementRegistre: FormControl<IFicheVonWillebrand['dateEnregistrementRegistre']>;
  consentementRegistre: FormControl<IFicheVonWillebrand['consentementRegistre']>;
  parentsConsanguins: FormControl<IFicheVonWillebrand['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFicheVonWillebrand['degreParenteConsanguins']>;
  casSimilaires: FormControl<IFicheVonWillebrand['casSimilaires']>;
  nombreCas: FormControl<IFicheVonWillebrand['nombreCas']>;
  cas1Numero: FormControl<IFicheVonWillebrand['cas1Numero']>;
  lienParenteCas1: FormControl<IFicheVonWillebrand['lienParenteCas1']>;
  degreParenteCas1: FormControl<IFicheVonWillebrand['degreParenteCas1']>;
  typeHemorragieCas1: FormControl<IFicheVonWillebrand['typeHemorragieCas1']>;
  cas2Numero: FormControl<IFicheVonWillebrand['cas2Numero']>;
  lienParenteCas2: FormControl<IFicheVonWillebrand['lienParenteCas2']>;
  degreParenteCas2: FormControl<IFicheVonWillebrand['degreParenteCas2']>;
  typeHemorragieCas2: FormControl<IFicheVonWillebrand['typeHemorragieCas2']>;
  cas3Numero: FormControl<IFicheVonWillebrand['cas3Numero']>;
  lienParenteCas3: FormControl<IFicheVonWillebrand['lienParenteCas3']>;
  degreParenteCas3: FormControl<IFicheVonWillebrand['degreParenteCas3']>;
  typeHemorragieCas3: FormControl<IFicheVonWillebrand['typeHemorragieCas3']>;
  decesSyndromeHemorragique: FormControl<IFicheVonWillebrand['decesSyndromeHemorragique']>;
  nombreDeces: FormControl<IFicheVonWillebrand['nombreDeces']>;
  deces1Numero: FormControl<IFicheVonWillebrand['deces1Numero']>;
  lienParenteDeces1: FormControl<IFicheVonWillebrand['lienParenteDeces1']>;
  causeDeces1: FormControl<IFicheVonWillebrand['causeDeces1']>;
  deces2Numero: FormControl<IFicheVonWillebrand['deces2Numero']>;
  lienParenteDeces2: FormControl<IFicheVonWillebrand['lienParenteDeces2']>;
  causeDeces2: FormControl<IFicheVonWillebrand['causeDeces2']>;
  deces3Numero: FormControl<IFicheVonWillebrand['deces3Numero']>;
  lienParenteDeces3: FormControl<IFicheVonWillebrand['lienParenteDeces3']>;
  causeDeces3: FormControl<IFicheVonWillebrand['causeDeces3']>;
  enqueteFamiliale: FormControl<IFicheVonWillebrand['enqueteFamiliale']>;
  circonstancesDecouverte: FormControl<IFicheVonWillebrand['circonstancesDecouverte']>;
  circonstancesDecouvertePrecision: FormControl<IFicheVonWillebrand['circonstancesDecouvertePrecision']>;
  jamaisSaigne: FormControl<IFicheVonWillebrand['jamaisSaigne']>;
  agePremierSigneCliniqueJours: FormControl<IFicheVonWillebrand['agePremierSigneCliniqueJours']>;
  agePremierSigneCliniqueMois: FormControl<IFicheVonWillebrand['agePremierSigneCliniqueMois']>;
  agePremierSigneCliniqueAnnees: FormControl<IFicheVonWillebrand['agePremierSigneCliniqueAnnees']>;
  symptomeEcchymose: FormControl<IFicheVonWillebrand['symptomeEcchymose']>;
  symptomeEpistaxis: FormControl<IFicheVonWillebrand['symptomeEpistaxis']>;
  symptomeGingivorragies: FormControl<IFicheVonWillebrand['symptomeGingivorragies']>;
  symptomeMetrorragies: FormControl<IFicheVonWillebrand['symptomeMetrorragies']>;
  symptomeMelena: FormControl<IFicheVonWillebrand['symptomeMelena']>;
  symptomeHemoptysie: FormControl<IFicheVonWillebrand['symptomeHemoptysie']>;
  symptomeHemorragiePostAmygdalectomie: FormControl<IFicheVonWillebrand['symptomeHemorragiePostAmygdalectomie']>;
  symptomeHemorragiePostAvulsionDentaire: FormControl<IFicheVonWillebrand['symptomeHemorragiePostAvulsionDentaire']>;
  symptomeHemorragieChuteCordon: FormControl<IFicheVonWillebrand['symptomeHemorragieChuteCordon']>;
  symptomeAutre: FormControl<IFicheVonWillebrand['symptomeAutre']>;
  symptomeAutreDescription: FormControl<IFicheVonWillebrand['symptomeAutreDescription']>;
  datePremiereConsultation: FormControl<IFicheVonWillebrand['datePremiereConsultation']>;
  agePremiereConsultationJours: FormControl<IFicheVonWillebrand['agePremiereConsultationJours']>;
  agePremiereConsultationMois: FormControl<IFicheVonWillebrand['agePremiereConsultationMois']>;
  agePremiereConsultationAnnees: FormControl<IFicheVonWillebrand['agePremiereConsultationAnnees']>;
  ageDiagnosticJours: FormControl<IFicheVonWillebrand['ageDiagnosticJours']>;
  ageDiagnosticMois: FormControl<IFicheVonWillebrand['ageDiagnosticMois']>;
  ageDiagnosticAnnees: FormControl<IFicheVonWillebrand['ageDiagnosticAnnees']>;
  typeMvW: FormControl<IFicheVonWillebrand['typeMvW']>;
  sousTypeMvW: FormControl<IFicheVonWillebrand['sousTypeMvW']>;
  pathologiesAssociees: FormControl<IFicheVonWillebrand['pathologiesAssociees']>;
  dateTestConfirmation: FormControl<IFicheVonWillebrand['dateTestConfirmation']>;
  gs: FormControl<IFicheVonWillebrand['gs']>;
  hb: FormControl<IFicheVonWillebrand['hb']>;
  hte: FormControl<IFicheVonWillebrand['hte']>;
  plaquettes: FormControl<IFicheVonWillebrand['plaquettes']>;
  tp: FormControl<IFicheVonWillebrand['tp']>;
  tcaMT: FormControl<IFicheVonWillebrand['tcaMT']>;
  tcaMPlusT: FormControl<IFicheVonWillebrand['tcaMPlusT']>;
  tcaMPlusT2H: FormControl<IFicheVonWillebrand['tcaMPlusT2H']>;
  fibrinogene: FormControl<IFicheVonWillebrand['fibrinogene']>;
  tsResultat: FormControl<IFicheVonWillebrand['tsResultat']>;
  tsValeur: FormControl<IFicheVonWillebrand['tsValeur']>;
  occlusionPlaquettaire: FormControl<IFicheVonWillebrand['occlusionPlaquettaire']>;
  collageneEpinephrine: FormControl<IFicheVonWillebrand['collageneEpinephrine']>;
  collageneADP: FormControl<IFicheVonWillebrand['collageneADP']>;
  fvwag: FormControl<IFicheVonWillebrand['fvwag']>;
  fvwact: FormControl<IFicheVonWillebrand['fvwact']>;
  f8c: FormControl<IFicheVonWillebrand['f8c']>;
  ratioFvwactFvwag: FormControl<IFicheVonWillebrand['ratioFvwactFvwag']>;
  ratioF8cFvwag: FormControl<IFicheVonWillebrand['ratioF8cFvwag']>;
  autresTestsSpecifiques: FormControl<IFicheVonWillebrand['autresTestsSpecifiques']>;
  vwfCB: FormControl<IFicheVonWillebrand['vwfCB']>;
  vwfCBResultat: FormControl<IFicheVonWillebrand['vwfCBResultat']>;
  ripa: FormControl<IFicheVonWillebrand['ripa']>;
  ripaResultat: FormControl<IFicheVonWillebrand['ripaResultat']>;
  multimereHPM: FormControl<IFicheVonWillebrand['multimereHPM']>;
  liaisonFvwF8: FormControl<IFicheVonWillebrand['liaisonFvwF8']>;
  liaisonFvwF8Resultat: FormControl<IFicheVonWillebrand['liaisonFvwF8Resultat']>;
  fvwpp: FormControl<IFicheVonWillebrand['fvwpp']>;
  fvwppResultat: FormControl<IFicheVonWillebrand['fvwppResultat']>;
  mutationGenetique: FormControl<IFicheVonWillebrand['mutationGenetique']>;
  mutationPresente: FormControl<IFicheVonWillebrand['mutationPresente']>;
  mutationNom: FormControl<IFicheVonWillebrand['mutationNom']>;
  mutationZygote: FormControl<IFicheVonWillebrand['mutationZygote']>;
  ecchymoses: FormControl<IFicheVonWillebrand['ecchymoses']>;
  ecchymosesSpontanees: FormControl<IFicheVonWillebrand['ecchymosesSpontanees']>;
  ecchymosesProvoquees: FormControl<IFicheVonWillebrand['ecchymosesProvoquees']>;
  ecchymosesSiege: FormControl<IFicheVonWillebrand['ecchymosesSiege']>;
  ecchymosesFrequence: FormControl<IFicheVonWillebrand['ecchymosesFrequence']>;
  hemorragieMuqueuses: FormControl<IFicheVonWillebrand['hemorragieMuqueuses']>;
  epistaxis: FormControl<IFicheVonWillebrand['epistaxis']>;
  epistaxisFrequence: FormControl<IFicheVonWillebrand['epistaxisFrequence']>;
  hemorragiesIntrabuccales: FormControl<IFicheVonWillebrand['hemorragiesIntrabuccales']>;
  hemorragiesIntrabuccalesFrequence: FormControl<IFicheVonWillebrand['hemorragiesIntrabuccalesFrequence']>;
  menoragie: FormControl<IFicheVonWillebrand['menoragie']>;
  menoragieFrequence: FormControl<IFicheVonWillebrand['menoragieFrequence']>;
  scoreHighamFait: FormControl<IFicheVonWillebrand['scoreHighamFait']>;
  scoreHighamValeur: FormControl<IFicheVonWillebrand['scoreHighamValeur']>;
  hematome: FormControl<IFicheVonWillebrand['hematome']>;
  hematomeSpontane: FormControl<IFicheVonWillebrand['hematomeSpontane']>;
  hematomeProvoque: FormControl<IFicheVonWillebrand['hematomeProvoque']>;
  hematomeSousCutane: FormControl<IFicheVonWillebrand['hematomeSousCutane']>;
  hematomeMusculaire: FormControl<IFicheVonWillebrand['hematomeMusculaire']>;
  hematomeTissusMous: FormControl<IFicheVonWillebrand['hematomeTissusMous']>;
  hematomeFrequence: FormControl<IFicheVonWillebrand['hematomeFrequence']>;
  hemarthrose: FormControl<IFicheVonWillebrand['hemarthrose']>;
  hemarthroseSpontane: FormControl<IFicheVonWillebrand['hemarthroseSpontane']>;
  hemarthroseProvoque: FormControl<IFicheVonWillebrand['hemarthroseProvoque']>;
  hemarthroseSiege: FormControl<IFicheVonWillebrand['hemarthroseSiege']>;
  hemarthroseFrequence: FormControl<IFicheVonWillebrand['hemarthroseFrequence']>;
  hemorragieAmygdalienne: FormControl<IFicheVonWillebrand['hemorragieAmygdalienne']>;
  hemorragieAmygdalienneFrequence: FormControl<IFicheVonWillebrand['hemorragieAmygdalienneFrequence']>;
  avulsionDentaire: FormControl<IFicheVonWillebrand['avulsionDentaire']>;
  avulsionPrepare: FormControl<IFicheVonWillebrand['avulsionPrepare']>;
  avulsionBienDeroule: FormControl<IFicheVonWillebrand['avulsionBienDeroule']>;
  traumatisme: FormControl<IFicheVonWillebrand['traumatisme']>;
  traumatismeType: FormControl<IFicheVonWillebrand['traumatismeType']>;
  chirurgie: FormControl<IFicheVonWillebrand['chirurgie']>;
  chirurgieType: FormControl<IFicheVonWillebrand['chirurgieType']>;
  chirurgiePrepare: FormControl<IFicheVonWillebrand['chirurgiePrepare']>;
  chirurgieBienDeroule: FormControl<IFicheVonWillebrand['chirurgieBienDeroule']>;
  accouchement: FormControl<IFicheVonWillebrand['accouchement']>;
  hemorragiePostpartum: FormControl<IFicheVonWillebrand['hemorragiePostpartum']>;
  circoncision: FormControl<IFicheVonWillebrand['circoncision']>;
  ageCirconcision: FormControl<IFicheVonWillebrand['ageCirconcision']>;
  circoncisionPrepare: FormControl<IFicheVonWillebrand['circoncisionPrepare']>;
  circoncisionBienDeroule: FormControl<IFicheVonWillebrand['circoncisionBienDeroule']>;
  hemorragieVisceres: FormControl<IFicheVonWillebrand['hemorragieVisceres']>;
  hemorragieVisceresSpontane: FormControl<IFicheVonWillebrand['hemorragieVisceresSpontane']>;
  hemorragieVisceresProvoque: FormControl<IFicheVonWillebrand['hemorragieVisceresProvoque']>;
  hemorragieVisceresType: FormControl<IFicheVonWillebrand['hemorragieVisceresType']>;
  autreHemorragieVisceres: FormControl<IFicheVonWillebrand['autreHemorragieVisceres']>;
  explorationVisceres: FormControl<IFicheVonWillebrand['explorationVisceres']>;
  lesionSousJacente: FormControl<IFicheVonWillebrand['lesionSousJacente']>;
  hemorragieGrave: FormControl<IFicheVonWillebrand['hemorragieGrave']>;
  graveDeglobulisation: FormControl<IFicheVonWillebrand['graveDeglobulisation']>;
  graveEtatDeChoc: FormControl<IFicheVonWillebrand['graveEtatDeChoc']>;
  graveParLocalisation: FormControl<IFicheVonWillebrand['graveParLocalisation']>;
  siegeGraviteLocalisation: FormControl<IFicheVonWillebrand['siegeGraviteLocalisation']>;
  nombreHospitalisations: FormControl<IFicheVonWillebrand['nombreHospitalisations']>;
  nombreTransfusions: FormControl<IFicheVonWillebrand['nombreTransfusions']>;
  scoreISTHBAT: FormControl<IFicheVonWillebrand['scoreISTHBAT']>;
  desmopressine: FormControl<IFicheVonWillebrand['desmopressine']>;
  traitementSubstitutifPrevention: FormControl<IFicheVonWillebrand['traitementSubstitutifPrevention']>;
  traitementPreparation: FormControl<IFicheVonWillebrand['traitementPreparation']>;
  traitementSubstitutifCuratif: FormControl<IFicheVonWillebrand['traitementSubstitutifCuratif']>;
  cryoprecipite: FormControl<IFicheVonWillebrand['cryoprecipite']>;
  pfc: FormControl<IFicheVonWillebrand['pfc']>;
  concentreFVIIIFVW: FormControl<IFicheVonWillebrand['concentreFVIIIFVW']>;
  concentreFVW: FormControl<IFicheVonWillebrand['concentreFVW']>;
  concentreFVIII: FormControl<IFicheVonWillebrand['concentreFVIII']>;
  agentBypassant: FormControl<IFicheVonWillebrand['agentBypassant']>;
  antifibrinolytiques: FormControl<IFicheVonWillebrand['antifibrinolytiques']>;
  voieOrale: FormControl<IFicheVonWillebrand['voieOrale']>;
  voieIV: FormControl<IFicheVonWillebrand['voieIV']>;
  voieLocale: FormControl<IFicheVonWillebrand['voieLocale']>;
  oestroprogestatifs: FormControl<IFicheVonWillebrand['oestroprogestatifs']>;
  traitementAntalgique: FormControl<IFicheVonWillebrand['traitementAntalgique']>;
  corticotherapie: FormControl<IFicheVonWillebrand['corticotherapie']>;
  kinesitherapie: FormControl<IFicheVonWillebrand['kinesitherapie']>;
  traitementMartial: FormControl<IFicheVonWillebrand['traitementMartial']>;
  culotsGlobulaires: FormControl<IFicheVonWillebrand['culotsGlobulaires']>;
  nombreCulotsGlobulaires: FormControl<IFicheVonWillebrand['nombreCulotsGlobulaires']>;
  immunosuppresseur: FormControl<IFicheVonWillebrand['immunosuppresseur']>;
  nomImmunosuppresseur: FormControl<IFicheVonWillebrand['nomImmunosuppresseur']>;
  anemie: FormControl<IFicheVonWillebrand['anemie']>;
  anemieSevere: FormControl<IFicheVonWillebrand['anemieSevere']>;
  tauxHb: FormControl<IFicheVonWillebrand['tauxHb']>;
  anticorpsAntiFVW: FormControl<IFicheVonWillebrand['anticorpsAntiFVW']>;
  anticorpsAntiFVIII: FormControl<IFicheVonWillebrand['anticorpsAntiFVIII']>;
  hepatiteB: FormControl<IFicheVonWillebrand['hepatiteB']>;
  hepatiteC: FormControl<IFicheVonWillebrand['hepatiteC']>;
  hiv: FormControl<IFicheVonWillebrand['hiv']>;
  thrombose: FormControl<IFicheVonWillebrand['thrombose']>;
  reactionAllergique: FormControl<IFicheVonWillebrand['reactionAllergique']>;
  autreComplication: FormControl<IFicheVonWillebrand['autreComplication']>;
  autreComplicationDescription: FormControl<IFicheVonWillebrand['autreComplicationDescription']>;
  adherenceTherapeutique: FormControl<IFicheVonWillebrand['adherenceTherapeutique']>;
  causeNonAdherence: FormControl<IFicheVonWillebrand['causeNonAdherence']>;
  statutSocial: FormControl<IFicheVonWillebrand['statutSocial']>;
  marie: FormControl<IFicheVonWillebrand['marie']>;
  enfants: FormControl<IFicheVonWillebrand['enfants']>;
  activitePhysique: FormControl<IFicheVonWillebrand['activitePhysique']>;
  typeActivitePhysique: FormControl<IFicheVonWillebrand['typeActivitePhysique']>;
  sequelles: FormControl<IFicheVonWillebrand['sequelles']>;
  typeSequelles: FormControl<IFicheVonWillebrand['typeSequelles']>;
  patientDecede: FormControl<IFicheVonWillebrand['patientDecede']>;
  dateDeces: FormControl<IFicheVonWillebrand['dateDeces']>;
  ageDeces: FormControl<IFicheVonWillebrand['ageDeces']>;
  causeDeces: FormControl<IFicheVonWillebrand['causeDeces']>;
};

export type FicheVonWillebrandFormGroup = FormGroup<FicheVonWillebrandFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheVonWillebrandFormService {
  createFicheVonWillebrandFormGroup(ficheVonWillebrand: FicheVonWillebrandFormGroupInput = { id: null }): FicheVonWillebrandFormGroup {
    const ficheVonWillebrandRawValue = {
      ...this.getFormDefaults(),
      ...ficheVonWillebrand,
    };
    return new FormGroup<FicheVonWillebrandFormGroupContent>({
      id: new FormControl(
        { value: ficheVonWillebrandRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dossierNumber: new FormControl(ficheVonWillebrandRawValue.dossierNumber),
      ordreNumber: new FormControl(ficheVonWillebrandRawValue.ordreNumber, {
        validators: [Validators.maxLength(25)],
      }),
      indexNumber: new FormControl(ficheVonWillebrandRawValue.indexNumber, {
        validators: [Validators.maxLength(25)],
      }),
      prenom: new FormControl(ficheVonWillebrandRawValue.prenom, {
        validators: [Validators.maxLength(25)],
      }),
      nom: new FormControl(ficheVonWillebrandRawValue.nom, {
        validators: [Validators.maxLength(25)],
      }),
      nomJeuneFille: new FormControl(ficheVonWillebrandRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(25)],
      }),
      profession: new FormControl(ficheVonWillebrandRawValue.profession, {
        validators: [Validators.maxLength(25)],
      }),
      etatCivil: new FormControl(ficheVonWillebrandRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      serviceClinique: new FormControl(ficheVonWillebrandRawValue.serviceClinique, {
        validators: [Validators.required],
      }),
      autreServiceClinique: new FormControl(ficheVonWillebrandRawValue.autreServiceClinique, {
        validators: [Validators.maxLength(255)],
      }),
      couvertureSociale: new FormControl(ficheVonWillebrandRawValue.couvertureSociale, {
        validators: [Validators.required],
      }),
      sexe: new FormControl(ficheVonWillebrandRawValue.sexe, {
        validators: [Validators.required],
      }),
      dateNaissance: new FormControl(ficheVonWillebrandRawValue.dateNaissance),
      ageActuel: new FormControl(ficheVonWillebrandRawValue.ageActuel),
      origine: new FormControl(ficheVonWillebrandRawValue.origine),
      autreOrigine: new FormControl(ficheVonWillebrandRawValue.autreOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      adresse: new FormControl(ficheVonWillebrandRawValue.adresse, {
        validators: [Validators.maxLength(25)],
      }),
      telephone: new FormControl(ficheVonWillebrandRawValue.telephone, {
        validators: [Validators.maxLength(20)],
      }),
      prenomPere: new FormControl(ficheVonWillebrandRawValue.prenomPere, {
        validators: [Validators.maxLength(25)],
      }),
      professionPere: new FormControl(ficheVonWillebrandRawValue.professionPere, {
        validators: [Validators.maxLength(25)],
      }),
      nomPrenomMere: new FormControl(ficheVonWillebrandRawValue.nomPrenomMere, {
        validators: [Validators.maxLength(25)],
      }),
      professionMere: new FormControl(ficheVonWillebrandRawValue.professionMere, {
        validators: [Validators.maxLength(25)],
      }),
      medecinTraitant: new FormControl(ficheVonWillebrandRawValue.medecinTraitant, {
        validators: [Validators.maxLength(25)],
      }),
      serviceOrigine: new FormControl(ficheVonWillebrandRawValue.serviceOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      biologisteResponsableDg: new FormControl(ficheVonWillebrandRawValue.biologisteResponsableDg, {
        validators: [Validators.maxLength(25)],
      }),
      diagnostic: new FormControl(ficheVonWillebrandRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(ficheVonWillebrandRawValue.autreDiagnostic, {
        validators: [Validators.maxLength(25)],
      }),
      anneeDiagnostic: new FormControl(ficheVonWillebrandRawValue.anneeDiagnostic),
      dateEnregistrementRegistre: new FormControl(ficheVonWillebrandRawValue.dateEnregistrementRegistre),
      consentementRegistre: new FormControl(ficheVonWillebrandRawValue.consentementRegistre),
      parentsConsanguins: new FormControl(ficheVonWillebrandRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheVonWillebrandRawValue.degreParenteConsanguins),
      casSimilaires: new FormControl(ficheVonWillebrandRawValue.casSimilaires),
      nombreCas: new FormControl(ficheVonWillebrandRawValue.nombreCas),
      cas1Numero: new FormControl(ficheVonWillebrandRawValue.cas1Numero),
      lienParenteCas1: new FormControl(ficheVonWillebrandRawValue.lienParenteCas1),
      degreParenteCas1: new FormControl(ficheVonWillebrandRawValue.degreParenteCas1, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas1: new FormControl(ficheVonWillebrandRawValue.typeHemorragieCas1, {
        validators: [Validators.maxLength(255)],
      }),
      cas2Numero: new FormControl(ficheVonWillebrandRawValue.cas2Numero),
      lienParenteCas2: new FormControl(ficheVonWillebrandRawValue.lienParenteCas2),
      degreParenteCas2: new FormControl(ficheVonWillebrandRawValue.degreParenteCas2, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas2: new FormControl(ficheVonWillebrandRawValue.typeHemorragieCas2, {
        validators: [Validators.maxLength(255)],
      }),
      cas3Numero: new FormControl(ficheVonWillebrandRawValue.cas3Numero),
      lienParenteCas3: new FormControl(ficheVonWillebrandRawValue.lienParenteCas3),
      degreParenteCas3: new FormControl(ficheVonWillebrandRawValue.degreParenteCas3, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragieCas3: new FormControl(ficheVonWillebrandRawValue.typeHemorragieCas3, {
        validators: [Validators.maxLength(255)],
      }),
      decesSyndromeHemorragique: new FormControl(ficheVonWillebrandRawValue.decesSyndromeHemorragique),
      nombreDeces: new FormControl(ficheVonWillebrandRawValue.nombreDeces),
      deces1Numero: new FormControl(ficheVonWillebrandRawValue.deces1Numero),
      lienParenteDeces1: new FormControl(ficheVonWillebrandRawValue.lienParenteDeces1),
      causeDeces1: new FormControl(ficheVonWillebrandRawValue.causeDeces1, {
        validators: [Validators.maxLength(255)],
      }),
      deces2Numero: new FormControl(ficheVonWillebrandRawValue.deces2Numero),
      lienParenteDeces2: new FormControl(ficheVonWillebrandRawValue.lienParenteDeces2),
      causeDeces2: new FormControl(ficheVonWillebrandRawValue.causeDeces2, {
        validators: [Validators.maxLength(255)],
      }),
      deces3Numero: new FormControl(ficheVonWillebrandRawValue.deces3Numero),
      lienParenteDeces3: new FormControl(ficheVonWillebrandRawValue.lienParenteDeces3),
      causeDeces3: new FormControl(ficheVonWillebrandRawValue.causeDeces3, {
        validators: [Validators.maxLength(255)],
      }),
      enqueteFamiliale: new FormControl(ficheVonWillebrandRawValue.enqueteFamiliale),
      circonstancesDecouverte: new FormControl(ficheVonWillebrandRawValue.circonstancesDecouverte),
      circonstancesDecouvertePrecision: new FormControl(ficheVonWillebrandRawValue.circonstancesDecouvertePrecision, {
        validators: [Validators.maxLength(100)],
      }),
      jamaisSaigne: new FormControl(ficheVonWillebrandRawValue.jamaisSaigne),
      agePremierSigneCliniqueJours: new FormControl(ficheVonWillebrandRawValue.agePremierSigneCliniqueJours),
      agePremierSigneCliniqueMois: new FormControl(ficheVonWillebrandRawValue.agePremierSigneCliniqueMois),
      agePremierSigneCliniqueAnnees: new FormControl(ficheVonWillebrandRawValue.agePremierSigneCliniqueAnnees),
      symptomeEcchymose: new FormControl(ficheVonWillebrandRawValue.symptomeEcchymose),
      symptomeEpistaxis: new FormControl(ficheVonWillebrandRawValue.symptomeEpistaxis),
      symptomeGingivorragies: new FormControl(ficheVonWillebrandRawValue.symptomeGingivorragies),
      symptomeMetrorragies: new FormControl(ficheVonWillebrandRawValue.symptomeMetrorragies),
      symptomeMelena: new FormControl(ficheVonWillebrandRawValue.symptomeMelena),
      symptomeHemoptysie: new FormControl(ficheVonWillebrandRawValue.symptomeHemoptysie),
      symptomeHemorragiePostAmygdalectomie: new FormControl(ficheVonWillebrandRawValue.symptomeHemorragiePostAmygdalectomie),
      symptomeHemorragiePostAvulsionDentaire: new FormControl(ficheVonWillebrandRawValue.symptomeHemorragiePostAvulsionDentaire),
      symptomeHemorragieChuteCordon: new FormControl(ficheVonWillebrandRawValue.symptomeHemorragieChuteCordon),
      symptomeAutre: new FormControl(ficheVonWillebrandRawValue.symptomeAutre),
      symptomeAutreDescription: new FormControl(ficheVonWillebrandRawValue.symptomeAutreDescription, {
        validators: [Validators.maxLength(255)],
      }),
      datePremiereConsultation: new FormControl(ficheVonWillebrandRawValue.datePremiereConsultation),
      agePremiereConsultationJours: new FormControl(ficheVonWillebrandRawValue.agePremiereConsultationJours),
      agePremiereConsultationMois: new FormControl(ficheVonWillebrandRawValue.agePremiereConsultationMois),
      agePremiereConsultationAnnees: new FormControl(ficheVonWillebrandRawValue.agePremiereConsultationAnnees),
      ageDiagnosticJours: new FormControl(ficheVonWillebrandRawValue.ageDiagnosticJours),
      ageDiagnosticMois: new FormControl(ficheVonWillebrandRawValue.ageDiagnosticMois),
      ageDiagnosticAnnees: new FormControl(ficheVonWillebrandRawValue.ageDiagnosticAnnees),
      typeMvW: new FormControl(ficheVonWillebrandRawValue.typeMvW, {
        validators: [Validators.required],
      }),
      sousTypeMvW: new FormControl(ficheVonWillebrandRawValue.sousTypeMvW),
      pathologiesAssociees: new FormControl(ficheVonWillebrandRawValue.pathologiesAssociees),
      dateTestConfirmation: new FormControl(ficheVonWillebrandRawValue.dateTestConfirmation),
      gs: new FormControl(ficheVonWillebrandRawValue.gs),
      hb: new FormControl(ficheVonWillebrandRawValue.hb),
      hte: new FormControl(ficheVonWillebrandRawValue.hte),
      plaquettes: new FormControl(ficheVonWillebrandRawValue.plaquettes),
      tp: new FormControl(ficheVonWillebrandRawValue.tp),
      tcaMT: new FormControl(ficheVonWillebrandRawValue.tcaMT),
      tcaMPlusT: new FormControl(ficheVonWillebrandRawValue.tcaMPlusT),
      tcaMPlusT2H: new FormControl(ficheVonWillebrandRawValue.tcaMPlusT2H),
      fibrinogene: new FormControl(ficheVonWillebrandRawValue.fibrinogene),
      tsResultat: new FormControl(ficheVonWillebrandRawValue.tsResultat),
      tsValeur: new FormControl(ficheVonWillebrandRawValue.tsValeur),
      occlusionPlaquettaire: new FormControl(ficheVonWillebrandRawValue.occlusionPlaquettaire),
      collageneEpinephrine: new FormControl(ficheVonWillebrandRawValue.collageneEpinephrine),
      collageneADP: new FormControl(ficheVonWillebrandRawValue.collageneADP),
      fvwag: new FormControl(ficheVonWillebrandRawValue.fvwag),
      fvwact: new FormControl(ficheVonWillebrandRawValue.fvwact),
      f8c: new FormControl(ficheVonWillebrandRawValue.f8c),
      ratioFvwactFvwag: new FormControl(ficheVonWillebrandRawValue.ratioFvwactFvwag),
      ratioF8cFvwag: new FormControl(ficheVonWillebrandRawValue.ratioF8cFvwag),
      autresTestsSpecifiques: new FormControl(ficheVonWillebrandRawValue.autresTestsSpecifiques),
      vwfCB: new FormControl(ficheVonWillebrandRawValue.vwfCB),
      vwfCBResultat: new FormControl(ficheVonWillebrandRawValue.vwfCBResultat),
      ripa: new FormControl(ficheVonWillebrandRawValue.ripa),
      ripaResultat: new FormControl(ficheVonWillebrandRawValue.ripaResultat),
      multimereHPM: new FormControl(ficheVonWillebrandRawValue.multimereHPM),
      liaisonFvwF8: new FormControl(ficheVonWillebrandRawValue.liaisonFvwF8),
      liaisonFvwF8Resultat: new FormControl(ficheVonWillebrandRawValue.liaisonFvwF8Resultat),
      fvwpp: new FormControl(ficheVonWillebrandRawValue.fvwpp),
      fvwppResultat: new FormControl(ficheVonWillebrandRawValue.fvwppResultat),
      mutationGenetique: new FormControl(ficheVonWillebrandRawValue.mutationGenetique),
      mutationPresente: new FormControl(ficheVonWillebrandRawValue.mutationPresente),
      mutationNom: new FormControl(ficheVonWillebrandRawValue.mutationNom),
      mutationZygote: new FormControl(ficheVonWillebrandRawValue.mutationZygote),
      ecchymoses: new FormControl(ficheVonWillebrandRawValue.ecchymoses),
      ecchymosesSpontanees: new FormControl(ficheVonWillebrandRawValue.ecchymosesSpontanees),
      ecchymosesProvoquees: new FormControl(ficheVonWillebrandRawValue.ecchymosesProvoquees),
      ecchymosesSiege: new FormControl(ficheVonWillebrandRawValue.ecchymosesSiege),
      ecchymosesFrequence: new FormControl(ficheVonWillebrandRawValue.ecchymosesFrequence),
      hemorragieMuqueuses: new FormControl(ficheVonWillebrandRawValue.hemorragieMuqueuses),
      epistaxis: new FormControl(ficheVonWillebrandRawValue.epistaxis),
      epistaxisFrequence: new FormControl(ficheVonWillebrandRawValue.epistaxisFrequence),
      hemorragiesIntrabuccales: new FormControl(ficheVonWillebrandRawValue.hemorragiesIntrabuccales),
      hemorragiesIntrabuccalesFrequence: new FormControl(ficheVonWillebrandRawValue.hemorragiesIntrabuccalesFrequence),
      menoragie: new FormControl(ficheVonWillebrandRawValue.menoragie),
      menoragieFrequence: new FormControl(ficheVonWillebrandRawValue.menoragieFrequence),
      scoreHighamFait: new FormControl(ficheVonWillebrandRawValue.scoreHighamFait),
      scoreHighamValeur: new FormControl(ficheVonWillebrandRawValue.scoreHighamValeur),
      hematome: new FormControl(ficheVonWillebrandRawValue.hematome),
      hematomeSpontane: new FormControl(ficheVonWillebrandRawValue.hematomeSpontane),
      hematomeProvoque: new FormControl(ficheVonWillebrandRawValue.hematomeProvoque),
      hematomeSousCutane: new FormControl(ficheVonWillebrandRawValue.hematomeSousCutane),
      hematomeMusculaire: new FormControl(ficheVonWillebrandRawValue.hematomeMusculaire),
      hematomeTissusMous: new FormControl(ficheVonWillebrandRawValue.hematomeTissusMous),
      hematomeFrequence: new FormControl(ficheVonWillebrandRawValue.hematomeFrequence),
      hemarthrose: new FormControl(ficheVonWillebrandRawValue.hemarthrose),
      hemarthroseSpontane: new FormControl(ficheVonWillebrandRawValue.hemarthroseSpontane),
      hemarthroseProvoque: new FormControl(ficheVonWillebrandRawValue.hemarthroseProvoque),
      hemarthroseSiege: new FormControl(ficheVonWillebrandRawValue.hemarthroseSiege),
      hemarthroseFrequence: new FormControl(ficheVonWillebrandRawValue.hemarthroseFrequence),
      hemorragieAmygdalienne: new FormControl(ficheVonWillebrandRawValue.hemorragieAmygdalienne),
      hemorragieAmygdalienneFrequence: new FormControl(ficheVonWillebrandRawValue.hemorragieAmygdalienneFrequence),
      avulsionDentaire: new FormControl(ficheVonWillebrandRawValue.avulsionDentaire),
      avulsionPrepare: new FormControl(ficheVonWillebrandRawValue.avulsionPrepare),
      avulsionBienDeroule: new FormControl(ficheVonWillebrandRawValue.avulsionBienDeroule),
      traumatisme: new FormControl(ficheVonWillebrandRawValue.traumatisme),
      traumatismeType: new FormControl(ficheVonWillebrandRawValue.traumatismeType),
      chirurgie: new FormControl(ficheVonWillebrandRawValue.chirurgie),
      chirurgieType: new FormControl(ficheVonWillebrandRawValue.chirurgieType),
      chirurgiePrepare: new FormControl(ficheVonWillebrandRawValue.chirurgiePrepare),
      chirurgieBienDeroule: new FormControl(ficheVonWillebrandRawValue.chirurgieBienDeroule),
      accouchement: new FormControl(ficheVonWillebrandRawValue.accouchement),
      hemorragiePostpartum: new FormControl(ficheVonWillebrandRawValue.hemorragiePostpartum),
      circoncision: new FormControl(ficheVonWillebrandRawValue.circoncision),
      ageCirconcision: new FormControl(ficheVonWillebrandRawValue.ageCirconcision),
      circoncisionPrepare: new FormControl(ficheVonWillebrandRawValue.circoncisionPrepare),
      circoncisionBienDeroule: new FormControl(ficheVonWillebrandRawValue.circoncisionBienDeroule),
      hemorragieVisceres: new FormControl(ficheVonWillebrandRawValue.hemorragieVisceres),
      hemorragieVisceresSpontane: new FormControl(ficheVonWillebrandRawValue.hemorragieVisceresSpontane),
      hemorragieVisceresProvoque: new FormControl(ficheVonWillebrandRawValue.hemorragieVisceresProvoque),
      hemorragieVisceresType: new FormControl(ficheVonWillebrandRawValue.hemorragieVisceresType),
      autreHemorragieVisceres: new FormControl(ficheVonWillebrandRawValue.autreHemorragieVisceres),
      explorationVisceres: new FormControl(ficheVonWillebrandRawValue.explorationVisceres),
      lesionSousJacente: new FormControl(ficheVonWillebrandRawValue.lesionSousJacente),
      hemorragieGrave: new FormControl(ficheVonWillebrandRawValue.hemorragieGrave),
      graveDeglobulisation: new FormControl(ficheVonWillebrandRawValue.graveDeglobulisation),
      graveEtatDeChoc: new FormControl(ficheVonWillebrandRawValue.graveEtatDeChoc),
      graveParLocalisation: new FormControl(ficheVonWillebrandRawValue.graveParLocalisation),
      siegeGraviteLocalisation: new FormControl(ficheVonWillebrandRawValue.siegeGraviteLocalisation),
      nombreHospitalisations: new FormControl(ficheVonWillebrandRawValue.nombreHospitalisations),
      nombreTransfusions: new FormControl(ficheVonWillebrandRawValue.nombreTransfusions),
      scoreISTHBAT: new FormControl(ficheVonWillebrandRawValue.scoreISTHBAT),
      desmopressine: new FormControl(ficheVonWillebrandRawValue.desmopressine),
      traitementSubstitutifPrevention: new FormControl(ficheVonWillebrandRawValue.traitementSubstitutifPrevention),
      traitementPreparation: new FormControl(ficheVonWillebrandRawValue.traitementPreparation),
      traitementSubstitutifCuratif: new FormControl(ficheVonWillebrandRawValue.traitementSubstitutifCuratif),
      cryoprecipite: new FormControl(ficheVonWillebrandRawValue.cryoprecipite),
      pfc: new FormControl(ficheVonWillebrandRawValue.pfc),
      concentreFVIIIFVW: new FormControl(ficheVonWillebrandRawValue.concentreFVIIIFVW),
      concentreFVW: new FormControl(ficheVonWillebrandRawValue.concentreFVW),
      concentreFVIII: new FormControl(ficheVonWillebrandRawValue.concentreFVIII),
      agentBypassant: new FormControl(ficheVonWillebrandRawValue.agentBypassant),
      antifibrinolytiques: new FormControl(ficheVonWillebrandRawValue.antifibrinolytiques),
      voieOrale: new FormControl(ficheVonWillebrandRawValue.voieOrale),
      voieIV: new FormControl(ficheVonWillebrandRawValue.voieIV),
      voieLocale: new FormControl(ficheVonWillebrandRawValue.voieLocale),
      oestroprogestatifs: new FormControl(ficheVonWillebrandRawValue.oestroprogestatifs),
      traitementAntalgique: new FormControl(ficheVonWillebrandRawValue.traitementAntalgique),
      corticotherapie: new FormControl(ficheVonWillebrandRawValue.corticotherapie),
      kinesitherapie: new FormControl(ficheVonWillebrandRawValue.kinesitherapie),
      traitementMartial: new FormControl(ficheVonWillebrandRawValue.traitementMartial),
      culotsGlobulaires: new FormControl(ficheVonWillebrandRawValue.culotsGlobulaires),
      nombreCulotsGlobulaires: new FormControl(ficheVonWillebrandRawValue.nombreCulotsGlobulaires),
      immunosuppresseur: new FormControl(ficheVonWillebrandRawValue.immunosuppresseur),
      nomImmunosuppresseur: new FormControl(ficheVonWillebrandRawValue.nomImmunosuppresseur),
      anemie: new FormControl(ficheVonWillebrandRawValue.anemie),
      anemieSevere: new FormControl(ficheVonWillebrandRawValue.anemieSevere),
      tauxHb: new FormControl(ficheVonWillebrandRawValue.tauxHb),
      anticorpsAntiFVW: new FormControl(ficheVonWillebrandRawValue.anticorpsAntiFVW),
      anticorpsAntiFVIII: new FormControl(ficheVonWillebrandRawValue.anticorpsAntiFVIII),
      hepatiteB: new FormControl(ficheVonWillebrandRawValue.hepatiteB),
      hepatiteC: new FormControl(ficheVonWillebrandRawValue.hepatiteC),
      hiv: new FormControl(ficheVonWillebrandRawValue.hiv),
      thrombose: new FormControl(ficheVonWillebrandRawValue.thrombose),
      reactionAllergique: new FormControl(ficheVonWillebrandRawValue.reactionAllergique),
      autreComplication: new FormControl(ficheVonWillebrandRawValue.autreComplication),
      autreComplicationDescription: new FormControl(ficheVonWillebrandRawValue.autreComplicationDescription),
      adherenceTherapeutique: new FormControl(ficheVonWillebrandRawValue.adherenceTherapeutique),
      causeNonAdherence: new FormControl(ficheVonWillebrandRawValue.causeNonAdherence),
      statutSocial: new FormControl(ficheVonWillebrandRawValue.statutSocial),
      marie: new FormControl(ficheVonWillebrandRawValue.marie),
      enfants: new FormControl(ficheVonWillebrandRawValue.enfants),
      activitePhysique: new FormControl(ficheVonWillebrandRawValue.activitePhysique),
      typeActivitePhysique: new FormControl(ficheVonWillebrandRawValue.typeActivitePhysique),
      sequelles: new FormControl(ficheVonWillebrandRawValue.sequelles),
      typeSequelles: new FormControl(ficheVonWillebrandRawValue.typeSequelles),
      patientDecede: new FormControl(ficheVonWillebrandRawValue.patientDecede),
      dateDeces: new FormControl(ficheVonWillebrandRawValue.dateDeces),
      ageDeces: new FormControl(ficheVonWillebrandRawValue.ageDeces),
      causeDeces: new FormControl(ficheVonWillebrandRawValue.causeDeces),
    });
  }

  getFicheVonWillebrand(form: FicheVonWillebrandFormGroup): IFicheVonWillebrand | NewFicheVonWillebrand {
    return form.getRawValue() as IFicheVonWillebrand | NewFicheVonWillebrand;
  }

  resetForm(form: FicheVonWillebrandFormGroup, ficheVonWillebrand: FicheVonWillebrandFormGroupInput): void {
    const ficheVonWillebrandRawValue = { ...this.getFormDefaults(), ...ficheVonWillebrand };
    form.reset(
      {
        ...ficheVonWillebrandRawValue,
        id: { value: ficheVonWillebrandRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheVonWillebrandFormDefaults {
    return {
      id: null,
      mutationPresente: false,
      ecchymosesSpontanees: false,
      ecchymosesProvoquees: false,
      scoreHighamFait: false,
      hematomeSpontane: false,
      hematomeProvoque: false,
      hematomeSousCutane: false,
      hematomeMusculaire: false,
      hematomeTissusMous: false,
      hemarthroseSpontane: false,
      hemarthroseProvoque: false,
      avulsionPrepare: false,
      hemorragieVisceresSpontane: false,
      hemorragieVisceresProvoque: false,
      explorationVisceres: false,
      graveDeglobulisation: false,
      graveEtatDeChoc: false,
      graveParLocalisation: false,
      voieOrale: false,
      voieIV: false,
      voieLocale: false,
      anemieSevere: false,
      thrombose: false,
      reactionAllergique: false,
      autreComplication: false,
    };
  }
}
