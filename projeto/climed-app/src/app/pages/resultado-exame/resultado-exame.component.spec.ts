import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ResultadoExameComponent } from './resultado-exame.component';

describe('ResultadoExameComponent', () => {
  let component: ResultadoExameComponent;
  let fixture: ComponentFixture<ResultadoExameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ResultadoExameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ResultadoExameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
