package org.dbp.dao;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.dbp.bom.localizacion.Provincia;
import org.dbp.core.config.TestConfiguracion;
import org.dbp.dto.localizacion.ProvinciaFiltro;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestConfiguracion.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProvinciaDaoTest {

	@Autowired private ProvinciaDao dao;
	
	@Test
	public void test001Consultar(){
		final Provincia provincia = dao.obtenerId(1);
		assertProvinciaAlava(provincia);
	}
	
	@Test
	public void test002FiltroFull(){
		final ProvinciaFiltro provinciaFiltro = new ProvinciaFiltro();
		List<Provincia> provincias = dao.consultarFiltro(provinciaFiltro);
		assertEquals("Se esperan 52 provincias",52,provincias.size());
	}
	
	@Test
	public void test003FiltroIdMenor(){
		final ProvinciaFiltro provinciaFiltro = new ProvinciaFiltro();
		provinciaFiltro.setId("<2");
		List<Provincia> provincias = dao.consultarFiltro(provinciaFiltro);
		assertEquals("Se esperan 1 provincias",1,provincias.size());
		assertProvinciaAlava(provincias.get(0));
	}
	@Test
	public void test004FiltroDescripcion(){
		final ProvinciaFiltro provinciaFiltro = new ProvinciaFiltro();
		provinciaFiltro.setNombre("Araba*");
		List<Provincia> provincias = dao.consultarFiltro(provinciaFiltro);
		assertEquals("Se esperan 1 provincias",1,provincias.size());
		assertProvinciaAlava(provincias.get(0));
	}
	
	@Test
	public void test005FiltrComunidadAutonoma(){
		final ProvinciaFiltro provinciaFiltro = new ProvinciaFiltro();
		provinciaFiltro.setComunidadAutonoma("*Vasco*");
		List<Provincia> provincias = dao.consultarFiltro(provinciaFiltro);
		assertEquals("Se esperan 3 provincias",3,provincias.size());
		assertThat("",provincias,hasItem(hasProperty("nombre",equalTo("Araba/Álava"))));
		assertThat("",provincias,hasItem(hasProperty("nombre",equalTo("Gipuzkoa"))));
		assertThat("",provincias,hasItem(hasProperty("nombre",equalTo("Bizkaia"))));
	}
	
	private void assertProvinciaAlava(final Provincia provincia) {
		assertEquals("Descripcion","Araba/Álava",provincia.getNombre());
		assertEquals("Comunidad autonoma","País Vasco",provincia.getComunidadAutonoma().getNombre());
		assertEquals("Id Comunidad autonoma",Long.valueOf(16),provincia.getComunidadAutonoma().getId());
	}

}
