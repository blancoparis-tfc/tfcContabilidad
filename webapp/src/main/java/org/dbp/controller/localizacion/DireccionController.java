package org.dbp.controller.localizacion;

import java.util.List;

import org.dbp.bom.localizacion.Direccion;
import org.dbp.core.controller.GenericRestController;
import org.dbp.service.DireccionService;
import org.dbp.dto.localizacion.DireccionFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("localizacion/direccion")
public class DireccionController extends GenericRestController<Direccion,Long> {

	private DireccionService service;

	@Autowired
	public DireccionController(final DireccionService service) {
		super(service, entidad->entidad.getId());
		this.service = service;
	}

	@RequestMapping(value="/filtro",method=RequestMethod.GET)
	public List<Direccion> consultarFiltro(final DireccionFiltro filtro){
		return service.consultarFiltro(filtro);
	}
}
