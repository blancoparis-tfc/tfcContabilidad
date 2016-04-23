package org.dbp.dao.impl;

import java.util.List;

import org.dbp.bom.localizacion.ComunidadAutonoma;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.ComunidadAutonomaDao;
import org.dbp.dto.localizacion.ComunidadAutonomaFiltro;
import org.dbp.utils.JqplFilterUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class ComunidadAutonomaDaoImpl extends GenericDaoImpl<ComunidadAutonoma,Long> implements ComunidadAutonomaDao{

	@PersistenceContext private EntityManager entityManager;
	
	public ComunidadAutonomaDaoImpl() {
		super(ComunidadAutonoma.class);
	}
	@Override
	public List<ComunidadAutonoma> consultarFiltro(final ComunidadAutonomaFiltro filtro){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<ComunidadAutonoma> query = builder.createQuery(ComunidadAutonoma.class);
		Root<ComunidadAutonoma> fComunidadAutonoma = query.from(ComunidadAutonoma.class);
		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
			.addParametro(Long.class, "id", fComunidadAutonoma, filtro.getId())
			.addParametro(String.class, "nombre", fComunidadAutonoma, filtro.getNombre())
		;
		query.where(jqplFilterUtils.crearLosPredicados());
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

}