package org.dbp.controller.contabilidad;

import org.dbp.bom.contabilidad.Asiento;
import org.dbp.core.controller.GenericRestController;
import org.dbp.service.AsientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("contabilidad/asiento")
public class AsientoController extends GenericRestController<Asiento,Long>{
	
	@Autowired
	public AsientoController(
			AsientoService service) {
		super(service, entidad->entidad.getId());
	}

}
