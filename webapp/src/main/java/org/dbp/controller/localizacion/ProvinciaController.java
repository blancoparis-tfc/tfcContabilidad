package org.dbp.controller.localizacion;

import java.util.List;

import org.dbp.bom.localizacion.Provincia;
import org.dbp.core.controller.GenericRestController;
import org.dbp.service.ProvinciaService;
import org.dbp.dto.localizacion.ProvinciaFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("localizacion/provincia")
public class ProvinciaController extends GenericRestController<Provincia,Integer> {

	private ProvinciaService service;

	@Autowired
	public ProvinciaController(final ProvinciaService service) {
		super(service, entidad->entidad.getId());
		this.service = service;
	}

	@RequestMapping(value="/filtro",method=RequestMethod.GET)
	public List<Provincia> consultarFiltro(final ProvinciaFiltro filtro){
		return service.consultarFiltro(filtro);
	}
}
