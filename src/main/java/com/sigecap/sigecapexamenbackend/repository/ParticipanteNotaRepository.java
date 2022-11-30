package com.sigecap.sigecapexamenbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteNotaDto;




@Repository
public interface ParticipanteNotaRepository extends JpaRepository<ParticipanteNotaDto,Long> {

	@Query(nativeQuery = true,
			value = "call spu_ListarNotasParticipantesPorCriterios(:parIdSolicitudInscripcion,:parNuDocumentoAlumno,:parIdCurso,:parFecFinGrupoRi,:parFecFinGrupoRf,:parIdEntidadCliente, :parIdUnidad)")
	List<ParticipanteNotaDto> getListParticipanteNotaPorCriterios(
			@Param("parIdSolicitudInscripcion")String parIdSolicitudInscripcion,
			@Param("parNuDocumentoAlumno")String parNuDocumentoAlumno,
			@Param("parIdCurso")String parIdCurso,
			@Param("parFecFinGrupoRi")String parFecFinGrupoRi,
			@Param("parFecFinGrupoRf")String parFecFinGrupoRf,
			@Param("parIdEntidadCliente")String parIdEntidadCliente,
			@Param("parIdUnidad")String parIdUnidad
			);
    

}
