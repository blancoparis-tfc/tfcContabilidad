package org.dbp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.dbp.bom.localizacion.ComunidadAutonoma;
import org.dbp.bom.localizacion.Provincia;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.ProvinciaDao;
import org.dbp.dto.localizacion.ProvinciaFiltro;
import org.dbp.utils.JqplFilterUtils;
import org.springframework.stereotype.Repository;

@Repository
public class ProvinciaDaoImpl extends GenericDaoImpl<Provincia,Integer> implements ProvinciaDao{

	@PersistenceContext private EntityManager entityManager;
	
	public ProvinciaDaoImpl() {
		super(Provincia.class);
	}
	@Override
	public List<Provincia> consultarFiltro(final ProvinciaFiltro filtro){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Provincia> query = builder.createQuery(Provincia.class);
		Root<Provincia> fProvincia = query.from(Provincia.class);
		Join<Provincia,ComunidadAutonoma> jComunidad = fProvincia.join("comunidadAutonoma",JoinType.INNER); 
		
		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
			.addParametro(Integer.class, "id", fProvincia, filtro.getId())
			.addParametro(String.class, "nombre", fProvincia, filtro.getNombre())
			.addParametro(String.class, "nombre", jComunidad, filtro.getComunidadAutonoma())
		;
		query.where(jqplFilterUtils.crearLosPredicados());
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

}