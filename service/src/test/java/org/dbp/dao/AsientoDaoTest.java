package org.dbp.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.dbp.bom.contabilidad.Asiento;
import org.dbp.bom.contabilidad.CuentaContable;
import org.dbp.bom.contabilidad.LineaAsiento;
import org.dbp.bom.contabilidad.enums.TipoMovimientoContable;
import org.dbp.core.config.TestConfiguracion;
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
		final Asiento asiento = new Asiento();
		final CuentaContable cuentaHaber = cuentaContableDao.obtenerId("1000");
		final CuentaContable cuentaDeber = cuentaContableDao.obtenerId("2000");
		asiento.setDescripcion("Asiento con lineas");
		List<LineaAsiento> lineas = new ArrayList<LineaAsiento>();
		lineas.add(new LineaAsiento(
				cuentaHaber,TipoMovimientoContable.H,BigDecimal.valueOf(1000D)
				,asiento,"Prueba haber"));
		lineas.add(new LineaAsiento(
				cuentaDeber,TipoMovimientoContable.D,BigDecimal.valueOf(1000D)
				,asiento,"Prueba deber"));
		asiento.setLineas(lineas);
		asientoDao.crear(asiento);
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
	public void test015EliminarCuentasContables(){
		cuentaContableDao.eliminar(cuentaContableDao.obtenerId("1000"));
		cuentaContableDao.eliminar(cuentaContableDao.obtenerId("2000"));
	}
	
	@Test
	public void test016AssertEliminarCuentasContables(){
		assertNull("No se espera la cuenta 1000",cuentaContableDao.obtenerId("1000"));
		assertNull("No se espera la cuenta 2000",cuentaContableDao.obtenerId("2000"));
	}
}


