package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sge_tm_tipo_pregunta")
public class TipoPregunta {

    @Id
    @Column(name = "id_tipo_pregunta")
    private String idTipoPregunta;
    @Column(name = "no_tipo_pregunta")
    private String nombreTipoPregunta;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estado")
    private String estado;

    public String getIdTipoPregunta() {
        return idTipoPregunta;
    }

    public void setIdTipoPregunta(String idTipoPregunta) {
        this.idTipoPregunta = idTipoPregunta;
    }

    public String getNombreTipoPregunta() {
        return nombreTipoPregunta;
    }

    public void setNombreTipoPregunta(String nombreTipoPregunta) {
        this.nombreTipoPregunta = nombreTipoPregunta;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
