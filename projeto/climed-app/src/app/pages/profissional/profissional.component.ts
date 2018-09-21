import { Component, OnInit, ViewContainerRef} from '@angular/core';
import {Router} from '@angular/router';
import {ProfissionalService} from '../../services/profissionais/profissional.service';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {SimpleModalComponent, ModalDialogService} from 'ngx-modal-dialog';
import {Response} from '../../services/response';

@Component({
  selector: 'app-profissional',
  templateUrl: './profissional.component.html',
  styleUrls: ['./profissional.component.css']
})

export class ProfissionalComponent implements OnInit {
 
  protected  profissionais: any[];
  protected  titulo:string;
  public procurarPorNome: string;
  pager: any = {};
  pagedItems: any[];
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  qtdProfissional: number;

  constructor(private profissionalService: ProfissionalService,
              private router: Router, private spinnerService: Ng4LoadingSpinnerService,
              private modalService: ModalDialogService,
              private viewRef: ViewContainerRef){}

  ngOnInit() {
    this.titulo = "Profissionais";
    this.spinnerService.show();
    this.profissionalService.getProfissionais().subscribe(res => {
      this.profissionais = res;
      this.qtdProfissional = this.profissionais.length;
      this.spinnerService.hide();
    });
  }
  
  editar(idProfissional:number):void{
    this.router.navigate(['/cadastro_profissional', idProfissional]);
  }

  deleteProfissional(idProfissional:number, index:number){
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
                this.excluir(idProfissional, index);
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

  excluir(idProfissional:number, index:number):void {
      this.profissionalService.excluirProfissional(idProfissional).subscribe(response => {
            let res:Response = <Response>response;
            if(res.codigoRetorno == 1){
              this.profissionais.splice(index,1);
              this.qtdProfissional = this.profissionais.length;
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
