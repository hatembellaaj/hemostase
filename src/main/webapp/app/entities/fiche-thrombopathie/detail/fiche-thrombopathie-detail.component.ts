import { Component, input } from '@angular/core';
import { RouterModule } from '@angular/router';

import SharedModule from 'app/shared/shared.module';
import { DurationPipe, FormatMediumDatePipe, FormatMediumDatetimePipe } from 'app/shared/date';
import { IFicheThrombopathie } from '../fiche-thrombopathie.model';

@Component({
  standalone: true,
  selector: 'jhi-fiche-thrombopathie-detail',
  templateUrl: './fiche-thrombopathie-detail.component.html',
  imports: [SharedModule, RouterModule, DurationPipe, FormatMediumDatetimePipe, FormatMediumDatePipe],
})
export class FicheThrombopathieDetailComponent {
  ficheThrombopathie = input<IFicheThrombopathie | null>(null);

  previousState(): void {
    window.history.back();
  }
}
