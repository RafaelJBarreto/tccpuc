package br.com.puc.tcc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.dominio.Paciente;
import br.com.puc.tcc.integracao.spec.PacienteDao;
import br.com.puc.tcc.service.PacienteService;

@Service
public class PacienteServiceImpl implements PacienteService {

	@Autowired
	private PacienteDao pacienteDao;

	@Override
	public void salvarPaciente(Paciente paciente) {
		pacienteDao.salvarPaciente(paciente);
	}

	@Override
	public void editarPaciente(Paciente paciente) {
		pacienteDao.editarPaciente(paciente);
	}

	@Override
	public void deletarPaciente(Integer idPaciente) {
		pacienteDao.deletarPaciente(idPaciente);
	}

	@Override
	public List<Paciente> getPacientes() {
		return pacienteDao.getPacientes();
	}

	@Override
	public Paciente getPaciente(Integer idPaciente) {
		return pacienteDao.getPaciente(idPaciente);
	}
}
