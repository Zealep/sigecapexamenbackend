package com.sigecap.sigecapexamenbackend.model.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sge_tz_sid_por_encuesta_respuesta")
public class EncuestaInscripcionRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sid_por_encuesta_respuesta")
    private Long IdEncuestaInscripcionRespuesta;

    @Column(name = "id_solicitud_inscripcion_detalle")
    private String idSolicitudInscripcionDetalle;

    @Column(name = "id_pregunta")
    private String pregunta;

    @Column(name = "id_respuesta_marcada")
    private String respuesta;

    @Column(name = "respuesta_ingresada")
    private String respuestaIngresada;

    @CreatedBy
    @Column(name = "us_creacion",nullable = false, updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "fe_creacion",nullable = false, updatable = false)
    private Date created;

    @LastModifiedBy
    @Column(name = "us_modificacion",nullable = false)
    private String modifiedBy;

    @LastModifiedDate
    @Column(name = "fe_modificacion",nullable = false)
    private Date modified;

    public Long getIdEncuestaInscripcionRespuesta() {
        return IdEncuestaInscripcionRespuesta;
    }

    public void setIdEncuestaInscripcionRespuesta(Long idEncuestaInscripcionRespuesta) {
        IdEncuestaInscripcionRespuesta = idEncuestaInscripcionRespuesta;
    }

    public String getIdSolicitudInscripcionDetalle() {
        return idSolicitudInscripcionDetalle;
    }

    public void setIdSolicitudInscripcionDetalle(String idSolicitudInscripcionDetalle) {
        this.idSolicitudInscripcionDetalle = idSolicitudInscripcionDetalle;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getRespuestaIngresada() {
        return respuestaIngresada;
    }

    public void setRespuestaIngresada(String respuestaIngresada) {
        this.respuestaIngresada = respuestaIngresada;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }
}
