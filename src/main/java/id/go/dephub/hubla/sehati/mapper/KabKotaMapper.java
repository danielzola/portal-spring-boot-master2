package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.KabKota;

public class KabKotaMapper implements RowMapper<KabKota>{
	@Override
    public KabKota mapRow(ResultSet rs, int rowNum) throws SQLException {
		KabKota rest = new KabKota();
        rest.setkode_kemendagri(rs.getString("kode_kemendagri"));
        rest.setnama_kabkota(rs.getString("nama_kabkota"));
        return rest;
    }
}
