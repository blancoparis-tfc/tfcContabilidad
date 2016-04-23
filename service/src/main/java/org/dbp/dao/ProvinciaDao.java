package org.dbp.dao;

import java.util.List;

import org.dbp.bom.localizacion.Provincia;
import org.dbp.core.dao.GenericDao;
import org.dbp.dto.localizacion.ProvinciaFiltro;


public interface ProvinciaDao extends GenericDao<Provincia,Integer>{

	public abstract List<Provincia> consultarFiltro(final ProvinciaFiltro filtro);

}