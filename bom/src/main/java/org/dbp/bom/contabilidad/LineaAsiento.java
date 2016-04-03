package org.dbp.bom.contabilidad;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

import org.dbp.bom.contabilidad.enums.TipoMovimientoContable;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
@Entity	
public class LineaAsiento implements Serializable{

	@Id
	@TableGenerator(name = "LINEA_ASIENTOS_GEN",
    table = "SECUENCIAS2", pkColumnName = "SEQ_NAME",
    valueColumnName = "SEQ_LINEA_ASIENTO",  pkColumnValue = "SEQUENCE"
    ,allocationSize=1,initialValue=1000)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LINEA_ASIENTOS_GEN")
	private Long id;
	@ManyToOne
	@JoinColumn(name="cuenta")
	private CuentaContable cuenta;
	@Enumerated(EnumType.STRING)
	private TipoMovimientoContable tipoMovimientoContable;
	
	private BigDecimal importe;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="asientoId")
	private Asiento asiento;
	
	private String concepto;

	
	
	public LineaAsiento() {
		super();
	}

	
	
	public LineaAsiento(CuentaContable cuenta,
			TipoMovimientoContable tipoMovimientoContable, BigDecimal importe,
			Asiento asiento, String concepto) {
		super();
		this.cuenta = cuenta;
		this.tipoMovimientoContable = tipoMovimientoContable;
		this.importe = importe;
		this.asiento = asiento;
		this.concepto = concepto;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public CuentaContable getCuenta() {
		return cuenta;
	}

	public void setCuenta(CuentaContable cuenta) {
		this.cuenta = cuenta;
	}

	public TipoMovimientoContable getTipoMovimientoContable() {
		return tipoMovimientoContable;
	}

	public void setTipoMovimientoContable(
			TipoMovimientoContable tipoMovimientoContable) {
		this.tipoMovimientoContable = tipoMovimientoContable;
	}

	public BigDecimal getImporte() {
		return importe;
	}

	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}

	public Asiento getAsiento() {
		return asiento;
	}

	public void setAsiento(Asiento asiento) {
		this.asiento = asiento;
	}

	
}
