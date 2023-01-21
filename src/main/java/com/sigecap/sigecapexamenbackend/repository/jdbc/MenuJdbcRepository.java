package com.sigecap.sigecapexamenbackend.repository.jdbc;

import com.sigecap.sigecapexamenbackend.model.dto.MenuUsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class MenuJdbcRepository {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    public List<MenuUsuarioDTO> getMenuUsuario(String usuario){

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("usuario",usuario);

        String sql = "select " +
                "u.id_usuario," +
                "u.nombre_usuario," +
                "r.id_rol," +
                "r.no_rol," +
                "u.contraseña," +
                "m.no_menu," +
                "m.url_operacion" +
                " from sgc_tz_usuario u " +
                "inner join sgc_tz_usuario_por_rol uxr on u.id_usuario = uxr.id_usuario " +
                "inner join sgc_tm_rol r on uxr.id_rol = r.id_rol " +
                "inner join sgc_tm_rol_por_menu rxm on r.id_rol = rxm.id_rol " +
                "inner join sgc_tm_menu m on rxm.id_menu = m.id_menu " +
                "where r.sistema = 'SIGECAPEXAMEN' and u.nombre_usuario = :usuario;";

        return jdbcTemplate.query(sql, parameters,new getMenuUsuarioMapper());
    }

    private static final class getMenuUsuarioMapper implements RowMapper<MenuUsuarioDTO> {

        @Override
        public MenuUsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            MenuUsuarioDTO t = new MenuUsuarioDTO();
            t.setIdUsuario(rs.getString("id_usuario"));
            t.setNombreUsuario(rs.getString("nombre_usuario"));
            t.setIdRol(rs.getString("id_rol"));
            t.setNombreRol(rs.getString("no_rol"));
            t.setContraseña(rs.getString("contraseña"));
            t.setNombreMenu(rs.getString("no_menu"));
            t.setUrl(rs.getString("url_operacion"));
            return t;

        }
    }
}
