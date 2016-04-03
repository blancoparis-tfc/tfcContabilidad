package org.dbp.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.From;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.dbp.sql.contabilidad.ResumenAsiento;
import org.dbp.utils.ParserFilterUtils.Operador;

public class JqplFilterUtils {


	
	private final CriteriaBuilder builder; 
	private final Map<From<?,?>, List<ParametrosCriteria<?>>> parametros;
	private final ParserFilterUtils parserFilterUtils;
	
	public static JqplFilterUtils getInstancia(final CriteriaBuilder builder){
		return new JqplFilterUtils(builder);
	}
	
	private JqplFilterUtils(final CriteriaBuilder builder){
		this.builder=builder;
		this.parametros = new HashMap<From<?,?>,List<ParametrosCriteria<?>>>();
		this.parserFilterUtils=ParserFilterUtils.obtenerInstancia();
	}
	
	public JqplFilterUtils setParametros(final Map<Root<?>, List<ParametrosCriteria<?>>> parametros){
		this.parametros.putAll(parametros);
		return this;
	}
	
	public <C> JqplFilterUtils addParametro(Class<C> clase,String campo,From<?,?> root,String patron){
		parserFilterUtils.parser(builder.parameter(clase,campo), clase, patron).ifPresent(parametro->{
			this.addParametro(root,parametro);
		});
		return this;
	}
	
	public <C> JqplFilterUtils addParametro(Class<C> clase,String campo,Root<?> root,Operador operador,C valor){
		return addParametro(builder.parameter(clase,campo), root, operador,valor);
	}
	
	private <C> JqplFilterUtils addParametro(final From<?,?> root,final ParametrosCriteria<C> parametro){
		return this.addParametro(parametro.parametro, root, parametro.operador,parametro.valor);
	}
	
	private <C> JqplFilterUtils addParametro(ParameterExpression<C> param,From<?,?> root,Operador operador,C valor){
		if(!this.parametros.containsKey(root)){
			this.parametros.put(root, new ArrayList<ParametrosCriteria<?>>());
		}
		this.parametros.get(root).add(new ParametrosCriteria<C>(param,operador,valor));
		return this;
	}
	public TypedQuery<ResumenAsiento>  trasladarLosParametros(
			final TypedQuery<ResumenAsiento> entityQuery) {
		for(Map.Entry<From<?,?>,List<ParametrosCriteria<?>>> tabla:parametros.entrySet()){
			for(ParametrosCriteria<?> parametro:tabla.getValue()){
				entityQuery.setParameter(parametro.parametro.getName(),parametro.valor);
			}
		}
		return entityQuery;
	}
	
	public Predicate[] crearLosPredicados() {
		List<Predicate> predicados=new ArrayList<Predicate>();
		for(Map.Entry<From<?,?>,List<ParametrosCriteria<?>>> tabla:parametros.entrySet()){
			for(ParametrosCriteria<?> parametro:tabla.getValue()){
				predicados.add(procesarOperacion( tabla.getKey(), parametro));
			}
		}
		return predicados.toArray(new Predicate[0]);
	}
	
	private Predicate procesarOperacion( From<?,?> tabla,
			ParametrosCriteria<?> parametro) {
		switch(parametro.operador){
			case IGUAL: return builder.equal(tabla.get(parametro.parametro.getName()), parametro.parametro);
			//case MAYOR: return builder.greaterThan(tabla.get(parametro.parametro.getName()), parametro.parametro);
			case MAYOR:{
				
				if(  parametro.valor instanceof Long ){
					return parameterMayor(tabla,(ParametrosCriteria<Long>) parametro);
				}else if(parametro.valor instanceof Double){
					return parameterMayor(tabla,(ParametrosCriteria<Double>) parametro);
				}else if(parametro.valor instanceof BigDecimal){
					return parameterMayor(tabla,(ParametrosCriteria<BigDecimal>) parametro);
				}else{
					// TODO: Falta por poner el error que se ha hecho mal
					return null;
				}
			}
			case MAYORIGUAL:{
				
				if(  parametro.valor instanceof Long ){
					return parameterMayorIgual(tabla,(ParametrosCriteria<Long>) parametro);
				}else if(parametro.valor instanceof Double){
					return parameterMayorIgual(tabla,(ParametrosCriteria<Double>) parametro);
				}else if(parametro.valor instanceof BigDecimal){
					return parameterMayorIgual(tabla,(ParametrosCriteria<BigDecimal>) parametro);
				}else{
					// TODO: Falta por poner el error que se ha hecho mal
					return null;
				}
			}
			case MENOR:{
				if(  parametro.valor instanceof Long ){
					return parameterMenor(tabla,(ParametrosCriteria<Long>) parametro);
				}else if(parametro.valor instanceof Double){
					return parameterMenor(tabla,(ParametrosCriteria<Double>) parametro);
				}else if(parametro.valor instanceof BigDecimal){
					return parameterMenor(tabla,(ParametrosCriteria<BigDecimal>) parametro);
				}else{
					// TODO: Falta por poner el error que se ha hecho mal
					return null;
				}
			}
			case MENORIGUAL:{
				if(  parametro.valor instanceof Long ){
					return parameterMenorIgual(tabla,(ParametrosCriteria<Long>) parametro);
				}else if(parametro.valor instanceof Double){
					return parameterMenorIgual(tabla,(ParametrosCriteria<Double>) parametro);
				}else if(parametro.valor instanceof BigDecimal){
					return parameterMenorIgual(tabla,(ParametrosCriteria<BigDecimal>) parametro);
				}else{
					// TODO: Falta por poner el error que se ha hecho mal
					return null;
				}
			}
			case LIKE:{
				ParametrosCriteria<String> parametroAux=(ParametrosCriteria<String>)parametro;
				return builder.like(tabla.get(parametroAux.parametro.getName()), (parametroAux.parametro));
			}	
		}
		return null;
	}
	
	private <C extends Number> Predicate parameterMayor( From<?,?> tabla,ParametrosCriteria<C> parametro){
		return builder.gt(tabla.get(parametro.parametro.getName()), (parametro.parametro));
	}

	private <C extends Number> Predicate parameterMayorIgual( From<?,?> tabla, ParametrosCriteria<C> parametro){
		return builder.ge(tabla.get(parametro.parametro.getName()), (parametro.parametro));
	}

	
	private <C extends Number> Predicate parameterMenor( From<?,?> tabla, ParametrosCriteria<C> parametro){
		return builder.lt(tabla.get(parametro.parametro.getName()), (parametro.parametro));
	}

	private <C extends Number> Predicate parameterMenorIgual( From<?,?> tabla,ParametrosCriteria<C> parametro){
		return builder.le(tabla.get(parametro.parametro.getName()), (parametro.parametro));
	}
	
}
