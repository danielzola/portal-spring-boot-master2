package id.go.dephub.hubla.sehati.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import id.go.dephub.hubla.sehati.model.DashboardKeyValue;
import id.go.dephub.hubla.sehati.model.DashboardPelabuhan;

public interface DashboardDao {
	String getDsbPelabuhanJml(HttpServletRequest request);
	List<DashboardKeyValue> getDsbPelabuhanSatker(HttpServletRequest request);
	List<DashboardKeyValue> getDsbPelabuhanPropinsi(HttpServletRequest request);
	List<DashboardPelabuhan> getDsbPelabuhanTable(HttpServletRequest request);	
	List<DashboardPelabuhan> getDsbPelabuhanKapal(HttpServletRequest request);	
	String getDsbKapalJml(HttpServletRequest request);
	List<DashboardKeyValue> getDsbKapalBendera(HttpServletRequest request);
	List<DashboardKeyValue> getDsbKapalTahun(HttpServletRequest request);
	List<DashboardKeyValue> getDsbKapalJenis(HttpServletRequest request);
	List<DashboardKeyValue> getDsbKapalKategori(HttpServletRequest request);
	String getDsbLayananCounter(HttpServletRequest request);
	void snycTersus();
	String getDsbTersusJml(HttpServletRequest request);
	List<DashboardKeyValue> getDsbTersusSatker(HttpServletRequest request);
	List<DashboardKeyValue> getDsbTersusPropinsi(HttpServletRequest request);

}