import { Component, OnInit, ViewContainerRef } from '@angular/core';
import { Router } from '@angular/router';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { ConsultaService } from '../../services/consultas/consulta.service';
import { ConsultaMedicoService } from '../../services/consultas-medico/consulta-medico.service';
import { PedidoExameService } from '../../services/pedido-exame/pedido-exame.service';
import { ResultadoExameService } from '../../services/resultado-exame/resultado-exame.service';
import { Profissional } from '../../services/profissionais/profissional';
import { SimpleModalComponent, ModalDialogService } from 'ngx-modal-dialog';
import { Response } from '../../services/response';
import { ResultadoExame } from '../../services/resultado-exame/resultado-exame';
import { LocalStorage } from '@ngx-pwa/local-storage';

@Component({
  selector: 'app-consulta-medica',
  templateUrl: './consulta-medica.component.html',
  styleUrls: ['./consulta-medica.component.css']
})
export class ConsultaMedicaComponent implements OnInit {
  
  protected  consultasMedica: any[];
  protected  consultas: any[];
  protected  titulo:string;
  protected  pedidos: any[];
  protected  pacientes: any[];
  public procurarPorNome: string;
  public procurarConsultaPorNome: string;
  public procurarPedidoPorNome: string;
  protected  resultados: ResultadoExame[] = new Array();
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  qtdConsulta: number;
  qtdConsultaMedica: number;
  qtdResultadoExame: number;
  qtdPedido: number;  
  qtdPaciente: number;
  isDisabled: boolean;
  isDisabledExame: boolean;
  
  constructor(
    private storage: LocalStorage,
    private resultadoExameService: ResultadoExameService,
    private pedidoExameService: PedidoExameService,
    private consultaMedicoSevice: ConsultaMedicoService,
    private consultaService: ConsultaService,
    private router: Router, private spinnerService: Ng4LoadingSpinnerService,
    private modalService: ModalDialogService,
    private viewRef: ViewContainerRef
  ) { }

  ngOnInit() {
    this.titulo = "Consultas e Exames";
    this.getConsultasProfissional();
    this.isDisabled = false;
    this.isDisabledExame = false;
  }

  getPacientesPorProfissional(){
    this.spinnerService.show();
    this.storage.getItem<Profissional>('usuario').subscribe((usuario) => {
      if(usuario.idProfissional != null){
        this.consultaService.getPacientesPorProfissional(usuario.idProfissional).subscribe(res =>{
          this.pacientes = res;
          this.qtdPaciente = this.pacientes.length;
        });
      }
    });
    this.spinnerService.hide();
  }

  getPedidosPorProfissional(){
    this.spinnerService.show();
    this.storage.getItem<Profissional>('usuario').subscribe((usuario) => {
      if(usuario.idProfissional != null){
        this.pedidoExameService.getPedidosExamesPorProfissional(usuario.idProfissional).subscribe(res => {
          this.pedidos = res;
          this.qtdPedido = this.pedidos.length;
        });
      }
    });
    this.spinnerService.hide();
  }

  getConsultasMedicasPorProfissional(){
    this.spinnerService.show();
    this.storage.getItem<Profissional>('usuario').subscribe((usuario) => {
      if(usuario.idProfissional != null){
        this.consultaMedicoSevice.getConsultasMedicasPorProfissional(usuario.idProfissional).subscribe(res => {
          this.consultasMedica = res;
          this.qtdConsultaMedica = this.consultasMedica.length;
        });
      }
    });
    this.spinnerService.hide();
  }

  getConsultasProfissional(){
    this.spinnerService.show();
    this.storage.getItem<Profissional>('usuario').subscribe((usuario) => {
      if(usuario.idProfissional != null){
        this.consultaService.getConsultasPorProfissional(usuario.idProfissional).subscribe(res => {
          this.consultas = res;
          this.qtdConsulta = this.consultas.length;
        });
      }
    });
    this.spinnerService.hide();
  }

