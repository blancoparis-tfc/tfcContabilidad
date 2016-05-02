package org.dbp.dao;

import java.util.List;

import org.dbp.bom.localizacion.DatosDeContacto;
import org.dbp.core.dao.GenericDao;
import org.dbp.dto.localizacion.DatosDeContactoFiltro;


public interface DatosDeContactoDao extends GenericDao<DatosDeContacto,Long>{

	public abstract List<DatosDeContacto> consultarFiltro(final DatosDeContactoFiltro filtro);

}