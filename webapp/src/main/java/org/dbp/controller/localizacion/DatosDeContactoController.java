package org.dbp.controller.localizacion;

import java.util.List;

import org.dbp.bom.localizacion.DatosDeContacto;
import org.dbp.core.controller.GenericRestController;
import org.dbp.service.DatosDeContactoService;
import org.dbp.dto.localizacion.DatosDeContactoFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("localizacion/datosDeContacto")
public class DatosDeContactoController extends GenericRestController<DatosDeContacto,Long> {

	private DatosDeContactoService service;

	@Autowired
	public DatosDeContactoController(final DatosDeContactoService service) {
		super(service, entidad->entidad.getId());
		this.service = service;
	}

	@RequestMapping(value="/filtro",method=RequestMethod.GET)
	public List<DatosDeContacto> consultarFiltro(final DatosDeContactoFiltro filtro){
		return service.consultarFiltro(filtro);
	}
}
