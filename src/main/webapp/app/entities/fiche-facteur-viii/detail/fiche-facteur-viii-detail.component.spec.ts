import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { FicheFacteurVIIIDetailComponent } from './fiche-facteur-viii-detail.component';

describe('FicheFacteurVIII Management Detail Component', () => {
  let comp: FicheFacteurVIIIDetailComponent;
  let fixture: ComponentFixture<FicheFacteurVIIIDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FicheFacteurVIIIDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./fiche-facteur-viii-detail.component').then(m => m.FicheFacteurVIIIDetailComponent),
              resolve: { ficheFacteurVIII: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FicheFacteurVIIIDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FicheFacteurVIIIDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load ficheFacteurVIII on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FicheFacteurVIIIDetailComponent);

      // THEN
      expect(instance.ficheFacteurVIII()).toEqual(expect.objectContaining({ id: 123 }));
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
