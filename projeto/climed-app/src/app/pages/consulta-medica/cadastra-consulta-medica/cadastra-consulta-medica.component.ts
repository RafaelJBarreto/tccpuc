import { Component, OnInit, ViewContainerRef, OnDestroy} from '@angular/core';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {FormGroup,  FormBuilder,  Validators, FormControl, FormArray} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {ConsultaService} from '../../../services/consultas/consulta.service';
import {Response} from '../../../services/response';
import { Consulta } from '../../../services/consultas/consulta';
import { MedicamentoService } from '../../../services/medicamentos/medicamento.service';
import { MedicamentoConsulta } from '../../../services/dominiosUtil/medicamentoConsulta';
import { ConsultaMedico } from '../../../services/consultas-medico/consulta-medico';
import { ConsultaMedicoService } from '../../../services/consultas-medico/consulta-medico.service';
import { Medicamento } from '../../../services/medicamentos/medicamento';

@Component({
  selector: 'app-cadastra-consulta-medica',
  templateUrl: './cadastra-consulta-medica.component.html',
  styleUrls: ['./cadastra-consulta-medica.component.css']
})
export class CadastraConsultaMedicaComponent implements OnInit {

  nomePaciente:string;
  consulta: Consulta = new Consulta();
  medicamento: Medicamento = new Medicamento();
  medicamentos: any;
  private medicamentoConsulta:MedicamentoConsulta = new MedicamentoConsulta();
  private consultaMedico: ConsultaMedico = new ConsultaMedico();
  medicamentosConsulta: MedicamentoConsulta[] = new Array();
  private titulo:string;
  medicamentoConsultaForm: FormGroup;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  items: FormArray;
  medicamentoInvalido = false;
  prescricaoInvalido = false;
  qtdInvalido = false;
  consultaForm: FormGroup;
  isEdicao = false;
  isEdicaoItem = false;

  constructor(private consultaMedicoSevice: ConsultaMedicoService,
    private medicamentoService: MedicamentoService,
    private consultaService: ConsultaService,
    private spinnerService: Ng4LoadingSpinnerService,
    private router: Router,
    private activatedRoute: ActivatedRoute, 
    private formBuilder: FormBuilder){
      this.getMedicamentos();
    }
  
    ngOnInit() {
      this.nomePaciente = "";
      this.activatedRoute.params.subscribe(parametro=>{
        if(this.router.url.indexOf('consultamedica') !== -1){
            this.titulo = "Editar consulta médica";
            this.isEdicao = true;
            this.isEdicaoItem = true;
            this.form();
            this.consultaMedicoSevice.getConsultaMedica(parametro["idConsulta"]).subscribe(res => {
              this.consultaMedico = res;
              this.consulta = this.consultaMedico.consulta;
              this.nomePaciente = this.consultaMedico.consulta.paciente.nome;
              this.medicamentosConsulta = this.consultaMedico.listaMedicamentos;
              this.consultaForm.controls['diagnostico'].setValue(this.consultaMedico.diagnosticoConsulta);
              for(let i=0; i<this.consultaMedico.listaMedicamentos.length;i++){
                if(i != 0){
                  this.items = <FormArray>this.medicamentoConsultaForm.get('items');
                  this.items.push(this.criarItem());
                }
                  
              }

              for(let i=0; i<this.consultaMedico.listaMedicamentos.length;i++){
                IT:for(let j=i; j<this.items.controls.length;){
                    this.items.controls[i].get('medicamento').patchValue(this.consultaMedico.listaMedicamentos[i].medicamento.idMedicamento);
                    this.items.controls[i].get('prescricao').patchValue(this.consultaMedico.listaMedicamentos[i].prescricao);
                    this.items.controls[i].get('qtd').patchValue(this.consultaMedico.listaMedicamentos[i].quantidade);
                    break IT;
                  }
                };
            });
        }else{
          if(parametro["idConsulta"] != undefined){
            this.isEdicao = false;
            this.isEdicaoItem = false;
            this.titulo = "Nova consulta médica"; 
            this.form();
            this.consultaService.getConsulta(parametro["idConsulta"]).subscribe(res => {
              this.consulta = res;
              this.nomePaciente = this.consulta.paciente.nome;
            });
          }
        }
      });

      this.consultaForm = this.formBuilder.group({
        diagnostico: ['']
    });

    }

    form(){
      this.items = new FormArray([
        this.criarItem(),
      ])
      this.medicamentoConsultaForm = new FormGroup({ 
         items: this.items
      });
    }
  
