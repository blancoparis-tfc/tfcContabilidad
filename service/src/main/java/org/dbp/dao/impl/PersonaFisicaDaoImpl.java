package org.dbp.dao.impl;

import java.util.List;

import org.dbp.bom.persona.PersonaFisica;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.PersonaFisicaDao;
import org.dbp.dto.persona.PersonaFisicaFiltro;
import org.dbp.utils.JqplFilterUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

@Repository
public class PersonaFisicaDaoImpl extends GenericDaoImpl<PersonaFisica,Long> implements PersonaFisicaDao{

	@PersistenceContext private EntityManager entityManager;
	
	public PersonaFisicaDaoImpl() {
		super(PersonaFisica.class);
	}
	@Override
	public List<PersonaFisica> consultarFiltro(final PersonaFisicaFiltro filtro){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonaFisica> query = builder.createQuery(PersonaFisica.class);
		Root<PersonaFisica> fPersonaFisica = query.from(PersonaFisica.class);
		JqplFilterUtils jqplFilterUtils= JqplFilterUtils.getInstancia(builder)
			.addParametro(Long.class, "id", fPersonaFisica, filtro.getId())
			.addParametro(String.class, "identificadorFiscal", fPersonaFisica, filtro.getIdentificadorFiscal())
			.addParametro(String.class, "nombre", fPersonaFisica, filtro.getNombre())
			.addParametro(String.class, "apellidos", fPersonaFisica, filtro.getApellidos())
		;
		query.where(jqplFilterUtils.crearLosPredicados());
		return jqplFilterUtils.trasladarLosParametros(entityManager.createQuery(query)).getResultList();
	}

}