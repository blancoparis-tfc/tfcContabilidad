package org.dbp.dao;

import java.util.List;

import org.dbp.bom.localizacion.Municipio;
import org.dbp.core.dao.GenericDao;
import org.dbp.dto.localizacion.MunicipioFiltro;


public interface MunicipioDao extends GenericDao<Municipio,Long>{

	public abstract List<Municipio> consultarFiltro(final MunicipioFiltro filtro);

}