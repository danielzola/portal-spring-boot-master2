package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.MenuAction;

public class MenuActionMapper implements RowMapper<MenuAction>{
	@Override
    public MenuAction mapRow(ResultSet rs, int rowNum) throws SQLException {
		MenuAction rest = new MenuAction();
        rest.setrole_id(rs.getInt("role_id"));
        rest.setmenu_id(rs.getString("menu_id"));
        rest.setaction_id(rs.getString("action_id"));
        rest.setaction_name(rs.getString("action_name"));
        rest.setaction_act(rs.getString("action_act"));
        rest.setaction_icon(rs.getString("action_icon"));
        rest.setaction_class(rs.getString("action_class"));
        rest.setaction_intable(rs.getString("action_intable"));
        rest.setaction_status(rs.getString("action_status"));
        return rest;
    }
}
