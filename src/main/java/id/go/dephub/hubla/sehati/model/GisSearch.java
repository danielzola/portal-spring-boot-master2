package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisSearch implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id, tipe, nama, longitude, latitude;
	
	public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
    
	public String gettipe() {
        return tipe;
    }

    public void settipe(String tipe) {
        this.tipe = tipe;
    }
    
	public String getnama() {
        return nama;
    }

    public void setnama(String nama) {
        this.nama = nama;
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
