package br.com.puc.tcc.dominio.retorno;

import java.io.Serializable;

import br.com.puc.tcc.dominio.Exame;

@SuppressWarnings("serial")
public class ExameRetorno implements Serializable{

	private Exame exame;
	private boolean selected;
	public Exame getExame() {
		return exame;
	}
	public void setExame(Exame exame) {
		this.exame = exame;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
