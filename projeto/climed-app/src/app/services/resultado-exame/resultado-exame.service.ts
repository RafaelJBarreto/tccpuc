import { Injectable } from '@angular/core';
import { Http} from "@angular/http"
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
 
import {ResultadoExame} from './resultado-exame';
import {ConfigService} from '../config.service';
 
@Injectable()
export class ResultadoExameService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
 
    constructor(private http: Http, private configService: ConfigService) { 
        this.baseUrlService = configService.getUrlService();
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }

    public getResultadoExames(){        
        return this.http.get(this.baseUrlService + "/resultadoexames/todos/").map(res => res.json());
   }
    
   public getResultadoExamesPorProfissional(idProfissional:number){        
    return this.http.get(this.baseUrlService + "/resultadoexames/profissional/" + idProfissional).map(res => res.json());
   }

    getResultadoExame(idConsulta:number){
        return this.http.get(this.baseUrlService + "/resultadoexame/dados/" + idConsulta).map(res => res.json());
    }

    addResultadoExame(re: ResultadoExame){
        return this.http.post(this.baseUrlService + "/resultadoexame/novo", JSON.stringify(re),this.options).map(res => res.json());
    }
 
    atualizarResultadoExame(re:ResultadoExame){
        return this.http.post(this.baseUrlService + "/resultadoexame/atualizar" , JSON.stringify(re),this.options).map(res => res.json());
    }

    excluirResultadoExame(idConsulta:number){
        return this.http.delete(this.baseUrlService + "/resultadoexame/deletar/" + idConsulta).map(res => res.json());
    }

}
