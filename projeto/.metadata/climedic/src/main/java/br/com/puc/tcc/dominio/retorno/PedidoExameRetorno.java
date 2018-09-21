package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;
import java.util.List;

import br.com.puc.tcc.dominio.Consulta;

@SuppressWarnings("serial")
public class PedidoExameRetorno implements Serializable{

	private String nomePaciente;
	private Consulta consulta;
	private List<ExameRetorno> listaExames;
	
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public List<ExameRetorno> getListaExames() {
		return listaExames;
	}
	public void setListaExames(List<ExameRetorno> listaExames) {
		this.listaExames = listaExames;
	}
}
