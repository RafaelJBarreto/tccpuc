package br.com.puc.tcc.integracao.spec;

import java.util.List;

import br.com.puc.tcc.dominio.PedidoExame;
import br.com.puc.tcc.dominio.retorno.PedidoExameRetorno;

public interface PedidoExameDao {
	
	void salvarPedidoExame(PedidoExame pedidoExame);
	
	void editarPedidoExame(Integer idConsulta);
	
	void deletarPedidoExame(Integer idConsulta);
	
	List<PedidoExame> getPedidoExames();
	
	PedidoExameRetorno getPedidoExame(Integer idConsulta);
	
	List<PedidoExame> getPedidoExamesPorProfissional(Integer idProfissional);
}
