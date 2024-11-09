import { Injectable } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { IPatient, NewPatient } from '../patient.model';

/**
 * A partial Type with required key is used as form input.
 */
type PartialWithRequiredKeyOf<T extends { id: unknown }> = Partial<Omit<T, 'id'>> & { id: T['id'] };

/**
 * Type for createFormGroup and resetForm argument.
 * It accepts IPatient for edit and NewPatientFormGroupInput for create.
 */
type PatientFormGroupInput = IPatient | PartialWithRequiredKeyOf<NewPatient>;

type PatientFormDefaults = Pick<NewPatient, 'id'>;

type PatientFormGroupContent = {
  id: FormControl<IPatient['id'] | NewPatient['id']>;
  prenom: FormControl<IPatient['prenom']>;
  nom: FormControl<IPatient['nom']>;
  nomJeuneFille: FormControl<IPatient['nomJeuneFille']>;
  profession: FormControl<IPatient['profession']>;
  sexe: FormControl<IPatient['sexe']>;
  dateNaissance: FormControl<IPatient['dateNaissance']>;
  ageActuel: FormControl<IPatient['ageActuel']>;
  origine: FormControl<IPatient['origine']>;
  autreOrigine: FormControl<IPatient['autreOrigine']>;
  adresse: FormControl<IPatient['adresse']>;
  telephone: FormControl<IPatient['telephone']>;
  etatCivil: FormControl<IPatient['etatCivil']>;
  couvertureSociale: FormControl<IPatient['couvertureSociale']>;
  prenomPere: FormControl<IPatient['prenomPere']>;
  professionPere: FormControl<IPatient['professionPere']>;
  nomPrenomMere: FormControl<IPatient['nomPrenomMere']>;
  professionMere: FormControl<IPatient['professionMere']>;
  diagnostic: FormControl<IPatient['diagnostic']>;
  autreDiagnostic: FormControl<IPatient['autreDiagnostic']>;
  anneeDiagnostic: FormControl<IPatient['anneeDiagnostic']>;
  consentementRegistre: FormControl<IPatient['consentementRegistre']>;
  dateEnregistrementRegistre: FormControl<IPatient['dateEnregistrementRegistre']>;
};

export type PatientFormGroup = FormGroup<PatientFormGroupContent>;

@Injectable({ providedIn: 'root' })
export class PatientFormService {
  createPatientFormGroup(patient: PatientFormGroupInput = { id: null }): PatientFormGroup {
    const patientRawValue = {
      ...this.getFormDefaults(),
      ...patient,
    };
    return new FormGroup<PatientFormGroupContent>({
      id: new FormControl(
        { value: patientRawValue.id, disabled: true },
        {
          nonNullable: true,
          validators: [Validators.required],
        },
      ),
      prenom: new FormControl(patientRawValue.prenom, {
        validators: [Validators.required, Validators.maxLength(50)],
      }),
      nom: new FormControl(patientRawValue.nom, {
        validators: [Validators.required, Validators.maxLength(50)],
      }),
      nomJeuneFille: new FormControl(patientRawValue.nomJeuneFille, {
        validators: [Validators.maxLength(50)],
      }),
      profession: new FormControl(patientRawValue.profession, {
        validators: [Validators.maxLength(50)],
      }),
      sexe: new FormControl(patientRawValue.sexe, {
        validators: [Validators.required],
      }),
      dateNaissance: new FormControl(patientRawValue.dateNaissance),
      ageActuel: new FormControl(patientRawValue.ageActuel),
      origine: new FormControl(patientRawValue.origine),
      autreOrigine: new FormControl(patientRawValue.autreOrigine, {
        validators: [Validators.maxLength(50)],
      }),
      adresse: new FormControl(patientRawValue.adresse, {
        validators: [Validators.maxLength(100)],
      }),
      telephone: new FormControl(patientRawValue.telephone, {
        validators: [Validators.maxLength(20)],
      }),
      etatCivil: new FormControl(patientRawValue.etatCivil, {
        validators: [Validators.required],
      }),
      couvertureSociale: new FormControl(patientRawValue.couvertureSociale, {
        validators: [Validators.required],
      }),
      prenomPere: new FormControl(patientRawValue.prenomPere, {
        validators: [Validators.maxLength(50)],
      }),
      professionPere: new FormControl(patientRawValue.professionPere, {
        validators: [Validators.maxLength(50)],
      }),
      nomPrenomMere: new FormControl(patientRawValue.nomPrenomMere, {
        validators: [Validators.maxLength(50)],
      }),
      professionMere: new FormControl(patientRawValue.professionMere, {
        validators: [Validators.maxLength(50)],
      }),
      diagnostic: new FormControl(patientRawValue.diagnostic, {
        validators: [Validators.required],
      }),
      autreDiagnostic: new FormControl(patientRawValue.autreDiagnostic, {
        validators: [Validators.maxLength(50)],
      }),
      anneeDiagnostic: new FormControl(patientRawValue.anneeDiagnostic),
      consentementRegistre: new FormControl(patientRawValue.consentementRegistre),
      dateEnregistrementRegistre: new FormControl(patientRawValue.dateEnregistrementRegistre),
    });
  }

  getPatient(form: PatientFormGroup): IPatient | NewPatient {
    return form.getRawValue() as IPatient | NewPatient;
  }

  resetForm(form: PatientFormGroup, patient: PatientFormGroupInput): void {
    const patientRawValue = { ...this.getFormDefaults(), ...patient };
    form.reset(
      {
        ...patientRawValue,
        id: { value: patientRawValue.id, disabled: true },
      } as any /* cast to workaround https://github.com/angular/angular/issues/46458 */,
    );
  }

  private getFormDefaults(): PatientFormDefaults {
    return {
      id: null,
    };
  }
}
