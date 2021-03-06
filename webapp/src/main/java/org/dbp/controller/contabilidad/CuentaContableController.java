package org.dbp.controller.contabilidad;

import org.dbp.bom.contabilidad.CuentaContable;
import org.dbp.core.controller.GenericRestController;
import org.dbp.service.CuentaContableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contabilidad/cuentaContable")
public class CuentaContableController extends GenericRestController<CuentaContable,String>{
	
	@Autowired
	public CuentaContableController(
			CuentaContableService cuentaContableService) {
		super(cuentaContableService,  	entidad->entidad.getCuenta());
	}
}
