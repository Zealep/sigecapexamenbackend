package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sgc_tz_curso_grupo")
public class CursoGrupo {

    @Id
    @Column(name = "id_curso_grupo")
    private String idCursoGrupo;

    @ManyToOne
    @JoinColumn(name="id_curso")
    private Curso curso;

    @Column(name = "nombre_grupo")
    private String nombreGrupo;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_fin")
    private Date fechaFin;

    @Column(name = "estado")
    private String estado;

    public String getIdCursoGrupo() {
        return idCursoGrupo;
    }

    public void setIdCursoGrupo(String idCursoGrupo) {
        this.idCursoGrupo = idCursoGrupo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
