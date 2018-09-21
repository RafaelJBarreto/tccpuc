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

import br.com.puc.tcc.dominio.Profissional;
import br.com.puc.tcc.service.ProfissionalService;
import br.com.puc.tcc.util.Retorno;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("service")
public class ProfissionalController {

	@Autowired
	private ProfissionalService profissionalService;
	
	@RequestMapping(value="/profissionais", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Profissional> getProfissionais(){
		return this.profissionalService.getProfissionais();
	}
	
	@RequestMapping(value="/medicos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<Profissional> getMedicos(){
		return this.profissionalService.getMedicos();
	}
	
	@RequestMapping(value="/profissional/novo", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno salvar(@RequestBody Profissional profissional){
		try {
			profissional.setSenha(Retorno.getSenhaBase64(profissional.getSenha()));
			this.profissionalService.salvarProfissional(profissional);
			return new Retorno(1, "Profissional cadastrado com sucesso!!!");
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar cadastrar profissional!!!");			
		}
	}
	
	@RequestMapping(value="/profissional/atualizar", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno atualizar(@RequestBody Profissional profissional){
 
		try {
			profissional.setSenha(Retorno.getSenhaBase64(profissional.getSenha()));
			this.profissionalService.editarProfissional(profissional);
			return new Retorno(1,"Profissional atualizado com sucesso!");
 
		}catch(Exception e) {
 
			return new Retorno(0,"Erro ao tentar editar profissional!");
		}
	}
	
	@RequestMapping(value="/profissional/{idProfissional}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Profissional getProfissional(@PathVariable("idProfissional") Integer idProfissional){
		return this.profissionalService.getProfissional(idProfissional);
	}
 
	@RequestMapping(value="/profissional/deletar/{idProfissional}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno excluir(@PathVariable("idProfissional") Integer idProfissional){
 
		try {
			this.profissionalService.deletarProfissional(idProfissional);
			return new Retorno(1, "Profissional excluido com sucesso!");
 
		}catch(Exception e) {
			return new Retorno(0, "Erro ao tentar excluir o profissional!");
		}
	}
	
	@RequestMapping(value="/login", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE,produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Profissional login(@RequestBody Profissional profissional){
		profissional.setSenha(Retorno.getSenhaBase64(profissional.getSenha()));
		return this.profissionalService.login(profissional);
	}
	
	@RequestMapping(value="/profissional/atualizarsenha", method = RequestMethod.PUT, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Retorno atualizarSenha(@RequestBody Profissional profissional){
 
		try {
			Profissional prof = this.profissionalService.getProfissionalSenha(profissional.getIdProfissional(), Retorno.getSenhaBase64(profissional.getSenha()));
			if(prof == null) {
				return new Retorno(0,"Por favor confira sua senha atual!");
			}else {
				prof.setSenha(Retorno.getSenhaBase64(profissional.getNovaSenha()));
				this.profissionalService.editarProfissional(prof);
				return new Retorno(1,"Senha atualizada com sucesso!");
			}
 
		}catch(Exception e) {
 
			return new Retorno(0,"Erro ao tentar editar profissional!");
		}
	}
	
}
