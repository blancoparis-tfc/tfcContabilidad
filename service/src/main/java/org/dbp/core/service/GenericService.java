package org.dbp.core.service;

import java.io.Serializable;
import java.util.List;

public interface GenericService <E extends Serializable,Id>{

		public E obtenerId(Id id); //NOPMD

		public void eliminar(E entidad); //NOPMD
		
		public void crear(E entidad); //NOPMD
		
		public E actualizar(E entidad); //NOPMD
		
		public List<E> obtenerTodos(); //NOPMD

}
