package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class Propinsi implements Serializable{
	private static final long serialVersionUID = 1L;
	private String kode_kemendagri, nama_provinsi;
	
	public String getkode_kemendagri() {
        return kode_kemendagri;
    }

    public void setkode_kemendagri(String kode_kemendagri) {
        this.kode_kemendagri = kode_kemendagri;
    }
    
	public String getnama_provinsi() {
        return nama_provinsi;
    }

    public void setnama_provinsi(String nama_provinsi) {
        this.nama_provinsi = nama_provinsi;
    }
    
}