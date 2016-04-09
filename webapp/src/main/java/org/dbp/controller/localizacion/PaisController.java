package org.dbp.controller.localizacion;

import java.util.List;

import org.dbp.bom.localizacion.Pais;
import org.dbp.core.controller.GenericRestController;
import org.dbp.dto.localizacion.PaisFiltro;
import org.dbp.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("localizacion/pais")
public class PaisController extends GenericRestController<Pais,String> {

	private PaisService service;
	
	@Autowired
	public PaisController(final PaisService service) {
		super(service, entidad->entidad.getIdAlfa2());
		this.service = service;
	}
	
	@RequestMapping(value="/filtro",method=RequestMethod.GET)
	public List<Pais> consultarFiltro(final PaisFiltro filtro){
		return service.consultarFiltro(filtro);
	}
}
