package org.dbp.dao;

import java.util.List;

import org.dbp.bom.localizacion.ComunidadAutonoma;
import org.dbp.core.dao.GenericDao;
import org.dbp.dto.localizacion.ComunidadAutonomaFiltro;


public interface ComunidadAutonomaDao extends GenericDao<ComunidadAutonoma,Long>{

	public abstract List<ComunidadAutonoma> consultarFiltro(final ComunidadAutonomaFiltro filtro);

}