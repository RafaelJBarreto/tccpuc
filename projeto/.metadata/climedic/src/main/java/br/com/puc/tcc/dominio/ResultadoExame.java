package br.com.puc.tcc.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="resultado_exame")
@Entity
public class ResultadoExame implements Serializable{

	@Id
    @Basic(optional = false)
    @Column(name = "id_Resultado_Exame", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idResultadoExame;

	@JoinColumn(name = "id_Exame", referencedColumnName = "id_Exame", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Exame exame;
	
	@JoinColumn(name = "id_Consulta", referencedColumnName = "id_Consulta", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Consulta consulta;
	
	@Column(name="data_coleta", nullable=true)
	private Date dataColeta;
	
	@Column(name="valores_obtidos", nullable=false)
	private Double valoresObtidos;
	
	@Column(name="unidade", nullable=false)
	private Integer unidade;
	
	@Column(name="valor_referencial", nullable=false)
	private String valorReferencial;
	
	@Column(name="enviado_medico", nullable=false)
	private boolean enviadoMedico;
	
	@Column(name="enviado_paciente", nullable=false)
	private boolean enviadoPaciente;

	public Integer getIdResultadoExame() {
		return idResultadoExame;
	}

	public void setIdResultadoExame(Integer idResultadoExame) {
		this.idResultadoExame = idResultadoExame;
	}

	public Exame getExame() {
		return exame;
	}

	public void setExame(Exame exame) {
		this.exame = exame;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Date getDataColeta() {
		return dataColeta;
	}

	public void setDataColeta(Date dataColeta) {
		this.dataColeta = dataColeta;
	}

	public Double getValoresObtidos() {
		return valoresObtidos;
	}

	public void setValoresObtidos(Double valoresObtidos) {
		this.valoresObtidos = valoresObtidos;
	}

	public Integer getUnidade() {
		return unidade;
	}

	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}

	public String getValorReferencial() {
		return valorReferencial;
	}

	public void setValorReferencial(String valorReferencial) {
		this.valorReferencial = valorReferencial;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idResultadoExame == null) ? 0 : idResultadoExame.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResultadoExame other = (ResultadoExame) obj;
		if (idResultadoExame == null) {
			if (other.idResultadoExame != null)
				return false;
		} else if (!idResultadoExame.equals(other.idResultadoExame))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ResultadoExame [idResultadoExame=" + idResultadoExame + "]";
	}
}
