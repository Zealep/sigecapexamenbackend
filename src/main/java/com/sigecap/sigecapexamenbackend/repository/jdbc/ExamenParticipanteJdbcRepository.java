package com.sigecap.sigecapexamenbackend.repository.jdbc;

import com.sigecap.sigecapexamenbackend.model.dto.CursosDisponibleExamenAlumnoDTO;
import com.sigecap.sigecapexamenbackend.model.dto.ExamenParticipanteDTO;
import com.sigecap.sigecapexamenbackend.model.dto.IntentoExamenDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ExamenParticipanteJdbcRepository {


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public List<ExamenParticipanteDTO> getExamenesParticipante(String idCursoGrupo, String idSolicitudInscripcionDetalle){
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("idCursoGrupo",idCursoGrupo);
            parameters.addValue("idSolicitudInscripcionDetalle",idSolicitudInscripcionDetalle);


            String sql = "call spu_sgeListarExamenesPorGrupoYparticipante( '"+idCursoGrupo+"','"+idSolicitudInscripcionDetalle+"');";


            return jdbcTemplate.query(sql, parameters, new getExamenParticipanteMapper());

        }catch (Exception e){
            return null;
        }


    }

    private static final class getExamenParticipanteMapper implements RowMapper<ExamenParticipanteDTO> {

        @Override
        public ExamenParticipanteDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            ExamenParticipanteDTO t = new ExamenParticipanteDTO();
            t.setIdSidExamen(rs.getLong("id_sid_examen"));
            t.setIdCursoGrupo(rs.getString("id_curso_grupo"));
            t.setNombreGrupo(rs.getString("nombre_grupo"));
            t.setIdCurso(rs.getString("id_curso"));
            t.setNombreCurso(rs.getString("nombre_curso"));
            t.setIdExamen(rs.getString("id_examen"));
            t.setNombreExamen(rs.getString("no_examen"));
            t.setFechaHoraApertura(rs.getTimestamp("fecha_hora_apertura"));
            t.setFechaHoraCierre(rs.getTimestamp("fecha_hora_cierre"));
            t.setNroIntentosPermitidos(rs.getInt("numero_intentos_permitidos"));
            t.setNroIntentosRealizados(rs.getInt("numero_intentos_realizados"));
            t.setEstado(rs.getString("estado"));
            t.setNombreEstado(rs.getString("nombre_estado"));
            t.setIndicadorAsistio(rs.getString("in_asistio"));
            return t;

        }
    }



    public List<IntentoExamenDTO> getIntentoExamen(Long idSidExamen){
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("idSidExamen",idSidExamen);


            String sql = "call spu_sgeListarIntentosPorExamen( '"+idSidExamen+"');";


            return jdbcTemplate.query(sql, parameters, new getIntentoExamenMapper());

        }catch (Exception e){
            return null;
        }


    }

    private static final class getIntentoExamenMapper implements RowMapper<IntentoExamenDTO> {

        @Override
        public IntentoExamenDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            IntentoExamenDTO t = new IntentoExamenDTO();
            t.setIdSidIntento(rs.getLong("id_sid_por_ea_intento"));
            t.setFechaInicio(rs.getTimestamp("fecha_inicio"));
            t.setFechaTermino(rs.getTimestamp("fecha_termino"));
            t.setNota(rs.getDouble("nota"));
            t.setCodigoEstado(rs.getString("codigo_estado"));
            t.setNombreEstado(rs.getString("nombre_estado"));
            t.setComentario(rs.getString("comentario"));

            return t;

        }
    }
}
