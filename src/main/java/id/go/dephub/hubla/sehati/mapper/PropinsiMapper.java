package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.Propinsi;

public class PropinsiMapper implements RowMapper<Propinsi>{
	@Override
    public Propinsi mapRow(ResultSet rs, int rowNum) throws SQLException {
		Propinsi rest = new Propinsi();
        rest.setkode_kemendagri(rs.getString("kode_kemendagri"));
        rest.setnama_provinsi(rs.getString("nama_provinsi"));
        return rest;
    }
}
