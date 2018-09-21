package br.com.puc.tcc.integracao.spec;

import java.util.List;

import br.com.puc.tcc.dominio.Profissional;

public interface ProfissionalDao {
	
	void salvarProfissional(Profissional profissional);
	
	void editarProfissional(Profissional profissional);
	
	void deletarProfissional(Integer idProfissional);
	
	List<Profissional> getProfissionais();
	
	Profissional getProfissional(Integer idProfissional);
	
	List<Profissional> getMedicos();
	
	Profissional login(Profissional profissional);
	
	Profissional getProfissionalSenha(Integer idProfissional, String senha);
}
