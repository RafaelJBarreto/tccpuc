import { Consulta } from "../consultas/consulta";
import { ExamesResultado } from "../dominiosUtil/examesResultado";

export class ResultadoExame {
    consulta: Consulta;
    listaResultado: ExamesResultado[] = new Array();
    dataColeta:Date;
    nomePaciente: String;
    enviadoMedico:boolean;
    enviadoPaciente:boolean;
}