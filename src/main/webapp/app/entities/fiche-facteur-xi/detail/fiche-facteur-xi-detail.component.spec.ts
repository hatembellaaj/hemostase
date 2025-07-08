import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { FicheFacteurXIDetailComponent } from './fiche-facteur-xi-detail.component';

describe('FicheFacteurXI Management Detail Component', () => {
  let comp: FicheFacteurXIDetailComponent;
  let fixture: ComponentFixture<FicheFacteurXIDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FicheFacteurXIDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./fiche-facteur-xi-detail.component').then(m => m.FicheFacteurXIDetailComponent),
              resolve: { ficheFacteurXI: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FicheFacteurXIDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FicheFacteurXIDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load ficheFacteurXI on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FicheFacteurXIDetailComponent);

      // THEN
      expect(instance.ficheFacteurXI()).toEqual(expect.objectContaining({ id: 123 }));
    });
  });

  describe('PreviousState', () => {
    it('Should navigate to previous state', () => {
      jest.spyOn(window.history, 'back');
      comp.previousState();
      expect(window.history.back).toHaveBeenCalled();
    });
  });
});
