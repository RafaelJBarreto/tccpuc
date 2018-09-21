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
import br.com.puc.tcc.dominio.ResultadoExame;
import br.com.puc.tcc.dominio.retorno.ExamesResultado;
import br.com.puc.tcc.dominio.retorno.ResultadoExameRetorno;
import br.com.puc.tcc.service.PedidoExameService;
import br.com.puc.tcc.service.ResultadoExameService;
import br.com.puc.tcc.util.Retorno;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("service")
public class ResultadoExameController {

	@Autowired
	private ResultadoExameService resultadoExameService;
	@Autowired
	private PedidoExameService pedidoExameService;
	
	@RequestMapping(value="/resultadoexames/todos/", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ResultadoExameRetorno> getResultadoExames(){
		return this.resultadoExameService.getResultadoExames();
	}
	
	@RequestMapping(value="/resultadoexames/profissional/{idProfissional}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ResultadoExameRetorno> getPedidosExamesPorProfissional(@PathVariable("idProfissional") Integer idProfissional){
		return this.resultadoExameService.getResultadoExamesPorProfissional(idProfissional);
	}
	
	@RequestMapping(value="/resultadoexame/novo", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno salvar(@RequestBody ResultadoExameRetorno resultadoExameRetorno){
		try {
			
			for(ExamesResultado re: resultadoExameRetorno.getListaResultado()) {
				ResultadoExame resultado = new ResultadoExame();
				resultado.setConsulta(new Consulta(resultadoExameRetorno.getConsulta().getIdConsulta()));
				resultado.setDataColeta(resultadoExameRetorno.getDataColeta());
				resultado.setEnviadoMedico(resultadoExameRetorno.isEnviadoMedico());
				resultado.setEnviadoPaciente(resultadoExameRetorno.isEnviadoPaciente());
				resultado.setExame(new Exame(re.getExame().getIdExame()));
				resultado.setValoresObtidos(re.getResultado());
				resultado.setUnidade(re.getUnidade());
				resultado.setValorReferencial(re.getValorReferencia());
				this.resultadoExameService.salvarResultadoExame(resultado);
			}
			this.pedidoExameService.editarPedidoExame(resultadoExameRetorno.getConsulta().getIdConsulta());
			return new Retorno(1, "Resultado de exame cadastrado com sucesso!!!");
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar cadastrar o resultado de exame!!!");			
		}
	}
	
	@RequestMapping(value="/resultadoexame/atualizar", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno atualizar(@RequestBody ResultadoExameRetorno resultadoExameRetorno){
 
		try {
			this.resultadoExameService.deletarResultadoExame(resultadoExameRetorno.getConsulta().getIdConsulta());
			for(ExamesResultado re: resultadoExameRetorno.getListaResultado()) {
				ResultadoExame resultado = new ResultadoExame();
				resultado.setConsulta(new Consulta(resultadoExameRetorno.getConsulta().getIdConsulta()));
				resultado.setDataColeta(resultadoExameRetorno.getDataColeta());
				resultado.setEnviadoMedico(resultadoExameRetorno.isEnviadoMedico());
				resultado.setEnviadoPaciente(resultadoExameRetorno.isEnviadoPaciente());
				resultado.setExame(new Exame(re.getExame().getIdExame()));
				resultado.setValoresObtidos(re.getResultado());
				resultado.setUnidade(re.getUnidade());
				resultado.setValorReferencial(re.getValorReferencia());
				this.resultadoExameService.salvarResultadoExame(resultado);
			}
			return new Retorno(1,"Resultado do exame atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new Retorno(0,"Erro ao tentar editar o resultado do exame!");
		}
	}
	
	@RequestMapping(value="/resultadoexame/dados/{idConsulta}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResultadoExameRetorno getPedidoExame(@PathVariable("idConsulta") Integer idConsulta){
		return this.resultadoExameService.getResultadoExame(idConsulta);
	}
 
	@RequestMapping(value="/resultadoexame/deletar/{idConsulta}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno excluir(@PathVariable("idConsulta") Integer idConsulta){
 
		try {
			this.resultadoExameService.deletarResultadoExame(idConsulta);
			return new Retorno(1, "Resultado do exame deletado com sucesso!");
 
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar deletar o resultado do exame!");
		}
	}
}
