package com.sigecap.sigecapexamenbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteKardexDto;





@Repository
public interface ParticipanteKardexRepository extends JpaRepository<ParticipanteKardexDto,Long> {

	@Query(nativeQuery = true,
			value = "call spu_ListaParticipantesParaKardex(:parNuDocumentoAlumno,:parIdCurso,:parFecFinGrupoRi,:parFecFinGrupoRf,:parIdEntidadCliente)")
	List<ParticipanteKardexDto> getListParticipanteKardexPorCriterios(
			@Param("parNuDocumentoAlumno")String parNuDocumentoAlumno,
			@Param("parIdCurso")String parIdCurso,
			@Param("parFecFinGrupoRi")String parFecFinGrupoRi,
			@Param("parFecFinGrupoRf")String parFecFinGrupoRf,
			@Param("parIdEntidadCliente")String parIdEntidadCliente);
    

}
