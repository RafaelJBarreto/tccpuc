import { Injectable } from '@angular/core';
import { Http} from "@angular/http"
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
 
import {Medicamento} from './medicamento';
import {ConfigService} from '../config.service';
 
@Injectable()
export class MedicamentoService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
 
    constructor(private http: Http, private configService: ConfigService) { 
        this.baseUrlService = configService.getUrlService();
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }
 
    public getMedicamentos(){        
         return this.http.get(this.baseUrlService + "/medicamentos/").map(res => res.json());
    }

    getMedicamento(idMedicamento:number){
        return this.http.get(this.baseUrlService + "/medicamento/" + idMedicamento).map(res => res.json());
    }

    addMedicamento(medicamento: Medicamento){
        return this.http.post(this.baseUrlService + "/medicamento/novo", JSON.stringify(medicamento),this.options).map(res => res.json());
    }

    excluirMedicamento(idMedicamento:number){
        return this.http.delete(this.baseUrlService + "/medicamento/deletar/" + idMedicamento).map(res => res.json());
    }
 
    atualizarMedicamento(medicamento:Medicamento){
        return this.http.put(this.baseUrlService + "/medicamento/atualizar" , JSON.stringify(medicamento),this.options).map(res => res.json());
    }
 
}
