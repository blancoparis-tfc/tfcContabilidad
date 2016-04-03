package org.dbp.dto.contabilidad;

import java.io.Serializable;

public class AsientoFiltro implements Serializable{

	private String id;
	private String descripcion;
	private String cuenta;
	private String concepto;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descipcion) {
		this.descripcion = descipcion;
	}
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getConcepto() {
		return concepto;
	}
	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}
	
	
	
}
