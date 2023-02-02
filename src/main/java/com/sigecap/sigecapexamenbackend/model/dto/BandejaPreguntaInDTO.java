package com.sigecap.sigecapexamenbackend.model.dto;

public class BandejaPreguntaInDTO {
    private String idCurso;
    private String nombrePregunta;

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombrePregunta() {
        return nombrePregunta;
    }

    public void setNombrePregunta(String nombrePregunta) {
        this.nombrePregunta = nombrePregunta;
    }
}
