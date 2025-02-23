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
import { NormalAllonge } from 'app/entities/enumerations/normal-allonge.model';
import { MutationType } from 'app/entities/enumerations/mutation-type.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';
import { TypeAccidentsThrombotiques } from 'app/entities/enumerations/type-accidents-thrombotiques.model';
import { TypeHemorragieGrave } from 'app/entities/enumerations/type-hemorragie-grave.model';
import { VieSocialeType } from 'app/entities/enumerations/vie-sociale-type.model';
import { FicheFacteurVIIIService } from '../service/fiche-facteur-viii.service';
import { IFicheFacteurVIII } from '../fiche-facteur-viii.model';
import { FicheFacteurVIIIFormGroup, FicheFacteurVIIIFormService } from './fiche-facteur-viii-form.service';

@Component({
  standalone: true,
  selector: 'jhi-fiche-facteur-viii-update',
  templateUrl: './fiche-facteur-viii-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FicheFacteurVIIIUpdateComponent implements OnInit {
  isSaving = false;
  ficheFacteurVIII: IFicheFacteurVIII | null = null;
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
  normalAllongeValues = Object.keys(NormalAllonge);
  mutationTypeValues = Object.keys(MutationType);
  typeManifestationValues = Object.keys(TypeManifestation);
  typeAccidentsThrombotiquesValues = Object.keys(TypeAccidentsThrombotiques);
  typeHemorragieGraveValues = Object.keys(TypeHemorragieGrave);
  vieSocialeTypeValues = Object.keys(VieSocialeType);

  protected ficheFacteurVIIIService = inject(FicheFacteurVIIIService);
  protected ficheFacteurVIIIFormService = inject(FicheFacteurVIIIFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FicheFacteurVIIIFormGroup = this.ficheFacteurVIIIFormService.createFicheFacteurVIIIFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ficheFacteurVIII }) => {
      this.ficheFacteurVIII = ficheFacteurVIII;
      if (ficheFacteurVIII) {
        this.updateForm(ficheFacteurVIII);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ficheFacteurVIII = this.ficheFacteurVIIIFormService.getFicheFacteurVIII(this.editForm);
    if (ficheFacteurVIII.id !== null) {
      this.subscribeToSaveResponse(this.ficheFacteurVIIIService.update(ficheFacteurVIII));
    } else {
      this.subscribeToSaveResponse(this.ficheFacteurVIIIService.create(ficheFacteurVIII));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFicheFacteurVIII>>): void {
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

  protected updateForm(ficheFacteurVIII: IFicheFacteurVIII): void {
    this.ficheFacteurVIII = ficheFacteurVIII;
    this.ficheFacteurVIIIFormService.resetForm(this.editForm, ficheFacteurVIII);
  }
}
