package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class DashboardKeyValue implements Serializable{
	private static final long serialVersionUID = 1L;
	private String kode, keterangan, nilai;
	
	public String getkode() {
        return kode;
    }

    public void setkode(String kode) {
        this.kode = kode;
    }
    
	public String getketerangan() {
        return keterangan;
    }

    public void setketerangan(String keterangan) {
        this.keterangan = keterangan;
    }
    
	public String getnilai() {
        return nilai;
    }

    public void setnilai(String nilai) {
        this.nilai = nilai;
    }
    
}