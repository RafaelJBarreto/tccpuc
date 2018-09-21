import { Component, OnInit, OnDestroy } from '@angular/core';
import {FormGroup,  FormBuilder,  Validators} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {ProfissionalService} from '../../../services/profissionais/profissional.service';
import {Profissional} from '../../../services/profissionais/profissional';
import {Response} from '../../../services/response';

@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})

export class CadastroComponent implements OnInit, OnDestroy {

  private titulo:string;
  private profissional:Profissional = new Profissional();
  profissionalForm: FormGroup;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;

  createForm() {
    this.profissionalForm = this.formBuilder.group({
      nome: ['', Validators.required],
      profissional: ['', Validators.required],
      crm_rms: [''],
      celular:[''],
      telefone: [''],
      endereco: [''],
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(3)]]
  });
  }

  constructor(private profissionalService: ProfissionalService,
              private router: Router,
              private activatedRoute: ActivatedRoute, 
              private formBuilder: FormBuilder){
                this.createForm();
              }

  ngOnDestroy() {
    this.profissionalForm.reset();
  }

  ngOnInit() {

    this.activatedRoute.params.subscribe(parametro=>{
      if(parametro["idProfissional"] == undefined){
        this.titulo = "Novo Profissional";
      }else{
         this.titulo = "Editar Profissional";
         this.profissionalService.getProfissional(Number(parametro["idProfissional"])).subscribe((res) => {
          this.profissional = res;
          this.profissionalEdit();
         });
      }
    });      
  }

  profissionalEdit(){
    this.profissionalForm.setValue({
      nome: this.profissional.nome, 
      email: this.profissional.email,
      profissional: this.profissional.profissional,
      crm_rms: this.profissional.crm_rms,
      celular: this.profissional.celular,
      telefone: this.profissional.telefone,
      endereco: this.profissional.endereco,
      senha: this.profissional.senha
    });
  }

  get f() { return this.profissionalForm.controls; }

  salvar():void {
        this.enviando = true;
        if (this.profissionalForm.invalid) {
            return;
        }else{
          this.profissional = new Profissional();
          this.profissional.idProfissional = this.profissional.idProfissional;
          this.profissional.nome = this.profissionalForm.controls.nome.value;
          this.profissional.email = this.profissionalForm.controls.email.value;
          this.profissional.profissional = this.profissionalForm.controls.profissional.value;
          this.profissional.senha = this.profissionalForm.controls.senha.value;
          this.profissional.crm_rms = this.profissionalForm.controls.crm_rms.value;
          this.profissional.celular = this.profissionalForm.controls.celular.value;
          this.profissional.telefone = this.profissionalForm.controls.telefone.value;
          this.profissional.endereco = this.profissionalForm.controls.endereco.value;
        }

    if(this.profissional.idProfissional == undefined){
      this.profissionalService.addProfissional(this.profissional).subscribe(response => {
        let res:Response = <Response>response;
        this.mensagem = res.mensagem;
        this.avisoMensagem = false
        setTimeout(() => this.avisoMensagem = true, 3000);
        this.profissional = new Profissional();
        this.profissionalForm.reset();
       },(erro) => {                  
          alert(erro);
       });

    }else{
      this.profissionalService.atualizarProfissional(this.profissional).subscribe(response => {
      let res:Response = <Response>response;
      this.avisoMensagem = false
      this.profissionalForm.reset();
      setTimeout(() => this.avisoMensagem = true, 3000);
      this.mensagem = res.mensagem;
      setTimeout(() => this.router.navigate(['/profissionais']), 3000);
     },(erro) => {                                  
        alert(erro);
     });
    }

  }
}

