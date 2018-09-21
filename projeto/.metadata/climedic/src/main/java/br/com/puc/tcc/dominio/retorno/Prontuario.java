package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;
import java.util.List;

import br.com.puc.tcc.dominio.Paciente;

@SuppressWarnings("serial")
public class Prontuario implements Serializable{

	private Paciente paciente;
	private List<ProntuarioDetails> lista;
	
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public List<ProntuarioDetails> getLista() {
		return lista;
	}
	public void setLista(List<ProntuarioDetails> lista) {
		this.lista = lista;
	}
	
}
