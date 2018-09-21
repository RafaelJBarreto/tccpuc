import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastropacienteComponent } from './cadastropaciente.component';

describe('CadastropacienteComponent', () => {
  let component: CadastropacienteComponent;
  let fixture: ComponentFixture<CadastropacienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastropacienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastropacienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
