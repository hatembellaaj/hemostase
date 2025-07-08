import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { FicheFacteurXVIIIDetailComponent } from './fiche-facteur-xviii-detail.component';

describe('FicheFacteurXVIII Management Detail Component', () => {
  let comp: FicheFacteurXVIIIDetailComponent;
  let fixture: ComponentFixture<FicheFacteurXVIIIDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FicheFacteurXVIIIDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./fiche-facteur-xviii-detail.component').then(m => m.FicheFacteurXVIIIDetailComponent),
              resolve: { ficheFacteurXVIII: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FicheFacteurXVIIIDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FicheFacteurXVIIIDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load ficheFacteurXVIII on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FicheFacteurXVIIIDetailComponent);

      // THEN
      expect(instance.ficheFacteurXVIII()).toEqual(expect.objectContaining({ id: 123 }));
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
