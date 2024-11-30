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

type FicheHemophilieFormDefaults = Pick<NewFicheHemophilie, 'id'>;

type FicheHemophilieFormGroupContent = {
  id: FormControl<IFicheHemophilie['id'] | NewFicheHemophilie['id']>;
  dossierNumber: FormControl<IFicheHemophilie['dossierNumber']>;
  ordreNumber: FormControl<IFicheHemophilie['ordreNumber']>;
  indexNumber: FormControl<IFicheHemophilie['indexNumber']>;
  parentsConsanguins: FormControl<IFicheHemophilie['parentsConsanguins']>;
  degreParenteConsanguins: FormControl<IFicheHemophilie['degreParenteConsanguins']>;
  casSimilairesFamille: FormControl<IFicheHemophilie['casSimilairesFamille']>;
  nbreCasSimilaires: FormControl<IFicheHemophilie['nbreCasSimilaires']>;
  degreParenteCasSimilaires: FormControl<IFicheHemophilie['degreParenteCasSimilaires']>;
  casDecesSyndromeHemorragique: FormControl<IFicheHemophilie['casDecesSyndromeHemorragique']>;
  nbreCasDeces: FormControl<IFicheHemophilie['nbreCasDeces']>;
  formeHemophilie: FormControl<IFicheHemophilie['formeHemophilie']>;
  nbreFreres: FormControl<IFicheHemophilie['nbreFreres']>;
  nbreSoeurs: FormControl<IFicheHemophilie['nbreSoeurs']>;
  circonstanceDecouverte: FormControl<IFicheHemophilie['circonstanceDecouverte']>;
  dateTestConfirmation: FormControl<IFicheHemophilie['dateTestConfirmation']>;
  hemogrammeHb: FormControl<IFicheHemophilie['hemogrammeHb']>;
  hemogrammeHt: FormControl<IFicheHemophilie['hemogrammeHt']>;
  plaquettes: FormControl<IFicheHemophilie['plaquettes']>;
  tp: FormControl<IFicheHemophilie['tp']>;
  fibrinogene: FormControl<IFicheHemophilie['fibrinogene']>;
  tcaMT: FormControl<IFicheHemophilie['tcaMT']>;
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
  hemarthrose: FormControl<IFicheHemophilie['hemarthrose']>;
  siegeHemarthrose: FormControl<IFicheHemophilie['siegeHemarthrose']>;
  circonstanceHemarthrose: FormControl<IFicheHemophilie['circonstanceHemarthrose']>;
  frequenceHemarthrose: FormControl<IFicheHemophilie['frequenceHemarthrose']>;
  hematomeSuperficiel: FormControl<IFicheHemophilie['hematomeSuperficiel']>;
  circonstanceHematomeSuperficiel: FormControl<IFicheHemophilie['circonstanceHematomeSuperficiel']>;
  siegeHematomeSuperficiel: FormControl<IFicheHemophilie['siegeHematomeSuperficiel']>;
  hematomePsoas: FormControl<IFicheHemophilie['hematomePsoas']>;
  circonstanceHematomePsoas: FormControl<IFicheHemophilie['circonstanceHematomePsoas']>;
  recidiveHematomePsoas: FormControl<IFicheHemophilie['recidiveHematomePsoas']>;
  hemorragiesCutaneoMuqueuses: FormControl<IFicheHemophilie['hemorragiesCutaneoMuqueuses']>;
  typeHemorragiesCutaneoMuqueuses: FormControl<IFicheHemophilie['typeHemorragiesCutaneoMuqueuses']>;
  frequenceHemorragiesCutaneoMuqueuses: FormControl<IFicheHemophilie['frequenceHemorragiesCutaneoMuqueuses']>;
  hemorragieVisceres: FormControl<IFicheHemophilie['hemorragieVisceres']>;
  typeHemorragieVisceres: FormControl<IFicheHemophilie['typeHemorragieVisceres']>;
  explorationHemorragieVisceres: FormControl<IFicheHemophilie['explorationHemorragieVisceres']>;
  examenLesionHemorragieVisceres: FormControl<IFicheHemophilie['examenLesionHemorragieVisceres']>;
  saignementSNC: FormControl<IFicheHemophilie['saignementSNC']>;
  evolutionSNC: FormControl<IFicheHemophilie['evolutionSNC']>;
  circoncision: FormControl<IFicheHemophilie['circoncision']>;
  typePreparationCirconcision: FormControl<IFicheHemophilie['typePreparationCirconcision']>;
  suiteOperatoireCirconcision: FormControl<IFicheHemophilie['suiteOperatoireCirconcision']>;
  ageCirconcision: FormControl<IFicheHemophilie['ageCirconcision']>;
  avulsionDentaire: FormControl<IFicheHemophilie['avulsionDentaire']>;
  typePreparationAvulsionDentaire: FormControl<IFicheHemophilie['typePreparationAvulsionDentaire']>;
  interventionChirurgicale: FormControl<IFicheHemophilie['interventionChirurgicale']>;
  typeInterventionChirurgicale: FormControl<IFicheHemophilie['typeInterventionChirurgicale']>;
  accidentsHemorragiquesGraves: FormControl<IFicheHemophilie['accidentsHemorragiquesGraves']>;
  circonstanceAccidentsHemorragiquesGraves: FormControl<IFicheHemophilie['circonstanceAccidentsHemorragiquesGraves']>;
  priseEnCharge: FormControl<IFicheHemophilie['priseEnCharge']>;
  causePriseEnCharge: FormControl<IFicheHemophilie['causePriseEnCharge']>;
  doseProphylaxie: FormControl<IFicheHemophilie['doseProphylaxie']>;
  frequenceProphylaxie: FormControl<IFicheHemophilie['frequenceProphylaxie']>;
  debutProphylaxie: FormControl<IFicheHemophilie['debutProphylaxie']>;
  modaliteInjection: FormControl<IFicheHemophilie['modaliteInjection']>;
  typeTraitementSubstitutif: FormControl<IFicheHemophilie['typeTraitementSubstitutif']>;
  age1ereSubstitution: FormControl<IFicheHemophilie['age1ereSubstitution']>;
  antecedentsPSL: FormControl<IFicheHemophilie['antecedentsPSL']>;
  plasmaFraisCongele: FormControl<IFicheHemophilie['plasmaFraisCongele']>;
  cryoprecipite: FormControl<IFicheHemophilie['cryoprecipite']>;
  complicationsOrthopediques: FormControl<IFicheHemophilie['complicationsOrthopediques']>;
  typeComplicationsOrthopediques: FormControl<IFicheHemophilie['typeComplicationsOrthopediques']>;
  articulationCible: FormControl<IFicheHemophilie['articulationCible']>;
  pecTherapeutique: FormControl<IFicheHemophilie['pecTherapeutique']>;
  complicationsInhibiteurs: FormControl<IFicheHemophilie['complicationsInhibiteurs']>;
  tauxInhibiteur: FormControl<IFicheHemophilie['tauxInhibiteur']>;
  testRecuperationFAH: FormControl<IFicheHemophilie['testRecuperationFAH']>;
  dateRecuperationFAH: FormControl<IFicheHemophilie['dateRecuperationFAH']>;
  tauxRecuperation: FormControl<IFicheHemophilie['tauxRecuperation']>;
  antiFAHNonNeutralisant: FormControl<IFicheHemophilie['antiFAHNonNeutralisant']>;
  rechercheACC: FormControl<IFicheHemophilie['rechercheACC']>;
  catTherapeutique: FormControl<IFicheHemophilie['catTherapeutique']>;
  iti: FormControl<IFicheHemophilie['iti']>;
  posologieITI: FormControl<IFicheHemophilie['posologieITI']>;
  dureeITI: FormControl<IFicheHemophilie['dureeITI']>;
  observanceITI: FormControl<IFicheHemophilie['observanceITI']>;
  causeNonObservanceITI: FormControl<IFicheHemophilie['causeNonObservanceITI']>;
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
  causeDateDeces: FormControl<IFicheHemophilie['causeDateDeces']>;
  patient: FormControl<IFicheHemophilie['patient']>;
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
      dossierNumber: new FormControl(ficheHemophilieRawValue.dossierNumber, {
        validators: [Validators.required, Validators.maxLength(30)],
      }),
      ordreNumber: new FormControl(ficheHemophilieRawValue.ordreNumber, {
        validators: [Validators.maxLength(30)],
      }),
      indexNumber: new FormControl(ficheHemophilieRawValue.indexNumber, {
        validators: [Validators.maxLength(30)],
      }),
      parentsConsanguins: new FormControl(ficheHemophilieRawValue.parentsConsanguins),
      degreParenteConsanguins: new FormControl(ficheHemophilieRawValue.degreParenteConsanguins, {
        validators: [Validators.maxLength(30)],
      }),
      casSimilairesFamille: new FormControl(ficheHemophilieRawValue.casSimilairesFamille),
      nbreCasSimilaires: new FormControl(ficheHemophilieRawValue.nbreCasSimilaires),
      degreParenteCasSimilaires: new FormControl(ficheHemophilieRawValue.degreParenteCasSimilaires, {
        validators: [Validators.maxLength(30)],
      }),
      casDecesSyndromeHemorragique: new FormControl(ficheHemophilieRawValue.casDecesSyndromeHemorragique),
      nbreCasDeces: new FormControl(ficheHemophilieRawValue.nbreCasDeces),
      formeHemophilie: new FormControl(ficheHemophilieRawValue.formeHemophilie),
      nbreFreres: new FormControl(ficheHemophilieRawValue.nbreFreres),
      nbreSoeurs: new FormControl(ficheHemophilieRawValue.nbreSoeurs),
      circonstanceDecouverte: new FormControl(ficheHemophilieRawValue.circonstanceDecouverte),
      dateTestConfirmation: new FormControl(ficheHemophilieRawValue.dateTestConfirmation),
      hemogrammeHb: new FormControl(ficheHemophilieRawValue.hemogrammeHb),
      hemogrammeHt: new FormControl(ficheHemophilieRawValue.hemogrammeHt),
      plaquettes: new FormControl(ficheHemophilieRawValue.plaquettes),
      tp: new FormControl(ficheHemophilieRawValue.tp),
      fibrinogene: new FormControl(ficheHemophilieRawValue.fibrinogene),
      tcaMT: new FormControl(ficheHemophilieRawValue.tcaMT),
      tcaMT2h: new FormControl(ficheHemophilieRawValue.tcaMT2h),
      tcaTemoin2h: new FormControl(ficheHemophilieRawValue.tcaTemoin2h),
      ts: new FormControl(ficheHemophilieRawValue.ts),
      pfa: new FormControl(ficheHemophilieRawValue.pfa),
      dosageFacteurVIII: new FormControl(ficheHemophilieRawValue.dosageFacteurVIII),
      dosageFacteurIX: new FormControl(ficheHemophilieRawValue.dosageFacteurIX),
      dosageFacteurVWActivite: new FormControl(ficheHemophilieRawValue.dosageFacteurVWActivite),
      dosageFacteurVWAntigene: new FormControl(ficheHemophilieRawValue.dosageFacteurVWAntigene),
      formeClinique: new FormControl(ficheHemophilieRawValue.formeClinique),
      severite: new FormControl(ficheHemophilieRawValue.severite),
      explorationMoleculaire: new FormControl(ficheHemophilieRawValue.explorationMoleculaire, {
        validators: [Validators.maxLength(30)],
      }),
      hemarthrose: new FormControl(ficheHemophilieRawValue.hemarthrose),
      siegeHemarthrose: new FormControl(ficheHemophilieRawValue.siegeHemarthrose),
      circonstanceHemarthrose: new FormControl(ficheHemophilieRawValue.circonstanceHemarthrose),
      frequenceHemarthrose: new FormControl(ficheHemophilieRawValue.frequenceHemarthrose),
      hematomeSuperficiel: new FormControl(ficheHemophilieRawValue.hematomeSuperficiel),
      circonstanceHematomeSuperficiel: new FormControl(ficheHemophilieRawValue.circonstanceHematomeSuperficiel, {
        validators: [Validators.maxLength(30)],
      }),
      siegeHematomeSuperficiel: new FormControl(ficheHemophilieRawValue.siegeHematomeSuperficiel, {
        validators: [Validators.maxLength(30)],
      }),
      hematomePsoas: new FormControl(ficheHemophilieRawValue.hematomePsoas),
      circonstanceHematomePsoas: new FormControl(ficheHemophilieRawValue.circonstanceHematomePsoas, {
        validators: [Validators.maxLength(30)],
      }),
      recidiveHematomePsoas: new FormControl(ficheHemophilieRawValue.recidiveHematomePsoas),
      hemorragiesCutaneoMuqueuses: new FormControl(ficheHemophilieRawValue.hemorragiesCutaneoMuqueuses),
      typeHemorragiesCutaneoMuqueuses: new FormControl(ficheHemophilieRawValue.typeHemorragiesCutaneoMuqueuses, {
        validators: [Validators.maxLength(30)],
      }),
      frequenceHemorragiesCutaneoMuqueuses: new FormControl(ficheHemophilieRawValue.frequenceHemorragiesCutaneoMuqueuses),
      hemorragieVisceres: new FormControl(ficheHemophilieRawValue.hemorragieVisceres),
      typeHemorragieVisceres: new FormControl(ficheHemophilieRawValue.typeHemorragieVisceres, {
        validators: [Validators.maxLength(30)],
      }),
      explorationHemorragieVisceres: new FormControl(ficheHemophilieRawValue.explorationHemorragieVisceres),
      examenLesionHemorragieVisceres: new FormControl(ficheHemophilieRawValue.examenLesionHemorragieVisceres, {
        validators: [Validators.maxLength(30)],
      }),
      saignementSNC: new FormControl(ficheHemophilieRawValue.saignementSNC),
      evolutionSNC: new FormControl(ficheHemophilieRawValue.evolutionSNC),
      circoncision: new FormControl(ficheHemophilieRawValue.circoncision),
      typePreparationCirconcision: new FormControl(ficheHemophilieRawValue.typePreparationCirconcision),
      suiteOperatoireCirconcision: new FormControl(ficheHemophilieRawValue.suiteOperatoireCirconcision),
      ageCirconcision: new FormControl(ficheHemophilieRawValue.ageCirconcision),
      avulsionDentaire: new FormControl(ficheHemophilieRawValue.avulsionDentaire),
      typePreparationAvulsionDentaire: new FormControl(ficheHemophilieRawValue.typePreparationAvulsionDentaire),
      interventionChirurgicale: new FormControl(ficheHemophilieRawValue.interventionChirurgicale),
      typeInterventionChirurgicale: new FormControl(ficheHemophilieRawValue.typeInterventionChirurgicale, {
        validators: [Validators.maxLength(30)],
      }),
      accidentsHemorragiquesGraves: new FormControl(ficheHemophilieRawValue.accidentsHemorragiquesGraves),
      circonstanceAccidentsHemorragiquesGraves: new FormControl(ficheHemophilieRawValue.circonstanceAccidentsHemorragiquesGraves, {
        validators: [Validators.maxLength(30)],
      }),
      priseEnCharge: new FormControl(ficheHemophilieRawValue.priseEnCharge, {
        validators: [Validators.required],
      }),
      causePriseEnCharge: new FormControl(ficheHemophilieRawValue.causePriseEnCharge, {
        validators: [Validators.maxLength(30)],
      }),
      doseProphylaxie: new FormControl(ficheHemophilieRawValue.doseProphylaxie, {
        validators: [Validators.maxLength(20)],
      }),
      frequenceProphylaxie: new FormControl(ficheHemophilieRawValue.frequenceProphylaxie),
      debutProphylaxie: new FormControl(ficheHemophilieRawValue.debutProphylaxie),
      modaliteInjection: new FormControl(ficheHemophilieRawValue.modaliteInjection),
      typeTraitementSubstitutif: new FormControl(ficheHemophilieRawValue.typeTraitementSubstitutif),
      age1ereSubstitution: new FormControl(ficheHemophilieRawValue.age1ereSubstitution),
      antecedentsPSL: new FormControl(ficheHemophilieRawValue.antecedentsPSL),
      plasmaFraisCongele: new FormControl(ficheHemophilieRawValue.plasmaFraisCongele),
      cryoprecipite: new FormControl(ficheHemophilieRawValue.cryoprecipite),
      complicationsOrthopediques: new FormControl(ficheHemophilieRawValue.complicationsOrthopediques),
      typeComplicationsOrthopediques: new FormControl(ficheHemophilieRawValue.typeComplicationsOrthopediques),
      articulationCible: new FormControl(ficheHemophilieRawValue.articulationCible),
      pecTherapeutique: new FormControl(ficheHemophilieRawValue.pecTherapeutique),
      complicationsInhibiteurs: new FormControl(ficheHemophilieRawValue.complicationsInhibiteurs),
      tauxInhibiteur: new FormControl(ficheHemophilieRawValue.tauxInhibiteur),
      testRecuperationFAH: new FormControl(ficheHemophilieRawValue.testRecuperationFAH),
      dateRecuperationFAH: new FormControl(ficheHemophilieRawValue.dateRecuperationFAH),
      tauxRecuperation: new FormControl(ficheHemophilieRawValue.tauxRecuperation),
      antiFAHNonNeutralisant: new FormControl(ficheHemophilieRawValue.antiFAHNonNeutralisant),
      rechercheACC: new FormControl(ficheHemophilieRawValue.rechercheACC),
      catTherapeutique: new FormControl(ficheHemophilieRawValue.catTherapeutique),
      iti: new FormControl(ficheHemophilieRawValue.iti),
      posologieITI: new FormControl(ficheHemophilieRawValue.posologieITI, {
        validators: [Validators.maxLength(30)],
      }),
      dureeITI: new FormControl(ficheHemophilieRawValue.dureeITI, {
        validators: [Validators.maxLength(30)],
      }),
      observanceITI: new FormControl(ficheHemophilieRawValue.observanceITI),
      causeNonObservanceITI: new FormControl(ficheHemophilieRawValue.causeNonObservanceITI, {
        validators: [Validators.maxLength(30)],
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
        validators: [Validators.maxLength(30)],
      }),
      traitementVHC: new FormControl(ficheHemophilieRawValue.traitementVHC),
      evolutionVHC: new FormControl(ficheHemophilieRawValue.evolutionVHC),
      serologieHIV: new FormControl(ficheHemophilieRawValue.serologieHIV),
      vieSociale: new FormControl(ficheHemophilieRawValue.vieSociale),
      marie: new FormControl(ficheHemophilieRawValue.marie),
      enfants: new FormControl(ficheHemophilieRawValue.enfants),
      handicap: new FormControl(ficheHemophilieRawValue.handicap),
      typeHandicap: new FormControl(ficheHemophilieRawValue.typeHandicap, {
        validators: [Validators.maxLength(30)],
      }),
      activiteSportive: new FormControl(ficheHemophilieRawValue.activiteSportive),
      typeActiviteSportive: new FormControl(ficheHemophilieRawValue.typeActiviteSportive, {
        validators: [Validators.maxLength(30)],
      }),
      decede: new FormControl(ficheHemophilieRawValue.decede),
      causeDateDeces: new FormControl(ficheHemophilieRawValue.causeDateDeces, {
        validators: [Validators.maxLength(30)],
      }),
      patient: new FormControl(ficheHemophilieRawValue.patient),
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
    };
  }
}
