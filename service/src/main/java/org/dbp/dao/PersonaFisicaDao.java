package org.dbp.dao;

import java.util.List;

import org.dbp.bom.persona.PersonaFisica;
import org.dbp.core.dao.GenericDao;
import org.dbp.dto.persona.PersonaFisicaFiltro;


public interface PersonaFisicaDao extends GenericDao<PersonaFisica,Long>{

	public abstract List<PersonaFisica> consultarFiltro(final PersonaFisicaFiltro filtro);

}