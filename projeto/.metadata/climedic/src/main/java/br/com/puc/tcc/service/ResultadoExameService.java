package br.com.puc.tcc.service;

import java.util.List;

import br.com.puc.tcc.dominio.ResultadoExame;
import br.com.puc.tcc.dominio.retorno.ResultadoExameRetorno;

public interface ResultadoExameService {
	
	void salvarResultadoExame(ResultadoExame resultadoExame);
	
	void editarResultadoExame(ResultadoExame resultadoExame);
	
	void deletarResultadoExame(Integer idConsulta);
	
	List<ResultadoExameRetorno> getResultadoExames();
	
	ResultadoExameRetorno getResultadoExame(Integer idConsulta);
	
	List<ResultadoExameRetorno> getResultadoExamesPorProfissional(Integer idProfissional);
}
