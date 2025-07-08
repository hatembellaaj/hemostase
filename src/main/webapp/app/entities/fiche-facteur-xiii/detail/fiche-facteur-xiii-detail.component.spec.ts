import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { FicheFacteurXIIIDetailComponent } from './fiche-facteur-xiii-detail.component';

describe('FicheFacteurXIII Management Detail Component', () => {
  let comp: FicheFacteurXIIIDetailComponent;
  let fixture: ComponentFixture<FicheFacteurXIIIDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FicheFacteurXIIIDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./fiche-facteur-xiii-detail.component').then(m => m.FicheFacteurXIIIDetailComponent),
              resolve: { ficheFacteurXIII: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FicheFacteurXIIIDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FicheFacteurXIIIDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load ficheFacteurXIII on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FicheFacteurXIIIDetailComponent);

      // THEN
      expect(instance.ficheFacteurXIII()).toEqual(expect.objectContaining({ id: 123 }));
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
