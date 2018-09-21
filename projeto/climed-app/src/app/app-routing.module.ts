import { NgModule, OnInit } from '@angular/core';
import { RouterModule, Routes, Router } from '@angular/router'
import { Location } from '@angular/common';
import $ from "jquery";
import { HomeComponent } from './pages/home/home.component'
import { ProfissionalComponent } from './pages/profissional/profissional.component';
import { CadastroComponent } from './pages/profissional/cadastro/cadastro.component';
import { PacienteComponent } from './pages/paciente/paciente.component'
import { CadastropacienteComponent } from './pages/paciente/cadastropaciente/cadastropaciente.component';
import { MedicamentoComponent } from './pages/medicamento/medicamento.component';
import { CadastromedicamentoComponent } from './pages/medicamento/cadastromedicamento/cadastromedicamento.component';
import { ConsultaComponent } from './pages/consulta/consulta.component';
import { CadastroconsultaComponent } from './pages/consulta/cadastroconsulta/cadastroconsulta.component';
import { LoginComponent } from './pages/login/login.component';
import { ConsultaMedicaComponent } from './pages/consulta-medica/consulta-medica.component';
import { CadastraConsultaMedicaComponent } from './pages/consulta-medica/cadastra-consulta-medica/cadastra-consulta-medica.component';
import { ExameComponent } from './pages/exame/exame.component';
import { CadastropedidoexameComponent } from './pages/exame/cadastropedidoexame/cadastropedidoexame.component';
import { ResultadoExameComponent } from './pages/resultado-exame/resultado-exame.component';
import { GerenciarResultadosComponent } from './pages/resultado-exame/gerenciar-resultados/gerenciar-resultados.component';
import { ProntuarioComponent } from './pages/prontuario/prontuario.component';
import { EstatisticaComponent } from './pages/estatistica/estatistica.component';
import { AlterarsenhaComponent } from './pages/alterarsenha/alterarsenha.component';

const routes : Routes = [
  {path: 'home', component: HomeComponent},
  {path: 'profissionais', component: ProfissionalComponent},
  {path: 'cadastro_profissional', component: CadastroComponent},
  {path: 'cadastro_profissional/:idProfissional', component: CadastroComponent},
  {path: 'pacientes', component: PacienteComponent},
  {path: 'cadastro_paciente', component: CadastropacienteComponent},
  {path: 'cadastro_paciente/:idPaciente', component: CadastropacienteComponent},
  {path: 'medicamentos', component: MedicamentoComponent},
  {path: 'cadastro_medicamento', component: CadastromedicamentoComponent},
  {path: 'cadastro_medicamento/:idMedicamento', component: CadastromedicamentoComponent},
  {path: 'consultas', component: ConsultaComponent},
  {path: 'cadastro_consulta', component: CadastroconsultaComponent},
  {path: 'cadastro_consulta/:idConsulta', component: CadastroconsultaComponent},
  {path: 'consultasmedicas', component: ConsultaMedicaComponent},
  {path: 'consultar/:idConsulta', component: CadastraConsultaMedicaComponent},
  {path: 'consultamedica/:idConsulta', component: CadastraConsultaMedicaComponent},
  {path: 'pedidos', component: ExameComponent},
  {path: 'cadastropedido/:idConsulta', component: CadastropedidoexameComponent},
  {path: 'editarpedido/:idConsulta', component: CadastropedidoexameComponent},
  {path: 'resultados', component: ResultadoExameComponent},
  {path: 'lancarresultado/:idConsulta', component: GerenciarResultadosComponent},
  {path: 'editarresultado/:idConsulta', component: GerenciarResultadosComponent},
  {path: 'prontuario/:idPaciente', component: ProntuarioComponent},
  {path: 'estatisticas', component: EstatisticaComponent},
  {path: 'alterarsenha', component: AlterarsenhaComponent},
  {path: 'login', component: LoginComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full' },
  {path: '**', component: HomeComponent }
]

@NgModule({
 exports:[
   RouterModule
 ],
 imports: [
   RouterModule.forRoot(routes)
 ]
})
export class AppRoutingModule implements OnInit{ 
  route: string;

  constructor(location: Location, router: Router) {
    router.events.subscribe((val) => {
      if(location.path() != ''){
        this.route = location.path();
        if(this.route == '/login'){
          $( "#principal" ).removeClass( "mostrar" ).addClass( "esconde" );
        }else{
          $( "#principal" ).removeClass( "esconde" ).addClass( "mostrar" );
        }
      }
    });
  }
  ngOnInit() {
  }
}
