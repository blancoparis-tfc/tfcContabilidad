package org.dbp.service;

import java.util.List;

import org.dbp.bom.persona.PersonaFisica;
import org.dbp.core.service.GenericService;
import org.dbp.dto.persona.PersonaFisicaFiltro;

public interface PersonaFisicaService extends GenericService<PersonaFisica,Long> {

	public abstract List<PersonaFisica> consultarFiltro(final PersonaFisicaFiltro filtro);

}
