package br.com.puc.tcc.dominio;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Table(name="paciente")
@Entity
public class Paciente implements Serializable{

	@Id
    @Basic(optional = false)
    @Column(name = "id_Paciente", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPaciente;
	
	@Basic(optional = false)
    @Column(name = "nome", nullable = false, length = 40)
	private String nome;
	
	@Basic(optional = true)
    @Column(name = "email", nullable = true, length = 50)
	private String email;
	
	@Basic(optional = true)
    @Column(name = "telefone", nullable = true, length = 13)
	private String telefone;
	
	@Basic(optional = true)
    @Column(name = "celular", nullable = true, length = 13)
	private String celular;
	
	@Basic(optional = true)
    @Column(name = "endereco", nullable = true, length = 130)
	private String endereco;
	
	@Basic(optional = false)
    @Column(name = "cartao_sus", nullable = false)
	private String cartaoSus;
	
	@Basic(optional = false)
    @Column(name = "data_nascimento", nullable = false)
	private Date dataNascimento;
	
	@Basic(optional = false)
    @Column(name = "sexo", nullable = false)
	private Integer sexo;
	
	public Paciente() {
		super();
	}

	public Paciente(Integer idPaciente) {
		super();
		this.idPaciente = idPaciente;
	}

	public Integer getIdPaciente() {
		return idPaciente;
	}

	public void setIdPaciente(Integer idPaciente) {
		this.idPaciente = idPaciente;
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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCartaoSus() {
		return cartaoSus;
	}

	public void setCartaoSus(String cartaoSus) {
		this.cartaoSus = cartaoSus;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getSexo() {
		return sexo;
	}

	public void setSexo(Integer sexo) {
		this.sexo = sexo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPaciente == null) ? 0 : idPaciente.hashCode());
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
		Paciente other = (Paciente) obj;
		if (idPaciente == null) {
			if (other.idPaciente != null)
				return false;
		} else if (!idPaciente.equals(other.idPaciente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paciente [idPaciente=" + idPaciente + "]";
	}
}
