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

import br.com.puc.tcc.dominio.Paciente;
import br.com.puc.tcc.service.PacienteService;
import br.com.puc.tcc.util.Retorno;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("service")
public class PacienteController {

	@Autowired
	private PacienteService pacienteService;
	
	@RequestMapping(value="/pacientes", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Paciente> getPacientes(){
		return this.pacienteService.getPacientes();
	}
	
	@RequestMapping(value="/paciente/novo", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno salvar(@RequestBody Paciente paciente){
		try {
			this.pacienteService.salvarPaciente(paciente);
			return new Retorno(1, "Paciente cadastrado com sucesso!!!");
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar cadastrar Paciente!!!");			
		}
	}
	
	@RequestMapping(value="/paciente/atualizar", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno atualizar(@RequestBody Paciente paciente){
 
		try {
			this.pacienteService.editarPaciente(paciente);
			return new Retorno(1,"Paciente atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new Retorno(0,"Erro ao tentar editar Paciente!");
		}
	}
	
	@RequestMapping(value="/paciente/{idPaciente}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Paciente getPaciente(@PathVariable("idPaciente") Integer idPaciente){
		return this.pacienteService.getPaciente(idPaciente);
	}
 
	@RequestMapping(value="/paciente/deletar/{idPaciente}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno excluir(@PathVariable("idPaciente") Integer idPaciente){
 
		try {
			this.pacienteService.deletarPaciente(idPaciente);
			return new Retorno(1, "Paciente excluido com sucesso!");
 
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar excluir o Paciente!");
		}
	}
}
