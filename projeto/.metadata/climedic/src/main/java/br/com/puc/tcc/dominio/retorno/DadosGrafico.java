package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class DadosGrafico implements Serializable {

	private List<SerieEstatistica> dados;

	public List<SerieEstatistica> getDados() {
		return dados;
	}

	public void setDados(List<SerieEstatistica> dados) {
		this.dados = dados;
	}
}
