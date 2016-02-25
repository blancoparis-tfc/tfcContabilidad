package org.dbp.bom.contabilidad;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class CuentaContable implements Serializable{ //NOPMD
	
	@Id
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
