package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
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

    @Column(name = "puntuacion")
    private BigDecimal puntuacion;
    @Lob
    @Column(name = "retroalimentacion")
    private String  retroalimentacion;

    @Column(name = "estado")
    private String  estado;

    @Column(name = "fecha_creacion")
    private Date fechaCrecion;

    @Column(name = "usuario_creacion")
    private String  usuarioCreacion;


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

    public Date getFechaCrecion() {
        return fechaCrecion;
    }

    public void setFechaCrecion(Date fechaCrecion) {
        this.fechaCrecion = fechaCrecion;
    }

    public String getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(String usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }
}
