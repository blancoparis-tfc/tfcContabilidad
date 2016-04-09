package org.dbp.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.dbp.bom.localizacion.Pais;
import org.dbp.core.dao.GenericDao;
import org.dbp.dto.localizacion.PaisFiltro;
import org.dbp.utils.JqplFilterUtils;

public interface PaisDao extends GenericDao<Pais,String>{

	public abstract List<Pais> consultarFiltro(final PaisFiltro filtro);

}
