import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { FicheVonWillebrandDetailComponent } from './fiche-von-willebrand-detail.component';

describe('FicheVonWillebrand Management Detail Component', () => {
  let comp: FicheVonWillebrandDetailComponent;
  let fixture: ComponentFixture<FicheVonWillebrandDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FicheVonWillebrandDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./fiche-von-willebrand-detail.component').then(m => m.FicheVonWillebrandDetailComponent),
              resolve: { ficheVonWillebrand: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FicheVonWillebrandDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FicheVonWillebrandDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load ficheVonWillebrand on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FicheVonWillebrandDetailComponent);

      // THEN
      expect(instance.ficheVonWillebrand()).toEqual(expect.objectContaining({ id: 123 }));
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
