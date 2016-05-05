package org.dbp.controller.persona;

import java.util.List;

import org.dbp.bom.persona.PersonaFisica;
import org.dbp.core.controller.GenericRestController;
import org.dbp.service.PersonaFisicaService;
import org.dbp.dto.persona.PersonaFisicaFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("persona/personaFisica")
public class PersonaFisicaController extends GenericRestController<PersonaFisica,Long> {

	private PersonaFisicaService service;

	@Autowired
	public PersonaFisicaController(final PersonaFisicaService service) {
		super(service, entidad->entidad.getId());
		this.service = service;
	}

	@RequestMapping(value="/filtro",method=RequestMethod.GET)
	public List<PersonaFisica> consultarFiltro(final PersonaFisicaFiltro filtro){
		return service.consultarFiltro(filtro);
	}
}
