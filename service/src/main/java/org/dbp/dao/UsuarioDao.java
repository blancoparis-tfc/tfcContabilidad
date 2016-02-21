package org.dbp.dao;

import org.dbp.bom.Usuario;
import org.dbp.core.dao.GenericDao;

public interface UsuarioDao extends GenericDao<Usuario,Long> {
	/**
	 * 
	 * Nos devuelve el usuario que tiene ese login.
	 * 
	 * @param login	Es el login del usuario
	 * @return
	 */
	public Usuario obtenerLogin(String login);
}
