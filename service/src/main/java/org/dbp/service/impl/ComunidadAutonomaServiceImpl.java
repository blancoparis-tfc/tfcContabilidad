package org.dbp.service.impl;

import java.util.List;

import org.dbp.bom.localizacion.ComunidadAutonoma;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.ComunidadAutonomaDao;
import org.dbp.service.ComunidadAutonomaService;
import org.dbp.dto.localizacion.ComunidadAutonomaFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComunidadAutonomaServiceImpl extends GenericServiceImpl<ComunidadAutonoma,Long> implements ComunidadAutonomaService {

	private ComunidadAutonomaDao dao;
		
	@Autowired
	public ComunidadAutonomaServiceImpl(final ComunidadAutonomaDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<ComunidadAutonoma> consultarFiltro(final ComunidadAutonomaFiltro filtro){
		return dao.consultarFiltro(filtro);
	}
}