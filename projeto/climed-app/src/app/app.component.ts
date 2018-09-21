import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Profissional } from '../app/services/profissionais/profissional';
import { LocalStorage } from '@ngx-pwa/local-storage';
import $ from "jquery";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styles: [`
  :host ::ng-deep .custom-spinner-template {
    position: absolute;
    left: 46%;
    background-color:black;
    width:64px;
    height:64px
}`]
})

export class AppComponent implements OnInit{
  nome:String;
  isMedico: boolean;
  isSecretaria: boolean;

  constructor(
    private router: Router,
    private storage: LocalStorage
  ) {}

  ngOnInit() {
    this.nome = "";
    this.storage.getItem<Profissional>('usuario').subscribe((usuario) => {
      if(usuario != null)
        if(usuario.profissional == 0){
          this.nome = usuario.nome;
          this.isMedico = false;
          this.isSecretaria = true;
        }else{
          this.nome = " Dr. " + usuario.nome;
          this.isMedico = true;
          this.isSecretaria = false;
        }
    });
  }

  sair(){
    this.storage.clear().subscribe(() => {});
    this.router.navigate(['login']);
  }

  template: string = '<img class="custom-spinner-template" src="./assets/loading.gif" />';
}
