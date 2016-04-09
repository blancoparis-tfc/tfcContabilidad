package org.dbp.bom.localizacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Pais implements Serializable{

	@Id
	@Column(length=2,updatable=false,insertable=false)
	private String idAlfa2;

	@Column(length=3,updatable=false,insertable=false)
	private String codAlfa3;
	@Column(length=3,updatable=false,insertable=false)
	private Integer codNumerico;  

	@Column(updatable=false,insertable=false)
	private String nombreComun;
	@Column(updatable=false,insertable=false)
	private String nombreOficial;
	public String getIdAlfa2() {
		return idAlfa2;
	}
	public void setIdAlfa2(String idAlfa2) {
		this.idAlfa2 = idAlfa2;
	}
	public String getCodAlfa3() {
		return codAlfa3;
	}
	public void setCodAlfa3(String codAlfa3) {
		this.codAlfa3 = codAlfa3;
	}
	public Integer getCodNumerico() {
		return codNumerico;
	}
	public void setCodNumerico(Integer codNumerico) {
		this.codNumerico = codNumerico;
	}
	public String getNombreComun() {
		return nombreComun;
	}
	public void setNombreComun(String nombreComun) {
		this.nombreComun = nombreComun;
	}
	public String getNombreOficial() {
		return nombreOficial;
	}
	public void setNombreOficial(String nombreOficial) {
		this.nombreOficial = nombreOficial;
	}

	
}
