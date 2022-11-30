package com.sigecap.sigecapexamenbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.sigecap.sigecapexamenbackend.model.entity.Participante;
import com.sigecap.sigecapexamenbackend.model.entity.TipoDocumentosIdentidad;





@Repository
public interface ParticipanteRepository extends JpaRepository<Participante,Long> {

	
	
    @Procedure(value = "spu_sgc_GeneraCorrelativoAnoMes")
    String generatePrimaryKey(String tabla,String campo);
    
    Participante findByIdParticipante(String idParticipante);
    
    Participante findByTipoDocumentosIdentidadAndNumeroDocumento(TipoDocumentosIdentidad tipoDocumentosIdentidad,String numeroDocumento);
    
    @Query(nativeQuery = true, value = 
    		"select "
    		+ "p.id_participante,"
    		+ "p.id_tipo_docu_identidad,"
    		+ "p.numero_documento,"
    		+ "p.apellido_paterno,"
    		+ "p.apellido_materno,"
    		+ "p.nombres,"
    		+ "p.cargo,"
    		+ "ifnull(p.centro_costo,'') centro_costo,"
    		+ "ifnull(p.unidad,'') unidad,"
    		+ "p.correo,"
    		+ "p.celular "
    		+ "from sgc_tz_curso c "
    		+ "inner join sgc_tz_curso_grupo cg                    on c.id_curso = cg.id_curso "
    		+ "inner join sgc_tz_solicitud_inscripcion si          on si.id_curso_grupo = cg.id_curso_grupo "
    		+ "inner join sgc_tz_solicitud_inscripcion_detalle sid on sid.id_solicitud_inscripcion = si.id_solicitud_inscripcion "
    		+ "inner join sgc_tz_participante p                    on sid.id_participante = p.id_participante "
    		+ "where c.id_curso = ?1")
	List<Participante> getListParticipanteByIdCurso(String idCurso);
    
    @Query(nativeQuery = true, value = 
    		"select "
    		+ "p.id_participante,"
    		+ "p.id_tipo_docu_identidad,"
    		+ "p.numero_documento,"
    		+ "p.apellido_paterno,"
    		+ "p.apellido_materno,"
    		+ "p.nombres,"
    		+ "p.cargo,"
    		+ "ifnull(p.centro_costo,'') centro_costo,"
    		+ "ifnull(p.unidad,'') unidad,"
    		+ "p.correo,"
    		+ "p.celular "
    		+ "from sgc_tz_curso c "
    		+ "inner join sgc_tz_curso_grupo cg                    on c.id_curso = cg.id_curso "
    		+ "inner join sgc_tz_solicitud_inscripcion si          on si.id_curso_grupo = cg.id_curso_grupo "
    		+ "inner join sgc_tz_solicitud_inscripcion_detalle sid on sid.id_solicitud_inscripcion = si.id_solicitud_inscripcion "
    		+ "inner join sgc_tz_participante p                    on sid.id_participante = p.id_participante "
    		+ "where c.id_curso = ?1 and cg.id_curso_grupo = ?2")
	List<Participante> getListParticipanteByIdCursoAndIdCursoGrupo(String idCurso,String idCursoGrupo);
    
    @Query(nativeQuery = true, value = 
    		"select "
    		+ "p.id_participante,"
    		+ "p.id_tipo_docu_identidad,"
    		+ "p.numero_documento,"
    		+ "p.apellido_paterno,"
    		+ "p.apellido_materno,"
    		+ "p.nombres,"
    		+ "p.cargo,"
    		+ "ifnull(p.centro_costo,'') centro_costo,"
    		+ "ifnull(p.unidad,'') unidad,"
    		+ "p.correo,"
    		+ "p.celular "
			+ "from sgc_tz_solicitud_inscripcion si "
			+ "inner join sgc_tz_solicitud_inscripcion_detalle sid on si.id_solicitud_inscripcion = sid.id_solicitud_inscripcion "
			+ "inner join sgc_tz_participante p on sid.id_participante = p.id_participante "
			+ "where si.id_solicitud_inscripcion = ?1 order by numero_documento asc")
	List<Participante> getListParticipanteByIdSolicitudInscripcion(String idSolicitudInscripcion);
}
