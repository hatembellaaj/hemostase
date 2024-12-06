import { Component, OnInit, inject } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { finalize } from 'rxjs/operators';

import SharedModule from 'app/shared/shared.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { Gender } from 'app/entities/enumerations/gender.model';
import { Region } from 'app/entities/enumerations/region.model';
import { CivilStatus } from 'app/entities/enumerations/civil-status.model';
import { CouvertureSociale } from 'app/entities/enumerations/couverture-sociale.model';
import { DiagnosticType } from 'app/entities/enumerations/diagnostic-type.model';
import { OuiNonNP } from 'app/entities/enumerations/oui-non-np.model';
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
  genderValues = Object.keys(Gender);
  regionValues = Object.keys(Region);
  civilStatusValues = Object.keys(CivilStatus);
  couvertureSocialeValues = Object.keys(CouvertureSociale);
  diagnosticTypeValues = Object.keys(DiagnosticType);
  ouiNonNPValues = Object.keys(OuiNonNP);

  protected ficheHemophilieService = inject(FicheHemophilieService);
  protected ficheHemophilieFormService = inject(FicheHemophilieFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FicheHemophilieFormGroup = this.ficheHemophilieFormService.createFicheHemophilieFormGroup();

  ngOnInit(): void {
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
  }
}
