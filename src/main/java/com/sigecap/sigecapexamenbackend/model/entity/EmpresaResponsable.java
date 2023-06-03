package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="sgc_tz_empresa_responsable")
public class EmpresaResponsable {

	
	@Id
	@Column(name="id_empresa_responsable")
	private String idEmpresaResponsable;
	
	@Column(name="id_entidad_empresa")
	private String idEntidadEmpresa;

	
	@Column(name="id_entidad_responsable")
	private String idEntidadResponsable;
		
	@Column(name="estado")
	private String estado;

	public String getIdEmpresaResponsable() {
		return idEmpresaResponsable;
	}

	public void setIdEmpresaResponsable(String idEmpresaResponsable) {
		this.idEmpresaResponsable = idEmpresaResponsable;
	}

	public String getIdEntidadEmpresa() {
		return idEntidadEmpresa;
	}

	public void setIdEntidadEmpresa(String idEntidadEmpresa) {
		this.idEntidadEmpresa = idEntidadEmpresa;
	}

	public String getIdEntidadResponsable() {
		return idEntidadResponsable;
	}

	public void setIdEntidadResponsable(String idEntidadResponsable) {
		this.idEntidadResponsable = idEntidadResponsable;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
}
