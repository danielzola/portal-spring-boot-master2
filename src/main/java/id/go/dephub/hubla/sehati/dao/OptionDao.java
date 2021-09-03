package id.go.dephub.hubla.sehati.dao;

import javax.servlet.http.HttpServletRequest;

public interface OptionDao {
	String roleAction(HttpServletRequest request, String menuid, String intable);
	String getListLayanan(HttpServletRequest request);
	String getListRole(HttpServletRequest request, String key);
	String getListPriviledge(HttpServletRequest request, String role);
	String getListProvinsi(HttpServletRequest request, String key);
	String getListKabKota(HttpServletRequest request, String provinsi, String key);
	String getListEsignUser(HttpServletRequest request, String office_code, String key);
}
