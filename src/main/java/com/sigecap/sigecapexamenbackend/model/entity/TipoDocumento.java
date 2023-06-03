package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sgc_tm_tipo_documento")
public class TipoDocumento {
	
	@Id
	@Column(name = "id_tipo_documento")
	private int idTipoDocumento;
	
	@Column(name = "no_tipo_documento")
	private String nombreTipoDocumento;
	
	@Column(name = "sigla")
	private String sigla;
	
	@Column(name = "estado")
	private String estado;

	@Column(name = "grupo")
	private String grupo;
	
	public int getIdTipoDocumento() {
		return idTipoDocumento;
	}

	public void setIdTipoDocumento(int idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
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

	public String getNombreTipoDocumento() {
		return nombreTipoDocumento;
	}

	public void setNombreTipoDocumento(String nombreTipoDocumento) {
		this.nombreTipoDocumento = nombreTipoDocumento;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	
}
