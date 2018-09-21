import { Injectable } from '@angular/core';
import { Http} from "@angular/http"
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
 
import {Consulta} from './consulta';
import {ConfigService} from '../config.service';
import { BehaviorSubject } from 'rxjs';
 
@Injectable()
export class ConsultaService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
    private data = new BehaviorSubject('');
    currentData = this.data.asObservable()
 
    constructor(private http: Http, private configService: ConfigService) { 
        this.baseUrlService = configService.getUrlService();
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }

    public getPacientesPorProfissional(idProfissional:number){        
        return this.http.get(this.baseUrlService + "/pacientes/profissional/" + idProfissional).map(res => res.json());
   }
 
    public getConsultas(){        
         return this.http.get(this.baseUrlService + "/consultas/").map(res => res.json());
    }

    public getConsultasValidas(){        
        return this.http.get(this.baseUrlService + "/consultas/validas/").map(res => res.json());
   }

   public getConsultasPorProfissional(idProfissional:number){        
    return this.http.get(this.baseUrlService + "/consultas/profissional/" + idProfissional).map(res => res.json());
   }

   setConsulta(item: any) {
    this.data.next(item);
   }

    getConsulta(idConsulta:number){
        return this.http.get(this.baseUrlService + "/consulta/" + idConsulta).map(res => res.json());
    }

    addConsulta(consulta: Consulta){
        return this.http.post(this.baseUrlService + "/consulta/novo", JSON.stringify(consulta),this.options).map(res => res.json());
    }

    cancelarConsulta(idConsulta:number){
        return this.http.delete(this.baseUrlService + "/consulta/cancelar/" + idConsulta).map(res => res.json());
    }
 
    atualizarConsulta(consulta:Consulta){
        return this.http.put(this.baseUrlService + "/consulta/atualizar" , JSON.stringify(consulta),this.options).map(res => res.json());
    }
 
}
