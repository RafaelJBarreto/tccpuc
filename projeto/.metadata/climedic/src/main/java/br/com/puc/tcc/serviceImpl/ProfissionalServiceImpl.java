package br.com.puc.tcc.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.dominio.Profissional;
import br.com.puc.tcc.integracao.spec.ProfissionalDao;
import br.com.puc.tcc.service.ProfissionalService;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {

	@Autowired
	private ProfissionalDao profissionalDao;

	@Override
	public void salvarProfissional(Profissional profissional) {
		profissionalDao.salvarProfissional(profissional);
	}

	@Override
	public void editarProfissional(Profissional profissional) {
		profissionalDao.editarProfissional(profissional);
	}

	@Override
	public void deletarProfissional(Integer idProfissional) {
		profissionalDao.deletarProfissional(idProfissional);
	}

	@Override
	public List<Profissional> getProfissionais() {
		return profissionalDao.getProfissionais();
	}

	@Override
	public Profissional getProfissional(Integer idProfissional) {
		return profissionalDao.getProfissional(idProfissional);
	}

	@Override
	public List<Profissional> getMedicos() {
		return profissionalDao.getMedicos();
	}

	@Override
	public Profissional login(Profissional profissional) {
		return profissionalDao.login(profissional);
	}

	@Override
	public Profissional getProfissionalSenha(Integer idProfissional, String senha) {
		return profissionalDao.getProfissionalSenha(idProfissional, senha);
	}

}
