package com.sigecap.sigecapexamenbackend.model.dto;

import com.sigecap.sigecapexamenbackend.model.entity.EncuestaInscripcionRespuesta;

import java.util.List;

public class EncuestaInscripcionRespuestasDTO {

    private String idSolicitudInscripcion;
    private List<EncuestaInscripcionRespuesta> respuestasEncuesta;
    private String comentarios;

    public String getIdSolicitudInscripcion() {
        return idSolicitudInscripcion;
    }

    public void setIdSolicitudInscripcion(String idSolicitudInscripcion) {
        this.idSolicitudInscripcion = idSolicitudInscripcion;
    }

    public List<EncuestaInscripcionRespuesta> getRespuestasEncuesta() {
        return respuestasEncuesta;
    }

    public void setRespuestasEncuesta(List<EncuestaInscripcionRespuesta> respuestasEncuesta) {
        this.respuestasEncuesta = respuestasEncuesta;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
