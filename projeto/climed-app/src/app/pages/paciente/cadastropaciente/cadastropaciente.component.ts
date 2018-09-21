import { Component, OnInit, OnDestroy } from '@angular/core';
import {FormGroup,  FormBuilder,  Validators} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {Paciente} from '../../../services/pacientes/paciente';
import {Response} from '../../../services/response';
import { PacienteService } from '../../../services/pacientes/paciente.service';

@Component({
  selector: 'app-cadastropaciente',
  templateUrl: './cadastropaciente.component.html',
  styleUrls: ['./cadastropaciente.component.css']
})
export class CadastropacienteComponent implements OnInit {

  private titulo:string;
  private paciente:Paciente = new Paciente();
  pacienteForm: FormGroup;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;

  createForm() {
    this.pacienteForm = this.formBuilder.group({
      nome: ['', Validators.required],
      celular:[''],
      telefone: [''],
      endereco: [''],
      email: ['', [Validators.required, Validators.email]],
      cartaoSus: ['', Validators.required],
      dataNascimento: ['', Validators.required],
      sexo:['', Validators.required]
  });
  }

  constructor(private pacienteService: PacienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute, 
    private formBuilder: FormBuilder){
      this.createForm();
    }

  ngOnDestroy() {
      this.pacienteForm.reset();
  }

    ngOnInit() {

    this.activatedRoute.params.subscribe(parametro=>{
      if(parametro["idPaciente"] == undefined){
        this.titulo = "Novo Paciente";
      }else{
         this.titulo = "Editar Paciente";
         this.pacienteService.getPaciente(Number(parametro["idPaciente"])).subscribe((res) => {
          this.paciente = res;
          this.pacienteEdit();
         });
      }
    });      
  }

  pacienteEdit(){
    this.pacienteForm.setValue({
      nome: this.paciente.nome, 
      email: this.paciente.email,
      celular: this.paciente.celular,
      telefone: this.paciente.telefone,
      endereco: this.paciente.endereco,
      cartaoSus: this.paciente.cartaoSus,
      sexo: this.paciente.sexo,
      dataNascimento: new Date(this.paciente.dataNascimento)
    });
  }

  get f() { return this.pacienteForm.controls; }

  salvar():void {
        this.enviando = true;
        if (this.pacienteForm.invalid) {
            return;
        }else{
          this.paciente.idPaciente = this.paciente.idPaciente;
          this.paciente.nome = this.pacienteForm.controls.nome.value;
          this.paciente.email = this.pacienteForm.controls.email.value;
          this.paciente.celular = this.pacienteForm.controls.celular.value;
          this.paciente.telefone = this.pacienteForm.controls.telefone.value;
          this.paciente.endereco = this.pacienteForm.controls.endereco.value;
          this.paciente.cartaoSus = this.pacienteForm.controls.cartaoSus.value;
          this.paciente.dataNascimento = this.pacienteForm.controls.dataNascimento.value;
          this.paciente.sexo = this.pacienteForm.controls.sexo.value;
        }

    if(this.paciente.idPaciente == undefined){
      this.pacienteService.addPaciente(this.paciente).subscribe(response => {
        let res:Response = <Response>response;
        this.mensagem = res.mensagem;
        this.avisoMensagem = false
        setTimeout(() => this.avisoMensagem = true, 3000);
        this.paciente = new Paciente();
        this.pacienteForm.reset();
       },(erro) => {                  
          alert(erro);
       });

    }else{
      this.pacienteService.atualizarPaciente(this.paciente).subscribe(response => {
      let res:Response = <Response>response;
      this.avisoMensagem = false
      this.pacienteForm.reset();
      setTimeout(() => this.avisoMensagem = true, 3000);
      this.mensagem = res.mensagem;
      setTimeout(() => this.router.navigate(['/pacientes']), 3000);
     },(erro) => {                                  
        alert(erro);
     });
    }

  }

}
