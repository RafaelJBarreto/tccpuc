import { Injectable } from '@angular/core';
import { Http} from "@angular/http"
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
 
import {Paciente} from './paciente';
import {ConfigService} from '../config.service';
 
@Injectable()
export class PacienteService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
 
    constructor(private http: Http, private configService: ConfigService) { 
        this.baseUrlService = configService.getUrlService();
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }
 
    public getPacientes(){        
         return this.http.get(this.baseUrlService + "/pacientes/").map(res => res.json());
    }

    getPaciente(idPaciente:number){
        return this.http.get(this.baseUrlService + "/paciente/" + idPaciente).map(res => res.json());
    }

    addPaciente(paciente: Paciente){
        return this.http.post(this.baseUrlService + "/paciente/novo", JSON.stringify(paciente),this.options).map(res => res.json());
    }

    excluirPaciente(idPaciente:number){
        return this.http.delete(this.baseUrlService + "/paciente/deletar/" + idPaciente).map(res => res.json());
    }
 
    atualizarPaciente(paciente:Paciente){
        return this.http.put(this.baseUrlService + "/paciente/atualizar" , JSON.stringify(paciente),this.options).map(res => res.json());
    }
 
}
