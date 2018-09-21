package br.com.puc.tcc.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.dominio.Exame;
import br.com.puc.tcc.dominio.PedidoExame;
import br.com.puc.tcc.dominio.retorno.ExameRetorno;
import br.com.puc.tcc.dominio.retorno.PedidoExameRetorno;
import br.com.puc.tcc.integracao.spec.PedidoExameDao;
import br.com.puc.tcc.service.PedidoExameService;

@Service
public class PedidoExameServiceImpl implements PedidoExameService {

	@Autowired
	private PedidoExameDao pedidoExameDao;

	@Override
	public void salvarPedidoExame(PedidoExame pedidoExame) {
		pedidoExameDao.salvarPedidoExame(pedidoExame);
	}

	@Override
	public void editarPedidoExame(Integer idConsulta) {
		pedidoExameDao.editarPedidoExame(idConsulta);
	}

	@Override
	public void deletarPedidoExame(Integer idConsulta) {
		pedidoExameDao.deletarPedidoExame(idConsulta);
	}

	@Override
	public List<PedidoExameRetorno> getPedidoExames() {
		List<PedidoExame> pedidos = pedidoExameDao.getPedidoExames();
		
		List<PedidoExameRetorno> resultado = new ArrayList<>();
		List<ExameRetorno> listaExames = new ArrayList<>();
		
		for(int i=0; i<pedidos.size(); i++) {
			if(i < pedidos.size() - 1) {
				if(pedidos.get(i).getConsulta().getIdConsulta().equals(pedidos.get(i + 1).getConsulta().getIdConsulta())) {
					ExameRetorno er = new ExameRetorno();
					Exame e = new Exame();
					e.setIdExame(pedidos.get(i).getExame().getIdExame());
					e.setExame(pedidos.get(i).getExame().getExame());
					er.setExame(e);
					er.setSelected(true);
					listaExames.add(er);
				}else {
					PedidoExameRetorno per = new PedidoExameRetorno();
					per.setNomePaciente(pedidos.get(i).getConsulta().getNomePaciente());
					per.setConsulta(pedidos.get(i).getConsulta());
					ExameRetorno er = new ExameRetorno();
					Exame e = new Exame();
					e.setIdExame(pedidos.get(i).getExame().getIdExame());
					e.setExame(pedidos.get(i).getExame().getExame());
					er.setExame(e);
					er.setSelected(true);
					listaExames.add(er);
					per.setListaExames(listaExames);
					resultado.add(per);
					listaExames = new ArrayList<>();
				}
			}else {
				PedidoExameRetorno per = new PedidoExameRetorno();
				per.setNomePaciente(pedidos.get(i).getConsulta().getNomePaciente());
				per.setConsulta(pedidos.get(i).getConsulta());
				ExameRetorno er = new ExameRetorno();
				Exame e = new Exame();
				e.setIdExame(pedidos.get(i).getExame().getIdExame());
				e.setExame(pedidos.get(i).getExame().getExame());
				er.setExame(e);
				er.setSelected(true);
				listaExames.add(er);
				per.setListaExames(listaExames);
				resultado.add(per);
				listaExames = new ArrayList<>();
			}
		}
		
		return resultado;
	}

	@Override
	public PedidoExameRetorno getPedidoExame(Integer idConsulta) {
		return pedidoExameDao.getPedidoExame(idConsulta);
	}

	@Override
	public List<PedidoExameRetorno> getPedidoExamesPorProfissional(Integer idProfissional) {
		List<PedidoExame> pedidos = pedidoExameDao.getPedidoExamesPorProfissional(idProfissional);
		
		List<PedidoExameRetorno> resultado = new ArrayList<>();
		List<ExameRetorno> listaExames = new ArrayList<>();
		
		for(int i=0; i<pedidos.size(); i++) {
			if(i < pedidos.size() - 1) {
				if(pedidos.get(i).getConsulta().getIdConsulta().equals(pedidos.get(i + 1).getConsulta().getIdConsulta())) {
					ExameRetorno er = new ExameRetorno();
					Exame e = new Exame();
					e.setIdExame(pedidos.get(i).getExame().getIdExame());
					e.setExame(pedidos.get(i).getExame().getExame());
					er.setExame(e);
					er.setSelected(true);
					listaExames.add(er);
				}else {
					PedidoExameRetorno per = new PedidoExameRetorno();
					per.setNomePaciente(pedidos.get(i).getConsulta().getNomePaciente());
					per.setConsulta(pedidos.get(i).getConsulta());
					ExameRetorno er = new ExameRetorno();
					Exame e = new Exame();
					e.setIdExame(pedidos.get(i).getExame().getIdExame());
					e.setExame(pedidos.get(i).getExame().getExame());
					er.setExame(e);
					er.setSelected(true);
					listaExames.add(er);
					per.setListaExames(listaExames);
					resultado.add(per);
					listaExames = new ArrayList<>();
				}
			}else {
				PedidoExameRetorno per = new PedidoExameRetorno();
				per.setNomePaciente(pedidos.get(i).getConsulta().getNomePaciente());
				per.setConsulta(pedidos.get(i).getConsulta());
				ExameRetorno er = new ExameRetorno();
				Exame e = new Exame();
				e.setIdExame(pedidos.get(i).getExame().getIdExame());
				e.setExame(pedidos.get(i).getExame().getExame());
				er.setExame(e);
				er.setSelected(true);
				listaExames.add(er);
				per.setListaExames(listaExames);
				resultado.add(per);
				listaExames = new ArrayList<>();
			}
		}
		
		return resultado;
	}
}
