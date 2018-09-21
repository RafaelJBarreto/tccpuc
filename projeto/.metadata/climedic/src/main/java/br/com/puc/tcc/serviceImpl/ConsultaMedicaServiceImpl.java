package br.com.puc.tcc.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.dominio.ConsultaMedica;
import br.com.puc.tcc.dominio.Medicamento;
import br.com.puc.tcc.dominio.ResultadoExame;
import br.com.puc.tcc.dominio.retorno.ProntuarioDetails;
import br.com.puc.tcc.dominio.retorno.ConsultaMedicaRetorno;
import br.com.puc.tcc.dominio.retorno.ExamesResultado;
import br.com.puc.tcc.dominio.retorno.MedicamentoConsulta;
import br.com.puc.tcc.dominio.retorno.ResultadoExameRetorno;
import br.com.puc.tcc.integracao.spec.ConsultaMedicaDao;
import br.com.puc.tcc.integracao.spec.ResultadoExameDao;
import br.com.puc.tcc.service.ConsultaMedicaService;
import br.com.puc.tcc.util.Retorno;

@Service
public class ConsultaMedicaServiceImpl implements ConsultaMedicaService {

	@Autowired
	private ConsultaMedicaDao consultaMedicaDao;
	@Autowired
	private ResultadoExameDao resultadoExameDao;

	@Override
	public void salvarConsultaMedica(ConsultaMedica consultaMedica) {
		consultaMedicaDao.salvarConsultaMedica(consultaMedica);
	}

	@Override
	public void editarConsultaMedica(ConsultaMedica consultaMedica) {
		consultaMedicaDao.editarConsultaMedica(consultaMedica);
	}

	@Override
	public void deletarConsultaMedica(Integer idConsulta) {
		consultaMedicaDao.deletarConsultaMedica(idConsulta);
	}

	@Override
	public ConsultaMedicaRetorno getConsultaMedica(Integer idConsulta) {
		return consultaMedicaDao.getConsultaMedica(idConsulta);
	}

	@Override
	public List<ConsultaMedicaRetorno> getConsultaMedicasPorProfissional(Integer idProfissional) {
		List<ConsultaMedica> consultas = consultaMedicaDao.getConsultaMedicasPorProfissional(idProfissional);
		List<ConsultaMedicaRetorno> resultado = new ArrayList<>();
		List<MedicamentoConsulta> listaMedicamentos = new ArrayList<>();
		
		for(int i=0; i<consultas.size(); i++) {
			if(i < consultas.size() - 1) {
				if(consultas.get(i).getConsulta().getIdConsulta().equals(consultas.get(i + 1).getConsulta().getIdConsulta())) {
					MedicamentoConsulta mc = new MedicamentoConsulta();
					mc.setPrescricao(consultas.get(i).getPrescricao());
					mc.setQuantidade(consultas.get(i).getQuantidade());
					Medicamento m = new Medicamento();
	            	m.setNomeGenerico(consultas.get(i).getMedicamento().getNomeGenerico());
	            	m.setNomeComercial(consultas.get(i).getMedicamento().getNomeComercial());
	            	mc.setMedicamento(m);
	            	listaMedicamentos.add(mc);
				}else {
					MedicamentoConsulta mc = new MedicamentoConsulta();
					mc.setPrescricao(consultas.get(i).getPrescricao());
					mc.setQuantidade(consultas.get(i).getQuantidade());
					Medicamento m = new Medicamento();
	            	m.setNomeGenerico(consultas.get(i).getMedicamento().getNomeGenerico());
	            	m.setNomeComercial(consultas.get(i).getMedicamento().getNomeComercial());
	            	mc.setMedicamento(m);
	            	listaMedicamentos.add(mc);
	            	ConsultaMedicaRetorno cmr = new ConsultaMedicaRetorno();
	            	cmr.setDiagnosticoConsulta(consultas.get(i).getDiagnosticoConsulta());
	            	cmr.setConsulta(consultas.get(i).getConsulta());
	            	cmr.setListaMedicamentos(listaMedicamentos);
	            	cmr.setNomePaciente(consultas.get(i).getConsulta().getNomePaciente());
	            	resultado.add(cmr);
	            	listaMedicamentos = new ArrayList<>();
				}
			}else {
				MedicamentoConsulta mc = new MedicamentoConsulta();
				mc.setPrescricao(consultas.get(i).getPrescricao());
				mc.setQuantidade(consultas.get(i).getQuantidade());
				Medicamento m = new Medicamento();
            	m.setNomeGenerico(consultas.get(i).getMedicamento().getNomeGenerico());
            	m.setNomeComercial(consultas.get(i).getMedicamento().getNomeComercial());
            	mc.setMedicamento(m);
            	listaMedicamentos.add(mc);
            	ConsultaMedicaRetorno cmr = new ConsultaMedicaRetorno();
            	cmr.setDiagnosticoConsulta(consultas.get(i).getDiagnosticoConsulta());
            	cmr.setConsulta(consultas.get(i).getConsulta());
            	cmr.setListaMedicamentos(listaMedicamentos);
            	cmr.setNomePaciente(consultas.get(i).getConsulta().getNomePaciente());
            	resultado.add(cmr);
			}
		}
		
		return resultado;
	}