    criarItem() {
      return this.formBuilder.group({
        medicamento: [{ value:'', disabled: this.isEdicaoItem? true:false }, Validators.required],
        prescricao: ['', Validators.required],
        qtd: ['', Validators.required]
    });
    }
  
    addMedicamento() {
      this.isEdicaoItem = false;
      this.items = <FormArray>this.medicamentoConsultaForm.get('items');
      this.items.push(this.criarItem());
    }



  getMedicamentos(){
    this.medicamentoService.getMedicamentos().subscribe((res) => {
      this.medicamentos = res;
     });
  }

  excluirMedicamentoConsulta(index: number){
    this.items.removeAt(index);
    this.medicamentosConsulta.splice(index,1);
    this.avisoMensagem = false;
    this.mensagem = "Medicamento excluido com sucesso!"
    setTimeout(() => this.avisoMensagem = true, 1000);
  }

  verificaMedicamento(idMedicamento:number):boolean{
    let medicamentos = this.medicamentosConsulta.filter(m => m.medicamento.idMedicamento == idMedicamento);
    if(medicamentos.length > 0)
      return true;
    else
      return false;
  }
  

  verificaMedicamentoEdicao(index:number){
    if(this.medicamentoConsulta.idMedicamento != undefined){
      if(this.verificaMedicamento(this.medicamentoConsulta.idMedicamento)){
          this.avisoMensagem = false;
          this.mensagem = "Esse medicamento já está adicionado na receita!";
          setTimeout(() => this.avisoMensagem = true, 1000);
      }else{
        this.medicamentosConsulta.push(this.medicamentoConsulta);
        this.avisoMensagem = false;
        this.mensagem = "Medicamento adicionado com sucesso!"
        setTimeout(() => this.avisoMensagem = true, 1000);
      }
    }else{
      for(let i=0; i<this.medicamentosConsulta.length; i++){
        if(i == index){
          this.medicamentosConsulta[i].prescricao = this.medicamentoConsulta.prescricao;
          this.medicamentosConsulta[i].quantidade = this.medicamentoConsulta.quantidade;
          this.avisoMensagem = false;
          this.mensagem = "Medicamento atualizado com sucesso!";
          setTimeout(() => this.avisoMensagem = true, 1000);
        }
      }
    }
  }

  add(index: number){
        this.medicamentoConsulta = new MedicamentoConsulta()
        this.medicamento = new Medicamento();
        this.medicamento.idMedicamento = this.medicamentoConsultaForm.value.items[index].medicamento;
        this.medicamentoConsulta.medicamento = this.medicamento;
        this.medicamentoConsulta.prescricao = this.medicamentoConsultaForm.value.items[index].prescricao;
        this.medicamentoConsulta.quantidade = this.medicamentoConsultaForm.value.items[index].qtd;
        this.medicamentoConsulta.idMedicamento = this.medicamentoConsultaForm.value.items[index].medicamento;
        if(!this.isEdicao){
          if(this.verificaMedicamento(this.medicamentoConsulta.idMedicamento)){
            this.avisoMensagem = false;
            this.mensagem = "Esse medicamento já está adicionado na receita!"
            setTimeout(() => this.avisoMensagem = true, 1000);
          }else{
            this.medicamentosConsulta.push(this.medicamentoConsulta);
            this.avisoMensagem = false;
            this.mensagem = "Medicamento adicionado com sucesso!"
            setTimeout(() => this.avisoMensagem = true, 1000);
          }
      }else{
        this.verificaMedicamentoEdicao(index);
      }
  }

  salvar():void {
    if(this.medicamentosConsulta.length == 0){
      this.avisoMensagem = false;
      this.mensagem = "Por favor, adicione um medicamento à receita!"
      setTimeout(() => this.avisoMensagem = true, 3000);
      return;
    }else{
      this.consultaMedico.consulta = this.consulta;
      this.consultaMedico.listaMedicamentos = this.medicamentosConsulta;
      this.consultaMedico.diagnosticoConsulta = this.consultaForm.controls.diagnostico.value;
      
      if(!this.isEdicao){
        this.consultaMedicoSevice.addConsultaMedica(this.consultaMedico).subscribe(response => {
          let res:Response = <Response>response;
          this.mensagem = res.mensagem;
          this.avisoMensagem = false;
          setTimeout(() => this.avisoMensagem = true, 2000);
          setTimeout(() => this.router.navigate(['/consultasmedicas']), 3000);
        },(erro) => {                  
            alert(erro);
        });
    }else{
      this.consultaMedicoSevice.atualizarConsultaMedica(this.consultaMedico).subscribe(response => {
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
