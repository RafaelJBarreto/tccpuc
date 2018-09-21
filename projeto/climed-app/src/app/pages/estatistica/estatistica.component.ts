import { Component, OnInit } from '@angular/core';
import { Ng4LoadingSpinnerService } from 'ng4-loading-spinner';
import { Router, ActivatedRoute } from '@angular/router';
import { EstatisticaService } from '../../services/estatistica/estatistica.service';
import { DadosGrafico } from '../../services/estatistica/estatistica';
import { Chart } from 'angular-highcharts';

@Component({
  selector: 'app-estatistica',
  templateUrl: './estatistica.component.html',
  styleUrls: ['./estatistica.component.css']
})
export class EstatisticaComponent implements OnInit {

  titulo:string;
  dadosGrafico: DadosGrafico;
  chart: Chart;
  series:any;
  seriesR:any;

  constructor(
    private estatisticaService: EstatisticaService,
    private spinnerService: Ng4LoadingSpinnerService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
  ) { }

  ngOnInit() {
    this.titulo = "Estatisticas";
    this.spinnerService.show();
    this.series = new Array();
    this.seriesR = new Array();
    this.estatisticaService.getDados().subscribe((res) => {
      this.dadosGrafico = res;
      let valor;
      for(let i = 0; i < this.dadosGrafico.dados.length; i++){
        for(let j = 0; j < this.dadosGrafico.dados[i].consultas.length; j++){
          valor = this.dadosGrafico.dados[i].consultas[j].split("|");
          this.series.push(valor[1], parseInt(valor[0]))
        }
      }
      let valorR;
      for(let i = 0; i < this.dadosGrafico.dados.length; i++){
        for(let j = 0; j < this.dadosGrafico.dados[i].resultados.length; j++){
          valorR = this.dadosGrafico.dados[i].resultados[j].split("|");
          this.seriesR.push(valorR[1], parseInt(valorR[0]))
        }
      }
      this.gerarGrafico(this.series, this.seriesR);
     });
     this.spinnerService.hide();
  }

  gerarGrafico(series, seriesR){
    this.chart = new Chart({
      title: {
        text: 'Consultas e resultados de exames'
    },
    yAxis: {
        allowDecimals: false,
        title: {
            text: 'Dados'
        }
    },
    credits: {
      enabled: false
    },
    series: [{
      "data": [
         series
      ],
      "name": "Consultas"
  }, {
      "data": [
         seriesR
      ],
      "name": "Exames"
  }]
    });
  }

}
