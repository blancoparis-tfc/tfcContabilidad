package org.dbp.bom.localizacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@SuppressWarnings("serial")
@Entity
public class Municipio implements Serializable{
	
	@Id
	private Long id;
	@Column(updatable=false,insertable=false)
	private String municipio;
	@ManyToOne()
	@JoinColumn(name="provinciaId",updatable=false,insertable=false)
	private Provincia provincia;

	public Provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMunicipio() {
		return municipio;
	}
	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

}
