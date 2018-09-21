package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DadosEstatistica implements Serializable{
	
	private Integer id;
	private Integer profissional;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProfissional() {
		return profissional;
	}
	public void setProfissional(Integer profissional) {
		this.profissional = profissional;
	}
	
	

}
