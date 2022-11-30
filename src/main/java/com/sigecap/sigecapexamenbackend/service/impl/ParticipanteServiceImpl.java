package com.sigecap.sigecapexamenbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sigecap.sigecapexamenbackend.model.entity.Participante;
import com.sigecap.sigecapexamenbackend.model.entity.TipoDocumentosIdentidad;
import com.sigecap.sigecapexamenbackend.repository.ParticipanteRepository;
import com.sigecap.sigecapexamenbackend.service.ParticipanteService;
import com.sigecap.sigecapexamenbackend.util.Constantes;



@Service("participanteService")
public class ParticipanteServiceImpl implements ParticipanteService{
	
	@Autowired
	ParticipanteRepository participanteRepository;

	

	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackForClassName={"Exception"})
	public Participante save(Participante objParticipante) {
		String pk = "";
		if(objParticipante.getIdParticipante() == null) {
			pk = participanteRepository.generatePrimaryKey(Constantes.TABLA_PARTICIPANTE, Constantes.ID_TABLA_PARTICIPANTE);
			objParticipante.setIdParticipante(pk);
		}
		return participanteRepository.save(objParticipante);
	}
	
	@Override
	public Participante findByIdParticipante(String idParticipante) {
		
		return participanteRepository.findByIdParticipante(idParticipante);
	}
	
	@Override
	public Participante findByTipoDocumentosIdentidadAndNumeroDocumento(TipoDocumentosIdentidad tipoDocumentosIdentidad,String numeroDocumento) {
		
		return participanteRepository.findByTipoDocumentosIdentidadAndNumeroDocumento(tipoDocumentosIdentidad,numeroDocumento);
	}
	
	@Override
	public List<Participante> getListParticipanteByIdCursoAndIdCursoGrupo(String idCurso,String idCursoGrupo) {
		if(idCursoGrupo.equals("-1")) {
			return participanteRepository.getListParticipanteByIdCurso(idCurso);
		}else {
			return participanteRepository.getListParticipanteByIdCursoAndIdCursoGrupo(idCurso,idCursoGrupo);
		}
		
	}
	
	@Override
	public List<Participante> getListParticipanteByIdSolicitudInscripcion(String idSolicitudInscripcion) {
		
		return participanteRepository.getListParticipanteByIdSolicitudInscripcion(idSolicitudInscripcion);
	}
	
}
