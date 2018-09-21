package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class ProntuarioDetails implements Serializable{

	private Integer idConsulta;
	private String medico;
	private String especialidade;
	private Date dataConsulta;
	private String diagnosticoConsulta;
	private List<MedicamentoConsulta> listaMedicamentos;
	private List<ResultadoExameRetorno>  listaResultado;
	
	public Integer getIdConsulta() {
		return idConsulta;
	}
	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}
	public String getMedico() {
		return medico;
	}
	public void setMedico(String medico) {
		this.medico = medico;
	}
	public String getEspecialidade() {
		return especialidade;
	}
	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
	}
	public Date getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public List<MedicamentoConsulta> getListaMedicamentos() {
		return listaMedicamentos;
	}
	public void setListaMedicamentos(List<MedicamentoConsulta> listaMedicamentos) {
		this.listaMedicamentos = listaMedicamentos;
	}
	public String getDiagnosticoConsulta() {
		return diagnosticoConsulta;
	}
	public void setDiagnosticoConsulta(String diagnosticoConsulta) {
		this.diagnosticoConsulta = diagnosticoConsulta;
	}
	public List<ResultadoExameRetorno> getListaResultado() {
		return listaResultado;
	}
	public void setListaResultado(List<ResultadoExameRetorno> listaResultado) {
		this.listaResultado = listaResultado;
	}
}
