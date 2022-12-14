package com.sigecap.sigecapexamenbackend.model.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "sge_tm_pregunta")
public class Pregunta {

    @Id
    @Column(name = "id_pregunta")
    private String idPregunta;

    @ManyToOne
    @JoinColumn(name="id_curso")
    private Curso curso;

    @ManyToOne
    @JoinColumn(name="id_tipo_pregunta")
    private TipoPregunta tipoPregunta;

    @Column(name = "no_pregunta")
    private String nombrePregunta;
    @Lob
    @Column(name = "enunciado")
    private String  enunciado;

    @Lob
    @Column(name = "enunciado_texto")
    private String  enunciadoTexto;


    @Column(name = "puntuacion")
    private BigDecimal puntuacion;
    @Lob
    @Column(name = "retroalimentacion")
    private String  retroalimentacion;

    @Column(name = "estado")
    private String  estado;

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




    public String getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(String idPregunta) {
        this.idPregunta = idPregunta;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public TipoPregunta getTipoPregunta() {
        return tipoPregunta;
    }

    public void setTipoPregunta(TipoPregunta tipoPregunta) {
        this.tipoPregunta = tipoPregunta;
    }

    public String getNombrePregunta() {
        return nombrePregunta;
    }

    public void setNombrePregunta(String nombrePregunta) {
        this.nombrePregunta = nombrePregunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public BigDecimal getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(BigDecimal puntuacion) {
        this.puntuacion = puntuacion;
    }

    public String getRetroalimentacion() {
        return retroalimentacion;
    }

    public void setRetroalimentacion(String retroalimentacion) {
        this.retroalimentacion = retroalimentacion;
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
