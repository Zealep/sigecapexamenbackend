package com.sigecap.sigecapexamenbackend.model.dto;

import java.util.List;

public class BandejaCursoExamenPorAlumnoDTO {

    private String idCurso;
    private String nombreCurso;
    private String idCursoGrupo;
    private List<BandejaExamenPorAlumnoDTO> examanes;

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getIdCursoGrupo() {
        return idCursoGrupo;
    }

    public void setIdCursoGrupo(String idCursoGrupo) {
        this.idCursoGrupo = idCursoGrupo;
    }

    public List<BandejaExamenPorAlumnoDTO> getExamanes() {
        return examanes;
    }

    public void setExamanes(List<BandejaExamenPorAlumnoDTO> examanes) {
        this.examanes = examanes;
    }
}
