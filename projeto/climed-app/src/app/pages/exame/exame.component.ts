import { Component, OnInit, ViewContainerRef } from '@angular/core';
import {Router} from '@angular/router';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {PedidoExameService} from '../../services/pedido-exame/pedido-exame.service';


@Component({
  selector: 'app-exame',
  templateUrl: './exame.component.html',
  styleUrls: ['./exame.component.css']
})
export class ExameComponent implements OnInit {

  public procurarExame: string;
  protected  titulo:string;
  protected  pedidos: any[];
  protected procurarPedidoPorNome: string;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  qtdPedido: number;

  constructor(
    private pedidoExameService: PedidoExameService,
    private router: Router, private spinnerService: Ng4LoadingSpinnerService,
    private viewRef: ViewContainerRef
  ) { }

  ngOnInit() {
    this.titulo = "Pedidos de Exames";
    this.getPedidos();
  }

  getPedidos(){
    this.spinnerService.show();
    this.pedidoExameService.getPedidos().subscribe(res => {
      this.pedidos = res;
      this.qtdPedido = this.pedidos.length;
      this.spinnerService.hide();
    });
  }

  lancarResultado(idConsulta: number){
    this.router.navigate(['/lancarresultado', idConsulta]);
  }
}