	@Override
	public List<ProntuarioDetails> getConsultaMedicasPorPaciente(Integer idPaciente) {
		List<ConsultaMedica> consultas = consultaMedicaDao.getConsultaMedicasPorPaciente(idPaciente);
		List<ProntuarioDetails> resultado = new ArrayList<>();
		List<MedicamentoConsulta> listaMedicamentos = new ArrayList<>();
		
		for(int i=0; i<consultas.size(); i++) {
			if(i < consultas.size() - 1) {
				if(consultas.get(i).getConsulta().getIdConsulta().equals(consultas.get(i + 1).getConsulta().getIdConsulta())) {
					MedicamentoConsulta mc = new MedicamentoConsulta();
					mc.setPrescricao(consultas.get(i).getPrescricao());
					mc.setQuantidade(consultas.get(i).getQuantidade());
					Medicamento m = new Medicamento();
	            	m.setNomeGenerico(consultas.get(i).getMedicamento().getNomeGenerico());
	            	m.setNomeComercial(consultas.get(i).getMedicamento().getNomeComercial());
	            	mc.setMedicamento(m);
	            	listaMedicamentos.add(mc);
				}else {
					MedicamentoConsulta mc = new MedicamentoConsulta();
					mc.setPrescricao(consultas.get(i).getPrescricao());
					mc.setQuantidade(consultas.get(i).getQuantidade());
					Medicamento m = new Medicamento();
	            	m.setNomeGenerico(consultas.get(i).getMedicamento().getNomeGenerico());
	            	m.setNomeComercial(consultas.get(i).getMedicamento().getNomeComercial());
	            	mc.setMedicamento(m);
	            	listaMedicamentos.add(mc);
	            	ProntuarioDetails cmp = new ProntuarioDetails();
	            	cmp.setIdConsulta(consultas.get(i).getConsulta().getIdConsulta());
	            	cmp.setDiagnosticoConsulta(consultas.get(i).getDiagnosticoConsulta());
	            	cmp.setListaMedicamentos(listaMedicamentos);
	            	cmp.setDataConsulta(consultas.get(i).getConsulta().getDataHora());
	            	cmp.setMedico(consultas.get(i).getConsulta().getProfissional().getNome());
	            	cmp.setEspecialidade(Retorno.getEspecialidade(consultas.get(i).getConsulta().getProfissional().getProfissional()));
	            	cmp.setListaResultado(getResultadoExamesPorConsulta(consultas.get(i).getConsulta().getIdConsulta()));
	            	resultado.add(cmp);
	            	listaMedicamentos = new ArrayList<>();
				}
			}else {
				MedicamentoConsulta mc = new MedicamentoConsulta();
				mc.setPrescricao(consultas.get(i).getPrescricao());
				mc.setQuantidade(consultas.get(i).getQuantidade());
				Medicamento m = new Medicamento();
            	m.setNomeGenerico(consultas.get(i).getMedicamento().getNomeGenerico());
            	m.setNomeComercial(consultas.get(i).getMedicamento().getNomeComercial());
            	mc.setMedicamento(m);
            	listaMedicamentos.add(mc);
            	ProntuarioDetails cmp = new ProntuarioDetails();
            	cmp.setIdConsulta(consultas.get(i).getConsulta().getIdConsulta());
            	cmp.setDiagnosticoConsulta(consultas.get(i).getDiagnosticoConsulta());
            	cmp.setListaMedicamentos(listaMedicamentos);
            	cmp.setDataConsulta(consultas.get(i).getConsulta().getDataHora());
            	cmp.setMedico(consultas.get(i).getConsulta().getProfissional().getNome());
            	cmp.setEspecialidade(Retorno.getEspecialidade(consultas.get(i).getConsulta().getProfissional().getProfissional()));
            	cmp.setListaResultado(getResultadoExamesPorConsulta(consultas.get(i).getConsulta().getIdConsulta()));
            	resultado.add(cmp);
            	listaMedicamentos = new ArrayList<>();
			}
		}
		
		return resultado;
	}
	
	
	
