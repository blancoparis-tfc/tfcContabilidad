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
 * Configuraci�n de los los aspecto para el sistema de logs.
 * 
 * 	- Por un lado logeamos los m�todos que esten anotados por @DpbLog.
 *  - Y Siempre que tengamos una excepci�n, este o no anotado en los siguientes paquetes: org.dbp.controller
 * 
 * @author david
 *
 */
@Aspect
public class LogAspect {

	private static Logger logger=LoggerFactory.getLogger(LogAspect.class); //NOPMD
	/**
	 * 
	 * Antes de ejecutar el m�todo.
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
	 * Despues de ejecutar un m�todo que tiene return.
	 * 
	 * @param joinPoint	Es el punto de union.
	 * @param valdev	El valor devuelto por el m�todo.
	 */
	@AfterReturning(
			pointcut="execution(* *(..)) && @annotation(org.dbp.conf.aop.log.DbpLog)"
			,returning="valdev"
			)
	public void despuesReturn(final JoinPoint joinPoint,final Object valdev){
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
	 * Se ejecutara siempre que tengamos una excepti�n, para el paquete controller.
	 * 
	 * @param joinPoint	Es el punto de union.
	 * @param exception		La excpeci�n que se ha ejecutado.
	 */
	@AfterThrowing(
			pointcut="execution(* org.dbp.controller.*.*(..))  "
			,throwing="exception"
			)
	public void despuesExcepcion(final JoinPoint joinPoint,final Throwable exception){
		logger.warn(" [{}] argumentos [{}] "
				,joinPoint.getSignature().toString()
				,Arrays.toString(joinPoint.getArgs()));
		logger.error(" Error [{}] "
				,joinPoint.getSignature().toString()
				,exception);
	}
	
}
