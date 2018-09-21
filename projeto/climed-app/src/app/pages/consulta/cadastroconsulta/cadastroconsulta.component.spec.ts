import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastroconsultaComponent } from './cadastroconsulta.component';

describe('CadastroconsultaComponent', () => {
  let component: CadastroconsultaComponent;
  let fixture: ComponentFixture<CadastroconsultaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastroconsultaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastroconsultaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
