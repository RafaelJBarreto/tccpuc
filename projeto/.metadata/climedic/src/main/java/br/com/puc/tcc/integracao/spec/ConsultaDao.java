package br.com.puc.tcc.integracao.spec;

import java.util.List;

import br.com.puc.tcc.dominio.Consulta;
import br.com.puc.tcc.dominio.Paciente;

public interface ConsultaDao {
	
	void salvarConsulta(Consulta consulta);
	
	void editarConsulta(Consulta consulta);
	
	void cancelarConsulta(Integer idConsulta);
	
	List<Consulta> getConsultas();
	
	Consulta getConsulta(Integer idConsulta);
	
	List<Consulta> getConsultasValidas();
	
	List<Consulta> getConsultasProfissional(Integer idProfissional);
	
	List<Paciente> getPacientesProfissional(Integer idProfissional);
}
