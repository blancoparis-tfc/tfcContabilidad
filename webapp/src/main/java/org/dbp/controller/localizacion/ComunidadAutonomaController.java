package org.dbp.controller.localizacion;

import java.util.List;

import org.dbp.bom.localizacion.ComunidadAutonoma;
import org.dbp.core.controller.GenericRestController;
import org.dbp.service.ComunidadAutonomaService;
import org.dbp.dto.localizacion.ComunidadAutonomaFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("localizacion/comunidadAutonoma")
public class ComunidadAutonomaController extends GenericRestController<ComunidadAutonoma,Long> {

	private ComunidadAutonomaService service;

	@Autowired
	public ComunidadAutonomaController(final ComunidadAutonomaService service) {
		super(service, entidad->entidad.getId());
		this.service = service;
	}

	@RequestMapping(value="/filtro",method=RequestMethod.GET)
	public List<ComunidadAutonoma> consultarFiltro(final ComunidadAutonomaFiltro filtro){
		return service.consultarFiltro(filtro);
	}
}
