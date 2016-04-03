package org.dbp.utils;

import javax.persistence.criteria.ParameterExpression;

import org.dbp.utils.ParserFilterUtils.Operador;

public class ParametrosCriteria <T>{
	public ParameterExpression<T> parametro;
	public Operador operador;
	public T valor;
	public ParametrosCriteria(ParameterExpression<T> parametro,Operador operador,T valor) {
		super();
		this.parametro = parametro;
		this.operador = operador;
		this.valor = valor;
	}
}
