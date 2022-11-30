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
@Table(name = "sge_tm_respuesta")
public class Respuesta {

    @Id
    @Column(name = "id_respuesta")
    private String idRespuesta;

    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;

    @Lob
    @Column(name = "enunciado")
    private String enunciado;

    @Lob
    @Column(name = "enunciado_texto")
    private String enunciadoTexto;

    @Lob
    @Column(name = "retroalimentacion")
    private String retroAlimentacion;

    @Column(name = "in_respuesta_correcta")
    private String respuestaCorrecta;

    @Column(name = "estado")
    private String estado;

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


    public String getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(String idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public String getRetroAlimentacion() {
        return retroAlimentacion;
    }

    public void setRetroAlimentacion(String retroAlimentacion) {
        this.retroAlimentacion = retroAlimentacion;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEnunciadoTexto() {
        return enunciadoTexto;
    }

    public void setEnunciadoTexto(String enunciadoTexto) {
        this.enunciadoTexto = enunciadoTexto;
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
