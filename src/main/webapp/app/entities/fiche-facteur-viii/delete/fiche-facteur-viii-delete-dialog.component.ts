import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFicheFacteurVIII } from '../fiche-facteur-viii.model';
import { FicheFacteurVIIIService } from '../service/fiche-facteur-viii.service';

@Component({
  standalone: true,
  templateUrl: './fiche-facteur-viii-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FicheFacteurVIIIDeleteDialogComponent {
  ficheFacteurVIII?: IFicheFacteurVIII;

  protected ficheFacteurVIIIService = inject(FicheFacteurVIIIService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ficheFacteurVIIIService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
