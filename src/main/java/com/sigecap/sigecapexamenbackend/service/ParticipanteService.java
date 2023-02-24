package com.sigecap.sigecapexamenbackend.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import com.sigecap.sigecapexamenbackend.model.entity.Participante;
import com.sigecap.sigecapexamenbackend.model.entity.TipoDocumentosIdentidad;


public interface ParticipanteService {
	public Participante save(Participante objCurso);
	
	public Participante findByIdParticipante(String idParticipante);
	
	public Participante findByTipoDocumentosIdentidadAndNumeroDocumento(TipoDocumentosIdentidad tipoDocumentosIdentidad,String numeroDocumento);
	
	public List<Participante> getListParticipanteByIdCursoAndIdCursoGrupo(String idCurso,String idCursoGrupo);
	
	public List<Participante> getListParticipanteByIdSolicitudInscripcion(String idSolicitudInscripcion);
}
