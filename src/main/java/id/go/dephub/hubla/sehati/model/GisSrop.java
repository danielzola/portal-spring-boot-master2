package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisSrop implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id_srop;
	private String nama_srop, call_sign_srop, longitude, latitude;
	
	public int getid_srop() {
        return id_srop;
    }

    public void setid_srop(int id_srop) {
        this.id_srop = id_srop;
    }
    
	public String getnama_srop() {
        return nama_srop;
    }

    public void setnama_srop(String nama_srop) {
        this.nama_srop = nama_srop;
    }
    
	public String getcall_sign_srop() {
        return call_sign_srop;
    }

    public void setcall_sign_srop(String call_sign_srop) {
        this.call_sign_srop = call_sign_srop;
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
