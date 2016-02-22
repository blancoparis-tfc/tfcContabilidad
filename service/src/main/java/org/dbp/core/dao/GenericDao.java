package org.dbp.core.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDao <E extends Serializable,ID>{

	 E obtenerId(ID identificador); 

	 void eliminar(E entidad); 
	
	 void crear(E entidad); 
	
	 E actualizar(E entidad); 
	
	 List<E> obtenerTodos(); 
	
}
