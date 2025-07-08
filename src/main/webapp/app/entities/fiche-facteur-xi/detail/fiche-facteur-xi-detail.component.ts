import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFicheFacteurXI } from '../fiche-facteur-xi.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-facteur-xi-detail',
  templateUrl: './fiche-facteur-xi-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FicheFacteurXIDetailComponent {
  ficheFacteurXI = input<IFicheFacteurXI | null>(null);

  previousState(): void {
    window.history.back();
  }
}
