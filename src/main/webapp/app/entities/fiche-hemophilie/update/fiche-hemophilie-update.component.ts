import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import dayjs from 'dayjs/esm';

import { CivilStatus } from 'app/entities/enumerations/civil-status.model';
import { ServiceCliniqueType } from 'app/entities/enumerations/service-clinique-type.model';
import { CouvertureSociale } from 'app/entities/enumerations/couverture-sociale.model';
import { Gender } from 'app/entities/enumerations/gender.model';
import { Region } from 'app/entities/enumerations/region.model';
import { DiagnosticType } from 'app/entities/enumerations/diagnostic-type.model';
import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { DegreeParente } from 'app/entities/enumerations/degree-parente.model';
import { FormeHemophilie } from 'app/entities/enumerations/forme-hemophilie.model';
import { CircumstanceDecouverte } from 'app/entities/enumerations/circumstance-decouverte.model';
import { TestStatus } from 'app/entities/enumerations/test-status.model';
import { FormeClinique } from 'app/entities/enumerations/forme-clinique.model';
import { Severite } from 'app/entities/enumerations/severite.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';
import { TypeHemorragie } from 'app/entities/enumerations/type-hemorragie.model';
import { TypeHemorragieCutaneoMuqueuse } from 'app/entities/enumerations/type-hemorragie-cutaneo-muqueuse.model';
import { TypeHemorragieViscere } from 'app/entities/enumerations/type-hemorragie-viscere.model';
import { EvolutionSNC } from 'app/entities/enumerations/evolution-snc.model';
import { SuiteOperatoire } from 'app/entities/enumerations/suite-operatoire.model';
import { TypeAccidentHemorragiqueGrave } from 'app/entities/enumerations/type-accident-hemorragique-grave.model';
import { TypePriseEnCharge } from 'app/entities/enumerations/type-prise-en-charge.model';
import { InjectionType } from 'app/entities/enumerations/injection-type.model';
import { ModaliteTraitement } from 'app/entities/enumerations/modalite-traitement.model';
import { PECTherapeutique } from 'app/entities/enumerations/pec-therapeutique.model';
import { TauxInhibiteur } from 'app/entities/enumerations/taux-inhibiteur.model';
import { TauxRecuperation } from 'app/entities/enumerations/taux-recuperation.model';
import { RechercheACC } from 'app/entities/enumerations/recherche-acc.model';
import { EvolutionInhibiteur } from 'app/entities/enumerations/evolution-inhibiteur.model';
import { SchemaTherapeutique } from 'app/entities/enumerations/schema-therapeutique.model';
import { Serologie } from 'app/entities/enumerations/serologie.model';
import { EvolutionVHC } from 'app/entities/enumerations/evolution-vhc.model';
import { VieSociale } from 'app/entities/enumerations/vie-sociale.model';
import { FicheHemophilieService } from '../service/fiche-hemophilie.service';
import { IFicheHemophilie } from '../fiche-hemophilie.model';
import { FicheHemophilieFormGroup, FicheHemophilieFormService } from './fiche-hemophilie-form.service';

@Component({
  standalone: true,
  selector: 'jhi-fiche-hemophilie-update',
  templateUrl: './fiche-hemophilie-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FicheHemophilieUpdateComponent implements OnInit {
  isSaving = false;
  ficheHemophilie: IFicheHemophilie | null = null;
  civilStatusValues = Object.keys(CivilStatus);
  serviceCliniqueTypeValues = Object.keys(ServiceCliniqueType);
  couvertureSocialeValues = Object.keys(CouvertureSociale);
  genderValues = Object.keys(Gender);
  regionValues = Object.keys(Region);
  diagnosticTypeValues = Object.keys(DiagnosticType);
  ouiNonNPValues = Object.keys(OuiNonNP);
  degreeParenteValues = Object.keys(DegreeParente);
  formeHemophilieValues = Object.keys(FormeHemophilie);
  circumstanceDecouverteValues = Object.keys(CircumstanceDecouverte);
  testStatusValues = Object.keys(TestStatus);
  formeCliniqueValues = Object.keys(FormeClinique);
  severiteValues = Object.keys(Severite);
  typeManifestationValues = Object.keys(TypeManifestation);
  typeHemorragieValues = Object.keys(TypeHemorragie);
  typeHemorragieCutaneoMuqueuseValues = Object.keys(TypeHemorragieCutaneoMuqueuse);
  typeHemorragieViscereValues = Object.keys(TypeHemorragieViscere);
  evolutionSNCValues = Object.keys(EvolutionSNC);
  suiteOperatoireValues = Object.keys(SuiteOperatoire);
  typeAccidentHemorragiqueGraveValues = Object.keys(TypeAccidentHemorragiqueGrave);
  typePriseEnChargeValues = Object.keys(TypePriseEnCharge);
  injectionTypeValues = Object.keys(InjectionType);
  modaliteTraitementValues = Object.keys(ModaliteTraitement);
  pECTherapeutiqueValues = Object.keys(PECTherapeutique);
  tauxInhibiteurValues = Object.keys(TauxInhibiteur);
  tauxRecuperationValues = Object.keys(TauxRecuperation);
  rechercheACCValues = Object.keys(RechercheACC);
  evolutionInhibiteurValues = Object.keys(EvolutionInhibiteur);
  schemaTherapeutiqueValues = Object.keys(SchemaTherapeutique);
  serologieValues = Object.keys(Serologie);
  evolutionVHCValues = Object.keys(EvolutionVHC);
  vieSocialeValues = Object.keys(VieSociale);

  protected ficheHemophilieService = inject(FicheHemophilieService);
  protected ficheHemophilieFormService = inject(FicheHemophilieFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FicheHemophilieFormGroup = this.ficheHemophilieFormService.createFicheHemophilieFormGroup();

  ngOnInit(): void {
    this.registerDateNaissanceWatcher();
    this.activatedRoute.data.subscribe(({ ficheHemophilie }) => {
      this.ficheHemophilie = ficheHemophilie;
      if (ficheHemophilie) {
        this.updateForm(ficheHemophilie);
      }
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

  onSiegeChange(event: Event): void {
    const checkbox = event.target as HTMLInputElement;
    const siegeCtrl = this.editForm.get('hemarthroseSiege');
    let currentValue = siegeCtrl?.value ? siegeCtrl.value.split(',').map(s => s.trim()) : [];

    if (checkbox.checked) {
      currentValue.push(checkbox.value);
    } else {
      currentValue = currentValue.filter(val => val !== checkbox.value);
    }

    siegeCtrl?.setValue(currentValue.join(', '));
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
    this.updateAgeActuel(this.editForm.get('dateNaissance')?.value);
  }

  private registerDateNaissanceWatcher(): void {
    this.editForm.get('dateNaissance')?.valueChanges.subscribe(dateNaissance => this.updateAgeActuel(dateNaissance));
  }

  private updateAgeActuel(dateNaissance: dayjs.Dayjs | null | undefined): void {
    const ageControl = this.editForm.get('ageActuel');

    if (!ageControl) {
      return;
    }

    if (!dateNaissance || !dayjs.isDayjs(dateNaissance) || !dateNaissance.isValid()) {
      ageControl.setValue(null, { emitEvent: false });
      return;
    }

    const age = dayjs().diff(dateNaissance, 'year');
    ageControl.setValue(age >= 0 ? age : null, { emitEvent: false });
  }
}
