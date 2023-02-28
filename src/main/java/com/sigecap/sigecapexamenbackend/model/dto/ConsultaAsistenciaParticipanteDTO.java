package com.sigecap.sigecapexamenbackend.model.dto;

public class ConsultaAsistenciaParticipanteDTO {
    private String parIdCurso;
    private String parIdCursoGrupo;
    private String parIdExamenApertura;

    public String getParIdCurso() {
        return parIdCurso;
    }

    public void setParIdCurso(String parIdCurso) {
        this.parIdCurso = parIdCurso;
    }

    public String getParIdCursoGrupo() {
        return parIdCursoGrupo;
    }

    public void setParIdCursoGrupo(String parIdCursoGrupo) {
        this.parIdCursoGrupo = parIdCursoGrupo;
    }

    public String getParIdExamenApertura() {
        return parIdExamenApertura;
    }

    public void setParIdExamenApertura(String parIdExamenApertura) {
        this.parIdExamenApertura = parIdExamenApertura;
    }
}
