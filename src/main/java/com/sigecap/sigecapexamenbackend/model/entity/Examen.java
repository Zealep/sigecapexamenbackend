package com.sigecap.sigecapexamenbackend.model.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sge_tz_examen")
public class Examen {

    @Id
    @Column(name = "id_examen")
    private String idExamen;

    @ManyToOne
    @JoinColumn(name = "id_curso")
    private Curso curso;

    @Column(name = "no_examen")
    private String nombreExamen;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "examen",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<ExamenPregunta> detalleExamen;

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

    public String getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(String idExamen) {
        this.idExamen = idExamen;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ExamenPregunta> getDetalleExamen() {
        return detalleExamen;
    }

    public void setDetalleExamen(List<ExamenPregunta> detalleExamen) {
        this.detalleExamen = detalleExamen;
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
