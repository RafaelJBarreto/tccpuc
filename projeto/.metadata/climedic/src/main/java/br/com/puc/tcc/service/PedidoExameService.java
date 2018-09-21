package br.com.puc.tcc.service;

import java.util.List;

import br.com.puc.tcc.dominio.PedidoExame;
import br.com.puc.tcc.dominio.retorno.PedidoExameRetorno;

public interface PedidoExameService {
	
	void salvarPedidoExame(PedidoExame pedidoExame);
	
	void editarPedidoExame(Integer idConsulta);
	
	void deletarPedidoExame(Integer idConsulta);
	
	List<PedidoExameRetorno> getPedidoExames();
	
	PedidoExameRetorno getPedidoExame(Integer idConsulta);
	
	List<PedidoExameRetorno> getPedidoExamesPorProfissional(Integer idProfissional);
}
