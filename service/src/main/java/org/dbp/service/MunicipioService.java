package org.dbp.service;

import java.util.List;

import org.dbp.bom.localizacion.Municipio;
import org.dbp.core.service.GenericService;
import org.dbp.dto.localizacion.MunicipioFiltro;

public interface MunicipioService extends GenericService<Municipio,Long> {

	public abstract List<Municipio> consultarFiltro(final MunicipioFiltro filtro);

}
