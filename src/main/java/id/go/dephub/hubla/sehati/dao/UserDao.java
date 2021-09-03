package id.go.dephub.hubla.sehati.dao;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import id.go.dephub.hubla.sehati.model.Role;
import id.go.dephub.hubla.sehati.model.User;

public interface UserDao {
	List<User> getUser(String key);
	void setLogin(String user_name, HttpServletRequest request, HttpSession session);
	void setLogout(HttpServletRequest request, HttpSession session);
	String getMenu(HttpServletRequest request);
	List<User> getPegawaiList();	
	public String savePegawai(HttpServletRequest request);
	public String deletePegawai(HttpServletRequest request);
	List<User> getPerusahaanList();	
	List<Role> getRoleList();	
	List<Role> getRole(String key);
	public String saveRole(HttpServletRequest request);
	public String deleteRole(HttpServletRequest request);
	public String savePassword(HttpServletRequest request);
	public String saveProfile(HttpServletRequest request);
}
