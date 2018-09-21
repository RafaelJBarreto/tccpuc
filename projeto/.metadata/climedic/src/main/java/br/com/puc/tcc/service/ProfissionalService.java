package br.com.puc.tcc.service;

import java.util.List;

import br.com.puc.tcc.dominio.Profissional;

public interface ProfissionalService {

	void salvarProfissional(Profissional profissional);
	
	void editarProfissional(Profissional profissional);
	
	void deletarProfissional(Integer idProfissional);
	
	List<Profissional> getProfissionais();
	
	Profissional getProfissional(Integer idProfissional);
	
	List<Profissional> getMedicos();
	
	Profissional login(Profissional profissional);
	
	Profissional getProfissionalSenha(Integer idProfissional, String senha);
}
