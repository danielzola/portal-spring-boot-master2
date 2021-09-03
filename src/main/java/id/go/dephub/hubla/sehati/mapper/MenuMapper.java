package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.Menu;

public class MenuMapper implements RowMapper<Menu>{
	@Override
    public Menu mapRow(ResultSet rs, int rowNum) throws SQLException {
		Menu rest = new Menu();
        rest.setmenu_id(rs.getString("menu_id"));
        rest.setmenu_parent(rs.getString("menu_parent"));
        rest.setmenu_name(rs.getString("menu_name"));
        rest.setmenu_url(rs.getString("menu_url"));
        rest.setmenu_icon(rs.getString("menu_icon"));
        rest.setmenu_status(rs.getString("menu_status"));
        rest.setmenu_level(rs.getString("menu_level"));
        rest.setmenu_counter(rs.getString("menu_counter"));
        rest.setrole_id(rs.getInt("role_id"));
        return rest;
    }
}
