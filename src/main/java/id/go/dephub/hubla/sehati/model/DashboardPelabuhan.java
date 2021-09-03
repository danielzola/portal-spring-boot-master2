package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class DashboardPelabuhan implements Serializable{
	private static final long serialVersionUID = 1L;
	private String kode_pelabuhan, nama_pelabuhan, koordinat_lintang, koordinat_bujur, satker, kabkota, propinsi, kedalaman_min, kedalaman_max;
	private String jml_terminal, jml_kapal;
	
	public String getjml_terminal() {
        return jml_terminal;
    }

    public void setjml_terminal(String jml_terminal) {
        this.jml_terminal = jml_terminal;
    }   
    
	public String getjml_kapal() {
        return jml_kapal;
    }

    public void setjml_kapal(String jml_kapal) {
        this.jml_kapal = jml_kapal;
    }   
    
	public String getkedalaman_max() {
        return kedalaman_max;
    }

    public void setkedalaman_max(String kedalaman_max) {
        this.kedalaman_max = kedalaman_max;
    }   
    
	public String getkedalaman_min() {
        return kedalaman_min;
    }

    public void setkedalaman_min(String kedalaman_min) {
        this.kedalaman_min = kedalaman_min;
    }   
    
	public String getpropinsi() {
        return propinsi;
    }

    public void setpropinsi(String propinsi) {
        this.propinsi = propinsi;
    }   
    
	public String getkabkota() {
        return kabkota;
    }

    public void setkabkota(String kabkota) {
        this.kabkota = kabkota;
    }   
    
	public String getsatker() {
        return satker;
    }

    public void setsatker(String satker) {
        this.satker = satker;
    }   
    
	public String getkoordinat_bujur() {
        return koordinat_bujur;
    }

    public void setkoordinat_bujur(String koordinat_bujur) {
        this.koordinat_bujur = koordinat_bujur;
    }   
    
	public String getkoordinat_lintang() {
        return koordinat_lintang;
    }

    public void setkoordinat_lintang(String koordinat_lintang) {
        this.koordinat_lintang = koordinat_lintang;
    }   
    
	public String getnama_pelabuhan() {
        return nama_pelabuhan;
    }

    public void setnama_pelabuhan(String nama_pelabuhan) {
        this.nama_pelabuhan = nama_pelabuhan;
    }   
    
	public String getkode_pelabuhan() {
        return kode_pelabuhan;
    }

    public void setkode_pelabuhan(String kode_pelabuhan) {
        this.kode_pelabuhan = kode_pelabuhan;
    }   
    
    
}