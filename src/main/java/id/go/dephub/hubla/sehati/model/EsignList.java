package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class EsignList  implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id, user_id;
	private String esign_nik, esign_nama, esign_jabatan, esign_tanggal, esign_status, esign_respon;
	private String esign_email, esign_telp, esign_kota, esign_propinsi, esign_nip, esign_unit_kerja, esign_ktp, esign_rekom, esign_ttd;
	
	public String getesign_respon() {
        return esign_respon;
    }

    public void setesign_respon(String esign_respon) {
        this.esign_respon = esign_respon;
    }
    
	public String getesign_ttd() {
        return esign_ttd;
    }

    public void setesign_ttd(String esign_ttd) {
        this.esign_ttd = esign_ttd;
    }
    
	public String getesign_rekom() {
        return esign_rekom;
    }

    public void setesign_rekom(String esign_rekom) {
        this.esign_rekom = esign_rekom;
    }
    
	public String getesign_ktp() {
        return esign_ktp;
    }

    public void setesign_ktp(String esign_ktp) {
        this.esign_ktp = esign_ktp;
    }
    
	public String getesign_unit_kerja() {
        return esign_unit_kerja;
    }

    public void setesign_unit_kerja(String esign_unit_kerja) {
        this.esign_unit_kerja = esign_unit_kerja;
    }
    
	public String getesign_nip() {
        return esign_nip;
    }

    public void setesign_nip(String esign_nip) {
        this.esign_nip = esign_nip;
    }
    
	public String getesign_propinsi() {
        return esign_propinsi;
    }

    public void setesign_propinsi(String esign_propinsi) {
        this.esign_propinsi = esign_propinsi;
    }
    
	public String getesign_kota() {
        return esign_kota;
    }

    public void setesign_kota(String esign_kota) {
        this.esign_kota = esign_kota;
    }
    
	public String getesign_telp() {
        return esign_telp;
    }

    public void setesign_telp(String esign_telp) {
        this.esign_telp = esign_telp;
    }
    
	public String getesign_email() {
        return esign_email;
    }

    public void setesign_email(String esign_email) {
        this.esign_email = esign_email;
    }
    
	public int getuser_id() {
        return user_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }
    
	public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
    
	public String getesign_nik() {
        return esign_nik;
    }

    public void setesign_nik(String esign_nik) {
        this.esign_nik = esign_nik;
    }

	public String getesign_nama() {
        return esign_nama;
    }

    public void setesign_nama(String esign_nama) {
        this.esign_nama = esign_nama;
    }
    
	public String getesign_jabatan() {
        return esign_jabatan;
    }

    public void setesign_jabatan(String esign_jabatan) {
        this.esign_jabatan = esign_jabatan;
    }
    
	public String getesign_tanggal() {
        return esign_tanggal;
    }

    public void setesign_tanggal(String esign_tanggal) {
        this.esign_tanggal = esign_tanggal;
    }
        
	public String getesign_status() {
        return esign_status;
    }

    public void setesign_status(String esign_status) {
        this.esign_status = esign_status;
    }
}
