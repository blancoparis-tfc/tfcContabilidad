package org.dbp.json.comun;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ResultadoJson implements Serializable{

	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
}
