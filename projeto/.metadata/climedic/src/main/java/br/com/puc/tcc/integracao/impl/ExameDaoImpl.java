package br.com.puc.tcc.integracao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.puc.tcc.dominio.Exame;
import br.com.puc.tcc.integracao.spec.ExameDao;

@Transactional
@Repository
public class ExameDaoImpl extends GenericDao<Exame> implements ExameDao{

	public ExameDaoImpl() {
		super(Exame.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Exame> getExamess() {
		List<Object[]> result = null;
        List<Exame> lista = new ArrayList<>();
        String qry = "SELECT e.idExame, e.exame FROM Exame e ORDER BY e.exame";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o : result){
            	Exame e =  new Exame();
            	e.setIdExame((Integer) o[0]);
            	e.setExame((String) o[1]);
                lista.add(e);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}
	
	
}
