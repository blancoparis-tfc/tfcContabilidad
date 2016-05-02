package org.dbp.service.impl;

import java.util.List;

import org.dbp.bom.localizacion.Direccion;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.DireccionDao;
import org.dbp.service.DireccionService;
import org.dbp.dto.localizacion.DireccionFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DireccionServiceImpl extends GenericServiceImpl<Direccion,Long> implements DireccionService {

	private DireccionDao dao;
		
	@Autowired
	public DireccionServiceImpl(final DireccionDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<Direccion> consultarFiltro(final DireccionFiltro filtro){
		return dao.consultarFiltro(filtro);
	}
}