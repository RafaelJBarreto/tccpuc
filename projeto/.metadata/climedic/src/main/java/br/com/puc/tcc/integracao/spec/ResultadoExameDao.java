package br.com.puc.tcc.integracao.spec;

import java.util.List;

import br.com.puc.tcc.dominio.ResultadoExame;
import br.com.puc.tcc.dominio.retorno.DadosEstatistica;
import br.com.puc.tcc.dominio.retorno.ResultadoExameRetorno;

public interface ResultadoExameDao {
	
	void salvarResultadoExame(ResultadoExame resultadoExame);
	
	void editarResultadoExame(ResultadoExame resultadoExame);
	
	void deletarResultadoExame(Integer idConsulta);
	
	List<ResultadoExame> getResultadoExames();
	
	ResultadoExameRetorno getResultadoExame(Integer idConsulta);
	
	List<ResultadoExame> getResultadoExamesPorProfissional(Integer idProfissional);
	
	List<ResultadoExame> getResultadoExamesPorConsulta(Integer idConsulta);
	
	List<DadosEstatistica> getEstatisticaResultadoExames();
}
