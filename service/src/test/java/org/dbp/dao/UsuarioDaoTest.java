package org.dbp.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.dbp.bom.Usuario;
import org.dbp.core.config.TestConfiguracion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestConfiguracion.class)
public class UsuarioDaoTest {

	@Autowired private UsuarioDao usuarioDao;
	
	@Test
	public void testRecuperarTodos(){
		List<Usuario> usuarios=usuarioDao.obtenerTodos();
		assertEquals("Miramos el número de elementos",1,usuarios.size());
	}
	@Test
	public void testObtenerPorId(){
		Usuario usuario=usuarioDao.obtenerId(1L);
		assertEquals("Validamos el ID:",new Long(1L),usuario.getId());
		assertEquals("Validamos el loging","dblanco",usuario.getLogin());
	}

	@Test
	public void testObtenerLogin(){
		Usuario usuario=usuarioDao.obtenerLogin("dblanco");
		assertEquals("Validamos el ID:",new Long(1L),usuario.getId());
		assertEquals("Validamos el loging","dblanco",usuario.getLogin());
	}
}
