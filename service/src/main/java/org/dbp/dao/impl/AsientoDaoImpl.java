package org.dbp.dao.impl;

import org.dbp.bom.contabilidad.Asiento;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.AsientoDao;
import org.springframework.stereotype.Repository;

@Repository
public class AsientoDaoImpl extends GenericDaoImpl<Asiento,Long> implements AsientoDao{

	public AsientoDaoImpl() {
		super(Asiento.class);
	}
	
}
