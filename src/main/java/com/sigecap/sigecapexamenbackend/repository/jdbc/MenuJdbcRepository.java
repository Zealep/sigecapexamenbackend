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


    public List<MenuUsuarioDTO> getMenuUsuario(String usuario, String rol){

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("usuario",usuario);
        parameters.addValue("rol",rol);

        String sql = "SELECT m.*,u.id_usuario,r.id_rol,r.sistema,u.nombre_usuario FROM sigecapdesa.sgc_tm_menu m " +
                "inner join sgc_tm_rol_por_menu rm on m.id_menu = rm.id_menu " +
                "inner join sgc_tm_rol r on rm.id_rol = r.id_rol " +
                "inner join sgc_tm_usuario_por_rol ur on r.id_rol = ur.id_rol " +
                "inner join sgc_tz_usuario u on ur.id_usuario = u.id_usuario " +
                "where u.id_usuario = :usuario and r.id_rol = :rol";

        return jdbcTemplate.query(sql, parameters,new getMenuUsuarioMapper());
    }

    private static final class getMenuUsuarioMapper implements RowMapper<MenuUsuarioDTO> {

        @Override
        public MenuUsuarioDTO mapRow(ResultSet rs, int rowNum) throws SQLException {

            MenuUsuarioDTO t = new MenuUsuarioDTO();
            t.setIdMenu(rs.getString("id_menu"));
            t.setNombreLargo(rs.getString("no_menu"));
            t.setNombreCorto(rs.getString("no_menu_corto"));
            t.setUrl(rs.getString("url_operacion"));
            t.setOrden(rs.getInt("orden_mostrar"));
            t.setEstadoMenu(rs.getString("estado"));
            t.setIdUsuario(rs.getString("id_usuario"));
            t.setIdRol(rs.getString("id_rol"));
            t.setSistema(rs.getString("sistema"));
            t.setNombreUsuario(rs.getString("nombre_usuario"));

            return t;

        }
    }
}
