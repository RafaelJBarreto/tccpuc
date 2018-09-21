package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;
import java.util.List;

import br.com.puc.tcc.dominio.Consulta;

@SuppressWarnings("serial")
public class ConsultaMedicaRetorno implements Serializable{

	private String nomePaciente;
	private String diagnosticoConsulta;
	private Consulta consulta;
	private List<MedicamentoConsulta> listaMedicamentos;
	
	public String getNomePaciente() {
		return nomePaciente;
	}
	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}
	public String getDiagnosticoConsulta() {
		return diagnosticoConsulta;
	}
	public void setDiagnosticoConsulta(String diagnosticoConsulta) {
		this.diagnosticoConsulta = diagnosticoConsulta;
	}
	public Consulta getConsulta() {
		return consulta;
	}
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}
	public List<MedicamentoConsulta> getListaMedicamentos() {
		return listaMedicamentos;
	}
	public void setListaMedicamentos(List<MedicamentoConsulta> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}
}
