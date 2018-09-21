package br.com.puc.tcc.integracao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.puc.tcc.dominio.Consulta;
import br.com.puc.tcc.dominio.Exame;
import br.com.puc.tcc.dominio.Paciente;
import br.com.puc.tcc.dominio.Profissional;
import br.com.puc.tcc.dominio.ResultadoExame;
import br.com.puc.tcc.dominio.retorno.DadosEstatistica;
import br.com.puc.tcc.dominio.retorno.ExamesResultado;
import br.com.puc.tcc.dominio.retorno.ResultadoExameRetorno;
import br.com.puc.tcc.integracao.spec.ResultadoExameDao;

@Transactional
@Repository
public class ResultadoExameDaoImpl extends GenericDao<ResultadoExame> implements ResultadoExameDao{

	public ResultadoExameDaoImpl() {
		super(ResultadoExame.class);
	}

	@Override
	public void salvarResultadoExame(ResultadoExame resultadoExame) {
		super.salvar(resultadoExame);
	}

	@Override
	public void editarResultadoExame(ResultadoExame resultadoExame) {
		super.alterar(resultadoExame);
	}

	@Override
	public void deletarResultadoExame(Integer idConsulta) {
		String qry = "DELETE FROM ResultadoExame re WHERE re.consulta.idConsulta = :idConsulta";
		Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idConsulta", idConsulta);
            query.executeUpdate();
        }catch(Exception ex) {
        	
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultadoExame> getResultadoExames() {
		List<Object[]> result = null;
        List<ResultadoExame> lista = new ArrayList<>();
        String qry = "SELECT e.idExame, e.exame, p.idProfissional, p.nome, p.crm_rms, pa.idPaciente, pa.nome, re.idResultadoExame, c.idConsulta, c.dataHora, re.dataColeta, re.valoresObtidos, re.unidade,"
        		+ " re.valorReferencial, re.enviadoMedico, re.enviadoPaciente FROM ResultadoExame re LEFT JOIN re.exame e LEFT JOIN re.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa ORDER BY c.dataHora";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o : result){
            	Exame e = new Exame();
            	e.setIdExame((Integer) o[0]);
            	e.setExame((String) o[1]);
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[2]);
            	p.setNome((String) o[3]);
            	p.setCrm_rms((String) o[4]);
            	Paciente pa = new Paciente();
            	pa.setIdPaciente((Integer) o[5]);
            	pa.setNome((String) o[6]);
            	Consulta c = new Consulta();
            	c.setIdConsulta((Integer) o[8]);
            	c.setDataHora((Date) o[9]);
            	c.setPaciente(pa);
            	c.setProfissional(p);
            	c.setNomePaciente((String) o[6]);
            	
            	ResultadoExame re = new ResultadoExame();
            	re.setIdResultadoExame((Integer) o[7]);
            	re.setDataColeta((Date) o[10]);
            	re.setValoresObtidos((Double) o[11]);
            	re.setUnidade((Integer) o[12]);
            	re.setValorReferencial((String) o[13]);
            	re.setEnviadoMedico((boolean) o[14]);
            	re.setEnviadoPaciente((boolean) o[15]);
            	re.setExame(e);
            	re.setConsulta(c);
            	
                lista.add(re);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ResultadoExameRetorno getResultadoExame(Integer idConsulta) {
		List<Object[]> result = null;
		ResultadoExameRetorno rer = new ResultadoExameRetorno();
		List<ExamesResultado> listaResultado = new ArrayList<>();
        String qry = "SELECT e.idExame, e.exame, p.idProfissional, p.nome, p.crm_rms, pa.idPaciente, pa.nome, re.idResultadoExame, c.idConsulta, c.dataHora, re.dataColeta, re.valoresObtidos, re.unidade,"
        		+ " re.valorReferencial, re.enviadoMedico, re.enviadoPaciente FROM ResultadoExame re LEFT JOIN re.exame e LEFT JOIN re.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa "
        		+ " WHERE c.idConsulta = :idConsulta";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idConsulta", idConsulta);
            result = query.getResultList();
            Integer contador = 0;
            for(Object[] o : result){
            	contador++;
            	Exame e = new Exame();
            	e.setIdExame((Integer) o[0]);
            	e.setExame((String) o[1]);
            	ExamesResultado ex = new ExamesResultado();
            	ex.setExame(e);
            	ex.setResultado((Double) o[11]);
            	ex.setUnidade((Integer) o[12]);
            	ex.setValorReferencia((String) o[13]);
            	listaResultado.add(ex);
            	
            
            	if(contador.equals(result.size())) {
            		Profissional p = new Profissional();
                	p.setIdProfissional((Integer) o[2]);
                	p.setNome((String) o[3]);
                	p.setCrm_rms((String) o[4]);
                	Paciente pa = new Paciente();
                	pa.setIdPaciente((Integer) o[5]);
                	pa.setNome((String) o[6]);
                	Consulta c = new Consulta();
                	c.setIdConsulta((Integer) o[8]);
                	c.setDataHora((Date) o[9]);
                	c.setPaciente(pa);
                	c.setProfissional(p);
                	c.setNomePaciente((String) o[6]);
                	
                	rer.setDataColeta((Date) o[10]);
                	rer.setEnviadoMedico((boolean) o[14]);
                	rer.setEnviadoPaciente((boolean) o[15]);
                	rer.setConsulta(c);
                	rer.setListaResultado(listaResultado);
            	}
            	
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        
        return rer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultadoExame> getResultadoExamesPorProfissional(Integer idProfissional) {
		List<Object[]> result = null;
        List<ResultadoExame> lista = new ArrayList<>();
        String qry = "SELECT e.idExame, e.exame, p.idProfissional, p.nome, p.crm_rms, pa.idPaciente, pa.nome, re.idResultadoExame, c.idConsulta, c.dataHora, re.dataColeta, re.valoresObtidos, re.unidade,"
        		+ " re.valorReferencial, re.enviadoMedico, re.enviadoPaciente FROM ResultadoExame re LEFT JOIN re.exame e LEFT JOIN re.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa "
        		+ " WHERE p.idProfissional = :idProfissional AND re.enviadoMedico = 1 ORDER BY c.dataHora";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idProfissional", idProfissional);
            result = query.getResultList();
            for(Object[] o : result){
            	Exame e = new Exame();
            	e.setIdExame((Integer) o[0]);
            	e.setExame((String) o[1]);
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[2]);
            	p.setNome((String) o[3]);
            	p.setCrm_rms((String) o[4]);
            	Paciente pa = new Paciente();
            	pa.setIdPaciente((Integer) o[5]);
            	pa.setNome((String) o[6]);
            	Consulta c = new Consulta();
            	c.setIdConsulta((Integer) o[8]);
            	c.setDataHora((Date) o[9]);
            	c.setPaciente(pa);
            	c.setProfissional(p);
            	c.setNomePaciente((String) o[6]);
            	
            	ResultadoExame re = new ResultadoExame();
            	re.setIdResultadoExame((Integer) o[7]);
            	re.setDataColeta((Date) o[10]);
            	re.setValoresObtidos((Double) o[11]);
            	re.setUnidade((Integer) o[12]);
            	re.setValorReferencial((String) o[13]);
            	re.setEnviadoMedico((boolean) o[14]);
            	re.setEnviadoPaciente((boolean) o[15]);
            	re.setExame(e);
            	re.setConsulta(c);
            	
                lista.add(re);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ResultadoExame> getResultadoExamesPorConsulta(Integer idConsulta) {
		List<Object[]> result = null;
        List<ResultadoExame> lista = new ArrayList<>();
        String qry = "SELECT e.idExame, e.exame, p.idProfissional, p.nome, p.crm_rms, pa.idPaciente, pa.nome, re.idResultadoExame, c.idConsulta, c.dataHora, re.dataColeta, re.valoresObtidos, re.unidade,"
        		+ " re.valorReferencial, re.enviadoMedico, re.enviadoPaciente FROM ResultadoExame re LEFT JOIN re.exame e LEFT JOIN re.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa "
        		+ " WHERE c.idConsulta = :idConsulta ORDER BY c.idConsulta";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idConsulta", idConsulta);
            result = query.getResultList();
            for(Object[] o : result){
            	Exame e = new Exame();
            	e.setIdExame((Integer) o[0]);
            	e.setExame((String) o[1]);
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[2]);
            	p.setNome((String) o[3]);
            	p.setCrm_rms((String) o[4]);
            	Paciente pa = new Paciente();
            	pa.setIdPaciente((Integer) o[5]);
            	pa.setNome((String) o[6]);
            	Consulta c = new Consulta();
            	c.setIdConsulta((Integer) o[8]);
            	c.setDataHora((Date) o[9]);
            	c.setPaciente(pa);
            	c.setProfissional(p);
            	c.setNomePaciente((String) o[6]);
            	
            	ResultadoExame re = new ResultadoExame();
            	re.setIdResultadoExame((Integer) o[7]);
            	re.setDataColeta((Date) o[10]);
            	re.setValoresObtidos((Double) o[11]);
            	re.setUnidade((Integer) o[12]);
            	re.setValorReferencial((String) o[13]);
            	re.setEnviadoMedico((boolean) o[14]);
            	re.setEnviadoPaciente((boolean) o[15]);
            	re.setExame(e);
            	re.setConsulta(c);
            	
                lista.add(re);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DadosEstatistica> getEstatisticaResultadoExames() {
		List<Object[]> result = null;
        List<DadosEstatistica> lista = new ArrayList<>();
        List<Integer> idsExames = new ArrayList<>();
        String qry = "SELECT re.idResultadoExame, p.profissional FROM ResultadoExame re LEFT JOIN re.exame e LEFT JOIN re.consulta c LEFT JOIN c.profissional p "
        		+ "  ORDER BY p.profissional ";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o : result){
            	if(!idsExames.contains((Integer) o[0])) {
	            	DadosEstatistica de = new DadosEstatistica();
	            	de.setId((Integer) o[0]);
	            	de.setProfissional((Integer) o[1]);
	            	lista.add(de);
	            	idsExames.add((Integer) o[0]);
            	}
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}
}
