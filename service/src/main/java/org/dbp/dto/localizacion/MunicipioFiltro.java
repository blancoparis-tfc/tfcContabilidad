package org.dbp.dto.localizacion;


public class MunicipioFiltro {
		private String id;
		private String municipio;
		private String provincia;
		private String comunidadAutonoma;
		
		public String getProvincia() {
			return provincia;
		}
		public void setProvincia(String provincia) {
			this.provincia = provincia;
		}
		public String getComunidadAutonoma() {
			return comunidadAutonoma;
		}
		public void setComunidadAutonoma(String comunidadAutonoma) {
			this.comunidadAutonoma = comunidadAutonoma;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getMunicipio() {
			return municipio;
		}
		public void setMunicipio(String municipio) {
			this.municipio = municipio;
		}
	
}
