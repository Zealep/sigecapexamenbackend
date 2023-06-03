package com.sigecap.sigecapexamenbackend.service.impl;


import com.sigecap.sigecapexamenbackend.model.entity.Participante;
import com.sigecap.sigecapexamenbackend.model.entity.SolicitudInscripcion;
import com.sigecap.sigecapexamenbackend.model.entity.SolicitudInscripcionDetalle;
import com.sigecap.sigecapexamenbackend.repository.SolicitudInscripcionDetalleRepository;
import com.sigecap.sigecapexamenbackend.service.SolicitudInscripcionDetalleService;
import com.sigecap.sigecapexamenbackend.util.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service("solicitudInscripcionDetalleService")
public class SolicitudInscripcionDetalleServiceImpl implements SolicitudInscripcionDetalleService {
	
	@Autowired
	SolicitudInscripcionDetalleRepository solicitudInscripcionDetalleRepository;
	
	
	@Override
	public SolicitudInscripcionDetalle save(SolicitudInscripcionDetalle solicitudInscripcionDetalle) {
		String pk = "";
		if(solicitudInscripcionDetalle.getIdSolicitudInscripcionDetalle() == null) {
			pk = solicitudInscripcionDetalleRepository.generatePrimaryKey(Constantes.TABLA_SOLICITUD_INSCRIPCION_DETALLE, Constantes.ID_TABLA_SOLICITUD_INSCRIPCION_DETALLE);
			solicitudInscripcionDetalle.setIdSolicitudInscripcionDetalle(pk);
		}
		
		
		return solicitudInscripcionDetalleRepository.save(solicitudInscripcionDetalle);
	}

	@Override
	public void delete(SolicitudInscripcionDetalle solicitudInscripcionDetalle) {
		solicitudInscripcionDetalleRepository.delete(solicitudInscripcionDetalle);
	}
	
	@Override
	public SolicitudInscripcionDetalle consultarSolicitudDetallePorId(String idSolicitudInscripcionDetalle) {
		return solicitudInscripcionDetalleRepository.findByIdSolicitudInscripcionDetalle(idSolicitudInscripcionDetalle);
		
	}
	
	@Override
	public SolicitudInscripcionDetalle consultarSolicitudDetallePorCodigoCertificado(String codigoCertificado) {
		return solicitudInscripcionDetalleRepository.findByCodigoCertificado(codigoCertificado);
		
	}
	
	@Override
	public List<SolicitudInscripcionDetalle> findBySolicitudInscripcion(SolicitudInscripcion objSolicitudInscripcion) {
		return solicitudInscripcionDetalleRepository.findBySolicitudInscripcion(objSolicitudInscripcion);
		
	}
	
	@Override
	public SolicitudInscripcionDetalle consultarSolicitudDetallePorSolicitudInscripcionAndParticipante(SolicitudInscripcion solicitudInscripcion, Participante objParticipante) {
		return solicitudInscripcionDetalleRepository.findBySolicitudInscripcionAndParticipante(solicitudInscripcion,objParticipante);
		
	}
	
	@Override
	public List<SolicitudInscripcionDetalle> consultarSolicitudDetallePorParticipante(Participante objParticipante) {
		return solicitudInscripcionDetalleRepository.findByParticipante(objParticipante);
		
	}
	




}
