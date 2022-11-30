package com.sigecap.sigecapexamenbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteInscritoDto;
import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteKardexDto;
import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteNotaDto;
import com.sigecap.sigecapexamenbackend.repository.ParticipanteInscritoRepository;
import com.sigecap.sigecapexamenbackend.repository.ParticipanteKardexRepository;
import com.sigecap.sigecapexamenbackend.repository.ParticipanteNotaRepository;
import com.sigecap.sigecapexamenbackend.service.ParticipanteDtoService;


@Service("participanteInscritoService")
public class ParticipanteDtoServiceImpl implements ParticipanteDtoService{
	
	@Autowired
	ParticipanteInscritoRepository participanteInscritoRepository;

	@Autowired
	ParticipanteNotaRepository participanteNotaRepository;

	@Autowired
	ParticipanteKardexRepository participanteKardexRepository;
	
	@Override
	public List<ParticipanteInscritoDto> getListParticipanteByIdCursoAndIdCursoGrupo(String idCurso,String idCursoGrupo) {
		if(idCursoGrupo.equals("-1")) {
			return participanteInscritoRepository.getListParticipanteByIdCurso(idCurso);
		}else {
			return participanteInscritoRepository.getListParticipanteByIdCursoAndIdCursoGrupo(idCurso,idCursoGrupo);
		}
		
	}
	
	@Override
	public List<ParticipanteInscritoDto> getListParticipantesInscritosPorCriterios(String idCurso,String idCursoGrupo,String fecFinGrupoRi,String fecFinGrupoRf,String idEntidadCliente) {
		return participanteInscritoRepository.getListParticipantesInscritosPorCriterios(idCurso,idCursoGrupo,fecFinGrupoRi,fecFinGrupoRf,idEntidadCliente);
		
	}

	@Override
	public List<ParticipanteNotaDto> getListParticipanteNotaPorCriterios(String idSi,String parNuDocumentoAlumno,String idCurso,String fecFinGrupoRi,String fecFinGrupoRf,String idEntidadCliente,String idUnidad) {
		return participanteNotaRepository.getListParticipanteNotaPorCriterios(idSi,parNuDocumentoAlumno,idCurso,fecFinGrupoRi,fecFinGrupoRf,idEntidadCliente,idUnidad);
		
	}
	
	@Override
	public List<ParticipanteKardexDto> getListParticipanteKardexPorCriterios(String parNuDocumentoAlumno,String idCurso,String fecFinGrupoRi,String fecFinGrupoRf,String idEntidadCliente) {
		return participanteKardexRepository.getListParticipanteKardexPorCriterios(parNuDocumentoAlumno,idCurso,fecFinGrupoRi,fecFinGrupoRf,idEntidadCliente);
		
	}

}
