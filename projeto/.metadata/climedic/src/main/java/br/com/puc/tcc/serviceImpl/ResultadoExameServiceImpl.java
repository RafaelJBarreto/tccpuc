package br.com.puc.tcc.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.dominio.ResultadoExame;
import br.com.puc.tcc.dominio.retorno.ExamesResultado;
import br.com.puc.tcc.dominio.retorno.ResultadoExameRetorno;
import br.com.puc.tcc.integracao.spec.ResultadoExameDao;
import br.com.puc.tcc.service.ResultadoExameService;

@Service
public class ResultadoExameServiceImpl implements ResultadoExameService {

	@Autowired
	private ResultadoExameDao resultadoExameDao;

	@Override
	public void salvarResultadoExame(ResultadoExame resultadoExame) {
		resultadoExameDao.salvarResultadoExame(resultadoExame);
	}

	@Override
	public void editarResultadoExame(ResultadoExame resultadoExame) {
		resultadoExameDao.editarResultadoExame(resultadoExame);
	}

	@Override
	public void deletarResultadoExame(Integer idConsulta) {
		resultadoExameDao.deletarResultadoExame(idConsulta);
	}

	@Override
	public List<ResultadoExameRetorno> getResultadoExames() {
		List<ResultadoExame> lista = resultadoExameDao.getResultadoExames();
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

	@Override
	public ResultadoExameRetorno getResultadoExame(Integer idConsulta) {
		return resultadoExameDao.getResultadoExame(idConsulta);
	}

	@Override
	public List<ResultadoExameRetorno> getResultadoExamesPorProfissional(Integer idProfissional) {
		List<ResultadoExame> lista = resultadoExameDao.getResultadoExamesPorProfissional(idProfissional);
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
