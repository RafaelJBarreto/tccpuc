import { Component, OnInit} from '@angular/core';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { FormGroup,  FormBuilder,  Validators, FormControl, FormArray } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ConsultaService } from '../../../services/consultas/consulta.service';
import { Response } from '../../../services/response';
import { Consulta } from '../../../services/consultas/consulta';
import { PedidoExameService } from '../../../services/pedido-exame/pedido-exame.service';
import { ExameRetorno } from '../../../services/dominiosUtil/exameRetorno';
import { PedidoExame } from '../../../services/pedido-exame/pedido-exame';
import { Exame } from '../../../services/dominiosUtil/exame';


@Component({
  selector: 'app-cadastropedidoexame',
  templateUrl: './cadastropedidoexame.component.html',
  styleUrls: ['./cadastropedidoexame.component.css']
})

export class CadastropedidoexameComponent implements OnInit {
  nomePaciente:string;
  consulta: Consulta = new Consulta();
  pedidoExame: PedidoExame = new PedidoExame();
  exameRetorno: ExameRetorno = new ExameRetorno();
  exames: Exame[] = new Array();
  listaExames:  ExameRetorno[] = new Array();;
  private titulo:string;
  pedidoExameForm: FormGroup;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  items: FormArray;
  qtdInvalido = false;
  isEdicao = false;
  isEdicaoItem = false;
  
  constructor(
    private pedidoExameService: PedidoExameService,
    private consultaService: ConsultaService,
    private spinnerService: Ng4LoadingSpinnerService,
    private router: Router,
    private activatedRoute: ActivatedRoute, 
    private formBuilder: FormBuilder){
      this.getExames();
    }
  
    ngOnInit() {
      this.nomePaciente = "";
      this.activatedRoute.params.subscribe(parametro=>{
        if(this.router.url.indexOf('editarpedido') !== -1){
          this.titulo = "Editar pedido de exame";
          this.isEdicao = true;
          this.isEdicaoItem = true;
          this.form();
          this.pedidoExameService.getPedidoExame(parametro["idConsulta"]).subscribe(res => {
            this.pedidoExame = res;
            this.consulta = this.pedidoExame.consulta;
            this.nomePaciente = this.pedidoExame.consulta.paciente.nome;
            this.listaExames = this.pedidoExame.listaExames;
            for(let i=0; i<this.pedidoExame.listaExames.length;i++){
              if(i != 0){
                this.items = <FormArray>this.pedidoExameForm.get('items');
                this.items.push(this.criarItem());
              }
            }

            for(let i=0; i<this.pedidoExame.listaExames.length;i++){
              IT:for(let j=i; j<this.items.controls.length;){
                  this.items.controls[i].get('exame').patchValue(this.pedidoExame.listaExames[i].exame.idExame);
                  break IT;
                }
              };
          });
      }else
          if(parametro["idConsulta"] != undefined){
            this.isEdicao = false;
            this.isEdicaoItem = false;
            this.form();
            this.titulo = "Novo pedido de exame";
            this.consultaService.getConsulta(parametro["idConsulta"]).subscribe(res => {
              this.consulta = res;
              this.nomePaciente = this.consulta.paciente.nome;
            });
          }
      });
    }

    form(){
      this.items = new FormArray([
        this.criarItem(),
      ])
      this.pedidoExameForm = new FormGroup({ 
         items: this.items
      });
    }
  
    criarItem() {
      return this.formBuilder.group({
        exame: [{ value:'', disabled: this.isEdicaoItem? true:false }, Validators.required],
    });
    }
  
    addExame() {
      this.isEdicaoItem = false;
      this.items = <FormArray>this.pedidoExameForm.get('items');
      this.items.push(this.criarItem());
    }


    getExames(){
    this.pedidoExameService.getExames().subscribe((res) => {
      this.exames = res;
     });
    }

  excluirExame(index: number){
    this.items.removeAt(index);
    this.listaExames.splice(index,1);
    this.avisoMensagem = false;
    this.mensagem = "Exame excluido com sucesso!"
    setTimeout(() => this.avisoMensagem = true, 1000);
  }

  verificaExame(idExame:number):boolean{
    let exames = this.listaExames.filter(e => e.exame.idExame == idExame);
    if(exames.length > 0)
      return true;
    else
      return false;
  }

  verificaExameEdicao(index:number){
    if(this.exameRetorno.idExame != undefined){
      if(this.verificaExame(this.exameRetorno.idExame)){
          this.avisoMensagem = false;
          this.mensagem = "Esse exame já está adicionado ao pedido!"
          setTimeout(() => this.avisoMensagem = true, 1000);
      }else{
          this.listaExames.push(this.exameRetorno);
          this.avisoMensagem = false;
          this.mensagem = "Exame adicionado com sucesso!"
          setTimeout(() => this.avisoMensagem = true, 1000);
      }
    }
  }

  add(index: number){

    if(this.pedidoExameForm.value.items[index].exame != undefined){
        this.exameRetorno = new ExameRetorno();
        this.exameRetorno.exame = this.pedidoExameForm.value.items[index].exame;
        this.exameRetorno.idExame = this.pedidoExameForm.value.items[index].exame;

      if(!this.isEdicao){
        if(this.verificaExame(this.exameRetorno.idExame)){
          this.avisoMensagem = false;
          this.mensagem = "Esse exame já está adicionado ao pedido!"
          setTimeout(() => this.avisoMensagem = true, 1000);
        }else{
          this.listaExames.push(this.exameRetorno);
          this.avisoMensagem = false;
          this.mensagem = "Exame adicionado com sucesso!"
          setTimeout(() => this.avisoMensagem = true, 1000);
        }
      }else{
        this.verificaExameEdicao(index);
      }
    }
  }

  salvar():void {
    if(this.listaExames.length == 0){
      this.avisoMensagem = false;
      this.mensagem = "Por favor, adicione um exame ao pedido!"
      setTimeout(() => this.avisoMensagem = true, 3000);
      return;
    }else{
      this.pedidoExame.consulta = this.consulta;
      this.pedidoExame.listaExames = this.listaExames;

      if(!this.isEdicao){
        this.pedidoExameService.addPedidoExame(this.pedidoExame).subscribe(response => {
          let res:Response = <Response>response;
          this.mensagem = res.mensagem;
          this.avisoMensagem = false;
          setTimeout(() => this.avisoMensagem = true, 2000);
          setTimeout(() => this.router.navigate(['/consultasmedicas']), 3000);
        },(erro) => {                  
            alert(erro);
        });
    }else{
      this.pedidoExameService.atualizarPedidoExame(this.pedidoExame).subscribe(response => {
        let res:Response = <Response>response;
        this.mensagem = res.mensagem;
        this.avisoMensagem = false;
        setTimeout(() => this.avisoMensagem = true, 2000);
        setTimeout(() => this.router.navigate(['/consultasmedicas']), 3000);
      },(erro) => {                  
          alert(erro);
      });
    }

    }
  }

}
