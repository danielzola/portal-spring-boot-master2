package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import id.go.dephub.hubla.sehati.model.GisSearch;

public class GisSearchMapper implements RowMapper<GisSearch>{
	@Override
    public GisSearch mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisSearch rest = new GisSearch();
        rest.setid(rs.getString("id"));
        rest.settipe(rs.getString("tipe"));
        rest.setnama(rs.getString("nama"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setlatitude(rs.getString("latitude"));
        return rest;
    }
}
