jest.mock('@ng-bootstrap/ng-bootstrap');

import { ComponentFixture, TestBed, fakeAsync, inject, tick } from '@angular/core/testing';
import { HttpResponse, provideHttpClient } from '@angular/common/http';
import { of } from 'rxjs';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { FicheFacteurVIIService } from '../service/fiche-facteur-vii.service';

import { FicheFacteurVIIDeleteDialogComponent } from './fiche-facteur-vii-delete-dialog.component';

describe('FicheFacteurVII Management Delete Component', () => {
  let comp: FicheFacteurVIIDeleteDialogComponent;
  let fixture: ComponentFixture<FicheFacteurVIIDeleteDialogComponent>;
  let service: FicheFacteurVIIService;
  let mockActiveModal: NgbActiveModal;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FicheFacteurVIIDeleteDialogComponent],
      providers: [provideHttpClient(), NgbActiveModal],
    })
      .overrideTemplate(FicheFacteurVIIDeleteDialogComponent, '')
      .compileComponents();
    fixture = TestBed.createComponent(FicheFacteurVIIDeleteDialogComponent);
    comp = fixture.componentInstance;
    service = TestBed.inject(FicheFacteurVIIService);
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
