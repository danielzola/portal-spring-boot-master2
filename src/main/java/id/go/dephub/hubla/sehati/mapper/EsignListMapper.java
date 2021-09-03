package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.EsignList;

public class EsignListMapper implements RowMapper<EsignList>{
	@Override
    public EsignList mapRow(ResultSet rs, int rowNum) throws SQLException {
		EsignList rest = new EsignList();
        rest.setid(rs.getInt("id"));
        rest.setuser_id(rs.getInt("user_id"));
        rest.setesign_nik(rs.getString("esign_nik"));
        rest.setesign_nama(rs.getString("esign_nama"));
        rest.setesign_jabatan(rs.getString("esign_jabatan"));
        rest.setesign_tanggal(rs.getString("esign_tanggal"));
        rest.setesign_status(rs.getString("esign_status"));
        rest.setesign_email(rs.getString("esign_email"));
        rest.setesign_telp(rs.getString("esign_telp"));
        rest.setesign_kota(rs.getString("esign_kota"));
        rest.setesign_propinsi(rs.getString("esign_propinsi"));
        rest.setesign_nip(rs.getString("esign_nip"));
        rest.setesign_unit_kerja(rs.getString("esign_unit_kerja"));
        rest.setesign_ktp(rs.getString("esign_ktp"));
        rest.setesign_rekom(rs.getString("esign_rekom"));
        rest.setesign_ttd(rs.getString("esign_ttd"));
        rest.setesign_respon(rs.getString("esign_respon"));
        return rest;
    }
}
