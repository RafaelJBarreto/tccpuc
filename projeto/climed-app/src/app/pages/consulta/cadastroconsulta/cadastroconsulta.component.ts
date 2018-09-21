import { Component, OnInit} from '@angular/core';
import {Ng4LoadingSpinnerService} from 'ng4-loading-spinner';
import {FormGroup,  FormBuilder,  Validators} from '@angular/forms';
import {Router, ActivatedRoute} from '@angular/router';
import {ConsultaService} from '../../../services/consultas/consulta.service';
import {Response} from '../../../services/response';
import {PacienteService} from '../../../services/pacientes/paciente.service';
import {ProfissionalService} from '../../../services/profissionais/profissional.service';
import { Consulta } from '../../../services/consultas/consulta';
import * as moment from 'moment'; 

@Component({
  selector: 'app-cadastroconsulta',
  templateUrl: './cadastroconsulta.component.html',
  styleUrls: ['./cadastroconsulta.component.css']
})
export class CadastroconsultaComponent implements OnInit {

  public dataAtual = new Date();
  public min = new Date(this.dataAtual.getFullYear(), this.dataAtual.getMonth(), this.dataAtual.getUTCDay() + 1, 0, 0);
  protected  consultas: any[];
  protected  pacientes: any[];
  protected  medicos: any[];
  private titulo:string;
  private consulta:Consulta = new Consulta();
  consultaForm: FormGroup;
  enviando = false;
  avisoMensagem = true;
  mensagem:string;
  dataInvalida = true;
  diferenca: number;

  createForm() {
    this.consultaForm = this.formBuilder.group({
      profissional: ['', Validators.required],
      paciente: ['', Validators.required],
      dataHora: ['', Validators.required]
  });
  }

  constructor(private pacienteService: PacienteService,
    private consultaService: ConsultaService,
    private profissionalService: ProfissionalService,
    private spinnerService: Ng4LoadingSpinnerService,
    private router: Router,
    private activatedRoute: ActivatedRoute, 
    private formBuilder: FormBuilder){
      this.spinnerService.show();
      this.createForm();
      this.getPacientes();
      this.getMedicos();
      this.getConsultasValidas();
      this.spinnerService.hide();
    }

  ngOnInit() {
    console.log(this.dataAtual.getDay());
    this.activatedRoute.params.subscribe(parametro=>{
      if(parametro["idConsulta"] == undefined){
        this.titulo = "Nova Consulta";
      }
    });      
  }

  getPacientes(){
    this.pacienteService.getPacientes().subscribe(res => {
      this.pacientes = res;
    });
  }

  getMedicos(){
    this.profissionalService.getMedicos().subscribe(res => {
      this.medicos = res;
    });
  }

  getConsultasValidas(){
    this.spinnerService.show();
    this.consultaService.getConsultasValidas().subscribe(res =>{
      this.consultas = res;
      this.spinnerService.hide();
    });
  }

  verificaDatas():boolean{
    let idProfissional = this.consultaForm.controls.profissional.value;
    let dataHora = moment(this.consultaForm.controls.dataHora.value);
    for(let i=0; i<this.consultas.length; i++){
      if(this.consultas[i].profissional.idProfissional == idProfissional){
        var ms = moment(dataHora,"DD/MM/YYYY HH:mm:ss").diff(moment(moment(this.consultas[i].dataHora),"DD/MM/YYYY HH:mm:ss"));
        var minuto = moment.utc(ms).format('mm');
        var hora = moment.utc(ms).format('HH');
        this.diferenca = (parseInt(hora) * 60) + parseInt(minuto);
        if(Math.abs(this.diferenca) < 30){
          this.dataInvalida = false;
          return true;
        }
        
      }
    }
    return false;
  }

  get f() { return this.consultaForm.controls; }

  salvar():void {
    this.enviando = true;
    if (this.consultaForm.invalid) {
        return;
    }else if(this.verificaDatas()){
      return;
    }else{
      this.consulta.idConsulta = this.consulta.idConsulta;
      this.consulta.profissional = this.consultaForm.controls.profissional.value;
      this.consulta.paciente = this.consultaForm.controls.paciente.value;
      this.consulta.dataHora = moment(this.consultaForm.controls.dataHora.value).toDate();
      
      this.dataInvalida = true;

      if(this.consulta.idConsulta == undefined){
        this.consultaService.addConsulta(this.consulta).subscribe(response => {
          let res:Response = <Response>response;
          this.mensagem = res.mensagem;
          this.avisoMensagem = false
          setTimeout(() => this.avisoMensagem = true, 3000);
          this.getConsultasValidas();
          this.consulta = new Consulta();
          this.consultaForm.reset();
        },(erro) => {                  
            alert(erro);
        });

      }
    }
  }

}
