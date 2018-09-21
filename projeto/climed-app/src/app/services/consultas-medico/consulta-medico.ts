import { Consulta } from "../consultas/consulta";
import {MedicamentoConsulta} from "../dominiosUtil/medicamentoConsulta"

export class ConsultaMedico {
    consulta: Consulta;
    listaMedicamentos: MedicamentoConsulta[] = new Array();
    diagnosticoConsulta:string;
    nomePaciente: String;
}