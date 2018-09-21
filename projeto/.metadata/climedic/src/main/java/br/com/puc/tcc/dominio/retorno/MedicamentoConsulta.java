package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;

import br.com.puc.tcc.dominio.Medicamento;

@SuppressWarnings("serial")
public class MedicamentoConsulta implements Serializable {
	
	private Medicamento medicamento;
	private String prescricao;
	private Integer quantidade;
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
}
