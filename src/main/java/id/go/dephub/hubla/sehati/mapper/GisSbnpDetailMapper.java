package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisSbnpDetail;

public class GisSbnpDetailMapper implements RowMapper<GisSbnpDetail>{
	@Override
    public GisSbnpDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisSbnpDetail rest = new GisSbnpDetail();
        rest.setid_suar(rs.getInt("id_suar"));
        rest.setnm_location(rs.getString("nm_location"));
        rest.setdsi_nr(rs.getString("dsi_nr"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setlatitude(rs.getString("latitude"));
        rest.setdescription(rs.getString("description"));
        rest.setpower(rs.getString("power"));
        rest.setjenis(rs.getString("jenis"));
        rest.sethigh(rs.getString("high"));
        rest.setelevation(rs.getString("elevation"));
        rest.setjangkauan(rs.getString("jangkauan"));
        rest.setpemilik(rs.getString("pemilik"));
        rest.setremark(rs.getString("remark"));
        return rest;
    }
}
