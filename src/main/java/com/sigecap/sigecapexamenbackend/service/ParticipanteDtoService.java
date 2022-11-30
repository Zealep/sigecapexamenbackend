package com.sigecap.sigecapexamenbackend.service;

import java.util.List;

import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteInscritoDto;
import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteKardexDto;
import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteNotaDto;




public interface ParticipanteDtoService {
	
	
	public List<ParticipanteInscritoDto> getListParticipanteByIdCursoAndIdCursoGrupo(String idCurso,String idCursoGrupo);
	
	public List<ParticipanteInscritoDto> getListParticipantesInscritosPorCriterios(String idCurso,String idCursoGrupo,String fecFinGrupoRi,String fecFinGrupoRf,String idEntidadCliente);
	
	public List<ParticipanteNotaDto> getListParticipanteNotaPorCriterios(String idSi,String parNuDocumentoAlumno,String idCurso,String fecFinGrupoRi,String fecFinGrupoRf,String idEntidadCliente,String idUnidad);
	
	public List<ParticipanteKardexDto> getListParticipanteKardexPorCriterios(String parNuDocumentoAlumno,String idCurso,String fecFinGrupoRi,String fecFinGrupoRf,String idEntidadCliente);
	
}
