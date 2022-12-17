package com.sigecap.sigecapexamenbackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sge_tz_sid_por_ea_intento_respuesta")
public class ExamenSolicInsIntentoRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sid_por_ea_respuesta")
    private Long idExamSoliInscIntentoRespuesta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_sid_por_ea_intento")
    private ExamenSolicInsIntento examenSolicInsIntento;

    @Column(name = "id_pregunta")
    private String idPregunta;

    @Column(name = "id_respuesta_marcada")
    private String idRespuestaMarcada;

    @Column(name = "respuesta_ingresada")
    private String  respuestaIngresada;

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

    public Long getIdExamSoliInscIntentoRespuesta() {
        return idExamSoliInscIntentoRespuesta;
    }

    public void setIdExamSoliInscIntentoRespuesta(Long idExamSoliInscIntentoRespuesta) {
        this.idExamSoliInscIntentoRespuesta = idExamSoliInscIntentoRespuesta;
    }

    public ExamenSolicInsIntento getExamenSolicInsIntento() {
        return examenSolicInsIntento;
    }

    public void setExamenSolicInsIntento(ExamenSolicInsIntento examenSolicInsIntento) {
        this.examenSolicInsIntento = examenSolicInsIntento;
    }

    public String getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(String idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getIdRespuestaMarcada() {
        return idRespuestaMarcada;
    }

    public void setIdRespuestaMarcada(String idRespuestaMarcada) {
        this.idRespuestaMarcada = idRespuestaMarcada;
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
