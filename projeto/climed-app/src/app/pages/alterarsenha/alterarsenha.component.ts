import { Component, OnInit, ViewContainerRef} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {ProfissionalService} from '../../services/profissionais/profissional.service';
import {FormGroup,  FormBuilder,  Validators} from '@angular/forms';
import {Profissional} from '../../services/profissionais/profissional';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {Response} from '../../services/response';
import { LocalStorage } from '@ngx-pwa/local-storage';

@Component({
  selector: 'app-alterarsenha',
  templateUrl: './alterarsenha.component.html',
  styleUrls: ['./alterarsenha.component.css']
})
export class AlterarsenhaComponent implements OnInit {

  protected titulo: string;
  private profissional:Profissional = new Profissional();
  alterarForm: FormGroup;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;

  constructor(
    private spinnerService: Ng4LoadingSpinnerService,
    private router: Router,
    private storage: LocalStorage,
    private profissionalService: ProfissionalService,
    private formBuilder: FormBuilder
  ) { 
    this.createForm();
  }

  ngOnInit() {
    this.titulo = "Alterar senha";
  }

  createForm() {
    this.alterarForm = this.formBuilder.group({
      senhaAtual: ['', [Validators.required, Validators.minLength(3)]],
      novaSenha: ['', [Validators.required, Validators.minLength(3)]]
  });
  }

  alterar():void {
    this.enviando = true;
    if (this.alterarForm.invalid) {
        return;
    }else{
      this.storage.getItem<Profissional>('usuario').subscribe((usuario) => {
        if(usuario == null){
          this.mensagem ="Por favor, saia do sistema e realize o login, para tentar novamente...";
          this.avisoMensagem = false
          setTimeout(() => this.avisoMensagem = true, 3000);
        }else{
          this.profissional = new Profissional();
          this.profissional.idProfissional = usuario.idProfissional;
          this.profissional.senha = this.alterarForm.controls.senhaAtual.value;
          this.profissional.novaSenha = this.alterarForm.controls.novaSenha.value;
          this.spinnerService.show();
          this.profissionalService.atualizarSenha(this.profissional).subscribe(response => {
            let res:Response = <Response>response;
            this.avisoMensagem = false
            this.alterarForm.reset();
            this.spinnerService.hide();
            setTimeout(() => this.avisoMensagem = true, 2000);
            this.mensagem = res.mensagem;
            setTimeout(() => this.router.navigate(['/login']), 2000);
           },(erro) => {                                  
              alert(erro);
           });
       }
      });
    }
  }

  get f() { return this.alterarForm.controls; }

}
