package com.sigecap.sigecapexamenbackend.repository.jdbc;

import com.sigecap.sigecapexamenbackend.model.dto.PreguntasPorEncuestaDTO;
import com.sigecap.sigecapexamenbackend.rest.TipoPreguntaController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class EncuestaJdbcRepository {

    private static final Logger logger = LoggerFactory.getLogger(EncuestaJdbcRepository.class);


    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    public List<PreguntasPorEncuestaDTO> getPreguntasPorEncuesta() {
        try {

            String sql = "call spu_ListarPreguntasEncuesta();";
            return jdbcTemplate.query(sql, new getPreguntasPorEncuestaMapper());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

    }

    private static final class getPreguntasPorEncuestaMapper implements RowMapper<PreguntasPorEncuestaDTO> {

        @Override
        public PreguntasPorEncuestaDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            PreguntasPorEncuestaDTO t = new PreguntasPorEncuestaDTO();
            t.setIdTipoPregunta(rs.getString("id_tipo_pregunta"));
            t.setNombreTipoPregunta(rs.getString("no_tipo_pregunta"));
            t.setNombrePregunta(rs.getString("no_pregunta"));
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
