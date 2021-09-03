package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisSbnp;

public class GisSbnpMapper implements RowMapper<GisSbnp>{
	@Override
    public GisSbnp mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisSbnp rest = new GisSbnp();
        rest.setid_suar(rs.getInt("id_suar"));
        rest.setnm_location(rs.getString("nm_location"));
        rest.setdsi_nr(rs.getString("dsi_nr"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setlatitude(rs.getString("latitude"));
        return rest;
    }
}
