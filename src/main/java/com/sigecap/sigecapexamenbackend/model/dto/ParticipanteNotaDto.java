package com.sigecap.sigecapexamenbackend.model.dto;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParticipanteNotaDto{

	@Id
	@Column(name="ID_SOL_INS_DET")
	private String idSolicitudInscripcionDetalle;
	
	
	@Column(name="ID_SOL_INS")
	private String idSolicitudInscripcion;
	
	
	@Column(name="ID_ARCHIVO")
	private String idArchivo;
	
	@Column(name="ID_ENTIDAD_CLIENTE")
	private String idEntidadCliente;
	
	@Column(name="NO_CLIENTE")
	private String nombreCliente;
	@Column(name="NU_DOCU_CLIENTE")
	private String numeroDocumentoCliente;
	@Column(name="DIRECCION_CLIENTE")
	private String direccionCliente;
	
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
	
	@Column(name="NO_CURSO")
	private String nombreCurso;
	@Column(name="NOTA_TEORICA")
	private String notaTeorica;
	@Column(name="NOTA_PRACTICA")
	private String notaPractica;
	@Column(name="PROMEDIO")
	private String promedio;
	@Column(name="FE_FIN_GRUPO")
	private String fechaFinGrupo;
	
	@Column(name="TIPO_NOTA")
	private String tipoNota;
	
	@Column(name="ORDEN_MOSTRAR")
	private int ordenMostrar;
	
	@Column(name="TIENE_CERTIFICADO")
	private String tieneCertificado;
	
	@Column(name="INFORMACION_ADICIONAL")
	private String informacionAdicional;
	
	@Column(name="ID_ESTADO")
	private String idEstado;
	
	@Column(name="NO_ESTADO")
	private String nombreEstado;
	
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
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getNumeroDocumentoCliente() {
		return numeroDocumentoCliente;
	}
	public void setNumeroDocumentoCliente(String numeroDocumentoCliente) {
		this.numeroDocumentoCliente = numeroDocumentoCliente;
	}
	public String getNombreCurso() {
		return nombreCurso;
	}
	public void setNombreCurso(String nombreCurso) {
		this.nombreCurso = nombreCurso;
	}



	public int getOrdenMostrar() {
		return ordenMostrar;
	}
	public void setOrdenMostrar(int ordenMostrar) {
		this.ordenMostrar = ordenMostrar;
	}
	public String getFechaFinGrupo() {
		return fechaFinGrupo;
	}
	public void setFechaFinGrupo(String fechaFinGrupo) {
		this.fechaFinGrupo = fechaFinGrupo;
	}
	public String getIdSolicitudInscripcionDetalle() {
		return idSolicitudInscripcionDetalle;
	}
	public void setIdSolicitudInscripcionDetalle(String idSolicitudInscripcionDetalle) {
		this.idSolicitudInscripcionDetalle = idSolicitudInscripcionDetalle;
	}
	public String getTipoDocumentoParticipante() {
		return tipoDocumentoParticipante;
	}
	public void setTipoDocumentoParticipante(String tipoDocumentoParticipante) {
		this.tipoDocumentoParticipante = tipoDocumentoParticipante;
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
	public String getPromedio() {
		return promedio;
	}
	public void setPromedio(String promedio) {
		this.promedio = promedio;
	}
	public String getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(String tipoNota) {
		this.tipoNota = tipoNota;
	}
	public String getTieneCertificado() {
		return tieneCertificado;
	}
	public void setTieneCertificado(String tieneCertificado) {
		this.tieneCertificado = tieneCertificado;
	}
	public String getDireccionCliente() {
		return direccionCliente;
	}
	public void setDireccionCliente(String direccionCliente) {
		this.direccionCliente = direccionCliente;
	}
	public String getInformacionAdicional() {
		return informacionAdicional;
	}
	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}
	public String getIdSolicitudInscripcion() {
		return idSolicitudInscripcion;
	}
	public void setIdSolicitudInscripcion(String idSolicitudInscripcion) {
		this.idSolicitudInscripcion = idSolicitudInscripcion;
	}
	public String getIdArchivo() {
		return idArchivo;
	}
	public void setIdArchivo(String idArchivo) {
		this.idArchivo = idArchivo;
	}
	public String getIdEntidadCliente() {
		return idEntidadCliente;
	}
	public void setIdEntidadCliente(String idEntidadCliente) {
		this.idEntidadCliente = idEntidadCliente;
	}
	public String getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getUnidad() {
		return unidad;
	}
	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	
	
	
}
