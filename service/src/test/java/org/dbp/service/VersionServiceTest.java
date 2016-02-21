package org.dbp.service;

import static org.junit.Assert.assertEquals;

import org.dbp.core.config.TestConfiguracion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfiguracion.class)
public class VersionServiceTest {

	@Autowired VersionService versionService;
	
	@Test
	public void testVersion(){
		assertEquals("Es la version correcta","1.0.0",versionService.getVersion());
	}
	
}
