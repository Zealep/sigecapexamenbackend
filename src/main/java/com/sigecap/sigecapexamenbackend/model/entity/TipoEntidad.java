package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sgc_tm_tipo_entidad")
public class TipoEntidad {
	
	@Id
	@Column(name = "id_tipo_entidad")
	private String idTipoEntidad;
	
	@Column(name = "no_tipo_entidad")
	private String nombreTipoEntidad;
	
	@Column(name = "sigla")
	private String sigla;
	
	@Column(name = "estado")
	private String estado;

	public String getIdTipoEntidad() {
		return idTipoEntidad;
	}

	public void setIdTipoEntidad(String idTipoEntidad) {
		this.idTipoEntidad = idTipoEntidad;
	}

	public String getNombreTipoEntidad() {
		return nombreTipoEntidad;
	}

	public void setNombreTipoEntidad(String nombreTipoEntidad) {
		this.nombreTipoEntidad = nombreTipoEntidad;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	

	
}
