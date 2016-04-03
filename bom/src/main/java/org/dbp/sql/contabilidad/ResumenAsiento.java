package org.dbp.sql.contabilidad;

import java.io.Serializable;
import java.math.BigDecimal;

public class ResumenAsiento implements Serializable{

	private Long id;
	private String descripcion;
	private BigDecimal saldo;
	public ResumenAsiento(Long id, String descripcion) {
		this(id,descripcion,BigDecimal.valueOf(0D));
	}
	public ResumenAsiento(Long id, String descripcion, BigDecimal saldo) {
		super();
		this.id = id;
		this.descripcion = descripcion;
		this.saldo = saldo;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getSaldo() {
		return saldo;
	}
	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
	
	
	
}
