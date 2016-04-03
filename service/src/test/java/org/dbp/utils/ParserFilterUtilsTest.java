package org.dbp.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import org.dbp.utils.ParserFilterUtils.Operador;
import org.hamcrest.CoreMatchers;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runners.MethodSorters;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ParserFilterUtilsTest {
    @Rule
    public ExpectedException thrown= ExpectedException.none();
    
    @Test
    public void test000Vacio(){
    	assertTrue("Para el tipo Long",		 !ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "").isPresent());
    	assertTrue("Para el tipo Double",	 !ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "").isPresent());
    	assertTrue("Para el tipo Integer",	 !ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, "").isPresent());
    	assertTrue("Para el tipo BigDecimal",!ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "").isPresent());
    	assertTrue("Para el tipo String",	 !ParserFilterUtils.obtenerInstancia().parser(null, String.class, "").isPresent());
    }

    // TEST DE MAYOR
    
	@Test
	public void test001MayorLong(){
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, ">25").get(),25L,Operador.MAYOR);
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "> 25").get(),25L,Operador.MAYOR);
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "> 25 ").get(),25L,Operador.MAYOR);
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, " > 25 ").get(),25L,Operador.MAYOR);
	}

	@Test public void test002ErrorNoLongMayorBasico()						{pruebaError(Long.class,">25a","25a");}
	@Test public void test003ErrorNoLongMayorNumeroEspacioAfanumerico()		{pruebaError(Long.class,">25 a","25 a");}
	@Test public void test004ErrorNoLongMayorNumeroAlfanumericoIntercalado(){pruebaError(Long.class,">2a5","2a5");}
	@Test public void test004ErrorNoLongMayorNumeroEmpiezaAlfanumerico()	{pruebaError(Long.class,">a25",">a25");}
	@Test public void test005ErrorNoLongMayorAlfanumerico()					{pruebaError(Long.class,">asdf",">asdf");}
	@Test public void test006ErrorNoLongMayorVacio()						{pruebaError(Long.class,">",">");}
	@Test public void test007ErrorNoLongSoloAlfanumerico()					{pruebaError(Long.class,"a","a");}
	@Test public void test008ErrorNoLongMayorDecimal()						{pruebaError(Long.class,">25.2","25.2");}

	@Test
	public void test010MayorReal(){
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, ">25").get(),25D,Operador.MAYOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "> 25").get(),25D,Operador.MAYOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "> 25 ").get(),25D,Operador.MAYOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, " > 25 ").get(),25D,Operador.MAYOR);
		
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, ">25.2").get(),25.2D,Operador.MAYOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "> 25.2").get(),25.2D,Operador.MAYOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "> 25.2 ").get(),25.2D,Operador.MAYOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, " > 25.2 ").get(),25.2D,Operador.MAYOR);
	}

	@Test public void test011ErrorNoRealMayorBasico()						{pruebaError(Double.class,">25a","25a");}
	@Test public void test012ErrorNoRealMayorNumeroEspacioAfanumerico()		{pruebaError(Double.class,">25 a","25 a");}
	@Test public void test013ErrorNoRealMayorNumeroAlfanumericoIntercalado(){pruebaError(Double.class,">2a5","2a5");}
	@Test public void test014ErrorNoRealMayorNumeroEmpiezaAlfanumerico()	{pruebaError(Double.class,">a25",">a25");}
	@Test public void test015ErrorNoRealMayorAlfanumerico()					{pruebaError(Double.class,">asdf",">asdf");}
	@Test public void test016ErrorNoRealMayorVacio()						{pruebaError(Double.class,">",">");}
	@Test public void test017ErrorNoRealSoloAlfanumerico()					{pruebaError(Double.class,"a","a");}
	
	@Test public void test021ErrorNoRealMayorDecimalesBasico()						 {pruebaError(Double.class,">25.2a","25.2a");}
	@Test public void test022ErrorNoRealMayorDecimalesNumeroEspacioAfanumerico()	 {pruebaError(Double.class,">25.2 a","25.2 a");}
	@Test public void test023ErrorNoRealMayorDecimalesNumeroAlfanumericoIntercalado(){pruebaError(Double.class,">2a5.2","2a5.2");}
	@Test public void test024ErrorNoRealMayorDecimalesNumeroEmpiezaAlfanumerico()	 {pruebaError(Double.class,">a25.2",">a25.2");}
	
	
	@Test
	public void test030MayorBigDecimal(){
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, ">25").get(),new BigDecimal("25"),Operador.MAYOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "> 25").get(),new BigDecimal("25"),Operador.MAYOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "> 25 ").get(),new BigDecimal("25"),Operador.MAYOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, " > 25 ").get(),new BigDecimal("25"),Operador.MAYOR);
		
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, ">25.2").get(),new BigDecimal("25.2"),Operador.MAYOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "> 25.2").get(),new BigDecimal("25.2"),Operador.MAYOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "> 25.2 ").get(),new BigDecimal("25.2"),Operador.MAYOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, " > 25.2 ").get(),new BigDecimal("25.2"),Operador.MAYOR);
	}

	@Test public void test031ErrorNoBigDecimalMayorBasico()						{pruebaError(BigDecimal.class,">25a","25a");}
	@Test public void test032ErrorNoBigDecimalMayorNumeroEspacioAfanumerico()		{pruebaError(BigDecimal.class,">25 a","25 a");}
	@Test public void test033ErrorNoBigDecimalMayorNumeroAlfanumericoIntercalado(){pruebaError(BigDecimal.class,">2a5","2a5");}
	@Test public void test034ErrorNoBigDecimalMayorNumeroEmpiezaAlfanumerico()	{pruebaError(BigDecimal.class,">a25",">a25");}
	@Test public void test035ErrorNoBigDecimalMayorAlfanumerico()					{pruebaError(BigDecimal.class,">asdf",">asdf");}
	@Test public void test036ErrorNoBigDecimalMayorVacio()						{pruebaError(BigDecimal.class,">",">");}
	@Test public void test037ErrorNoBigDecimalSoloAlfanumerico()					{pruebaError(BigDecimal.class,"a","a");}
	
	@Test public void test041ErrorNoBigDecimalMayorDecimalesBasico()						 {pruebaError(BigDecimal.class,">25.2a","25.2a");}
	@Test public void test042ErrorNoBigDecimalMayorDecimalesNumeroEspacioAfanumerico()	 {pruebaError(BigDecimal.class,">25.2 a","25.2 a");}
	@Test public void test043ErrorNoBigDecimalMayorDecimalesNumeroAlfanumericoIntercalado(){pruebaError(BigDecimal.class,">2a5.2","2a5.2");}
	@Test public void test044ErrorNoBigDecimalMayorDecimalesNumeroEmpiezaAlfanumerico()	 {pruebaError(BigDecimal.class,">a25.2",">a25.2");}
	
	@Test
	public void test050MayorEntero(){
		assertParameto(Integer.class,ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, ">25").get(),25,Operador.MAYOR);
		assertParameto(Integer.class,ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, "> 25").get(),25,Operador.MAYOR);
		assertParameto(Integer.class,ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, "> 25 ").get(),25,Operador.MAYOR);
		assertParameto(Integer.class,ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, " > 25 ").get(),25,Operador.MAYOR);
	}

	@Test public void test052ErrorNoEnteroMayorBasico()						  {pruebaError(Integer.class,">25a","25a");}
	@Test public void test053ErrorNoEnteroMayorNumeroEspacioAfanumerico()	  {pruebaError(Integer.class,">25 a","25 a");}
	@Test public void test054ErrorNoEnteroMayorNumeroAlfanumericoIntercalado(){pruebaError(Integer.class,">2a5","2a5");}
	@Test public void test054ErrorNoEnteroMayorNumeroEmpiezaAlfanumerico()	  {pruebaError(Integer.class,">a25",">a25");}
	@Test public void test055ErrorNoEnteroMayorAlfanumerico()				  {pruebaError(Integer.class,">asdf",">asdf");}
	@Test public void test056ErrorNoEnteroMayorVacio()						  {pruebaError(Integer.class,">",">");}
	@Test public void test057ErrorNoEnteroSoloAlfanumerico()				  {pruebaError(Integer.class,"a","a");}
	@Test public void test058ErrorNoEnteroMayorDecimal()					  {pruebaError(Integer.class,">25.2","25.2");}

	// TEST MAYOR IGUAL
	@Test
	public void test061MayorIgualLong(){
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, ">=25").get(),25L,Operador.MAYORIGUAL);
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, ">= 25").get(),25L,Operador.MAYORIGUAL);
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, ">= 25 ").get(),25L,Operador.MAYORIGUAL);
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, " >= 25 ").get(),25L,Operador.MAYORIGUAL);
	}

	@Test public void test062ErrorNoLongMayorIgualBasico()						{pruebaError(Long.class,">=25a","25a");}
	@Test public void test063ErrorNoLongMayorIgualNumeroEspacioAfanumerico()		{pruebaError(Long.class,">=25 a","25 a");}
	@Test public void test064ErrorNoLongMayorIgualNumeroAlfanumericoIntercalado(){pruebaError(Long.class,">=2a5","2a5");}
	@Test public void test064ErrorNoLongMayorIgualNumeroEmpiezaAlfanumerico()	{pruebaError(Long.class,">=a25",">=a25");}
	@Test public void test065ErrorNoLongMayorIgualAlfanumerico()					{pruebaError(Long.class,">=asdf",">=asdf");}
	@Test public void test066ErrorNoLongMayorIgualVacio()						{pruebaError(Long.class,">=",">=");}
	@Test public void test068ErrorNoLongMayorIgualDecimal()						{pruebaError(Long.class,">=25.2","25.2");}

	@Test
	public void test070MayorIgualReal(){
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, ">=25").get(),25D,Operador.MAYORIGUAL);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, ">= 25").get(),25D,Operador.MAYORIGUAL);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, ">= 25 ").get(),25D,Operador.MAYORIGUAL);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, " >= 25 ").get(),25D,Operador.MAYORIGUAL);
		
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, ">=25.2").get(),25.2D,Operador.MAYORIGUAL);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, ">= 25.2").get(),25.2D,Operador.MAYORIGUAL);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, ">= 25.2 ").get(),25.2D,Operador.MAYORIGUAL);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, " >= 25.2 ").get(),25.2D,Operador.MAYORIGUAL);
	}

	@Test public void test071ErrorNoRealMayorIgualBasico()						{pruebaError(Double.class,">=25a","25a");}
	@Test public void test072ErrorNoRealMayorIgualNumeroEspacioAfanumerico()		{pruebaError(Double.class,">=25 a","25 a");}
	@Test public void test073ErrorNoRealMayorIgualNumeroAlfanumericoIntercalado(){pruebaError(Double.class,">=2a5","2a5");}
	@Test public void test074ErrorNoRealMayorIgualNumeroEmpiezaAlfanumerico()	{pruebaError(Double.class,">=a25",">=a25");}
	@Test public void test075ErrorNoRealMayorIgualAlfanumerico()					{pruebaError(Double.class,">=asdf",">=asdf");}
	@Test public void test076ErrorNoRealMayorIgualVacio()						{pruebaError(Double.class,">=",">=");}
	
	
	@Test public void test081ErrorNoRealMayorIgualDecimalesBasico()						 {pruebaError(Double.class,">=25.2a","25.2a");}
	@Test public void test082ErrorNoRealMayorIgualDecimalesNumeroEspacioAfanumerico()	 {pruebaError(Double.class,">=25.2 a","25.2 a");}
	@Test public void test083ErrorNoRealMayorIgualDecimalesNumeroAlfanumericoIntercalado(){pruebaError(Double.class,">=2a5.2","2a5.2");}
	@Test public void test084ErrorNoRealMayorIgualDecimalesNumeroEmpiezaAlfanumerico()	 {pruebaError(Double.class,">=a25.2",">=a25.2");}
	
	@Test
	public void test090MayorIgualBigDecimal(){
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, ">=25").get(),new BigDecimal("25"),Operador.MAYORIGUAL);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, ">= 25").get(),new BigDecimal("25"),Operador.MAYORIGUAL);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, ">= 25 ").get(),new BigDecimal("25"),Operador.MAYORIGUAL);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, " >= 25 ").get(),new BigDecimal("25"),Operador.MAYORIGUAL);
		
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, ">=25.2").get(),new BigDecimal("25.2"),Operador.MAYORIGUAL);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, ">= 25.2").get(),new BigDecimal("25.2"),Operador.MAYORIGUAL);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, ">= 25.2 ").get(),new BigDecimal("25.2"),Operador.MAYORIGUAL);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, " >= 25.2 ").get(),new BigDecimal("25.2"),Operador.MAYORIGUAL);
	}

	@Test public void test091ErrorNoBigDecimalMayorIgualBasico()						{pruebaError(BigDecimal.class,">=25a","25a");}
	@Test public void test092ErrorNoBigDecimalMayorIgualNumeroEspacioAfanumerico()		{pruebaError(BigDecimal.class,">=25 a","25 a");}
	@Test public void test093ErrorNoBigDecimalMayorIgualNumeroAlfanumericoIntercalado(){pruebaError(BigDecimal.class,">=2a5","2a5");}
	@Test public void test094ErrorNoBigDecimalMayorIgualNumeroEmpiezaAlfanumerico()	{pruebaError(BigDecimal.class,">=a25",">=a25");}
	@Test public void test095ErrorNoBigDecimalMayorIgualAlfanumerico()					{pruebaError(BigDecimal.class,">=asdf",">=asdf");}
	@Test public void test096ErrorNoBigDecimalMayorIgualVacio()						{pruebaError(BigDecimal.class,">=",">=");}
	@Test public void test097ErrorNoBigDecimalSoloAlfanumerico()					{pruebaError(BigDecimal.class,"a","a");}
	
	@Test public void test101ErrorNoBigDecimalMayorIgualDecimalesBasico()						 {pruebaError(BigDecimal.class,">=25.2a","25.2a");}
	@Test public void test102ErrorNoBigDecimalMayorIgualDecimalesNumeroEspacioAfanumerico()	 {pruebaError(BigDecimal.class,">=25.2 a","25.2 a");}
	@Test public void test103ErrorNoBigDecimalMayorIgualDecimalesNumeroAlfanumericoIntercalado(){pruebaError(BigDecimal.class,">=2a5.2","2a5.2");}
	@Test public void test104ErrorNoBigDecimalMayorIgualDecimalesNumeroEmpiezaAlfanumerico()	 {pruebaError(BigDecimal.class,">=a25.2",">=a25.2");}
	
	// TEST MENOR
	@Test
	public void test201MenorrLong(){
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "<25").get(),25L,Operador.MENOR);
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "< 25").get(),25L,Operador.MENOR);
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "< 25 ").get(),25L,Operador.MENOR);
		assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, " < 25 ").get(),25L,Operador.MENOR);
	}

	@Test public void test202ErrorNoLongMenorrBasico()						{pruebaError(Long.class,"<25a","25a");}
	@Test public void test203ErrorNoLongMenorrNumeroEspacioAfanumerico()		{pruebaError(Long.class,"<25 a","25 a");}
	@Test public void test204ErrorNoLongMenorrNumeroAlfanumericoIntercalado(){pruebaError(Long.class,"<2a5","2a5");}
	@Test public void test204ErrorNoLongMenorrNumeroEmpiezaAlfanumerico()	{pruebaError(Long.class,"<a25","<a25");}
	@Test public void test205ErrorNoLongMenorrAlfanumerico()					{pruebaError(Long.class,"<asdf","<asdf");}
	@Test public void test206ErrorNoLongMenorrVacio()						{pruebaError(Long.class,"<","<");}
	@Test public void test207ErrorNoLongSoloAlfanumerico()					{pruebaError(Long.class,"a","a");}
	@Test public void test208ErrorNoLongMenorrDecimal()						{pruebaError(Long.class,"<25.2","25.2");}

	@Test
	public void test210MenorrReal(){
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "<25").get(),25D,Operador.MENOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "< 25").get(),25D,Operador.MENOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "< 25 ").get(),25D,Operador.MENOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, " < 25 ").get(),25D,Operador.MENOR);
		
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "<25.2").get(),25.2D,Operador.MENOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "< 25.2").get(),25.2D,Operador.MENOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "< 25.2 ").get(),25.2D,Operador.MENOR);
		assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, " < 25.2 ").get(),25.2D,Operador.MENOR);
	}

	@Test public void test211ErrorNoRealMenorrBasico()						{pruebaError(Double.class,"<25a","25a");}
	@Test public void test212ErrorNoRealMenorrNumeroEspacioAfanumerico()		{pruebaError(Double.class,"<25 a","25 a");}
	@Test public void test213ErrorNoRealMenorrNumeroAlfanumericoIntercalado(){pruebaError(Double.class,"<2a5","2a5");}
	@Test public void test214ErrorNoRealMenorrNumeroEmpiezaAlfanumerico()	{pruebaError(Double.class,"<a25","<a25");}
	@Test public void test215ErrorNoRealMenorrAlfanumerico()					{pruebaError(Double.class,"<asdf","<asdf");}
	@Test public void test216ErrorNoRealMenorrVacio()						{pruebaError(Double.class,"<","<");}
	@Test public void test217ErrorNoRealSoloAlfanumerico()					{pruebaError(Double.class,"a","a");}
	
	@Test public void test221ErrorNoRealMenorrDecimalesBasico()						 {pruebaError(Double.class,"<25.2a","25.2a");}
	@Test public void test222ErrorNoRealMenorrDecimalesNumeroEspacioAfanumerico()	 {pruebaError(Double.class,"<25.2 a","25.2 a");}
	@Test public void test223ErrorNoRealMenorrDecimalesNumeroAlfanumericoIntercalado(){pruebaError(Double.class,"<2a5.2","2a5.2");}
	@Test public void test224ErrorNoRealMenorrDecimalesNumeroEmpiezaAlfanumerico()	 {pruebaError(Double.class,"<a25.2","<a25.2");}
	
	
	@Test
	public void test230MenorrBigDecimal(){
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "<25").get(),new BigDecimal("25"),Operador.MENOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "< 25").get(),new BigDecimal("25"),Operador.MENOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "< 25 ").get(),new BigDecimal("25"),Operador.MENOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, " < 25 ").get(),new BigDecimal("25"),Operador.MENOR);
		
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "<25.2").get(),new BigDecimal("25.2"),Operador.MENOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "< 25.2").get(),new BigDecimal("25.2"),Operador.MENOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "< 25.2 ").get(),new BigDecimal("25.2"),Operador.MENOR);
		assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, " < 25.2 ").get(),new BigDecimal("25.2"),Operador.MENOR);
	}

	@Test public void test231ErrorNoBigDecimalMenorrBasico()						{pruebaError(BigDecimal.class,"<25a","25a");}
	@Test public void test232ErrorNoBigDecimalMenorrNumeroEspacioAfanumerico()		{pruebaError(BigDecimal.class,"<25 a","25 a");}
	@Test public void test233ErrorNoBigDecimalMenorrNumeroAlfanumericoIntercalado(){pruebaError(BigDecimal.class,"<2a5","2a5");}
	@Test public void test234ErrorNoBigDecimalMenorrNumeroEmpiezaAlfanumerico()	{pruebaError(BigDecimal.class,"<a25","<a25");}
	@Test public void test235ErrorNoBigDecimalMenorrAlfanumerico()					{pruebaError(BigDecimal.class,"<asdf","<asdf");}
	@Test public void test236ErrorNoBigDecimalMenorrVacio()						{pruebaError(BigDecimal.class,"<","<");}
	@Test public void test237ErrorNoBigDecimalSoloAlfanumerico()					{pruebaError(BigDecimal.class,"a","a");}
	
	@Test public void test241ErrorNoBigDecimalMenorrDecimalesBasico()						 {pruebaError(BigDecimal.class,"<25.2a","25.2a");}
	@Test public void test242ErrorNoBigDecimalMenorrDecimalesNumeroEspacioAfanumerico()	 {pruebaError(BigDecimal.class,"<25.2 a","25.2 a");}
	@Test public void test243ErrorNoBigDecimalMenorrDecimalesNumeroAlfanumericoIntercalado(){pruebaError(BigDecimal.class,"<2a5.2","2a5.2");}
	@Test public void test244ErrorNoBigDecimalMenorrDecimalesNumeroEmpiezaAlfanumerico()	 {pruebaError(BigDecimal.class,"<a25.2","<a25.2");}
	
	@Test
	public void test250MenorrEntero(){
		assertParameto(Integer.class,ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, "<25").get(),25,Operador.MENOR);
		assertParameto(Integer.class,ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, "< 25").get(),25,Operador.MENOR);
		assertParameto(Integer.class,ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, "< 25 ").get(),25,Operador.MENOR);
		assertParameto(Integer.class,ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, " < 25 ").get(),25,Operador.MENOR);
	}

	@Test public void test252ErrorNoEnteroMenorrBasico()						  {pruebaError(Integer.class,"<25a","25a");}
	@Test public void test253ErrorNoEnteroMenorrNumeroEspacioAfanumerico()	  {pruebaError(Integer.class,"<25 a","25 a");}
	@Test public void test254ErrorNoEnteroMenorrNumeroAlfanumericoIntercalado(){pruebaError(Integer.class,"<2a5","2a5");}
	@Test public void test254ErrorNoEnteroMenorrNumeroEmpiezaAlfanumerico()	  {pruebaError(Integer.class,"<a25","<a25");}
	@Test public void test255ErrorNoEnteroMenorrAlfanumerico()				  {pruebaError(Integer.class,"<asdf","<asdf");}
	@Test public void test256ErrorNoEnteroMenorrVacio()						  {pruebaError(Integer.class,"<","<");}
	@Test public void test257ErrorNoEnteroSoloAlfanumerico()				  {pruebaError(Integer.class,"a","a");}
	@Test public void test258ErrorNoEnteroMenorrDecimal()					  {pruebaError(Integer.class,"<25.2","25.2");}
	// TEST MENOR IGUAL
	
	@Test
	public void test261MenorIgualLong(){
	  assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "<=25").get(),25L,Operador.MENORIGUAL);
	  assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "<= 25").get(),25L,Operador.MENORIGUAL);
	  assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "<= 25 ").get(),25L,Operador.MENORIGUAL);
	  assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, " <= 25 ").get(),25L,Operador.MENORIGUAL);
	}

	@Test public void test262ErrorNoLongMenorIgualBasico()						{pruebaError(Long.class,"<=25a","25a");}
	@Test public void test263ErrorNoLongMenorIgualNumeroEspacioAfanumerico()		{pruebaError(Long.class,"<=25 a","25 a");}
	@Test public void test264ErrorNoLongMenorIgualNumeroAlfanumericoIntercalado(){pruebaError(Long.class,"<=2a5","2a5");}
	@Test public void test264ErrorNoLongMenorIgualNumeroEmpiezaAlfanumerico()	{pruebaError(Long.class,"<=a25","<=a25");}
	@Test public void test265ErrorNoLongMenorIgualAlfanumerico()					{pruebaError(Long.class,"<=asdf","<=asdf");}
	@Test public void test266ErrorNoLongMenorIgualVacio()						{pruebaError(Long.class,"<=","<=");}
	@Test public void test268ErrorNoLongMenorIgualDecimal()						{pruebaError(Long.class,"<=25.2","25.2");}

	@Test
	public void test270MenorIgualReal(){
	  assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "<=25").get(),25D,Operador.MENORIGUAL);
	  assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "<= 25").get(),25D,Operador.MENORIGUAL);
	  assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "<= 25 ").get(),25D,Operador.MENORIGUAL);
	  assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, " <= 25 ").get(),25D,Operador.MENORIGUAL);
	  
	  assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "<=25.2").get(),25.2D,Operador.MENORIGUAL);
	  assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "<= 25.2").get(),25.2D,Operador.MENORIGUAL);
	  assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "<= 25.2 ").get(),25.2D,Operador.MENORIGUAL);
	  assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, " <= 25.2 ").get(),25.2D,Operador.MENORIGUAL);
	}

	@Test public void test271ErrorNoRealMenorIgualBasico()						{pruebaError(Double.class,"<=25a","25a");}
	@Test public void test272ErrorNoRealMenorIgualNumeroEspacioAfanumerico()		{pruebaError(Double.class,"<=25 a","25 a");}
	@Test public void test273ErrorNoRealMenorIgualNumeroAlfanumericoIntercalado(){pruebaError(Double.class,"<=2a5","2a5");}
	@Test public void test274ErrorNoRealMenorIgualNumeroEmpiezaAlfanumerico()	{pruebaError(Double.class,"<=a25","<=a25");}
	@Test public void test275ErrorNoRealMenorIgualAlfanumerico()					{pruebaError(Double.class,"<=asdf","<=asdf");}
	@Test public void test276ErrorNoRealMenorIgualVacio()						{pruebaError(Double.class,"<=","<=");}


	@Test public void test281ErrorNoRealMenorIgualDecimalesBasico()						 {pruebaError(Double.class,"<=25.2a","25.2a");}
	@Test public void test282ErrorNoRealMenorIgualDecimalesNumeroEspacioAfanumerico()	 {pruebaError(Double.class,"<=25.2 a","25.2 a");}
	@Test public void test283ErrorNoRealMenorIgualDecimalesNumeroAlfanumericoIntercalado(){pruebaError(Double.class,"<=2a5.2","2a5.2");}
	@Test public void test284ErrorNoRealMenorIgualDecimalesNumeroEmpiezaAlfanumerico()	 {pruebaError(Double.class,"<=a25.2","<=a25.2");}

	@Test
	public void test290MenorIgualBigDecimal(){
	  assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "<=25").get(),new BigDecimal("25"),Operador.MENORIGUAL);
	  assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "<= 25").get(),new BigDecimal("25"),Operador.MENORIGUAL);
	  assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "<= 25 ").get(),new BigDecimal("25"),Operador.MENORIGUAL);
	  assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, " <= 25 ").get(),new BigDecimal("25"),Operador.MENORIGUAL);
	  
	  assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "<=25.2").get(),new BigDecimal("25.2"),Operador.MENORIGUAL);
	  assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "<= 25.2").get(),new BigDecimal("25.2"),Operador.MENORIGUAL);
	  assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "<= 25.2 ").get(),new BigDecimal("25.2"),Operador.MENORIGUAL);
	  assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, " <= 25.2 ").get(),new BigDecimal("25.2"),Operador.MENORIGUAL);
	}

	@Test public void test291ErrorNoBigDecimalMenorIgualBasico()						{pruebaError(BigDecimal.class,"<=25a","25a");}
	@Test public void test292ErrorNoBigDecimalMenorIgualNumeroEspacioAfanumerico()		{pruebaError(BigDecimal.class,"<=25 a","25 a");}
	@Test public void test293ErrorNoBigDecimalMenorIgualNumeroAlfanumericoIntercalado(){pruebaError(BigDecimal.class,"<=2a5","2a5");}
	@Test public void test294ErrorNoBigDecimalMenorIgualNumeroEmpiezaAlfanumerico()	{pruebaError(BigDecimal.class,"<=a25","<=a25");}
	@Test public void test295ErrorNoBigDecimalMenorIgualAlfanumerico()					{pruebaError(BigDecimal.class,"<=asdf","<=asdf");}
	@Test public void test296ErrorNoBigDecimalMenorIgualVacio()						{pruebaError(BigDecimal.class,"<=","<=");}
	@Test public void test297ErrorNoBigDecimalSoloAlfanumerico()					{pruebaError(BigDecimal.class,"a","a");}

	@Test public void test301ErrorNoBigDecimalMenorIgualDecimalesBasico()						 {pruebaError(BigDecimal.class,"<=25.2a","25.2a");}
	@Test public void test302ErrorNoBigDecimalMenorIgualDecimalesNumeroEspacioAfanumerico()	 {pruebaError(BigDecimal.class,"<=25.2 a","25.2 a");}
	@Test public void test303ErrorNoBigDecimalMenorIgualDecimalesNumeroAlfanumericoIntercalado(){pruebaError(BigDecimal.class,"<=2a5.2","2a5.2");}
	@Test public void test304ErrorNoBigDecimalMenorIgualDecimalesNumeroEmpiezaAlfanumerico()	 {pruebaError(BigDecimal.class,"<=a25.2","<=a25.2");}
	
	// LIKE
    @Test
	public void test400Like(){
		assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "23*").get(),"23%",Operador.LIKE);
		assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "*23*").get(),"%23%",Operador.LIKE);
		assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "*23*as").get(),"%23%as",Operador.LIKE);
		assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "*23*rd*").get(),"%23%rd%",Operador.LIKE);
		assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "*23*rd*Péñ`^Ç.,áíóúäöüïë{}[]_-/").get(),"%23%rd%Péñ`^Ç.,áíóúäöüïë{}[]_-/",Operador.LIKE);
		assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "david*").get(),"david%",Operador.LIKE);
		assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, " da vid*").get()," da vid%",Operador.LIKE);
		assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, " da vid*  per *").get()," da vid%  per %",Operador.LIKE);
		assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "*").get(),"%",Operador.LIKE);
	}
	// IGUAL
    @Test
    public void test500Igual(){
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "23").get(),"23",Operador.IGUAL);
    	assertParameto(Integer.class,ParserFilterUtils.obtenerInstancia().parser(null, Integer.class, "23").get(),23,Operador.IGUAL);
    	assertParameto(Long.class,ParserFilterUtils.obtenerInstancia().parser(null, Long.class, "23").get(),23L,Operador.IGUAL);
    	assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "23").get(),23D,Operador.IGUAL);
    	assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "23").get(),new BigDecimal("23"),Operador.IGUAL);
    	assertParameto(Double.class,ParserFilterUtils.obtenerInstancia().parser(null, Double.class, "23.2").get(),23.2D,Operador.IGUAL);
    	assertParameto(BigDecimal.class,ParserFilterUtils.obtenerInstancia().parser(null, BigDecimal.class, "23.2").get(),new BigDecimal("23.2"),Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "david áéíóúäëïöü{}[]`+?¿=!·$%&/()").get(),"david áéíóúäëïöü{}[]`+?¿=!·$%&/()",Operador.IGUAL);
    	
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, ">a").get(),">a",Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, ">=a").get(),">=a",Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "<a").get(),"<a",Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "<=a").get(),"<=a",Operador.IGUAL);

    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "> a").get(),"> a",Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, ">= a").get(),">= a",Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "< a").get(),"< a",Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, "<= a").get(),"<= a",Operador.IGUAL);

    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, " > a").get()," > a",Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, " >= a").get()," >= a",Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, " < a").get()," < a",Operador.IGUAL);
    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, " <= a").get()," <= a",Operador.IGUAL);

    	assertParameto(String.class,ParserFilterUtils.obtenerInstancia().parser(null, String.class, " david <><=>=").get()," david <><=>=",Operador.IGUAL);
    }
	private <T extends Number> void pruebaError(Class<T> clase,String patron,String resultado){
		errorObtenerValorNumerico(resultado);
		ParserFilterUtils.obtenerInstancia().parser(null, clase, patron);
	}
	
	private void errorObtenerValorNumerico(String patron) {
		thrown.expect(RuntimeException.class);
		thrown.expectMessage("error con el tipo de datos no valido ("+patron+")");
		thrown.expectCause(CoreMatchers.isA(NumberFormatException.class));
	}
	
	private <T> void assertParameto(Class<T> classe,ParametrosCriteria<T> parametros,T vale,Operador operador){
		assertEquals("Se espera el número",parametros.valor,vale);
		assertEquals("Se espera el operador",parametros.operador,operador);
		assertTrue("No se epsera",parametros.parametro==null);
	}
}

