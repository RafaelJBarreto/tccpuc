import { Profissional } from "../profissionais/profissional";
import { Paciente } from "../pacientes/paciente";

export class Consulta {
    idConsulta: number;
    profissional: Profissional;
    paciente: Paciente;
    nomePaciente: String;
    dataHora: Date;
    cancelada: boolean;
    realizada: boolean;
    pedidoRealizado: boolean;
}