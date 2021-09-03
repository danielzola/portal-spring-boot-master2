package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.DashboardKeyValue;

public class DashboardKeyValueMapper implements RowMapper<DashboardKeyValue>{
	@Override
    public DashboardKeyValue mapRow(ResultSet rs, int rowNum) throws SQLException {
		DashboardKeyValue rest = new DashboardKeyValue();
        rest.setkode(rs.getString("kode"));
        rest.setketerangan(rs.getString("keterangan"));
        rest.setnilai(rs.getString("nilai"));
        return rest;
    }
}
