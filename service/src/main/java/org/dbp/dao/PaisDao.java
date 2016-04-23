package org.dbp.dao;

import java.util.List;

import org.dbp.bom.localizacion.Pais;
import org.dbp.core.dao.GenericDao;
import org.dbp.dto.localizacion.PaisFiltro;

public interface PaisDao extends GenericDao<Pais,String>{

	public abstract List<Pais> consultarFiltro(final PaisFiltro filtro);

}
