package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisVtsDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String namobj, jns_lynn, frek_krj, alat, latitude, longitude;
	
	public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }
    
	public String getnamobj() {
        return namobj;
    }

    public void setnamobj(String namobj) {
        this.namobj = namobj;
    }
    
	public String getjns_lynn() {
        return jns_lynn;
    }

    public void setjns_lynn(String jns_lynn) {
        this.jns_lynn = jns_lynn;
    }
    
	public String getfrek_krj() {
        return frek_krj;
    }

    public void setfrek_krj(String frek_krj) {
        this.frek_krj = frek_krj;
    }
    
	public String getalat() {
        return alat;
    }

    public void setalat(String alat) {
        this.alat = alat;
    }

	public String getlatitude() {
        return latitude;
    }

    public void setlatitude(String latitude) {
        this.latitude = latitude;
    }

	public String getlongitude() {
        return longitude;
    }

    public void setlongitude(String longitude) {
        this.longitude = longitude;
    }

}
