package org.dbp.service;

import java.util.List;

import org.dbp.bom.localizacion.Provincia;
import org.dbp.core.service.GenericService;
import org.dbp.dto.localizacion.ProvinciaFiltro;

public interface ProvinciaService extends GenericService<Provincia,Integer> {

	public abstract List<Provincia> consultarFiltro(final ProvinciaFiltro filtro);

}
