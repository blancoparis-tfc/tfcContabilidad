package org.dbp.core.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.PropertyUtils;
import org.dbp.core.service.GenericService;
import org.dbp.json.comun.ResultadoJson;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

public class GenericRestController <E extends Serializable,ID,J extends Serializable>{
	
	private Class<E> classE;
	private Class<J> classJ;
	private GenericService<E,ID> service;
	
	public GenericRestController(
			final GenericService<E,ID> service
			,final Class<E> classE
			,final Class<J> classJ){
		this.classE=classE;
		this.classJ=classJ;
		this.service=service;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public @ResponseBody  ResultadoJson crear(@RequestBody J json) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		final ResultadoJson valdev = new ResultadoJson();
		final E entidad = classE.newInstance();
		PropertyUtils.copyProperties(entidad, json);
		service.crear(entidad);
		valdev.setMensaje("Generico");
		return valdev;
	}
	
}
