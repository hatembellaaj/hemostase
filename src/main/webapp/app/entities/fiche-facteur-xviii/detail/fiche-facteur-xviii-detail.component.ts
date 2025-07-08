import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFicheFacteurXVIII } from '../fiche-facteur-xviii.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-facteur-xviii-detail',
  templateUrl: './fiche-facteur-xviii-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FicheFacteurXVIIIDetailComponent {
  ficheFacteurXVIII = input<IFicheFacteurXVIII | null>(null);

  previousState(): void {
    window.history.back();
  }
}
