import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng4LoadingSpinnerModule } from 'ng4-loading-spinner';
import { HttpModule } from '@angular/http';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { HomeComponent } from './pages/home/home.component';
import { ConfigService} from './services/config.service';
import { ModalDialogModule } from 'ngx-modal-dialog';
import { Filter } from '../app/filter/filter';
import { NgxPaginationModule } from 'ngx-pagination';
import { OwlDateTimeModule, OwlNativeDateTimeModule } from 'ng-pick-datetime';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ProfissionalService } from './services/profissionais/profissional.service';
import { ProfissionalComponent } from './pages/profissional/profissional.component';
import { CadastroComponent } from './pages/profissional/cadastro/cadastro.component';
import { PacienteService} from './services/pacientes/paciente.service';
import { PacienteComponent} from './pages/paciente/paciente.component';
import { CadastropacienteComponent } from './pages/paciente/cadastropaciente/cadastropaciente.component';
import { MedicamentoComponent } from './pages/medicamento/medicamento.component';
import { MedicamentoService } from './services/medicamentos/medicamento.service';
import { CadastromedicamentoComponent } from './pages/medicamento/cadastromedicamento/cadastromedicamento.component';
import { ConsultaComponent } from './pages/consulta/consulta.component';
import { ConsultaService } from './services/consultas/consulta.service';
import { CadastroconsultaComponent } from './pages/consulta/cadastroconsulta/cadastroconsulta.component';
import { LoginComponent } from './pages/login/login.component';
import { ConsultaMedicaComponent } from './pages/consulta-medica/consulta-medica.component';
import { CadastraConsultaMedicaComponent } from './pages/consulta-medica/cadastra-consulta-medica/cadastra-consulta-medica.component'
import { ConsultaMedicoService } from './services/consultas-medico/consulta-medico.service'
import { MatListModule } from '@angular/material/list';
import { MatExpansionModule } from '@angular/material/expansion';
import { ExameComponent } from './pages/exame/exame.component';
import { PedidoExameService } from './services/pedido-exame/pedido-exame.service';
import { CadastropedidoexameComponent } from './pages/exame/cadastropedidoexame/cadastropedidoexame.component';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import {MatSelectModule} from '@angular/material/select';
import { ResultadoExameComponent } from './pages/resultado-exame/resultado-exame.component';
import { GerenciarResultadosComponent } from './pages/resultado-exame/gerenciar-resultados/gerenciar-resultados.component'
import { ResultadoExameService } from './services/resultado-exame/resultado-exame.service';
import { ProntuarioComponent } from './pages/prontuario/prontuario.component';
import { ProntuarioService } from './services/prontuario/prontuario.service';
import { ChartModule } from 'angular-highcharts';
import { EstatisticaComponent } from './pages/estatistica/estatistica.component';
import { EstatisticaService } from './services/estatistica/estatistica.service';
import { AlterarsenhaComponent } from './pages/alterarsenha/alterarsenha.component';
import { NgxMatSelectSearchModule } from 'ngx-mat-select-search';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProfissionalComponent,
    CadastroComponent,
    Filter,
    PacienteComponent,
    CadastropacienteComponent,
    MedicamentoComponent,
    CadastromedicamentoComponent,
    ConsultaComponent,
    CadastroconsultaComponent,
    LoginComponent,
    ConsultaMedicaComponent,
    CadastraConsultaMedicaComponent,
    ExameComponent,
    CadastropedidoexameComponent,
    ResultadoExameComponent,
    GerenciarResultadosComponent,
    ProntuarioComponent,
    EstatisticaComponent,
    AlterarsenhaComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule,
    NgxPaginationModule,
    ReactiveFormsModule,
    NgbModule.forRoot(),
    Ng4LoadingSpinnerModule.forRoot(),
    ModalDialogModule.forRoot(),
    OwlDateTimeModule, 
    OwlNativeDateTimeModule,
    BrowserAnimationsModule,
    MatListModule,
    MatExpansionModule,
    MatAutocompleteModule,
    MatFormFieldModule,
    MatInputModule,
    ChartModule,
    MatSelectModule,
    NgxMatSelectSearchModule
  ],
  providers: [ConfigService, ProfissionalService, PacienteService, MedicamentoService, ConsultaService, ConsultaMedicoService, 
              PedidoExameService, ResultadoExameService, ProntuarioService, EstatisticaService],
  exports: [Filter],

  bootstrap: [AppComponent]
})
export class AppModule { }
