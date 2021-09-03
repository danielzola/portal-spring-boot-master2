package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisShip implements Serializable{
	private static final long serialVersionUID = 1L;
	private String kapal_id, vessel_name, mmsi, jenis_kapal, tipe_kapal, true_heading, longitude, latitude;
	
	public String getkapal_id() {
        return kapal_id;
    }

    public void setkapal_id(String kapal_id) {
        this.kapal_id = kapal_id;
    }
    
	public String getvessel_name() {
        return vessel_name;
    }

    public void setvessel_name(String vessel_name) {
        this.vessel_name = vessel_name;
    }
    
	public String getmmsi() {
        return mmsi;
    }

    public void setmmsi(String mmsi) {
        this.mmsi = mmsi;
    }

	public String gettipe_kapal() {
        return tipe_kapal;
    }

    public void settipe_kapal(String tipe_kapal) {
        this.tipe_kapal = tipe_kapal;
    }
    
	public String getjenis_kapal() {
        return jenis_kapal;
    }

    public void setjenis_kapal(String jenis_kapal) {
        this.jenis_kapal = jenis_kapal;
    }
    
	public String gettrue_heading() {
        return true_heading;
    }

    public void settrue_heading(String true_heading) {
        this.true_heading = true_heading;
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