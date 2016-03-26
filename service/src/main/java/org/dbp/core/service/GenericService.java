package org.dbp.core.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService <E extends Serializable,Id>{

		 E obtenerId(Id identificador); 

		 void eliminar(E entidad); 
		
		 void crear(E entidad); 
		
		 E actualizar(E entidad);
		
		 List<E> obtenerTodos();

		public abstract List<E> actualizar(final List<E> entidades); 

}
