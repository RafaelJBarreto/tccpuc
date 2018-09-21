package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class SerieEstatistica implements Serializable {

	private List<String> consultas;
	private List<String> resultados;
	
	public List<String> getConsultas() {
		return consultas;
	}
	public void setConsultas(List<String> consultas) {
		this.consultas = consultas;
	}
	public List<String> getResultados() {
		return resultados;
	}
	public void setResultados(List<String> resultados) {
		this.resultados = resultados;
	}
}
