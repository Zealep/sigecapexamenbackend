package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "sge_tm_tipo_examen")
public class TipoExamen {

    @Id
    @Column(name = "id_tipo_examen")
    private String idTipoExamen;

    @Column(name = "no_tipo_examen")
    private String nombre;

    @Column(name = "estado")
    private String estado;


    public String getIdTipoExamen() {
        return idTipoExamen;
    }

    public void setIdTipoExamen(String idTipoExamen) {
        this.idTipoExamen = idTipoExamen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
