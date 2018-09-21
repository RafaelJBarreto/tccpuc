import { Component, OnInit, ViewContainerRef} from '@angular/core';
import {Router} from '@angular/router';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {SimpleModalComponent, ModalDialogService} from 'ngx-modal-dialog';
import {Response} from '../../services/response';
import {MedicamentoService} from '../../services/medicamentos/medicamento.service';

@Component({
  selector: 'app-medicamento',
  templateUrl: './medicamento.component.html',
  styleUrls: ['./medicamento.component.css']
})
export class MedicamentoComponent implements OnInit {

  protected  medicamentos: any[];
  protected  titulo:string;
  public procurarPorNome: string;
  pager: any = {};
  pagedItems: any[];
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  qtdMedicamento: number;

  constructor(
    private medicamentoService: MedicamentoService,
              private router: Router, private spinnerService: Ng4LoadingSpinnerService,
              private modalService: ModalDialogService,
              private viewRef: ViewContainerRef
  ) { }

  ngOnInit() {
    this.titulo = "Medicamentos";
    this.spinnerService.show();
    this.medicamentoService.getMedicamentos().subscribe(res => {
      this.medicamentos = res;
      this.qtdMedicamento = this.medicamentos.length;
      this.spinnerService.hide();
    });
  }

  editar(idMedicamento:number):void{
    this.router.navigate(['/cadastro_medicamento', idMedicamento]);
  }

  deleteMedicamento(idMedicamento:number, index:number){
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
                this.excluir(idMedicamento, index);
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

  excluir(idMedicamento:number, index:number):void {
      this.medicamentoService.excluirMedicamento(idMedicamento).subscribe(response => {
            let res:Response = <Response>response;
            if(res.codigoRetorno == 1){
              this.medicamentos.splice(index,1);
              this.qtdMedicamento = this.medicamentos.length;
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
