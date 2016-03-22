package org.dbp.core.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import org.dbp.core.dao.GenericDao;
import org.dbp.core.service.GenericService;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor=Exception.class)
public class GenericServiceImpl <E extends Serializable,Id> implements GenericService<E,Id>{

	private final GenericDao<E,Id> dao;
	
	public GenericServiceImpl(final GenericDao<E, Id> dao) {
		super();
		this.dao = dao;
	}

	@Override
	public E obtenerId(final Id identificador) {
		return dao.obtenerId(identificador);
	}

	@Override
	public void eliminar(final E entidad) {
		dao.eliminar(entidad);
	}

	@Override
	public void crear(final E entidad) {
		dao.crear(entidad);
	}

	@Override
	public E actualizar(final E entidad) {
		return dao.actualizar(entidad);
	}

	
	@Override
	public List<E> actualizar(final List<E> entidades) {
		return entidades.stream()
				.map(entidad->actualizar(entidad))
				.collect(Collectors.toList());
	}
	
	@Override
	public List<E> obtenerTodos() {
		return dao.obtenerTodos();
	}

	
	
}
