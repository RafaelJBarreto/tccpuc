import { Component, OnInit, OnDestroy } from '@angular/core';
import {FormGroup,  FormBuilder,  Validators} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Medicamento} from '../../../services/medicamentos/medicamento'
import {Response} from '../../../services/response';
import {MedicamentoService} from '../../../services/medicamentos/medicamento.service';


@Component({
  selector: 'app-cadastromedicamento',
  templateUrl: './cadastromedicamento.component.html',
  styleUrls: ['./cadastromedicamento.component.css']
})
export class CadastromedicamentoComponent implements OnInit {

  private titulo:string;
  private medicamento:Medicamento = new Medicamento();
  medicamentoForm: FormGroup;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;

  createForm() {
    this.medicamentoForm = this.formBuilder.group({
      nomeG: ['', Validators.required],
      nomeC: ['', Validators.required],
      fabricante: ['']
  });
  }

  constructor(private medicamentoService: MedicamentoService,
    private router: Router,
    private activatedRoute: ActivatedRoute, 
    private formBuilder: FormBuilder){
      this.createForm();
    }

    ngOnInit() {

      this.activatedRoute.params.subscribe(parametro=>{
        if(parametro["idMedicamento"] == undefined){
          this.titulo = "Novo Medicamento";
        }else{
           this.titulo = "Editar Medicamento";
           this.medicamentoService.getMedicamento(Number(parametro["idMedicamento"])).subscribe((res) => {
            this.medicamento = res;
            this.medicamentoEdit();
           });
        }
      });      
    }

    medicamentoEdit(){
      this.medicamentoForm.setValue({
        nomeG: this.medicamento.nomeGenerico, 
        nomeC: this.medicamento.nomeComercial,
        fabricante: this.medicamento.fabricante
      });
    }

    get f() { return this.medicamentoForm.controls; }

    salvar():void {
          this.enviando = true;
          if (this.medicamentoForm.invalid) {
              return;
          }else{
            this.medicamento.idMedicamento = this.medicamento.idMedicamento;
            this.medicamento.nomeComercial = this.medicamentoForm.controls.nomeC.value;
            this.medicamento.nomeGenerico = this.medicamentoForm.controls.nomeG.value;
            this.medicamento.fabricante = this.medicamentoForm.controls.fabricante.value;
          }
  
      if(this.medicamento.idMedicamento == undefined){
        this.medicamentoService.addMedicamento(this.medicamento).subscribe(response => {
          let res:Response = <Response>response;
          this.mensagem = res.mensagem;
          this.avisoMensagem = false
          setTimeout(() => this.avisoMensagem = true, 3000);
          this.medicamento = new Medicamento();
          this.medicamentoForm.reset();
         },(erro) => {                  
            alert(erro);
         });
  
      }else{
        this.medicamentoService.atualizarMedicamento(this.medicamento).subscribe(response => {
        let res:Response = <Response>response;
        this.avisoMensagem = false
        this.medicamentoForm.reset();
        setTimeout(() => this.avisoMensagem = true, 3000);
        this.mensagem = res.mensagem;
        setTimeout(() => this.router.navigate(['/medicamentos']), 3000);
       },(erro) => {                                  
          alert(erro);
       });
      }
  
    }

}
