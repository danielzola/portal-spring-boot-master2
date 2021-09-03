package id.go.dephub.hubla.sehati.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import id.go.dephub.hubla.sehati.mapper.MenuMapper;
import id.go.dephub.hubla.sehati.mapper.RoleMapper;
import id.go.dephub.hubla.sehati.mapper.UserMapper;
import id.go.dephub.hubla.sehati.model.Menu;
import id.go.dephub.hubla.sehati.model.Role;
import id.go.dephub.hubla.sehati.model.User;

@Transactional
@Repository
public class UserDaoImp implements UserDao{
	@Autowired
	@Qualifier("portalJdbcTemplate")
	private JdbcTemplate portalTemplate;
	
	@Autowired
	HomeDao home;	

	@Override
	public List<User> getUser(String key) {
		String sql  = "select a.user_id, a.user_name, a.user_type,  "
					+ "a.user_fullname, a.user_nik, a.user_hp, a.user_email, a.user_photo,  "
					+ "to_char(a.user_last_login,'DD-MM-YYYY HH24:MI:SS') user_last_login, a.user_status, "
					+ "b.emp_name, b.emp_number, to_char(b.emp_birth_date,'DD-MM-YYYY') emp_birth_date,  "
					+ "b.emp_gender, b.unit_code, b.unit_name, b.office_code, b.office_name, to_char(b.group_date,'DD-MM-YYYY') group_date,  "
					+ "b.group_name, b.grade_name, b.position_name, b.emp_hp, "
					+ "string_agg(DISTINCT d.role_id::varchar, ';') role_id, string_agg(DISTINCT d.role_name, ';') role_name, "
					+ "e.company_id, e.company_nib, e.company_name, e.company_address, e.company_phone, e.company_email, e.company_taxno, e.company_type, f.reff_name company_type_name "
					+ "from portal.tm_users a  "
					+ "left join portal.tm_employee b on a.user_id=b.user_id  "
					+ "left join portal.td_users_roles c on a.user_id = c.user_id  "
					+ "left join portal.tm_roles d on c.role_id = d.role_id "
					+ "left join portal.tm_company e on a.user_id=e.user_id "
					+ "left join portal.tb_reff f on e.company_type=f.reff_code and f.reff_group='0009' "
					+ "where a.user_name=? "
					+ "group by a.user_id, a.user_name, a.user_type,  "
					+ "a.user_fullname, a.user_nik, a.user_hp, a.user_email, a.user_photo, to_char(a.user_last_login,'DD-MM-YYYY HH24:MI:SS'), a.user_status, "
					+ "b.emp_name, b.emp_number, to_char(b.emp_birth_date,'DD-MM-YYYY'), b.emp_gender, b.unit_code, b.unit_name,  "
					+ "b.office_code, b.office_name, to_char(b.group_date,'DD-MM-YYYY'), b.group_name, b.grade_name, b.position_name, b.emp_hp, "
					+ "e.company_id, e.company_nib, e.company_name, e.company_address, e.company_phone, e.company_email, e.company_taxno, e.company_type, f.reff_name";
        List<User> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, key.toString());
			}
        }, new UserMapper());
        return rest;
	}

	@Override
	public void setLogin(String user_name, HttpServletRequest request, HttpSession session) {
		
        String sqlUserID = "select a.user_id "
			   	   		   + "from portal.tm_users a where a.user_name=?";
		@SuppressWarnings("deprecation")
		final int user_id = portalTemplate.queryForObject(sqlUserID, new Object[]{user_name}, Integer.class);

        String sqlLog  = "INSERT INTO portal.tx_log "
			           + "(user_id, log_desc, log_date, log_ip) "
			           + "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta', ?)";
		portalTemplate.update(sqlLog, new PreparedStatementSetter() {
							              public void setValues(PreparedStatement ps) throws SQLException {
							            	  ps.setInt(1, user_id);
							            	  ps.setString(2, "Login Aplikasi");
							            	  ps.setString(3, home.getClientIp(request));
							              }
						          });
		
		session.setAttribute("sessionLoginTime", home.getNow("yyyy-MM-dd HH:mm:ss"));
		session.setAttribute("sessionUserName", user_name);
		session.setAttribute("sessionUserId", user_id);
	}

	@Override
	public void setLogout(HttpServletRequest request, HttpSession session) {
		String user_name = (String)request.getSession().getAttribute("sessionUserName");
		if(user_name!=null && user_name.length()>0) {
	        String sqlUserID = "select a.user_id "
				   	   		 + "from portal.tm_users a where a.user_name=?";
			@SuppressWarnings("deprecation")
			final int user_id = portalTemplate.queryForObject(sqlUserID, new Object[]{user_name}, Integer.class);
			
			String sqlLog  = "INSERT INTO portal.tx_log "
				           + "(user_id, log_desc, log_date, log_ip) "
				           + "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta', ?)";
			portalTemplate.update(sqlLog, new PreparedStatementSetter() {
								              public void setValues(PreparedStatement ps) throws SQLException {
								            	  ps.setInt(1, user_id);
								            	  ps.setString(2, "Logout Aplikasi");
								            	  ps.setString(3, home.getClientIp(request));
								              }
							          });
			
		}
		session.invalidate();
	}

	@Override
	public String getMenu(HttpServletRequest request) {
		final int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
		String sql  = "select distinct c.menu_id, c.menu_parent, c.menu_name, c.menu_url, c.menu_icon, c.menu_status, c.menu_level, c.menu_counter, 0 role_id "
				+ "from portal.td_users_roles a "
				+ "left join portal.ts_role_menu b on a.role_id =b.role_id "
				+ "left join portal.tm_menu c on b.menu_id = c.menu_id "
				+ "where a.user_id = ? and c.menu_level = '0' "
				+ "order by c.menu_id";
	    List<Menu> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, user_id);
			}
	    }, new MenuMapper());

        String menu = "<nav>"
		  			+ "		<div class=\"user logged-in\">"
		  			+ "			<input type=\"text\" style=\"color:#000 !important;\" class=\"form-control\" placeholder=\"Cari menu...\">"
		  			+ "		</div>"
		  			+ "		<ul class=\"console-menu\" id=\"sidemenucontent\">"
		  			+ "			<li class=\"sehatimenu\"><a href=\"/\"><i class=\"fas fa-home\"></i><span> Beranda</span></a></li>";
        for (int i = 0; i < rest.size(); i++) {
     	   	   menu = menu
     	   			+ "			<li class=\"sidebar-header sehatimenu\"><span>"+rest.get(i).getmenu_name()+"</span></li>"
     	   			+ "		   "+getSubMenu(request, rest.get(i).getmenu_id(), rest.get(i).getmenu_level());
		}
		 	   menu = menu
		 			+ "		</ul>"
		 			+ "</nav>";
		return menu;
	}	
	
	public String getSubMenu(HttpServletRequest request, String parent, String level) {		
		final int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
		String newlevel = String.valueOf(Integer.valueOf(level)+1);
		String sql  = "select distinct c.menu_id, c.menu_parent, c.menu_name, c.menu_url, c.menu_icon, c.menu_status, c.menu_level, c.menu_counter, 0 role_id "
				+ "from portal.td_users_roles a "
				+ "left join portal.ts_role_menu b on a.role_id =b.role_id "
				+ "left join portal.tm_menu c on b.menu_id = c.menu_id "
				+ "where a.user_id = ? and c.menu_level = ? and c.menu_parent = ? "
				+ "order by c.menu_id";
	    List<Menu> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, user_id);
				ps.setString(2, newlevel);
				ps.setString(3, parent);
			}
	    }, new MenuMapper());
		
	    String menu = "";
        for (int i = 0; i < rest.size(); i++) {
        	String icon = "";
    		if(rest.get(i).getmenu_icon()!=null && rest.get(i).getmenu_icon().length()>0) {
    			icon = " <i class=\""+rest.get(i).getmenu_icon()+"\"></i> ";
    		}else {
    			icon = " &nbsp; &nbsp; &nbsp;";
    		}
        	if(rest.get(i).getmenu_url()!=null && rest.get(i).getmenu_url().length()>0) {
        		menu =  menu+
        				"<li class=\"sehatimenu\"><a href=\""+rest.get(i).getmenu_url()+"\" title=\""+rest.get(i).getmenu_name()+"\"> "+icon+" <span>"+rest.get(i).getmenu_name()+"</span></a></li>";        		
        	}else {
        		menu = menu
        			 +"<li class=\"menu-item-has-children sehatimenu\">"
        			 + "	<a href=\"#\" title=\""+rest.get(i).getmenu_name()+"\"> "+icon+" <span>"+rest.get(i).getmenu_name()+"</span></a>"
        			 + "	<ul>";
        		menu = menu+getSubMenu(request, rest.get(i).getmenu_id(), rest.get(i).getmenu_level());
        		menu = menu
        			 + "	</ul>"
        			 + "</li>";
        	}
		}
		return menu;
	}

	@Override
	public List<User> getPegawaiList() {
		String sql  = "select a.user_id, a.user_name, a.user_type,  "
					+ "a.user_fullname, a.user_nik, a.user_hp, a.user_email, a.user_photo,  "
					+ "to_char(a.user_last_login,'DD-MM-YYYY HH24:MI:SS') user_last_login, a.user_status, "
					+ "b.emp_name, b.emp_number, to_char(b.emp_birth_date,'DD-MM-YYYY') emp_birth_date,  "
					+ "b.emp_gender, b.unit_code, b.unit_name, b.office_code, b.office_name, to_char(b.group_date,'DD-MM-YYYY') group_date,  "
					+ "b.group_name, b.grade_name, b.position_name, b.emp_hp, "
					+ "string_agg(DISTINCT d.role_id::varchar, ';') role_id, string_agg(DISTINCT d.role_name, ';') role_name, "
					+ "e.company_id, e.company_nib, e.company_name, e.company_address, e.company_phone, e.company_email, e.company_taxno, e.company_type, f.reff_name company_type_name "
					+ "from portal.tm_users a  "
					+ "left join portal.tm_employee b on a.user_id=b.user_id  "
					+ "left join portal.td_users_roles c on a.user_id = c.user_id  "
					+ "left join portal.tm_roles d on c.role_id = d.role_id "
					+ "left join portal.tm_company e on a.user_id=e.user_id "
					+ "left join portal.tb_reff f on e.company_type=f.reff_code and f.reff_group='0009' "
					+ "where a.user_type='1' and a.user_status<>'9' "
					+ "group by a.user_id, a.user_name, a.user_type,  "
					+ "a.user_fullname, a.user_nik, a.user_hp, a.user_email, a.user_photo, to_char(a.user_last_login,'DD-MM-YYYY HH24:MI:SS'), a.user_status, "
					+ "b.emp_name, b.emp_number, to_char(b.emp_birth_date,'DD-MM-YYYY'), b.emp_gender, b.unit_code, b.unit_name,  "
					+ "b.office_code, b.office_name, to_char(b.group_date,'DD-MM-YYYY'), b.group_name, b.grade_name, b.position_name, b.emp_hp, "
					+ "e.company_id, e.company_nib, e.company_name, e.company_address, e.company_phone, e.company_email, e.company_taxno, e.company_type, f.reff_name";
		List<User> rest = portalTemplate.query(sql, new UserMapper());
	    return rest;
	}
	
	@Override
	public String savePegawai(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";
		if(request.getParameter("user_password")!=null && request.getParameter("user_password").length()>0) {
			if(!request.getParameter("user_password").equals(request.getParameter("user_password2"))) {
				code    = "02";
				status  = "failed";
				message = "Konfirmasi password tidak sesuai";
			}else {
		        String sqlUser = "UPDATE portal.tm_users SET user_nik=?, user_hp=?, user_password=?, user_status=?, user_update=?, last_update=NOW() AT TIME ZONE 'Asia/Jakarta' WHERE user_name=?";
			    int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setString(1, request.getParameter("user_nik"));
									            	  ps.setString(2, request.getParameter("user_hp"));
									            	  ps.setString(3, home.getSecure(request.getParameter("user_password")));
									            	  ps.setString(4, request.getParameter("user_status"));
									            	  ps.setInt(5, (Integer)request.getSession().getAttribute("sessionUserId"));
									            	  ps.setString(6, request.getParameter("emp_number"));
									              }
								    });
			
			     if(rowInsertUser>0) {
			        	String [] role_id = request.getParameterValues("role_id");
				        String sqlResetRole = "delete from portal.td_users_roles where user_id=?";
					    int rowResetRole = portalTemplate.update(sqlResetRole, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setInt(1, Integer.valueOf(request.getParameter("user_id")));
									              }
								    		});
					    
			        	if(rowResetRole>0) {
			        		for(int i=0; i<role_id.length; i++) {
						        String sqlRole = "insert into portal.td_users_roles(user_id, role_id) values(?, "+role_id[i]+")";
							    portalTemplate.update(sqlRole, new PreparedStatementSetter() {
						              public void setValues(PreparedStatement ps) throws SQLException {
						            	  ps.setInt(1, Integer.valueOf(request.getParameter("user_id")));
						              }
							    });			        			
			        		}
			        	}
				 		RestTemplate restTemplate = new RestTemplate();
						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
						headers.add("Authorization", "Basic "+home.getInitByName("api_basic_auth"));
						MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
						map.add("grant_type","client_credentials");
						HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
						ResponseEntity<String> responseToken = restTemplate.exchange(home.baseUrl(request)+"/apis/token", HttpMethod.POST, entity, String.class);
						String jsonToken		 = responseToken.getBody();
						JSONObject dataToken 	 = new JSONObject(jsonToken);
						String access_token		 = dataToken.getString("access_token");
						
						//Reset First
						RestTemplate apiResetPassword = new RestTemplate();
						HttpHeaders headerResetPassword = new HttpHeaders();
						headerResetPassword.setContentType(MediaType.APPLICATION_JSON);
						headerResetPassword.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
						headerResetPassword.add("Authorization", "Bearer "+access_token);			
						Map<String, Object> mapResetPassword = new HashMap<>();
						mapResetPassword.put("username", request.getParameter("emp_number"));					
						
						HttpEntity<Map<String, Object>> entityResetPassword = new HttpEntity<>(mapResetPassword, headerResetPassword);
						ResponseEntity<String> responseResetPassword = apiResetPassword.postForEntity(home.baseUrl(request)+"/apis/sso/resetPassword", entityResetPassword, String.class);
						String jsonResetPassword 		 = responseResetPassword.getBody();
						JSONObject objResetPassword 	 = new JSONObject(jsonResetPassword);
						if(objResetPassword.getString("status").equals("1")) {
							JSONObject objdata = objResetPassword.getJSONObject("data");
							String new_password = objdata.getString("new_password");
							
							//Change Then
							RestTemplate apiChangePassword = new RestTemplate();
							HttpHeaders headerChangePassword = new HttpHeaders();
							headerChangePassword.setContentType(MediaType.APPLICATION_JSON);
							headerChangePassword.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
							headerChangePassword.add("Authorization", "Bearer "+access_token);			
							Map<String, Object> mapChangePassword = new HashMap<>();
							mapChangePassword.put("username", request.getParameter("emp_number"));					
							mapChangePassword.put("password", request.getParameter("user_password"));					
							mapChangePassword.put("old_password", new_password);
											    			
							HttpEntity<Map<String, Object>> entityChangePassword = new HttpEntity<>(mapChangePassword, headerChangePassword);
							ResponseEntity<String> responseChangePassword = apiChangePassword.postForEntity(home.baseUrl(request)+"/apis/sso/changePassword", entityChangePassword, String.class);
							String jsonChangePassword 		 = responseChangePassword.getBody();
							JSONObject objChangePassword 	 = new JSONObject(jsonChangePassword);
							if(objChangePassword.getString("status").equals("1")) {
								code    = "01";
								status  = "success";
								message = "Data pegawai berhasil disimpan";	
								home.writelog(request, "Edit data pegawai ("+request.getParameter("emp_name")+")");
							}
						}
			     }							
			}
		}else {
	        String sqlUser = "UPDATE portal.tm_users SET user_nik=?, user_hp=?, user_status=?, user_update=?, last_update=NOW() AT TIME ZONE 'Asia/Jakarta' WHERE user_name=?";
		    int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
								              public void setValues(PreparedStatement ps) throws SQLException {
								            	  ps.setString(1, request.getParameter("user_nik"));
								            	  ps.setString(2, request.getParameter("user_hp"));
								            	  ps.setString(3, request.getParameter("user_status"));
								            	  ps.setInt(4, (Integer)request.getSession().getAttribute("sessionUserId"));
								            	  ps.setString(5, request.getParameter("emp_number"));
								              }
							    });
		
		     if(rowInsertUser>0) {
		        	String [] role_id = request.getParameterValues("role_id");
			        String sqlResetRole = "delete from portal.td_users_roles where user_id=?";
				    int rowResetRole = portalTemplate.update(sqlResetRole, new PreparedStatementSetter() {
								              public void setValues(PreparedStatement ps) throws SQLException {
								            	  ps.setInt(1, Integer.valueOf(request.getParameter("user_id")));
								              }
							    		});
				    
		        	if(rowResetRole>0) {
		        		for(int i=0; i<role_id.length; i++) {
					        String sqlRole = "insert into portal.td_users_roles(user_id, role_id) values(?, "+role_id[i]+")";
						    portalTemplate.update(sqlRole, new PreparedStatementSetter() {
					              public void setValues(PreparedStatement ps) throws SQLException {
					            	  ps.setInt(1, Integer.valueOf(request.getParameter("user_id")));
					              }
						    });			        			
		        		}
		        	}
		        	
					code    = "01";
					status  = "success";
					message = "Data pegawai berhasil disimpan";		    	 
					home.writelog(request, "Edit data pegawai ("+request.getParameter("emp_name")+")");
		     }			
		}
		return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";
	}
	
	@Override
	public String deletePegawai(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";
		
	    String sql        = "select a.user_fullname "
 		 		 		  + "from portal.tm_users a where a.user_name = ?";
		@SuppressWarnings("deprecation")
		String user_fullname  = portalTemplate.queryForObject(sql, new Object[]{request.getParameter("id")}, String.class);

        String sqlUser = "UPDATE portal.tm_users SET user_status='9', user_update=?, last_update=NOW() AT TIME ZONE 'Asia/Jakarta' WHERE user_name=?";
	    int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
							              public void setValues(PreparedStatement ps) throws SQLException {
							            	  ps.setInt(1, (Integer)request.getSession().getAttribute("sessionUserId"));
							            	  ps.setString(2, request.getParameter("id"));
							              }
						    });
	
	     if(rowInsertUser>0) {
				code    = "01";
				status  = "success";
				message = "Data pegawai berhasil dihapus";		    	 
				home.writelog(request, "Hapus data pegawai ("+user_fullname+")");
	     }			
		 return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";
	}	

	@Override
	public List<User> getPerusahaanList() {
		String sql  = "select a.user_id, a.user_name, a.user_type,  "
					+ "a.user_fullname, a.user_nik, a.user_hp, a.user_email, a.user_photo,  "
					+ "to_char(a.user_last_login,'DD-MM-YYYY HH24:MI:SS') user_last_login, a.user_status, "
					+ "b.emp_name, b.emp_number, to_char(b.emp_birth_date,'DD-MM-YYYY') emp_birth_date,  "
					+ "b.emp_gender, b.unit_code, b.unit_name, b.office_code, b.office_name, to_char(b.group_date,'DD-MM-YYYY') group_date,  "
					+ "b.group_name, b.grade_name, b.position_name, b.emp_hp, "
					+ "string_agg(DISTINCT d.role_id::varchar, ';') role_id, string_agg(DISTINCT d.role_name, ';') role_name, "
					+ "e.company_id, e.company_nib, e.company_name, e.company_address, e.company_phone, e.company_email, e.company_taxno, e.company_type, f.reff_name company_type_name "
					+ "from portal.tm_users a  "
					+ "left join portal.tm_employee b on a.user_id=b.user_id  "
					+ "left join portal.td_users_roles c on a.user_id = c.user_id  "
					+ "left join portal.tm_roles d on c.role_id = d.role_id "
					+ "left join portal.tm_company e on a.user_id=e.user_id "
					+ "left join portal.tb_reff f on e.company_type=f.reff_code and f.reff_group='0009' "
					+ "where a.user_type='2' "
					+ "group by a.user_id, a.user_name, a.user_type,  "
					+ "a.user_fullname, a.user_nik, a.user_hp, a.user_email, a.user_photo, to_char(a.user_last_login,'DD-MM-YYYY HH24:MI:SS'), a.user_status, "
					+ "b.emp_name, b.emp_number, to_char(b.emp_birth_date,'DD-MM-YYYY'), b.emp_gender, b.unit_code, b.unit_name,  "
					+ "b.office_code, b.office_name, to_char(b.group_date,'DD-MM-YYYY'), b.group_name, b.grade_name, b.position_name, b.emp_hp, "
					+ "e.company_id, e.company_nib, e.company_name, e.company_address, e.company_phone, e.company_email, e.company_taxno, e.company_type, f.reff_name";
		List<User> rest = portalTemplate.query(sql, new UserMapper());
	    return rest;
	}
	
	@Override
	public List<Role> getRoleList() {
		String sql  = "select role_id, role_name, role_status, role_desc from portal.tm_roles where role_status<>'9'";
		List<Role> rest = portalTemplate.query(sql, new RoleMapper());
	    return rest;
	}

	@Override
	public List<Role> getRole(String key) {
		String sql  = "select role_id, role_name, role_status, role_desc from portal.tm_roles where role_id=?";
		List<Role> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.valueOf(key));
			}
        }, new RoleMapper());
	    return rest;
	}

	@Override
	public String saveRole(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";
		if(request.getParameter("role_id")==null || request.getParameter("role_id").length()<1) {
			
		    String sqlRoleId  = "select coalesce(max(a.role_id),0) role_id "
			      		 		 + "from portal.tm_roles a";
			int getrole_id 	 = portalTemplate.queryForObject(sqlRoleId, Integer.class);
			 	getrole_id    = getrole_id+1;
			final int role_id = getrole_id;
			
	        String sqlRole = "insert into portal.tm_roles(role_id, role_name, role_status, role_desc) values(?, ?, ?, ?)";
	        int InsertRole = portalTemplate.update(sqlRole, new PreparedStatementSetter() {
					              public void setValues(PreparedStatement ps) throws SQLException {
					            	  ps.setInt(1, Integer.valueOf(role_id));
					            	  ps.setString(2, request.getParameter("role_name"));
					            	  ps.setString(3, request.getParameter("role_status"));
					            	  ps.setString(4, request.getParameter("role_desc"));
					              }
						    });
	        if(InsertRole>0) {
				if(request.getParameterValues("menu_id") != null) {
		        	String [] menu_id = request.getParameterValues("menu_id");
	        		for(int i=0; i<menu_id.length; i++) {
				        String sqlMenu = "insert into portal.ts_role_menu(role_id, menu_id) values(?, '"+menu_id[i]+"')";
					    portalTemplate.update(sqlMenu, new PreparedStatementSetter() {
				              public void setValues(PreparedStatement ps) throws SQLException {
				            	  ps.setInt(1, Integer.valueOf(role_id));
				              }
					    });
	        		}
				}
				
				if(request.getParameterValues("action_id") != null) {
		        	String [] action_id = request.getParameterValues("action_id");
	        		for(int i=0; i<action_id.length; i++) {
				        String sqlMenuAction = "insert into portal.ts_role_menu_action(role_id, menu_id, action_id) values(?, '"+action_id[i].substring(0,8)+"', '"+action_id[i]+"')";
					    portalTemplate.update(sqlMenuAction, new PreparedStatementSetter() {
				              public void setValues(PreparedStatement ps) throws SQLException {
				            	  ps.setInt(1, Integer.valueOf(role_id));
				              }
					    });
	        		}
				}
				code    = "01";
				status  = "success";
				message = "Data hak akses berhasil disimpan";
				home.writelog(request, "Tambah data hak akses ("+request.getParameter("role_name")+")");

	        }
		    
		}else {
			
	        String sqlRole = "update portal.tm_roles SET role_name = ?, role_status = ?, role_desc = ? where role_id = ?";
	        int InsertRole = portalTemplate.update(sqlRole, new PreparedStatementSetter() {
					              public void setValues(PreparedStatement ps) throws SQLException {
					            	  ps.setString(1, request.getParameter("role_name"));
					            	  ps.setString(2, request.getParameter("role_status"));
					            	  ps.setString(3, request.getParameter("role_desc"));
					            	  ps.setInt(4, Integer.valueOf(request.getParameter("role_id")));
					              }
						    });
	        if(InsertRole>0) {
				if(request.getParameterValues("menu_id") != null) {
			        String sqlResetRole = "delete from portal.ts_role_menu where role_id = ?";
					portalTemplate.update(sqlResetRole, new PreparedStatementSetter() {
			              public void setValues(PreparedStatement ps) throws SQLException {
			            	  ps.setInt(1, Integer.valueOf(request.getParameter("role_id")));
			              }
			    	});
					
		        	String [] menu_id = request.getParameterValues("menu_id");
	        		for(int i=0; i<menu_id.length; i++) {
				        String sqlMenu = "insert into portal.ts_role_menu(role_id, menu_id) values(?, '"+menu_id[i]+"')";
					    portalTemplate.update(sqlMenu, new PreparedStatementSetter() {
				              public void setValues(PreparedStatement ps) throws SQLException {
				            	  ps.setInt(1, Integer.valueOf(request.getParameter("role_id")));
				              }
					    });
	        		}
				}	        	
				
				if(request.getParameterValues("action_id") != null) {
			        String sqlResetRoleAction = "delete from portal.ts_role_menu_action where role_id = ?";
					portalTemplate.update(sqlResetRoleAction, new PreparedStatementSetter() {
			              public void setValues(PreparedStatement ps) throws SQLException {
			            	  ps.setInt(1, Integer.valueOf(request.getParameter("role_id")));
			              }
			    	});
					
		        	String [] action_id = request.getParameterValues("action_id");
	        		for(int i=0; i<action_id.length; i++) {
				        String sqlMenuAction = "insert into portal.ts_role_menu_action(role_id, menu_id, action_id) values(?, '"+action_id[i].substring(0,8)+"', '"+action_id[i]+"')";
					    portalTemplate.update(sqlMenuAction, new PreparedStatementSetter() {
				              public void setValues(PreparedStatement ps) throws SQLException {
				            	  ps.setInt(1, Integer.valueOf(request.getParameter("role_id")));
				              }
					    });
	        		}					
				}
				code    = "01";
				status  = "success";
				message = "Data hak akses berhasil disimpan";		    	         		
				home.writelog(request, "Edit data hak akses ("+request.getParameter("role_name")+")");
	        }
		}
		return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";
	}

	@Override
	public String deleteRole(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";
		
	    String sql        = "select a.role_name "
 		 		 		  + "from portal.tm_roles a where a.role_id=?";
		@SuppressWarnings("deprecation")
		String role_name  = portalTemplate.queryForObject(sql, new Object[]{request.getParameter("id")}, String.class);

        String sqlUser = "UPDATE portal.tm_roles SET role_status='9' WHERE role_id=?";
	    int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
							              public void setValues(PreparedStatement ps) throws SQLException {
							            	  ps.setInt(1, Integer.valueOf(request.getParameter("id")));
							              }
						    });
	
	     if(rowInsertUser>0) {
				code    = "01";
				status  = "success";
				message = "Data hak akses berhasil dihapus";		    	 
				home.writelog(request, "Hapus data hak akses ("+role_name+")");
	     }			
		 return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";
	}

	@Override
	public String savePassword(HttpServletRequest request) {
		
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";

		//Validate
		if(!request.getParameter("txt_password_baru").equals(request.getParameter("txt_password_konfirm"))) {
			code    = "00";
			status  = "error";
			message = "Konfirmasi password tidak sesuai";			
		}
		if(!home.standardPassword(request.getParameter("txt_password_baru"))) {
			code    = "00";
			status  = "error";
			message = "Password minimal 8 karakter terdiri dari huruf besar, huruf kecil dan angka";			
		}
		
		if(!status.equals("error")) {
	 		RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("Authorization", "Basic "+home.getInitByName("api_basic_auth"));
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type","client_credentials");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			ResponseEntity<String> responseToken = restTemplate.exchange(home.baseUrl(request)+"/apis/token", HttpMethod.POST, entity, String.class);
			String jsonToken		 = responseToken.getBody();
			JSONObject dataToken 	 = new JSONObject(jsonToken);
			String access_token		 = dataToken.getString("access_token");			
			
			try {
				RestTemplate apiChangePassword = new RestTemplate();
				HttpHeaders headerChangePassword = new HttpHeaders();
				headerChangePassword.setContentType(MediaType.APPLICATION_JSON);
				headerChangePassword.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				headerChangePassword.add("Authorization", "Bearer "+access_token);			
				Map<String, Object> mapChangePassword = new HashMap<>();
				mapChangePassword.put("username", request.getParameter("user_name"));					
				mapChangePassword.put("password", request.getParameter("txt_password_baru"));					
				mapChangePassword.put("old_password", request.getParameter("txt_password"));
								    			
				HttpEntity<Map<String, Object>> entityChangePassword = new HttpEntity<>(mapChangePassword, headerChangePassword);
				ResponseEntity<String> responseChangePassword = apiChangePassword.postForEntity(home.baseUrl(request)+"/apis/sso/changePassword", entityChangePassword, String.class);
				String jsonChangePassword 		 = responseChangePassword.getBody();
				JSONObject objChangePassword 	 = new JSONObject(jsonChangePassword);
				if(objChangePassword.getString("status").equals("1")) {
					code    = "01";
					status  = "success";
					message = "Edit password berhasil";	
					home.writelog(request, "Edit password");
				}
			}catch(HttpStatusCodeException e) {
					code    = "02";
					status  = "failed";
					message = "Password lama tidak sesuai";	
			}
		}
		return   "{"+
						"\"code\":\""+code+"\","+
						"\"status\":\""+status+"\","+
						"\"message\":\""+message+"\""+
				 "}";
	}

	@Override
	public String saveProfile(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";
		
        String sqlUser = "UPDATE portal.tm_users SET user_nik=?, user_hp=?, user_status=?, user_update=?, last_update=NOW() AT TIME ZONE 'Asia/Jakarta' WHERE user_name=?";
	    int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
							              public void setValues(PreparedStatement ps) throws SQLException {
							            	  ps.setString(1, request.getParameter("user_nik"));
							            	  ps.setString(2, request.getParameter("user_hp"));
							            	  ps.setString(3, request.getParameter("user_status"));
							            	  ps.setInt(4, (Integer)request.getSession().getAttribute("sessionUserId"));
							            	  ps.setString(5, request.getParameter("emp_number"));
							              }
						    });
	
	     if(rowInsertUser>0) {
				code    = "01";
				status  = "success";
				message = "Data pegawai berhasil disimpan";		    	 
				home.writelog(request, "Edit profil");
	     }			
		return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";
	}

}
