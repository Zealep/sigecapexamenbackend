package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sgc_tm_tipo_docu_identidad")
public class TipoDocumentosIdentidad {
	
	@Id
	@Column(name = "id_tipo_docu_identidad")
	private int idTipoDocumentoIdentidad;
	
	@Column(name = "no_tipo_docu_identidad")
	private String noTipoDocumentoIdentidad;

	@Column(name = "sigla")
	private String sigla;
	
	@Column(name = "estado")
	private String estado;
	public int getIdTipoDocumentoIdentidad() {
		return idTipoDocumentoIdentidad;
	}

	public void setIdTipoDocumentoIdentidad(int idTipoDocumentoIdentidad) {
		this.idTipoDocumentoIdentidad = idTipoDocumentoIdentidad;
	}

	public String getNoTipoDocumentoIdentidad() {
		return noTipoDocumentoIdentidad;
	}

	public void setNoTipoDocumentoIdentidad(String noTipoDocumentoIdentidad) {
		this.noTipoDocumentoIdentidad = noTipoDocumentoIdentidad;
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
