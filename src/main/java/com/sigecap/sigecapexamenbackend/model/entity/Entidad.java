package com.sigecap.sigecapexamenbackend.model.entity;
import javax.persistence.*;


@Entity
@Table(name="sgc_tz_entidad")
public class Entidad {
	
	@Id
	@Column(name="id_entidad")
	private String idEntidad;

	@ManyToOne
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
	private Usuario usuario;
	
	@ManyToOne
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_entidad", referencedColumnName = "id_tipo_entidad")
	private TipoEntidad tipoEntidad;
	
	@ManyToOne
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_tipo_docu_identidad", referencedColumnName = "id_tipo_docu_identidad")
	private TipoDocumentosIdentidad tipoDocumentosIdentidad;
	
	
	@Column(name="numero_documento")
	private String numeroDocumento;
	
	@Column(name="apellido_paterno")
	private String apellidoPaterno;
	
	@Column(name="apellido_materno")
	private String apellidoMaterno;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="razon_social")
	private String razonSocial;
	
	@Column(name="tipo_persona")
	private String tipoPersona;
	
	@Column(name="genero")
	private String genero;
	
	@Column(name="id_pais")
	private String idPais;
	
	@Column(name="id_dpto")
	private String idDpto;
	
	@Column(name="id_prov")
	private String idProv;
	
	@Column(name="id_ubigeo")
	private String idUbigeo;
	
	@Column(name="direccion")
	private String direccion;
	
	@Column(name="telefono_fijo")
	private String telefonoFijo;
	
	@Column(name="telefono_movil")
	private int telefonoMovil;
	
	@Column(name="correo_electronico")
	private String correoElectronico;
	
	@Column(name="estado")
	private String estado;

	public String getIdEntidad() {
		return idEntidad;
	}

	public void setIdEntidad(String idEntidad) {
		this.idEntidad = idEntidad;
	}


	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getNombres() {
		return nombres;
	}

	public void setNombres(String nombres) {
		this.nombres = nombres;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getIdPais() {
		return idPais;
	}

	public void setIdPais(String idPais) {
		this.idPais = idPais;
	}

	public String getIdDpto() {
		return idDpto;
	}

	public void setIdDpto(String idDpto) {
		this.idDpto = idDpto;
	}

	public String getIdProv() {
		return idProv;
	}

	public void setIdProv(String idProv) {
		this.idProv = idProv;
	}

	public String getIdUbigeo() {
		return idUbigeo;
	}

	public void setIdUbigeo(String idUbigeo) {
		this.idUbigeo = idUbigeo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefonoFijo() {
		return telefonoFijo;
	}

	public void setTelefonoFijo(String telefonoFijo) {
		this.telefonoFijo = telefonoFijo;
	}

	public int getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(int telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public TipoEntidad getTipoEntidad() {
		return tipoEntidad;
	}

	public void setTipoEntidad(TipoEntidad tipoEntidad) {
		this.tipoEntidad = tipoEntidad;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public TipoDocumentosIdentidad getTipoDocumentosIdentidad() {
		return tipoDocumentosIdentidad;
	}

	public void setTipoDocumentosIdentidad(TipoDocumentosIdentidad tipoDocumentosIdentidad) {
		this.tipoDocumentosIdentidad = tipoDocumentosIdentidad;
	}


	
}
