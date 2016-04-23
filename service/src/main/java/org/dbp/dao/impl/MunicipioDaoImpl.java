package org.dbp.dao.impl;

import java.util.List;

import org.dbp.bom.localizacion.Municipio;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.MunicipioDao;
import org.dbp.dto.localizacion.MunicipioFiltro;
import org.dbp.utils.JqplFilterUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class MunicipioDaoImpl extends GenericDaoImpl<Municipio,Long> implements MunicipioDao{

	@PersistenceContext private EntityManager entityManager;
	
	public MunicipioDaoImpl() {
		super(Municipio.class);
	}
	@Override
	public List<Municipio> consultarFiltro(final MunicipioFiltro filtro){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Municipio> query = builder.createQuery(Municipio.class);
		Root<Municipio> fMunicipio = query.from(Municipio.class);
		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
			.addParametro(Long.class, "id", fMunicipio, filtro.getId())
			.addParametro(String.class, "municipio", fMunicipio, filtro.getMunicipio())
		;
		query.where(jqplFilterUtils.crearLosPredicados());
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

}