package org.dbp.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.dbp.bom.Usuario;
import org.dbp.core.dao.impl.GenericDaoImpl;
import org.dbp.dao.UsuarioDao;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDaoImpl  
	extends GenericDaoImpl<Usuario,Long> 
	implements UsuarioDao{

	@PersistenceContext private EntityManager entityManager;
	
	public UsuarioDaoImpl() {
		super(Usuario.class);
	}
	
	@Override
	public Usuario obtenerLogin(final String login){
		return entityManager.createQuery("from Usuario u where u.login = :login",Usuario.class)
		.setParameter("login", login)
		.getSingleResult();
	}
	
}
