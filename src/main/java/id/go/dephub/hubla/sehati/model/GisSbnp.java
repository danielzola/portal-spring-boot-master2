package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisSbnp implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_suar;
	private String nm_location, dsi_nr, longitude, latitude;
	
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
