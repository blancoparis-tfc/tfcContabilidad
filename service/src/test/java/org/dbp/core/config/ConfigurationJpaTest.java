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

	@PersistenceContext EntityManager em;
	
	@Test
	public void testRecuperarUnUsuario(){
		Usuario usuario=em.find(Usuario.class, 1L);
		assertEquals("El id del usuario 1",new Long(1L),usuario.getId());
		assertEquals("El login del usaurio","dblanco",usuario.getLogin());
		assertEquals("El nombre","david blanco paris",usuario.getNombre());
	}
	
	@Test
	public void testInsertarUsuario(){
		Usuario usuario=new Usuario();
		usuario.setId(2L);
		usuario.setLogin("perico");
		usuario.setNombre("Perico el de los palotes");
		em.persist(usuario);
	}
	
}
