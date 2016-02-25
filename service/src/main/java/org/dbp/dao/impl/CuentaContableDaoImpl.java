package org.dbp.dao.impl;

import org.dbp.bom.contabilidad.CuentaContable;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.CuentaContableDao;
import org.springframework.stereotype.Repository;

@Repository
public class CuentaContableDaoImpl extends GenericDaoImpl<CuentaContable,String> implements CuentaContableDao {

	public CuentaContableDaoImpl() {
		super(CuentaContable.class);
	}

}
