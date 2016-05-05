package org.dbp.service.impl;

import java.util.List;

import org.dbp.bom.persona.PersonaFisica;
import org.dbp.core.service.impl.GenericServiceImpl;
import org.dbp.dao.PersonaFisicaDao;
import org.dbp.service.PersonaFisicaService;
import org.dbp.dto.persona.PersonaFisicaFiltro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaFisicaServiceImpl extends GenericServiceImpl<PersonaFisica,Long> implements PersonaFisicaService {

	private PersonaFisicaDao dao;
		
	@Autowired
	public PersonaFisicaServiceImpl(final PersonaFisicaDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<PersonaFisica> consultarFiltro(final PersonaFisicaFiltro filtro){
		return dao.consultarFiltro(filtro);
	}
}