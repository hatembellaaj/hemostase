import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFicheFacteurVIII } from '../fiche-facteur-viii.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-facteur-viii-detail',
  templateUrl: './fiche-facteur-viii-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FicheFacteurVIIIDetailComponent {
  ficheFacteurVIII = input<IFicheFacteurVIII | null>(null);

  previousState(): void {
    window.history.back();
  }
}
