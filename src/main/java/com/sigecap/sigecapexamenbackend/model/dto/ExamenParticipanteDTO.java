package com.sigecap.sigecapexamenbackend.model.dto;

import java.util.Date;
import java.util.List;

public class ExamenParticipanteDTO {

    private Long idSidExamen;
    private String idCursoGrupo;
    private String nombreGrupo;
    private String idCurso;
    private String nombreCurso;
    private String idExamen;
    private String nombreExamen;
    private Date fechaHoraApertura;
    private Date fechaHoraCierre;
    private Integer nroIntentosPermitidos;
    private Integer nroIntentosRealizados;
    private String estado;
    private String nombreEstado;
    private String indicadorAsistio;
    private List<IntentoExamenDTO> intentosExamen;

    public Long getIdSidExamen() {
        return idSidExamen;
    }

    public void setIdSidExamen(Long idSidExamen) {
        this.idSidExamen = idSidExamen;
    }

    public String getIdCursoGrupo() {
        return idCursoGrupo;
    }

    public void setIdCursoGrupo(String idCursoGrupo) {
        this.idCursoGrupo = idCursoGrupo;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
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

    public Integer getNroIntentosPermitidos() {
        return nroIntentosPermitidos;
    }

    public void setNroIntentosPermitidos(Integer nroIntentosPermitidos) {
        this.nroIntentosPermitidos = nroIntentosPermitidos;
    }

    public Integer getNroIntentosRealizados() {
        return nroIntentosRealizados;
    }

    public void setNroIntentosRealizados(Integer nroIntentosRealizados) {
        this.nroIntentosRealizados = nroIntentosRealizados;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public String getIndicadorAsistio() {
        return indicadorAsistio;
    }

    public void setIndicadorAsistio(String indicadorAsistio) {
        this.indicadorAsistio = indicadorAsistio;
    }

    public List<IntentoExamenDTO> getIntentosExamen() {
        return intentosExamen;
    }

    public void setIntentosExamen(List<IntentoExamenDTO> intentosExamen) {
        this.intentosExamen = intentosExamen;
    }


}
