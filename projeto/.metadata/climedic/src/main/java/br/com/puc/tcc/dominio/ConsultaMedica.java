package br.com.puc.tcc.dominio;

import java.io.Serializable;

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
@Table(name="consulta_medica")
@Entity
public class ConsultaMedica implements Serializable{

	@Id
    @Basic(optional = false)
    @Column(name = "id_Consulta_Medica", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConsultaMedica;
		
	@JoinColumn(name = "id_Consulta", referencedColumnName = "id_Consulta", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Consulta consulta;
	
	@JoinColumn(name = "id_Medicamento", referencedColumnName = "id_Medicamento", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Medicamento medicamento;
	
	@Column(name="prescricao", nullable=false)
	private String prescricao;
	
	@Column(name="quantidade", nullable=false)
	private Integer quantidade;
	
	@Column(name="diagnotisco_Consulta", nullable=true)
	private String diagnosticoConsulta;
	
	public ConsultaMedica() {
		super();
	}

	public ConsultaMedica(Integer idConsultaMedica) {
		super();
		this.idConsultaMedica = idConsultaMedica;
	}

	public Integer getIdConsultaMedica() {
		return idConsultaMedica;
	}

	public void setIdConsultaMedica(Integer idConsultaMedica) {
		this.idConsultaMedica = idConsultaMedica;
	}

	public Consulta getConsulta() {
		return consulta;
	}

	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	public Medicamento getMedicamento() {
		return medicamento;
	}

	public void setMedicamento(Medicamento medicamento) {
		this.medicamento = medicamento;
	}

	public String getPrescricao() {
		return prescricao;
	}

	public void setPrescricao(String prescricao) {
		this.prescricao = prescricao;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public String getDiagnosticoConsulta() {
		return diagnosticoConsulta;
	}

	public void setDiagnosticoConsulta(String diagnosticoConsulta) {
		this.diagnosticoConsulta = diagnosticoConsulta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConsultaMedica == null) ? 0 : idConsultaMedica.hashCode());
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
		ConsultaMedica other = (ConsultaMedica) obj;
		if (idConsultaMedica == null) {
			if (other.idConsultaMedica != null)
				return false;
		} else if (!idConsultaMedica.equals(other.idConsultaMedica))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ConsultaMedica [idConsultaMedica=" + idConsultaMedica + "]";
	}	
}
