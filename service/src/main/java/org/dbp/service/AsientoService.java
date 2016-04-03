package org.dbp.service;

import java.util.List;

import org.dbp.bom.contabilidad.Asiento;
import org.dbp.core.service.GenericService;
import org.dbp.dto.contabilidad.AsientoFiltro;
import org.dbp.sql.contabilidad.ResumenAsiento;

public interface AsientoService extends GenericService<Asiento,Long> {

	public abstract List<ResumenAsiento> consultarFiltro(AsientoFiltro filtro);

}
