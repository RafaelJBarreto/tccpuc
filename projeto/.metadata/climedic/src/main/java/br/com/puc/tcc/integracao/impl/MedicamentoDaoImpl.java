package br.com.puc.tcc.integracao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.puc.tcc.dominio.Medicamento;
import br.com.puc.tcc.integracao.spec.MedicamentoDao;

@Transactional
@Repository
public class MedicamentoDaoImpl extends GenericDao<Medicamento> implements MedicamentoDao{

	public MedicamentoDaoImpl() {
		super(Medicamento.class);
	}

	@Override
	public void salvarMedicamento(Medicamento medicamento) {
		super.salvar(medicamento);
	}

	@Override
	public void editarMedicamento(Medicamento medicamento) {
		super.alterar(medicamento);
	}

	@Override
	public void deletarMedicamento(Integer idMedicamento) {
		super.apagar(new Medicamento(idMedicamento));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Medicamento> getMedicamentos() {
		List<Object[]> result = null;
        List<Medicamento> lista = new ArrayList<>();
        String qry = "SELECT m.idMedicamento, m.nomeGenerico, m.nomeComercial, m.fabricante FROM Medicamento m ORDER BY m.nomeGenerico";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            result = query.getResultList();
            for(Object[] o : result){
            	Medicamento m = new Medicamento();
            	m.setIdMedicamento((Integer) o[0]);
            	m.setNomeGenerico((String) o[1]);
            	m.setNomeComercial((String) o[2]);
            	m.setFabricante((Integer) o[3]);
                lista.add(m);
            }
        } catch (Exception ex) {
        	ex.printStackTrace();
        }
        return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Medicamento getMedicamento(Integer idMedicamento) {
		List<Object[]> result = null;
        String qry = "SELECT m.idMedicamento, m.nomeGenerico, m.nomeComercial, m.fabricante FROM Medicamento m WHERE m.idMedicamento = :idMedicamento";
        
        Query query = null;
        try {
            query = entityManager.createQuery(qry);
            query.setParameter("idMedicamento", idMedicamento);
            result = query.getResultList();
            for(Object[] o : result){
            	Medicamento m = new Medicamento();
            	m.setIdMedicamento((Integer) o[0]);
            	m.setNomeGenerico((String) o[1]);
            	m.setNomeComercial((String) o[2]);
            	m.setFabricante((Integer) o[3]);
                return m;
            }
        } catch (Exception ex) {
        }
        return null;
	}

}
