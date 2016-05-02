package org.dbp.service;

import java.util.List;

import org.dbp.bom.localizacion.DatosDeContacto;
import org.dbp.core.service.GenericService;
import org.dbp.dto.localizacion.DatosDeContactoFiltro;

public interface DatosDeContactoService extends GenericService<DatosDeContacto,Long> {

	public abstract List<DatosDeContacto> consultarFiltro(final DatosDeContactoFiltro filtro);

}
