package com.sigecap.sigecapexamenbackend.repository.jdbc;

import com.sigecap.sigecapexamenbackend.model.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class ExamenJdbcRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    public List<CursosDisponibleExamenAlumnoDTO> getBandejaPorAlumno(String usuario){
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("usuario",usuario);

            String sql = "call spu_sgeListarCursosDisponiblesParaExamen( '"+usuario+"');";


            return jdbcTemplate.query(sql, parameters,new getBandejaPorAlumnoMapper());

        }catch (Exception e){
            return null;
        }


    }

    private static final class getBandejaPorAlumnoMapper implements RowMapper<CursosDisponibleExamenAlumnoDTO> {

        @Override
        public CursosDisponibleExamenAlumnoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            CursosDisponibleExamenAlumnoDTO t = new CursosDisponibleExamenAlumnoDTO();
            t.setIdCursoGrupo(rs.getString("id_curso_grupo"));
            t.setNombreGrupo(rs.getString("nombre_grupo"));
            t.setIdCurso(rs.getString("id_curso"));
            t.setNombreCurso(rs.getString("nombre_curso"));
            t.setIdUsuario(rs.getString("id_usuario"));
            t.setNombreUsuario(rs.getString("nombre_usuario"));
            t.setIdSolicitudInscripcionDetall(rs.getString("id_solicitud_inscripcion_detalle"));
            t.setNombreCliente(rs.getString("nombre_cliente"));
            t.setIndicadorRealizarEncuesta(rs.getString("indicador_realizar_encuesta"));
            t.setIndicadorRealizoEncuesta(rs.getString("indicador_realizo_encuesta"));
            t.setIdArchivo(rs.getString("id_archivo"));
            t.setNombreArchivo(rs.getString("nombre_archivo"));
            return t;

        }
    }

    public List<PreguntasPorExamenDTO> getPreguntasPorExamen(String examen){
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("examen",examen);

            String sql = "call spu_ListarPreguntasPorExamen( '"+examen+"');";

            return jdbcTemplate.query(sql, parameters,new getPreguntasPorExamenMapper());

        }catch (Exception e){
            return null;
        }

    }

    private static final class getPreguntasPorExamenMapper implements RowMapper<PreguntasPorExamenDTO> {

        @Override
        public PreguntasPorExamenDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            PreguntasPorExamenDTO t = new PreguntasPorExamenDTO();
            t.setIdTipoPregunta(rs.getString("id_tipo_pregunta"));
            t.setNombreTipoPregunta(rs.getString("no_tipo_pregunta"));
            t.setIdPregunta(rs.getString("id_pregunta"));
            t.setEnunciadoPregunta(rs.getString("enunciado_pregunta"));
            t.setRetroAlimentacionPregunta(rs.getString("retroalimentacion_pregunta"));
            t.setIdRespuesta(rs.getString("id_respuesta"));
            t.setEnunciadoRespuesta(rs.getString("enunciado_respuesta"));
            t.setRetroAlimentacionRespuesta(rs.getString("retroalimentacion_respuesta"));
            return t;

        }


    }



}
