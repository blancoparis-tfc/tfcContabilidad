package org.dbp.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.dbp.bom.localizacion.ComunidadAutonoma;
import org.dbp.core.config.TestConfiguracion;
import org.dbp.dto.localizacion.ComunidadAutonomaFiltro;
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
public class ComunidadAutonomaDaoTest {

	@Autowired private ComunidadAutonomaDao dao;
	
	@Test
	public void test001Consultar(){
		final ComunidadAutonoma comunidadAutonoma = dao.obtenerId(1L);
		assertEquals("Descripcion","Andalucía",comunidadAutonoma.getNombre());
	}
	
	@Test
	public void test002FiltroFull(){
		final ComunidadAutonomaFiltro filtro = new ComunidadAutonomaFiltro();
		List<ComunidadAutonoma> resultado = dao.consultarFiltro(filtro);
		assertEquals("Se esperan 19 paises",19, resultado.size());
	}

	@Test
	public void test003FiltroIdMenor(){
		final ComunidadAutonomaFiltro filtro = new ComunidadAutonomaFiltro();
		filtro.setId("<2");
		List<ComunidadAutonoma> resultado = dao.consultarFiltro(filtro);
		assertEquals("Se esperan 1 paises",1, resultado.size());
		assertEquals("Descripcion","Andalucía",resultado.get(0).getNombre());
	}
	
	@Test
	public void test004FiltroDescripcion(){
		final ComunidadAutonomaFiltro filtro = new ComunidadAutonomaFiltro();
		filtro.setNombre("Andalu*");
		List<ComunidadAutonoma> resultado = dao.consultarFiltro(filtro);
		assertEquals("Se esperan 1 paises",1, resultado.size());
		assertEquals("Descripcion","Andalucía",resultado.get(0).getNombre());
	}
}
