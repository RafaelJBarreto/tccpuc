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
import br.com.puc.tcc.dominio.ConsultaMedica;
import br.com.puc.tcc.dominio.Medicamento;
import br.com.puc.tcc.dominio.retorno.ConsultaMedicaRetorno;
import br.com.puc.tcc.dominio.retorno.MedicamentoConsulta;
import br.com.puc.tcc.service.ConsultaMedicaService;
import br.com.puc.tcc.service.ConsultaService;
import br.com.puc.tcc.util.Retorno;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("service")
public class ConsultaMedicaController {

	@Autowired
	private ConsultaMedicaService consultaMedicaService;
	@Autowired
	private ConsultaService consultaService;
		
	@RequestMapping(value="/consultasmedicas/profissional/{idProfissional}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<ConsultaMedicaRetorno> getConsultasMedicaPorProfissional(@PathVariable("idProfissional") Integer idProfissional){
		return this.consultaMedicaService.getConsultaMedicasPorProfissional(idProfissional);
	}
	
	@RequestMapping(value="/consultamedica/novo", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno salvar(@RequestBody ConsultaMedicaRetorno consultaMedicaRetorno){
		try {
			
			for(MedicamentoConsulta mc: consultaMedicaRetorno.getListaMedicamentos()) {
				ConsultaMedica cm = new ConsultaMedica();
				cm.setConsulta(new Consulta(consultaMedicaRetorno.getConsulta().getIdConsulta()));
				cm.setMedicamento(new Medicamento(mc.getMedicamento().getIdMedicamento()));
				cm.setPrescricao(mc.getPrescricao());
				cm.setQuantidade(mc.getQuantidade());
				cm.setDiagnosticoConsulta(consultaMedicaRetorno.getDiagnosticoConsulta());
				this.consultaMedicaService.salvarConsultaMedica(cm);
			}
			Consulta c = consultaMedicaRetorno.getConsulta();
			c.setRealizada(true);
			consultaService.editarConsulta(c);
			return new Retorno(1, "Consulta Médica cadastrada com sucesso!!!");
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar cadastrar Consulta Médica!!!");			
		}
	}
	
	@RequestMapping(value="/consultamedica/atualizar", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno atualizar(@RequestBody ConsultaMedicaRetorno consultaMedicaRetorno){
 
		try {
			this.consultaMedicaService.deletarConsultaMedica(consultaMedicaRetorno.getConsulta().getIdConsulta());
			for(MedicamentoConsulta mc: consultaMedicaRetorno.getListaMedicamentos()) {
				ConsultaMedica cm = new ConsultaMedica();
				cm.setConsulta(new Consulta(consultaMedicaRetorno.getConsulta().getIdConsulta()));
				cm.setMedicamento(new Medicamento(mc.getMedicamento().getIdMedicamento()));
				cm.setPrescricao(mc.getPrescricao());
				cm.setQuantidade(mc.getQuantidade());
				cm.setDiagnosticoConsulta(consultaMedicaRetorno.getDiagnosticoConsulta());
				this.consultaMedicaService.salvarConsultaMedica(cm);
			}
			Consulta c = consultaMedicaRetorno.getConsulta();
			c.setRealizada(true);
			consultaService.editarConsulta(c);
			return new Retorno(1,"Consulta Médica atualizada com sucesso!");
 
		}catch(Exception e) {
 
			return new Retorno(0,"Erro ao tentar editar Consulta Médica!");
		}
	}
	
	@RequestMapping(value="/consultamedica/dados/{idConsulta}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ConsultaMedicaRetorno getConsultaMedica(@PathVariable("idConsulta") Integer idConsulta){
		return this.consultaMedicaService.getConsultaMedica(idConsulta);
	}
 
	@RequestMapping(value="/consultamedica/deletar/{idConsultaMedica}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno excluir(@PathVariable("idConsultaMedica") Integer idConsultaMedica){
 
		try {
			this.consultaMedicaService.deletarConsultaMedica(idConsultaMedica);
			return new Retorno(1, "Consulta Médica removida com sucesso!");
 
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar remover a Consulta Médica!");
		}
	}
}
