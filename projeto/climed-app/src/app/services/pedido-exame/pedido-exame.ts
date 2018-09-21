import { ExameRetorno } from "../dominiosUtil/exameRetorno";
import { Consulta } from "../consultas/consulta";

export class PedidoExame {
    consulta: Consulta;
    listaExames: ExameRetorno[] = new Array();
    nomePaciente: String;
}