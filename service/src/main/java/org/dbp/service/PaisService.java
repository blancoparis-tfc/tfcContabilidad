package org.dbp.service;

import java.util.List;

import org.dbp.bom.localizacion.Pais;
import org.dbp.core.service.GenericService;
import org.dbp.dto.localizacion.PaisFiltro;

public interface PaisService extends GenericService<Pais,String> {

	public abstract List<Pais> consultarFiltro(final PaisFiltro filtro);

}
