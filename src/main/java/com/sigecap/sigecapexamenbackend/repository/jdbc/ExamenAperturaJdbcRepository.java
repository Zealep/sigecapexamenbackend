package com.sigecap.sigecapexamenbackend.repository.jdbc;

import com.sigecap.sigecapexamenbackend.model.dto.ExamenParticipanteDTO;
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
public class ExamenAperturaJdbcRepository {

    private static final Logger logger = LoggerFactory.getLogger(ExamenParticipanteJdbcRepository.class);

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    public List<String> getNuevosInscritos(String idCursoGrupo, String idExamenApertura){
        try{
            MapSqlParameterSource parameters = new MapSqlParameterSource();
            parameters.addValue("idCursoGrupo",idCursoGrupo);
            parameters.addValue("idSolicitudInscripcionDetalle",idExamenApertura);
            String sql = "call spu_sgeListarParticipantesNuevosPorGrupoYApertura( '"+idCursoGrupo+"','"+idExamenApertura+"');";
            return jdbcTemplate.query(sql, parameters, new GetNuevosInscritosMapper());

        }catch (Exception e){
            logger.error(e.getMessage());
            return null;
        }

    }

    private static final class GetNuevosInscritosMapper implements RowMapper<String> {

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString("id_solicitud_inscripcion_detalle");
        }
    }
}
