package org.dbp.service.impl;

import java.util.List;

import org.dbp.bom.localizacion.Provincia;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.ProvinciaDao;
import org.dbp.service.ProvinciaService;
import org.dbp.dto.localizacion.ProvinciaFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServiceImpl extends GenericServiceImpl<Provincia,Integer> implements ProvinciaService {

	private ProvinciaDao dao;
		
	@Autowired
	public ProvinciaServiceImpl(final ProvinciaDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<Provincia> consultarFiltro(final ProvinciaFiltro filtro){
		return dao.consultarFiltro(filtro);
	}
}