package br.com.puc.tcc.integracao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

@Transactional
@Repository
public abstract class GenericDao<T> {

	@PersistenceContext
	protected EntityManager entityManager;	
	
	@SuppressWarnings("unused")
	private Class<T> entity;
	
	public GenericDao(Class<T> entity) {
        this.entity = entity;
    }
	
	
	public boolean salvar(final T entity) {
		try {
			this.entityManager.persist(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean apagar(T entity) {
		try {
			entity = this.entityManager.merge(entity);
			this.entityManager.remove(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean alterar(final T entity) {
		try {
			this.entityManager.merge(entity);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
