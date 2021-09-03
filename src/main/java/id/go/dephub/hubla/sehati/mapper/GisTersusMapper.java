package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisTersus;

public class GisTersusMapper implements RowMapper<GisTersus>{
	@Override
    public GisTersus mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisTersus rest = new GisTersus();
        rest.setid_tersus(rs.getInt("id_tersus"));
        rest.setnama_tersus(rs.getString("nama_tersus"));
        rest.settersus_tuks(rs.getString("tersus_tuks"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setlatitude(rs.getString("latitude"));
        return rest;
    }
}
