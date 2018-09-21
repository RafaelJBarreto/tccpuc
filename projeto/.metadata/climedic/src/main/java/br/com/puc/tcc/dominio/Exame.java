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
@Table(name="exame")
@Entity
public class Exame implements Serializable{

	@Id
    @Basic(optional = false)
    @Column(name = "id_Exame", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idExame;

	@Basic(optional = false)
	@Column(name="exame", nullable=false, length = 150)
	private String exame;
	
	public Exame() {
		super();
	}

	public Exame(Integer idExame) {
		super();
		this.idExame = idExame;
	}

	public Integer getIdExame() {
		return idExame;
	}

	public void setIdExame(Integer idExame) {
		this.idExame = idExame;
	}

	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idExame == null) ? 0 : idExame.hashCode());
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
		Exame other = (Exame) obj;
		if (idExame == null) {
			if (other.idExame != null)
				return false;
		} else if (!idExame.equals(other.idExame))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Exame [idExame=" + idExame + "]";
	}	
}
