package org.dbp.bom.persona;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.dbp.bom.localizacion.DatosDeContacto;
import org.dbp.bom.persona.enums.TipoDeIdentificadorFiscal;

@Entity
public class Persona implements Serializable{

	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable=false)
	private TipoDeIdentificadorFiscal tipoDeIdentificadorFiscal;
	
	@Column(nullable=false)
	private String identificadorFiscal;
	
	@OneToMany(orphanRemoval=true,cascade={CascadeType.ALL})
	private Set<DatosDeContacto> datosDeContacto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDeIdentificadorFiscal getTipoDeIdentificadorFiscal() {
		return tipoDeIdentificadorFiscal;
	}

	public void setTipoDeIdentificadorFiscal(TipoDeIdentificadorFiscal tipoDeIdentificadorFiscal) {
		this.tipoDeIdentificadorFiscal = tipoDeIdentificadorFiscal;
	}

	public String getIdentificadorFiscal() {
		return identificadorFiscal;
	}

	public void setIdentificadorFiscal(String identificadorFiscal) {
		this.identificadorFiscal = identificadorFiscal;
	}

	public Set<DatosDeContacto> getDatosDeContacto() {
		return datosDeContacto;
	}

	public void setDatosDeContacto(Set<DatosDeContacto> datosDeContacto) {
		this.datosDeContacto = datosDeContacto;
	}
	
}
