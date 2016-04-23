package org.dbp.service;

import java.util.List;

import org.dbp.bom.localizacion.ComunidadAutonoma;
import org.dbp.core.service.GenericService;
import org.dbp.dto.localizacion.ComunidadAutonomaFiltro;

public interface ComunidadAutonomaService extends GenericService<ComunidadAutonoma,Long> {

	public abstract List<ComunidadAutonoma> consultarFiltro(final ComunidadAutonomaFiltro filtro);

}
