package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;

import br.com.puc.tcc.dominio.Exame;

@SuppressWarnings("serial")
public class ExamesResultado implements Serializable{

	private Exame exame;
	private Double resultado;
    private Integer unidade;
    private String valorReferencia;
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	public Double getResultado() {
		return resultado;
	}
	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}
	public Integer getUnidade() {
		return unidade;
	}
	public void setUnidade(Integer unidade) {
		this.unidade = unidade;
	}
	public String getValorReferencia() {
		return valorReferencia;
	}
	public void setValorReferencia(String valorReferencia) {
		this.valorReferencia = valorReferencia;
	}
}
