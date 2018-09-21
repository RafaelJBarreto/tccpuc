package br.com.puc.tcc.integracao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.puc.tcc.dominio.Paciente;
import br.com.puc.tcc.integracao.spec.PacienteDao;

@Transactional
@Repository
public class PacienteDaoImpl extends GenericDao<Paciente> implements PacienteDao{

	public PacienteDaoImpl() {
		super(Paciente.class);
	}

	@Override
	public void salvarPaciente(Paciente paciente) {
		super.salvar(paciente);		
	}

	@Override
	public void editarPaciente(Paciente paciente) {
		super.alterar(paciente);		
	}

	@Override
	public void deletarPaciente(Integer idPaciente) {
		super.apagar(new Paciente(idPaciente));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Paciente> getPacientes() {
		List<Object[]> result = null;
        List<Paciente> lista = new ArrayList<>();
        String qry = "SELECT p.idPaciente, p.nome, p.email, p.telefone, p.celular, p.endereco, p.dataNascimento, p.cartaoSus, p.sexo FROM Paciente p ORDER BY p.nome";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o : result){
            	Paciente p = new Paciente();
            	p.setIdPaciente((Integer) o[0]);
            	p.setNome((String) o[1]);
            	p.setEmail((String) o[2]);
            	p.setTelefone((String) o[3]);
            	p.setCelular((String) o[4]);
            	p.setEndereco((String) o[5]);
            	p.setDataNascimento((Date) o[6]);
            	p.setCartaoSus((String) o[7]);
            	p.setSexo((Integer) o[8]);
                lista.add(p);
            }
        } catch (Exception ex) {
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Paciente getPaciente(Integer idPaciente) {
		List<Object[]> result = null;
        String qry = "SELECT p.idPaciente, p.nome, p.email, p.telefone, p.celular, p.endereco, p.dataNascimento, p.cartaoSus, p.sexo FROM Paciente p WHERE p.idPaciente = :idPaciente";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idPaciente", idPaciente);
            result = query.getResultList();
            for(Object[] o : result){
            	Paciente p = new Paciente();
            	p.setIdPaciente((Integer) o[0]);
            	p.setNome((String) o[1]);
            	p.setEmail((String) o[2]);
            	p.setTelefone((String) o[3]);
            	p.setCelular((String) o[4]);
            	p.setEndereco((String) o[5]);
            	p.setDataNascimento((Date) o[6]);
            	p.setCartaoSus((String) o[7]);
            	p.setSexo((Integer) o[8]);
                return p;
            }
        } catch (Exception ex) {
        }
        return null;
	}

	
}
