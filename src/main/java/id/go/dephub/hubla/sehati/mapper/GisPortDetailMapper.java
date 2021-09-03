package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisPortDetail;

public class GisPortDetailMapper implements RowMapper<GisPortDetail>{
	@Override
    public GisPortDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisPortDetail rest = new GisPortDetail();
        rest.setnama_pelabuhan(rs.getString("nama_pelabuhan"));
        rest.setkode_pelabuhan(rs.getString("kode_pelabuhan"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setlatitude(rs.getString("latitude"));
        rest.setkabkota(rs.getString("kabkota"));
        rest.setprovinsi(rs.getString("provinsi"));
        rest.setsatker(rs.getString("satker"));
        return rest;
    }
}
