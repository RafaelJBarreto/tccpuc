package br.com.puc.tcc.integracao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.puc.tcc.dominio.Consulta;
import br.com.puc.tcc.dominio.ConsultaMedica;
import br.com.puc.tcc.dominio.Medicamento;
import br.com.puc.tcc.dominio.Paciente;
import br.com.puc.tcc.dominio.Profissional;
import br.com.puc.tcc.dominio.retorno.ConsultaMedicaRetorno;
import br.com.puc.tcc.dominio.retorno.DadosEstatistica;
import br.com.puc.tcc.dominio.retorno.MedicamentoConsulta;
import br.com.puc.tcc.integracao.spec.ConsultaMedicaDao;

@Transactional
@Repository
public class ConsultaMedicaDaoImpl extends GenericDao<ConsultaMedica> implements ConsultaMedicaDao{

	public ConsultaMedicaDaoImpl() {
		super(ConsultaMedica.class);
	}

	@Override
	public void salvarConsultaMedica(ConsultaMedica consultaMedica) {
		super.salvar(consultaMedica);
	}

	@Override
	public void editarConsultaMedica(ConsultaMedica consultaMedica) {
		super.alterar(consultaMedica);
	}

	@Override
	public void deletarConsultaMedica(Integer idConsulta) {
		String qry = "DELETE FROM ConsultaMedica cm WHERE cm.consulta.idConsulta = :idConsulta";
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
	public ConsultaMedicaRetorno getConsultaMedica(Integer idConsulta) {
		List<Object[]> result = null;
		ConsultaMedicaRetorno retorno = new ConsultaMedicaRetorno();
		List<MedicamentoConsulta> listaMedicamentos = new ArrayList<>();
		Integer contador = 0;
        String qry = "SELECT c.idConsulta, c.dataHora, p.idProfissional, p.nome, p.profissional, pa.idPaciente, pa.nome, c.cancelada, cm.idConsultaMedica, cm.prescricao, cm.quantidade, "
        		+ " cm.diagnosticoConsulta, m.idMedicamento, m.nomeGenerico, m.nomeComercial FROM ConsultaMedica cm LEFT JOIN cm.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa LEFT JOIN cm.medicamento m"
        		+ " WHERE c.idConsulta = :idConsulta";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idConsulta", idConsulta);
            result = query.getResultList();
            for(Object[] o : result){
            	contador++;
            	Medicamento m = new Medicamento();
            	m.setIdMedicamento((Integer) o[12]);
            	m.setNomeGenerico((String) o[13]);
            	m.setNomeComercial((String) o[14]);
            	MedicamentoConsulta mc = new MedicamentoConsulta();
            	mc.setPrescricao((String) o[9]);
            	mc.setQuantidade((Integer) o[10]);
            	mc.setMedicamento(m);
            	listaMedicamentos.add(mc);
            	
            	if(contador.equals(result.size())) {
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
                	retorno.setConsulta(c);
                	retorno.setDiagnosticoConsulta((String) o[11]);
                	retorno.setListaMedicamentos(listaMedicamentos);
            	}
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return retorno;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConsultaMedica> getConsultaMedicasPorProfissional(Integer idProfissional) {
		List<Object[]> result = null;
        List<ConsultaMedica> lista = new ArrayList<>();
        String qry = "SELECT c.idConsulta, c.dataHora, p.idProfissional, p.nome, p.profissional, pa.idPaciente, pa.nome, c.cancelada, cm.idConsultaMedica, cm.prescricao, cm.quantidade, "
        		+ " cm.diagnosticoConsulta, m.idMedicamento, m.nomeGenerico, m.nomeComercial FROM ConsultaMedica cm LEFT JOIN cm.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa LEFT JOIN cm.medicamento m"
        		+ " WHERE p.idProfissional = :idProfissional ORDER BY c.idConsulta";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idProfissional", idProfissional);
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
            	Medicamento m = new Medicamento();
            	m.setIdMedicamento((Integer) o[12]);
            	m.setNomeGenerico((String) o[13]);
            	m.setNomeComercial((String) o[14]);
            	ConsultaMedica cm = new ConsultaMedica();
            	cm.setConsulta(c);
            	cm.setMedicamento(m);
            	cm.setIdConsultaMedica((Integer) o[8]);
            	cm.setPrescricao((String) o[9]);
            	cm.setQuantidade((Integer) o[10]);
            	cm.setDiagnosticoConsulta((String) o[11]);
                lista.add(cm);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ConsultaMedica> getConsultaMedicasPorPaciente(Integer idPaciente) {
		List<Object[]> result = null;
        List<ConsultaMedica> lista = new ArrayList<>();
        String qry = "SELECT c.idConsulta, c.dataHora, p.idProfissional, p.nome, p.profissional, pa.idPaciente, pa.nome, c.cancelada, cm.idConsultaMedica, cm.prescricao, cm.quantidade, "
        		+ " cm.diagnosticoConsulta, m.idMedicamento, m.nomeGenerico, m.nomeComercial FROM ConsultaMedica cm LEFT JOIN cm.consulta c LEFT JOIN c.profissional p LEFT JOIN c.paciente pa LEFT JOIN cm.medicamento m"
        		+ " WHERE pa.idPaciente = :idPaciente ORDER BY c.idConsulta";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idPaciente", idPaciente);
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
            	Medicamento m = new Medicamento();
            	m.setIdMedicamento((Integer) o[12]);
            	m.setNomeGenerico((String) o[13]);
            	m.setNomeComercial((String) o[14]);
            	ConsultaMedica cm = new ConsultaMedica();
            	cm.setConsulta(c);
            	cm.setMedicamento(m);
            	cm.setIdConsultaMedica((Integer) o[8]);
            	cm.setPrescricao((String) o[9]);
            	cm.setQuantidade((Integer) o[10]);
            	cm.setDiagnosticoConsulta((String) o[11]);
                lista.add(cm);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DadosEstatistica> getEstatisticaConsultasMedicas() {
		List<Object[]> result = null;
		List<DadosEstatistica> resultado = new ArrayList<>();
		List<Integer> idsConsultas = new ArrayList<>();
        String qry = "SELECT c.idConsulta, p.profissional FROM ConsultaMedica cm LEFT JOIN cm.consulta c LEFT JOIN c.profissional p "
        		+ "  ORDER BY p.profissional";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o: result) {
            	if(!idsConsultas.contains((Integer) o[0])) {
	            	DadosEstatistica de = new DadosEstatistica();
	            	de.setId((Integer) o[0]);
	            	de.setProfissional((Integer) o[1]);
	            	resultado.add(de);
	            	idsConsultas.add((Integer) o[0]);
            	}
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return resultado;
	}
}
