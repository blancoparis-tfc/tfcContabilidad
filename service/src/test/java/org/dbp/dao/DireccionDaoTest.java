package org.dbp.dao;

import static org.junit.Assert.*;

import org.dbp.bom.localizacion.Direccion;
import org.dbp.core.config.TestConfiguracion;
import org.junit.FixMethodOrder;
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
public class DireccionDaoTest {

	@Autowired private DireccionDao dao;
	
	
	
}
