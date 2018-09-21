import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastromedicamentoComponent } from './cadastromedicamento.component';

describe('CadastromedicamentoComponent', () => {
  let component: CadastromedicamentoComponent;
  let fixture: ComponentFixture<CadastromedicamentoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastromedicamentoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastromedicamentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
