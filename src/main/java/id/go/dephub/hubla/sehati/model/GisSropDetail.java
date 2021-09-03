package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class GisSropDetail implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String nama_srop, call_sign_srop, koordinat_lintang, koordinat_bujur, kelas_srop, coverage_area;
	
	public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
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
    
	public String getkoordinat_lintang() {
        return koordinat_lintang;
    }

    public void setkoordinat_lintang(String koordinat_lintang) {
        this.koordinat_lintang = koordinat_lintang;
    }
    
	public String getkoordinat_bujur() {
        return koordinat_bujur;
    }

    public void setkoordinat_bujur(String koordinat_bujur) {
        this.koordinat_bujur = koordinat_bujur;
    }
    
	public String getkelas_srop() {
        return kelas_srop;
    }

    public void setkelas_srop(String kelas_srop) {
        this.kelas_srop = kelas_srop;
    }
    
	public String getcoverage_area() {
        return coverage_area;
    }

    public void setcoverage_area(String coverage_area) {
        this.coverage_area = coverage_area;
    }
}
