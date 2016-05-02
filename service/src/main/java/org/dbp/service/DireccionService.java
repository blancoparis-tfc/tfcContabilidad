package org.dbp.service;

import java.util.List;

import org.dbp.bom.localizacion.Direccion;
import org.dbp.core.service.GenericService;
import org.dbp.dto.localizacion.DireccionFiltro;

public interface DireccionService extends GenericService<Direccion,Long> {

	public abstract List<Direccion> consultarFiltro(final DireccionFiltro filtro);

}
