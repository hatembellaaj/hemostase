import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { CivilStatus } from 'app/entities/enumerations/civil-status.model';
import { ServiceCliniqueType } from 'app/entities/enumerations/service-clinique-type.model';
import { CouvertureSociale } from 'app/entities/enumerations/couverture-sociale.model';
import { Gender } from 'app/entities/enumerations/gender.model';
import { Region } from 'app/entities/enumerations/region.model';
import { DiagnosticType } from 'app/entities/enumerations/diagnostic-type.model';
import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
import { FaiteNonFaite } from 'app/entities/enumerations/faite-non-faite.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';
import { FicheThrombopathieService } from '../service/fiche-thrombopathie.service';
import { IFicheThrombopathie } from '../fiche-thrombopathie.model';
import { FicheThrombopathieFormGroup, FicheThrombopathieFormService } from './fiche-thrombopathie-form.service';

@Component({
  standalone: true,
  selector: 'jhi-fiche-thrombopathie-update',
  templateUrl: './fiche-thrombopathie-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FicheThrombopathieUpdateComponent implements OnInit {
  isSaving = false;
  ficheThrombopathie: IFicheThrombopathie | null = null;
  civilStatusValues = Object.keys(CivilStatus);
  serviceCliniqueTypeValues = Object.keys(ServiceCliniqueType);
  couvertureSocialeValues = Object.keys(CouvertureSociale);
  genderValues = Object.keys(Gender);
  regionValues = Object.keys(Region);
  diagnosticTypeValues = Object.keys(DiagnosticType);
  ouiNonNPValues = Object.keys(OuiNonNP);
  faiteNonFaiteValues = Object.keys(FaiteNonFaite);
  typeManifestationValues = Object.keys(TypeManifestation);

  protected ficheThrombopathieService = inject(FicheThrombopathieService);
  protected ficheThrombopathieFormService = inject(FicheThrombopathieFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FicheThrombopathieFormGroup = this.ficheThrombopathieFormService.createFicheThrombopathieFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ficheThrombopathie }) => {
      this.ficheThrombopathie = ficheThrombopathie;
      if (ficheThrombopathie) {
        this.updateForm(ficheThrombopathie);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ficheThrombopathie = this.ficheThrombopathieFormService.getFicheThrombopathie(this.editForm);
    if (ficheThrombopathie.id !== null) {
      this.subscribeToSaveResponse(this.ficheThrombopathieService.update(ficheThrombopathie));
    } else {
      this.subscribeToSaveResponse(this.ficheThrombopathieService.create(ficheThrombopathie));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFicheThrombopathie>>): void {
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

  protected updateForm(ficheThrombopathie: IFicheThrombopathie): void {
    this.ficheThrombopathie = ficheThrombopathie;
    this.ficheThrombopathieFormService.resetForm(this.editForm, ficheThrombopathie);
  }

  getAgeEnAnnees(): string {
  const mois = this.editForm.get('agePremierSigneHemorragique')?.value;
  if (mois != null && !isNaN(mois)) {
    return (mois / 12).toFixed(2); // 2 chiffres après la virgule
  }
  return '';
}

}
