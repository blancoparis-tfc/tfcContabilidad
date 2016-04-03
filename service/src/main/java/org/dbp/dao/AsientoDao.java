package org.dbp.dao;

import java.util.List;

import org.dbp.bom.contabilidad.Asiento;
import org.dbp.core.dao.GenericDao;
import org.dbp.dto.contabilidad.AsientoFiltro;
import org.dbp.sql.contabilidad.ResumenAsiento;

public interface AsientoDao extends GenericDao<Asiento,Long>{

	public abstract List<ResumenAsiento> consultarFiltro(AsientoFiltro filtro);


}
