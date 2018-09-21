package br.com.puc.tcc.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.puc.tcc.dominio.retorno.DadosEstatistica;
import br.com.puc.tcc.dominio.retorno.DadosGrafico;
import br.com.puc.tcc.dominio.retorno.SerieEstatistica;
import br.com.puc.tcc.integracao.spec.ConsultaMedicaDao;
import br.com.puc.tcc.integracao.spec.ResultadoExameDao;
import br.com.puc.tcc.service.EstatisticaService;
import br.com.puc.tcc.util.Retorno;

@Service
public class EstatisticaServiceImpl implements EstatisticaService {

	@Autowired
	private ConsultaMedicaDao consultaMedicaDao;
	@Autowired
	private ResultadoExameDao resultadoExameDao;

	@Override
	public DadosGrafico getDados() {
		List<DadosEstatistica> listaConsultas = consultaMedicaDao.getEstatisticaConsultasMedicas();
		List<DadosEstatistica> listaResultados = resultadoExameDao.getEstatisticaResultadoExames();
		
		List<String> serieConsulta = new ArrayList<>();
		List<String> serieResultados = new ArrayList<>();
		List<SerieEstatistica> series = new ArrayList<>();
		
		
		Integer contConsulta = 0;
		for(int i = 0; i < listaConsultas.size(); i++) {
			if(i < listaConsultas.size() - 1) {
				if(listaConsultas.get(i).getProfissional().equals(listaConsultas.get(i + 1).getProfissional())) {
					contConsulta++;
				}else {
					contConsulta++;
					serieConsulta.add(contConsulta + "|" + Retorno.getEspecialidade(listaConsultas.get(i).getProfissional()));
					contConsulta = 0;
					
				}
			}else {
				contConsulta++;
				serieConsulta.add(contConsulta + "|" + Retorno.getEspecialidade(listaConsultas.get(i).getProfissional()));
				contConsulta = 0;
			}
		}
		
		Integer contResultado = 0;
		for(int i = 0; i < listaResultados.size(); i++) {
			if(i < listaResultados.size() - 1) {
				if(listaResultados.get(i).getProfissional().equals(listaResultados.get(i + 1).getProfissional())) {
					contResultado++;
				}else {
					contResultado++;
					serieResultados.add(contResultado + "|" + Retorno.getEspecialidade(listaResultados.get(i).getProfissional()));
					contResultado = 0;
					
				}
			}else {
				contResultado++;
				serieResultados.add(contResultado + "|" + Retorno.getEspecialidade(listaResultados.get(i).getProfissional()));
				contResultado = 0;
			}
		}
		
		SerieEstatistica se = new SerieEstatistica();
		se.setConsultas(serieConsulta);
		se.setResultados(serieResultados);
		series.add(se);
		DadosGrafico dg = new DadosGrafico();
		dg.setDados(series);
		return dg;
		
	}
}
