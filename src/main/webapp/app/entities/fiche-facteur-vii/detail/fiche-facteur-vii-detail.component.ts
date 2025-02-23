import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFicheFacteurVII } from '../fiche-facteur-vii.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-facteur-vii-detail',
  templateUrl: './fiche-facteur-vii-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FicheFacteurVIIDetailComponent {
  ficheFacteurVII = input<IFicheFacteurVII | null>(null);

  previousState(): void {
    window.history.back();
  }
}
