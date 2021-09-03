package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisVts;

public class GisVtsMapper implements RowMapper<GisVts>{
	@Override
    public GisVts mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisVts rest = new GisVts();
        rest.setid_vts(rs.getInt("id_vts"));
        rest.setnama_vts(rs.getString("nama_vts"));
        rest.setjns_lynn(rs.getString("jns_lynn"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setlatitude(rs.getString("latitude"));
        return rest;
    }
}
