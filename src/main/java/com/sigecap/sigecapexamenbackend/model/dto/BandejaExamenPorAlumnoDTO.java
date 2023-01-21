package com.sigecap.sigecapexamenbackend.model.dto;

import java.util.Date;

public class BandejaExamenPorAlumnoDTO {

    private Long idSidExamen;
    private String idSolicitudInscripcionDetalle;
    private String idUsuario;
    private String nombreUsuario;
    private String nombreCliente;
    private String idCursoGrupo;
    private String idCurso;
    private String nombreCurso;
    private String idExamen;
    private String nombreExamen;
    private Date fechaHoraApertura;
    private Date fechaHoraCierre;
    private String estado;
    private Integer notaUltimoIntento;
    private Integer notaFinal;
    private String indicadorEncuesta;
    private String indicadorRealizoEncuesta;
    private String indicadorAsistio;
    private String indicadorRealizoExamen;
    private Integer nroIntentoPermitido;
    private Integer nroIntentoRealizado;
    private Date fechaEnvio;


    public Long getIdSidExamen() {
        return idSidExamen;
    }

    public void setIdSidExamen(Long idSidExamen) {
        this.idSidExamen = idSidExamen;
    }

    public String getIdSolicitudInscripcionDetalle() {
        return idSolicitudInscripcionDetalle;
    }

    public void setIdSolicitudInscripcionDetalle(String idSolicitudInscripcionDetalle) {
        this.idSolicitudInscripcionDetalle = idSolicitudInscripcionDetalle;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getIdCursoGrupo() {
        return idCursoGrupo;
    }

    public void setIdCursoGrupo(String idCursoGrupo) {
        this.idCursoGrupo = idCursoGrupo;
    }

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

    public String getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(String idExamen) {
        this.idExamen = idExamen;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public Date getFechaHoraApertura() {
        return fechaHoraApertura;
    }

    public void setFechaHoraApertura(Date fechaHoraApertura) {
        this.fechaHoraApertura = fechaHoraApertura;
    }

    public Date getFechaHoraCierre() {
        return fechaHoraCierre;
    }

    public void setFechaHoraCierre(Date fechaHoraCierre) {
        this.fechaHoraCierre = fechaHoraCierre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getNotaUltimoIntento() {
        return notaUltimoIntento;
    }

    public void setNotaUltimoIntento(Integer notaUltimoIntento) {
        this.notaUltimoIntento = notaUltimoIntento;
    }

    public Integer getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Integer notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getIndicadorEncuesta() {
        return indicadorEncuesta;
    }

    public void setIndicadorEncuesta(String indicadorEncuesta) {
        this.indicadorEncuesta = indicadorEncuesta;
    }

    public String getIndicadorRealizoEncuesta() {
        return indicadorRealizoEncuesta;
    }

    public void setIndicadorRealizoEncuesta(String indicadorRealizoEncuesta) {
        this.indicadorRealizoEncuesta = indicadorRealizoEncuesta;
    }

    public String getIndicadorAsistio() {
        return indicadorAsistio;
    }

    public void setIndicadorAsistio(String indicadorAsistio) {
        this.indicadorAsistio = indicadorAsistio;
    }

    public String getIndicadorRealizoExamen() {
        return indicadorRealizoExamen;
    }

    public void setIndicadorRealizoExamen(String indicadorRealizoExamen) {
        this.indicadorRealizoExamen = indicadorRealizoExamen;

    }

    public Integer getNroIntentoPermitido() {
        return nroIntentoPermitido;
    }

    public void setNroIntentoPermitido(Integer nroIntentoPermitido) {
        this.nroIntentoPermitido = nroIntentoPermitido;
    }

    public Integer getNroIntentoRealizado() {
        return nroIntentoRealizado;
    }

    public void setNroIntentoRealizado(Integer nroIntentoRealizado) {
        this.nroIntentoRealizado = nroIntentoRealizado;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
