package org.dbp.core.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.dbp.core.dao.GenericDao;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public class GenericDaoImpl <E extends Serializable,ID> implements GenericDao<E,ID>{

	@PersistenceContext private EntityManager em;
	
	private Class<E> clazzE;

	public GenericDaoImpl(Class<E> clazzE) {
		super();
		this.clazzE = clazzE;
	}

	public E obtenerId(ID id){
		return em.find(clazzE, id);
	}

	public void eliminar(E entidad){
		em.remove(entidad);
	}
	
	public void crear(E entidad){
		em.persist(entidad);
	}
	
	public E actualizar(E actualizar){
		return em.merge(actualizar);
	}
	
	public List<E> obtenerTodos(){
		CriteriaBuilder cb=em.getCriteriaBuilder();
		CriteriaQuery<E> criteria=cb.createQuery(clazzE);
		Root<E> from=criteria.from(clazzE);
		TypedQuery<E> query=em.createQuery(criteria.select(from));
		return query.getResultList();
	}

}
