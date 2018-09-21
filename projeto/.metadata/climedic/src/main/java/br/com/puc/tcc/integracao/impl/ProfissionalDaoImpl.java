package br.com.puc.tcc.integracao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.puc.tcc.dominio.Profissional;
import br.com.puc.tcc.integracao.spec.ProfissionalDao;

@Transactional
@Repository
public class ProfissionalDaoImpl extends GenericDao<Profissional> implements ProfissionalDao{

	public ProfissionalDaoImpl() {
		super(Profissional.class);
	}

	@Override
	public void salvarProfissional(Profissional profissional) {
		super.salvar(profissional);
	}

	@Override
	public void editarProfissional(Profissional profissional) {
		super.alterar(profissional);
	}

	@Override
	public void deletarProfissional(Integer idProfissional) {
		super.apagar(new Profissional(idProfissional));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profissional> getProfissionais() {
		List<Object[]> result = null;
        List<Profissional> lista = new ArrayList<Profissional>();
        String qry = "SELECT p.idProfissional, p.nome, p.email, p.telefone, p.celular, p.crm_rms, p.endereco, p.profissional FROM Profissional p ORDER BY p.nome";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o : result){
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[0]);
            	p.setNome((String) o[1]);
            	p.setEmail((String) o[2]);
            	p.setTelefone((String) o[3]);
            	p.setCelular((String) o[4]);
            	p.setCrm_rms((String) o[5]);
            	p.setEndereco((String) o[6]);
            	p.setProfissional((Integer) o[7]);
                lista.add(p);
            }
        } catch (Exception ex) {
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Profissional getProfissional(Integer idProfissional) {
		List<Object[]> result = null;
        String qry = "SELECT p.idProfissional, p.nome, p.email, p.telefone, p.celular, p.crm_rms, p.endereco, p.profissional, p.senha FROM Profissional p WHERE p.idProfissional = :idProfissional";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idProfissional", idProfissional);
            result = query.getResultList();
            for(Object[] o : result){
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[0]);
            	p.setNome((String) o[1]);
            	p.setEmail((String) o[2]);
            	p.setTelefone((String) o[3]);
            	p.setCelular((String) o[4]);
            	p.setCrm_rms((String) o[5]);
            	p.setEndereco((String) o[6]);
            	p.setProfissional((Integer) o[7]);
            	p.setSenha((String) o[8]);
            	return p;
            }
        } catch (Exception ex) {
        }
        return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profissional> getMedicos() {
		List<Object[]> result = null;
        List<Profissional> lista = new ArrayList<Profissional>();
        String qry = "SELECT p.idProfissional, p.nome, p.email, p.telefone, p.celular, p.crm_rms, p.endereco, p.profissional FROM Profissional p WHERE p.profissional <> 0 ORDER BY p.nome";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o : result){
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[0]);
            	p.setNome((String) o[1]);
            	p.setEmail((String) o[2]);
            	p.setTelefone((String) o[3]);
            	p.setCelular((String) o[4]);
            	p.setCrm_rms((String) o[5]);
            	p.setEndereco((String) o[6]);
            	p.setProfissional((Integer) o[7]);
                lista.add(p);
            }
        } catch (Exception ex) {
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Profissional login(Profissional profissional) {
		List<Object[]> result = null;
		Profissional p = new Profissional();
        String qry = "SELECT p.idProfissional, p.nome, p.profissional FROM Profissional p WHERE p.email = :email AND p.senha = :senha";
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("email", profissional.getEmail());
            query.setParameter("senha", profissional.getSenha());
            result = query.getResultList();
            for(Object[] o : result){
            	
            	p.setIdProfissional((Integer) o[0]);
            	p.setNome((String) o[1]);
            	p.setProfissional((Integer) o[2]);
            	return p;
            }
        } catch (Exception ex) {
        }
        return p;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Profissional getProfissionalSenha(Integer idProfissional, String senha) {
		List<Object[]> result = null;
        String qry = "SELECT p.idProfissional, p.nome, p.email, p.telefone, p.celular, p.crm_rms, p.endereco, p.profissional, p.senha "
        		+ " FROM Profissional p WHERE p.idProfissional = :idProfissional AND p.senha = :senha";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idProfissional", idProfissional);
            query.setParameter("senha", senha);
            result = query.getResultList();
            for(Object[] o : result){
            	Profissional p = new Profissional();
            	p.setIdProfissional((Integer) o[0]);
            	p.setNome((String) o[1]);
            	p.setEmail((String) o[2]);
            	p.setTelefone((String) o[3]);
            	p.setCelular((String) o[4]);
            	p.setCrm_rms((String) o[5]);
            	p.setEndereco((String) o[6]);
            	p.setProfissional((Integer) o[7]);
            	p.setSenha((String) o[8]);
            	return p;
            }
        } catch (Exception ex) {
        }
        return null;
	}
}
