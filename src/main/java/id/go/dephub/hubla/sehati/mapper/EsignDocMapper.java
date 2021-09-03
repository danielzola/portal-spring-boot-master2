package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.EsignDoc;

public class EsignDocMapper implements RowMapper<EsignDoc>{
	@Override
    public EsignDoc mapRow(ResultSet rs, int rowNum) throws SQLException {
		EsignDoc rest = new EsignDoc();
        rest.setdoc_id(rs.getInt("doc_id"));
        rest.setdoc_by(rs.getInt("doc_by"));
        rest.setuser_create(rs.getInt("user_create"));
        rest.setuser_update(rs.getInt("user_update"));
        rest.setdoc_code(rs.getString("doc_code"));
        rest.setdoc_desc(rs.getString("doc_desc"));
        rest.setdoc_type(rs.getString("doc_type"));
        rest.setdoc_number(rs.getString("doc_number"));
        rest.setdoc_date_start(rs.getString("doc_date_start"));
        rest.setdoc_date_end(rs.getString("doc_date_end"));
        rest.setdoc_file(rs.getString("doc_file"));
        rest.setdoc_file_path(rs.getString("doc_file_path"));
        rest.setdoc_file_sign(rs.getString("doc_file_sign"));
        rest.setdoc_file_sign_path(rs.getString("doc_file_sign_path"));
        rest.setdoc_file_page(rs.getString("doc_file_page"));
        rest.setdoc_file_sign_id(rs.getString("doc_file_sign_id"));
        rest.setdoc_qr_page(rs.getString("doc_qr_page"));
        rest.setdoc_qr_position(rs.getString("doc_qr_position"));
        rest.setdoc_qr_x(rs.getString("doc_qr_x"));
        rest.setdoc_qr_y(rs.getString("doc_qr_y"));
        rest.setdoc_qr_size(rs.getString("doc_qr_size"));
        rest.setdoc_sign_use(rs.getString("doc_sign_use"));
        rest.setdoc_sign_page(rs.getString("doc_sign_page"));
        rest.setdoc_sign_size(rs.getString("doc_sign_size"));
        rest.setdoc_sign_x(rs.getString("doc_sign_x"));
        rest.setdoc_sign_y(rs.getString("doc_sign_y"));
        rest.setdoc_status(rs.getString("doc_status"));
        rest.setdate_create(rs.getString("date_create"));
        rest.setlast_update(rs.getString("last_update"));
        return rest;
    }
}
