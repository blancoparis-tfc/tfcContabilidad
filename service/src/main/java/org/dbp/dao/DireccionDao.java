package org.dbp.dao;

import java.util.List;

import org.dbp.bom.localizacion.Direccion;
import org.dbp.core.dao.GenericDao;
import org.dbp.dto.localizacion.DireccionFiltro;


public interface DireccionDao extends GenericDao<Direccion,Long>{

	public abstract List<Direccion> consultarFiltro(final DireccionFiltro filtro);

}