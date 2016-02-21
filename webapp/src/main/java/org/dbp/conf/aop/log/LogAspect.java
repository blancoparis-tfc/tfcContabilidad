package org.dbp.conf.aop.log;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 
 * Configuración de los los aspecto para el sistema de logs.
 * 
 * 	- Por un lado logeamos los métodos que esten anotados por @DpbLog.
 *  - Y Siempre que tengamos una excepción, este o no anotado en los siguientes paquetes: org.dbp.controller
 * 
 * @author david
 *
 */
@Aspect
public class LogAspect {

	private static Logger logger=LoggerFactory.getLogger(LogAspect.class);
	/**
	 * 
	 * Antes de ejecutar el método.
	 * 
	 * @param joinPoint	Es el punto de union.
	 */
	@Before("execution(* *(..)) && @annotation(org.dbp.conf.aop.log.DbpLog)")
	public void antes(final JoinPoint joinPoint){
		logger.debug(" Antes: [{}] argumentos [{}]"
				,joinPoint.getSignature().toString()
				,Arrays.toString(joinPoint.getArgs()));
	}
	/**
	 * 
	 * Despues de ejecutar un método que tiene return.
	 * 
	 * @param joinPoint	Es el punto de union.
	 * @param valdev	El valor devuelto por el método.
	 */
	@AfterReturning(
			pointcut="execution(* *(..)) && @annotation(org.dbp.conf.aop.log.DbpLog)"
			,returning="valdev"
			)
	public void despuesReturn(final JoinPoint joinPoint,Object valdev){
		logger.debug(" [{}] valdev: [{}]"
					,joinPoint.getSignature().toString()
					,valdev);
	}
	/**
	 * 
	 * Despues de ejecutar un metodo que tiene void.
	 * 
	 * Nota: Se ejecutara si procede, despuest de la traza de debug.
	 * 
	 * @param joinPoint Es el punto de union.
	 * 
	 */
	@After("execution(* *(..)) && @annotation(org.dbp.conf.aop.log.DbpLog)")
	public void despues(final JoinPoint joinPoint){
		logger.debug(" Despues: [{}] argumentos [{}]"
				,joinPoint.getSignature().toString()
				,Arrays.toString(joinPoint.getArgs()));
	}
	/**
	 * 
	 * Se ejecutara siempre que tengamos una exceptión, para el paquete controller.
	 * 
	 * @param joinPoint	Es el punto de union.
	 * @param ex		La excpeción que se ha ejecutado.
	 */
	@AfterThrowing(
			pointcut="execution(* org.dbp.controller.*.*(..))  "
			,throwing="ex"
			)
	public void despuesExcepcion(final JoinPoint joinPoint,final Throwable ex){
		logger.warn(" [{}] argumentos [{}] "
				,joinPoint.getSignature().toString()
				,Arrays.toString(joinPoint.getArgs()));
		logger.error(" Error [{}] "
				,joinPoint.getSignature().toString()
				,ex);
	}
	
}
