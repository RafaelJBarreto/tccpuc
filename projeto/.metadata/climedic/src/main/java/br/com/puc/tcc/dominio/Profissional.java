package br.com.puc.tcc.dominio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Table(name="profissional")
@Entity
public class Profissional implements Serializable{

	@Id
    @Basic(optional = false)
    @Column(name = "id_Profissional", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProfissional;
	
	@Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 40)
	private String nome;
	
	@Basic(optional = false)
    @Column(name = "email", nullable = false, length = 50)
	private String email;
	
	@Basic(optional = true)
    @Column(name = "telefone", nullable = true, length = 13)
	private String telefone;
	
	@Basic(optional = true)
    @Column(name = "celular", nullable = true, length = 13)
	private String celular;
	
	@Basic(optional = false)
    @Column(name = "senha", nullable = false, length = 255)
	private String senha;
	
	@Basic(optional = false)
    @Column(name = "profissional", nullable = false)
	private Integer profissional;
	
	@Basic(optional = true)
    @Column(name = "crm_rms", nullable = true, length = 13)
	private String crm_rms;
	
	@Basic(optional = true)
    @Column(name = "endereco", nullable = true, length = 130)
	private String endereco;
	
	@Transient
	private String novaSenha;
	
	public Profissional() {
		super();
	}

	public Profissional(Integer idProfissional) {
		super();
		this.idProfissional = idProfissional;
	}
	
	public Integer getIdProfissional() {
		return idProfissional;
	}

	public void setIdProfissional(Integer idProfissional) {
		this.idProfissional = idProfissional;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getProfissional() {
		return profissional;
	}

	public void setProfissional(Integer profissional) {
		this.profissional = profissional;
	}

	public String getCrm_rms() {
		return crm_rms;
	}

	public void setCrm_rms(String crm_rms) {
		this.crm_rms = crm_rms;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNovaSenha() {
		return novaSenha;
	}

	public void setNovaSenha(String novaSenha) {
		this.novaSenha = novaSenha;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idProfissional == null) ? 0 : idProfissional.hashCode());
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
		Profissional other = (Profissional) obj;
		if (idProfissional == null) {
			if (other.idProfissional != null)
				return false;
		} else if (!idProfissional.equals(other.idProfissional))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Profissional [idProfissional=" + idProfissional + "]";
	}
}
