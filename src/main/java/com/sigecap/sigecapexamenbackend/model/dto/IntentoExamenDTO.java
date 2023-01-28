package com.sigecap.sigecapexamenbackend.model.dto;

import java.util.Date;

public class IntentoExamenDTO {
    private Long idSidIntento;
    private Date fechaInicio;
    private Date fechaTermino;
    private Double nota;
    private String codigoEstado;
    private String nombreEstado;
    private String comentario;

    public Long getIdSidIntento() {
        return idSidIntento;
    }

    public void setIdSidIntento(Long idSidIntento) {
        this.idSidIntento = idSidIntento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getCodigoEstado() {
        return codigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        this.codigoEstado = codigoEstado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
