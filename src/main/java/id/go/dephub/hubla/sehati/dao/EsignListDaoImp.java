package id.go.dephub.hubla.sehati.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import id.go.dephub.hubla.sehati.mapper.EsignDocMapper;
import id.go.dephub.hubla.sehati.mapper.EsignListMapper;
import id.go.dephub.hubla.sehati.model.EsignDoc;
import id.go.dephub.hubla.sehati.model.EsignList;
import id.go.dephub.hubla.sehati.model.User;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Repository
public class EsignListDaoImp implements EsignListDao{
	@Autowired
	@Qualifier("portalJdbcTemplate")
	private JdbcTemplate portalTemplate;
	
	@Autowired
	UserDao users;	

	@Autowired
	HomeDao home;	
	
	@Override
	public void setEsignSync(HttpServletRequest request) {
		String user_name = (String)request.getSession().getAttribute("sessionUserName");
		int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
        String sqlUserID = "select count(a.user_id) user_id "
	   	   		   		 + "from portal.tx_esign a where a.user_id=?";
		@SuppressWarnings("deprecation")
		final int cek_esign = portalTemplate.queryForObject(sqlUserID, new Object[]{user_id}, Integer.class);
		if(cek_esign<1) {
	        String sqlUserNIK = "select a.user_nik "
  	   		   		 		  + "from portal.tm_users a where a.user_id=?";
			@SuppressWarnings("deprecation")
			final String user_nik = portalTemplate.queryForObject(sqlUserNIK, new Object[]{user_id}, String.class);
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", "Basic ZW9mZmljZTpGODVqU3QzeW1iNTI1WDRMelN0Mw==");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
			ResponseEntity<String> responseCekStatusUser = restTemplate.exchange("https://esign.hubla.dephub.go.id/api/user/status/"+user_nik, HttpMethod.GET, entity, String.class);
			String jsonCekStatusUser 		 = responseCekStatusUser.getBody();
			JSONObject objCekStatusUser 	 = new JSONObject(jsonCekStatusUser);
			String status_cekstatususer		 = objCekStatusUser.getString("status");
			if(!status_cekstatususer.equals("NOT_REGISTERED")) {
				List<User> userdata = users.getUser(user_name);
		        String sqlUserId = "select coalesce(max(a.esign_id),0) esign_id "
		        		 		 + "from portal.tx_esign a";
		        int getuser_id = portalTemplate.queryForObject(sqlUserId, Integer.class);
		        	getuser_id = getuser_id+1;
		        final int esign_id = getuser_id;
		        
				for(int i = 0; i <userdata.size(); i++) {
					final String getuser_nik = userdata.get(i).getuser_nik();
					final String getuser_fullname = userdata.get(i).getuser_fullname();
					final String getuser_email = userdata.get(i).getuser_email();
					final String getuser_hp = userdata.get(i).getuser_hp();
					final String getemp_number = userdata.get(i).getemp_number();
					final String getposition_name = userdata.get(i).getposition_name();
					final String getoffice_name = userdata.get(i).getoffice_name();
					String setesign_status = "2";
					if(status_cekstatususer.equals("ON_SET_PASSPHRASE")) {
						setesign_status = "3";
					}
					if(status_cekstatususer.equals("ISSUE")) {
						setesign_status = "4";
					}
					final String esign_status = setesign_status;
					
			        String sqlUser = "INSERT INTO portal.tx_esign "
					        	   + "(esign_id, user_id, esign_nik, esign_nama, esign_email, esign_telp, esign_nip, esign_jabatan, esign_unit_kerja, esign_status, esign_tanggal) "
					        	   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
			        
			        portalTemplate.update(sqlUser, new PreparedStatementSetter() {
								              public void setValues(PreparedStatement ps) throws SQLException {
								            	  ps.setInt(1, esign_id);
								            	  ps.setInt(2, user_id);
								            	  ps.setString(3, getuser_nik);
								            	  ps.setString(4, getuser_fullname);
								            	  ps.setString(5, getuser_email);
								            	  ps.setString(6, getuser_hp);
								            	  ps.setString(7, getemp_number);
								            	  ps.setString(8, getposition_name);
								            	  ps.setString(9, getoffice_name);
								            	  ps.setString(10, esign_status);
								              }
							        });
				}
			}
		}else {
			
	        String sqlEsignStatus = "select a.esign_status "
				   		 		  + "from portal.tx_esign a where a.user_id=?";
			@SuppressWarnings("deprecation")
			final String reg_status = portalTemplate.queryForObject(sqlEsignStatus, new Object[]{user_id}, String.class);
			if(!reg_status.equals("0") && !reg_status.equals("4")) {
		        String sqlEsignNik = "select a.esign_nik "
		   		 		  + "from portal.tx_esign a where a.user_id=?";
				@SuppressWarnings("deprecation")
				final String user_nik = portalTemplate.queryForObject(sqlEsignNik, new Object[]{user_id}, String.class);
				
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization", "Basic ZW9mZmljZTpGODVqU3QzeW1iNTI1WDRMelN0Mw==");
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
				ResponseEntity<String> responseCekStatusUser = restTemplate.exchange("https://esign.hubla.dephub.go.id/api/user/status/"+user_nik, HttpMethod.GET, entity, String.class);
				String jsonCekStatusUser 		 = responseCekStatusUser.getBody();
				JSONObject objCekStatusUser 	 = new JSONObject(jsonCekStatusUser);
				String status_cekstatususer		 = objCekStatusUser.getString("status");
				
				String setesign_status = "0";
				if(status_cekstatususer.equals("ON_SET_PASSPHRASE")) {
					setesign_status = "3";
					final String esign_status = setesign_status;
			        String sqlUser = "update portal.tx_esign set esign_status = ? where user_id = ?";
				    portalTemplate.update(sqlUser, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setString(1, esign_status);
									            	  ps.setInt(2, user_id);
									              }
								        });
				}
				if(status_cekstatususer.equals("ISSUE")) {
					setesign_status = "4";
					final String esign_status = setesign_status;
					
			        String sqlUser = "update portal.tx_esign set esign_status = ? where user_id = ?";
				    portalTemplate.update(sqlUser, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setString(1, esign_status);
									            	  ps.setInt(2, user_id);
									              }
								        });
				}
			}
		}
	}

	@Override
	public List<EsignList> getEsignList(HttpServletRequest request) {
		String user_name = (String)request.getSession().getAttribute("sessionUserName");
		final int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
		List<User> userdata = users.getUser(user_name);
		String isadmin = "0";
		for(int i = 0; i <userdata.size(); i++) {
			String role_id = userdata.get(i).getrole_id();
			String [] role_id_arr = role_id.split("\\;");
			List<String> list = Arrays.asList(role_id_arr);
			if(list.contains("1")){
				isadmin = "1";
			}
		}
		if(isadmin.equals("0")) {
	        String sql = "SELECT A.esign_id id, A.user_id, A.esign_nik, A.esign_nama, A.esign_jabatan, A.esign_email, A.esign_telp, A.esign_kota, A.esign_propinsi, A.esign_nip, A.esign_unit_kerja, A.esign_ktp, A.esign_rekom, A.esign_ttd, "
	 			       + "TO_CHAR(A.esign_tanggal,'DD-MM-YYYY HH24:MI:SS') esign_tanggal, A.esign_status, A.esign_respon "
	     		       + "FROM portal.tx_esign A WHERE A.user_id = ? ";
	        List<EsignList> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, user_id);
				}
	        }, new EsignListMapper());			
	        return rest;
		}else{
	        String sql = "SELECT A.esign_id id, A.user_id, A.esign_nik, A.esign_nama, A.esign_jabatan, A.esign_email, A.esign_telp, A.esign_kota, A.esign_propinsi, A.esign_nip, A.esign_unit_kerja, A.esign_ktp, A.esign_rekom, A.esign_ttd, "
	 			       + "TO_CHAR(A.esign_tanggal,'DD-MM-YYYY HH24:MI:SS') esign_tanggal, A.esign_status, A.esign_respon "
	     		       + "FROM portal.tx_esign A ";
	        List<EsignList> rest = portalTemplate.query(sql, new EsignListMapper());			
	        return rest;			
		}
	}

	@Override
	public List<EsignList> getEsignData(String key) {
         String sql = "SELECT A.esign_id id, A.user_id, A.esign_nik, A.esign_nama, A.esign_jabatan, A.esign_email, A.esign_telp, A.esign_kota, A.esign_propinsi, A.esign_nip, A.esign_unit_kerja, A.esign_ktp, A.esign_rekom, A.esign_ttd, "
			       + "TO_CHAR(A.esign_tanggal,'DD-MM-YYYY HH24:MI:SS') esign_tanggal, A.esign_status, A.esign_respon "
	  		       + "FROM portal.tx_esign A WHERE A.user_id = ?";
	     List<EsignList> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, Integer.valueOf(key));
				}
	        }, new EsignListMapper());
	     return rest;
	}

	@Override
	public String saveEsign(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";
		
		if(request.getParameter("action").equals("register")) {
			int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
			
	        String sqlUserID = "select count(a.user_id) user_id "
  	   		   		 + "from portal.tx_esign a where a.user_id=?";
			@SuppressWarnings("deprecation")
			final int cek_esign = portalTemplate.queryForObject(sqlUserID, new Object[]{user_id}, Integer.class);
			int insertesign = 0;
			if(cek_esign<1) {
				
		        String sqlUserId = "select coalesce(max(a.esign_id),0) esign_id "
		        				 + "from portal.tx_esign a";
				int getuser_id = portalTemplate.queryForObject(sqlUserId, Integer.class);
					getuser_id = getuser_id+1;
				final int esign_id = getuser_id;
		
		        String sqlUser = "INSERT INTO portal.tx_esign "
			        	   	   + "(esign_id, user_id, esign_nik, esign_nama, esign_email, esign_telp, esign_nip, esign_jabatan, esign_unit_kerja, esign_status, esign_kota, esign_propinsi, esign_ktp, esign_rekom, esign_ttd, esign_tanggal) "
			        	   	   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
		
		        insertesign = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
						              public void setValues(PreparedStatement ps) throws SQLException {
						            	  ps.setInt(1, esign_id);
						            	  ps.setInt(2, user_id);
						            	  ps.setString(3, request.getParameter("esign_nik"));
						            	  ps.setString(4, request.getParameter("esign_nama"));
						            	  ps.setString(5, request.getParameter("esign_email"));
						            	  ps.setString(6, request.getParameter("esign_telp"));
						            	  ps.setString(7, request.getParameter("esign_nip"));
						            	  ps.setString(8, request.getParameter("esign_jabatan"));
						            	  ps.setString(9, request.getParameter("esign_unit_kerja"));
						            	  ps.setString(10, "0");
						            	  ps.setString(11, request.getParameter("esign_kota"));
						            	  ps.setString(12, request.getParameter("esign_propinsi"));
						            	  ps.setString(13, request.getParameter("esign_ktp"));
						            	  ps.setString(14, request.getParameter("esign_rekom"));
						            	  ps.setString(15, request.getParameter("esign_ttd"));
						              }
					        });
			}else {
		        String sqlUser = "UPDATE portal.tx_esign SET "
		        			   + "esign_status=?, esign_kota=?, esign_propinsi=?, esign_ktp=?, esign_rekom=?, esign_ttd=? "
		        			   + "WHERE user_id=?";
	
		        insertesign = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
					              public void setValues(PreparedStatement ps) throws SQLException {
					            	  ps.setString(1, "0");
					            	  ps.setString(2, request.getParameter("esign_kota"));
					            	  ps.setString(3, request.getParameter("esign_propinsi"));
					            	  ps.setString(4, request.getParameter("esign_ktp"));
					            	  ps.setString(5, request.getParameter("esign_rekom"));
					            	  ps.setString(6, request.getParameter("esign_ttd"));
					            	  ps.setInt(7, user_id);
					              }
				        });
				
			}	        
	        if(insertesign>0) {
	        	home.writelog(request, "Registrasi E-sign");	
				code    = "01";
				status  = "success";
				message = "Registrasi E-Sign berhasil! Silahkan menunggu verifikasi admin.";		    	         		        	
	        }
		}
		if(request.getParameter("action").equals("approve")) {
	         String sqlUser = "UPDATE portal.tx_esign SET esign_status='2', esign_respon=? WHERE esign_nik=?";
		     int insertesign = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
						              public void setValues(PreparedStatement ps) throws SQLException {
						            	  ps.setString(1, request.getParameter("esign_respon"));
						            	  ps.setString(2, request.getParameter("esign_nik"));
						              }
					        });
		     
		     if(insertesign>0) {
		     		home.writelog(request, "Approve Registrasi E-sign ("+request.getParameter("esign_nama")+")");	
					code    = "01";
					status  = "success";
					message = "Data berhasil disetujui";		    	         		        	
		     }
			
		}
		if(request.getParameter("action").equals("reject")) {
	        String sqlUser = "UPDATE portal.tx_esign SET esign_status='1', esign_respon=? WHERE esign_nik=?";

		     int insertesign = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
						              public void setValues(PreparedStatement ps) throws SQLException {
						            	  ps.setString(1, request.getParameter("esign_respon"));
						            	  ps.setString(2, request.getParameter("esign_nik"));
						              }
					        });
		     
		     if(insertesign>0) {
		     		home.writelog(request, "Reject Registrasi E-sign ("+request.getParameter("esign_nama")+")");	
					code    = "01";
					status  = "success";
					message = "Data berhasil ditolak";		    	         		        	
		     }
			
		}
		
		return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";	
	}
	
	@Override
	public String cekEsign(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";

		int user_id = Integer.valueOf(request.getParameter("id"));
        String sqlUserID = "select a.esign_status "
	   	   		   		 + "from portal.tx_esign a where a.user_id=?";
		@SuppressWarnings("deprecation")
		String esign_status = portalTemplate.queryForObject(sqlUserID, new Object[]{user_id}, String.class);
		if(esign_status.equals("0")) {
			code    = "01";
			status  = "success";
			message = "Data sedang dalam proses verifikasi Admin";		    	         		        				
		}else {
			if(esign_status.equals("1")) {
				code    = "01";
				status  = "success";
				message = "Data registrasi ditolak oleh Admin";		    	         		        				
			}else {
				
		        String sqlUserNik = "select a.esign_nik "
		        				  + "from portal.tx_esign a where a.user_id=?";
				@SuppressWarnings("deprecation")
				String user_nik = portalTemplate.queryForObject(sqlUserNik, new Object[]{user_id}, String.class);
				
				RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.add("Authorization", "Basic ZW9mZmljZTpGODVqU3QzeW1iNTI1WDRMelN0Mw==");
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
				ResponseEntity<String> responseCekStatusUser = restTemplate.exchange("https://esign.hubla.dephub.go.id/api/user/status/"+user_nik, HttpMethod.GET, entity, String.class);
				String jsonCekStatusUser 		 = responseCekStatusUser.getBody();
				JSONObject objCekStatusUser 	 = new JSONObject(jsonCekStatusUser);
				String status_cekstatususer		 = objCekStatusUser.getString("status");
				String message_cekstatususer	 = objCekStatusUser.getString("message");

				String setesign_status = "0";
				if(status_cekstatususer.equals("ON_SET_PASSPHRASE")) {
					setesign_status = "3";
					final String paresign_status = setesign_status;
			        String sqlUser = "update portal.tx_esign set esign_status = ? where user_id = ?";
				    portalTemplate.update(sqlUser, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setString(1, paresign_status);
									            	  ps.setInt(2, user_id);
									              }
								        });
					code    = "01";
					status  = "success";
					message = message_cekstatususer;				
				    
				}
				if(status_cekstatususer.equals("ISSUE")) {
					setesign_status = "4";
					final String paresign_status = setesign_status;
					
			        String sqlUser = "update portal.tx_esign set esign_status = ? where user_id = ?";
				    portalTemplate.update(sqlUser, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setString(1, paresign_status);
									            	  ps.setInt(2, user_id);
									              }
								        });
					code    = "01";
					status  = "success";
					message = message_cekstatususer;				
				}
				
			}			
		}
    	home.writelog(request, "Cek Status E-sign");			
		return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";	
	}	

	@Override
	public List<EsignDoc> getEsignDocList(HttpServletRequest request) {
		final int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
        String sql = "SELECT doc_id, doc_code, doc_desc, doc_type, doc_number, TO_CHAR(doc_date_start,'YYYY-MM-DD HH24:MI:SS') doc_date_start, TO_CHAR(doc_date_end,'YYYY-MM-DD HH24:MI:SS') doc_date_end, doc_file, doc_file_path, doc_file_sign, doc_file_sign_path, doc_file_page, doc_file_sign_id, doc_by, doc_qr_page, doc_qr_position, doc_qr_x, doc_qr_y, doc_qr_size, doc_sign_use, doc_sign_page, doc_sign_size, doc_sign_x, doc_sign_y, doc_status, user_create, TO_CHAR(date_create,'YYYY-MM-DD HH24:MI:SS') date_create, user_update, TO_CHAR(last_update,'YYYY-MM-DD HH24:MI:SS') last_update "
        		   + "FROM portal.tx_esign_doc where doc_status not in('9') and (user_create = ? or doc_by = ?)";
	    List<EsignDoc> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, user_id);
				ps.setInt(2, user_id);
			}
        }, new EsignDocMapper());			
	    return rest;			
	}

	@Override
	public String saveEsignDoc(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";
		final int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
		
        String sqlDocId  = "select coalesce(max(a.doc_id),0) doc_id "
						 + "from portal.tx_esign_doc a";
		int getdoc_id 	 = portalTemplate.queryForObject(sqlDocId, Integer.class);
			getdoc_id    = getdoc_id+1;
		final int doc_id = getdoc_id;
		
		String SqlInsert = "INSERT INTO portal.tx_esign_doc "
						 + "(doc_id, doc_code, doc_desc, doc_type, doc_number, doc_date_start, doc_date_end, doc_file, "
						 + "doc_file_path, doc_file_page, doc_by, doc_qr_page, doc_qr_position, doc_qr_x, doc_qr_y, doc_qr_size, "
						 + "doc_status, user_create, date_create) "
						 + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
        int insertesign = portalTemplate.update(SqlInsert, new PreparedStatementSetter() {
				            public void setValues(PreparedStatement ps) throws SQLException {
				          	  ps.setInt(1, doc_id);
				          	  ps.setString(2, request.getParameter("doc_code"));
				          	  ps.setString(3, request.getParameter("doc_desc"));
				          	  ps.setString(4, request.getParameter("doc_type"));
				          	  ps.setString(5, request.getParameter("doc_number"));
				          	  ps.setDate(6, Date.valueOf(home.dateFormat(request.getParameter("doc_date_start"))));
				          	  ps.setDate(7, request.getParameter("doc_date_end").length()>0 ? Date.valueOf(home.dateFormat(request.getParameter("doc_date_end"))) : null);
				          	  ps.setString(8, request.getParameter("frmFileName"));
				          	  ps.setString(9, request.getParameter("frmFilePath"));
				          	  ps.setString(10, request.getParameter("jmlHal"));
				          	  ps.setInt(11, Integer.valueOf(request.getParameter("doc_by")));
				          	  ps.setString(12, request.getParameter("doc_qr_page"));
				          	  ps.setString(13, request.getParameter("doc_qr_position"));
				          	  ps.setString(14, request.getParameter("doc_qr_x"));
				          	  ps.setString(15, request.getParameter("doc_qr_y"));
				          	  ps.setString(16, request.getParameter("doc_qr_size"));
				          	  ps.setString(17, "1");
				          	  ps.setInt(18, user_id);
				            }
				        });
        
	     if(insertesign>0) {
	     		home.writelog(request, "Tambah Dokumen Digital Signature ("+request.getParameter("doc_number")+")");	
				code    = "01";
				status  = "success";
				message = "Data berhasil disimpan";		    	         		        	
	     }
		 
		 return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";	
	}

	@Override
	public List<EsignDoc> getEsignDocData(String key) {
         String sql = "SELECT doc_id, doc_code, doc_desc, doc_type, doc_number, TO_CHAR(doc_date_start,'DD-MM-YYYY') doc_date_start, TO_CHAR(doc_date_end,'DD-MM-YYYY') doc_date_end, doc_file, doc_file_path, doc_file_sign, doc_file_sign_path, doc_file_page, doc_file_sign_id, doc_by, doc_qr_page, doc_qr_position, doc_qr_x, doc_qr_y, doc_qr_size, doc_sign_use, doc_sign_page, doc_sign_size, doc_sign_x, doc_sign_y, doc_status, user_create, TO_CHAR(date_create,'DD-MM-YYYY HH24:MI:SS') date_create, user_update, TO_CHAR(last_update,'DD-MM-YYYY HH24:MI:SS') last_update "
        		   + "FROM portal.tx_esign_doc WHERE doc_id = ?";
	     List<EsignDoc> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, Integer.valueOf(key));
				}
	        }, new EsignDocMapper());
	     return rest;
	}

	@Override
	public ResponseEntity<InputStreamResource> preview(String id) {
        String sql = "SELECT doc_id, doc_code, doc_desc, doc_type, doc_number, TO_CHAR(doc_date_start,'DD-MM-YYYY') doc_date_start, TO_CHAR(doc_date_end,'DD-MM-YYYY') doc_date_end, doc_file, doc_file_path, doc_file_sign, doc_file_sign_path, doc_file_page, doc_file_sign_id, doc_by, doc_qr_page, doc_qr_position, doc_qr_x, doc_qr_y, doc_qr_size, doc_sign_use, doc_sign_page, doc_sign_size, doc_sign_x, doc_sign_y, doc_status, user_create, TO_CHAR(date_create,'DD-MM-YYYY HH24:MI:SS') date_create, user_update, TO_CHAR(last_update,'DD-MM-YYYY HH24:MI:SS') last_update "
        			+ "FROM portal.tx_esign_doc WHERE doc_id = ?";
	    List<EsignDoc> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setInt(1, Integer.valueOf(id));
				}
	        }, new EsignDocMapper());
	    
	    float added = 0;
	    if(Integer.valueOf(rest.get(0).getdoc_qr_size())<=60) {
	    	added = 70-Integer.valueOf(rest.get(0).getdoc_qr_size());
	    }
	    float percent = (((100-Integer.valueOf(rest.get(0).getdoc_qr_size()))/10)*10)+((100-Integer.valueOf(rest.get(0).getdoc_qr_size()))/10)+added;
		float scale = ((60+percent)/10);
		String srcPdf = home.rootPath()+"/"+rest.get(0).getdoc_file_path();
		String destPdf = home.rootPath()+"/"+rest.get(0).getdoc_file_path().replace(".pdf", "_stamp.pdf");
		String imagePath = home.root()+"classes/static/assets/apps/images/qr.png";
		//String imagePath = home.root()+"assets/apps/images/qr.png";
		//float scale = (80/100);
		try {
			PdfReader readers = new PdfReader(srcPdf);
	        PdfStamper stamper;
	        
			stamper = new PdfStamper(readers, new FileOutputStream(destPdf));
			Rectangle cropbox = readers.getCropBox(1);
					  
	        String qrpage = rest.get(0).getdoc_qr_page();
	        PdfContentByte content;
        	int n = readers.getNumberOfPages();
        	if(qrpage.contains(",")) {
        		String [] qrpagearr = qrpage.split(",");
        		String [] doc_qr_xarr = rest.get(0).getdoc_qr_x().split(",");
        		String [] doc_qr_yarr = rest.get(0).getdoc_qr_y().split(",");
        		for(int p = 0; p<qrpagearr.length; p++) {
        			if(Integer.valueOf(qrpagearr[p].trim())<=n) {
            			Image imageqr = Image.getInstance(imagePath.replaceAll("upload/", ""));
            	        imageqr.scaleAbsolute((cropbox.getWidth()/scale), (cropbox.getWidth()/scale));
            	        
            	        if(rest.get(0).getdoc_qr_position().equals("bottomright")) {
            		        imageqr.setAbsolutePosition(cropbox.getWidth()-((cropbox.getWidth()/scale)+20), cropbox.getBottom()+20);		        	
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("bottomleft")) {
            		        imageqr.setAbsolutePosition(cropbox.getBottom()+15, cropbox.getBottom()+20);
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("bottommid")) {
            		        imageqr.setAbsolutePosition((cropbox.getWidth()/2)-(((cropbox.getWidth()/scale)/2)), cropbox.getBottom()+20);
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("topright")) {
            		        imageqr.setAbsolutePosition(cropbox.getWidth()-((cropbox.getWidth()/scale)+20), cropbox.getHeight()-((cropbox.getWidth()/scale)+10));		        	
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("topleft")) {
            		        imageqr.setAbsolutePosition(cropbox.getBottom()+12, cropbox.getHeight()-((cropbox.getWidth()/scale)+10));		        	
            	        } 
            	        if(rest.get(0).getdoc_qr_position().equals("topmid")) {
            		        imageqr.setAbsolutePosition((cropbox.getWidth()/2)-(((cropbox.getWidth()/scale)/2)), cropbox.getHeight()-((cropbox.getWidth()/scale)+10));
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("coordinate")) {
            	        	imageqr.setAbsolutePosition(Float.parseFloat(doc_qr_xarr[p]),Float.parseFloat(doc_qr_yarr[p]));
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("custom")) {
            	        	imageqr.setAbsolutePosition(Float.parseFloat(doc_qr_xarr[p]),Float.parseFloat(doc_qr_yarr[p]));
            	        }
            	        
            	        /*
            	        if(rest.get(0).getdoc_qr_position().equals("coordinate")) {
            	        	float doc_qr_x = Float.parseFloat(doc_qr_xarr[p])-55;
            	            if(Float.parseFloat(doc_qr_xarr[p])>55) {
            	            	float diff = doc_qr_x/3;
            	            	doc_qr_x = doc_qr_x - diff;
            	            }
            	        	float doc_qr_y = Float.parseFloat(doc_qr_yarr[p]);
            	        	if(doc_qr_y>0) {
            	        		doc_qr_y = (doc_qr_y/100)*70;
            	        		float prec = (doc_qr_y/100)*(72/10);
            	        		doc_qr_y = doc_qr_y-prec;
            	        	}
            		        imageqr.setAbsolutePosition(cropbox.getBottom()+12+doc_qr_x, cropbox.getHeight()-((cropbox.getWidth()/scale)+10)-doc_qr_y);
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("custom")) {
            	        	float doc_qr_x = Float.parseFloat(doc_qr_xarr[p])-55;
            	            if(Float.parseFloat(doc_qr_xarr[p])>55) {
            	            	float diff = doc_qr_x/3;
            	            	doc_qr_x = doc_qr_x - diff;
            	            }
            	        	float doc_qr_y = Float.parseFloat(doc_qr_yarr[p]);
            	        	float doc_qr_page = Float.parseFloat(qrpagearr[p]);
            	        	float minuspage = 1;
            	        	if(doc_qr_page>1) {
            	        		minuspage = doc_qr_page-1;
            	        	}
            	        	if(doc_qr_y>cropbox.getHeight()) {
            	        		doc_qr_y = doc_qr_y - ((cropbox.getHeight()+(462)+((75/10)*minuspage))*(minuspage));
            	        	}
            	        	if(doc_qr_y>0) {
            	        		doc_qr_y = (doc_qr_y/100)*70;
            	        		float prec = (doc_qr_y/100)*(72/10);
            	        		doc_qr_y = doc_qr_y-prec;
            	        	}
            		        imageqr.setAbsolutePosition(cropbox.getBottom()+12+doc_qr_x, cropbox.getHeight()-((cropbox.getWidth()/scale)+10)-doc_qr_y);
            	        }        		
            	        */
			        	content = stamper.getOverContent(Integer.valueOf(qrpagearr[p].trim())); 
			        	content.addImage(imageqr);		        				        			
        			}
        		}
        	}else {
    			Image imageqr = Image.getInstance(imagePath.replaceAll("upload/", ""));
    	        imageqr.scaleAbsolute((cropbox.getWidth()/scale), (cropbox.getWidth()/scale));
    	        if(rest.get(0).getdoc_qr_position().equals("bottomright")) {
    		        imageqr.setAbsolutePosition(cropbox.getWidth()-((cropbox.getWidth()/scale)+20), cropbox.getBottom()+20);		        	
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("bottomleft")) {
    		        imageqr.setAbsolutePosition(cropbox.getBottom()+15, cropbox.getBottom()+20);
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("bottommid")) {
    		        imageqr.setAbsolutePosition((cropbox.getWidth()/2)-(((cropbox.getWidth()/scale)/2)), cropbox.getBottom()+20);
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("topright")) {
    		        imageqr.setAbsolutePosition(cropbox.getWidth()-((cropbox.getWidth()/scale)+20), cropbox.getHeight()-((cropbox.getWidth()/scale)+10));		        	
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("topleft")) {
    		        imageqr.setAbsolutePosition(cropbox.getBottom()+12, cropbox.getHeight()-((cropbox.getWidth()/scale)+10));		        	
    	        } 
    	        if(rest.get(0).getdoc_qr_position().equals("topmid")) {
    		        imageqr.setAbsolutePosition((cropbox.getWidth()/2)-(((cropbox.getWidth()/scale)/2)), cropbox.getHeight()-((cropbox.getWidth()/scale)+10));
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("coordinate")) {
    	        	imageqr.setAbsolutePosition(Float.parseFloat(rest.get(0).getdoc_qr_x()),Float.parseFloat(rest.get(0).getdoc_qr_y()));
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("custom")) {
    	        	imageqr.setAbsolutePosition(Float.parseFloat(rest.get(0).getdoc_qr_x()),Float.parseFloat(rest.get(0).getdoc_qr_y()));
    	        }
	        	content = stamper.getOverContent(Integer.valueOf(qrpage));
	        	content.addImage(imageqr);
        	}
	        stamper.close();
			File file = new File(home.rootPath()+"/"+rest.get(0).getdoc_file_path().replace(".pdf", "_stamp.pdf"));		
	        InputStreamResource resource;
	        String ext = "application/pdf";
	        
			resource = new InputStreamResource(new FileInputStream(file));
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + file.getName())
	                .contentType(MediaType.parseMediaType(ext))
	                .contentLength(file.length()) //
	                .body(resource);	        
		}catch(Exception e) {
			return null;
		}
		
	}

	@Override
	public String signEsignDoc(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";

        String sql = "SELECT doc_id, doc_code, doc_desc, doc_type, doc_number, TO_CHAR(doc_date_start,'DD-MM-YYYY') doc_date_start, TO_CHAR(doc_date_end,'DD-MM-YYYY') doc_date_end, doc_file, doc_file_path, doc_file_sign, doc_file_sign_path, doc_file_page, doc_file_sign_id, doc_by, doc_qr_page, doc_qr_position, doc_qr_x, doc_qr_y, doc_qr_size, doc_sign_use, doc_sign_page, doc_sign_size, doc_sign_x, doc_sign_y, doc_status, user_create, TO_CHAR(date_create,'DD-MM-YYYY HH24:MI:SS') date_create, user_update, TO_CHAR(last_update,'DD-MM-YYYY HH24:MI:SS') last_update "
    			+ "FROM portal.tx_esign_doc WHERE doc_id = ?";
        List<EsignDoc> rest = portalTemplate.query(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, Integer.valueOf(request.getParameter("doc_id")));
			}
        }, new EsignDocMapper());
		
        String [] file  = rest.get(0).getdoc_file_path().split("/");
        String filesign = file[2].replace(".pdf", "_stamp_signed.pdf");
        String qrcontent = home.baseUrl(request)+"/index.jsp?mod=verifypdf&file="+filesign;
		home.qrcode(qrcontent);
		
	    float added = 0;
	    if(Integer.valueOf(rest.get(0).getdoc_qr_size())<=60) {
	    	added = 70-Integer.valueOf(rest.get(0).getdoc_qr_size());
	    }
	    float percent = (((100-Integer.valueOf(rest.get(0).getdoc_qr_size()))/10)*10)+((100-Integer.valueOf(rest.get(0).getdoc_qr_size()))/10)+added;
		float scale = ((60+percent)/10);
		String srcPdf = home.rootPath()+"/"+rest.get(0).getdoc_file_path();
		String destPdf = home.rootPath()+"/"+rest.get(0).getdoc_file_path().replace(".pdf", "_stamp_signed.pdf");
		String imagePath = home.root()+"classes/static/assets/apps/images/qrcode.png";
		//String imagePath = home.root()+"assets/apps/images/qrcode.png";
		String blankPath = home.root()+"classes/static/assets/apps/images/blank.png";
		//String blankPath = home.root()+"assets/apps/images/blank.png";
		
		
		try {
			PdfReader readers = new PdfReader(srcPdf);
	        PdfStamper stamper;
	        
			stamper = new PdfStamper(readers, new FileOutputStream(destPdf));
			Rectangle cropbox = readers.getCropBox(1);
					  
	        String qrpage = rest.get(0).getdoc_qr_page();
	        PdfContentByte content;
        	int n = readers.getNumberOfPages();
        	if(qrpage.contains(",")) {
        		String [] qrpagearr = qrpage.split(",");
        		String [] doc_qr_xarr = rest.get(0).getdoc_qr_x().split(",");
        		String [] doc_qr_yarr = rest.get(0).getdoc_qr_y().split(",");
        		for(int p = 0; p<qrpagearr.length; p++) {
        			if(Integer.valueOf(qrpagearr[p].trim())<=n) {
            			Image imageqr = Image.getInstance(imagePath.replaceAll("upload/", ""));
            	        imageqr.scaleAbsolute((cropbox.getWidth()/scale), (cropbox.getWidth()/scale));
            	        
            	        if(rest.get(0).getdoc_qr_position().equals("bottomright")) {
            		        imageqr.setAbsolutePosition(cropbox.getWidth()-((cropbox.getWidth()/scale)+20), cropbox.getBottom()+20);		        	
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("bottomleft")) {
            		        imageqr.setAbsolutePosition(cropbox.getBottom()+15, cropbox.getBottom()+20);
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("bottommid")) {
            		        imageqr.setAbsolutePosition((cropbox.getWidth()/2)-(((cropbox.getWidth()/scale)/2)), cropbox.getBottom()+20);
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("topright")) {
            		        imageqr.setAbsolutePosition(cropbox.getWidth()-((cropbox.getWidth()/scale)+20), cropbox.getHeight()-((cropbox.getWidth()/scale)+10));		        	
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("topleft")) {
            		        imageqr.setAbsolutePosition(cropbox.getBottom()+12, cropbox.getHeight()-((cropbox.getWidth()/scale)+10));		        	
            	        } 
            	        if(rest.get(0).getdoc_qr_position().equals("topmid")) {
            		        imageqr.setAbsolutePosition((cropbox.getWidth()/2)-(((cropbox.getWidth()/scale)/2)), cropbox.getHeight()-((cropbox.getWidth()/scale)+10));
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("coordinate")) {
            	        	imageqr.setAbsolutePosition(Float.parseFloat(doc_qr_xarr[p]),Float.parseFloat(doc_qr_yarr[p]));
            	        }
            	        if(rest.get(0).getdoc_qr_position().equals("custom")) {
            	        	imageqr.setAbsolutePosition(Float.parseFloat(doc_qr_xarr[p]),Float.parseFloat(doc_qr_yarr[p]));
            	        }      		
			        	content = stamper.getOverContent(Integer.valueOf(qrpagearr[p].trim())); 
			        	content.addImage(imageqr);		        				        			
        			}
        		}
        	}else {
    			Image imageqr = Image.getInstance(imagePath.replaceAll("upload/", ""));
    	        imageqr.scaleAbsolute((cropbox.getWidth()/scale), (cropbox.getWidth()/scale));
    	        
    	        if(rest.get(0).getdoc_qr_position().equals("bottomright")) {
    		        imageqr.setAbsolutePosition(cropbox.getWidth()-((cropbox.getWidth()/scale)+20), cropbox.getBottom()+20);		        	
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("bottomleft")) {
    		        imageqr.setAbsolutePosition(cropbox.getBottom()+15, cropbox.getBottom()+20);
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("bottommid")) {
    		        imageqr.setAbsolutePosition((cropbox.getWidth()/2)-(((cropbox.getWidth()/scale)/2)), cropbox.getBottom()+20);
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("topright")) {
    		        imageqr.setAbsolutePosition(cropbox.getWidth()-((cropbox.getWidth()/scale)+20), cropbox.getHeight()-((cropbox.getWidth()/scale)+10));		        	
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("topleft")) {
    		        imageqr.setAbsolutePosition(cropbox.getBottom()+12, cropbox.getHeight()-((cropbox.getWidth()/scale)+10));		        	
    	        } 
    	        if(rest.get(0).getdoc_qr_position().equals("topmid")) {
    		        imageqr.setAbsolutePosition((cropbox.getWidth()/2)-(((cropbox.getWidth()/scale)/2)), cropbox.getHeight()-((cropbox.getWidth()/scale)+10));
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("coordinate")) {
    	        	imageqr.setAbsolutePosition(Float.parseFloat(rest.get(0).getdoc_qr_x()),Float.parseFloat(rest.get(0).getdoc_qr_y()));
    	        }
    	        if(rest.get(0).getdoc_qr_position().equals("custom")) {
    	        	imageqr.setAbsolutePosition(Float.parseFloat(rest.get(0).getdoc_qr_x()),Float.parseFloat(rest.get(0).getdoc_qr_y()));
    	        }
	        	content = stamper.getOverContent(Integer.valueOf(qrpage));
	        	content.addImage(imageqr);
        		
        	}      
	        stamper.close();
	        
	        final TrustManager[] trustAllCerts = new TrustManager[] {
			        new X509TrustManager() {
			          @Override
			          public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
			          }

			          @Override
			          public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
			          }

			          @Override
			          public java.security.cert.X509Certificate[] getAcceptedIssuers() {
			            return new java.security.cert.X509Certificate[]{};
			          }
			        }
			    };
	        
		    SSLContext sc;
			try {
				sc = SSLContext.getInstance("SSL");
		        try {
					sc.init(null, trustAllCerts, new java.security.SecureRandom());
					final SSLSocketFactory sslSocketFactory = sc.getSocketFactory();
					OkHttpClient.Builder builder = new OkHttpClient.Builder();
				    builder.sslSocketFactory(sslSocketFactory, (X509TrustManager)trustAllCerts[0]);
				    builder.hostnameVerifier(new HostnameVerifier() {
					      @Override
					      public boolean verify(String hostname, SSLSession session) {
					        return true;
					      }
				    });				    

		    		final int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
			        String sqlUserNik = "select a.esign_nik "
	        				  		  + "from portal.tx_esign a where a.user_id=?";
					@SuppressWarnings("deprecation")
					String nik = portalTemplate.queryForObject(sqlUserNik, new Object[]{user_id}, String.class);
					
					OkHttpClient client = builder.build();
					RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
			                .addFormDataPart("nik", nik)
			                .addFormDataPart("passphrase", request.getParameter("passphrase"))
			                .addFormDataPart("tampilan", "visible")
			                .addFormDataPart("page", "1")
			                .addFormDataPart("image", "true")
			                .addFormDataPart("xAxis", "1")
			                .addFormDataPart("yAxis", "1")
			                .addFormDataPart("width", "1")
			                .addFormDataPart("height", "1")
			                .addFormDataPart("reason", "Otentikasi Dokumen")
			                .addFormDataPart("location", "Kementerian Perhubungan. Jl. Medan Merdeka Barat No. 8. Jakarta Pusat. DKI Jakarta")
			                .addFormDataPart("file", filesign,
			                        RequestBody.create(
			                            okhttp3.MediaType.get("application/pdf"),
			                            new File(destPdf)))
			                .addFormDataPart("imageTTD", "blank.png",
			                        RequestBody.create(
			                            okhttp3.MediaType.get("image/png"),
			                            new File(blankPath)))
			                .build();
					
					Request requests = new Request.Builder()
							  .url(home.getInitByName("esign_url")+"/api/sign/pdf")
							  .method("POST", body)
							  .addHeader("Authorization", home.getInitByName("esign_authorization"))
							  .build();
					Response respon = client.newCall(requests).execute();
					String id_dokumen = respon.header("id_dokumen");
					if(id_dokumen==null) {
						code    = "02";
						status  = "failed";
						message = "Passphrase yang Anda masukkan salah.";						
					}else {
						FileOutputStream fos = new FileOutputStream(destPdf);
						fos.write(respon.body().bytes());
					    fos.close();	
					    
				        String sqUpdate = "update portal.tx_esign_doc set doc_status = '2', doc_file_sign = ?, doc_file_sign_path = ?, doc_file_sign_id = ? where doc_id = ?";
					    int updates = portalTemplate.update(sqUpdate, new PreparedStatementSetter() {
										              public void setValues(PreparedStatement ps) throws SQLException {
										            	  ps.setString(1, file[1]+"/"+filesign);
										            	  ps.setString(2, "files/"+rest.get(0).getdoc_file_path().replace(".pdf", "_stamp_signed.pdf"));
										            	  ps.setString(3, id_dokumen);
										            	  ps.setInt(4, Integer.valueOf(request.getParameter("doc_id")));
										              }
									   });
					    if(updates>0) {
					    	home.writelog(request, "Tanda Tangan Digital ("+rest.get(0).getdoc_number()+")");
		    				code = "01";
		    				status = "success";
		    				message = "Tanda Tangan Digital Berhasil";					    	
					    }
					}
		        }catch(Exception e) {
    				code = "02";
    				status = "failed";
    				message = e.toString();					    	
		        }
			}catch(Exception e) {
				code = "02";
				status = "failed";
				message = e.toString();					    		        	
			}
		}catch(Exception e) {
			code = "02";
			status = "failed";
			message = e.toString();					    				
		}
		
		return   "{"+
				"\"code\":\""+code+"\","+
				"\"status\":\""+status+"\","+
				"\"message\":\""+message+"\""+
			 "}";	
		
	}

	@Override
	public String deleteEsignDoc(HttpServletRequest request) {
		String code    = "02";
		String status  = "failed";
		String message = "Terjadi kesalahan sistem";
		
	    String sql        = "select a.doc_number "
 		 		 		  + "from portal.tx_esign_doc a where a.doc_id=?";
		@SuppressWarnings("deprecation")
		String role_name  = portalTemplate.queryForObject(sql, new Object[]{Integer.valueOf(request.getParameter("id"))}, String.class);

        String sqlUser = "UPDATE portal.tx_esign_doc SET doc_status='9' WHERE doc_id=?";
	    int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
							              public void setValues(PreparedStatement ps) throws SQLException {
							            	  ps.setInt(1, Integer.valueOf(request.getParameter("id")));
							              }
						    });
	
	     if(rowInsertUser>0) {
				code    = "01";
				status  = "success";
				message = "Data berhasil dihapus";		    	 
				home.writelog(request, "Hapus Dokumen Digital Signature ("+role_name+")");
	     }			
		 return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";
	}

}
