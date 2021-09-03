package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.GisShip;

public class GisShipMapper implements RowMapper<GisShip>{
	@Override
    public GisShip mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisShip rest = new GisShip();
        rest.setkapal_id(rs.getString("kapal_id"));
        rest.setvessel_name(rs.getString("vessel_name"));
        rest.setmmsi(rs.getString("mmsi"));
        rest.setjenis_kapal(rs.getString("jenis_kapal"));
        rest.settipe_kapal(rs.getString("tipe_kapal"));
        rest.settrue_heading(rs.getString("true_heading"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setlatitude(rs.getString("latitude"));
        return rest;
    }
}
