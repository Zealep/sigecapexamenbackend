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
@Table(name = "sge_tz_examen_apertura")
public class ExamenApertura {

    @Id
    @Column(name="id_examen_apertura")
    private String idExamenApertura;

    @ManyToOne
    @JoinColumn(name = "id_examen")
    private Examen examen;

    @ManyToOne
    @JoinColumn(name = "id_curso_grupo")
    private CursoGrupo cursoGrupo;

    @Column(name = "fecha_hora_apertura")
    private Date fechaHoraApertura;

    @Column(name = "fecha_hora_cierre")
    private Date fechaHoraCierre;

    @Column(name = "tiempo_duracion")
    private Integer tiempoDuracion;

    @Column(name = "numero_intentos")
    private Integer numeroIntentos;

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

    public CursoGrupo getCursoGrupo() {
        return cursoGrupo;
    }

    public void setCursoGrupo(CursoGrupo cursoGrupo) {
        this.cursoGrupo = cursoGrupo;
    }

    public String getIdExamenApertura() {
        return idExamenApertura;
    }

    public void setIdExamenApertura(String idExamenApertura) {
        this.idExamenApertura = idExamenApertura;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Date getFechaHoraApertura() {
        return fechaHoraApertura;
    }

    public void setFechaHoraApertura(Date fechaHoraApertura) {
        this.fechaHoraApertura = fechaHoraApertura;
    }

    public Date getFechaHoraCierre() {
        return fechaHoraCierre;
    }

    public void setFechaHoraCierre(Date fechaHoraCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
    }

    public Integer getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(Integer tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }

    public Integer getNumeroIntentos() {
        return numeroIntentos;
    }

    public void setNumeroIntentos(Integer numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
