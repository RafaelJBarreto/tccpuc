package br.com.puc.tcc.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.puc.tcc.dominio.Paciente;
import br.com.puc.tcc.dominio.retorno.Prontuario;
import br.com.puc.tcc.dominio.retorno.ProntuarioDetails;
import br.com.puc.tcc.service.ConsultaMedicaService;
import br.com.puc.tcc.service.PacienteService;

@CrossOrigin(origins  = "http://localhost:4200")
@RestController
@RequestMapping("service")
public class ProntuarioController {

	@Autowired
	private ConsultaMedicaService consultaMedicaService;
	@Autowired
	private PacienteService pacienteService;
	
	@RequestMapping(value="/prontuario/{idPaciente}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Prontuario getProntuario(@PathVariable("idPaciente") Integer idPaciente){
		Paciente paciente = pacienteService.getPaciente(idPaciente);
		
		List<ProntuarioDetails> detalhes = consultaMedicaService.getConsultaMedicasPorPaciente(idPaciente);
		
		detalhes = detalhes.stream().sorted(Comparator.comparing(ProntuarioDetails::getDataConsulta)).collect(Collectors.toList());
		
		Prontuario prontuario = new Prontuario();
		prontuario.setPaciente(paciente);
		prontuario.setLista(detalhes);
		return prontuario;
	}
}
