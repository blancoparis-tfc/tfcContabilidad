package org.dbp.service.impl;

import org.dbp.bom.contabilidad.Asiento;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.AsientoDao;
import org.dbp.service.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AsientoServiceImpl extends GenericServiceImpl<Asiento,Long> implements AsientoService{

	@Autowired
	public AsientoServiceImpl(final AsientoDao dao) {
		super(dao);
	}

}
