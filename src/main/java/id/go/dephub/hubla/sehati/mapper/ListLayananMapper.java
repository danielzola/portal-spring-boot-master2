package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.ListLayanan;

public class ListLayananMapper implements RowMapper<ListLayanan>{
	@Override
    public ListLayanan mapRow(ResultSet rs, int rowNum) throws SQLException {
		ListLayanan rest = new ListLayanan();
        rest.setid(rs.getString("id"));
        rest.setusername(rs.getString("username"));
        rest.setkey_(rs.getString("key_"));
        rest.setname_(rs.getString("name_"));
        rest.setorder_(rs.getInt("order_"));
        return rest;
    }
}
