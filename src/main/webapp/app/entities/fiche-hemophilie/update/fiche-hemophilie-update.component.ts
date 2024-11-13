import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize, map } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { IPatient } from 'app/entities/patient/patient.model';
import { PatientService } from 'app/entities/patient/service/patient.service';
import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { FormeHemophilie } from 'app/entities/enumerations/forme-hemophilie.model';
import { TestStatus } from 'app/entities/enumerations/test-status.model';
import { ArticulationCible } from 'app/entities/enumerations/articulation-cible.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';
import { TypeHemorragie } from 'app/entities/enumerations/type-hemorragie.model';
import { TypeHemorragieVisceres } from 'app/entities/enumerations/type-hemorragie-visceres.model';
import { EvolutionSNC } from 'app/entities/enumerations/evolution-snc.model';
import { TypePreparation } from 'app/entities/enumerations/type-preparation.model';
import { SuiteOperatoire } from 'app/entities/enumerations/suite-operatoire.model';
import { AccidentsGravesType } from 'app/entities/enumerations/accidents-graves-type.model';
import { PriseEnChargeType } from 'app/entities/enumerations/prise-en-charge-type.model';
import { InjectionType } from 'app/entities/enumerations/injection-type.model';
import { TraitementType } from 'app/entities/enumerations/traitement-type.model';
import { TypeComplicationsOrthopediques } from 'app/entities/enumerations/type-complications-orthopediques.model';
import { PECTherapeutique } from 'app/entities/enumerations/pec-therapeutique.model';
import { TauxInhibiteur } from 'app/entities/enumerations/taux-inhibiteur.model';
import { TauxRecuperation } from 'app/entities/enumerations/taux-recuperation.model';
import { RechercheACC } from 'app/entities/enumerations/recherche-acc.model';
import { CATTherapeutique } from 'app/entities/enumerations/cat-therapeutique.model';
import { Observance } from 'app/entities/enumerations/observance.model';
import { EvolutionInhibiteur } from 'app/entities/enumerations/evolution-inhibiteur.model';
import { SchemaTherapeutique } from 'app/entities/enumerations/schema-therapeutique.model';
import { Serologie } from 'app/entities/enumerations/serologie.model';
import { EvolutionVHC } from 'app/entities/enumerations/evolution-vhc.model';
import { FicheHemophilieService } from '../service/fiche-hemophilie.service';
import { IFicheHemophilie } from '../fiche-hemophilie.model';
import { FicheHemophilieFormGroup, FicheHemophilieFormService } from './fiche-hemophilie-form.service';
import { DiagnosticType } from 'app/entities/enumerations/diagnostic-type.model';
import { circumstanceDecouverte } from 'app/entities/enumerations/circumstance-decouverte.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-hemophilie-update',
  templateUrl: './fiche-hemophilie-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FicheHemophilieUpdateComponent implements OnInit {
  isSaving = false;
  ficheHemophilie: IFicheHemophilie | null = null;
  ouiNonNPValues = Object.keys(OuiNonNP);
  formeHemophilieValues = Object.keys(FormeHemophilie);
  testStatusValues = Object.keys(TestStatus);
  articulationCibleValues = Object.keys(ArticulationCible);
  typeManifestationValues = Object.keys(TypeManifestation);
  typeHemorragieValues = Object.keys(TypeHemorragie);
  typeHemorragieVisceresValues = Object.keys(TypeHemorragieVisceres);
  evolutionSNCValues = Object.keys(EvolutionSNC);
  typePreparationValues = Object.keys(TypePreparation);
  suiteOperatoireValues = Object.keys(SuiteOperatoire);
  accidentsGravesTypeValues = Object.keys(AccidentsGravesType);
  priseEnChargeTypeValues = Object.keys(PriseEnChargeType);
  injectionTypeValues = Object.keys(InjectionType);
  traitementTypeValues = Object.keys(TraitementType);
  typeComplicationsOrthopediquesValues = Object.keys(TypeComplicationsOrthopediques);
  pECTherapeutiqueValues = Object.keys(PECTherapeutique);
  tauxInhibiteurValues = Object.keys(TauxInhibiteur);
  tauxRecuperationValues = Object.keys(TauxRecuperation);
  rechercheACCValues = Object.keys(RechercheACC);
  cATTherapeutiqueValues = Object.keys(CATTherapeutique);
  observanceValues = Object.keys(Observance);
  evolutionInhibiteurValues = Object.keys(EvolutionInhibiteur);
  schemaTherapeutiqueValues = Object.keys(SchemaTherapeutique);
  serologieValues = Object.keys(Serologie);
  evolutionVHCValues = Object.keys(EvolutionVHC);
  diagnosticTypeValues = Object.keys(DiagnosticType);
  circumstanceDecouverteValues  = Object.keys(circumstanceDecouverte);

  patientsSharedCollection: IPatient[] = [];

  protected ficheHemophilieService = inject(FicheHemophilieService);
  protected ficheHemophilieFormService = inject(FicheHemophilieFormService);
  protected patientService = inject(PatientService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FicheHemophilieFormGroup = this.ficheHemophilieFormService.createFicheHemophilieFormGroup();

  comparePatient = (o1: IPatient | null, o2: IPatient | null): boolean => this.patientService.comparePatient(o1, o2);

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ficheHemophilie }) => {
      this.ficheHemophilie = ficheHemophilie;
      if (ficheHemophilie) {
        this.updateForm(ficheHemophilie);
      }

      this.loadRelationshipsOptions();
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ficheHemophilie = this.ficheHemophilieFormService.getFicheHemophilie(this.editForm);
    if (ficheHemophilie.id !== null) {
      this.subscribeToSaveResponse(this.ficheHemophilieService.update(ficheHemophilie));
    } else {
      this.subscribeToSaveResponse(this.ficheHemophilieService.create(ficheHemophilie));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFicheHemophilie>>): void {
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

  protected updateForm(ficheHemophilie: IFicheHemophilie): void {
    this.ficheHemophilie = ficheHemophilie;
    this.ficheHemophilieFormService.resetForm(this.editForm, ficheHemophilie);

    this.patientsSharedCollection = this.patientService.addPatientToCollectionIfMissing<IPatient>(
      this.patientsSharedCollection,
      ficheHemophilie.patient,
    );
  }

  protected loadRelationshipsOptions(): void {
    this.patientService
      .query()
      .pipe(map((res: HttpResponse<IPatient[]>) => res.body ?? []))
      .pipe(
        map((patients: IPatient[]) =>
          this.patientService.addPatientToCollectionIfMissing<IPatient>(patients, this.ficheHemophilie?.patient),
        ),
      )
      .subscribe((patients: IPatient[]) => (this.patientsSharedCollection = patients));
  }
}
