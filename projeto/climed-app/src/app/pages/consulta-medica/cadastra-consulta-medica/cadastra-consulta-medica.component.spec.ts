import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CadastraConsultaMedicaComponent } from './cadastra-consulta-medica.component';

describe('CadastraConsultaMedicaComponent', () => {
  let component: CadastraConsultaMedicaComponent;
  let fixture: ComponentFixture<CadastraConsultaMedicaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CadastraConsultaMedicaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CadastraConsultaMedicaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
