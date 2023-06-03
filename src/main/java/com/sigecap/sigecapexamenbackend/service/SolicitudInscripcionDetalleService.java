package com.sigecap.sigecapexamenbackend.service;

import com.sigecap.sigecapexamenbackend.model.entity.Participante;
import com.sigecap.sigecapexamenbackend.model.entity.SolicitudInscripcion;
import com.sigecap.sigecapexamenbackend.model.entity.SolicitudInscripcionDetalle;


import java.util.List;

public interface SolicitudInscripcionDetalleService {
	public SolicitudInscripcionDetalle save(SolicitudInscripcionDetalle solicitudInscripcionDetalle);
	
	public void delete(SolicitudInscripcionDetalle solicitudInscripcionDetalle);
	
	public SolicitudInscripcionDetalle consultarSolicitudDetallePorId(String idSolicitudInscripcionDetalle);
	
	public SolicitudInscripcionDetalle consultarSolicitudDetallePorCodigoCertificado(String codigoCertificado);
	
	public SolicitudInscripcionDetalle consultarSolicitudDetallePorSolicitudInscripcionAndParticipante(SolicitudInscripcion solicitudInscripcion, Participante objParticipante);
	
	public List<SolicitudInscripcionDetalle> findBySolicitudInscripcion(SolicitudInscripcion objSolicitudInscripcion);
	
	public List<SolicitudInscripcionDetalle> consultarSolicitudDetallePorParticipante(Participante objParticipante);
	

}
