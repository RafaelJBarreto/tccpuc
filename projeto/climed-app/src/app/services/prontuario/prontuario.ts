import { Paciente } from "../pacientes/paciente";
import { ProntuarioDetails } from "../dominiosUtil/prontuarioDetails";

export class Prontuario {
    paciente: Paciente;
    lista: ProntuarioDetails[] = new Array();
}