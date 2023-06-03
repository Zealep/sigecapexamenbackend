package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="sgc_tz_solicitud_inscripcion_detalle")
public class SolicitudInscripcionDetalle {

	
	@Id
	@Column(name="id_solicitud_inscripcion_detalle")
	private String idSolicitudInscripcionDetalle;

	

		
	@ManyToOne
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_solicitud_inscripcion", referencedColumnName = "id_solicitud_inscripcion")
	private SolicitudInscripcion solicitudInscripcion;
	
	@ManyToOne
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_participante", referencedColumnName = "id_participante")
	private Participante participante;
	
	
	@Column(name="nota_teorica")
	private BigDecimal notaTeorica;
	
	@Column(name="nota_practica")
	private BigDecimal notaPractica;
	
	@Column(name="promedio")
	private BigDecimal promedio;
	
	@Column(name="codigo_certificado")
	private String codigoCertificado;

	@Column(name="informacion_adicional")
	private String informacionAdicional;
	
	@Column(name="fecha_emision_certificado")
	private Date fechaEmisionCertificado;

	@Column(name="estado")
	private String estado;
	
	@Column(name="nexa_id_apertura_usuario")
	private String nexaIdAperturaUsuario;
	
	@ManyToOne
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_comprobante_pago", referencedColumnName = "id_comprobante_pago")
	private ComprobantePago comprobantePago;
	
	@Column(name="in_realizo_encuesta")
	private String inRealizoEncuesta;
	
	public String getIdSolicitudInscripcionDetalle() {
		return idSolicitudInscripcionDetalle;
	}

	public void setIdSolicitudInscripcionDetalle(String idSolicitudInscripcionDetalle) {
		this.idSolicitudInscripcionDetalle = idSolicitudInscripcionDetalle;
	}

	public BigDecimal getNotaTeorica() {
		return notaTeorica;
	}

	public void setNotaTeorica(BigDecimal notaTeorica) {
		this.notaTeorica = notaTeorica;
	}

	public BigDecimal getNotaPractica() {
		return notaPractica;
	}

	public void setNotaPractica(BigDecimal notaPractica) {
		this.notaPractica = notaPractica;
	}

	public BigDecimal getPromedio() {
		return promedio;
	}

	public void setPromedio(BigDecimal promedio) {
		this.promedio = promedio;
	}

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
		this.participante = participante;
	}

	public String getCodigoCertificado() {
		return codigoCertificado;
	}

	public void setCodigoCertificado(String codigoCertificado) {
		this.codigoCertificado = codigoCertificado;
	}

	public String getInformacionAdicional() {
		return informacionAdicional;
	}

	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	public Date getFechaEmisionCertificado() {
		return fechaEmisionCertificado;
	}

	public void setFechaEmisionCertificado(Date fechaEmisionCertificado) {
		this.fechaEmisionCertificado = fechaEmisionCertificado;
	}

	public SolicitudInscripcion getSolicitudInscripcion() {
		return solicitudInscripcion;
	}

	public void setSolicitudInscripcion(SolicitudInscripcion solicitudInscripcion) {
		this.solicitudInscripcion = solicitudInscripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public ComprobantePago getComprobantePago() {
		return comprobantePago;
	}

	public void setComprobantePago(ComprobantePago comprobantePago) {
		this.comprobantePago = comprobantePago;
	}

	public String getNexaIdAperturaUsuario() {
		return nexaIdAperturaUsuario;
	}

	public void setNexaIdAperturaUsuario(String nexaIdAperturaUsuario) {
		this.nexaIdAperturaUsuario = nexaIdAperturaUsuario;
	}

	public String getInRealizoEncuesta() {
		return inRealizoEncuesta;
	}

	public void setInRealizoEncuesta(String inRealizoEncuesta) {
		this.inRealizoEncuesta = inRealizoEncuesta;
	}


}
