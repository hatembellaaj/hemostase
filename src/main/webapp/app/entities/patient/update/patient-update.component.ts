import { Component, OnDestroy, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import dayjs from 'dayjs/esm';
import { Observable, Subject } from 'rxjs';
import { finalize, takeUntil } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { Gender } from 'app/entities/enumerations/gender.model';
import { Region } from 'app/entities/enumerations/region.model';
import { CivilStatus } from 'app/entities/enumerations/civil-status.model';
import { CouvertureSociale } from 'app/entities/enumerations/couverture-sociale.model';
import { DiagnosticType } from 'app/entities/enumerations/diagnostic-type.model';
import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { PatientService } from '../service/patient.service';
import { IPatient } from '../patient.model';
import { PatientFormGroup, PatientFormService } from './patient-form.service';

@Component({
  standalone: true,
  selector: 'jhi-patient-update',
  templateUrl: './patient-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class PatientUpdateComponent implements OnInit, OnDestroy {
  isSaving = false;
  patient: IPatient | null = null;
  genderValues = Object.keys(Gender);
  regionValues = Object.keys(Region);
  civilStatusValues = Object.keys(CivilStatus);
  couvertureSocialeValues = Object.keys(CouvertureSociale);
  diagnosticTypeValues = Object.keys(DiagnosticType);
  ouiNonNPValues = Object.keys(OuiNonNP);

  protected patientService = inject(PatientService);
  protected patientFormService = inject(PatientFormService);
  protected activatedRoute = inject(ActivatedRoute);

  private readonly destroy$ = new Subject<void>();

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: PatientFormGroup = this.patientFormService.createPatientFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ patient }) => {
      this.patient = patient;
      if (patient) {
        this.updateForm(patient);
      }
      this.syncAgeFromBirthDate();
      this.registerBirthDateWatcher();
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    this.syncAgeFromBirthDate();
    const patient = this.patientFormService.getPatient(this.editForm);
    if (patient.id !== null) {
      this.subscribeToSaveResponse(this.patientService.update(patient));
    } else {
      this.subscribeToSaveResponse(this.patientService.create(patient));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPatient>>): void {
    result.pipe(finalize(() => this.onSaveFinalize())).subscribe({
      next: () => this.onSaveSuccess(),
      error: () => this.onSaveError(),
    });
  }

  protected onSaveSuccess(): void {
    this.previousState();
  }

  protected onSaveError(): void {
    // Api for inheritance.
  }

  protected onSaveFinalize(): void {
    this.isSaving = false;
  }

  protected updateForm(patient: IPatient): void {
    this.patient = patient;
    this.patientFormService.resetForm(this.editForm, patient);
  }

  private registerBirthDateWatcher(): void {
    this.editForm
      .get('dateNaissance')
      ?.valueChanges.pipe(takeUntil(this.destroy$))
      .subscribe(value => this.syncAgeFromBirthDate(value));
  }

  private syncAgeFromBirthDate(dateValue?: dayjs.Dayjs | string | null): void {
    const ageControl = this.editForm.get('ageActuel');
    if (!ageControl) {
      return;
    }

    const birthDate = this.normalizeDateValue(dateValue ?? this.editForm.get('dateNaissance')?.value);
    const age = birthDate ? dayjs().diff(birthDate, 'year') : null;

    ageControl.setValue(age, { emitEvent: false });
  }

  private normalizeDateValue(dateValue: unknown): dayjs.Dayjs | null {
    if (!dateValue) {
      return null;
    }

    if (dayjs.isDayjs(dateValue)) {
      return dateValue;
    }

    const parsedDate = dayjs(dateValue);
    return parsedDate.isValid() ? parsedDate : null;
  }
}
