package br.com.puc.tcc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.tcc.dominio.retorno.DadosGrafico;
import br.com.puc.tcc.service.EstatisticaService;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("service")
public class EstatisticaController {

	@Autowired
	private EstatisticaService estatisticaService;
	
	@RequestMapping(value="/estatistica", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody DadosGrafico getDados(){
		return this.estatisticaService.getDados();
	}
}
