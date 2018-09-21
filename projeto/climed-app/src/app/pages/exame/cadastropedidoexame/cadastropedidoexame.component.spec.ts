import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastropedidoexameComponent } from './cadastropedidoexame.component';

describe('CadastropedidoexameComponent', () => {
  let component: CadastropedidoexameComponent;
  let fixture: ComponentFixture<CadastropedidoexameComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastropedidoexameComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastropedidoexameComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
