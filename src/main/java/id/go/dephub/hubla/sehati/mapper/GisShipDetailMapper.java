package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import id.go.dephub.hubla.sehati.model.GisShipDetail;

public class GisShipDetailMapper implements RowMapper<GisShipDetail>{
	
	@Override
    public GisShipDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		GisShipDetail rest = new GisShipDetail();
        rest.setkapal_id(rs.getString("kapal_id"));
        rest.setmmsi(rs.getString("mmsi"));
        rest.setmid(rs.getString("mid"));
        rest.setvessel_name(rs.getString("vessel_name"));
        rest.setcall_sign(rs.getString("call_sign"));
        rest.setjenis_kapal(rs.getString("jenis_kapal"));
        rest.setimo_number(rs.getString("imo_number"));
        rest.setcourse_over_ground(rs.getString("course_over_ground"));
        rest.setspeed_over_ground(rs.getString("speed_over_ground"));
        rest.setdestination(rs.getString("destination"));
        rest.setreceived_time_utc_unix(rs.getString("received_time_utc_unix"));
        rest.setlatitude(rs.getString("latitude"));
        rest.setlongitude(rs.getString("longitude"));
        rest.setnama_pemilik(rs.getString("nama_pemilik"));
        rest.setalamat_pemilik(rs.getString("alamat_pemilik"));
        rest.settempat_pembuatan(rs.getString("tempat_pembuatan"));
        rest.settahun_pembuatan(rs.getString("tahun_pembuatan"));
        rest.setno_akta(rs.getString("no_akta"));
        rest.settempat_pendaftaran(rs.getString("tempat_pendaftaran"));
        rest.setno_tanda_pendaftaran(rs.getString("no_tanda_pendaftaran"));
        rest.settgl_pendaftaran(rs.getString("tgl_pendaftaran"));
        rest.setno_surat_ukur(rs.getString("no_surat_ukur"));
        rest.settgl_surat_ukur(rs.getString("tgl_surat_ukur"));
        rest.setdimensi_dalam(rs.getString("dimensi_dalam"));
        rest.setdimensi_lebar(rs.getString("dimensi_lebar"));
        rest.setdimensi_panjang(rs.getString("dimensi_panjang"));
        rest.setdimensi_isikotor(rs.getString("dimensi_isikotor"));
        rest.setdimensi_isibersih(rs.getString("dimensi_isibersih"));
        rest.setdimensi_loa(rs.getString("dimensi_loa"));
        rest.setbahan_ket(rs.getString("bahan_ket"));
        rest.setpenggerak_ket(rs.getString("penggerak_ket"));
        rest.setjml_cerobong(rs.getString("jml_cerobong"));
        rest.setjml_geladak(rs.getString("jml_geladak"));
        rest.setjml_baling(rs.getString("jml_baling"));
        rest.setmesin_merk(rs.getString("mesin_merk"));
        rest.setdaya_mesin1(rs.getString("daya_mesin1"));
        rest.setsatuan_mesin1(rs.getString("satuan_mesin1"));
                
        return rest;
    }
}
