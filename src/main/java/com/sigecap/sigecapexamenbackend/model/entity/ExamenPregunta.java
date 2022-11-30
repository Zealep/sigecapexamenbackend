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
@Table(name = "sge_tz_examen_por_pregunta")
public class ExamenPregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_examen_pregunta")
    private String idExamenPregunta;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_examen")
    private Examen examen;

    @ManyToOne
    @JoinColumn(name = "id_pregunta")
    private Pregunta pregunta;

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



    public String getIdExamenPregunta() {
        return idExamenPregunta;
    }

    public void setIdExamenPregunta(String idExamenPregunta) {
        this.idExamenPregunta = idExamenPregunta;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
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
