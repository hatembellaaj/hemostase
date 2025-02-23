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
import { DegreParente } from 'app/entities/enumerations/degre-parente.model';
import { LienParente } from 'app/entities/enumerations/lien-parente.model';
import { FaiteNonFaite } from 'app/entities/enumerations/faite-non-faite.model';
import { ExplorationType } from 'app/entities/enumerations/exploration-type.model';
import { MutationType } from 'app/entities/enumerations/mutation-type.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';
import { TypeAccidentsThrombotiques } from 'app/entities/enumerations/type-accidents-thrombotiques.model';
import { TypeHemorragieGrave } from 'app/entities/enumerations/type-hemorragie-grave.model';
import { VieSocialeType } from 'app/entities/enumerations/vie-sociale-type.model';
import { FicheFacteurVIIService } from '../service/fiche-facteur-vii.service';
import { IFicheFacteurVII } from '../fiche-facteur-vii.model';
import { FicheFacteurVIIFormGroup, FicheFacteurVIIFormService } from './fiche-facteur-vii-form.service';

@Component({
  standalone: true,
  selector: 'jhi-fiche-facteur-vii-update',
  templateUrl: './fiche-facteur-vii-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FicheFacteurVIIUpdateComponent implements OnInit {
  isSaving = false;
  ficheFacteurVII: IFicheFacteurVII | null = null;
  civilStatusValues = Object.keys(CivilStatus);
  serviceCliniqueTypeValues = Object.keys(ServiceCliniqueType);
  couvertureSocialeValues = Object.keys(CouvertureSociale);
  genderValues = Object.keys(Gender);
  regionValues = Object.keys(Region);
  diagnosticTypeValues = Object.keys(DiagnosticType);
  ouiNonNPValues = Object.keys(OuiNonNP);
  degreParenteValues = Object.keys(DegreParente);
  lienParenteValues = Object.keys(LienParente);
  faiteNonFaiteValues = Object.keys(FaiteNonFaite);
  explorationTypeValues = Object.keys(ExplorationType);
  mutationTypeValues = Object.keys(MutationType);
  typeManifestationValues = Object.keys(TypeManifestation);
  typeAccidentsThrombotiquesValues = Object.keys(TypeAccidentsThrombotiques);
  typeHemorragieGraveValues = Object.keys(TypeHemorragieGrave);
  vieSocialeTypeValues = Object.keys(VieSocialeType);

  protected ficheFacteurVIIService = inject(FicheFacteurVIIService);
  protected ficheFacteurVIIFormService = inject(FicheFacteurVIIFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FicheFacteurVIIFormGroup = this.ficheFacteurVIIFormService.createFicheFacteurVIIFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ficheFacteurVII }) => {
      this.ficheFacteurVII = ficheFacteurVII;
      if (ficheFacteurVII) {
        this.updateForm(ficheFacteurVII);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ficheFacteurVII = this.ficheFacteurVIIFormService.getFicheFacteurVII(this.editForm);
    if (ficheFacteurVII.id !== null) {
      this.subscribeToSaveResponse(this.ficheFacteurVIIService.update(ficheFacteurVII));
    } else {
      this.subscribeToSaveResponse(this.ficheFacteurVIIService.create(ficheFacteurVII));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFicheFacteurVII>>): void {
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

  protected updateForm(ficheFacteurVII: IFicheFacteurVII): void {
    this.ficheFacteurVII = ficheFacteurVII;
    this.ficheFacteurVIIFormService.resetForm(this.editForm, ficheFacteurVII);
  }
}
