import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http"
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
 
import {Profissional} from './profissional';
import {ConfigService} from '../config.service';
 
@Injectable()
export class ProfissionalService {
 
    private baseUrlService:string = '';
    private headers:Headers;
    private options:RequestOptions;
    private profissional: Profissional;
 
    constructor(
        private http: Http, private configService: ConfigService) { 
        this.baseUrlService = configService.getUrlService();
        this.headers = new Headers({ 'Content-Type': 'application/json;charset=UTF-8' });                
        this.options = new RequestOptions({ headers: this.headers });
    }
 
    public getProfissionais(){        
         return this.http.get(this.baseUrlService + "/profissionais/").map(res => res.json());
    }

    public getMedicos(){        
        return this.http.get(this.baseUrlService + "/medicos/").map(res => res.json());
   }

    getProfissional(idProfissional:number){
        return this.http.get(this.baseUrlService + "/profissional/" + idProfissional).map(res => res.json());
    }

    addProfissional(profissional: Profissional){
        return this.http.post(this.baseUrlService + "/profissional/novo", JSON.stringify(profissional),this.options).map(res => res.json());
    }

    excluirProfissional(idProfissional:number){
        return this.http.delete(this.baseUrlService + "/profissional/deletar/" + idProfissional).map(res => res.json());
    }
 
    atualizarProfissional(profissional:Profissional){
        return this.http.put(this.baseUrlService + "/profissional/atualizar" , JSON.stringify(profissional),this.options).map(res => res.json());
    }

    atualizarSenha(profissional:Profissional){
        return this.http.put(this.baseUrlService + "/profissional/atualizarsenha" , JSON.stringify(profissional),this.options).map(res => res.json());
    }

    login(profissional: Profissional){
        return this.http.post(this.baseUrlService + "/login", JSON.stringify(profissional), this.options).map(res => res.json());
    }
}
