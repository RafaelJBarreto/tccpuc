import { Component, OnInit, ViewContainerRef} from '@angular/core';
import {Router} from '@angular/router';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {SimpleModalComponent, ModalDialogService} from 'ngx-modal-dialog';
import {Response} from '../../services/response';
import {ConsultaService} from '../../services/consultas/consulta.service';

@Component({
  selector: 'app-consulta',
  templateUrl: './consulta.component.html',
  styleUrls: ['./consulta.component.css']
})
export class ConsultaComponent implements OnInit {

  protected  consultas: any[];
  protected  titulo:string;
  public procurarPorNome: string;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  qtdConsulta: number;

  constructor(
    private consultaService: ConsultaService,
              private router: Router, private spinnerService: Ng4LoadingSpinnerService,
              private modalService: ModalDialogService,
              private viewRef: ViewContainerRef
  ) { }

  ngOnInit() {
    this.titulo = "Consultas";
    this.getConsultas();
  }

  getConsultas(){
    this.spinnerService.show();
    this.consultaService.getConsultas().subscribe(res => {
      this.consultas = res;
      this.qtdConsulta = this.consultas.length;
      this.spinnerService.hide();
    });
  }

  editar(idConsulta:number):void{
    this.router.navigate(['/cadastro_consulta', idConsulta]);
  }

  cancelaConsulta(idConsulta:number){
    this.modalService.openDialog(this.viewRef, {
        title: 'Cancelar',
        childComponent: SimpleModalComponent,
          data:  {
          text: 'Tem certeza que deseja cancelar essa consulta?'
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
                this.cancelar(idConsulta);
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

  cancelar(idConsulta:number):void {
      this.consultaService.cancelarConsulta(idConsulta).subscribe(response => {
            let res:Response = <Response>response;
            this.getConsultas();
            this.mensagem = res.mensagem;
            this.avisoMensagem = false;
            setTimeout(() => this.avisoMensagem = true, 3000);
        },
        (erro) => {                    
             alert(erro);
        });        
  }

}
