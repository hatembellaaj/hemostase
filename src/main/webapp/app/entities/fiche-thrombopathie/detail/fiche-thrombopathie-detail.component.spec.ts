import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { RouterTestingHarness } from '@angular/router/testing';
import { of } from 'rxjs';

import { FicheThrombopathieDetailComponent } from './fiche-thrombopathie-detail.component';

describe('FicheThrombopathie Management Detail Component', () => {
  let comp: FicheThrombopathieDetailComponent;
  let fixture: ComponentFixture<FicheThrombopathieDetailComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FicheThrombopathieDetailComponent],
      providers: [
        provideRouter(
          [
            {
              path: '**',
              loadComponent: () => import('./fiche-thrombopathie-detail.component').then(m => m.FicheThrombopathieDetailComponent),
              resolve: { ficheThrombopathie: () => of({ id: 123 }) },
            },
          ],
          withComponentInputBinding(),
        ),
      ],
    })
      .overrideTemplate(FicheThrombopathieDetailComponent, '')
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FicheThrombopathieDetailComponent);
    comp = fixture.componentInstance;
  });

  describe('OnInit', () => {
    it('Should load ficheThrombopathie on init', async () => {
      const harness = await RouterTestingHarness.create();
      const instance = await harness.navigateByUrl('/', FicheThrombopathieDetailComponent);

      // THEN
      expect(instance.ficheThrombopathie()).toEqual(expect.objectContaining({ id: 123 }));
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
