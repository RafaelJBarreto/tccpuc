import { Injectable } from '@angular/core';
import { Http} from "@angular/http"
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
 
import {ConsultaMedico} from './consulta-medico';
import {ConfigService} from '../config.service';
import { BehaviorSubject } from 'rxjs';
 
@Injectable()
export class ConsultaMedicoService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
 
    constructor(private http: Http, private configService: ConfigService) { 
        this.baseUrlService = configService.getUrlService();
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }
 
   public getConsultasMedicasPorProfissional(idProfissional:number){        
    return this.http.get(this.baseUrlService + "/consultasmedicas/profissional/" + idProfissional).map(res => res.json());
   }

    getConsultaMedica(idConsulta:number){
        return this.http.get(this.baseUrlService + "/consultamedica/dados/" + idConsulta).map(res => res.json());
    }

    addConsultaMedica(consultaMedica: ConsultaMedico){
        return this.http.post(this.baseUrlService + "/consultamedica/novo", JSON.stringify(consultaMedica),this.options).map(res => res.json());
    }
 
    atualizarConsultaMedica(consultaMedica:ConsultaMedico){
        return this.http.post(this.baseUrlService + "/consultamedica/atualizar" , JSON.stringify(consultaMedica),this.options).map(res => res.json());
    }

    excluirConsulta(idConsulta:number){
        return this.http.delete(this.baseUrlService + "/consultamedica/deletar/" + idConsulta).map(res => res.json());
    }
 
}
