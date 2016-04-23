package org.dbp.controller.localizacion;

import java.util.List;

import org.dbp.bom.localizacion.Municipio;
import org.dbp.core.controller.GenericRestController;
import org.dbp.service.MunicipioService;
import org.dbp.dto.localizacion.MunicipioFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("localizacion/municipio")
public class MunicipioController extends GenericRestController<Municipio,Long> {

	private MunicipioService service;

	@Autowired
	public MunicipioController(final MunicipioService service) {
		super(service, entidad->entidad.getId());
		this.service = service;
	}

	@RequestMapping(value="/filtro",method=RequestMethod.GET)
	public List<Municipio> consultarFiltro(final MunicipioFiltro filtro){
		return service.consultarFiltro(filtro);
	}
}
