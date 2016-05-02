package org.dbp.dto.localizacion;


public class DatosDeContactoFiltro {
		private String id;
		private String telefono;
		private String nombre;
		private String direccionDeCorreo;
	
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getTelefono() {
			return telefono;
		}
		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getDireccionDeCorreo() {
			return direccionDeCorreo;
		}
		public void setDireccionDeCorreo(String direccionDeCorreo) {
			this.direccionDeCorreo = direccionDeCorreo;
		}
	
}
