package com.sigecap.sigecapexamenbackend.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sigecap.sigecapexamenbackend.model.dto.ParticipanteInscritoDto;




@Repository
public interface ParticipanteInscritoRepository extends JpaRepository<ParticipanteInscritoDto,Long> {

	@Query(nativeQuery = true,
			value = "call spu_ListarParticipantesInscritosPorCriterios(:parIdCurso,:parIdCursoGrupo,:parFecFinGrupoRi,:parFecFinGrupoRf,:parIdEntidadCliente)")
	List<ParticipanteInscritoDto> getListParticipantesInscritosPorCriterios(
			@Param("parIdCurso")String parIdCurso,
			@Param("parIdCursoGrupo")String parIdCursoGrupo,
			@Param("parFecFinGrupoRi")String parFecFinGrupoRi,
			@Param("parFecFinGrupoRf")String parFecFinGrupoRf,
			@Param("parIdEntidadCliente")String parIdEntidadCliente);
	
	@Query(nativeQuery = true, value = 
    		"select  "
    		+ "p.id_participante id_participante, "
    		+ "p.id_tipo_docu_identidad id_tipo_docu_identidad, "
    		+ "1 as orden_mostrar, "
    		+ "tdi.no_tipo_docu_identidad no_tipo_docu_identidad, "
    		+ "p.numero_documento numero_documento, "
    		+ "p.apellido_paterno apellido_paterno, "
    		+ "p.apellido_materno apellido_materno, "
    		+ "p.nombres nombres, "
    		+ "si.id_entidad_cliente, "
    		+ "ifnull(e.razon_social,'PARTICULAR') nombre_empresa, "
    		+ "p.cargo cargo, "
    		+ "p.correo correo, "
    		+ "p.celular celular, "
    		+ "c.id_curso, "
    		+ "c.nombre_curso, "
    		+ "c.nombre_corto_curso,"
    		+ "c.ponente, "
    		+ "cg.id_curso_grupo, "
    		+ "cg.nombre_grupo "
    		+ "from sgc_tz_curso c  "
    		+ "inner join sgc_tz_curso_grupo cg  on c.id_curso = cg.id_curso  "
    		+ "inner join sgc_tz_solicitud_inscripcion si on si.id_curso_grupo = cg.id_curso_grupo  "
    		+ "inner join sgc_tz_solicitud_inscripcion_detalle sid on sid.id_solicitud_inscripcion = si.id_solicitud_inscripcion  "
    		+ "inner join sgc_tz_participante p  on sid.id_participante = p.id_participante "
    		+ "inner join sgc_tm_tipo_docu_identidad tdi  on p.id_tipo_docu_identidad = tdi.id_tipo_docu_identidad "
    		+ "inner join sgc_tz_entidad e       on e.id_entidad = si.id_entidad_cliente "
    		+ "where c.id_curso = ?1")
	List<ParticipanteInscritoDto> getListParticipanteByIdCurso(String idCurso);
    
    @Query(nativeQuery = true, value = 
    		"select  "
    		+ "p.id_participante id_participante, "
    		+ "p.id_tipo_docu_identidad id_tipo_docu_identidad,"
    		+ "1 as orden_mostrar, "
    		+ "tdi.no_tipo_docu_identidad no_tipo_docu_identidad, "
    		+ "p.numero_documento numero_documento, "
    		+ "p.apellido_paterno apellido_paterno, "
    		+ "p.apellido_materno apellido_materno, "
    		+ "p.nombres nombres, "
    		+ "si.id_entidad_cliente, "
    		+ "ifnull(e.razon_social,'PARTICULAR') nombre_empresa, "
    		+ "p.cargo cargo, "
    		+ "p.correo correo, "
    		+ "p.celular celular, "
    		+ "c.id_curso, "
    		+ "c.nombre_curso, "
    		+ "c.nombre_corto_curso, "
    		+ "c.ponente, "
    		+ "cg.id_curso_grupo, "
    		+ "cg.nombre_grupo "
    		+ "from sgc_tz_curso c  "
    		+ "inner join sgc_tz_curso_grupo cg  on c.id_curso = cg.id_curso  "
    		+ "inner join sgc_tz_solicitud_inscripcion si on si.id_curso_grupo = cg.id_curso_grupo  "
    		+ "inner join sgc_tz_solicitud_inscripcion_detalle sid on sid.id_solicitud_inscripcion = si.id_solicitud_inscripcion  "
    		+ "inner join sgc_tz_participante p  on sid.id_participante = p.id_participante "
    		+ "inner join sgc_tm_tipo_docu_identidad tdi  on p.id_tipo_docu_identidad = tdi.id_tipo_docu_identidad "
    		+ "inner join sgc_tz_entidad e       on e.id_entidad = si.id_entidad_cliente "
    		+ "where  c.id_curso = ?1 and cg.id_curso_grupo = ?2")
	List<ParticipanteInscritoDto> getListParticipanteByIdCursoAndIdCursoGrupo(String idCurso,String idCursoGrupo);
    

}
