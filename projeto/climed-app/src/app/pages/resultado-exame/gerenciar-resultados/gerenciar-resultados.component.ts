import { Component, OnInit, ViewContainerRef, OnDestroy} from '@angular/core';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {FormGroup,  FormBuilder,  Validators, FormControl, FormArray} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {ConsultaService} from '../../../services/consultas/consulta.service';
import {Response} from '../../../services/response';
import { Consulta } from '../../../services/consultas/consulta';
import { PedidoExameService } from '../../../services/pedido-exame/pedido-exame.service';
import { ResultadoExameService } from '../../../services/resultado-exame/resultado-exame.service';
import { ExameRetorno } from '../../../services/dominiosUtil/exameRetorno';
import { PedidoExame } from '../../../services/pedido-exame/pedido-exame';
import { Exame } from '../../../services/dominiosUtil/exame';
import { ResultadoExame } from '../../../services/resultado-exame/resultado-exame';
import { ExamesResultado } from '../../../services/dominiosUtil/examesResultado';

@Component({
  selector: 'app-gerenciar-resultados',
  templateUrl: './gerenciar-resultados.component.html',
  styleUrls: ['./gerenciar-resultados.component.css']
})
export class GerenciarResultadosComponent implements OnInit {

  private titulo:string;
  nomePaciente:string;
  nomeMedico:string;
  consulta: Consulta = new Consulta();
  pedidoExame: PedidoExame = new PedidoExame();
  exameRetorno: ExameRetorno = new ExameRetorno();
  resultadoExame: ResultadoExame = new ResultadoExame();
  examesResultado: ExamesResultado = new ExamesResultado();
  exames: Exame[] = new Array();
  listaResultado: ExamesResultado[] = new Array();
  resultadoExameForm: FormGroup;
  resultadoForm: FormGroup;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  items: FormArray;
  qtdInvalido = false;
  isEdicao = false;

  constructor(
    private resultadoExameService: ResultadoExameService,
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
      this.nomeMedico = "";
      this.form();
      this.activatedRoute.params.subscribe(parametro=>{
        if(this.router.url.indexOf('lancarresultado') !== -1){
          this.titulo = "Lançar resultado de exame";
          this.pedidoExameService.getPedidoExame(parametro["idConsulta"]).subscribe(res => {
            this.pedidoExame = res;
            this.consulta = this.pedidoExame.consulta;
            this.nomePaciente = this.pedidoExame.consulta.paciente.nome;
            this.nomeMedico = this.pedidoExame.consulta.profissional.nome;
            for(let i=0; i<this.pedidoExame.listaExames.length;i++){
              if(i != 0)
                this.addExame();
            }

            for(let i=0; i<this.pedidoExame.listaExames.length;i++){
              IT:for(let j=i; j<this.items.controls.length;){
                  this.items.controls[i].get('exame').patchValue(this.pedidoExame.listaExames[i].exame.idExame);
                  break IT;
                }
              };
          });
        }else{
          this.resultadoExameService.getResultadoExame(parametro["idConsulta"]).subscribe(res => {
            this.isEdicao = true;
            this.titulo = "Editar resultado de exame";
            this.resultadoExame = res;
            this.consulta = this.resultadoExame.consulta;
            this.nomePaciente = this.resultadoExame.consulta.paciente.nome;
            this.nomeMedico = this.resultadoExame.consulta.profissional.nome;
            this.listaResultado = this.resultadoExame.listaResultado;
            this.resultadoForm.controls['dataHoraColeta'].setValue(new Date(this.resultadoExame.dataColeta));
            this.resultadoForm.controls['enviarMedico'].setValue(this.resultadoExame.enviadoMedico);
            this.resultadoForm.controls['enviarPaciente'].setValue(this.resultadoExame.enviadoPaciente);
            for(let i=0; i<this.resultadoExame.listaResultado.length;i++){
              if(i != 0)
                this.addExame();
            }

            for(let i=0; i<this.resultadoExame.listaResultado.length;i++){
              IT:for(let j=i; j<this.items.controls.length;){
                  this.items.controls[i].get('exame').patchValue(this.resultadoExame.listaResultado[i].exame.idExame);
                  this.items.controls[i].get('resultado').patchValue(this.resultadoExame.listaResultado[i].resultado);
                  this.items.controls[i].get('unidade').patchValue(this.resultadoExame.listaResultado[i].unidade);
                  this.items.controls[i].get('valoresReferencia').patchValue(this.resultadoExame.listaResultado[i].valorReferencia); 
                  break IT;
                }
              };
          });
        }
      });

      this.resultadoForm = this.formBuilder.group({
        dataHoraColeta: [''],
        enviarMedico: [''],
        enviarPaciente: ['']
    });

    }

