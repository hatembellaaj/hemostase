import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFicheHemophilie } from '../fiche-hemophilie.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-hemophilie-detail',
  templateUrl: './fiche-hemophilie-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FicheHemophilieDetailComponent {
  ficheHemophilie = input<IFicheHemophilie | null>(null);

  previousState(): void {
    window.history.back();
  }
}
