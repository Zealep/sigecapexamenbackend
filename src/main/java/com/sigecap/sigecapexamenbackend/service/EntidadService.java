package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.Entidad;
import com.sigecap.sigecapexamenbackend.model.entity.TipoDocumentosIdentidad;
import com.sigecap.sigecapexamenbackend.model.entity.Usuario;


import java.util.List;

public interface EntidadService {
	
	List<Entidad> getAllEntidades();
	
	List<Entidad> getAllEntidadesByIdTipoEntidad(String idTipoEntidad);
	
	List<Entidad> getAllEntidadesByIdTipoEntidadAndTipoPersona(String idTipoEntidad,String tipoPersona);
	
	List<Entidad> getAllEntidadesByIdTipoEntidadAndTipoPersonaNoResponsable(String idTipoEntidad,String tipoPersona);
	
	List<Entidad> getAllEntidadesResponsableByIdEntidadCliente(String idEntidadCliente);
	
	List<Entidad> getEntidadByIdEntidadCliente(String idEntidadCliente);
	
	public Entidad save(Entidad entidad);
	
	public Entidad consultarEntidadPorNumeroDocumento(String numeroDocumento);
	
	public Entidad consultarEntidadPorTiDocuIdentYnuDocu(TipoDocumentosIdentidad tipoDocumentosIdentidad, String numeroDocumento);
	
	public Entidad consultarEntidadPorIdEntidad(String idEntidad);
	
	public Entidad consultarEntidadPorIdUsuario(Usuario Usuario);
	
	public boolean existeEntidadPorNumeroDocumento(String numeroDocumento);
	
	public Entidad existeEntidadPorCorreoElectronico(String correoElectronico);
	
}
