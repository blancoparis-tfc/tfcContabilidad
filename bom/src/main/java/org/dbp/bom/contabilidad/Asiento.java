package org.dbp.bom.contabilidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

@SuppressWarnings("serial")
@NamedQueries({
	@NamedQuery(
		name="asiento.filtro",
		query="from Asiento")
})
@Entity
public class Asiento implements Serializable{

	@Id
	@TableGenerator(name = "ASIENTOS_GEN",
     table = "SECUENCIAS", pkColumnName = "SEQ_NAME",
     valueColumnName = "SEQ_ASIENTOS",  pkColumnValue = "SEQUENCE"
     ,allocationSize=1,initialValue=1000)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ASIENTOS_GEN")
	private Long id;
	
	private String descripcion;
	@OneToMany(mappedBy="asiento",orphanRemoval=true,cascade={CascadeType.ALL})
	private List<LineaAsiento> lineas;
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
	public List<LineaAsiento> getLineas() {
		return lineas;
	}
	public void setLineas(List<LineaAsiento> lineas) {
		this.lineas = lineas;
	}
	
}
