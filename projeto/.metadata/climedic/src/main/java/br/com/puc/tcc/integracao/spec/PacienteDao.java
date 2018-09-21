package br.com.puc.tcc.integracao.spec;

import java.util.List;

import br.com.puc.tcc.dominio.Paciente;

public interface PacienteDao {
	
	void salvarPaciente(Paciente paciente);
	
	void editarPaciente(Paciente paciente);
	
	void deletarPaciente(Integer idPaciente);
	
	List<Paciente> getPacientes();
	
	Paciente getPaciente(Integer idPaciente);
}