  getResultadosExames(){
    this.spinnerService.show();
    this.storage.getItem<Profissional>('usuario').subscribe((usuario) => {
      if(usuario.idProfissional != null){
        this.resultadoExameService.getResultadoExamesPorProfissional(usuario.idProfissional).subscribe(res => {
          this.resultados = res;
          this.qtdResultadoExame = this.resultados.length;
        });
      }
    });
    this.spinnerService.hide();
  }

  consultar(idConsulta: number){
    this.router.navigate(['/consultar', idConsulta]);
  }

  editar(idConsulta: number){
    this.router.navigate(['/consultamedica', idConsulta]);
  }

  pedirexame(idConsulta: number){
    this.router.navigate(['/cadastropedido', idConsulta]);
  }

  editarpedidoexame(idConsulta: number){
    this.router.navigate(['/editarpedido', idConsulta]);
  }

  prontuario(idPaciente: number){
    this.router.navigate(['/prontuario', idPaciente]);
  }

  removerpedidoexame(idConsulta:number, index:number){
    this.isDisabledExame = true;
    this.modalService.openDialog(this.viewRef, {
        title: 'Deletar',
        childComponent: SimpleModalComponent,
          data:  {
          text: 'Tem certeza que deseja apagar esse pedido de exame?'
          },
          placeOnTop: true,
          settings: {
            closeButtonClass: 'close theme-icon-close'
          },
          actionButtons: [
            {
              text: 'Sim',
              buttonClass: 'btn btn-danger',
              onAction: () => new Promise((resolve: any, reject: any) => {
                this.excluir(idConsulta, index);
                resolve();
              })
            },
            {
              text: 'Não',
              buttonClass: 'btn btn-default',
              onAction: () => new Promise((resolve: any) => {
                  this.isDisabledExame = false;
                  resolve();
              })
            }
      
          ]
        });
}

  excluir(idConsulta:number, index:number):void {
      this.pedidoExameService.excluirPedido(idConsulta).subscribe(response => {
            let res:Response = <Response>response;
            if(res.codigoRetorno == 1){
              this.pedidos.splice(index,1);
              this.qtdPedido = this.pedidos.length;
              this.isDisabledExame = false;
            }
            this.mensagem = res.mensagem;
            this.avisoMensagem = false
            setTimeout(() => this.avisoMensagem = true, 3000);
        },
        (erro) => {                    
             alert(erro);
        });        
  }

  removerconsulta(idConsulta:number, index:number){
    this.isDisabled = true;
    this.modalService.openDialog(this.viewRef, {
        title: 'Deletar',
        childComponent: SimpleModalComponent,
          data:  {
          text: 'Tem certeza que deseja apagar essa consulta?'
          },
          placeOnTop: true,
          settings: {
            closeButtonClass: 'close theme-icon-close'
          },
          actionButtons: [
            {
              text: 'Sim',
              buttonClass: 'btn btn-danger',
              onAction: () => new Promise((resolve: any, reject: any) => {
                this.excluirConsulta(idConsulta, index);
                resolve();
              })
            },
            {
              text: 'Não',
              buttonClass: 'btn btn-default',
              onAction: () => new Promise((resolve: any) => {
                  this.isDisabled = false;
                  resolve();
              })
            }
      
          ]
        });
}

  excluirConsulta(idConsulta:number, index:number):void {
      this.consultaMedicoSevice.excluirConsulta(idConsulta).subscribe(response => {
            let res:Response = <Response>response;
            if(res.codigoRetorno == 1){
              this.consultasMedica.splice(index,1);
              this.qtdConsultaMedica = this.consultasMedica.length;
              this.isDisabled = false;
            }
            this.mensagem = res.mensagem;
            this.avisoMensagem = false
            setTimeout(() => this.avisoMensagem = true, 3000);
        },
        (erro) => {                    
             alert(erro);
        });        
  }

}
