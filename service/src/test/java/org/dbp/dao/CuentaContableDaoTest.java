package org.dbp.dao;

import static org.junit.Assert.*;

import org.dbp.bom.contabilidad.CuentaContable;
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
public class CuentaContableDaoTest {

	 private static final String CLAVE_TEST = "0001";
	 @Autowired private CuentaContableDao dao;
	 
	 @Commit
	 @Test
	 public void test001InsertarCuenta(){
		final CuentaContable cuentaContable = new CuentaContable();
		cuentaContable.setCuenta(CLAVE_TEST);
		cuentaContable.setDescripcion("Datos prueba");
		dao.crear(cuentaContable);
		assertEquals("No se esperan cuentas ",1,dao.obtenerTodos().size());
	 }
	 
	 @Test
	 public void test002InsertarCuenta(){
		final CuentaContable cuentaContable = dao.obtenerId(CLAVE_TEST);
		assertEquals("La cuenta",CLAVE_TEST,cuentaContable.getCuenta());
		assertEquals("La descripcion","Datos prueba",cuentaContable.getDescripcion());
		assertEquals("No se esperan cuentas  ",1,dao.obtenerTodos().size());
	 }
	 @Commit
	 @Test
	 public void test003EliminarCuenta(){
		 assertEquals("No se  esperan cuentas",1,dao.obtenerTodos().size());
		 dao.eliminar(dao.obtenerId(CLAVE_TEST));
		 assertEquals("No se esperan  cuentas",0,dao.obtenerTodos().size());
	 }
	 @Test
	 public void test004MirarQueNoHayDatos(){
		 assertEquals(" No se esperan cuentas",0,dao.obtenerTodos().size());
	 }
}
