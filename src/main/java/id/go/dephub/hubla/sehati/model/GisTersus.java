package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisTersus implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_tersus;
	private String nama_tersus, tersus_tuks, longitude, latitude;
	
	public int getid_tersus() {
        return id_tersus;
    }

    public void setid_tersus(int id_tersus) {
        this.id_tersus = id_tersus;
    }
    
	public String getnama_tersus() {
        return nama_tersus;
    }

    public void setnama_tersus(String nama_tersus) {
        this.nama_tersus = nama_tersus;
    }
    
	public String gettersus_tuks() {
        return tersus_tuks;
    }

    public void settersus_tuks(String tersus_tuks) {
        this.tersus_tuks = tersus_tuks;
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
