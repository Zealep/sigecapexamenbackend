package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sgc_tm_unidad")
public class Unidad {
	
	@Id
	@Column(name = "id_unidad")
	private String idUnidad;
	
	@Column(name = "no_unidad")
	private String noUnidad;

	@Column(name = "orden_mostar")
	private int orderMostar;
	
	@Column(name = "estado")
	private String estado;

	public String getIdUnidad() {
		return idUnidad;
	}

	public void setIdUnidad(String idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getNoUnidad() {
		return noUnidad;
	}

	public void setNoUnidad(String noUnidad) {
		this.noUnidad = noUnidad;
	}

	public int getOrderMostar() {
		return orderMostar;
	}

	public void setOrderMostar(int orderMostar) {
		this.orderMostar = orderMostar;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}



	
}
