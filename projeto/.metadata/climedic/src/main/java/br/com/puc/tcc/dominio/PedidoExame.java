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
@Table(name="pedido_exame")
@Entity
public class PedidoExame implements Serializable{

	@Id
    @Basic(optional = false)
    @Column(name = "id_Pedido_Exame", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPedidoExame;

	@JoinColumn(name = "id_Exame", referencedColumnName = "id_Exame", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Exame exame;
	
	@JoinColumn(name = "id_Consulta", referencedColumnName = "id_Consulta", nullable = false)
	@ManyToOne(optional = false, fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	private Consulta consulta;
	
	@Column(name="existe_resultado", nullable=false)
	private boolean existeResultado;
	
	public Integer getIdPedidoExame() {
		return idPedidoExame;
	}

	public void setIdPedidoExame(Integer idPedidoExame) {
		this.idPedidoExame = idPedidoExame;
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
	
	

	public boolean isExisteResultado() {
		return existeResultado;
	}

	public void setExisteResultado(boolean existeResultado) {
		this.existeResultado = existeResultado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPedidoExame == null) ? 0 : idPedidoExame.hashCode());
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
		PedidoExame other = (PedidoExame) obj;
		if (idPedidoExame == null) {
			if (other.idPedidoExame != null)
				return false;
		} else if (!idPedidoExame.equals(other.idPedidoExame))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PedidoExame [idPedidoExame=" + idPedidoExame + "]";
	}
}
