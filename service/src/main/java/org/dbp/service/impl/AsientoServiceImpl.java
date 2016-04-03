package org.dbp.service.impl;

import java.util.List;

import org.dbp.bom.contabilidad.Asiento;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.AsientoDao;
import org.dbp.dto.contabilidad.AsientoFiltro;
import org.dbp.service.AsientoService;
import org.dbp.sql.contabilidad.ResumenAsiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsientoServiceImpl extends GenericServiceImpl<Asiento,Long> implements AsientoService{

	private AsientoDao dao;
	
	@Autowired
	public AsientoServiceImpl(final AsientoDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	@Override
	public List<ResumenAsiento> consultarFiltro(AsientoFiltro filtro){
		return dao.consultarFiltro(filtro);
	}

	@Override
	public void crear(final Asiento entidad) {
		entidad.getLineas().forEach(linea->{
			linea.setAsiento(entidad);
		});
		super.crear(entidad);
	}
	
	
}
