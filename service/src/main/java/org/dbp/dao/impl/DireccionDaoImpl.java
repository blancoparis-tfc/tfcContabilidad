package org.dbp.dao.impl;

import java.util.List;

import org.dbp.bom.localizacion.Direccion;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.DireccionDao;
import org.dbp.dto.localizacion.DireccionFiltro;
import org.dbp.utils.JqplFilterUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class DireccionDaoImpl extends GenericDaoImpl<Direccion,Long> implements DireccionDao{

	@PersistenceContext private EntityManager entityManager;
	
	public DireccionDaoImpl() {
		super(Direccion.class);
	}
	@Override
	public List<Direccion> consultarFiltro(final DireccionFiltro filtro){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Direccion> query = builder.createQuery(Direccion.class);
		Root<Direccion> fDireccion = query.from(Direccion.class);
		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
			.addParametro(Long.class, "id", fDireccion, filtro.getId())
			.addParametro(String.class, "direccion", fDireccion, filtro.getDireccion())
		;
		query.where(jqplFilterUtils.crearLosPredicados());
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

}