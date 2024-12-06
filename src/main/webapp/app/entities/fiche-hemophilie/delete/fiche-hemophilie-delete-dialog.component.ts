import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFicheHemophilie } from '../fiche-hemophilie.model';
import { FicheHemophilieService } from '../service/fiche-hemophilie.service';

@Component({
  standalone: true,
  templateUrl: './fiche-hemophilie-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FicheHemophilieDeleteDialogComponent {
  ficheHemophilie?: IFicheHemophilie;

  protected ficheHemophilieService = inject(FicheHemophilieService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ficheHemophilieService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
