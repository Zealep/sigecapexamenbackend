package com.sigecap.sigecapexamenbackend.model.dto;

import java.util.Date;

public class FirmaExamenDTO {
    private Long idSidAsistencia;
    private String idSolicitudInscripcion;
    private Date fechaAsistencia;
    private String realizoFirma;

    public Long getIdSidAsistencia() {
        return idSidAsistencia;
    }

    public void setIdSidAsistencia(Long idSidAsistencia) {
        this.idSidAsistencia = idSidAsistencia;
    }

    public String getIdSolicitudInscripcion() {
        return idSolicitudInscripcion;
    }

    public void setIdSolicitudInscripcion(String idSolicitudInscripcion) {
        this.idSolicitudInscripcion = idSolicitudInscripcion;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }

    public String getRealizoFirma() {
        return realizoFirma;
    }

    public void setRealizoFirma(String realizoFirma) {
        this.realizoFirma = realizoFirma;
    }
}
