package id.go.dephub.hubla.sehati.dao;

import javax.servlet.http.HttpServletRequest;

public interface HomeDao {
	String getInitByName(String init_name);
	String getSecure(String str);
	String getRandom(int len);
	String getNow(String str);
	String dateFormat(String str);
	String getSHA256(String message);
	String getClientIp(HttpServletRequest request);
	String baseUrl(HttpServletRequest request);
	String authorizationRequestBaseUri();
	String rootPath();
	String root();
	String hasAccess(HttpServletRequest request, String what, String actionid);
	void qrcode(String content);
	void writelog(HttpServletRequest request, String desc);
	boolean standardPassword(String str);
}
