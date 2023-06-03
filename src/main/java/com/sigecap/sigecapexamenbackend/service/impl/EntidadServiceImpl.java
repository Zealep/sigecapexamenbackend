package com.sigecap.sigecapexamenbackend.service.impl;


import com.sigecap.sigecapexamenbackend.model.entity.Entidad;
import com.sigecap.sigecapexamenbackend.model.entity.TipoDocumentosIdentidad;
import com.sigecap.sigecapexamenbackend.model.entity.Usuario;
import com.sigecap.sigecapexamenbackend.repository.EntidadRepository;
import com.sigecap.sigecapexamenbackend.service.EntidadService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("entidadService")
public class EntidadServiceImpl implements EntidadService {
	
	@Autowired
	EntidadRepository entidadRepository;
	
	
	@Override
	public List<Entidad> getAllEntidades() {
		return entidadRepository.getAllEntidades();
	}
	
	@Override
	public List<Entidad> getEntidadByIdEntidadCliente(String idEntidadCliente) {
		return entidadRepository.getEntidadByIdEntidadCliente(idEntidadCliente);
	}
	
	
	@Override
	public List<Entidad> getAllEntidadesByIdTipoEntidadAndTipoPersona(String idTipoEntidad,String tipoPersona) {
		return entidadRepository.getAllEntidadesByIdTipoEntidadAndTipoPersona(idTipoEntidad,tipoPersona);
	}
	
	@Override
	public List<Entidad> getAllEntidadesByIdTipoEntidadAndTipoPersonaNoResponsable(String idTipoEntidad,String tipoPersona) {
		return entidadRepository.getAllEntidadesByIdTipoEntidadAndTipoPersonaNoResponsable(idTipoEntidad,tipoPersona);
	}
	
	@Override
	public List<Entidad> getAllEntidadesResponsableByIdEntidadCliente(String idEntidadCliente) {
		return entidadRepository.getAllEntidadesResponsableByIdEntidadCliente(idEntidadCliente);
	}
	
	@Override
	public List<Entidad> getAllEntidadesByIdTipoEntidad(String idTipoEntidad) {
		return entidadRepository.getAllEntidadesByIdTipoEntidad(idTipoEntidad);
	}
	
	@Override
	public Entidad save(Entidad entidad) {
		if(entidad.getIdEntidad() == null || entidad.getIdEntidad().equals("")) {
			
			String pk = entidadRepository.generatePrimaryKeyUsuario(Constantes.TABLA_ENTIDAD, Constantes.ID_TABLA_ENTIDAD);
			entidad.setIdEntidad(pk);
			
		}
		entidad.setEstado(Constantes.ESTADO_ACTIVO);
		return entidadRepository.save(entidad);
	}

	@Override
	public Entidad consultarEntidadPorNumeroDocumento(String numeroDocumento) {
		return entidadRepository.findByNumeroDocumento(numeroDocumento);
		
		
	}

	@Override
	public Entidad consultarEntidadPorTiDocuIdentYnuDocu(TipoDocumentosIdentidad tipoDocumentosIdentidad, String numeroDocumento) {
		return entidadRepository.findByTipoDocumentosIdentidadAndNumeroDocumentoAndEstado(tipoDocumentosIdentidad,numeroDocumento,"ACT");
		
		
	}
	@Override
	public Entidad consultarEntidadPorIdEntidad(String idEntidad) {
		return entidadRepository.findByIdEntidad(idEntidad);
		
		
	}
	
	@Override
	public Entidad consultarEntidadPorIdUsuario(Usuario Usuario) {
		return entidadRepository.findByUsuario(Usuario);
		
		
	}
	
	@Override
	public boolean existeEntidadPorNumeroDocumento(String numeroDocumento) {
		return entidadRepository.findByNumeroDocumento(numeroDocumento)!=null?true:false;
	}
	
	@Override
	public Entidad existeEntidadPorCorreoElectronico(String correoElectronico) {
		return entidadRepository.findByCorreoElectronico(correoElectronico);
	}
	
}
