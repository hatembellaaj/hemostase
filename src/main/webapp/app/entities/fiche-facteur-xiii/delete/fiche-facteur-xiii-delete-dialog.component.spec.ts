jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { FicheFacteurXIIIService } from '../service/fiche-facteur-xiii.service';

import { FicheFacteurXIIIDeleteDialogComponent } from './fiche-facteur-xiii-delete-dialog.component';

describe('FicheFacteurXIII Management Delete Component', () => {
  let comp: FicheFacteurXIIIDeleteDialogComponent;
  let fixture: ComponentFixture<FicheFacteurXIIIDeleteDialogComponent>;
  let service: FicheFacteurXIIIService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheFacteurXIIIDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(FicheFacteurXIIIDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FicheFacteurXIIIDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FicheFacteurXIIIService);
    mockActiveModal = TestBed.inject(NgbActiveModal);
  });

  describe('confirmDelete', () => {
    it('Should call delete service on confirmDelete', inject(
      [],
      fakeAsync(() => {
        // GIVEN
        jest.spyOn(service, 'delete').mockReturnValue(of(new HttpResponse({ body: {} })));

        // WHEN
        comp.confirmDelete(123);
        tick();

        // THEN
        expect(service.delete).toHaveBeenCalledWith(123);
        expect(mockActiveModal.close).toHaveBeenCalledWith('deleted');
      }),
    ));

    it('Should not call delete service on clear', () => {
      // GIVEN
      jest.spyOn(service, 'delete');

      // WHEN
      comp.cancel();

      // THEN
      expect(service.delete).not.toHaveBeenCalled();
      expect(mockActiveModal.close).not.toHaveBeenCalled();
      expect(mockActiveModal.dismiss).toHaveBeenCalled();
    });
  });
});
