import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFicheFacteurXIII } from '../fiche-facteur-xiii.model';
import { FicheFacteurXIIIService } from '../service/fiche-facteur-xiii.service';

@Component({
  standalone: true,
  templateUrl: './fiche-facteur-xiii-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FicheFacteurXIIIDeleteDialogComponent {
  ficheFacteurXIII?: IFicheFacteurXIII;

  protected ficheFacteurXIIIService = inject(FicheFacteurXIIIService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ficheFacteurXIIIService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
