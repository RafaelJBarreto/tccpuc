package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import br.com.puc.tcc.dominio.Consulta;

@SuppressWarnings("serial")
public class ResultadoExameRetorno implements Serializable{

	private String nomePaciente;
	private Consulta consulta;
	private List<ExamesResultado> listaResultado;
	private Date dataColeta;
	private boolean enviadoMedico;
	private boolean enviadoPaciente;
	
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
	public List<ExamesResultado> getListaResultado() {
		return listaResultado;
	}
	public void setListaResultado(List<ExamesResultado> listaResultado) {
		this.listaResultado = listaResultado;
	}
	public Date getDataColeta() {
		return dataColeta;
	}
	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}
	public boolean isEnviadoMedico() {
		return enviadoMedico;
	}
	public void setEnviadoMedico(boolean enviadoMedico) {
		this.enviadoMedico = enviadoMedico;
	}
	public boolean isEnviadoPaciente() {
		return enviadoPaciente;
	}
	public void setEnviadoPaciente(boolean enviadoPaciente) {
		this.enviadoPaciente = enviadoPaciente;
	}
}
