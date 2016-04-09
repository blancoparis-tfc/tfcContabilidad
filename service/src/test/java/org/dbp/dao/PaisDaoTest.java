package org.dbp.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.dbp.bom.localizacion.Pais;
import org.dbp.core.config.TestConfiguracion;
import org.dbp.dto.localizacion.PaisFiltro;
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
public class PaisDaoTest {

	@Autowired private PaisDao dao;
	
	@Commit
	@Test
	public void test001ModificarValores(){
		final Pais pais = dao.obtenerId("AF");
		assertEquals("Alfa 3","AFG",pais.getCodAlfa3());
		assertEquals("Codigo númerico",Integer.valueOf(4),pais.getCodNumerico());
		assertEquals("Nombre comun","Afganistán",pais.getNombreComun()); //NOPMD
		assertEquals("Nombre oficial","Afganistán",pais.getNombreOficial());
		pais.setCodAlfa3("A");
		pais.setCodNumerico(1);
		pais.setNombreComun("NC");
		pais.setNombreOficial("NOF");
		dao.actualizar(pais);
	}
	@Test
	public void test002RevisarValores(){
		final Pais pais = dao.obtenerId("AF");
		assertEquals("Alfa 3","AFG",pais.getCodAlfa3());
		assertEquals("Codigo númerico",Integer.valueOf(4),pais.getCodNumerico());
		assertEquals("Nombre comun","Afganistán",pais.getNombreComun());
		assertEquals("Nombre oficial","Afganistán",pais.getNombreOficial());
	}
	@Test
	public void test003FiltroFull(){
		final PaisFiltro paisFiltro = new PaisFiltro();
		List<Pais> resultado = dao.consultarFiltro(paisFiltro);
		assertEquals("Se esperan 229 paises",229, resultado.size());
	}
	
	@Test
	public void test004FiltroBuscarEspaniaIdAlfa2(){
		final PaisFiltro paisFiltro = new PaisFiltro();
		paisFiltro.setIdAlfa2("ES");
		List<Pais> resultado = dao.consultarFiltro(paisFiltro);
		assertEquals("Se espera un elemento",1,resultado.size());
		assertEspania(resultado.get(0));
	}
	
	@Test
	public void test005FiltroBuscarEspaniaCodAlfa3(){
		final PaisFiltro paisFiltro = new PaisFiltro();
		paisFiltro.setCodAlfa3("ESP");
		List<Pais> resultado = dao.consultarFiltro(paisFiltro);
		assertEquals("Se espera un elemento",1,resultado.size());
		assertEspania(resultado.get(0));
	}
	
	@Test
	public void test006FiltroBuscarEspaniaCodNumerico(){
		final PaisFiltro paisFiltro = new PaisFiltro();
		paisFiltro.setCodNumerico("724");
		List<Pais> resultado = dao.consultarFiltro(paisFiltro);
		assertEquals("Se espera un elemento",1,resultado.size());
		assertEspania(resultado.get(0));
	}
	
	@Test
	public void test007FiltroBuscarEspaniaNombreComun(){
		final PaisFiltro paisFiltro = new PaisFiltro();
		paisFiltro.setNombreComun("España");
		List<Pais> resultado = dao.consultarFiltro(paisFiltro);
		assertEquals("Se espera un elemento",1,resultado.size());
		assertEspania(resultado.get(0));
	}

	@Test
	public void test008FiltroBuscarEspaniaNombreOficial(){
		final PaisFiltro paisFiltro = new PaisFiltro();
		paisFiltro.setNombreOficial("España");
		List<Pais> resultado = dao.consultarFiltro(paisFiltro);
		assertEquals("Se espera un elemento",1,resultado.size());
		assertEspania(resultado.get(0));
	}
	
	@Test
	public void test009FiltroBuscarEspaniaMayor700(){
		final PaisFiltro paisFiltro = new PaisFiltro();
		paisFiltro.setCodNumerico(">700");
		List<Pais> resultado = dao.consultarFiltro(paisFiltro);
		assertEquals("Elemento 44",44,resultado.size());
	}
	
	private void assertEspania(Pais pais) {
		assertEquals("CodAlfa3","ESP",pais.getCodAlfa3());
		assertEquals("IdAlfa2","ES",pais.getIdAlfa2());
		assertEquals("Codígo númerico",Integer.valueOf(724),pais.getCodNumerico());
		assertEquals("Nombre común","España",pais.getNombreComun());
		assertEquals("Nombre oficial","España",pais.getNombreOficial());
	}
	
}
