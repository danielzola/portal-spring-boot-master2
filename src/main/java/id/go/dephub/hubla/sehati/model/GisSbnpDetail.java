package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisSbnpDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_suar;
	private String nm_location, dsi_nr, longitude, latitude, description, power, jenis, high, elevation, jangkauan, pemilik, remark;
	
	public String gethigh() {
        return high;
    }

    public void sethigh(String high) {
        this.high = high;
    }
    
	public String getremark() {
        return remark;
    }

    public void setremark(String remark) {
        this.remark = remark;
    }
    
	public String getpemilik() {
        return pemilik;
    }

    public void setpemilik(String pemilik) {
        this.pemilik = pemilik;
    }
    
	public String getjangkauan() {
        return jangkauan;
    }

    public void setjangkauan(String jangkauan) {
        this.jangkauan = jangkauan;
    }
    
	public String getelevation() {
        return elevation;
    }

    public void setelevation(String elevation) {
        this.elevation = elevation;
    }
    
	public String getpower() {
        return power;
    }

    public void setpower(String power) {
        this.power = power;
    }
    
	public String getdescription() {
        return description;
    }

    public void setdescription(String description) {
        this.description = description;
    }
    
	public String getjenis() {
        return jenis;
    }

    public void setjenis(String jenis) {
        this.jenis = jenis;
    }

    public int getid_suar() {
        return id_suar;
    }

    public void setid_suar(int id_suar) {
        this.id_suar = id_suar;
    }
    
	public String getnm_location() {
        return nm_location;
    }

    public void setnm_location(String nm_location) {
        this.nm_location = nm_location;
    }
    
	public String getdsi_nr() {
        return dsi_nr;
    }

    public void setdsi_nr(String dsi_nr) {
        this.dsi_nr = dsi_nr;
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
        
}
