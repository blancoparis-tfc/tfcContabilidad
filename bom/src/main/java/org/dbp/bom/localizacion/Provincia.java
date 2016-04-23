package org.dbp.bom.localizacion;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@SuppressWarnings("serial")
@Entity
public class Provincia implements Serializable{

	@Id
	@Column(length=2)
	private Integer id;
	@Column(updatable=false,insertable=false)
	private String nombre;
	
	@ManyToOne
	@JoinColumn(name="comunidadId",updatable=false,insertable=false)
	private ComunidadAutonoma comunidadAutonoma;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ComunidadAutonoma getComunidadAutonoma() {
		return comunidadAutonoma;
	}

	public void setComunidadAutonoma(ComunidadAutonoma comunidadAutonoma) {
		this.comunidadAutonoma = comunidadAutonoma;
	}
	

}
