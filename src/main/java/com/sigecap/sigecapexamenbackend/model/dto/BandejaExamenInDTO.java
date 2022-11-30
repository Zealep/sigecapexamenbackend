package com.sigecap.sigecapexamenbackend.model.dto;

public class BandejaExamenInDTO {

    private String idCurso;
    private String nombreExamen;

    public String getIdCurso() {
        return idCurso;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;

    }
}
