package org.dbp.core.controller;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.persistence.PersistenceException;

import org.dbp.core.service.GenericService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class GenericRestController <E extends Serializable,ID>{
	

	
	private GenericService<E,ID> service;
	private ObtenerId<ID,E> obtenerId;
	
	public GenericRestController(
			final GenericService<E,ID> service
			,final ObtenerId<ID,E> obtenerId){

		this.service=service;
		this.obtenerId=obtenerId;
	}
	
	public interface ObtenerId<ID,E>{
		public ID obtenerId(E entidad);
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<E> crear(@RequestBody final E entidad) throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		service.crear(entidad);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(ServletUriComponentsBuilder
				.fromCurrentRequest().path("/{id}")
				.buildAndExpand(this.obtenerId).toUri());
		//throw new DuplicadoException();
		return new ResponseEntity<E>(entidad,httpHeaders, HttpStatus.CREATED);
	}
	@RequestMapping(method=RequestMethod.GET)
	public List<E> obtenerTodos(){
		return service.obtenerTodos();
	}
	
	@RequestMapping(value="/{identificador}",method=RequestMethod.GET)
	public E obtenerId(@PathVariable final ID identificador){
			return service.obtenerId(identificador);
	}

	@RequestMapping(method=RequestMethod.PUT)
	public E actualizar (@RequestBody E entidad){
		return service.actualizar(entidad);
	}

	@RequestMapping(value="/lista",method=RequestMethod.PUT)
	public List<E> actualizar (@RequestBody List<E> entidad){
		return service.actualizar(entidad);
	}

	
	@Transactional(rollbackFor=Exception.class)
	@RequestMapping(value="/{identificador}",method=RequestMethod.DELETE)
	public void eliminar(@PathVariable final ID identificador){
		service.eliminar(service.obtenerId(identificador));
	}
	
	// Procesamiento de los errores.
	//DataIntegrityViolationException
	@ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleConflict() {
		//VndErrors;
        return "Duplicado";
    }
	
	
	@ResponseStatus(HttpStatus.CONFLICT)  // 409
    @ExceptionHandler(PersistenceException.class)
    public String handleConflictCreado() {
		//VndErrors;
        return "El registro ya fue creado";
    }
	
	
	
}
