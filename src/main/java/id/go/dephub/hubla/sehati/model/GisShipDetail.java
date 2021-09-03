package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisShipDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private String kapal_id, mmsi, mid, vessel_name, call_sign, jenis_kapal, imo_number, course_over_ground, speed_over_ground;
	private String destination, received_time_utc_unix, longitude, latitude, nama_pemilik, alamat_pemilik, tempat_pembuatan;
	private String tahun_pembuatan, no_akta, tempat_pendaftaran, no_tanda_pendaftaran, tgl_pendaftaran, no_surat_ukur, tgl_surat_ukur, dimensi_dalam, dimensi_lebar, dimensi_panjang, dimensi_isikotor;
	private String dimensi_isibersih, dimensi_loa, bahan_ket, penggerak_ket, jml_cerobong, jml_geladak, jml_baling, mesin_merk, daya_mesin1, satuan_mesin1;
	
	public String getno_tanda_pendaftaran() {
        return no_tanda_pendaftaran;
    }

    public void setno_tanda_pendaftaran(String no_tanda_pendaftaran) {
        this.no_tanda_pendaftaran = (no_tanda_pendaftaran == null || no_tanda_pendaftaran.length() == 0) ? "-" : no_tanda_pendaftaran;
    }
    
	public String getdimensi_lebar() {
        return dimensi_lebar;
    }

    public void setdimensi_lebar(String dimensi_lebar) {
        this.dimensi_lebar = (dimensi_lebar == null || dimensi_lebar.length() == 0) ? "-" : dimensi_lebar;
    }
    
	public String getdimensi_panjang() {
        return dimensi_panjang;
    }

    public void setdimensi_panjang(String dimensi_panjang) {
        this.dimensi_panjang = (dimensi_panjang == null || dimensi_panjang.length() == 0) ? "-" : dimensi_panjang;
    }
    
	public String getdimensi_isikotor() {
        return dimensi_isikotor;
    }

    public void setdimensi_isikotor(String dimensi_isikotor) {
        this.dimensi_isikotor = (dimensi_isikotor == null || dimensi_isikotor.length() == 0) ? "-" : dimensi_isikotor;
    }
    
	public String getsatuan_mesin1() {
        return satuan_mesin1;
    }

    public void setsatuan_mesin1(String satuan_mesin1) {
        this.satuan_mesin1 = (satuan_mesin1 == null || satuan_mesin1.length() == 0) ? "-" : satuan_mesin1;
    }
    
	public String getdaya_mesin1() {
        return daya_mesin1;
    }

    public void setdaya_mesin1(String daya_mesin1) {
        this.daya_mesin1 = (daya_mesin1 == null || daya_mesin1.length() == 0) ? "-" : daya_mesin1;
    }
    
	public String getmesin_merk() {
        return mesin_merk;
    }

    public void setmesin_merk(String mesin_merk) {
        this.mesin_merk = (mesin_merk == null || mesin_merk.length() == 0) ? "-" : mesin_merk;
    }
    
	public String getjml_baling() {
        return jml_baling;
    }

    public void setjml_baling(String jml_baling) {
        this.jml_baling = (jml_baling == null || jml_baling.length() == 0) ? "-" : jml_baling;
    }
    
	public String getjml_geladak() {
        return jml_geladak;
    }

    public void setjml_geladak(String jml_geladak) {
        this.jml_geladak = (jml_geladak == null || jml_geladak.length() == 0) ? "-" : jml_geladak;
    }
    
	public String getjml_cerobong() {
        return jml_cerobong;
    }

    public void setjml_cerobong(String jml_cerobong) {
        this.jml_cerobong = (jml_cerobong == null || jml_cerobong.length() == 0) ? "-" : jml_cerobong;
    }
    
	public String getpenggerak_ket() {
        return penggerak_ket;
    }

    public void setpenggerak_ket(String penggerak_ket) {
        this.penggerak_ket = (penggerak_ket == null || penggerak_ket.length() == 0) ? "-" : penggerak_ket;
    }
    
	public String getbahan_ket() {
        return bahan_ket;
    }

    public void setbahan_ket(String bahan_ket) {
        this.bahan_ket = (bahan_ket == null || bahan_ket.length() == 0) ? "-" : bahan_ket;
    }
    
	public String getdimensi_loa() {
        return dimensi_loa;
    }

    public void setdimensi_loa(String dimensi_loa) {
        this.dimensi_loa = (dimensi_loa == null || dimensi_loa.length() == 0) ? "-" : dimensi_loa;
    }
    
	public String getdimensi_isibersih() {
        return dimensi_isibersih;
    }

    public void setdimensi_isibersih(String dimensi_isibersih) {
        this.dimensi_isibersih = (dimensi_isibersih == null || dimensi_isibersih.length() == 0) ? "-" : dimensi_isibersih;
    }
    
	public String getdimensi_dalam() {
        return dimensi_dalam;
    }

    public void setdimensi_dalam(String dimensi_dalam) {
        this.dimensi_dalam = (dimensi_dalam == null || dimensi_dalam.length() == 0) ? "-" : dimensi_dalam;
    }
    
	public String gettgl_surat_ukur() {
        return tgl_surat_ukur;
    }

    public void settgl_surat_ukur(String tgl_surat_ukur) {
        this.tgl_surat_ukur = (tgl_surat_ukur == null || tgl_surat_ukur.length() == 0) ? "-" : tgl_surat_ukur;
    }
    
	public String getno_surat_ukur() {
        return no_surat_ukur;
    }

    public void setno_surat_ukur(String no_surat_ukur) {
        this.no_surat_ukur = (no_surat_ukur == null || no_surat_ukur.length() == 0) ? "-" : no_surat_ukur;
    }
    
	public String gettgl_pendaftaran() {
        return tgl_pendaftaran;
    }

    public void settgl_pendaftaran(String tgl_pendaftaran) {
        this.tgl_pendaftaran = (tgl_pendaftaran == null || tgl_pendaftaran.length() == 0) ? "-" : tgl_pendaftaran;
    }
    
	public String gettempat_pendaftaran() {
        return tempat_pendaftaran;
    }

    public void settempat_pendaftaran(String tempat_pendaftaran) {
        this.tempat_pendaftaran = (tempat_pendaftaran == null || tempat_pendaftaran.length() == 0) ? "-" : tempat_pendaftaran;
    }
    
	public String getno_akta() {
        return no_akta;
    }

    public void setno_akta(String no_akta) {
        this.no_akta = (no_akta == null || no_akta.length() == 0) ? "-" : no_akta;
    }
    
	public String gettahun_pembuatan() {
        return tahun_pembuatan;
    }

    public void settahun_pembuatan(String tahun_pembuatan) {
        this.tahun_pembuatan = (tahun_pembuatan == null || tahun_pembuatan.length() == 0) ? "-" : tahun_pembuatan;
    }
    
	public String gettempat_pembuatan() {
        return tempat_pembuatan;
    }

    public void settempat_pembuatan(String tempat_pembuatan) {
        this.tempat_pembuatan = (tempat_pembuatan == null || tempat_pembuatan.length() == 0) ? "-" : tempat_pembuatan;
    }

	public String getkapal_id() {
        return kapal_id;
    }

    public void setkapal_id(String kapal_id) {
        this.kapal_id = kapal_id;
    }
        
	public String getmmsi() {
        return mmsi;
    }

    public void setmmsi(String mmsi) {
        this.mmsi = (mmsi == null || mmsi.length() == 0) ? "-" : mmsi;
    }
    
	public String getmid() {
        return mid;
    }

    public void setmid(String mid) {
        this.mid = (mid == null || mid.length() == 0) ? "-" : mid;
    }
   
	public String getvessel_name() {
        return vessel_name;
    }

    public void setvessel_name(String vessel_name) {
        this.vessel_name = (vessel_name == null || vessel_name.length() == 0) ? "-" : vessel_name;
    }
    
	public String getcall_sign() {
        return call_sign;
    }

    public void setcall_sign(String call_sign) {
        this.call_sign = (call_sign == null || call_sign.length() == 0) ? "-" : call_sign;
    }
    
	public String getjenis_kapal() {
        return jenis_kapal;
    }

    public void setjenis_kapal(String jenis_kapal) {
        this.jenis_kapal = (jenis_kapal == null || jenis_kapal.length() == 0) ? "-" : jenis_kapal;
    }
    
	public String getimo_number() {
        return imo_number;
    }

    public void setimo_number(String imo_number) {
        this.imo_number = (imo_number == null || imo_number.length() == 0) ? "-" : imo_number;
    }
    
	public String getcourse_over_ground() {
        return course_over_ground;
    }

    public void setcourse_over_ground(String course_over_ground) {
        this.course_over_ground = (course_over_ground == null || course_over_ground.length() == 0) ? "-" : course_over_ground;
    }
    
	public String getspeed_over_ground() {
        return speed_over_ground;
    }

    public void setspeed_over_ground(String speed_over_ground) {
        this.speed_over_ground = (speed_over_ground == null || speed_over_ground.length() == 0) ? "-" : speed_over_ground;
    }
    
	public String getdestination() {
        return destination;
    }

    public void setdestination(String destination) {
        this.destination = (destination == null || destination.length() == 0) ? "-" : destination;
    }    
    
	public String getreceived_time_utc_unix() {
        return received_time_utc_unix;
    }

    public void setreceived_time_utc_unix(String received_time_utc_unix) {
        this.received_time_utc_unix = (received_time_utc_unix == null || received_time_utc_unix.length() == 0) ? "-" : received_time_utc_unix;
    }    
    
	public String getlatitude() {
        return latitude;
    }

    public void setlatitude(String latitude) {
        this.latitude = (latitude == null || latitude.length() == 0) ? "-" : latitude;
    }    

	public String getlongitude() {
        return longitude;
    }

    public void setlongitude(String longitude) {
        this.longitude = (longitude == null || longitude.length() == 0) ? "-" : longitude;
    }    
    
	public String getnama_pemilik() {
        return nama_pemilik;
    }

    public void setnama_pemilik(String nama_pemilik) {
        this.nama_pemilik = (nama_pemilik == null || nama_pemilik.length() == 0) ? "-" : nama_pemilik;
    }    

	public String getalamat_pemilik() {
        return alamat_pemilik;
    }

    public void setalamat_pemilik(String alamat_pemilik) {
        this.alamat_pemilik = (alamat_pemilik == null || alamat_pemilik.length() == 0) ? "-" : alamat_pemilik;
    }    
    
    
}