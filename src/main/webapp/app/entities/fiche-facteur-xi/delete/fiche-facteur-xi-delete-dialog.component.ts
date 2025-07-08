import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFicheFacteurXI } from '../fiche-facteur-xi.model';
import { FicheFacteurXIService } from '../service/fiche-facteur-xi.service';

@Component({
  standalone: true,
  templateUrl: './fiche-facteur-xi-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FicheFacteurXIDeleteDialogComponent {
  ficheFacteurXI?: IFicheFacteurXI;

  protected ficheFacteurXIService = inject(FicheFacteurXIService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ficheFacteurXIService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
