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
import org.dbp.bom.localizacion.Municipio;
import org.dbp.bom.localizacion.Provincia;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.MunicipioDao;
import org.dbp.dto.localizacion.MunicipioFiltro;
import org.dbp.utils.JqplFilterUtils;
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
		Join<Municipio,Provincia> jProvincia = fMunicipio.join("provincia",JoinType.INNER);
		Join<Provincia,ComunidadAutonoma> jComunidad = jProvincia.join("comunidadAutonoma",JoinType.INNER); 

		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
			.addParametro(Long.class, "id", fMunicipio, filtro.getId())
			.addParametro(String.class, "municipio", fMunicipio, filtro.getMunicipio())
			.addParametro(String.class, "nombre", jProvincia, filtro.getProvincia())
			.addParametro(String.class, "nombre", jComunidad, filtro.getComunidadAutonoma())
		;
		query.where(jqplFilterUtils.crearLosPredicados());
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

}