package br.com.puc.tcc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.tcc.dominio.Consulta;
import br.com.puc.tcc.dominio.Exame;
import br.com.puc.tcc.dominio.PedidoExame;
import br.com.puc.tcc.dominio.retorno.ExameRetorno;
import br.com.puc.tcc.dominio.retorno.PedidoExameRetorno;
import br.com.puc.tcc.service.ConsultaService;
import br.com.puc.tcc.service.ExameService;
import br.com.puc.tcc.service.PedidoExameService;
import br.com.puc.tcc.util.Retorno;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("service")
public class PedidoExameController {

	@Autowired
	private PedidoExameService pedidoExameService;
	@Autowired
	private ExameService exameService;
	@Autowired
	private ConsultaService consultaService;
	
	@RequestMapping(value="/exames/", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Exame> getExames(){
		return this.exameService.getExames();
	}
	
	@RequestMapping(value="/pedidosexames/todos/", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<PedidoExameRetorno> getPedidosExames(){
		return this.pedidoExameService.getPedidoExames();
	}
	
	@RequestMapping(value="/pedidosexames/profissional/{idProfissional}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<PedidoExameRetorno> getPedidosExamesPorProfissional(@PathVariable("idProfissional") Integer idProfissional){
		return this.pedidoExameService.getPedidoExamesPorProfissional(idProfissional);
	}
	
	@RequestMapping(value="/pedidoexame/novo", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno salvar(@RequestBody PedidoExameRetorno pedidoExameRetorno){
		try {
			this.pedidoExameService.deletarPedidoExame(pedidoExameRetorno.getConsulta().getIdConsulta());
			for(ExameRetorno per: pedidoExameRetorno.getListaExames()) {
				Exame e = new Exame(per.getExame().getIdExame());
				Consulta c = new Consulta(pedidoExameRetorno.getConsulta().getIdConsulta());
				PedidoExame pe = new PedidoExame();
				pe.setExame(e);
				pe.setConsulta(c);
				pedidoExameService.salvarPedidoExame(pe);
			}
			
			Consulta c = pedidoExameRetorno.getConsulta();
			c.setPedidoRealizado(true);
			consultaService.editarConsulta(c);
			
			return new Retorno(1, "Pedido de exame cadastrado com sucesso!!!");
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar cadastrar o pedido de exame!!!");			
		}
	}
	
	@RequestMapping(value="/pedidoExame/atualizar", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno atualizar(@RequestBody PedidoExameRetorno pedidoExameRetorno){
 
		try {
			this.pedidoExameService.deletarPedidoExame(pedidoExameRetorno.getConsulta().getIdConsulta());
			for(ExameRetorno per: pedidoExameRetorno.getListaExames()) {
				Exame e = new Exame(per.getExame().getIdExame());
				Consulta c = new Consulta(pedidoExameRetorno.getConsulta().getIdConsulta());
				PedidoExame pe = new PedidoExame();
				pe.setExame(e);
				pe.setConsulta(c);
				pedidoExameService.salvarPedidoExame(pe);
			}
			return new Retorno(1,"Pedido de exame atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new Retorno(0,"Erro ao tentar editar o pedido de exame!");
		}
	}
	
	@RequestMapping(value="/pedidoExame/dados/{idConsulta}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody PedidoExameRetorno getPedidoExame(@PathVariable("idConsulta") Integer idConsulta){
		return this.pedidoExameService.getPedidoExame(idConsulta);
	}
 
	@RequestMapping(value="/pedidoExame/deletar/{idConsulta}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno excluir(@PathVariable("idConsulta") Integer idConsulta){
 
		try {
			this.pedidoExameService.deletarPedidoExame(idConsulta);
			return new Retorno(1, "Pedido de exame deletado com sucesso!");
 
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar deletar o pedido de exame!");
		}
	}
}
