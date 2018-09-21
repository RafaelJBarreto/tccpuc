import { Component, OnInit, ViewContainerRef } from '@angular/core';
import {Router} from '@angular/router';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import { ResultadoExameService } from '../../services/resultado-exame/resultado-exame.service';
import { ResultadoExame } from '../../services/resultado-exame/resultado-exame';

@Component({
  selector: 'app-resultado-exame',
  templateUrl: './resultado-exame.component.html',
  styleUrls: ['./resultado-exame.component.css']
})
export class ResultadoExameComponent implements OnInit {

  public procurarExame: string;
  protected  titulo:string;
  protected  resultados: ResultadoExame[] = new Array();
  protected procurarResultado: string;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  qtdResultado: number;

  constructor(
    private resultadoExameService: ResultadoExameService,
    private router: Router, private spinnerService: Ng4LoadingSpinnerService,
    private viewRef: ViewContainerRef
  ) { }

  ngOnInit() {
    this.titulo = "Resultados de Exames";
    this.getResultados();
  }

  getResultados(){
    this.spinnerService.show();
    this.resultadoExameService.getResultadoExames().subscribe(res => {
      this.resultados = res;
      this.qtdResultado = this.resultados.length;
      this.spinnerService.hide();
    });
  }

  editarResultado(idConsulta: number){
    this.router.navigate(['/editarresultado', idConsulta]);
  }

}
