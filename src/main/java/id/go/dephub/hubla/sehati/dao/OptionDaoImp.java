package id.go.dephub.hubla.sehati.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import id.go.dephub.hubla.sehati.mapper.EsignListMapper;
import id.go.dephub.hubla.sehati.mapper.KabKotaMapper;
import id.go.dephub.hubla.sehati.mapper.ListLayananMapper;
import id.go.dephub.hubla.sehati.mapper.MenuActionMapper;
import id.go.dephub.hubla.sehati.mapper.MenuMapper;
import id.go.dephub.hubla.sehati.mapper.PropinsiMapper;
import id.go.dephub.hubla.sehati.mapper.RoleMapper;
import id.go.dephub.hubla.sehati.model.EsignList;
import id.go.dephub.hubla.sehati.model.KabKota;
import id.go.dephub.hubla.sehati.model.ListLayanan;
import id.go.dephub.hubla.sehati.model.Menu;
import id.go.dephub.hubla.sehati.model.MenuAction;
import id.go.dephub.hubla.sehati.model.Propinsi;
import id.go.dephub.hubla.sehati.model.Role;

@Transactional
@Repository
public class OptionDaoImp implements OptionDao{
	@Autowired
	@Qualifier("portalJdbcTemplate")
	private JdbcTemplate portalTemplate;

	@Autowired
	@Qualifier("workflowJdbcTemplate")
	private JdbcTemplate workflowTemplate;

	@Autowired
	@Qualifier("datalakeJdbcTemplate")
	private JdbcTemplate datalakeTemplate;

