package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisSrop;

public class GisSropMapper implements RowMapper<GisSrop>{
	@Override
    public GisSrop mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisSrop rest = new GisSrop();
        rest.setid_srop(rs.getInt("id_srop"));
        rest.setnama_srop(rs.getString("nama_srop"));
        rest.setcall_sign_srop(rs.getString("call_sign_srop"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setlatitude(rs.getString("latitude"));
        return rest;
    }
}
