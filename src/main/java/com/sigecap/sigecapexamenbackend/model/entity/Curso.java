package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sgc_tz_curso")
public class Curso {

    @Id
    @Column(name = "id_curso")
    private String idCurso;

    @Column(name = "nombre_curso")
    private String nombreCurso;

    @Column(name = "nombre_corto_curso")
    private String nombreCortoCurso;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "url_temario")
    private String urlTemario;

    @Column(name = "cantidad_horas")
    private Double cantidadHoras;

    @Column(name = "precio_unitario")
    private Double precioUnitario;

    @Column(name = "numero_grupos")
    private Integer numeroGrupos;

    @Column(name = "estado")
    private String estado;

    @Column(name = "id_archivo")
    private String idArchivo;

    public String getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(String idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNombreCortoCurso() {
        return nombreCortoCurso;
    }

    public void setNombreCortoCurso(String nombreCortoCurso) {
        this.nombreCortoCurso = nombreCortoCurso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlTemario() {
        return urlTemario;
    }

    public void setUrlTemario(String urlTemario) {
        this.urlTemario = urlTemario;
    }

    public Double getCantidadHoras() {
        return cantidadHoras;
    }

    public void setCantidadHoras(Double cantidadHoras) {
        this.cantidadHoras = cantidadHoras;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Integer getNumeroGrupos() {
        return numeroGrupos;
    }

    public void setNumeroGrupos(Integer numeroGrupos) {
        this.numeroGrupos = numeroGrupos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(String idArchivo) {
        this.idArchivo = idArchivo;
    }
}
