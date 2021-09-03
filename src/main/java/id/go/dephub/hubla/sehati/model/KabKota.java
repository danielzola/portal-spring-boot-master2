package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class KabKota implements Serializable{
	private static final long serialVersionUID = 1L;
	private String kode_kemendagri, nama_kabkota;
	
	public String getkode_kemendagri() {
        return kode_kemendagri;
    }

    public void setkode_kemendagri(String kode_kemendagri) {
        this.kode_kemendagri = kode_kemendagri;
    }
    
	public String getnama_kabkota() {
        return nama_kabkota;
    }

    public void setnama_kabkota(String nama_kabkota) {
        this.nama_kabkota = nama_kabkota;
    }
    
}