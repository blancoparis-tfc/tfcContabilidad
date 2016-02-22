package org.dbp.controller.json;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

@SuppressWarnings("serial")
public class EjemploJson implements Serializable{

	public interface Resumen{};

	public interface Resumen2 extends Resumen{}
	@JsonView(Resumen.class)
	private Long numero;
	
	private String cadena;
	@JsonView(Resumen2.class)
	private String descripcion;
	
	private Date fecha;
	
	private LocalDate fechaLocal;
	
	private LocalDateTime fechaTime;
	
	
	public LocalDateTime getFechaTime() {
		return fechaTime;
	}
	public void setFechaTime(final LocalDateTime fechaTime) {
		this.fechaTime = fechaTime;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(final Date fecha) {
		this.fecha = fecha;
	}
	public LocalDate getFechaLocal() {
		return fechaLocal;
	}
	public void setFechaLocal(final LocalDate fechaLocal) {
		this.fechaLocal = fechaLocal;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(final String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getNumero() {
		return numero;
	}
	public void setNumero(final Long numero) {
		this.numero = numero;
	}
	public String getCadena() {
		return cadena;
	}
	public void setCadena(final String cadena) {
		this.cadena = cadena;
	}
	@Override
	public String toString() {
		return "EjemploJson [numero=" + numero + ", cadena=" + cadena + "]";
	}
	
}
