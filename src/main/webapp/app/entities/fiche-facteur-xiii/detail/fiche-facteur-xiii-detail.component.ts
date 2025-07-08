import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFicheFacteurXIII } from '../fiche-facteur-xiii.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-facteur-xiii-detail',
  templateUrl: './fiche-facteur-xiii-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FicheFacteurXIIIDetailComponent {
  ficheFacteurXIII = input<IFicheFacteurXIII | null>(null);

  previousState(): void {
    window.history.back();
  }
}
