package br.com.puc.tcc.integracao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.puc.tcc.dominio.Consulta;
import br.com.puc.tcc.dominio.Paciente;
import br.com.puc.tcc.dominio.Profissional;
import br.com.puc.tcc.integracao.spec.ConsultaDao;

@Transactional
@Repository
public class ConsultaDaoImpl extends GenericDao<Consulta> implements ConsultaDao{

	public ConsultaDaoImpl() {
		super(Consulta.class);
	}

	@Override
	public void salvarConsulta(Consulta consulta) {
		super.salvar(consulta);
	}

	@Override
	public void editarConsulta(Consulta consulta) {
		super.alterar(consulta);
	}

	@Override
	public void cancelarConsulta(Integer idConsulta) {
		String qry = "UPDATE Consulta set cancelada = 1 WHERE idConsulta = :idConsulta";
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
	public List<Consulta> getConsultas() {
		List<Object[]> result = null;
        List<Consulta> lista = new ArrayList<>();
        String qry = "SELECT c.idConsulta, c.dataHora, p.idProfissional, p.nome, p.profissional, pa.idPaciente, pa.nome, c.cancelada, c.realizada FROM Consulta c "
        		+ " LEFT JOIN c.profissional p LEFT JOIN c.paciente pa ORDER BY c.dataHora DESC";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o : result){
            	Consulta c = new Consulta();
            	c.setIdConsulta((Integer) o[0]);
            	c.setDataHora((Date) o[1]);
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[2]);
            	p.setNome((String) o[3]);
            	p.setProfissional((Integer) o[4]);
            	c.setProfissional(p);
            	Paciente pa = new Paciente();
            	pa.setIdPaciente((Integer) o[5]);
            	pa.setNome((String) o[6]);
            	c.setPaciente(pa);
            	c.setNomePaciente((String) o[6]);
            	c.setCancelada((boolean) o[7]);
            	c.setRealizada((boolean) o[8]);
                lista.add(c);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Consulta getConsulta(Integer idConsulta) {
		List<Object[]> result = null;
        String qry = "SELECT c.idConsulta, c.dataHora, p.idProfissional, p.nome, p.profissional, pa.idPaciente, pa.nome, c.cancelada, c.cancelada, c.pedidoRealizado FROM Consulta c "
        		+ " LEFT JOIN c.profissional p LEFT JOIN c.paciente pa WHERE c.idConsulta = :idConsulta ";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idConsulta", idConsulta);
            result = query.getResultList();
            for(Object[] o : result){
            	Consulta c = new Consulta();
            	c.setIdConsulta((Integer) o[0]);
            	c.setDataHora((Date) o[1]);
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[2]);
            	p.setNome((String) o[3]);
            	p.setProfissional((Integer) o[4]);
            	c.setProfissional(p);
            	Paciente pa = new Paciente();
            	pa.setIdPaciente((Integer) o[5]);
            	pa.setNome((String) o[6]);
            	c.setPaciente(pa);
            	c.setNomePaciente((String) o[6]);
            	c.setCancelada((boolean) o[7]);
            	c.setRealizada((boolean) o[8]);
            	c.setPedidoRealizado((boolean) o[9]);
                return c;
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consulta> getConsultasValidas() {
		List<Object[]> result = null;
        List<Consulta> lista = new ArrayList<>();
        String qry = "SELECT c.idConsulta, c.dataHora, p.idProfissional, p.nome, p.profissional, pa.idPaciente, pa.nome, c.cancelada, c.realizada FROM Consulta c "
        		+ " LEFT JOIN c.profissional p LEFT JOIN c.paciente pa WHERE c.cancelada = 0 ORDER BY c.dataHora DESC";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o : result){
            	Consulta c = new Consulta();
            	c.setIdConsulta((Integer) o[0]);
            	c.setDataHora((Date) o[1]);
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[2]);
            	p.setNome((String) o[3]);
            	p.setProfissional((Integer) o[4]);
            	c.setProfissional(p);
            	Paciente pa = new Paciente();
            	pa.setIdPaciente((Integer) o[5]);
            	pa.setNome((String) o[6]);
            	c.setPaciente(pa);
            	c.setNomePaciente((String) o[6]);
            	c.setCancelada((boolean) o[7]);
            	c.setRealizada((boolean) o[8]);
                lista.add(c);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Consulta> getConsultasProfissional(Integer idProfissional) {
		List<Object[]> result = null;
        List<Consulta> lista = new ArrayList<>();
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, -60);
        
        String qry = "SELECT c.idConsulta, c.dataHora, p.idProfissional, p.nome, p.profissional, pa.idPaciente, pa.nome, c.cancelada, c.realizada, c.pedidoRealizado FROM Consulta c "
        		+ " LEFT JOIN c.profissional p LEFT JOIN c.paciente pa WHERE c.cancelada = 0 AND p.idProfissional = :idProfissional AND c.dataHora >= :dataHora ORDER BY c.dataHora DESC";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idProfissional", idProfissional);
            query.setParameter("dataHora", cal.getTime());
            result = query.getResultList();
            for(Object[] o : result){
            	Consulta c = new Consulta();
            	c.setIdConsulta((Integer) o[0]);
            	c.setDataHora((Date) o[1]);
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[2]);
            	p.setNome((String) o[3]);
            	p.setProfissional((Integer) o[4]);
            	c.setProfissional(p);
            	Paciente pa = new Paciente();
            	pa.setIdPaciente((Integer) o[5]);
            	pa.setNome((String) o[6]);
            	c.setPaciente(pa);
            	c.setNomePaciente((String) o[6]);
            	c.setCancelada((boolean) o[7]);
            	c.setRealizada((boolean) o[8]);
            	c.setPedidoRealizado((boolean) o[9]);
                lista.add(c);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> getPacientesProfissional(Integer idProfissional) {
		List<Object[]> result = null;
        List<Paciente> lista = new ArrayList<>();
        List<Integer> idsPacientes = new ArrayList<>();
        String qry = "SELECT pa.idPaciente, pa.nome, pa.dataNascimento, pa.sexo FROM Consulta c "
        		+ " LEFT JOIN c.profissional p LEFT JOIN c.paciente pa WHERE c.realizada = 1  AND p.idProfissional = :idProfissional ORDER BY c.dataHora DESC";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idProfissional", idProfissional);
            result = query.getResultList();
            for(Object[] o : result){
            	if(!idsPacientes.contains((Integer) o[0])) {
	            	Paciente pa = new Paciente();
	            	pa.setIdPaciente((Integer) o[0]);
	            	pa.setNome((String) o[1]);
	            	pa.setDataNascimento((Date) o[2]);
	            	pa.setSexo((Integer) o[3]);
	                lista.add(pa);
	                idsPacientes.add((Integer) o[0]);
            	}
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}
}
