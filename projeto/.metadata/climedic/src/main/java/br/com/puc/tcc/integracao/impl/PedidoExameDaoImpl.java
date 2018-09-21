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
import br.com.puc.tcc.dominio.PedidoExame;
import br.com.puc.tcc.dominio.Profissional;
import br.com.puc.tcc.dominio.retorno.ExameRetorno;
import br.com.puc.tcc.dominio.retorno.PedidoExameRetorno;
import br.com.puc.tcc.integracao.spec.PedidoExameDao;

@Transactional
@Repository
public class PedidoExameDaoImpl extends GenericDao<PedidoExame> implements PedidoExameDao{

	public PedidoExameDaoImpl() {
		super(PedidoExame.class);
	}

	@Override
	public void salvarPedidoExame(PedidoExame pedidoExame) {
		super.salvar(pedidoExame);
	}

	@Override
	public void editarPedidoExame(Integer idConsulta) {
		String qry = "UPDATE PedidoExame pe set pe.existeResultado = 1 WHERE pe.consulta.idConsulta = :idConsulta";
		Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idConsulta", idConsulta);
            query.executeUpdate();
        }catch(Exception ex) {
        	
        }
	}

	@Override
	public void deletarPedidoExame(Integer idConsulta) {
		String qry = "DELETE FROM PedidoExame pe WHERE pe.consulta.idConsulta = :idConsulta";
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
	public List<PedidoExame> getPedidoExames() {
		List<Object[]> result = null;
        List<PedidoExame> lista = new ArrayList<>();
        String qry = "SELECT e.idExame, e.exame, p.idProfissional, p.nome, p.crm_rms, pa.idPaciente, pa.nome, pe.idPedidoExame, c.idConsulta, c.dataHora,"
        		+ " p.profissional FROM PedidoExame pe LEFT JOIN pe.exame e LEFT JOIN pe.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa "
        		+ " WHERE pe.existeResultado = 0 ORDER BY c.dataHora ";
        
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
            	p.setProfissional((Integer) o[10]);
            	Paciente pa = new Paciente();
            	pa.setIdPaciente((Integer) o[5]);
            	pa.setNome((String) o[6]);
            	Consulta c = new Consulta();
            	c.setIdConsulta((Integer) o[8]);
            	c.setDataHora((Date) o[9]);
            	c.setPaciente(pa);
            	c.setProfissional(p);
            	c.setNomePaciente((String) o[6]);
            	PedidoExame pe = new PedidoExame();
            	pe.setIdPedidoExame((Integer) o[7]);
            	pe.setExame(e);
            	pe.setConsulta(c);
                lista.add(pe);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PedidoExameRetorno getPedidoExame(Integer idConsulta) {
		List<Object[]> result = null;
		PedidoExameRetorno retorno = new PedidoExameRetorno();
		List<ExameRetorno> listaExames = new ArrayList<>();
		Integer contador = 0;
        String qry = "SELECT e.idExame, e.exame, p.idProfissional, p.nome, p.crm_rms, pa.idPaciente, pa.nome, pe.idPedidoExame, c.idConsulta, c.dataHora FROM PedidoExame pe LEFT JOIN pe.exame e LEFT JOIN pe.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa "
        		+ " WHERE c.idConsulta = :idConsulta ";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idConsulta", idConsulta);
            result = query.getResultList();
            for(Object[] o : result){
            	
            	contador++;
            	Exame e = new Exame();
            	e.setIdExame((Integer) o[0]);
            	e.setExame((String) o[1]);
            	ExameRetorno er = new ExameRetorno();
            	er.setExame(e);
            	listaExames.add(er);
            	
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
	            	retorno.setConsulta(c);
	            	retorno.setListaExames(listaExames);
            	}
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return retorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PedidoExame> getPedidoExamesPorProfissional(Integer idProfissional) {
		List<Object[]> result = null;
        List<PedidoExame> lista = new ArrayList<>();
        String qry = "SELECT e.idExame, e.exame, p.idProfissional, p.nome, p.crm_rms, pa.idPaciente, pa.nome, pe.idPedidoExame, c.idConsulta, c.dataHora FROM PedidoExame pe LEFT JOIN pe.exame e LEFT JOIN pe.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa "
        		+ " WHERE p.idProfissional = :idProfissional ORDER BY c.dataHora";
        
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
            	PedidoExame pe = new PedidoExame();
            	pe.setIdPedidoExame((Integer) o[7]);
            	pe.setExame(e);
            	pe.setConsulta(c);
                lista.add(pe);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	
}
