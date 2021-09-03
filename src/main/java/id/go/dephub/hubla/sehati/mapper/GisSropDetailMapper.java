package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisSropDetail;

public class GisSropDetailMapper implements RowMapper<GisSropDetail>{
	@Override
    public GisSropDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisSropDetail rest = new GisSropDetail();
        rest.setid(rs.getInt("id"));
        rest.setnama_srop(rs.getString("nama_srop"));
        rest.setcall_sign_srop(rs.getString("call_sign_srop"));
        rest.setkoordinat_lintang(rs.getString("koordinat_lintang"));
        rest.setkoordinat_bujur(rs.getString("koordinat_bujur"));
        rest.setkelas_srop(rs.getString("kelas_srop"));
        rest.setcoverage_area(rs.getString("coverage_area"));
        return rest;
    }
}
