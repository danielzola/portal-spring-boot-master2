package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.Role;

public class RoleMapper implements RowMapper<Role>{
	@Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role rest = new Role();
        rest.setrole_id(rs.getInt("role_id"));
        rest.setrole_name(rs.getString("role_name"));
        rest.setrole_status(rs.getString("role_status"));
        rest.setrole_desc(rs.getString("role_desc"));
        return rest;
    }
}
