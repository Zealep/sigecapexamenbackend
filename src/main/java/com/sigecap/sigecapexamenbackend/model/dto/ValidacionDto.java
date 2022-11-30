package com.sigecap.sigecapexamenbackend.model.dto;

public class ValidacionDto{
	
	
	private int numeroItem;
	
	private int codigoSalida;

	private String mensajeSalida;


	public int getCodigoSalida() {
		return codigoSalida;
	}

	public void setCodigoSalida(int codigoSalida) {
		this.codigoSalida = codigoSalida;
	}

	public String getMensajeSalida() {
		return mensajeSalida;
	}

	public void setMensajeSalida(String mensajeSalida) {
		this.mensajeSalida = mensajeSalida;
	}

	public int getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(int numeroItem) {
		this.numeroItem = numeroItem;
	}
	
}
