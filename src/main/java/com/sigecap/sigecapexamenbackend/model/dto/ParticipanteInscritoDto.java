package com.sigecap.sigecapexamenbackend.model.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ParticipanteInscritoDto{
	
	@Id
	@Column(name="id_participante")
	private String idParticipante;
	
	@Column(name="orden_mostrar")
	private int ordenMostrar;
	
	@Column(name="id_tipo_docu_identidad")
	private int idTipoDocuIdentidad;
	
	@Column(name="no_tipo_docu_identidad")
	private String nombreTipoDocuIdentidad;
	
	@Column(name="numero_documento")
	private String numeroDocumento;
	
	@Column(name="apellido_paterno")
	private String apellidoPaterno;
	
	@Column(name="apellido_materno")
	private String apellidoMaterno;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="unidad")
	private String unidad;
	
	@Column(name="centro_costo")
	private String centroCosto;
	
	@Column(name="nombre_empresa")
	private String nombreEmpresa;
	
	@Column(name="cargo")
	private String cargo;

	@Column(name="correo")
	private String correo;
	
	@Column(name="celular")
	private int celular;
	
	@Column(name="id_curso")
	private String idCurso;
	
	@Column(name="nombre_curso")
	private String nombreCurso;
	
	@Column(name="nombre_corto_curso")
	private String nombreCortoCurso;
	
	@Column(name="id_curso_grupo")
	private String idCursoGrupo;
	
	@Column(name="nombre_grupo")
	private String nombreGrupo;

	@Column(name="ponente")
	private String ponente;

	@Column(name="asistio")
	private String asistio;

	@Column(name="id_solicitud_inscripcion_detalle")
	private String idSolicitudInscripcionDetalle;

	public String getIdSolicitudInscripcionDetalle() {
		return idSolicitudInscripcionDetalle;
	}

	public void setIdSolicitudInscripcionDetalle(String idSolicitudInscripcionDetalle) {
		this.idSolicitudInscripcionDetalle = idSolicitudInscripcionDetalle;
	}

	public String getIdParticipante() {
		return idParticipante;
	}

	public void setIdParticipante(String idParticipante) {
		this.idParticipante = idParticipante;
	}

	public int getIdTipoDocuIdentidad() {
		return idTipoDocuIdentidad;
	}

	public void setIdTipoDocuIdentidad(int idTipoDocuIdentidad) {
		this.idTipoDocuIdentidad = idTipoDocuIdentidad;
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

	public String getNombreEmpresa() {
		return nombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		this.nombreEmpresa = nombreEmpresa;
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

	public String getNombreTipoDocuIdentidad() {
		return nombreTipoDocuIdentidad;
	}

	public void setNombreTipoDocuIdentidad(String nombreTipoDocuIdentidad) {
		this.nombreTipoDocuIdentidad = nombreTipoDocuIdentidad;
	}

	public String getNombreCortoCurso() {
		return nombreCortoCurso;
	}

	public void setNombreCortoCurso(String nombreCortoCurso) {
		this.nombreCortoCurso = nombreCortoCurso;
	}

	public String getPonente() {
		return ponente;
	}

	public void setPonente(String ponente) {
		this.ponente = ponente;
	}

	public int getOrdenMostrar() {
		return ordenMostrar;
	}

	public void setOrdenMostrar(int ordenMostrar) {
		this.ordenMostrar = ordenMostrar;
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

	public String getAsistio() {
		return asistio;
	}

	public void setAsistio(String asistio) {
		this.asistio = asistio;
	}
}
