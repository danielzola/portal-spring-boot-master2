package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisVtsDetail;

public class GisVtsDetailMapper implements RowMapper<GisVtsDetail>{
	@Override
    public GisVtsDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisVtsDetail rest = new GisVtsDetail();
        rest.setid(rs.getInt("id"));
        rest.setnamobj(rs.getString("namobj"));
        rest.setjns_lynn(rs.getString("jns_lynn"));
        rest.setfrek_krj(rs.getString("frek_krj"));
        rest.setalat(rs.getString("alat"));
        rest.setlatitude(rs.getString("latitude"));
        rest.setlongitude(rs.getString("longitude"));
        return rest;
    }
}
