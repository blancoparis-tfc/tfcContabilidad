package org.dbp.json.contabilidad;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CuentaContableJson implements Serializable {
	
	private String cuenta;
	
	private String descripcion;

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
