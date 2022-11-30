package com.sigecap.sigecapexamenbackend.model.dto;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParticipanteKardexDto{

	@Id
	@Column(name="ID_SOL_INS_DET")
	private String idSolicitudInscripcionDetalle;
	
	@Column(name="ID_SOL_INS")
	private String idSolicitudInscripcion;
	
	@Column(name="TIPO_DOCU_PART")
	private String tipoDocumentoParticipante;
	@Column(name="NU_DOCU_PART")
	private String numeroDocumentoParticipante;
	@Column(name="AP_PART")
	private String apellidosParticipante;
	@Column(name="NO_PART")
	private String nombresParticipante;
	@Column(name="UNIDAD")
	private String unidad;
	@Column(name="CENTRO_COSTO")
	private String centroCosto;
	
	@Column(name="ID_ENTIDAD_CLIENTE")
	private String idEntidadCliente;
	@Column(name="NU_DOCU_CLIENTE")
	private String numeroDocumentoCliente;
	@Column(name="NO_CLIENTE")
	private String nombreCliente;
	@Column(name="DIRECCION_CLIENTE")
	private String direccionCliente;
	@Column(name="NO_RESPONSABLE")
	private String nombreResponsable;
	
	@Column(name="NOTA_TEORICA")
	private String notaTeorica;
	@Column(name="NOTA_PRACTICA")
	private String notaPractica;
	@Column(name="NOTA_FINAL")
	private String notaFinal;
	@Column(name="NO_CURSO")
	private String nombreCurso;
	@Column(name="FE_INI_GRUPO")
	private String fechaIniGrupo;
	
	@Column(name="CUPOS_SOLICITADOS")
	private int cuposSolicitados;
	@Column(name="COSTO_SIN_IGV")
	private BigDecimal costoSinIgv;
	@Column(name="COSTO_SIN_IGV_POR_PART")
	private BigDecimal costoSinIgvPorPart;
	
	@Column(name="ID_TIPO_COBRANZA")
	private String idTipoCobranza;
	@Column(name="NO_TIPO_COBRANZA")
	private String nombreTipoCobranza;
	
	@Column(name="ESTADO")
	private String nombreEstado;

	@Column(name="ID_SI_AND_ID_TIPO_COBRANZA")
	private String idSiAndTipoCobranza;
	
	public String getIdSolicitudInscripcionDetalle() {
		return idSolicitudInscripcionDetalle;
	}

	public void setIdSolicitudInscripcionDetalle(String idSolicitudInscripcionDetalle) {
		this.idSolicitudInscripcionDetalle = idSolicitudInscripcionDetalle;
	}

	public String getIdSolicitudInscripcion() {
		return idSolicitudInscripcion;
	}

	public void setIdSolicitudInscripcion(String idSolicitudInscripcion) {
		this.idSolicitudInscripcion = idSolicitudInscripcion;
	}

	public String getTipoDocumentoParticipante() {
		return tipoDocumentoParticipante;
	}

	public void setTipoDocumentoParticipante(String tipoDocumentoParticipante) {
		this.tipoDocumentoParticipante = tipoDocumentoParticipante;
	}

	public String getNumeroDocumentoParticipante() {
		return numeroDocumentoParticipante;
	}

	public void setNumeroDocumentoParticipante(String numeroDocumentoParticipante) {
		this.numeroDocumentoParticipante = numeroDocumentoParticipante;
	}

	public String getApellidosParticipante() {
		return apellidosParticipante;
	}

	public void setApellidosParticipante(String apellidosParticipante) {
		this.apellidosParticipante = apellidosParticipante;
	}

	public String getNombresParticipante() {
		return nombresParticipante;
	}

	public void setNombresParticipante(String nombresParticipante) {
		this.nombresParticipante = nombresParticipante;
	}

	public String getIdEntidadCliente() {
		return idEntidadCliente;
	}

	public void setIdEntidadCliente(String idEntidadCliente) {
		this.idEntidadCliente = idEntidadCliente;
	}

	public String getNumeroDocumentoCliente() {
		return numeroDocumentoCliente;
	}

	public void setNumeroDocumentoCliente(String numeroDocumentoCliente) {
		this.numeroDocumentoCliente = numeroDocumentoCliente;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}

	public String getDireccionCliente() {
		return direccionCliente;
	}

	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}

	public String getNotaTeorica() {
		return notaTeorica;
	}

	public void setNotaTeorica(String notaTeorica) {
		this.notaTeorica = notaTeorica;
	}

	public String getNotaPractica() {
		return notaPractica;
	}

	public void setNotaPractica(String notaPractica) {
		this.notaPractica = notaPractica;
	}

	public String getNotaFinal() {
		return notaFinal;
	}

	public void setNotaFinal(String notaFinal) {
		this.notaFinal = notaFinal;
	}

	public String getNombreCurso() {
		return nombreCurso;
	}

	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}

	public String getFechaIniGrupo() {
		return fechaIniGrupo;
	}

	public void setFechaIniGrupo(String fechaIniGrupo) {
		this.fechaIniGrupo = fechaIniGrupo;
	}

	public int getCuposSolicitados() {
		return cuposSolicitados;
	}

	public void setCuposSolicitados(int cuposSolicitados) {
		this.cuposSolicitados = cuposSolicitados;
	}

	public BigDecimal getCostoSinIgv() {
		return costoSinIgv;
	}

	public void setCostoSinIgv(BigDecimal costoSinIgv) {
		this.costoSinIgv = costoSinIgv;
	}

	public BigDecimal getCostoSinIgvPorPart() {
		return costoSinIgvPorPart;
	}

	public void setCostoSinIgvPorPart(BigDecimal costoSinIgvPorPart) {
		this.costoSinIgvPorPart = costoSinIgvPorPart;
	}

	public String getNombreEstado() {
		return nombreEstado;
	}

	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}

	public String getNombreTipoCobranza() {
		return nombreTipoCobranza;
	}

	public void setNombreTipoCobranza(String nombreTipoCobranza) {
		this.nombreTipoCobranza = nombreTipoCobranza;
	}

	public String getIdTipoCobranza() {
		return idTipoCobranza;
	}

	public void setIdTipoCobranza(String idTipoCobranza) {
		this.idTipoCobranza = idTipoCobranza;
	}

	public String getIdSiAndTipoCobranza() {
		return idSiAndTipoCobranza;
	}

	public void setIdSiAndTipoCobranza(String idSiAndTipoCobranza) {
		this.idSiAndTipoCobranza = idSiAndTipoCobranza;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public String getNombreResponsable() {
		return nombreResponsable;
	}

	public void setNombreResponsable(String nombreResponsable) {
		this.nombreResponsable = nombreResponsable;
	}
	
	
	
	
}