	private List<ResultadoExameRetorno> getResultadoExamesPorConsulta(Integer idConsulta) {
		 List<ResultadoExame> lista = resultadoExameDao.getResultadoExamesPorConsulta(idConsulta);
		 List<ExamesResultado> listaResultado = new ArrayList<>();
		 List<ResultadoExameRetorno> resultado = new ArrayList<>();
			
			for(int i = 0; i < lista.size(); i++) {
				if(i < lista.size() - 1) {
					if(lista.get(i).getConsulta().getIdConsulta().equals(lista.get(i + 1).getConsulta().getIdConsulta())) {
						ExamesResultado er = new ExamesResultado();
						er.setExame(lista.get(i).getExame());
						er.setResultado(lista.get(i).getValoresObtidos());
						er.setUnidade(lista.get(i).getUnidade());
						er.setValorReferencia(lista.get(i).getValorReferencial());
						listaResultado.add(er);
					}else {
						ExamesResultado er = new ExamesResultado();
						er.setExame(lista.get(i).getExame());
						er.setResultado(lista.get(i).getValoresObtidos());
						er.setUnidade(lista.get(i).getUnidade());
						er.setValorReferencia(lista.get(i).getValorReferencial());
						listaResultado.add(er);
						
						ResultadoExameRetorno exr = new ResultadoExameRetorno();
						exr.setConsulta(lista.get(i).getConsulta());
						exr.setDataColeta(lista.get(i).getDataColeta());
						exr.setEnviadoMedico(lista.get(i).isEnviadoMedico());
						exr.setEnviadoPaciente(lista.get(i).isEnviadoPaciente());
						exr.setNomePaciente(lista.get(i).getConsulta().getNomePaciente());
						exr.setListaResultado(listaResultado);
						resultado.add(exr);
						listaResultado = new ArrayList<>();
					}
				}else {
					
					ExamesResultado er = new ExamesResultado();
					er.setExame(lista.get(i).getExame());
					er.setResultado(lista.get(i).getValoresObtidos());
					er.setUnidade(lista.get(i).getUnidade());
					er.setValorReferencia(lista.get(i).getValorReferencial());
					listaResultado.add(er);
					
					ResultadoExameRetorno exr = new ResultadoExameRetorno();
					exr.setConsulta(lista.get(i).getConsulta());
					exr.setDataColeta(lista.get(i).getDataColeta());
					exr.setEnviadoMedico(lista.get(i).isEnviadoMedico());
					exr.setEnviadoPaciente(lista.get(i).isEnviadoPaciente());
					exr.setNomePaciente(lista.get(i).getConsulta().getNomePaciente());
					exr.setListaResultado(listaResultado);
					resultado.add(exr);
					listaResultado = new ArrayList<>();
				}
				
			}
			
			return resultado;
	}	
}
