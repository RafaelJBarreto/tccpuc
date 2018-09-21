package br.com.puc.tcc.service;

import java.util.List;

import br.com.puc.tcc.dominio.ConsultaMedica;
import br.com.puc.tcc.dominio.retorno.ProntuarioDetails;
import br.com.puc.tcc.dominio.retorno.ConsultaMedicaRetorno;

public interface ConsultaMedicaService {
	
	void salvarConsultaMedica(ConsultaMedica consultaMedica);
	
	void editarConsultaMedica(ConsultaMedica consultaMedica);
	
	void deletarConsultaMedica(Integer idConsulta);
		
	ConsultaMedicaRetorno getConsultaMedica(Integer idConsultaMedica);
	
	List<ConsultaMedicaRetorno> getConsultaMedicasPorProfissional(Integer idProfissional);
	
	List<ProntuarioDetails> getConsultaMedicasPorPaciente(Integer idPaciente);
}
