import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IFicheThrombopathie, NewFicheThrombopathie } from '../fiche-thrombopathie.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IFicheThrombopathie for edit and NewFicheThrombopathieFormGroupInput for create.
 */
type FicheThrombopathieFormGroupInput = IFicheThrombopathie | PartialWithRequiredKeyOf<NewFicheThrombopathie>;

type FicheThrombopathieFormDefaults = Pick<
  NewFicheThrombopathie,
  | 'id'
  | 'decouverteFortuite'
  | 'enqueteFamilialeBool'
  | 'explorationSyndromeHemorragique'
  | 'thrombasthenieGlanzmann'
  | 'maladieBernardSoulier'
  | 'autreThrombopathie'
  | 'hemorragieDigestiveHaute'
  | 'hemorragieDigestiveBasse'
  | 'hematurie'
  | 'hematomesCerebrales'
  | 'edcHemorragique'
>;

type FicheThrombopathieFormGroupContent = {
  id: FormControl<IFicheThrombopathie['id'] | NewFicheThrombopathie['id']>;
  dossierNumber: FormControl<IFicheThrombopathie['dossierNumber']>;
  ordreNumber: FormControl<IFicheThrombopathie['ordreNumber']>;
  indexNumber: FormControl<IFicheThrombopathie['indexNumber']>;
  prenom: FormControl<IFicheThrombopathie['prenom']>;
  nom: FormControl<IFicheThrombopathie['nom']>;
  nomJeuneFille: FormControl<IFicheThrombopathie['nomJeuneFille']>;
  profession: FormControl<IFicheThrombopathie['profession']>;
  etatCivil: FormControl<IFicheThrombopathie['etatCivil']>;
  serviceClinique: FormControl<IFicheThrombopathie['serviceClinique']>;
  autreServiceClinique: FormControl<IFicheThrombopathie['autreServiceClinique']>;
  couvertureSociale: FormControl<IFicheThrombopathie['couvertureSociale']>;
  sexe: FormControl<IFicheThrombopathie['sexe']>;
  dateNaissance: FormControl<IFicheThrombopathie['dateNaissance']>;
  ageActuel: FormControl<IFicheThrombopathie['ageActuel']>;
  origine: FormControl<IFicheThrombopathie['origine']>;
  autreOrigine: FormControl<IFicheThrombopathie['autreOrigine']>;
  adresse: FormControl<IFicheThrombopathie['adresse']>;
  telephone: FormControl<IFicheThrombopathie['telephone']>;
  prenomPere: FormControl<IFicheThrombopathie['prenomPere']>;
  professionPere: FormControl<IFicheThrombopathie['professionPere']>;
  nomPrenomMere: FormControl<IFicheThrombopathie['nomPrenomMere']>;
  professionMere: FormControl<IFicheThrombopathie['professionMere']>;
  medecinTraitant: FormControl<IFicheThrombopathie['medecinTraitant']>;
  serviceOrigine: FormControl<IFicheThrombopathie['serviceOrigine']>;
  biologisteResponsableDg: FormControl<IFicheThrombopathie['biologisteResponsableDg']>;
  diagnostic: FormControl<IFicheThrombopathie['diagnostic']>;
  autreDiagnostic: FormControl<IFicheThrombopathie['autreDiagnostic']>;
  anneeDiagnostic: FormControl<IFicheThrombopathie['anneeDiagnostic']>;
  consentementRegistre: FormControl<IFicheThrombopathie['consentementRegistre']>;
  dateEnregistrementRegistre: FormControl<IFicheThrombopathie['dateEnregistrementRegistre']>;
  parentsConsanguins: FormControl<IFicheThrombopathie['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFicheThrombopathie['degreParenteConsanguins']>;
  casSimilaires: FormControl<IFicheThrombopathie['casSimilaires']>;
  nombreCas: FormControl<IFicheThrombopathie['nombreCas']>;
  degreParenteCasSimilaires: FormControl<IFicheThrombopathie['degreParenteCasSimilaires']>;
  decesSyndromeHemorragique: FormControl<IFicheThrombopathie['decesSyndromeHemorragique']>;
  nombreDeces: FormControl<IFicheThrombopathie['nombreDeces']>;
  typeHemorragie: FormControl<IFicheThrombopathie['typeHemorragie']>;
  lienParenteDeces: FormControl<IFicheThrombopathie['lienParenteDeces']>;
  enqueteFamiliale: FormControl<IFicheThrombopathie['enqueteFamiliale']>;
  antecedentsMedicaux: FormControl<IFicheThrombopathie['antecedentsMedicaux']>;
  antecedentsChirurgicaux: FormControl<IFicheThrombopathie['antecedentsChirurgicaux']>;
  typeChirurgie: FormControl<IFicheThrombopathie['typeChirurgie']>;
  complicationHemorragique: FormControl<IFicheThrombopathie['complicationHemorragique']>;
  agePremierSigneHemorragique: FormControl<IFicheThrombopathie['agePremierSigneHemorragique']>;
  typeSyndromeHemorragique: FormControl<IFicheThrombopathie['typeSyndromeHemorragique']>;
  ageDiagnostic: FormControl<IFicheThrombopathie['ageDiagnostic']>;
  decouverteFortuite: FormControl<IFicheThrombopathie['decouverteFortuite']>;
  enqueteFamilialeBool: FormControl<IFicheThrombopathie['enqueteFamilialeBool']>;
  explorationSyndromeHemorragique: FormControl<IFicheThrombopathie['explorationSyndromeHemorragique']>;
  typeCirconstanceDiagnostic: FormControl<IFicheThrombopathie['typeCirconstanceDiagnostic']>;
  eczema: FormControl<IFicheThrombopathie['eczema']>;
  syndromeDysmorphique: FormControl<IFicheThrombopathie['syndromeDysmorphique']>;
  albinisme: FormControl<IFicheThrombopathie['albinisme']>;
  arthrogrypose: FormControl<IFicheThrombopathie['arthrogrypose']>;
  gb: FormControl<IFicheThrombopathie['gb']>;
  hb: FormControl<IFicheThrombopathie['hb']>;
  vgm: FormControl<IFicheThrombopathie['vgm']>;
  tcmh: FormControl<IFicheThrombopathie['tcmh']>;
  plaquettes: FormControl<IFicheThrombopathie['plaquettes']>;
  frottisSanguin: FormControl<IFicheThrombopathie['frottisSanguin']>;
  ts: FormControl<IFicheThrombopathie['ts']>;
  toPfa: FormControl<IFicheThrombopathie['toPfa']>;
  toPfa100ColEpi: FormControl<IFicheThrombopathie['toPfa100ColEpi']>;
  toPfa100ColAdp: FormControl<IFicheThrombopathie['toPfa100ColAdp']>;
  tp: FormControl<IFicheThrombopathie['tp']>;
  tca: FormControl<IFicheThrombopathie['tca']>;
  aggregometriePlaquettaire: FormControl<IFicheThrombopathie['aggregometriePlaquettaire']>;
  resultatAgregometrie: FormControl<IFicheThrombopathie['resultatAgregometrie']>;
  cmf: FormControl<IFicheThrombopathie['cmf']>;
  resultatCmf: FormControl<IFicheThrombopathie['resultatCmf']>;
  dosageFvwAg: FormControl<IFicheThrombopathie['dosageFvwAg']>;
  dosageFvwRco: FormControl<IFicheThrombopathie['dosageFvwRco']>;
  etudeGenetique: FormControl<IFicheThrombopathie['etudeGenetique']>;
  resultatEtudeGenetique: FormControl<IFicheThrombopathie['resultatEtudeGenetique']>;
  thrombasthenieGlanzmann: FormControl<IFicheThrombopathie['thrombasthenieGlanzmann']>;
  maladieBernardSoulier: FormControl<IFicheThrombopathie['maladieBernardSoulier']>;
  autreThrombopathie: FormControl<IFicheThrombopathie['autreThrombopathie']>;
  autreThrombopathieDetails: FormControl<IFicheThrombopathie['autreThrombopathieDetails']>;
  ecchymose: FormControl<IFicheThrombopathie['ecchymose']>;
  typeEcchymose: FormControl<IFicheThrombopathie['typeEcchymose']>;
  gingivorragies: FormControl<IFicheThrombopathie['gingivorragies']>;
  typeGingivorragies: FormControl<IFicheThrombopathie['typeGingivorragies']>;
  epistaxis: FormControl<IFicheThrombopathie['epistaxis']>;
  typeEpistaxis: FormControl<IFicheThrombopathie['typeEpistaxis']>;
  hematome: FormControl<IFicheThrombopathie['hematome']>;
  typeHematome: FormControl<IFicheThrombopathie['typeHematome']>;
  siegelHematome: FormControl<IFicheThrombopathie['siegelHematome']>;
  menoMetrorragies: FormControl<IFicheThrombopathie['menoMetrorragies']>;
  frequenceMenoMetrorragies: FormControl<IFicheThrombopathie['frequenceMenoMetrorragies']>;
  severiteMenoMetrorragies: FormControl<IFicheThrombopathie['severiteMenoMetrorragies']>;
  explorationMenoMetrorragies: FormControl<IFicheThrombopathie['explorationMenoMetrorragies']>;
  typeLesionSousJacente: FormControl<IFicheThrombopathie['typeLesionSousJacente']>;
  hemorragieVisceres: FormControl<IFicheThrombopathie['hemorragieVisceres']>;
  hemorragieDigestiveHaute: FormControl<IFicheThrombopathie['hemorragieDigestiveHaute']>;
  hemorragieDigestiveBasse: FormControl<IFicheThrombopathie['hemorragieDigestiveBasse']>;
  hematurie: FormControl<IFicheThrombopathie['hematurie']>;
  explorationHemorragieVisceres: FormControl<IFicheThrombopathie['explorationHemorragieVisceres']>;
  lesionSousJacenteVisceres: FormControl<IFicheThrombopathie['lesionSousJacenteVisceres']>;
  interventionChirurgicale: FormControl<IFicheThrombopathie['interventionChirurgicale']>;
  typeInterventionChirurgicale: FormControl<IFicheThrombopathie['typeInterventionChirurgicale']>;
  hematomesCerebrales: FormControl<IFicheThrombopathie['hematomesCerebrales']>;
  edcHemorragique: FormControl<IFicheThrombopathie['edcHemorragique']>;
  transfusionCps: FormControl<IFicheThrombopathie['transfusionCps']>;
  facteurVIIRecombinant: FormControl<IFicheThrombopathie['facteurVIIRecombinant']>;
  dicynone: FormControl<IFicheThrombopathie['dicynone']>;
  exacyl: FormControl<IFicheThrombopathie['exacyl']>;
  traitementOestroprogestatif: FormControl<IFicheThrombopathie['traitementOestroprogestatif']>;
  mechage: FormControl<IFicheThrombopathie['mechage']>;
  transfusionCgr: FormControl<IFicheThrombopathie['transfusionCgr']>;
  traitementMartial: FormControl<IFicheThrombopathie['traitementMartial']>;
  thrombose: FormControl<IFicheThrombopathie['thrombose']>;
  alloImmunisationHLA: FormControl<IFicheThrombopathie['alloImmunisationHLA']>;
};

export type FicheThrombopathieFormGroup = FormGroup<FicheThrombopathieFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class FicheThrombopathieFormService {
  createFicheThrombopathieFormGroup(ficheThrombopathie: FicheThrombopathieFormGroupInput = { id: null }): FicheThrombopathieFormGroup {
    const ficheThrombopathieRawValue = {
      ...this.getFormDefaults(),
      ...ficheThrombopathie,
    };
    return new FormGroup<FicheThrombopathieFormGroupContent>({
      id: new FormControl(
        { value: ficheThrombopathieRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      dossierNumber: new FormControl(ficheThrombopathieRawValue.dossierNumber),
      ordreNumber: new FormControl(ficheThrombopathieRawValue.ordreNumber, {
        validators: [Validators.maxLength(25)],
      }),
      indexNumber: new FormControl(ficheThrombopathieRawValue.indexNumber, {
        validators: [Validators.maxLength(25)],
      }),
      prenom: new FormControl(ficheThrombopathieRawValue.prenom, {
        validators: [Validators.maxLength(25)],
      }),
      nom: new FormControl(ficheThrombopathieRawValue.nom, {
        validators: [Validators.maxLength(25)],
      }),
      nomJeuneFille: new FormControl(ficheThrombopathieRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(25)],
      }),
      profession: new FormControl(ficheThrombopathieRawValue.profession, {
        validators: [Validators.maxLength(25)],
      }),
      etatCivil: new FormControl(ficheThrombopathieRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      serviceClinique: new FormControl(ficheThrombopathieRawValue.serviceClinique, {
        validators: [Validators.required],
      }),
      autreServiceClinique: new FormControl(ficheThrombopathieRawValue.autreServiceClinique, {
        validators: [Validators.maxLength(255)],
      }),
      couvertureSociale: new FormControl(ficheThrombopathieRawValue.couvertureSociale, {
        validators: [Validators.required],
      }),
      sexe: new FormControl(ficheThrombopathieRawValue.sexe, {
        validators: [Validators.required],
      }),
      dateNaissance: new FormControl(ficheThrombopathieRawValue.dateNaissance),
      ageActuel: new FormControl(ficheThrombopathieRawValue.ageActuel),
      origine: new FormControl(ficheThrombopathieRawValue.origine),
      autreOrigine: new FormControl(ficheThrombopathieRawValue.autreOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      adresse: new FormControl(ficheThrombopathieRawValue.adresse, {
        validators: [Validators.maxLength(25)],
      }),
      telephone: new FormControl(ficheThrombopathieRawValue.telephone, {
        validators: [Validators.maxLength(20)],
      }),
      prenomPere: new FormControl(ficheThrombopathieRawValue.prenomPere, {
        validators: [Validators.maxLength(25)],
      }),
      professionPere: new FormControl(ficheThrombopathieRawValue.professionPere, {
        validators: [Validators.maxLength(25)],
      }),
      nomPrenomMere: new FormControl(ficheThrombopathieRawValue.nomPrenomMere, {
        validators: [Validators.maxLength(25)],
      }),
      professionMere: new FormControl(ficheThrombopathieRawValue.professionMere, {
        validators: [Validators.maxLength(25)],
      }),
      medecinTraitant: new FormControl(ficheThrombopathieRawValue.medecinTraitant, {
        validators: [Validators.maxLength(25)],
      }),
      serviceOrigine: new FormControl(ficheThrombopathieRawValue.serviceOrigine, {
        validators: [Validators.maxLength(25)],
      }),
      biologisteResponsableDg: new FormControl(ficheThrombopathieRawValue.biologisteResponsableDg, {
        validators: [Validators.maxLength(25)],
      }),
      diagnostic: new FormControl(ficheThrombopathieRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(ficheThrombopathieRawValue.autreDiagnostic, {
        validators: [Validators.maxLength(25)],
      }),
      anneeDiagnostic: new FormControl(ficheThrombopathieRawValue.anneeDiagnostic),
      consentementRegistre: new FormControl(ficheThrombopathieRawValue.consentementRegistre),
      dateEnregistrementRegistre: new FormControl(ficheThrombopathieRawValue.dateEnregistrementRegistre),
      parentsConsanguins: new FormControl(ficheThrombopathieRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheThrombopathieRawValue.degreParenteConsanguins, {
        validators: [Validators.maxLength(25)],
      }),
      casSimilaires: new FormControl(ficheThrombopathieRawValue.casSimilaires),
      nombreCas: new FormControl(ficheThrombopathieRawValue.nombreCas),
      degreParenteCasSimilaires: new FormControl(ficheThrombopathieRawValue.degreParenteCasSimilaires, {
        validators: [Validators.maxLength(25)],
      }),
      decesSyndromeHemorragique: new FormControl(ficheThrombopathieRawValue.decesSyndromeHemorragique),
      nombreDeces: new FormControl(ficheThrombopathieRawValue.nombreDeces),
      typeHemorragie: new FormControl(ficheThrombopathieRawValue.typeHemorragie, {
        validators: [Validators.maxLength(255)],
      }),
      lienParenteDeces: new FormControl(ficheThrombopathieRawValue.lienParenteDeces, {
        validators: [Validators.maxLength(255)],
      }),
      enqueteFamiliale: new FormControl(ficheThrombopathieRawValue.enqueteFamiliale),
      antecedentsMedicaux: new FormControl(ficheThrombopathieRawValue.antecedentsMedicaux, {
        validators: [Validators.maxLength(255)],
      }),
      antecedentsChirurgicaux: new FormControl(ficheThrombopathieRawValue.antecedentsChirurgicaux),
      typeChirurgie: new FormControl(ficheThrombopathieRawValue.typeChirurgie, {
        validators: [Validators.maxLength(255)],
      }),
      complicationHemorragique: new FormControl(ficheThrombopathieRawValue.complicationHemorragique),
      agePremierSigneHemorragique: new FormControl(ficheThrombopathieRawValue.agePremierSigneHemorragique),
      typeSyndromeHemorragique: new FormControl(ficheThrombopathieRawValue.typeSyndromeHemorragique, {
        validators: [Validators.maxLength(255)],
      }),
      ageDiagnostic: new FormControl(ficheThrombopathieRawValue.ageDiagnostic),
      decouverteFortuite: new FormControl(ficheThrombopathieRawValue.decouverteFortuite),
      enqueteFamilialeBool: new FormControl(ficheThrombopathieRawValue.enqueteFamilialeBool),
      explorationSyndromeHemorragique: new FormControl(ficheThrombopathieRawValue.explorationSyndromeHemorragique),
      typeCirconstanceDiagnostic: new FormControl(ficheThrombopathieRawValue.typeCirconstanceDiagnostic, {
        validators: [Validators.maxLength(255)],
      }),
      eczema: new FormControl(ficheThrombopathieRawValue.eczema),
      syndromeDysmorphique: new FormControl(ficheThrombopathieRawValue.syndromeDysmorphique),
      albinisme: new FormControl(ficheThrombopathieRawValue.albinisme),
      arthrogrypose: new FormControl(ficheThrombopathieRawValue.arthrogrypose),
      gb: new FormControl(ficheThrombopathieRawValue.gb),
      hb: new FormControl(ficheThrombopathieRawValue.hb),
      vgm: new FormControl(ficheThrombopathieRawValue.vgm),
      tcmh: new FormControl(ficheThrombopathieRawValue.tcmh),
      plaquettes: new FormControl(ficheThrombopathieRawValue.plaquettes),
      frottisSanguin: new FormControl(ficheThrombopathieRawValue.frottisSanguin, {
        validators: [Validators.maxLength(255)],
      }),
      ts: new FormControl(ficheThrombopathieRawValue.ts),
      toPfa: new FormControl(ficheThrombopathieRawValue.toPfa),
      toPfa100ColEpi: new FormControl(ficheThrombopathieRawValue.toPfa100ColEpi),
      toPfa100ColAdp: new FormControl(ficheThrombopathieRawValue.toPfa100ColAdp),
      tp: new FormControl(ficheThrombopathieRawValue.tp),
      tca: new FormControl(ficheThrombopathieRawValue.tca),
      aggregometriePlaquettaire: new FormControl(ficheThrombopathieRawValue.aggregometriePlaquettaire),
      resultatAgregometrie: new FormControl(ficheThrombopathieRawValue.resultatAgregometrie, {
        validators: [Validators.maxLength(255)],
      }),
      cmf: new FormControl(ficheThrombopathieRawValue.cmf),
      resultatCmf: new FormControl(ficheThrombopathieRawValue.resultatCmf, {
        validators: [Validators.maxLength(255)],
      }),
      dosageFvwAg: new FormControl(ficheThrombopathieRawValue.dosageFvwAg),
      dosageFvwRco: new FormControl(ficheThrombopathieRawValue.dosageFvwRco),
      etudeGenetique: new FormControl(ficheThrombopathieRawValue.etudeGenetique),
      resultatEtudeGenetique: new FormControl(ficheThrombopathieRawValue.resultatEtudeGenetique, {
        validators: [Validators.maxLength(255)],
      }),
      thrombasthenieGlanzmann: new FormControl(ficheThrombopathieRawValue.thrombasthenieGlanzmann),
      maladieBernardSoulier: new FormControl(ficheThrombopathieRawValue.maladieBernardSoulier),
      autreThrombopathie: new FormControl(ficheThrombopathieRawValue.autreThrombopathie),
      autreThrombopathieDetails: new FormControl(ficheThrombopathieRawValue.autreThrombopathieDetails, {
        validators: [Validators.maxLength(255)],
      }),
      ecchymose: new FormControl(ficheThrombopathieRawValue.ecchymose),
      typeEcchymose: new FormControl(ficheThrombopathieRawValue.typeEcchymose),
      gingivorragies: new FormControl(ficheThrombopathieRawValue.gingivorragies),
      typeGingivorragies: new FormControl(ficheThrombopathieRawValue.typeGingivorragies),
      epistaxis: new FormControl(ficheThrombopathieRawValue.epistaxis),
      typeEpistaxis: new FormControl(ficheThrombopathieRawValue.typeEpistaxis),
      hematome: new FormControl(ficheThrombopathieRawValue.hematome),
      typeHematome: new FormControl(ficheThrombopathieRawValue.typeHematome),
      siegelHematome: new FormControl(ficheThrombopathieRawValue.siegelHematome, {
        validators: [Validators.maxLength(255)],
      }),
      menoMetrorragies: new FormControl(ficheThrombopathieRawValue.menoMetrorragies),
      frequenceMenoMetrorragies: new FormControl(ficheThrombopathieRawValue.frequenceMenoMetrorragies),
      severiteMenoMetrorragies: new FormControl(ficheThrombopathieRawValue.severiteMenoMetrorragies, {
        validators: [Validators.maxLength(255)],
      }),
      explorationMenoMetrorragies: new FormControl(ficheThrombopathieRawValue.explorationMenoMetrorragies),
      typeLesionSousJacente: new FormControl(ficheThrombopathieRawValue.typeLesionSousJacente, {
        validators: [Validators.maxLength(255)],
      }),
      hemorragieVisceres: new FormControl(ficheThrombopathieRawValue.hemorragieVisceres),
      hemorragieDigestiveHaute: new FormControl(ficheThrombopathieRawValue.hemorragieDigestiveHaute),
      hemorragieDigestiveBasse: new FormControl(ficheThrombopathieRawValue.hemorragieDigestiveBasse),
      hematurie: new FormControl(ficheThrombopathieRawValue.hematurie),
      explorationHemorragieVisceres: new FormControl(ficheThrombopathieRawValue.explorationHemorragieVisceres),
      lesionSousJacenteVisceres: new FormControl(ficheThrombopathieRawValue.lesionSousJacenteVisceres, {
        validators: [Validators.maxLength(255)],
      }),
      interventionChirurgicale: new FormControl(ficheThrombopathieRawValue.interventionChirurgicale),
      typeInterventionChirurgicale: new FormControl(ficheThrombopathieRawValue.typeInterventionChirurgicale, {
        validators: [Validators.maxLength(255)],
      }),
      hematomesCerebrales: new FormControl(ficheThrombopathieRawValue.hematomesCerebrales),
      edcHemorragique: new FormControl(ficheThrombopathieRawValue.edcHemorragique),
      transfusionCps: new FormControl(ficheThrombopathieRawValue.transfusionCps),
      facteurVIIRecombinant: new FormControl(ficheThrombopathieRawValue.facteurVIIRecombinant),
      dicynone: new FormControl(ficheThrombopathieRawValue.dicynone),
      exacyl: new FormControl(ficheThrombopathieRawValue.exacyl),
      traitementOestroprogestatif: new FormControl(ficheThrombopathieRawValue.traitementOestroprogestatif),
      mechage: new FormControl(ficheThrombopathieRawValue.mechage),
      transfusionCgr: new FormControl(ficheThrombopathieRawValue.transfusionCgr),
      traitementMartial: new FormControl(ficheThrombopathieRawValue.traitementMartial),
      thrombose: new FormControl(ficheThrombopathieRawValue.thrombose),
      alloImmunisationHLA: new FormControl(ficheThrombopathieRawValue.alloImmunisationHLA),
    });
  }

  getFicheThrombopathie(form: FicheThrombopathieFormGroup): IFicheThrombopathie | NewFicheThrombopathie {
    return form.getRawValue() as IFicheThrombopathie | NewFicheThrombopathie;
  }

  resetForm(form: FicheThrombopathieFormGroup, ficheThrombopathie: FicheThrombopathieFormGroupInput): void {
    const ficheThrombopathieRawValue = { ...this.getFormDefaults(), ...ficheThrombopathie };
    form.reset(
      {
        ...ficheThrombopathieRawValue,
        id: { value: ficheThrombopathieRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): FicheThrombopathieFormDefaults {
    return {
      id: null,
      decouverteFortuite: false,
      enqueteFamilialeBool: false,
      explorationSyndromeHemorragique: false,
      thrombasthenieGlanzmann: false,
      maladieBernardSoulier: false,
      autreThrombopathie: false,
      hemorragieDigestiveHaute: false,
      hemorragieDigestiveBasse: false,
      hematurie: false,
      hematomesCerebrales: false,
      edcHemorragique: false,
    };
  }
}
