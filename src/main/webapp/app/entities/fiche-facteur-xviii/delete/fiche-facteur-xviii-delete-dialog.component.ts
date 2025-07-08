import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFicheFacteurXVIII } from '../fiche-facteur-xviii.model';
import { FicheFacteurXVIIIService } from '../service/fiche-facteur-xviii.service';

@Component({
  standalone: true,
  templateUrl: './fiche-facteur-xviii-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FicheFacteurXVIIIDeleteDialogComponent {
  ficheFacteurXVIII?: IFicheFacteurXVIII;

  protected ficheFacteurXVIIIService = inject(FicheFacteurXVIIIService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ficheFacteurXVIIIService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
