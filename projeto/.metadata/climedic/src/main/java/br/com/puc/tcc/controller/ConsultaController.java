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
import br.com.puc.tcc.dominio.Paciente;
import br.com.puc.tcc.service.ConsultaService;
import br.com.puc.tcc.util.Retorno;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("service")
public class ConsultaController {

	@Autowired
	private ConsultaService consultaService;
	
	@RequestMapping(value="/pacientes/profissional/{idProfissional}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Paciente> getPacientesProfissional(@PathVariable("idProfissional") Integer idProfissional){
		return this.consultaService.getPacientesProfissional(idProfissional);
	}
	
	@RequestMapping(value="/consultas", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Consulta> getConsultas(){
		return this.consultaService.getConsultas();
	}
	
	@RequestMapping(value="/consultas/validas", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Consulta> getConsultasValidas(){
		return this.consultaService.getConsultasValidas();
	}
	
	@RequestMapping(value="/consultas/profissional/{idProfissional}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Consulta> getConsultaPorProfissional(@PathVariable("idProfissional") Integer idProfissional){
		return this.consultaService.getConsultasProfissional(idProfissional);
	}
	
	@RequestMapping(value="/consulta/novo", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno salvar(@RequestBody Consulta consulta){
		try {
			consulta.setCancelada(false);
			this.consultaService.salvarConsulta(consulta);
			return new Retorno(1, "Consulta cadastrada com sucesso!!!");
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar cadastrar Consulta!!!");			
		}
	}
	
	@RequestMapping(value="/consulta/atualizar", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno atualizar(@RequestBody Consulta consulta){
 
		try {
			consulta.setCancelada(false);
			this.consultaService.editarConsulta(consulta);
			return new Retorno(1,"Consulta atualizada com sucesso!");
 
		}catch(Exception e) {
 
			return new Retorno(0,"Erro ao tentar editar Consulta!");
		}
	}
	
	@RequestMapping(value="/consulta/{idConsulta}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Consulta getConsulta(@PathVariable("idConsulta") Integer idConsulta){
		return this.consultaService.getConsulta(idConsulta);
	}
 
	@RequestMapping(value="/consulta/cancelar/{idConsulta}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno cancelar(@PathVariable("idConsulta") Integer idConsulta){
 
		try {
			this.consultaService.cancelarConsulta(idConsulta);
			return new Retorno(1, "Consulta cancelada com sucesso!");
 
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar cancelar a Consulta!");
		}
	}
}
