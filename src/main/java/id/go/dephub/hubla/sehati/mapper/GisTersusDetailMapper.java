package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisTersusDetail;

public class GisTersusDetailMapper implements RowMapper<GisTersusDetail>{
	@Override
    public GisTersusDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisTersusDetail rest = new GisTersusDetail();
        rest.setid(rs.getInt("id"));
        rest.setnama_tersus(rs.getString("nama_tersus"));
        rest.settersus_tuks(rs.getString("tersus_tuks"));
        rest.setbidang_usaha(rs.getString("bidang_usaha"));
        rest.setkoordinat(rs.getString("koordinat"));
        return rest;
    }
}
