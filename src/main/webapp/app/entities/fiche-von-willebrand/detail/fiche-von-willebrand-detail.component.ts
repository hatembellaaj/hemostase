import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFicheVonWillebrand } from '../fiche-von-willebrand.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-von-willebrand-detail',
  templateUrl: './fiche-von-willebrand-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FicheVonWillebrandDetailComponent {
  ficheVonWillebrand = input<IFicheVonWillebrand | null>(null);

  previousState(): void {
    window.history.back();
  }
}
