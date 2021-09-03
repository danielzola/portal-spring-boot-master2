package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisVts implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_vts;
	private String nama_vts, jns_lynn, longitude, latitude;
	
	public int getid_vts() {
        return id_vts;
    }

    public void setid_vts(int id_vts) {
        this.id_vts = id_vts;
    }
    
	public String getnama_vts() {
        return nama_vts;
    }

    public void setnama_vts(String nama_vts) {
        this.nama_vts = nama_vts;
    }
    
	public String getjns_lynn() {
        return jns_lynn;
    }

    public void setjns_lynn(String jns_lynn) {
        this.jns_lynn = jns_lynn;
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
