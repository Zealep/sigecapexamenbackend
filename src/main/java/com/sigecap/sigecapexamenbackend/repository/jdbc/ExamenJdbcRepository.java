package com.sigecap.sigecapexamenbackend.repository.jdbc;

import com.sigecap.sigecapexamenbackend.model.dto.BandejaExamenPorAlumnoDTO;
import com.sigecap.sigecapexamenbackend.model.dto.MenuUsuarioDTO;
import com.sigecap.sigecapexamenbackend.model.dto.PreguntasPorExamenDTO;
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


    public List<BandejaExamenPorAlumnoDTO> getBandejaPorAlumno(String usuario){
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("usuario",usuario);

            String sql = "call spu_ListarBandejaExamenes( '"+usuario+"');";
                /*
            String sql = "select " +
                    "cg.id_curso_grupo," +
                    "c.id_curso," +
                    "c.nombre_curso ," +
                    "ea.fecha_hora_apertura," +
                    "ea.fecha_hora_cierre," +
                    "ea.estado," +
                    "sid.promedio," +
                    "ea.in_realizar_encuesta " +
                    "from sgc_tz_curso_grupo cg " +
                    "inner join sgc_tz_curso c on cg.id_curso = c.id_curso " +
                    "inner join sgc_tz_solicitud_inscripcion si on cg.id_curso_grupo = si.id_curso_grupo " +
                    "inner join sgc_tz_solicitud_inscripcion_detalle sid on si.id_solicitud_inscripcion = sid.id_solicitud_inscripcion " +
                    "inner join sge_tz_examen_apertura ea on cg.id_curso_grupo = ea.id_curso_grupo " +
                    "inner join sgc_tz_participante p on sid.id_participante = p.id_participante " +
                    "inner join sgc_tz_usuario u on p.id_usuario = u.id_usuario " +
                    "where cg.estado != 'CER' " +
                    "and u.id_usuario = :usuario;";

            */

            return jdbcTemplate.query(sql, parameters,new getBandejaPorAlumnoMapper());

        }catch (Exception e){
            return null;
        }


    }

    private static final class getBandejaPorAlumnoMapper implements RowMapper<BandejaExamenPorAlumnoDTO> {

        @Override
        public BandejaExamenPorAlumnoDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            BandejaExamenPorAlumnoDTO t = new BandejaExamenPorAlumnoDTO();
            t.setIdSidExamen(rs.getLong("id_sid_examen"));
            t.setIdSolicitudInscripcionDetalle(rs.getString("id_solicitud_inscripcion_detalle"));
            t.setIdUsuario(rs.getString("id_usuario"));
            t.setNombreUsuario(rs.getString("nombre_usuario"));
            t.setNombreCliente(rs.getString("nombre_cliente"));
            t.setIdCursoGrupo(rs.getString("id_curso_grupo"));
            t.setIdCurso(rs.getString("id_curso"));
            t.setNombreCurso(rs.getString("nombre_curso"));
            t.setIdExamen(rs.getString("id_examen"));
            t.setNombreExamen(rs.getString("no_examen"));
            t.setFechaHoraApertura(rs.getTimestamp("fecha_hora_apertura"));
            t.setFechaHoraCierre(rs.getTimestamp("fecha_hora_cierre"));
            t.setEstado(rs.getString("estado"));
            t.setNotaUltimoIntento(rs.getInt("nota_ultimo_intento"));
            t.setNotaFinal(rs.getInt("nota_final"));
            t.setIndicadorEncuesta(rs.getString("in_realizar_encuesta"));
            t.setIndicadorRealizoEncuesta(rs.getString("in_realizo_encuesta"));
            t.setIndicadorAsistio(rs.getString("in_asistio"));
            t.setIndicadorRealizoExamen(rs.getString("in_realizo_examen"));
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
