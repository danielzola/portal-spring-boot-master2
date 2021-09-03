package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisPort;

public class GisPortMapper implements RowMapper<GisPort>{
	@Override
    public GisPort mapRow(ResultSet rs, int rowNum) throws SQLException {
        GisPort rest = new GisPort();
        rest.setnama_pelabuhan(rs.getString("nama_pelabuhan"));
        rest.setkode_pelabuhan(rs.getString("kode_pelabuhan"));
        rest.setkabkota(rs.getString("kabkota"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setlatitude(rs.getString("latitude"));
        return rest;
    }
}
