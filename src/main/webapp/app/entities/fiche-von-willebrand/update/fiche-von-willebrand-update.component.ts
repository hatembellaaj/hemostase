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
import { CirconstanceDecouverteMvW } from 'app/entities/enumerations/circonstance-decouverte-mv-w.model';
import { TypeMvW } from 'app/entities/enumerations/type-mv-w.model';
import { SousTypeMvW } from 'app/entities/enumerations/sous-type-mv-w.model';
import { GroupeSanguin } from 'app/entities/enumerations/groupe-sanguin.model';
import { TypeTS } from 'app/entities/enumerations/type-ts.model';
import { TypeOcclusion } from 'app/entities/enumerations/type-occlusion.model';
import { TypeRipaResultat } from 'app/entities/enumerations/type-ripa-resultat.model';
import { TypeMultimere } from 'app/entities/enumerations/type-multimere.model';
import { TypeResultatPatho } from 'app/entities/enumerations/type-resultat-patho.model';
import { TypeFvWppRatio } from 'app/entities/enumerations/type-fv-wpp-ratio.model';
import { TypeZygote } from 'app/entities/enumerations/type-zygote.model';
import { TypeHemorragieVisceres } from 'app/entities/enumerations/type-hemorragie-visceres.model';
import { ResultatSerologie } from 'app/entities/enumerations/resultat-serologie.model';
import { StatutSocial } from 'app/entities/enumerations/statut-social.model';
import { CauseDeces } from 'app/entities/enumerations/cause-deces.model';
import { FicheVonWillebrandService } from '../service/fiche-von-willebrand.service';
import { IFicheVonWillebrand } from '../fiche-von-willebrand.model';
import { FicheVonWillebrandFormGroup, FicheVonWillebrandFormService } from './fiche-von-willebrand-form.service';

@Component({
  standalone: true,
  selector: 'jhi-fiche-von-willebrand-update',
  templateUrl: './fiche-von-willebrand-update.component.html',
  imports: [SharedModule, FormsModule, ReactiveFormsModule],
})
export class FicheVonWillebrandUpdateComponent implements OnInit {
  isSaving = false;
  ficheVonWillebrand: IFicheVonWillebrand | null = null;
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
  circonstanceDecouverteMvWValues = Object.keys(CirconstanceDecouverteMvW);
  typeMvWValues = Object.keys(TypeMvW);
  sousTypeMvWValues = Object.keys(SousTypeMvW);
  groupeSanguinValues = Object.keys(GroupeSanguin);
  typeTSValues = Object.keys(TypeTS);
  typeOcclusionValues = Object.keys(TypeOcclusion);
  typeRipaResultatValues = Object.keys(TypeRipaResultat);
  typeMultimereValues = Object.keys(TypeMultimere);
  typeResultatPathoValues = Object.keys(TypeResultatPatho);
  typeFvWppRatioValues = Object.keys(TypeFvWppRatio);
  typeZygoteValues = Object.keys(TypeZygote);
  typeHemorragieVisceresValues = Object.keys(TypeHemorragieVisceres);
  resultatSerologieValues = Object.keys(ResultatSerologie);
  statutSocialValues = Object.keys(StatutSocial);
  causeDecesValues = Object.keys(CauseDeces);

  protected ficheVonWillebrandService = inject(FicheVonWillebrandService);
  protected ficheVonWillebrandFormService = inject(FicheVonWillebrandFormService);
  protected activatedRoute = inject(ActivatedRoute);

  // eslint-disable-next-line @typescript-eslint/member-ordering
  editForm: FicheVonWillebrandFormGroup = this.ficheVonWillebrandFormService.createFicheVonWillebrandFormGroup();

ngOnInit(): void {
  this.activatedRoute.data.subscribe(({ ficheVonWillebrand }) => {
    this.ficheVonWillebrand = ficheVonWillebrand;

    if (ficheVonWillebrand) {
      this.updateForm(ficheVonWillebrand);
    }

    // Calcul automatique des ratios
    this.editForm.valueChanges.subscribe(values => {
      const fvwag = values.fvwag;
      const fvwact = values.fvwact;
      const f8c = values.f8c;

      if (fvwag && fvwact && fvwag !== 0) {
        const ratioFvwactFvwag = parseFloat((fvwact / fvwag).toFixed(2));
        this.editForm.get('ratioFvwactFvwag')?.setValue(ratioFvwactFvwag, { emitEvent: false });
      }

      if (fvwag && f8c && fvwag !== 0) {
        const ratioF8cFvwag = parseFloat((f8c / fvwag).toFixed(2));
        this.editForm.get('ratioF8cFvwag')?.setValue(ratioF8cFvwag, { emitEvent: false });
      }
    });

    // Calcul automatique de l'âge actuel à partir de la date de naissance
    this.editForm.get('dateNaissance')?.valueChanges.subscribe((dateNaissance: any) => {
      if (dateNaissance && dateNaissance.year && dateNaissance.month && dateNaissance.day) {
        const today = new Date();
        const birthDate = new Date(dateNaissance.year, dateNaissance.month - 1, dateNaissance.day);
        let age = today.getFullYear() - birthDate.getFullYear();
        const m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
          age--;
        }
        this.editForm.get('ageActuel')?.setValue(age, { emitEvent: false });
      } else {
        this.editForm.get('ageActuel')?.setValue(null, { emitEvent: false });
      }
    });
  });
}



  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ficheVonWillebrand = this.ficheVonWillebrandFormService.getFicheVonWillebrand(this.editForm);
    if (ficheVonWillebrand.id !== null) {
      this.subscribeToSaveResponse(this.ficheVonWillebrandService.update(ficheVonWillebrand));
    } else {
      this.subscribeToSaveResponse(this.ficheVonWillebrandService.create(ficheVonWillebrand));
    }
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFicheVonWillebrand>>): void {
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

  protected updateForm(ficheVonWillebrand: IFicheVonWillebrand): void {
    this.ficheVonWillebrand = ficheVonWillebrand;
    this.ficheVonWillebrandFormService.resetForm(this.editForm, ficheVonWillebrand);
  }
}
