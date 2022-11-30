package com.sigecap.sigecapexamenbackend.model.dto;

public class BandejaRespuestasInDTO {

    private String idPregunta;
    private String nombreRespuesta;

    public String getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(String idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getNombreRespuesta() {
        return nombreRespuesta;
    }

    public void setNombreRespuesta(String nombreRespuesta) {
        this.nombreRespuesta = nombreRespuesta;
    }
}
