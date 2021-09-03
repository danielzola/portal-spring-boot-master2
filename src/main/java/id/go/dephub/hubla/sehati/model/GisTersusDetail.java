package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisTersusDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String nama_tersus, tersus_tuks, bidang_usaha, koordinat;
	
	public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
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
    
	public String getbidang_usaha() {
        return bidang_usaha;
    }

    public void setbidang_usaha(String bidang_usaha) {
        this.bidang_usaha = bidang_usaha;
    }
    
	public String getkoordinat() {
        return koordinat;
    }

    public void setkoordinat(String koordinat) {
        this.koordinat = koordinat;
    }
}