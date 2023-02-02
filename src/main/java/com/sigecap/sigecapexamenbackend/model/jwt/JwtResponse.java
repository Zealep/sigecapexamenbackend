package com.sigecap.sigecapexamenbackend.model.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwttoken;
	private final String idTrabajador;
	private final String nombre;


	public JwtResponse(String jwttoken,String idTrabajador,String nombre) {
		this.jwttoken = jwttoken;
		this.idTrabajador = idTrabajador;
		this.nombre = nombre;
	}

	public String getToken() {
		return this.jwttoken;
	}

	public String getIdUsuario(){
		return this.idTrabajador;
	}

	public String getNombre() {
		return this.nombre;
	}
}