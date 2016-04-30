package org.dbp.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.dbp.bom.localizacion.Municipio;
import org.dbp.core.config.TestConfiguracion;
import org.dbp.dto.localizacion.MunicipioFiltro;
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
public class MunicipioDaoTest {

	@Autowired private MunicipioDao dao;
	
	@Test
	public void test001Full(){
		MunicipioFiltro filtro = new MunicipioFiltro();
		filtro.setId("");
		filtro.setMunicipio("");
		filtro.setProvincia("");
		filtro.setComunidadAutonoma("");
		List<Municipio> municipios=dao.consultarFiltro(filtro);
		assertEquals("Se esperan 8028 municipios",8028,municipios.size());
	}
	
}
