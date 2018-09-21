import { Injectable } from '@angular/core';
import { Http} from "@angular/http"
import { Headers} from '@angular/http';
import { RequestOptions } from '@angular/http';
 
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';
import 'rxjs/add/operator/catch';
 
import {ConfigService} from '../config.service';
import { BehaviorSubject } from 'rxjs';
 
@Injectable()
export class EstatisticaService {
 
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

    public getDados(){        
        return this.http.get(this.baseUrlService + "/estatistica/").map(res => res.json());
   }
}
