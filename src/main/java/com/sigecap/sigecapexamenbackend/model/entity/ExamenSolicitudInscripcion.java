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
    @JoinColumn(name = "id_examen_apertura")
    private ExamenApertura examenApertura;

    @Column(name = "in_asistio")
    private String indicadorAsistio;

    @Column(name = "in_realizo_examen")
    private String indicadorRealizoExamen;


    @Column(name = "numero_intento_realizado")
    private Integer numeroIntentoRealizado;

    @Column(name = "estado")
    private String estado;

    public String getIndicadorAsistio() {
        return indicadorAsistio;
    }

    public void setIndicadorAsistio(String indicadorAsistio) {
        this.indicadorAsistio = indicadorAsistio;
    }

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

    public Integer getNumeroIntentoRealizado() {
        return numeroIntentoRealizado;
    }

    public void setNumeroIntentoRealizado(Integer numeroIntentoRealizado) {
        this.numeroIntentoRealizado = numeroIntentoRealizado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
