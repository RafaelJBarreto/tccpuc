import { Component, OnInit, ViewContainerRef } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import { ProntuarioService } from '../../services/prontuario/prontuario.service';
import { Prontuario } from '../../services/prontuario/prontuario';
import { ProntuarioDetails } from '../../services/dominiosUtil/prontuarioDetails';

@Component({
  selector: 'app-prontuario',
  templateUrl: './prontuario.component.html',
  styleUrls: ['./prontuario.component.css']
})
export class ProntuarioComponent implements OnInit {

  private prontuario: Prontuario;
  nomePaciente: String;
  dataNascimento: Date;
  sexo: String;
  cartao: String;
  lista: ProntuarioDetails[] = new Array();
  titulo: string;

  constructor(
    private prontuarioService: ProntuarioService,
    private router: Router, private spinnerService: Ng4LoadingSpinnerService,
    private viewRef: ViewContainerRef,
    private activatedRoute: ActivatedRoute, 
  ) { }

  ngOnInit() {
    this.titulo = "Prontuário";
    this.nomePaciente = "";
    this.activatedRoute.params.subscribe(parametro=>{
        this.prontuarioService.getProntuario(parametro["idPaciente"]).subscribe(res => {
            this.prontuario = res;
            this.nomePaciente = this.prontuario.paciente.nome;
            this.dataNascimento = this.prontuario.paciente.dataNascimento;
            this.cartao = this.prontuario.paciente.cartaoSus;
            this.lista = this.prontuario.lista;
            if( this.prontuario.paciente.sexo == 0)
              this.sexo = "Masculino";
            else
              this.sexo = "Feminino";
        });
    });
  }



}
