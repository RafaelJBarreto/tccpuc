import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup,  FormBuilder,  Validators } from '@angular/forms';
import { Profissional } from '../../services/profissionais/profissional';
import { ProfissionalService } from '../../services/profissionais/profissional.service';
import { LocalStorage } from '@ngx-pwa/local-storage';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  loginForm: FormGroup;
  enviando = false;
  profissional:Profissional;
  erroLogin = true;

  constructor(
    private storage: LocalStorage,
    private profissionalService: ProfissionalService,
    private router: Router,
    private formBuilder: FormBuilder) {
      this.createForm();
   }

   ngOnInit() {
  }
  
   createForm() {
    this.loginForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', [Validators.required, Validators.minLength(3)]]
     });
   }

  get f() { return this.loginForm.controls; }

  LogOn() {
    this.enviando = true;
    if (this.loginForm.invalid) {
        return;
    }else{
      this.storage.clear().subscribe(() => {});
      this.profissional = new Profissional();
      this.profissional.email = this.loginForm.controls.email.value;
      this.profissional.senha = this.loginForm.controls.senha.value;

      this.profissionalService.login(this.profissional).subscribe( res => {
          this.profissional = res;
          if(this.profissional.idProfissional != null){
            this.storage.setItem('usuario', this.profissional).subscribe(() => {});
            location.reload();
            this.router.navigate(['home']);
          }else{
            this.erroLogin = false;
          }
      });
    }
  }
}