package org.dbp.core.config;

import static org.junit.Assert.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dbp.bom.Usuario;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
/**
 * Test para validar la configuración del JPA.
 * @author david
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestConfiguracion.class)
public class ConfigurationJpaTest {

	@PersistenceContext private EntityManager entiyManager;
	
	@Test
	public void testRecuperarUnUsuario(){
		final Usuario usuario=entiyManager.find(Usuario.class, 1L);
		assertEquals("El id del usuario 1",Long.valueOf(1L),usuario.getId());
		assertEquals("El login del usaurio","dblanco",usuario.getLogin());
		assertEquals("El nombre","david blanco paris",usuario.getNombre());
	}
	
	@Test
	public void testInsertarUsuario(){//NOPMD
		final Usuario usuario=new Usuario();
		usuario.setId(2L);
		usuario.setLogin("perico");
		usuario.setNombre("Perico el de los palotes");
		entiyManager.persist(usuario);
	}
	
}
