package br.com.puc.tcc.integracao.spec;

import java.util.List;

import br.com.puc.tcc.dominio.Medicamento;

public interface MedicamentoDao {
	
	void salvarMedicamento(Medicamento medicamento);
	
	void editarMedicamento(Medicamento medicamento);
	
	void deletarMedicamento(Integer idMedicamento);
	
	List<Medicamento> getMedicamentos();
	
	Medicamento getMedicamento(Integer idMedicamento);
}
