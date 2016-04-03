package org.dbp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.dbp.bom.contabilidad.Asiento;
import org.dbp.bom.contabilidad.CuentaContable;
import org.dbp.bom.contabilidad.LineaAsiento;
import org.dbp.bom.contabilidad.enums.TipoMovimientoContable;
import org.dbp.core.config.TestConfiguracion;
import org.dbp.dto.contabilidad.AsientoFiltro;
import org.dbp.sql.contabilidad.ResumenAsiento;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestConfiguracion.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AsientoDaoTest {

	private static final Long idAsientoSinLineas=1001L;
	private static final Long idAsientoConLineas=1002L;
	private static final Long idAsientoConLineasElem1=1003L;
	private static final Long idAsientoConLineasElem2=1004L;
	
	@Autowired private AsientoDao asientoDao;
	@Autowired private CuentaContableDao cuentaContableDao;
	
	@Commit
	@Test
	public void test001CrearAsientoSinLineas(){
		final Asiento asiento = new Asiento();
		asiento.setDescripcion("prueba");
		asientoDao.crear(asiento);
	}
	
	@Test
	public void test002AssertAsientoSinLineas(){
		final Asiento asiento = asientoDao.obtenerId(idAsientoSinLineas);
		assertEquals("Se espera la descripción","prueba",asiento.getDescripcion());
		assertTrue("No hay lineas",asiento.getLineas().isEmpty());
	}
	@Commit
	@Test
	public void test003EliminarElAsientoSinLineas(){
		asientoDao.eliminar(asientoDao.obtenerId(idAsientoSinLineas));
	}
	
	@Test
	public void test004AssertEliminarElAsientoSinLineas(){
		assertNull("No se espera el asiento",asientoDao.obtenerId(idAsientoSinLineas));
	}
	@Commit
	@Test
	public void test010CrearLasCuentaContables(){
		cuentaContableDao.crear(new CuentaContable("1000","Cuenta de prueba H"));
		cuentaContableDao.crear(new CuentaContable("2000","Cuenta de prueba D"));
	}
	
	@Commit
	@Test
	public void test011CrearAsientoConLineas(){
		asientoDao.crear(crearAsiento("Prueba","Asiento con lineas",BigDecimal.valueOf(1000D)));
	}


	@Test
	public void test012AssertAsientoConLineas(){
		final Asiento asiento = asientoDao.obtenerId(idAsientoConLineas);
		assertEquals("Los datos son","Asiento con lineas",asiento.getDescripcion());
		assertEquals("número de lineas",2,asiento.getLineas().size());
		final LineaAsiento lineaHaber=
				asiento.getLineas().stream()
				.filter(linea->linea.getTipoMovimientoContable().equals(TipoMovimientoContable.H))
				.findAny().get();
		final LineaAsiento lineaDeber=
				asiento.getLineas().stream()
				.filter(linea->linea.getTipoMovimientoContable().equals(TipoMovimientoContable.D))
				.findAny().get(); 
		assertEquals("Los ids",idAsientoConLineas,lineaHaber.getAsiento().getId());
		assertEquals("El concepto","Prueba haber",lineaHaber.getConcepto());
		assertEquals("La cuenta","1000",lineaHaber.getCuenta().getCuenta());
		assertEquals("El importe",new BigDecimal("1000.00"),lineaHaber.getImporte());
		assertEquals("El tipo de movimiento",TipoMovimientoContable.H,lineaHaber.getTipoMovimientoContable());
		
		assertEquals("Los ids",idAsientoConLineas,lineaDeber.getAsiento().getId());
		assertEquals("El concepto","Prueba deber",lineaDeber.getConcepto());
		assertEquals("La cuenta","2000",lineaDeber.getCuenta().getCuenta());
		assertEquals("El importe",new BigDecimal("1000.00"),lineaDeber.getImporte());
		assertEquals("El tipo de movimiento",TipoMovimientoContable.D,lineaDeber.getTipoMovimientoContable());
	}
	
	@Commit
	@Test
	public void test013EliminarElAsiento(){
		asientoDao.eliminar(asientoDao.obtenerId(idAsientoConLineas));
	}
	
	@Test
	public void test014assertElAsientoConLineas(){
		assertNull("No se espera el asiento",asientoDao.obtenerId(idAsientoConLineas));
		// Validamos que no se han borrado las cuentas contables.
		assertNotNull("Se espera la cuenta 1000",cuentaContableDao.obtenerId("1000"));
		assertNotNull("Se espera la cuenta 2000",cuentaContableDao.obtenerId("2000"));
	}
	
	@Commit
	@Test
	public void test020CrearAsientoFiltro(){
		asientoDao.crear(crearAsiento("Primero","Asiento 1",BigDecimal.valueOf(1006D)));
		asientoDao.crear(crearAsiento("Segundo","Asiento 2",BigDecimal.valueOf(1010D)));
	}
	@Test
	public void test021AssertAsientosFiltro(){
		Asiento asientoElem1=asientoDao.obtenerId(idAsientoConLineasElem1);
		Asiento asientoElem2=asientoDao.obtenerId(idAsientoConLineasElem2);
		assertEquals("La descripción","Asiento 1",asientoElem1.getDescripcion());
		assertEquals("La descripción","Asiento 2",asientoElem2.getDescripcion());
	}
	
	// Las pruebas del filtrado
	@Test
	public void test022FiltroFull(){
		final AsientoFiltro asientoFiltro = new AsientoFiltro();
		List<Long> resultado = asientoDao.consultarFiltro(asientoFiltro).stream().map(elemento-> elemento.getId()).collect(Collectors.toList());
		assertEquals("Se esperan 2 elementos",2,resultado.size());
		assertThat("",resultado,contains(idAsientoConLineasElem1,idAsientoConLineasElem2));
	}
	
	@Test	public void test023FiltroMayorIdElemet1(){filtroId(">"+idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test	public void test024FiltroMayorIdElemet2(){filtroId(">"+idAsientoConLineasElem2);	}
	@Test	public void test025FiltroMayorIdCero(){	filtroId(">0",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	
	@Test	public void test026FiltroMayorIgualIdElemet1(){filtroId(">="+idAsientoConLineasElem1,idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test	public void test027FiltroMayorIgualIdElemet2(){filtroId(">="+idAsientoConLineasElem2,idAsientoConLineasElem2);	}
	@Test	public void test028FiltroMayorIgualIdCero(){	filtroId(">0",idAsientoConLineasElem1,idAsientoConLineasElem2);}

	@Test	public void test029FiltroMenorIdElemet1(){filtroId("<"+idAsientoConLineasElem1);}
	@Test	public void test030FiltroMenorIdElemet2(){filtroId("<"+idAsientoConLineasElem2,idAsientoConLineasElem1);	}
	@Test	public void test031FiltroMenorIdMayor(){  filtroId("<"+(idAsientoConLineasElem2+1),idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test	public void test032FiltroMenorIdCero(){   filtroId("<0");}

	@Test	public void test033FiltroMenorIgualIdElemet1(){filtroId("<="+idAsientoConLineasElem1,idAsientoConLineasElem1);}
	@Test	public void test034FiltroMenorIgualIdElemet2(){filtroId("<="+idAsientoConLineasElem2,idAsientoConLineasElem1,idAsientoConLineasElem2);	}
	@Test	public void test035FiltroMenorIgualIdMayor(){  filtroId("<="+(idAsientoConLineasElem2+1),idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test	public void test036FiltroMenorIgualIdCero(){   filtroId("<=0");}

	
	private void filtroId(String patron,Long... ids){
		final AsientoFiltro asientoFiltro = new AsientoFiltro();
		asientoFiltro.setId(patron);
		List<Long> resultado = asientoDao.consultarFiltro(asientoFiltro).stream().map(elemento-> elemento.getId()).collect(Collectors.toList());
		assertEquals("Se esperan "+ids.length+" elementos",ids.length,resultado.size());
		if(ids.length>0){
			assertThat("",resultado,contains(ids));
		}
	}
	
	@Test public void test050FiltroDescripcionEqualsAsiento1(){ 		 filtroDescirpcion("Asiento 1",idAsientoConLineasElem1);}
	@Test public void test051FiltroDescripcionEqualsAsiento2(){ 		 filtroDescirpcion("Asiento 2",idAsientoConLineasElem2);}
	@Test public void test052FiltroDescripcionEqualsAsiento2ConEspac(){  filtroDescirpcion("Asiento 2 ",idAsientoConLineasElem2);}
	@Test public void test053FiltroDescripcionEqualsAsiento2ConEspc2(){  filtroDescirpcion(" Asiento 2 ");}
	@Test public void test054FiltroDescripcionEqualsAsiento2EspcInter(){ filtroDescirpcion("Asiento  2");}
	@Test public void test055FiltroDescripcionLikeAsientoAsterisco(){ 	 filtroDescirpcion("Asiento*",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test056FiltroDescripcionLikeAAsterisco(){ 		 filtroDescirpcion("A*",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test057FiltroDescripcionLikeAsterisco1(){ 		 filtroDescirpcion("*1",idAsientoConLineasElem1);}
	@Test public void test058FiltroDescripcionLikeAsterisco2(){ 		 filtroDescirpcion("*2",idAsientoConLineasElem2);}
	@Test public void test059FiltroDescripcionLikesterisco(){ 			 filtroDescirpcion("*",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test060FiltroDescripcionLikeAsterisco1(){ 		 filtroDescirpcion("A*1",idAsientoConLineasElem1);}
	@Test public void test061FiltroDescripcionLikeAsterisco1Asterisco(){ filtroDescirpcion("A*1*",idAsientoConLineasElem1);}
	@Test public void test062FiltroDescripcionLikeAsterisco2(){ 		 filtroDescirpcion("A*2",idAsientoConLineasElem2);}
	@Test public void test063FiltroDescripcionLikeAsterisco2Asterisco(){ filtroDescirpcion("A*2*",idAsientoConLineasElem2);}
	@Test public void test064FiltroDescripcionVacio(){ 					 filtroDescirpcion("",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	
	private void filtroDescirpcion(String patron,Long... ids){
		final AsientoFiltro asientoFiltro = new AsientoFiltro();
		asientoFiltro.setDescripcion(patron);
		List<Long> resultado = asientoDao.consultarFiltro(asientoFiltro).stream().map(elemento-> elemento.getId()).collect(Collectors.toList());
		assertEquals("Se esperan "+ids.length+" elementos",ids.length,resultado.size());
		if(ids.length>0){
			assertThat("",resultado,contains(ids));
		}
	}
	
	@Test public void test070FiltroDescripcionEqualsCuenta1000(){	 filtroCuenta("1000",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test071FiltroDescripcionEqualsCuenta2000(){	 filtroCuenta("2000",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test072FiltroDescripcionEqualsCuenta2000(){	 filtroCuenta("3000");}
	@Test public void test073FiltroDescripcionLike1Asterisco(){		 filtroCuenta("1*",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test074FiltroDescripcionLike2Asterisco(){		 filtroCuenta("2*",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test075FiltroDescripcionLike3Asterisco(){		 filtroCuenta("3*");}
	@Test public void test076FiltroDescripcionLikeAsterisco(){		 filtroCuenta("*",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test077FiltroDescripcionLike2AsteriscoCero(){	 filtroCuenta("2*0",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test078FiltroDescripcionLike1AsteriscoCero(){	 filtroCuenta("1*0",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	
	private void filtroCuenta(String patron,Long... ids){
		final AsientoFiltro asientoFiltro = new AsientoFiltro();
		asientoFiltro.setCuenta(patron);
		List<Long> resultado = asientoDao.consultarFiltro(asientoFiltro).stream().map(elemento-> elemento.getId()).collect(Collectors.toList());
		assertEquals("Se esperan "+ids.length+" elementos",ids.length,resultado.size());
		if(ids.length>0){
			assertThat("",resultado,contains(ids));
		}
	}

	@Test public void test080FiltroDescripcionEqualsConceptoPrimeroH(){	 filtroConcepto("Primero haber",idAsientoConLineasElem1);}
	@Test public void test081FiltroDescripcionEqualsConceptoSegundoH(){	 filtroConcepto("Segundo haber",idAsientoConLineasElem2);}
	@Test public void test082FiltroDescripcionEqualsConceptoPrimeroD(){	 filtroConcepto("Primero deber",idAsientoConLineasElem1);}
	@Test public void test083FiltroDescripcionEqualsConceptoSegundoD(){	 filtroConcepto("Segundo deber",idAsientoConLineasElem2);}
	@Test public void test084FiltroDescripcionLikeConceptoPrimeroH(){	 filtroConcepto("Primero*",idAsientoConLineasElem1);}
	@Test public void test085FiltroDescripcionLikeConceptoSegundoH(){	 filtroConcepto("Segundo*",idAsientoConLineasElem2);}
	@Test public void test086FiltroDescripcionLikeConceptoH(){	 filtroConcepto("*haber",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	@Test public void test087FiltroDescripcionLikeConceptoD(){	 filtroConcepto("*deber",idAsientoConLineasElem1,idAsientoConLineasElem2);}
	
	private void filtroConcepto(String patron,Long... ids){
		final AsientoFiltro asientoFiltro = new AsientoFiltro();
		asientoFiltro.setConcepto(patron);
		List<Long> resultado = asientoDao.consultarFiltro(asientoFiltro).stream().map(elemento-> elemento.getId()).collect(Collectors.toList());
		assertEquals("Se esperan "+ids.length+" elementos",ids.length,resultado.size());
		if(ids.length>0){
			assertThat("",resultado,contains(ids));
		}
	}
	
	@Test
	public void test100todoCadenasVacias(){
		final AsientoFiltro asientoFiltro = new AsientoFiltro();
		asientoFiltro.setConcepto("");
		asientoFiltro.setId("");
		asientoFiltro.setConcepto("");
		asientoFiltro.setDescripcion("");
		List<Long> resultado = asientoDao.consultarFiltro(asientoFiltro).stream().map(elemento-> elemento.getId()).collect(Collectors.toList());
		assertEquals("Se esperan "+2+" elementos",2,resultado.size());
		assertThat("",resultado,contains(idAsientoConLineasElem1,idAsientoConLineasElem2));
	}
	
	@Commit
	@Test
	public void test228EliminarAsientos(){
		asientoDao.eliminar(asientoDao.obtenerId(idAsientoConLineasElem1));
		asientoDao.eliminar(asientoDao.obtenerId(idAsientoConLineasElem2));
	}
	
	public void test229AssetEliminarAsiento(){
		assertNull("No se espera el asiento",asientoDao.obtenerId(idAsientoConLineasElem1));
		assertNull("No se espera el asiento",asientoDao.obtenerId(idAsientoConLineasElem2));
	}
	
	@Commit
	@Test
	public void test998EliminarCuentasContables(){
		cuentaContableDao.eliminar(cuentaContableDao.obtenerId("1000"));
		cuentaContableDao.eliminar(cuentaContableDao.obtenerId("2000"));
	}
	
	@Test
	public void test999AssertEliminarCuentasContables(){
		assertNull("No se espera la cuenta 1000",cuentaContableDao.obtenerId("1000"));
		assertNull("No se espera la cuenta 2000",cuentaContableDao.obtenerId("2000"));
	}
	
	// Métodos privados
	
	private Asiento crearAsiento(String concepto,String descripcion,BigDecimal importe) {
		final Asiento asiento = new Asiento();
		final CuentaContable cuentaHaber = cuentaContableDao.obtenerId("1000");
		final CuentaContable cuentaDeber = cuentaContableDao.obtenerId("2000");
		asiento.setDescripcion(descripcion);
		List<LineaAsiento> lineas = new ArrayList<LineaAsiento>();
		lineas.add(new LineaAsiento(
				cuentaHaber,TipoMovimientoContable.H,importe
				,asiento,concepto+" haber"));
		lineas.add(new LineaAsiento(
				cuentaDeber,TipoMovimientoContable.D,importe
				,asiento,concepto+" deber"));
		asiento.setLineas(lineas);
		return asiento;
	}
	
}