	@Override
	public String getListLayanan(HttpServletRequest request) {
		String result = "<option value=\"\">-- Pilih Layanan --</option>";
		final String user_name = (String)request.getSession().getAttribute("sessionUserName");
        String sql = "select count(username) from ext_process_category_tree a where a.username=?";
		@SuppressWarnings("deprecation")
		int cek = workflowTemplate.queryForObject(sql, new Object[]{user_name}, Integer.class);
		if(cek>0) {
			String sqlCategory  = "select id, username, key_, (case when length(replace(trim(menu_name),' ',''))>0  and menu_name is not null then menu_name else name_ end) name_, order_ from ext_process_category_tree "
								+ "where username = ? and category_ = true and visible_ = true\n"
								+ "and children_id is not null and parent_id is null order by name_";
		    List<ListLayanan> restCategory = workflowTemplate.query(sqlCategory, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, user_name);
				}
		    }, new ListLayananMapper());
	        for (int i = 0; i < restCategory.size(); i++) {
	        	result = result+"<optgroup label=\""+restCategory.get(i).getname_()+"\">";
				result = result+getListSubLayanan(user_name, restCategory.get(i).getid(), false);
	        }
		}else {
			String sqlCategory  = "select id, username, key_, (case when length(replace(trim(menu_name),' ',''))>0  and menu_name is not null then menu_name else name_ end) name_, order_ from ext_process_category_tree "
					+ "where username is null and category_ = true and visible_ = true\n"
					+ "and children_id is not null and parent_id is null order by name_";
			List<ListLayanan> restCategory = workflowTemplate.query(sqlCategory, new ListLayananMapper());
			for (int i = 0; i < restCategory.size(); i++) {
				result = result+"<optgroup label=\""+restCategory.get(i).getname_()+"\">";
				result = result+getListSubLayanan(user_name, restCategory.get(i).getid(), true);
			}
			
		}
		return result;
	}
	
	public String getListSubLayanan(String user_name, String parent, boolean defaults) {
		String result = "";
		if(defaults) {
			String sqlCategory  = "select id, username, key_, (case when length(replace(trim(menu_name),' ',''))>0  and menu_name is not null then menu_name else name_ end) name_, order_ from ext_process_category_tree "
					+ "where username is null and parent_id = ? and visible_ = true and category_ = false "
					+ "order by 4";
		    List<ListLayanan> restCategory = workflowTemplate.query(sqlCategory, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, parent);
				}
		    }, new ListLayananMapper());
	        for (int i = 0; i < restCategory.size(); i++) {
	        	result = result+"<option value=\""+restCategory.get(i).getkey_()+"\">"+restCategory.get(i).getname_()+"</option>";
	        }			
		}else {
			String sqlCategory  = "select id, username, key_, (case when length(replace(trim(menu_name),' ',''))>0  and menu_name is not null then menu_name else name_ end) name_, order_ from ext_process_category_tree "
					+ "where username = ? and parent_id = ? and visible_ = true and category_ = false "
					+ "order by order_";
		    List<ListLayanan> restCategory = workflowTemplate.query(sqlCategory, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, user_name);
					ps.setString(2, parent);
				}
		    }, new ListLayananMapper());
	        for (int i = 0; i < restCategory.size(); i++) {
	        	result = result+"<option value=\""+restCategory.get(i).getkey_()+"\">"+restCategory.get(i).getname_()+"</option>";
	        }						
		}
		return result;
	}

	@Override
	public String getListRole(HttpServletRequest request, String key) {
		String result = "<option value=\"\">-- Pilih Hak Akses --</option>";
		String sqlCategory  = "select role_id, role_name, role_status, role_desc from portal.tm_roles where role_status='1'";
	    List<Role> restCategory = portalTemplate.query(sqlCategory, new RoleMapper());
	    String isSelected = "";
        for (int i = 0; i < restCategory.size(); i++) {
        	if(key.contains(",")) {
				String[] arrid = key.split(",");
				List<String> list = Arrays.asList(arrid);
				if(list.contains(String.valueOf(restCategory.get(i).getrole_id()))){
					isSelected = "selected";
				}else {
					isSelected = "";
				}
        	}else {
        		if(String.valueOf(restCategory.get(i).getrole_id()).equals(key)) {
        			isSelected = "selected";
				}else {
					isSelected = "";
				}
        	}        	
        	result = result+"<option "+isSelected+" value=\""+restCategory.get(i).getrole_id()+"\">"+restCategory.get(i).getrole_name()+"</option>";
        }						
		return result;
	}

	@Override
	public String getListPriviledge(HttpServletRequest request, String role) {
		String result = "";
		String sql    = "select a.menu_id, a.menu_parent, a.menu_name, a.menu_url, a.menu_icon, a.menu_status, a.menu_level, a.menu_counter, coalesce(b.role_id,0) role_id "
					  + "from portal.tm_menu a "
					  + "left join portal.ts_role_menu b on a.menu_id=b.menu_id and b.role_id = ?"
					  + "where a.menu_level = '0' "
					  + "order by a.menu_id";
		List<Menu> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException {
									ps.setInt(1, Integer.valueOf(role));
								}
						    },  new MenuMapper());
		for (int i = 0; i < rest.size(); i++) {
			String subresult = "";
			subresult = getListSubPriviledge(request, role, rest.get(i).getmenu_id(), rest.get(i).getmenu_level());
			String checked   = "";
			if(!Integer.valueOf(rest.get(i).getrole_id()).equals(0)) {
				checked = "checked";
			}
			result = result+ "<tr>"+ 
							 "		<td onclick=\"showSubHakAkses('0', '"+rest.get(i).getmenu_id()+"', $(this))\" style=\"max-height:50px !important; cursor:pointer;\" colspan=\"4\"><i class=\"fa fa-caret-right\"></i> "+rest.get(i).getmenu_name()+"</td>"+
							 "		<td style=\"text-align:center !important;\" align=\"center\">"+ 
							 "		<div style=\"width:100% !important;\" align=\"center\">"+
							 "			<div class=\"form-group\"  align=\"center\" style=\"width:100% !important;text-align:center;\">"+
							 "				<div class=\"console-checkbox-list\"  align=\"center\" style=\"padding-top:15% !important; padding-left:35% !important; width:100% !important; text-align:center !important;\">" + 
							 "					<label class=\"con-check\" style=\"padding-left:50% !important; width:100% !important; text-align:center !important;\">" + 
							 "						<input type=\"checkbox\" name=\"menu_id\" "+checked+" value=\""+rest.get(i).getmenu_id()+"\">" + 
							 "						<span class=\"checkmark\"></span>" + 
							 "					</label>"+ 
							 "				</div>"+
							 "			</div>"+
							 "		</div>"+
							 "		</td>"+ 
							 "		<td>&nbsp;</td>"+ 
							 "</tr>";
			result = result+subresult;
		}
		return result;
	}
	
	public String getListSubPriviledge(HttpServletRequest request, String role, String parent, String level) {
		String result    = "";
		String newlevel = String.valueOf(Integer.valueOf(level)+1);
		String sql    = "select a.menu_id, a.menu_parent, a.menu_name, a.menu_url, a.menu_icon, a.menu_status, a.menu_level, a.menu_counter, coalesce(b.role_id,0) role_id "
					  + "from portal.tm_menu a "
					  + "left join portal.ts_role_menu b on a.menu_id=b.menu_id and b.role_id = ?"
					  + "where a.menu_level = ? and a.menu_parent= ? "
					  + "order by a.menu_id";;
		List<Menu> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException {
									ps.setInt(1, Integer.valueOf(role));
									ps.setString(2, newlevel);
									ps.setString(3, parent);
								}
						    },  new MenuMapper());
		for (int i = 0; i < rest.size(); i++) {
			String icon = "";
			String tab = "";
			String subresult = "";
			String colspan = "";
			if(Integer.valueOf(newlevel)<4) {
				colspan = String.valueOf(4-Integer.valueOf(newlevel));				
			}
			if(colspan.equals("1")) {
				colspan = "";
			}
			if(Integer.valueOf(newlevel)<3) {
				subresult = getListSubPriviledge(request, role, rest.get(i).getmenu_id(), rest.get(i).getmenu_level());				
			}
			if(subresult.length()>0) {
				icon = "<i class=\"fa fa-caret-right\"></i>";
			}
			String checked   = "";
			if(!Integer.valueOf(rest.get(i).getrole_id()).equals(0)) {
				checked = "checked";
			}
			for(int t = 0; t < Integer.valueOf(newlevel); t++) {
				tab = tab+"<td width=\"50\"></td>";
			}
			result = result+ "<tr style=\"display:none;\" class=\"menu_"+newlevel+"_"+parent+"\">"+ 
							 tab+
							 "		<td onclick=\"showSubHakAkses('"+newlevel+"', '"+rest.get(i).getmenu_id()+"', $(this))\" style=\"max-height:50px !important; cursor:pointer;\" colspan=\""+colspan+"\">"+icon+" "+rest.get(i).getmenu_name()+"</td>"+
							 "		<td style=\"text-align:center !important;\" align=\"center\">"+ 
							 "		<div style=\"width:100% !important;\" align=\"center\">"+
							 "			<div class=\"form-group\"  align=\"center\" style=\"width:100% !important;text-align:center;\">"+
							 "				<div class=\"console-checkbox-list\"  align=\"center\" style=\"padding-top:15% !important; padding-left:35% !important; width:100% !important; text-align:center !important;\">" + 
							 "					<label class=\"con-check\" style=\"padding-left:50% !important; width:100% !important; text-align:center !important;\">" + 
							 "						<input type=\"checkbox\" name=\"menu_id\" "+checked+" value=\""+rest.get(i).getmenu_id()+"\">" + 
							 "						<span class=\"checkmark\"></span>" + 
							 "					</label>"+ 
							 "				</div>"+
							 "			</div>"+
							 "		</div>"+
							 "		</td>"+ 
							 "		<td>"+getListPriviledgeAction(request,rest.get(i).getmenu_id(), rest.get(i).getrole_id())+"</td>"+ 
							 "</tr>";
			result = result+subresult;
		}
		return result;
	}

	public String getListPriviledgeAction(HttpServletRequest request, String menuid, int role) {
		String result = "";
		String sql    = "select a.menu_id, a.action_id, a.action_name, a.action_act, a.action_icon, a.action_class, a.action_intable, a.action_status, coalesce(b.role_id,0) role_id "
					  + "from portal.tm_menu_action a "
					  + "left join portal.ts_role_menu_action b on a.menu_id=b.menu_id and a.action_id=b.action_id and b.role_id = ?"
					  + "where a.menu_id = ? "
					  + "order by a.menu_id, a.action_id";
		List<MenuAction> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
								@Override
								public void setValues(PreparedStatement ps) throws SQLException {
									ps.setInt(1, role);
									ps.setString(2, menuid);
								}
						    },  new MenuActionMapper());
		result = result+"<div class=\"col-lg-12 col-md-12\">"+
						"	<div class=\"form-group\" style=\"padding-top:20px !important;\">"+
						"		<div class=\"console-checkbox-inline\">";
		for (int i = 0; i < rest.size(); i++) {
			String checked   = "";
			if(!Integer.valueOf(rest.get(i).getrole_id()).equals(0)) {
				checked = "checked";
			}			
			result = result+ "		<label class=\"con-check\">" + 
							 "			<input type=\"checkbox\" "+checked+" name=\"action_id\" value=\""+rest.get(i).getaction_id()+"\">" +rest.get(i).getaction_name()+
							 "			<span class=\"checkmark\"></span>" + 
							 "		</label>";
		}
		result = result+"		</div>"+
						"	</div>"+
						"</div>";
		
		return result;
	}

	@Override
	public String getListProvinsi(HttpServletRequest request, String key) {
		String result = "<option value=\"\">-- Pilih Propinsi --</option>";
		String sqlCategory  = "select kode_kemendagri, nama_provinsi::varchar nama_provinsi from dwh.provinsi";
	    List<Propinsi> restCategory = datalakeTemplate.query(sqlCategory, new PropinsiMapper());
	    String isSelected = "";
        for (int i = 0; i < restCategory.size(); i++) {
        	if(key.contains(",")) {
				String[] arrid = key.split(",");
				List<String> list = Arrays.asList(arrid);
				if(list.contains(String.valueOf(restCategory.get(i).getkode_kemendagri()))){
					isSelected = "selected";
				}else {
					isSelected = "";
				}
        	}else {
        		if(String.valueOf(restCategory.get(i).getkode_kemendagri()).equals(key)) {
        			isSelected = "selected";
				}else {
					isSelected = "";
				}
        	}        	
        	result = result+"<option "+isSelected+" value=\""+restCategory.get(i).getkode_kemendagri()+"\">"+restCategory.get(i).getnama_provinsi()+"</option>";
        }		
		return result;
	}

	@Override
	public String getListKabKota(HttpServletRequest request, String provinsi, String key) {
		String result = "<option value=\"\">-- Pilih Kota/Kabupaten --</option>";
		String sqlCategory  = "select kode_kemendagri, nama_kabkota::varchar nama_kabkota from dwh.kabkota where kode_provinsi = ?";
	    List<KabKota> restCategory = datalakeTemplate.query(sqlCategory, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, provinsi);
			}
	    },  new KabKotaMapper());
	    String isSelected = "";
        for (int i = 0; i < restCategory.size(); i++) {
        	if(key.contains(",")) {
				String[] arrid = key.split(",");
				List<String> list = Arrays.asList(arrid);
				if(list.contains(String.valueOf(restCategory.get(i).getkode_kemendagri()))){
					isSelected = "selected";
				}else {
					isSelected = "";
				}
        	}else {
        		if(String.valueOf(restCategory.get(i).getkode_kemendagri()).equals(key)) {
        			isSelected = "selected";
				}else {
					isSelected = "";
				}
        	}        	
        	result = result+"<option "+isSelected+" value=\""+restCategory.get(i).getkode_kemendagri()+"\">"+restCategory.get(i).getnama_kabkota()+"</option>";
        }		
		return result;
	}

	@Override
	public String roleAction(HttpServletRequest request, String menuid, String intable) {
		String result = "";
		final int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
        String sql = "select distinct 0 role_id, c.menu_id, c.action_id, c.action_name, c.action_act, c.action_icon, c.action_class, c.action_intable, c.action_status "
        				 + "from portal.td_users_roles a "
        				 + "left join portal.ts_role_menu_action b on a.role_id=b.role_id "
        				 + "left join portal.tm_menu_action c on b.action_id=c.action_id "
        				 + "where a.user_id = ? and c.menu_id = ? and c.action_intable = ?";
		List<MenuAction> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, user_id);
				ps.setString(2, menuid);
				ps.setString(3, intable);
			}
	    },  new MenuActionMapper());
		for (int i = 0; i < rest.size(); i++) {
			result = result+"<button type=\"button\" onclick=\""+rest.get(i).getaction_act()+"\" class=\""+rest.get(i).getaction_class()+"\"><i class=\""+rest.get(i).getaction_icon()+"\"></i>"+rest.get(i).getaction_name()+"</button> ";
		}		
		return result;
	}

	@Override
	public String getListEsignUser(HttpServletRequest request, String office_code, String key) {
		String result = "<option value=\"\">-- Pilih Penandatangan --</option>";		
        String sql = "SELECT A.esign_id id, A.user_id, A.esign_nik, A.esign_nama, A.esign_jabatan, A.esign_email, A.esign_telp, A.esign_kota, A.esign_propinsi, A.esign_nip, A.esign_unit_kerja, A.esign_ktp, A.esign_rekom, A.esign_ttd, "
			       + "TO_CHAR(A.esign_tanggal,'DD-MM-YYYY HH24:MI:SS') esign_tanggal, A.esign_status, A.esign_respon "
  		       + "FROM portal.tx_esign A "
  		       + "LEFT JOIN portal.tm_employee B ON A.user_id=B.user_id "
  		       + "WHERE A.esign_status = '4' AND (B.office_code='004000000000000' OR LEFT(B.office_code,6)=?)";
	    List<EsignList> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, office_code.substring(0, 6));
			}
	    }, new EsignListMapper());	
	    String isSelected = "";
        for (int i = 0; i < rest.size(); i++) {
    		if(String.valueOf(rest.get(i).getuser_id()).equals(key)) {
    			isSelected = "selected";
			}else {
				isSelected = "";
			}
        	result = result+"<option "+isSelected+" value=\""+rest.get(i).getuser_id()+"\">"+rest.get(i).getesign_jabatan().toUpperCase()+" - "+rest.get(i).getesign_nama()+"</option>";
        }		
        return result;
	}
	
}
