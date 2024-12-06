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
  prenom: FormControl<IFicheHemophilie['prenom']>;
  nom: FormControl<IFicheHemophilie['nom']>;
  nomJeuneFille: FormControl<IFicheHemophilie['nomJeuneFille']>;
  profession: FormControl<IFicheHemophilie['profession']>;
  sexe: FormControl<IFicheHemophilie['sexe']>;
  dateNaissance: FormControl<IFicheHemophilie['dateNaissance']>;
  ageActuel: FormControl<IFicheHemophilie['ageActuel']>;
  origine: FormControl<IFicheHemophilie['origine']>;
  autreOrigine: FormControl<IFicheHemophilie['autreOrigine']>;
  adresse: FormControl<IFicheHemophilie['adresse']>;
  telephone: FormControl<IFicheHemophilie['telephone']>;
  etatCivil: FormControl<IFicheHemophilie['etatCivil']>;
  couvertureSociale: FormControl<IFicheHemophilie['couvertureSociale']>;
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
  dossierNumber: FormControl<IFicheHemophilie['dossierNumber']>;
  ordreNumber: FormControl<IFicheHemophilie['ordreNumber']>;
  indexNumber: FormControl<IFicheHemophilie['indexNumber']>;
  degreParenteConsanguins: FormControl<IFicheHemophilie['degreParenteConsanguins']>;
  degreParenteCasSimilaires: FormControl<IFicheHemophilie['degreParenteCasSimilaires']>;
  typeHemorragiesCutaneoMuqueuses: FormControl<IFicheHemophilie['typeHemorragiesCutaneoMuqueuses']>;
  circonstanceHematomeSuperficiel: FormControl<IFicheHemophilie['circonstanceHematomeSuperficiel']>;
  siegeHematomeSuperficiel: FormControl<IFicheHemophilie['siegeHematomeSuperficiel']>;
  circonstanceHematomePsoas: FormControl<IFicheHemophilie['circonstanceHematomePsoas']>;
  circonstanceAccidentsHemorragiquesGraves: FormControl<IFicheHemophilie['circonstanceAccidentsHemorragiquesGraves']>;
  typeInterventionChirurgicale: FormControl<IFicheHemophilie['typeInterventionChirurgicale']>;
  causePriseEnCharge: FormControl<IFicheHemophilie['causePriseEnCharge']>;
  posologieITI: FormControl<IFicheHemophilie['posologieITI']>;
  dureeITI: FormControl<IFicheHemophilie['dureeITI']>;
  causeNonObservanceITI: FormControl<IFicheHemophilie['causeNonObservanceITI']>;
  typeHandicap: FormControl<IFicheHemophilie['typeHandicap']>;
  typeActiviteSportive: FormControl<IFicheHemophilie['typeActiviteSportive']>;
  causeDateDeces: FormControl<IFicheHemophilie['causeDateDeces']>;
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
      prenom: new FormControl(ficheHemophilieRawValue.prenom, {
        validators: [Validators.required, Validators.maxLength(25)],
      }),
      nom: new FormControl(ficheHemophilieRawValue.nom, {
        validators: [Validators.required, Validators.maxLength(25)],
      }),
      nomJeuneFille: new FormControl(ficheHemophilieRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(25)],
      }),
      profession: new FormControl(ficheHemophilieRawValue.profession, {
        validators: [Validators.maxLength(25)],
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
      etatCivil: new FormControl(ficheHemophilieRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      couvertureSociale: new FormControl(ficheHemophilieRawValue.couvertureSociale, {
        validators: [Validators.required],
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
      dossierNumber: new FormControl(ficheHemophilieRawValue.dossierNumber, {
        validators: [Validators.required, Validators.maxLength(25)],
      }),
      ordreNumber: new FormControl(ficheHemophilieRawValue.ordreNumber, {
        validators: [Validators.maxLength(25)],
      }),
      indexNumber: new FormControl(ficheHemophilieRawValue.indexNumber, {
        validators: [Validators.maxLength(25)],
      }),
      degreParenteConsanguins: new FormControl(ficheHemophilieRawValue.degreParenteConsanguins, {
        validators: [Validators.maxLength(25)],
      }),
      degreParenteCasSimilaires: new FormControl(ficheHemophilieRawValue.degreParenteCasSimilaires, {
        validators: [Validators.maxLength(25)],
      }),
      typeHemorragiesCutaneoMuqueuses: new FormControl(ficheHemophilieRawValue.typeHemorragiesCutaneoMuqueuses, {
        validators: [Validators.maxLength(25)],
      }),
      circonstanceHematomeSuperficiel: new FormControl(ficheHemophilieRawValue.circonstanceHematomeSuperficiel, {
        validators: [Validators.maxLength(25)],
      }),
      siegeHematomeSuperficiel: new FormControl(ficheHemophilieRawValue.siegeHematomeSuperficiel, {
        validators: [Validators.maxLength(25)],
      }),
      circonstanceHematomePsoas: new FormControl(ficheHemophilieRawValue.circonstanceHematomePsoas, {
        validators: [Validators.maxLength(25)],
      }),
      circonstanceAccidentsHemorragiquesGraves: new FormControl(ficheHemophilieRawValue.circonstanceAccidentsHemorragiquesGraves, {
        validators: [Validators.maxLength(25)],
      }),
      typeInterventionChirurgicale: new FormControl(ficheHemophilieRawValue.typeInterventionChirurgicale, {
        validators: [Validators.maxLength(25)],
      }),
      causePriseEnCharge: new FormControl(ficheHemophilieRawValue.causePriseEnCharge, {
        validators: [Validators.maxLength(25)],
      }),
      posologieITI: new FormControl(ficheHemophilieRawValue.posologieITI, {
        validators: [Validators.maxLength(25)],
      }),
      dureeITI: new FormControl(ficheHemophilieRawValue.dureeITI, {
        validators: [Validators.maxLength(25)],
      }),
      causeNonObservanceITI: new FormControl(ficheHemophilieRawValue.causeNonObservanceITI, {
        validators: [Validators.maxLength(25)],
      }),
      typeHandicap: new FormControl(ficheHemophilieRawValue.typeHandicap, {
        validators: [Validators.maxLength(25)],
      }),
      typeActiviteSportive: new FormControl(ficheHemophilieRawValue.typeActiviteSportive, {
        validators: [Validators.maxLength(25)],
      }),
      causeDateDeces: new FormControl(ficheHemophilieRawValue.causeDateDeces, {
        validators: [Validators.maxLength(25)],
      }),
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
