package org.dbp.service.impl;

import java.util.List;

import org.dbp.bom.localizacion.Pais;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.PaisDao;
import org.dbp.dto.localizacion.PaisFiltro;
import org.dbp.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisServiceImpl extends GenericServiceImpl<Pais,String> implements PaisService {

	private PaisDao dao;
	
	@Autowired
	public PaisServiceImpl(final PaisDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<Pais> consultarFiltro(final PaisFiltro filtro){
		return dao.consultarFiltro(filtro);
	}
}
