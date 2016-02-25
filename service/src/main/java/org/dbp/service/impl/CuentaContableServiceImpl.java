package org.dbp.service.impl;

import org.dbp.bom.contabilidad.CuentaContable;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.CuentaContableDao;
import org.dbp.service.CuentaContableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CuentaContableServiceImpl extends GenericServiceImpl<CuentaContable,String> implements CuentaContableService{
	
	@Autowired
	public CuentaContableServiceImpl(final CuentaContableDao dao) {
		super(dao);
	
	}

}
