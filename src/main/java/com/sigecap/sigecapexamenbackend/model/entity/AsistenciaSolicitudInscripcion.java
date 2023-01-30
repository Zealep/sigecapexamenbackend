package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sge_tz_sid_por_asistencia")
public class AsistenciaSolicitudInscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sid_asistencia")
    private Long idSolicitudAsistencia;

    @Column(name = "id_solicitud_inscripcion_detalle")
    private String idSolicitudInscripcionDetalle;

    @Column(name = "fecha_asistencia")
    private Date fechaAsistencia;

    @Column(name = "realizo_firma")
    private String realizoFirma;

    public AsistenciaSolicitudInscripcion(){

    }

    public AsistenciaSolicitudInscripcion(String idSolicitudInscripcionDetalle, Date fechaAsistencia) {
        this.idSolicitudInscripcionDetalle = idSolicitudInscripcionDetalle;
        this.fechaAsistencia = fechaAsistencia;
    }

    public Long getIdSolicitudAsistencia() {
        return idSolicitudAsistencia;
    }

    public void setIdSolicitudAsistencia(Long idSolicitudAsistencia) {
        this.idSolicitudAsistencia = idSolicitudAsistencia;
    }

    public String getIdSolicitudInscripcionDetalle() {
        return idSolicitudInscripcionDetalle;
    }

    public void setIdSolicitudInscripcionDetalle(String idSolicitudInscripcionDetalle) {
        this.idSolicitudInscripcionDetalle = idSolicitudInscripcionDetalle;
    }

    public Date getFechaAsistencia() {
        return fechaAsistencia;
    }

    public void setFechaAsistencia(Date fechaAsistencia) {
        this.fechaAsistencia = fechaAsistencia;
    }
}
