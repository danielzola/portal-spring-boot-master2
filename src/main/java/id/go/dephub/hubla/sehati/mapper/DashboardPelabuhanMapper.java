package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.DashboardPelabuhan;

public class DashboardPelabuhanMapper implements RowMapper<DashboardPelabuhan>{
	@Override
    public DashboardPelabuhan mapRow(ResultSet rs, int rowNum) throws SQLException {
		DashboardPelabuhan rest = new DashboardPelabuhan();
        rest.setkode_pelabuhan(rs.getString("kode_pelabuhan"));
        rest.setnama_pelabuhan(rs.getString("nama_pelabuhan"));
        rest.setkoordinat_lintang(rs.getString("koordinat_lintang"));
        rest.setkoordinat_bujur(rs.getString("koordinat_bujur"));
        rest.setsatker(rs.getString("satker"));
        rest.setkabkota(rs.getString("kabkota"));
        rest.setpropinsi(rs.getString("propinsi"));
        rest.setkedalaman_min(rs.getString("kedalaman_min"));
        rest.setkedalaman_max(rs.getString("kedalaman_max"));
        rest.setjml_terminal(rs.getString("jml_terminal"));
        rest.setjml_kapal(rs.getString("jml_kapal"));
        return rest;
    }
}
