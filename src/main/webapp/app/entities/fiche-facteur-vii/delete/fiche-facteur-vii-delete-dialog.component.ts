import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFicheFacteurVII } from '../fiche-facteur-vii.model';
import { FicheFacteurVIIService } from '../service/fiche-facteur-vii.service';

@Component({
  standalone: true,
  templateUrl: './fiche-facteur-vii-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FicheFacteurVIIDeleteDialogComponent {
  ficheFacteurVII?: IFicheFacteurVII;

  protected ficheFacteurVIIService = inject(FicheFacteurVIIService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ficheFacteurVIIService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
