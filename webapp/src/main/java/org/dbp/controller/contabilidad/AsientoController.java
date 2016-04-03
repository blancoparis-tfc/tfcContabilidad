package org.dbp.controller.contabilidad;

import java.lang.reflect.InvocationTargetException;
import java.util.List;




import org.dbp.bom.contabilidad.Asiento;
import org.dbp.core.controller.GenericRestController;
import org.dbp.dto.contabilidad.AsientoFiltro;
import org.dbp.service.AsientoService;
import org.dbp.sql.contabilidad.ResumenAsiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Transactional
@RestController
@RequestMapping("contabilidad/asiento")
public class AsientoController extends GenericRestController<Asiento,Long>{
	
	private AsientoService service;
	
	@Autowired
	public AsientoController(AsientoService service) {
		super(service, entidad->entidad.getId());
		this.service=service;
	}

	@RequestMapping(value="/filtro",method=RequestMethod.GET)
	public List<ResumenAsiento> consultarFiltro( AsientoFiltro filtro){
		List<Asiento> asientos=service.obtenerTodos();
		List<ResumenAsiento> valdev= service.consultarFiltro(filtro);
		return valdev;
	}
	
}
