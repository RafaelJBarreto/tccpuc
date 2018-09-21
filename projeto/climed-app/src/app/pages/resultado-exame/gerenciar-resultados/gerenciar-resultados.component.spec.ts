import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GerenciarResultadosComponent } from './gerenciar-resultados.component';

describe('GerenciarResultadosComponent', () => {
  let component: GerenciarResultadosComponent;
  let fixture: ComponentFixture<GerenciarResultadosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GerenciarResultadosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GerenciarResultadosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
