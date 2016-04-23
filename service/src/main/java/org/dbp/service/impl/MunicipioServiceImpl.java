package org.dbp.service.impl;

import java.util.List;

import org.dbp.bom.localizacion.Municipio;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.MunicipioDao;
import org.dbp.service.MunicipioService;
import org.dbp.dto.localizacion.MunicipioFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipioServiceImpl extends GenericServiceImpl<Municipio,Long> implements MunicipioService {

	private MunicipioDao dao;
		
	@Autowired
	public MunicipioServiceImpl(final MunicipioDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<Municipio> consultarFiltro(final MunicipioFiltro filtro){
		return dao.consultarFiltro(filtro);
	}
}