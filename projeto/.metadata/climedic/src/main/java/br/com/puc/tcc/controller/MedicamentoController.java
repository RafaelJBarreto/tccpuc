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

import br.com.puc.tcc.dominio.Medicamento;
import br.com.puc.tcc.service.MedicamentoService;
import br.com.puc.tcc.util.Retorno;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("service")
public class MedicamentoController {

	@Autowired
	private MedicamentoService medicamentoService;
	
	@RequestMapping(value="/medicamentos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Medicamento> getMedicamentos(){
		return this.medicamentoService.getMedicamentos();
	}
	
	@RequestMapping(value="/medicamento/novo", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno salvar(@RequestBody Medicamento medicamento){
		try {
			this.medicamentoService.salvarMedicamento(medicamento);
			return new Retorno(1, "Medicamento cadastrado com sucesso!!!");
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar cadastrar Medicamento!!!");			
		}
	}
	
	@RequestMapping(value="/medicamento/atualizar", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno atualizar(@RequestBody Medicamento medicamento){
 
		try {
			this.medicamentoService.editarMedicamento(medicamento);
			return new Retorno(1,"Medicamento atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new Retorno(0,"Erro ao tentar editar Medicamento!");
		}
	}
	
	@RequestMapping(value="/medicamento/{idMedicamento}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Medicamento getMedicamento(@PathVariable("idMedicamento") Integer idMedicamento){
		return this.medicamentoService.getMedicamento(idMedicamento);
	}
 
	@RequestMapping(value="/medicamento/deletar/{idMedicamento}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno excluir(@PathVariable("idMedicamento") Integer idMedicamento){
 
		try {
			this.medicamentoService.deletarMedicamento(idMedicamento);
			return new Retorno(1, "Medicamento excluido com sucesso!");
 
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar excluir o Medicamento!");
		}
	}
}
