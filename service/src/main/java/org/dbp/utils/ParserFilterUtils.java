package org.dbp.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.persistence.criteria.ParameterExpression;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Esta clase se encargara de converit los filtros en los objetos.
 * 
 * @author david
 *
 */
public class ParserFilterUtils {

	private static Logger logger = LoggerFactory.getLogger(ParserFilterUtils.class);
	
	private static final ParserFilterUtils parserFilterUtils = new ParserFilterUtils();
	
	public static ParserFilterUtils obtenerInstancia(){
		return parserFilterUtils;
	}

	private static final Operador OPERADOR_DEFECTO=Operador.IGUAL;

	public enum Operador{
		MAYOR("^>([\\d]+|[\\s]+[\\d]+)",2,">",""),
		MAYORIGUAL("^>=([\\d]+|[\\s]+[\\d]+)",2,">=",""),
		MENOR("^<([\\d]+|[\\s]+[\\d]+)",2,"<",""),
		MENORIGUAL("^<=([\\d]+|[\\s]+[\\d]+)",2,"<=",""),
		LIKE("([\\s\\S]+[\\*]+[\\s\\S]+)|([\\*]+[\\s\\S]+)|([\\s\\S]+[\\*]+)|([\\*]+)",1,"*","%"),
		IGUAL(null,0,"","");
		public Optional<String> patron;
		private Integer nivel;
		private String buscar;
		private String remplazar;
		private Operador(String patron,int nivel,String buscar,String remplazar) {
			this.patron = Optional.ofNullable(patron);
			this.buscar = buscar;
			this.remplazar = remplazar;
			this.nivel=nivel;
		}
		public Integer getNivel() {
			return nivel;
		}
	}

	private final Map<Operador,Pattern> operadores;
	
	private ParserFilterUtils(){
		operadores=Arrays.stream(Operador.values())
				.filter(operador->operador.patron.isPresent())
				.collect(Collectors.toMap(
						  operador->operador
						, operador->Pattern.compile(operador.patron.get()))
						);
	}
	
	public <T> Optional<ParametrosCriteria<T>> parser(ParameterExpression<T> parametro,Class<T> clase,String patron){
		if(StringUtils.isBlank(patron)){
			return Optional.empty();
		}else{
			final Operador operadorSeleccionado=obtenerOperador(patron);
			// TODO: Nos falta el código para quitar segun el parametro.
			final T valor =obtenerValor(patron.replace(operadorSeleccionado.buscar, operadorSeleccionado.remplazar), clase);
			return Optional.of(new ParametrosCriteria<T>(parametro,
					  operadorSeleccionado
					, valor));
		}
		
	}

	private Operador obtenerOperador(String patron){
		Map<Integer,List<Operador>> operadoresSeleccionados = 
				operadores.entrySet().stream()
				.filter(entry->entry.getValue().matcher(patron.trim()).find())
				.map(entry->entry.getKey())
				.collect(Collectors.groupingBy(Operador::getNivel));
		if(operadoresSeleccionados.size()==1){
			return obtenerOperadorLista(operadoresSeleccionados.values().iterator().next());
		}else if(operadoresSeleccionados.size()>1){
			return obtenerOperadorLista(operadoresSeleccionados.get(
					operadoresSeleccionados.keySet().stream()
						.mapToInt(numero->numero).min()
						.getAsInt()
					));
		}else{
			return OPERADOR_DEFECTO;
		}
	}
	
	private Operador obtenerOperadorLista(List<Operador> operadores){
		if(operadores.size()>1){
			return OPERADOR_DEFECTO;
		}else{
			return operadores.get(0);
		}
	}
	
	private <T> T obtenerValor(String patron, Class<T> clase) {
		//TODO: Hay que hacer una mejora con la clase de formato de fechas y el locale. 
		T valor=null;
		try{
			if(clase.getName().equals(Integer.class.getName())){
				valor = (T)Integer.valueOf(patron.trim());
			}else if(clase.getName().equals(Long.class.getName())){
				valor = (T)Long.valueOf(patron.trim());
			}else if(clase.getName().equals(Double.class.getName())){
				valor = (T)Double.valueOf(patron.trim());
			}else if(clase.getName().equals(BigDecimal.class.getName())){
				valor = (T)new BigDecimal(patron.trim());
			}
		}catch (NumberFormatException e){
			logger.error("Se ha producido el siguiente error al intentar obtener el valor",e);
			throw new RuntimeException("error con el tipo de datos no valido ("+patron+")",e);
		}
		if(clase.getName().equals(String.class.getName())){
			valor=(T)patron;
		}
		return valor;
	}
}
