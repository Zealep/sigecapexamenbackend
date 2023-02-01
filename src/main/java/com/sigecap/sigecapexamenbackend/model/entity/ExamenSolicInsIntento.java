package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sge_tz_sid_por_ea_intento")
public class ExamenSolicInsIntento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sid_por_ea_intento")
    private Long idExamSoliInscIntento;

    @ManyToOne
    @JoinColumn(name = "id_sid_examen")
    private ExamenSolicitudInscripcion examenSolicitudInscripcion;

    @Column(name = "numero_intento")
    private Integer numeroIntento;

    @Column(name = "fecha_inicio")
    private Date fechaInicio;

    @Column(name = "fecha_termino")
    private Date fechaTermino;

    @Column(name = "nota")
    private Double nota;

    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "examenSolicInsIntento",cascade = CascadeType.ALL)
    private List<ExamenSolicInsIntentoRespuesta> detalleRespuestas;

    public Long getIdExamSoliInscIntento() {
        return idExamSoliInscIntento;
    }

    public void setIdExamSoliInscIntento(Long idExamSoliInscIntento) {
        this.idExamSoliInscIntento = idExamSoliInscIntento;
    }

    public ExamenSolicitudInscripcion getExamenSolicitudInscripcion() {
        return examenSolicitudInscripcion;
    }

    public void setExamenSolicitudInscripcion(ExamenSolicitudInscripcion examenSolicitudInscripcion) {
        this.examenSolicitudInscripcion = examenSolicitudInscripcion;
    }

    public Integer getNumeroIntento() {
        return numeroIntento;
    }

    public void setNumeroIntento(Integer numeroIntento) {
        this.numeroIntento = numeroIntento;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<ExamenSolicInsIntentoRespuesta> getDetalleRespuestas() {
        return detalleRespuestas;
    }

    public void setDetalleRespuestas(List<ExamenSolicInsIntentoRespuesta> detalleRespuestas) {
        this.detalleRespuestas = detalleRespuestas;
    }
}
