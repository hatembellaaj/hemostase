import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import SharedModule from 'app/shared/shared.module';
import { ITEM_DELETED_EVENT } from 'app/config/navigation.constants';
import { IFicheVonWillebrand } from '../fiche-von-willebrand.model';
import { FicheVonWillebrandService } from '../service/fiche-von-willebrand.service';

@Component({
  standalone: true,
  templateUrl: './fiche-von-willebrand-delete-dialog.component.html',
  imports: [SharedModule, FormsModule],
})
export class FicheVonWillebrandDeleteDialogComponent {
  ficheVonWillebrand?: IFicheVonWillebrand;

  protected ficheVonWillebrandService = inject(FicheVonWillebrandService);
  protected activeModal = inject(NgbActiveModal);

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ficheVonWillebrandService.delete(id).subscribe(() => {
      this.activeModal.close(ITEM_DELETED_EVENT);
    });
  }
}
