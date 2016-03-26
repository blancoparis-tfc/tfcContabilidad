package org.dbp.utils;

import java.util.Optional;

public class ExceptionUtils {

	private static final ExceptionUtils instancia = new ExceptionUtils();
	
	private ExceptionUtils(){
		
	}
	
	public static ExceptionUtils getInstancia(){
		return instancia;
	}
	
	public <T extends Throwable> Optional<T> buscarCause(Throwable e,Class<T> clase){
		if(e.getCause()==null || e.getCause().getClass().equals(clase)){
			return  Optional.ofNullable((T)e.getCause());
		}else{
			return buscarCause(e.getCause(), clase);
		}
	}
}
