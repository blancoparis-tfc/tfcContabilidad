package org.dbp.dao.impl;

import java.util.List;

import org.dbp.bom.localizacion.DatosDeContacto;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.DatosDeContactoDao;
import org.dbp.dto.localizacion.DatosDeContactoFiltro;
import org.dbp.utils.JqplFilterUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class DatosDeContactoDaoImpl extends GenericDaoImpl<DatosDeContacto,Long> implements DatosDeContactoDao{

	@PersistenceContext private EntityManager entityManager;
	
	public DatosDeContactoDaoImpl() {
		super(DatosDeContacto.class);
	}
	@Override
	public List<DatosDeContacto> consultarFiltro(final DatosDeContactoFiltro filtro){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DatosDeContacto> query = builder.createQuery(DatosDeContacto.class);
		Root<DatosDeContacto> fDatosDeContacto = query.from(DatosDeContacto.class);
		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
			.addParametro(Long.class, "id", fDatosDeContacto, filtro.getId())
			.addParametro(String.class, "telefono", fDatosDeContacto, filtro.getTelefono())
			.addParametro(String.class, "nombre", fDatosDeContacto, filtro.getNombre())
			.addParametro(String.class, "direccionDeCorreo", fDatosDeContacto, filtro.getDireccionDeCorreo())
		;
		query.where(jqplFilterUtils.crearLosPredicados());
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

}