import { Injectable } from '@angular/core';
import { Http} from "@angular/http"
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
 
import {PedidoExame} from './pedido-exame';
import {ConfigService} from '../config.service';
 
@Injectable()
export class PedidoExameService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
 
    constructor(private http: Http, private configService: ConfigService) { 
        this.baseUrlService = configService.getUrlService();
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }

    public getExames(){        
        return this.http.get(this.baseUrlService + "/exames/").map(res => res.json());
    }
    public getPedidos(){        
        return this.http.get(this.baseUrlService + "/pedidosexames/todos/").map(res => res.json());
    }
    
   public getPedidosExamesPorProfissional(idProfissional:number){        
    return this.http.get(this.baseUrlService + "/pedidosexames/profissional/" + idProfissional).map(res => res.json());
   }

    getPedidoExame(idConsulta:number){
        return this.http.get(this.baseUrlService + "/pedidoExame/dados/" + idConsulta).map(res => res.json());
    }

    addPedidoExame(pedidoexame: PedidoExame){
        return this.http.post(this.baseUrlService + "/pedidoexame/novo", JSON.stringify(pedidoexame),this.options).map(res => res.json());
    }
 
    atualizarPedidoExame(pedidoExame:PedidoExame){
        return this.http.post(this.baseUrlService + "/pedidoExame/atualizar" , JSON.stringify(pedidoExame),this.options).map(res => res.json());
    }

    excluirPedido(idConsulta:number){
        return this.http.delete(this.baseUrlService + "/pedidoExame/deletar/" + idConsulta).map(res => res.json());
    }
 
 
}
