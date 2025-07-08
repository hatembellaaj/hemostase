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
import { TypeFaitOuNonFaitEtResultat } from 'app/entities/enumerations/type-fait-ou-non-fait-et-resultat.model';
import { MutationType } from 'app/entities/enumerations/mutation-type.model';
import { TypeManifestation } from 'app/entities/enumerations/type-manifestation.model';
import { AccidentsThrombotiquesType } from 'app/entities/enumerations/accidents-thrombotiques-type.model';
import { VieSociale } from 'app/entities/enumerations/vie-sociale.model';
import { FicheFacteurXIIIService } from '../service/fiche-facteur-xiii.service';
import { IFicheFacteurXIII } from '../fiche-facteur-xiii.model';
import { FicheFacteurXIIIFormGroup, FicheFacteurXIIIFormService } from './fiche-facteur-xiii-form.service';

@Component({
  standalone: true,
  selector: 'jhi-fiche-facteur-xiii-update',
  templateUrl: './fiche-facteur-xiii-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FicheFacteurXIIIUpdateComponent implements OnInit {
  isSaving = false;
  ficheFacteurXIII: IFicheFacteurXIII | null = null;
  civilStatusValues = Object.keys(CivilStatus);
  serviceCliniqueTypeValues = Object.keys(ServiceCliniqueType);
  couvertureSocialeValues = Object.keys(CouvertureSociale);
  genderValues = Object.keys(Gender);
  regionValues = Object.keys(Region);
  diagnosticTypeValues = Object.keys(DiagnosticType);
  ouiNonNPValues = Object.keys(OuiNonNP);
  degreParenteValues = Object.keys(DegreParente);
  lienParenteValues = Object.keys(LienParente);
  typeFaitOuNonFaitEtResultatValues = Object.keys(TypeFaitOuNonFaitEtResultat);
  mutationTypeValues = Object.keys(MutationType);
  typeManifestationValues = Object.keys(TypeManifestation);
  accidentsThrombotiquesTypeValues = Object.keys(AccidentsThrombotiquesType);
  vieSocialeValues = Object.keys(VieSociale);

  protected ficheFacteurXIIIService = inject(FicheFacteurXIIIService);
  protected ficheFacteurXIIIFormService = inject(FicheFacteurXIIIFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FicheFacteurXIIIFormGroup = this.ficheFacteurXIIIFormService.createFicheFacteurXIIIFormGroup();

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ficheFacteurXIII }) => {
      this.ficheFacteurXIII = ficheFacteurXIII;
      if (ficheFacteurXIII) {
        this.updateForm(ficheFacteurXIII);
      }
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ficheFacteurXIII = this.ficheFacteurXIIIFormService.getFicheFacteurXIII(this.editForm);
    if (ficheFacteurXIII.id !== null) {
      this.subscribeToSaveResponse(this.ficheFacteurXIIIService.update(ficheFacteurXIII));
    } else {
      this.subscribeToSaveResponse(this.ficheFacteurXIIIService.create(ficheFacteurXIII));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFicheFacteurXIII>>): void {
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

  protected updateForm(ficheFacteurXIII: IFicheFacteurXIII): void {
    this.ficheFacteurXIII = ficheFacteurXIII;
    this.ficheFacteurXIIIFormService.resetForm(this.editForm, ficheFacteurXIII);
  }
}