    form(){
      this.items = new FormArray([
        this.criarItem(),
      ])
      this.resultadoExameForm = new FormGroup({ 
         items: this.items
      });
    }
  
    criarItem() {
      return this.formBuilder.group({
        exame: ['', Validators.required],
        resultado: ['', Validators.required],
        unidade: [''],
        valoresReferencia: ['']
    });
    }
  
    addExame() {
      this.items = <FormArray>this.resultadoExameForm.get('items');
      this.items.push(this.criarItem());
    }

    getExames(){
      this.pedidoExameService.getExames().subscribe((res) => {
        this.exames = res;
       });
    }

    updateResultado(){
      for(let i=0; i<this.listaResultado.length; i++){
        if(this.listaResultado[i].exame.idExame == this.examesResultado.idExame){
          this.listaResultado[i].resultado = this.examesResultado.resultado;
          this.listaResultado[i].unidade = this.examesResultado.unidade;
          this.listaResultado[i].valorReferencia = this.examesResultado.valorReferencia;
        }
      }
    }

    verificaResultado(idExame:number):boolean{
      let exames = this.listaResultado.filter(e => e.idExame == idExame);
      if(exames.length > 0)
        return true;
      else
        return false;
    }

    add(index: number){
      this.examesResultado = new ExamesResultado();
      this.examesResultado.exame = this.resultadoExameForm.value.items[index].exame;
      this.examesResultado.resultado = this.resultadoExameForm.value.items[index].resultado;
      this.examesResultado.unidade = this.resultadoExameForm.value.items[index].unidade;
      this.examesResultado.valorReferencia = this.resultadoExameForm.value.items[index].valoresReferencia;
      this.examesResultado.idExame = this.resultadoExameForm.value.items[index].exame;

      if(this.isEdicao){
        this.updateResultado();
        this.avisoMensagem = false;
        this.mensagem = "Resultado atualizado com sucesso!"
        setTimeout(() => this.avisoMensagem = true, 1000);
      }else{
        if(!this.verificaResultado( this.examesResultado.idExame)){
          this.listaResultado.push(this.examesResultado);
          this.avisoMensagem = false;
          this.mensagem = "Resultado adicionado com sucesso!"
          setTimeout(() => this.avisoMensagem = true, 1000);
        }else{
          this.avisoMensagem = false;
          this.mensagem = "Resultado já lançado!"
          setTimeout(() => this.avisoMensagem = true, 1000);
        }
      }
   }

   salvar():void {
    if(this.listaResultado.length == 0){
      this.avisoMensagem = false;
      this.mensagem = "Por favor, preencha o(s) resultado(s) do(s) exame(s)!"
      setTimeout(() => this.avisoMensagem = true, 3000);
      return;
    }else{
      this.resultadoExame = new ResultadoExame();
      this.resultadoExame.consulta = this.consulta;
      this.resultadoExame.listaResultado = this.listaResultado;
      this.resultadoExame.dataColeta = this.resultadoForm.controls.dataHoraColeta.value;
      this.resultadoExame.enviadoMedico = this.resultadoForm.controls.enviarMedico.value;
      this.resultadoExame.enviadoPaciente = this.resultadoForm.controls.enviarPaciente.value;

      if(!this.isEdicao){
        this.resultadoExameService.addResultadoExame(this.resultadoExame).subscribe(response => {
          let res:Response = <Response>response;
          this.mensagem = res.mensagem;
          this.avisoMensagem = false;
          setTimeout(() => this.avisoMensagem = true, 2000);
          setTimeout(() => this.router.navigate(['/pedidos']), 3000);
        },(erro) => {                  
            alert(erro);
        });
    }else{
      this.resultadoExameService.atualizarResultadoExame(this.resultadoExame).subscribe(response => {
        let res:Response = <Response>response;
        this.mensagem = res.mensagem;
        this.avisoMensagem = false;
        setTimeout(() => this.avisoMensagem = true, 2000);
        setTimeout(() => this.router.navigate(['/resultados']), 3000);
      },(erro) => {                  
          alert(erro);
      });
    }

    }
  }
}
