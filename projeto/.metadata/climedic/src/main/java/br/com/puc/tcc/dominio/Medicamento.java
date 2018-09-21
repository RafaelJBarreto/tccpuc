package br.com.puc.tcc.dominio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="medicamento")
@Entity
public class Medicamento implements Serializable{

	@Id
    @Basic(optional = false)
    @Column(name = "id_Medicamento", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idMedicamento;
	
	@Basic(optional = false)
    @Column(name = "nome_generico", nullable = false, length = 100)
	private String nomeGenerico;
	
	@Basic(optional = false)
    @Column(name = "nome_comercial", nullable = false, length = 100)
	private String nomeComercial;
	
	@Basic(optional = true)
    @Column(name = "fabricante", nullable = true)
	private Integer fabricante;

	public Medicamento() {
		super();
	}

	public Medicamento(Integer idMedicamento) {
		super();
		this.idMedicamento = idMedicamento;
	}

	public Integer getIdMedicamento() {
		return idMedicamento;
	}

	public void setIdMedicamento(Integer idMedicamento) {
		this.idMedicamento = idMedicamento;
	}

	public String getNomeGenerico() {
		return nomeGenerico;
	}

	public void setNomeGenerico(String nomeGenerico) {
		this.nomeGenerico = nomeGenerico;
	}

	public String getNomeComercial() {
		return nomeComercial;
	}

	public void setNomeComercial(String nomeComercial) {
		this.nomeComercial = nomeComercial;
	}

	public Integer getFabricante() {
		return fabricante;
	}

	public void setFabricante(Integer fabricante) {
		this.fabricante = fabricante;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idMedicamento == null) ? 0 : idMedicamento.hashCode());
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
		Medicamento other = (Medicamento) obj;
		if (idMedicamento == null) {
			if (other.idMedicamento != null)
				return false;
		} else if (!idMedicamento.equals(other.idMedicamento))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Medicamento [idMedicamento=" + idMedicamento + "]";
	}
}
