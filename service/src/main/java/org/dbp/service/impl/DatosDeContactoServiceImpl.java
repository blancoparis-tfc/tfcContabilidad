package org.dbp.service.impl;

import java.util.List;

import org.dbp.bom.localizacion.DatosDeContacto;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.DatosDeContactoDao;
import org.dbp.service.DatosDeContactoService;
import org.dbp.dto.localizacion.DatosDeContactoFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatosDeContactoServiceImpl extends GenericServiceImpl<DatosDeContacto,Long> implements DatosDeContactoService {

	private DatosDeContactoDao dao;
		
	@Autowired
	public DatosDeContactoServiceImpl(final DatosDeContactoDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<DatosDeContacto> consultarFiltro(final DatosDeContactoFiltro filtro){
		return dao.consultarFiltro(filtro);
	}
}