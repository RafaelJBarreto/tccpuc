import { Component, OnInit, ViewContainerRef} from '@angular/core';
import {Router} from '@angular/router';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {SimpleModalComponent, ModalDialogService} from 'ngx-modal-dialog';
import {Response} from '../../services/response';
import {PacienteService} from '../../services/pacientes/paciente.service';

@Component({
  selector: 'app-paciente',
  templateUrl: './paciente.component.html',
  styleUrls: ['./paciente.component.css']
})
export class PacienteComponent implements OnInit {

  protected  pacientes: any[];
  protected  titulo:string;
  public procurarPorNome: string;
  pager: any = {};
  pagedItems: any[];
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  qtdPaciente: number;

  constructor(
    private pacienteService: PacienteService,
              private router: Router, private spinnerService: Ng4LoadingSpinnerService,
              private modalService: ModalDialogService,
              private viewRef: ViewContainerRef
  ) { }

  ngOnInit() {
    this.titulo = "Pacientes";
    this.spinnerService.show();
    this.pacienteService.getPacientes().subscribe(res => {
      this.pacientes = res;
      this.qtdPaciente = this.pacientes.length;
      this.spinnerService.hide();
    });
  }
  
  editar(idPaciente:number):void{
    this.router.navigate(['/cadastro_paciente', idPaciente]);
  }

  deletePaciente(idPaciente:number, index:number){
    this.modalService.openDialog(this.viewRef, {
        title: 'Deletar',
        childComponent: SimpleModalComponent,
          data:  {
          text: 'Tem certeza que deseja apagar esse registro?'
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
                this.excluir(idPaciente, index);
                resolve();
              })
            },
            {
              text: 'Não',
              buttonClass: 'btn btn-default',
              onAction: () => new Promise((resolve: any) => {
                  resolve();
              })
            }
      
          ]
        });
}

  excluir(idPaciente:number, index:number):void {
      this.pacienteService.excluirPaciente(idPaciente).subscribe(response => {
            let res:Response = <Response>response;
            if(res.codigoRetorno == 1){
              this.pacientes.splice(index,1);
              this.qtdPaciente = this.pacientes.length;
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
