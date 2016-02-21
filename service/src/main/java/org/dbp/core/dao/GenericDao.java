package org.dbp.core.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <E extends Serializable,ID>{

	public E obtenerId(ID id);

	public void eliminar(E entidad);
	
	public void crear(E entidad);
	
	public E actualizar(E entidad);
	
	public List<E> obtenerTodos();
	
}
