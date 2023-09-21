package com.sigecap.sigecapexamenbackend.repository.jdbc;

import com.sigecap.sigecapexamenbackend.model.dto.CursosAsincronosDTO;
import com.sigecap.sigecapexamenbackend.model.dto.PreguntasPorEncuestaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CursoAsincronoRepository {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaJdbcRepository.class);

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public List<CursosAsincronosDTO> getCursosAsincronos(String idUsuario) {

        try {
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("idUsuario",idUsuario);

            String sql = "call spu_sgeListarCursosAsincronosDisponibles('"+idUsuario+"');";
            return jdbcTemplate.query(sql, parameters,(rs, rowNum) -> {
                CursosAsincronosDTO c = new CursosAsincronosDTO();
                c.setIdCursoGrupo(rs.getString("id_curso_grupo"));
                c.setNombreGrupo(rs.getString("nombre_grupo"));
                c.setNombreCurso(rs.getString("nombre_curso"));
                c.setIdCurso(rs.getString("id_curso"));
                c.setIdUsuario(rs.getString("id_usuario"));
                c.setNombreUsuario(rs.getString("nombre_usuario"));
                c.setIdSolInsDetall(rs.getString("id_solicitud_inscripcion_detalle"));
                c.setIdArchivo(rs.getString("id_archivo"));
                c.setNombreArchivo(rs.getString("nombre_archivo"));
                c.setUrlVideoCurso(rs.getString("url_video_curso"));
                return c;
            });
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }
}
