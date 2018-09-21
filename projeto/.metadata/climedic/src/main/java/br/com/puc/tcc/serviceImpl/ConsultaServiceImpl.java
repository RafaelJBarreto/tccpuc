package br.com.puc.tcc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.dominio.Consulta;
import br.com.puc.tcc.dominio.Paciente;
import br.com.puc.tcc.integracao.spec.ConsultaDao;
import br.com.puc.tcc.service.ConsultaService;

@Service
public class ConsultaServiceImpl implements ConsultaService {

	@Autowired
	private ConsultaDao consultaDao;

	@Override
	public void salvarConsulta(Consulta consulta) {
		consultaDao.salvarConsulta(consulta);
	}

	@Override
	public void editarConsulta(Consulta consulta) {
		consultaDao.editarConsulta(consulta);
	}

	@Override
	public void cancelarConsulta(Integer idConsulta) {
		consultaDao.cancelarConsulta(idConsulta);
	}

	@Override
	public List<Consulta> getConsultas() {
		return consultaDao.getConsultas();
	}

	@Override
	public Consulta getConsulta(Integer idConsulta) {
		return consultaDao.getConsulta(idConsulta);
	}

	@Override
	public List<Consulta> getConsultasValidas() {
		return consultaDao.getConsultasValidas();
	}

	@Override
	public List<Consulta> getConsultasProfissional(Integer idProfissional) {
		return consultaDao.getConsultasProfissional(idProfissional);
	}

	@Override
	public List<Paciente> getPacientesProfissional(Integer idProfissional) {
		return consultaDao.getPacientesProfissional(idProfissional);
	}
}
