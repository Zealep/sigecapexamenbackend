package com.sigecap.sigecapexamenbackend.model.entity;

import javax.persistence.*;


@Entity
@Table(name="sgc_tz_participante")
public class Participante{
	
	@Id
	@Column(name="id_participante")
	private String idParticipante;
	
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
	
	@Column(name="cargo")
	private String cargo;

	@Column(name="centro_costo")
	private String centroCosto;
	
	@ManyToOne
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "unidad", referencedColumnName = "id_unidad")
	private Unidad unidad;
	
	@Column(name="correo")
	private String correo;
	
	@Column(name="celular")
	private int celular;

	public String getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(String idParticipante) {
		this.idParticipante = idParticipante;
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

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public int getCelular() {
		return celular;
	}

	public void setCelular(int celular) {
		this.celular = celular;
	}

	public TipoDocumentosIdentidad getTipoDocumentosIdentidad() {
		return tipoDocumentosIdentidad;
	}

	public void setTipoDocumentosIdentidad(TipoDocumentosIdentidad tipoDocumentosIdentidad) {
		this.tipoDocumentosIdentidad = tipoDocumentosIdentidad;
	}

	public String getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(String centroCosto) {
		this.centroCosto = centroCosto;
	}

	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}


	
}
