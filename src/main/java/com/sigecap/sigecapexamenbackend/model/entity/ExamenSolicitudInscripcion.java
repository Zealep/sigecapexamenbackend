package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "sge_tz_sid_por_ea")
public class ExamenSolicitudInscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sid_examen")
    private Long idExamenSolicitudInscripcion;

    @Column(name = "id_solicitud_inscripcion_detalle")
    private String idSolicitudInscripcionDetalle;

    @ManyToOne
    @JoinColumn(name = "idExamenApertura")
    private ExamenApertura examenApertura;

    @Column(name = "in_realizo_examen")
    private String indicadorRealizoExamen;

    @Column(name = "nota")
    private Integer nota;

    public Long getIdExamenSolicitudInscripcion() {
        return idExamenSolicitudInscripcion;
    }

    public void setIdExamenSolicitudInscripcion(Long idExamenSolicitudInscripcion) {
        this.idExamenSolicitudInscripcion = idExamenSolicitudInscripcion;
    }

    public String getIdSolicitudInscripcionDetalle() {
        return idSolicitudInscripcionDetalle;
    }

    public void setIdSolicitudInscripcionDetalle(String idSolicitudInscripcionDetalle) {
        this.idSolicitudInscripcionDetalle = idSolicitudInscripcionDetalle;
    }

    public ExamenApertura getExamenApertura() {
        return examenApertura;
    }

    public void setExamenApertura(ExamenApertura examenApertura) {
        this.examenApertura = examenApertura;
    }

    public String getIndicadorRealizoExamen() {
        return indicadorRealizoExamen;
    }

    public void setIndicadorRealizoExamen(String indicadorRealizoExamen) {
        this.indicadorRealizoExamen = indicadorRealizoExamen;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }
}
