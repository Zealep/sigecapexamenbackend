package com.sigecap.sigecapexamenbackend.model.entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


@Entity
@Table(name="sgc_tz_solicitud_inscripcion")
public class SolicitudInscripcion {

	
	@Id
	@Column(name="id_solicitud_inscripcion")
	private String idSolicitudInscripcion;

	
	@ManyToOne
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_curso_grupo", referencedColumnName = "id_curso_grupo")
	private CursoGrupo cursoGrupo;
		
	@Column(name="id_entidad_cliente")
	private String idEntidadCliente;
	
	@Column(name="id_entidad_responsable")
	private String idEntidadResponsable;
	
	@Column(name="id_unidad")
	private String idUnidad;
	
	@Column(name="cupos_solicitados")
	private Integer cuposSolicitados;
	
	@Column(name="total_precio_unitario")
	private BigDecimal totalPrecioUnitario;
	
	@Column(name="total_valor_venta")
	private BigDecimal totalValorVenta;
	
	@Column(name="total_igv_venta")
	private BigDecimal totalIgvVenta;
	
	@Column(name="total_precio_venta")
	private BigDecimal totalPrecioVenta;
	
	@Column(name="total_dscto_vv")
	private BigDecimal totalDsctoVv;
	
	@Column(name="total_dscto_pv")
	private BigDecimal totalDsctoPv;
	
	@Column(name="total_detraccion")
	private BigDecimal totalDetraccion;
	
	@Column(name="total_deposito")
	private BigDecimal totalDeposito;
	
	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="fecha_pago")
	private Date fechaPago;

	
	@Column(name="fecha_anulacion")
	private String fechaAnulacion;

	
	@Column(name="estado")
	private String estado;


	@Column(name="in_hab_reg_documento")
	private String indHabRegDocumento;
	
	
	@Column(name="estado_eliminacion")
	private String estadoEliminacion;
	
	@Column(name="fecha_eliminacion")
	private Date fechaEliminacion;
	
	@Column(name="motivo_eliminacion")
	private String motivoEliminacion;
	
	@Column(name="estado_activacion")
	private String estadoActivacion;
	
	@Column(name="fecha_activacion")
	private Date fechaActivacion;
	
	public String getIdSolicitudInscripcion() {
		return idSolicitudInscripcion;
	}


	public void setIdSolicitudInscripcion(String idSolicitudInscripcion) {
		this.idSolicitudInscripcion = idSolicitudInscripcion;
	}


	public Date getFechaPago() {
		return fechaPago;
	}


	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}


	public String getFechaAnulacion() {
		return fechaAnulacion;
	}


	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}


	public Integer getCuposSolicitados() {
		return cuposSolicitados;
	}


	public void setCuposSolicitados(Integer cuposSolicitados) {
		this.cuposSolicitados = cuposSolicitados;
	}


	public String getIdEntidadCliente() {
		return idEntidadCliente;
	}


	public void setIdEntidadCliente(String idEntidadCliente) {
		this.idEntidadCliente = idEntidadCliente;
	}


	public String getIdEntidadResponsable() {
		return idEntidadResponsable;
	}


	public void setIdEntidadResponsable(String idEntidadResponsable) {
		this.idEntidadResponsable = idEntidadResponsable;
	}


	public CursoGrupo getCursoGrupo() {
		return cursoGrupo;
	}


	public void setCursoGrupo(CursoGrupo cursoGrupo) {
		this.cursoGrupo = cursoGrupo;
	}


	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}


	public BigDecimal getTotalValorVenta() {
		return totalValorVenta;
	}


	public void setTotalValorVenta(BigDecimal totalValorVenta) {
		this.totalValorVenta = totalValorVenta;
	}


	public BigDecimal getTotalIgvVenta() {
		return totalIgvVenta;
	}


	public void setTotalIgvVenta(BigDecimal totalIgvVenta) {
		this.totalIgvVenta = totalIgvVenta;
	}


	public BigDecimal getTotalPrecioVenta() {
		return totalPrecioVenta;
	}


	public void setTotalPrecioVenta(BigDecimal totalPrecioVenta) {
		this.totalPrecioVenta = totalPrecioVenta;
	}


	public BigDecimal getTotalDsctoVv() {
		return totalDsctoVv;
	}


	public void setTotalDsctoVv(BigDecimal totalDsctoVv) {
		this.totalDsctoVv = totalDsctoVv;
	}


	public BigDecimal getTotalDsctoPv() {
		return totalDsctoPv;
	}


	public void setTotalDsctoPv(BigDecimal totalDsctoPv) {
		this.totalDsctoPv = totalDsctoPv;
	}


	public BigDecimal getTotalDetraccion() {
		return totalDetraccion;
	}


	public void setTotalDetraccion(BigDecimal totalDetraccion) {
		this.totalDetraccion = totalDetraccion;
	}


	public BigDecimal getTotalDeposito() {
		return totalDeposito;
	}


	public void setTotalDeposito(BigDecimal totalDeposito) {
		this.totalDeposito = totalDeposito;
	}


	public BigDecimal getTotalPrecioUnitario() {
		return totalPrecioUnitario;
	}


	public void setTotalPrecioUnitario(BigDecimal totalPrecioUnitario) {
		this.totalPrecioUnitario = totalPrecioUnitario;
	}


	public String getIndHabRegDocumento() {
		return indHabRegDocumento;
	}


	public void setIndHabRegDocumento(String indHabRegDocumento) {
		this.indHabRegDocumento = indHabRegDocumento;
	}


	public String getIdUnidad() {
		return idUnidad;
	}


	public void setIdUnidad(String idUnidad) {
		this.idUnidad = idUnidad;
	}


	public String getEstadoEliminacion() {
		return estadoEliminacion;
	}


	public void setEstadoEliminacion(String estadoEliminacion) {
		this.estadoEliminacion = estadoEliminacion;
	}


	public Date getFechaEliminacion() {
		return fechaEliminacion;
	}


	public void setFechaEliminacion(Date fechaEliminacion) {
		this.fechaEliminacion = fechaEliminacion;
	}


	public String getEstadoActivacion() {
		return estadoActivacion;
	}


	public void setEstadoActivacion(String estadoActivacion) {
		this.estadoActivacion = estadoActivacion;
	}


	public Date getFechaActivacion() {
		return fechaActivacion;
	}


	public void setFechaActivacion(Date fechaActivacion) {
		this.fechaActivacion = fechaActivacion;
	}


	public String getMotivoEliminacion() {
		return motivoEliminacion;
	}


	public void setMotivoEliminacion(String motivoEliminacion) {
		this.motivoEliminacion = motivoEliminacion;
	}


	

}
