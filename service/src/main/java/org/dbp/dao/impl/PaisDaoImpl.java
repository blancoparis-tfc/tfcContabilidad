package org.dbp.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.dbp.bom.localizacion.Pais;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.PaisDao;
import org.dbp.dto.localizacion.PaisFiltro;
import org.dbp.utils.JqplFilterUtils;
import org.springframework.stereotype.Repository;

@Repository
public class PaisDaoImpl extends GenericDaoImpl<Pais,String> implements PaisDao{

	@PersistenceContext private EntityManager entityManager;
	
	public PaisDaoImpl() {
		super(Pais.class);
	}
	
	@Override
	public List<Pais> consultarFiltro(final PaisFiltro filtro){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Pais> query = builder.createQuery(Pais.class);
		Root<Pais> fPais = query.from(Pais.class);
		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
				.addParametro(String.class, "idAlfa2", fPais, filtro.getIdAlfa2())
				.addParametro(String.class, "codAlfa3", fPais, filtro.getCodAlfa3())
				.addParametro(Integer.class, "codNumerico", fPais, filtro.getCodNumerico())
				.addParametro(String.class, "nombreComun", fPais, filtro.getNombreComun())
				.addParametro(String.class, "nombreOficial", fPais, filtro.getNombreOficial())
				;
		query.where(jqplFilterUtils.crearLosPredicados());
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

}
