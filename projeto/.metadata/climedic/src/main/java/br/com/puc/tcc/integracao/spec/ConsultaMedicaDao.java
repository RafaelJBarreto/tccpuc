package br.com.puc.tcc.integracao.spec;

import java.util.List;

import br.com.puc.tcc.dominio.ConsultaMedica;
import br.com.puc.tcc.dominio.retorno.ConsultaMedicaRetorno;
import br.com.puc.tcc.dominio.retorno.DadosEstatistica;

public interface ConsultaMedicaDao {
	
	void salvarConsultaMedica(ConsultaMedica consultaMedica);
	
	void editarConsultaMedica(ConsultaMedica consultaMedica);
	
	void deletarConsultaMedica(Integer idConsultaMedica);
	
	ConsultaMedicaRetorno getConsultaMedica(Integer idConsulta);
	
	List<ConsultaMedica> getConsultaMedicasPorProfissional(Integer idProfissional);
	
	List<ConsultaMedica> getConsultaMedicasPorPaciente(Integer idPaciente);
	
	List<DadosEstatistica> getEstatisticaConsultasMedicas();
}
