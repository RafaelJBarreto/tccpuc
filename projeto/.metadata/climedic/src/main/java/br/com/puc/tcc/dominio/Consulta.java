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
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Table(name="consulta")
@Entity
public class Consulta implements Serializable{

	@Id
    @Basic(optional = false)
    @Column(name = "id_Consulta", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idConsulta;
	
	@JoinColumn(name = "id_Paciente", referencedColumnName = "id_Paciente", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Paciente paciente;
	
	@JoinColumn(name = "id_Profissional", referencedColumnName = "id_Profissional", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Profissional profissional;
	
	@Column(name="data_hora", nullable=false)
	private Date dataHora;
	
	@Column(name="cancelada", nullable=false)
	private boolean cancelada;
	
	@Column(name="realizada", nullable=false)
	private boolean realizada;
	
	@Column(name="pedido_realizado", nullable=false)
	private boolean pedidoRealizado;
	
	@Transient
	private String nomePaciente;
	
	public Consulta() {
		super();
	}

	public Consulta(Integer idConsulta) {
		super();
		this.idConsulta = idConsulta;
	}

	public Integer getIdConsulta() {
		return idConsulta;
	}

	public void setIdConsulta(Integer idConsulta) {
		this.idConsulta = idConsulta;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Date getDataHora() {
		return dataHora;
	}

	public void setDataHora(Date dataHora) {
		this.dataHora = dataHora;
	}

	public String getNomePaciente() {
		return nomePaciente;
	}

	public void setNomePaciente(String nomePaciente) {
		this.nomePaciente = nomePaciente;
	}

	public boolean isCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	public boolean isRealizada() {
		return realizada;
	}

	public void setRealizada(boolean realizada) {
		this.realizada = realizada;
	}

	public boolean isPedidoRealizado() {
		return pedidoRealizado;
	}

	public void setPedidoRealizado(boolean pedidoRealizado) {
		this.pedidoRealizado = pedidoRealizado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idConsulta == null) ? 0 : idConsulta.hashCode());
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
		Consulta other = (Consulta) obj;
		if (idConsulta == null) {
			if (other.idConsulta != null)
				return false;
		} else if (!idConsulta.equals(other.idConsulta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Consulta [idConsulta=" + idConsulta + "]";
	}
	
}
