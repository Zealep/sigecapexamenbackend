package com.sigecap.sigecapexamenbackend.model.dto;

import java.util.List;

import com.sigecap.sigecapexamenbackend.model.entity.Participante;



public class ParticipanteDto{
	
	
	List<ValidacionDto> listValidacion;

	List<Participante> listParticipante;


	public List<Participante> getListParticipante() {
		return listParticipante;
	}

	public void setListParticipante(List<Participante> listParticipante) {
		this.listParticipante = listParticipante;
	}

	public List<ValidacionDto> getListValidacion() {
		return listValidacion;
	}

	public void setListValidacion(List<ValidacionDto> listValidacion) {
		this.listValidacion = listValidacion;
	}

}
