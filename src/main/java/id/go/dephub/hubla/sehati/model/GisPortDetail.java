package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisPortDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private String nama_pelabuhan, kode_pelabuhan, longitude, latitude, kabkota, provinsi, satker;
	
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

	public String getlongitude() {
        return longitude;
    }

    public void setlongitude(String longitude) {
        this.longitude = longitude;
    }
    
	public String getlatitude() {
        return latitude;
    }

    public void setlatitude(String latitude) {
        this.latitude = latitude;
    }
    
	public String getkabkota() {
        return kabkota;
    }

    public void setkabkota(String kabkota) {
        this.kabkota = kabkota;
    }

	public String getprovinsi() {
        return provinsi;
    }

    public void setprovinsi(String provinsi) {
        this.provinsi = provinsi;
    }
    
	public String getsatker() {
        return satker;
    }

    public void setsatker(String satker) {
        this.satker = satker;
    }
    
}