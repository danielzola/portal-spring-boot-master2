package id.go.dephub.hubla.sehati.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import id.go.dephub.hubla.sehati.dao.HomeDao;

@Service
public class RegisterServiceImp implements RegisterService {
	
	@Autowired
	@Qualifier("portalJdbcTemplate")
	private JdbcTemplate portalTemplate;
	
	@Autowired
	HomeDao home;

	@Value("${apis.url}")
	private String apisUrl;

	@Value("${activation.url}")
	private String activationUrl;

	@Value("${resetpass.url}")
	private String resetpassUrl;
	
	@Override
	public String SetRegister(HttpServletRequest request) {
		String code 	= "02";
		String status 	= "failed";
		String message 	= "Terjadi kesalahan sistem. Silakan hubungi Administrator.";
		
		String username = request.getParameter("username");
		String sqlcek = "select count(a.user_id) user_id "
				 	  + "from portal.tm_users a where a.user_name=?";
		@SuppressWarnings("deprecation")
		int cek = portalTemplate.queryForObject(sqlcek, new Object[]{username}, Integer.class);
		if(cek<1) {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("Authorization", "Basic "+home.getInitByName("api_basic_auth"));
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("grant_type","client_credentials");
			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
			ResponseEntity<String> responseToken = restTemplate.exchange(apisUrl+"/token", HttpMethod.POST, entity, String.class);
			String jsonToken		 = responseToken.getBody();
			JSONObject dataToken 	 = new JSONObject(jsonToken.toString());
			String access_token		 = dataToken.getString("access_token");
			
			//Check Pegawai
			RestTemplate apiPegawai = new RestTemplate();
			HttpHeaders headerPegawai = new HttpHeaders();
			headerPegawai.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headerPegawai.add("Authorization", "Bearer "+access_token);
			HttpEntity<MultiValueMap<String,String>> entityPegawai = new HttpEntity<>(headerPegawai);
			ResponseEntity<String> responsePegawai = apiPegawai.exchange(apisUrl+"/data/pegawai?l=100&o=0&k=nip&q="+username.replace(" ", ""), HttpMethod.GET, entityPegawai, String.class);
			String jsonPegawai 		 = responsePegawai.getBody();
			JSONObject objPegawai 	 = new JSONObject(jsonPegawai);
			JSONArray arrPegawai = objPegawai.getJSONArray("data");
			int jmlPegawai = arrPegawai.length();
			if(jmlPegawai>0) {			
				JSONObject dataPegawai = arrPegawai.getJSONObject(0);
				String nip = dataPegawai.getString("nip");
				String email = dataPegawai.getString("email");
				String nama = dataPegawai.getString("nama");
				String no_telp = ((dataPegawai.has("no_telp") && !dataPegawai.isNull("no_telp")) ? dataPegawai.getString("no_telp") : "");
				String foto = ((dataPegawai.has("foto") && !dataPegawai.isNull("foto")) ? dataPegawai.getString("foto") : "");
				String tanggal_lahir = dataPegawai.getString("tanggal_lahir");
				String jabatan_fungsional = ((dataPegawai.has("jabatan_fungsional") && !dataPegawai.isNull("jabatan_fungsional")) ? dataPegawai.getString("jabatan_fungsional") : "");
				String pangkat = ((dataPegawai.has("pangkat") && !dataPegawai.isNull("pangkat")) ? dataPegawai.getString("pangkat") : "");
				String kode_kantor = dataPegawai.getString("kode_kantor");
				String golongan = ((dataPegawai.has("golongan") && !dataPegawai.isNull("golongan")) ? dataPegawai.getString("golongan") : "");
				String tmt_golongan = ((dataPegawai.has("tmt_golongan") && !dataPegawai.isNull("tmt_golongan")) ? dataPegawai.getString("tmt_golongan") : "");
				String jenis_kelamin = dataPegawai.getString("jenis_kelamin");
				String kantor = dataPegawai.getString("kantor");
				String kode_unit = dataPegawai.getString("kode_unit");
				String jabatan_struktural = ((dataPegawai.has("jabatan_struktural") && !dataPegawai.isNull("jabatan_struktural")) ? dataPegawai.getString("jabatan_struktural") : "");
				String unit = dataPegawai.getString("unit");
				if(email == null || email.length()==0) {
					return   "{"+
							"\"code\":\"02\","+
							"\"status\":\"failed\","+
							"\"message\":\"Mohon Maaf. Silahkan perbarui email Anda di Sistem Informasi Kepegawaian.\""+
						 "}";					
				}else {
			        String sql = "select count(user_id) from portal.tm_users a where a.user_name=?";
					@SuppressWarnings("deprecation")
					int pegawai = portalTemplate.queryForObject(sql, new Object[]{nip}, Integer.class);
			        if(pegawai<1) {
			        	
				        String sqlUserId = "select coalesce(max(a.user_id),0) user_id "
						        		 + "from portal.tm_users a";
				        int getuser_id = portalTemplate.queryForObject(sqlUserId, Integer.class);
				        	getuser_id = getuser_id+1;
				        final int user_id = getuser_id;
				        
				        String sqlEmpId = "select coalesce(max(a.emp_id),0) emp_id "
				        				+ "from portal.tm_employee a";			        
				        int getemp_id = portalTemplate.queryForObject(sqlEmpId, Integer.class);
				        	getemp_id = getemp_id+1;
				        final int emp_id = getemp_id;
				        
				        String sqlUser = "INSERT INTO portal.tm_users (user_id, user_name, user_password, user_type, user_fullname, user_hp, user_email, user_photo, user_status, user_create, date_create) "
						        	   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
				        int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
										              public void setValues(PreparedStatement ps) throws SQLException {
										            	  ps.setInt(1, user_id);
										            	  ps.setString(2, nip);
										            	  ps.setString(3, home.getSecure(nip));
										            	  ps.setString(4, "1");
										            	  ps.setString(5, nama);
										            	  ps.setString(6, no_telp);
										            	  ps.setString(7, email);
										            	  ps.setString(8, foto);
										            	  ps.setString(9, "2");
										            	  ps.setInt(10, user_id);
										              }
									        });
		
				        if(rowInsertUser>0) {
					        String sqlRole  = "INSERT INTO portal.td_users_roles "
						        	   		+ "(user_id, role_id) "
						        	   		+ "VALUES(?, ?)";
					        int rowInsertRole = portalTemplate.update(sqlRole, new PreparedStatementSetter() {
											              public void setValues(PreparedStatement ps) throws SQLException {
											            	  ps.setInt(1, user_id);
											            	  ps.setInt(2, 2);
											              }
										        });
				        	
					        String sqlEmp  = "INSERT INTO portal.tm_employee "
							        	   + "(emp_id, user_id, emp_name, emp_number, emp_birth_date, emp_gender, unit_code, unit_name, office_code, office_name, group_date, group_name, grade_name, position_name) "
							        	   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					        int rowInsertEmp = portalTemplate.update(sqlEmp, new PreparedStatementSetter() {
											              public void setValues(PreparedStatement ps) throws SQLException {
											            	  ps.setInt(1, emp_id);
											            	  ps.setInt(2, user_id);
											            	  ps.setString(3, nama);
											            	  ps.setString(4, nip);
											            	  ps.setDate(5, Date.valueOf(tanggal_lahir));
											            	  ps.setString(6, jenis_kelamin);
											            	  ps.setString(7, kode_unit);
											            	  ps.setString(8, unit);
											            	  ps.setString(9, kode_kantor);
											            	  ps.setString(10, kantor);
											            	  ps.setDate(11, Date.valueOf(tmt_golongan));
											            	  ps.setString(12, golongan);
											            	  ps.setString(13, pangkat);
											            	  ps.setString(14, (jabatan_fungsional == null || jabatan_fungsional.length()==0 ? jabatan_struktural : jabatan_fungsional));
											              }
										        });
				        	if(rowInsertEmp>0 && rowInsertRole>0) {
				        		
				        		String reset_key = home.getSecure(user_id+username+home.getNow("yyyy-MM-dd HH:mm:ss"));	
				        		String sqlReset = "INSERT INTO portal.tx_reset "
						        				+ "(user_id, reset_key, reset_expired, reset_status, reset_date) "
						        				+ "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta' + INTERVAL '1 DAYS', ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
				        		int rowInsertReset = portalTemplate.update(sqlReset, new PreparedStatementSetter() {
						              public void setValues(PreparedStatement ps) throws SQLException {
						            	  ps.setInt(1, user_id);
						            	  ps.setString(2, reset_key);
						            	  ps.setString(3, "0");
						              }
				        		});
				        		if(rowInsertReset>0) {
				        			
							        String sqlLog  = "INSERT INTO portal.tx_log "
									        	   + "(user_id, log_desc, log_date, log_ip) "
									        	   + "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta', ?)";
							        portalTemplate.update(sqlLog, new PreparedStatementSetter() {
													              public void setValues(PreparedStatement ps) throws SQLException {
													            	  ps.setInt(1, user_id);
													            	  ps.setString(2, "Registrasi Akun");
													            	  ps.setString(3, home.getClientIp(request));
													              }
												          });
				        			
					        		RestTemplate apiSendNotif = new RestTemplate();
					    			HttpHeaders headerSendNotif = new HttpHeaders();
					    			headerSendNotif.setContentType(MediaType.APPLICATION_JSON);
					    			headerSendNotif.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
					    			headerSendNotif.add("Authorization", "Bearer "+access_token);			
					    			Map<String, Object> mapSendNotif = new HashMap<>();
					    			mapSendNotif.put("username", username);
					    			
					    			Map<String, Object> mapSendNotifEmail = new HashMap<>();
					    			mapSendNotifEmail.put("subject", "Aktivasi Akun SEHATI");
					    			mapSendNotifEmail.put("message", "<table><tr><td><img width=\"70\" src=\""+"https://sehati.hubla.dephub.go.id/assets/public/images/logo/logo-kemenhub.png\"></td><td style=\"padding-left:10px;\"><div style=\"font-size:16px;\"><b>SEHATI</b></div><div>Sistem Elektronik Hubla Terintegrasi 2.0<div><div style=\"font-size:14px;\">Direktorat Jenderal Perhubungan Laut</div><div style=\"font-size:16px;\">Kementerian Perhubungan Republik Indonesia<div></td><tr></table><hr><div style=\"width:100%; text-align:left;\"><div><b>Selamat datang di Aplikasi SEHATI</b></div>Untuk mengaktifkan akun klik tautan dibawah ini <br> <a style=\"font-size:20px; font-weight:bold;\" target=\"_blank\" href=\""+activationUrl+"/"+reset_key+"\">Aktifkan Akun</a></div>");
					    			mapSendNotif.put("email", mapSendNotifEmail);
					    							    			
					    			HttpEntity<Map<String, Object>> entitySendNotif = new HttpEntity<>(mapSendNotif, headerSendNotif);
					    			ResponseEntity<String> responseSendNotif = apiSendNotif.postForEntity(apisUrl+"/notif/send", entitySendNotif, String.class);
					    			String jsonSendNotif 		 = responseSendNotif.getBody();
					    			JSONObject objSendNotif 	 = new JSONObject(jsonSendNotif);
					    			if(objSendNotif.getString("status").equals("001")) {
					    				code = "01";
					    				status = "success";
					    				message = "Terima Kasih. Untuk mengaktifkan akun silahkan klik tautan yang telah dikirimkan ke email dinas Anda";
					    			}
				        		}
				        	}
				        }
						return   "{"+
									"\"code\":\""+code+"\","+
									"\"status\":\""+status+"\","+
									"\"message\":\""+message+"\""+
								 "}";						        	
			        }else {
			        	
				        String sqlUserStatus = "select a.user_status "
		        		 		 			 + "from portal.tm_users a where a.user_name=?";
				        @SuppressWarnings("deprecation")
						String user_status = portalTemplate.queryForObject(sqlUserStatus, new Object[]{nip}, String.class);
				        
				        if(user_status.equals("2")) {
					        String sqlUserId = "select a.user_id "
					        				 + "from portal.tm_users a where a.user_name=?";
					        @SuppressWarnings("deprecation")
							final int user_id = portalTemplate.queryForObject(sqlUserId, new Object[]{nip}, Integer.class);
					        
					        String sqlRole  = "INSERT INTO portal.td_users_roles "
				        	   		+ "(user_id, role_id) "
				        	   		+ "VALUES(?, ?)";
					        int rowInsertRole = portalTemplate.update(sqlRole, new PreparedStatementSetter() {
											              public void setValues(PreparedStatement ps) throws SQLException {
											            	  ps.setInt(1, user_id);
											            	  ps.setInt(2, 2);
											              }
										        });
	
				       		String reset_key = home.getSecure(user_id+username+home.getNow("yyyy-MM-dd HH:mm:ss"));	
				       		String sqlReset = "INSERT INTO portal.tx_reset "
						        				+ "(user_id, reset_key, reset_expired, reset_status, reset_date) "
						        				+ "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta' + INTERVAL '1 DAYS', ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
				       		int rowInsertReset = portalTemplate.update(sqlReset, new PreparedStatementSetter() {
						              public void setValues(PreparedStatement ps) throws SQLException {
						            	  ps.setInt(1, user_id);
						            	  ps.setString(2, reset_key);
						            	  ps.setString(3, "0");
						              }
				       		});
			        		if(rowInsertRole>0 && rowInsertReset>0) {		        			
				        		RestTemplate apiSendNotif = new RestTemplate();
				    			HttpHeaders headerSendNotif = new HttpHeaders();
				    			headerSendNotif.setContentType(MediaType.APPLICATION_JSON);
				    			headerSendNotif.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				    			headerSendNotif.add("Authorization", "Bearer "+access_token);			
				    			Map<String, Object> mapSendNotif = new HashMap<>();
				    			mapSendNotif.put("username", username);
				    			
				    			Map<String, Object> mapSendNotifEmail = new HashMap<>();
				    			mapSendNotifEmail.put("subject", "Aktifasi Akun SEHATI");
				    			mapSendNotifEmail.put("message", "<table><tr><td><img width=\"70\" src=\""+"https://sehati.hubla.dephub.go.id/assets/public/images/logo/logo-kemenhub.png\"></td><td style=\"padding-left:10px;\"><div style=\"font-size:16px;\"><b>SEHATI</b></div><div>Sistem Elektronik Hubla Terintegrasi 2.0<div><div style=\"font-size:14px;\">Direktorat Jenderal Perhubungan Laut</div><div style=\"font-size:16px;\">Kementerian Perhubungan Republik Indonesia<div></td><tr></table><hr><div style=\"width:100%; text-align:left;\"><div><b>Selamat datang di Aplikasi SEHATI</b></div>Untuk mengaktifkan akun klik tautan dibawah ini <br> <a style=\"font-size:20px; font-weight:bold;\" target=\"_blank\" href=\""+activationUrl+"/"+reset_key+"\">Aktifkan Akun</a></div>");
				    			mapSendNotif.put("email", mapSendNotifEmail);
				    							    			
				    			HttpEntity<Map<String, Object>> entitySendNotif = new HttpEntity<>(mapSendNotif, headerSendNotif);
				    			ResponseEntity<String> responseSendNotif = apiSendNotif.postForEntity(apisUrl+"/notif/send", entitySendNotif, String.class);
				    			String jsonSendNotif 		 = responseSendNotif.getBody();
				    			JSONObject objSendNotif 	 = new JSONObject(jsonSendNotif);
				    			if(objSendNotif.getString("status").equals("001")) {
				    				code = "01";
				    				status = "success";
				    				message = "Terima Kasih. Untuk mengaktifkan akun silahkan klik tautan yang telah dikirimkan ke email dinas Anda";
				    			}
			        		}
							return   "{"+
										"\"code\":\""+code+"\","+
										"\"status\":\""+status+"\","+
										"\"message\":\""+message+"\""+
									 "}";						        	
			        		
				        }else {
							return   "{"+
									"\"code\":\"02\","+
									"\"status\":\"failed\","+
									"\"message\":\"NIP Anda sudah terdaftar, silahkan login.\""+
								 "}";						        				        	
				        }
			        	
			        }
				}
			}else {

				// belum ada di tm_users dan bukan pegawai
				
				JSONObject requestOSS = new JSONObject();
				requestOSS.put("nib", username);
				HttpHeaders headersOSS = new HttpHeaders();
				headersOSS.setContentType(MediaType.APPLICATION_JSON);
				headersOSS.add("Authorization", "Bearer "+access_token);
				HttpEntity<String> entityOSS = new HttpEntity<String>(requestOSS.toString(), headersOSS);
	
				// send request and parse result
				try{
					ResponseEntity<String> responseOSS = restTemplate.exchange(apisUrl+"/oss/inquirynib", HttpMethod.POST, entityOSS, String.class);
					String jsonOSS 		 	= responseOSS.getBody();
					JSONObject objOSS 		= new JSONObject(jsonOSS);
					JSONObject dataOSS 		= objOSS.getJSONObject("data");

					if(objOSS.getString("status").toString().equals("1")) {

						String sqlEmpId = "select coalesce(max(a.company_id),0) company_id "
								+ "from portal.tm_company a";
						int getemp_id = portalTemplate.queryForObject(sqlEmpId, Integer.class);
						getemp_id = getemp_id+1;
						final int emp_id = getemp_id;

						String sqlUserId = "select coalesce(max(a.user_id),0) user_id "
								+ "from portal.tm_users a";
						int getuser_id = portalTemplate.queryForObject(sqlUserId, Integer.class);
						getuser_id = getuser_id+1;
						final int user_id = getuser_id;

						String emailperusahaan = "";
						if(dataOSS.has("email_perusahaan") && !dataOSS.isNull("email_perusahaan")) {
							emailperusahaan = dataOSS.getString("email_perusahaan").toString();
						}else {
							emailperusahaan = dataOSS.getString("email_user_proses").toString();
						}
						final String email_perusahaan = emailperusahaan;

						String sqlUser = "INSERT INTO portal.tm_users (user_id, user_name, user_password, user_type, user_fullname, user_hp, user_email, user_status, user_create, date_create) "
								+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
						int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
							public void setValues(PreparedStatement ps) throws SQLException {
								ps.setInt(1, user_id);
								ps.setString(2, username);
								ps.setString(3, home.getSecure(username));
								ps.setString(4, "2");
								ps.setString(5, dataOSS.getString("nama_user_proses").toString());
								ps.setString(6, dataOSS.getString("hp_user_proses").toString());
								ps.setString(7, email_perusahaan);
								ps.setString(8, "2");
								ps.setInt(9, user_id);
							}
						});

						if(rowInsertUser>0) {

							String sqlRole  = "INSERT INTO portal.td_users_roles "
									+ "(user_id, role_id) "
									+ "VALUES(?, ?)";
							int rowInsertRole = portalTemplate.update(sqlRole, new PreparedStatementSetter() {
								public void setValues(PreparedStatement ps) throws SQLException {
									ps.setInt(1, user_id);
									ps.setInt(2, 3);
								}
							});

							String tipeperusahaan = "";
							String jenisperseroan = "";
							if(dataOSS.has("jenis_perseroan") && !dataOSS.isNull("jenis_perseroan")) {
								jenisperseroan = dataOSS.getString("jenis_perseroan").toString();
								if(dataOSS.getString("jenis_perseroan").toString().equals("01")) {
									tipeperusahaan = "PT ";
								}
								if(dataOSS.getString("jenis_perseroan").toString().equals("02")) {
									tipeperusahaan = "CV ";
								}
							}
							final String tipe_perusahaan = tipeperusahaan;
							final String jenis_perseroan = jenisperseroan;

							String alamatperseroan = "";
							if(dataOSS.has("alamat_perseroan") && !dataOSS.isNull("alamat_perseroan")) {
								alamatperseroan = dataOSS.getString("alamat_perseroan").toString();
							}else {
								alamatperseroan = dataOSS.getString("alamat").toString();
							}
							final String alamat_perseroan = alamatperseroan;


							String sqlEmp  = "INSERT INTO portal.tm_company "+
									"(user_id, company_id, company_nib, company_name, company_address, company_phone, company_email, company_taxno, company_type) "+
									"VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
							int rowInsertEmp = portalTemplate.update(sqlEmp, new PreparedStatementSetter() {
								public void setValues(PreparedStatement ps) throws SQLException {
									ps.setInt(1, user_id);
									ps.setInt(2, emp_id);
									ps.setString(3, username);
									ps.setString(4, tipe_perusahaan+dataOSS.getString("nama_perseroan").toString());
									ps.setString(5, alamat_perseroan);
									ps.setString(6, dataOSS.getString("nomor_telpon_perseroan").toString());
									ps.setString(7, email_perusahaan);
									ps.setString(8, dataOSS.getString("npwp_perseroan").toString());
									ps.setString(9, jenis_perseroan);
								}
							});
							if(rowInsertEmp>0 && rowInsertRole>0) {

								String reset_key = home.getSecure(user_id+username+home.getNow("yyyy-MM-dd HH:mm:ss"));
								String sqlReset = "INSERT INTO portal.tx_reset "
										+ "(user_id, reset_key, reset_expired, reset_status, reset_date) "
										+ "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta' + INTERVAL '1 DAYS', ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
								int rowInsertReset = portalTemplate.update(sqlReset, new PreparedStatementSetter() {
									public void setValues(PreparedStatement ps) throws SQLException {
										ps.setInt(1, user_id);
										ps.setString(2, reset_key);
										ps.setString(3, "0");
									}
								});
								if(rowInsertReset>0) {

									String sqlLog  = "INSERT INTO portal.tx_log "
											+ "(user_id, log_desc, log_date, log_ip) "
											+ "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta', ?)";
									portalTemplate.update(sqlLog, new PreparedStatementSetter() {
										public void setValues(PreparedStatement ps) throws SQLException {
											ps.setInt(1, user_id);
											ps.setString(2, "Registrasi Akun");
											ps.setString(3, home.getClientIp(request));
										}
									});

									RestTemplate apiSendNotif = new RestTemplate();
									HttpHeaders headerSendNotif = new HttpHeaders();
									headerSendNotif.setContentType(MediaType.APPLICATION_JSON);
									headerSendNotif.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
									headerSendNotif.add("Authorization", "Bearer "+access_token);
									Map<String, Object> mapSendNotif = new HashMap<>();
									mapSendNotif.put("username", username);

									Map<String, Object> mapSendNotifEmail = new HashMap<>();
									mapSendNotifEmail.put("subject", "Aktivasi Akun SEHATI");
									mapSendNotifEmail.put("message", "<table><tr><td><img width=\"70\" src=\""+"https://sehati.hubla.dephub.go.id/assets/public/images/logo/logo-kemenhub.png\"></td><td style=\"padding-left:10px;\"><div style=\"font-size:16px;\"><b>SEHATI</b></div><div>Sistem Elektronik Hubla Terintegrasi 2.0<div><div style=\"font-size:14px;\">Direktorat Jenderal Perhubungan Laut</div><div style=\"font-size:16px;\">Kementerian Perhubungan Republik Indonesia<div></td><tr></table><hr><div style=\"width:100%; text-align:left;\"><div><b>Selamat datang di Aplikasi SEHATI</b></div>Untuk mengaktifkan akun klik tautan dibawah ini <br> <a style=\"font-size:20px; font-weight:bold;\" target=\"_blank\" href=\""+activationUrl+"/"+reset_key+"\">Aktifkan Akun</a></div>");
									mapSendNotif.put("email", mapSendNotifEmail);

									HttpEntity<Map<String, Object>> entitySendNotif = new HttpEntity<>(mapSendNotif, headerSendNotif);
									ResponseEntity<String> responseSendNotif = apiSendNotif.postForEntity(apisUrl+"/notif/send", entitySendNotif, String.class);
									String jsonSendNotif 		 = responseSendNotif.getBody();
									JSONObject objSendNotif 	 = new JSONObject(jsonSendNotif);
									if(objSendNotif.getString("status").equals("001")) {
										code = "01";
										status = "success";
										message = "Terima Kasih. Untuk mengaktifkan akun silahkan klik tautan yang telah dikirimkan ke email Anda";
									}
								}
							}
						}

//					JSONObject requestregisterNib = new JSONObject();
//					requestregisterNib.put("username", username);
//					requestregisterNib.put("password", username); //requestregisterNib.put("password", "admin1");
//					HttpHeaders headersregisterNib = new HttpHeaders();
//					headersregisterNib.setContentType(MediaType.APPLICATION_JSON);
//					headersregisterNib.add("Authorization", "Bearer "+access_token);
//					HttpEntity<String> entityregisterNib = new HttpEntity<String>(requestregisterNib.toString(), headersregisterNib);
//
//					// send request and parse result
//					ResponseEntity<String> responseregisterNib = restTemplate.exchange(apisUrl+"/sso/registerNib", HttpMethod.POST, entityregisterNib, String.class);
//					String jsonregisterNib 		 	= responseregisterNib.getBody();
//					JSONObject objregisterNib 		= new JSONObject(jsonregisterNib);
//
//					if(objregisterNib.getString("status").toString().equals("1")) {
//
//						// NOOP
//					}

						return   "{"+
								"\"code\":\""+code+"\","+
								"\"status\":\""+status+"\","+
								"\"message\":\""+message+"\""+
								"}";

					}else {
						return   "{"+
								"\"code\":\"02\","+
								"\"status\":\"failed\","+
								"\"message\":\"Mohon Maaf. NIB atau NIP yang Anda masukkan tidak terdaftar. \""+
								"}";
					}
				} catch (Exception e) {
					e.printStackTrace();
					return   "{"+
							"\"code\":\"02\","+
							"\"status\":\"failed\","+
							"\"message\":\"Mohon Maaf. NIB atau NIP yang Anda masukkan tidak terdaftar. \""+
							"}";
				}


			}
		}else {
			
	        String sqlUserStatus = "select a.user_status "
					 			 + "from portal.tm_users a where a.user_name=?";
			@SuppressWarnings("deprecation")
			String user_status = portalTemplate.queryForObject(sqlUserStatus, new Object[]{username}, String.class);
			
			if(user_status.equals("2")) {
				
		        String sqlUserId = "select a.user_id "
						 			 + "from portal.tm_users a where a.user_name=?";
				@SuppressWarnings("deprecation")
				int user_id = portalTemplate.queryForObject(sqlUserId, new Object[]{username}, Integer.class);
				
				
        		String reset_key = home.getSecure(user_id+username+home.getNow("yyyy-MM-dd HH:mm:ss"));	
        		String sqlReset = "INSERT INTO portal.tx_reset "
		        				+ "(user_id, reset_key, reset_expired, reset_status, reset_date) "
		        				+ "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta' + INTERVAL '1 DAYS', ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
        		int rowInsertReset = portalTemplate.update(sqlReset, new PreparedStatementSetter() {
		              public void setValues(PreparedStatement ps) throws SQLException {
		            	  ps.setInt(1, user_id);
		            	  ps.setString(2, reset_key);
		            	  ps.setString(3, "0");
		              }
        		});		
        		
        		if(rowInsertReset>0) {
        			
        			RestTemplate restTemplate = new RestTemplate();
        			HttpHeaders headers = new HttpHeaders();
        			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        			headers.add("Authorization", "Basic "+home.getInitByName("api_basic_auth"));
        			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        			map.add("grant_type","client_credentials");
        			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
        			ResponseEntity<String> responseToken = restTemplate.exchange(apisUrl+"/token", HttpMethod.POST, entity, String.class);
        			String jsonToken		 = responseToken.getBody();
        			JSONObject dataToken 	 = new JSONObject(jsonToken.toString());
        			String access_token		 = dataToken.getString("access_token");

			        String sqlLog  = "INSERT INTO portal.tx_log "
					        	   + "(user_id, log_desc, log_date, log_ip) "
					        	   + "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta', ?)";
			        portalTemplate.update(sqlLog, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setInt(1, user_id);
									            	  ps.setString(2, "Registrasi Akun");
									            	  ps.setString(3, home.getClientIp(request));
									              }
								          });
        			
	        		RestTemplate apiSendNotif = new RestTemplate();
	    			HttpHeaders headerSendNotif = new HttpHeaders();
	    			headerSendNotif.setContentType(MediaType.APPLICATION_JSON);
	    			headerSendNotif.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
	    			headerSendNotif.add("Authorization", "Bearer "+access_token);			
	    			Map<String, Object> mapSendNotif = new HashMap<>();
	    			mapSendNotif.put("username", username);
	    			
	    			Map<String, Object> mapSendNotifEmail = new HashMap<>();
	    			mapSendNotifEmail.put("subject", "Aktivasi Akun SEHATI");
	    			mapSendNotifEmail.put("message", "<table><tr><td><img width=\"70\" src=\""+"https://sehati.hubla.dephub.go.id/assets/public/images/logo/logo-kemenhub.png\"></td><td style=\"padding-left:10px;\"><div style=\"font-size:16px;\"><b>SEHATI</b></div><div>Sistem Elektronik Hubla Terintegrasi 2.0<div><div style=\"font-size:14px;\">Direktorat Jenderal Perhubungan Laut</div><div style=\"font-size:16px;\">Kementerian Perhubungan Republik Indonesia<div></td><tr></table><hr><div style=\"width:100%; text-align:left;\"><div><b>Selamat datang di Aplikasi SEHATI</b></div>Untuk mengaktifkan akun klik tautan dibawah ini <br> <a style=\"font-size:20px; font-weight:bold;\" target=\"_blank\" href=\""+activationUrl+"/"+reset_key+"\">Aktifkan Akun</a></div>");
	    			mapSendNotif.put("email", mapSendNotifEmail);
	    							    			
	    			HttpEntity<Map<String, Object>> entitySendNotif = new HttpEntity<>(mapSendNotif, headerSendNotif);
	    			ResponseEntity<String> responseSendNotif = apiSendNotif.postForEntity(apisUrl+"/notif/send", entitySendNotif, String.class);
	    			String jsonSendNotif 		 = responseSendNotif.getBody();
	    			JSONObject objSendNotif 	 = new JSONObject(jsonSendNotif);
	    			if(objSendNotif.getString("status").equals("001")) {
	    				code = "01";
	    				status = "success";
	    				message = "Terima Kasih. Untuk mengaktifkan akun silahkan klik tautan yang telah dikirimkan ke email Anda";
	    			}
        		}
        		
				return   "{"+
							"\"code\":\""+code+"\","+
							"\"status\":\""+status+"\","+
							"\"message\":\""+message+"\""+
						 "}";				
				
			}else {
				return   "{"+
						"\"code\":\"02\","+
						"\"status\":\"failed\","+
						"\"message\":\"NIB/NIP sudah terdaftar. Silakan login.\""+
					 "}";
			}
		}
		
	}

	@Override
	public String SetActivate(HttpServletRequest request) {
		String code = "02";
		String status = "failed";
		String message = "Terjadi kesalahan sistem. Silakan hubungi Administrator.";
		
		String reset_key = request.getParameter("reset_key");
		String nik = request.getParameter("nik");
		String hp = request.getParameter("hp");
		String password = request.getParameter("password");
		
		if(home.standardPassword(password)) {
	        String sqlUserId = "select a.user_id "
							 + "from portal.tx_reset a where a.reset_key=?";
			@SuppressWarnings("deprecation")
			final int user_id = portalTemplate.queryForObject(sqlUserId, new Object[]{reset_key}, Integer.class);
			
	        String sqlUserName = "select a.user_name "
		 				   	   + "from portal.tm_users a where a.user_id=?";
			@SuppressWarnings("deprecation")
			String user_name = portalTemplate.queryForObject(sqlUserName, new Object[]{user_id}, String.class);
			
	        String sqlUser = "UPDATE portal.tm_users SET user_status='1', user_nik=?, user_hp=?, user_password=? WHERE user_id=?";
		    int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
								              public void setValues(PreparedStatement ps) throws SQLException {
								            	  ps.setString(1, nik);
								            	  ps.setString(2, hp);
								            	  ps.setString(3, home.getSecure(password));
								            	  ps.setInt(4, user_id);
								              }
							    });
		
		     if(rowInsertUser>0) {
		        String sqlLog  = "INSERT INTO portal.tx_log "
				        	   + "(user_id, log_desc, log_date, log_ip) "
				        	   + "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta', ?)";
		        portalTemplate.update(sqlLog, new PreparedStatementSetter() {
								              public void setValues(PreparedStatement ps) throws SQLException {
								            	  ps.setInt(1, user_id);
								            	  ps.setString(2, "Aktifasi Akun");
								            	  ps.setString(3, home.getClientIp(request));
								              }
							          });
		        
		        String sqlreset = "UPDATE portal.tx_reset SET reset_status='1' WHERE user_id=?";
			    portalTemplate.update(sqlreset, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setInt(1, user_id);
									              }
								    });

		        
		 		RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				headers.add("Authorization", "Basic "+home.getInitByName("api_basic_auth"));
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add("grant_type","client_credentials");
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
				ResponseEntity<String> responseToken = restTemplate.exchange(apisUrl+"/token", HttpMethod.POST, entity, String.class);
				String jsonToken		 = responseToken.getBody();
				JSONObject dataToken 	 = new JSONObject(jsonToken);
				String access_token		 = dataToken.getString("access_token");

				RestTemplate apiChangePassword = new RestTemplate();
				HttpHeaders headerChangePassword = new HttpHeaders();
				headerChangePassword.setContentType(MediaType.APPLICATION_JSON);
				headerChangePassword.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
				headerChangePassword.add("Authorization", "Bearer "+access_token);			
				Map<String, Object> mapChangePassword = new HashMap<>();
				mapChangePassword.put("username", user_name);					
				mapChangePassword.put("password", password);					
				mapChangePassword.put("old_password", "admin1");
								    			
				HttpEntity<Map<String, Object>> entityChangePassword = new HttpEntity<>(mapChangePassword, headerChangePassword);
				ResponseEntity<String> responseChangePassword = apiChangePassword.postForEntity(apisUrl+"/sso/changePassword", entityChangePassword, String.class);
				String jsonChangePassword 		 = responseChangePassword.getBody();
				JSONObject objChangePassword 	 = new JSONObject(jsonChangePassword);
				if(objChangePassword.getString("status").equals("1")) {
					code = "01";
					status = "success";
					message = "Selamat aktivasi akun berhasil silahkan login ke dalam Aplikasi SEHATI.";								
				}
				
		     }	
		}else {
			
			code = "02";
			status = "failed";
			message = "Password minimal 8 karakter terdiri dari huruf besar, huruf kecil dan angka.";	
			
			return   "{"+
				"\"code\":\""+code+"\","+
				"\"status\":\""+status+"\","+
				"\"message\":\""+message+"\""+
			 "}";	
		}
		
		return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";						        	
	}

	@Override
	public String SetForgotPass(HttpServletRequest request) {
		String code = "02";
		String status = "failed";
		String message = "Terjadi kesalahan sistem. Silakan hubungi Administrator.";
		
		String username = request.getParameter("username");
        
		String sqlcek = "select count(a.user_id) user_id "
						 + "from portal.tm_users a where a.user_name=?";
		@SuppressWarnings("deprecation")
		int cek = portalTemplate.queryForObject(sqlcek, new Object[]{username}, Integer.class);
		if(cek>0) {
	        String sqlUserId = "select a.user_id "
							 + "from portal.tm_users a where a.user_name=?";
			@SuppressWarnings("deprecation")
			final int user_id = portalTemplate.queryForObject(sqlUserId, new Object[]{username}, Integer.class);			
			
       		String reset_key = home.getSecure(user_id+username+home.getNow("yyyy-MM-dd HH:mm:ss"));	
       		String sqlReset = "INSERT INTO portal.tx_reset "
	        				+ "(user_id, reset_key, reset_expired, reset_status, reset_date) "
	        				+ "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta' + INTERVAL '1 DAYS', ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
       		int rowInsertReset = portalTemplate.update(sqlReset, new PreparedStatementSetter() {
		              public void setValues(PreparedStatement ps) throws SQLException {
		            	  ps.setInt(1, user_id);
		            	  ps.setString(2, reset_key);
		            	  ps.setString(3, "0");
		              }
       		});
    		if(rowInsertReset>0) {		    
    			RestTemplate restTemplate = new RestTemplate();
    			HttpHeaders headers = new HttpHeaders();
    			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    			headers.add("Authorization", "Basic "+home.getInitByName("api_basic_auth")); 
    			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    			map.add("grant_type","client_credentials");
    			HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
    			ResponseEntity<String> responseToken = restTemplate.exchange(apisUrl+"/token", HttpMethod.POST, entity, String.class);
    			String jsonToken		 = responseToken.getBody();
    			JSONObject dataToken 	 = new JSONObject(jsonToken.toString());
    			String access_token		 = dataToken.getString("access_token");
    			
        		RestTemplate apiSendNotif = new RestTemplate();
    			HttpHeaders headerSendNotif = new HttpHeaders();
    			headerSendNotif.setContentType(MediaType.APPLICATION_JSON);
    			headerSendNotif.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    			headerSendNotif.add("Authorization", "Bearer "+access_token);			
    			Map<String, Object> mapSendNotif = new HashMap<>();
    			mapSendNotif.put("username", username);
    			
    			Map<String, Object> mapSendNotifEmail = new HashMap<>();
    			mapSendNotifEmail.put("subject", "Reset Password Akun SEHATI");
    			mapSendNotifEmail.put("message", "<table><tr><td><img width=\"70\" src=\""+"https://sehati.hubla.dephub.go.id/assets/public/images/logo/logo-kemenhub.png\"></td><td style=\"padding-left:10px;\"><div style=\"font-size:16px;\"><b>SEHATI</b></div><div>Sistem Elektronik Hubla Terintegrasi<div><div style=\"font-size:14px;\">Direktorat Jenderal Perhubungan Laut</div><div style=\"font-size:16px;\">Kementerian Perhubungan Republik Indonesia<div></td><tr></table><hr><div style=\"width:100%; text-align:left;\"><div>Untuk mereset password silahkan klik tautan dibawah ini <br> <a style=\"font-size:20px; font-weight:bolder;\" target=\"_blank\" href=\""+resetpassUrl+"/"+reset_key+"\">Reset Password</a></div>");
    			mapSendNotif.put("email", mapSendNotifEmail);
    							    			
    			HttpEntity<Map<String, Object>> entitySendNotif = new HttpEntity<>(mapSendNotif, headerSendNotif);
    			ResponseEntity<String> responseSendNotif = apiSendNotif.postForEntity(apisUrl+"/notif/send", entitySendNotif, String.class);
    			String jsonSendNotif 		 = responseSendNotif.getBody();
    			JSONObject objSendNotif 	 = new JSONObject(jsonSendNotif);
    			if(objSendNotif.getString("status").equals("001")) {
    				code = "01";
    				status = "success";
    				message = "Untuk mereset password akun silahkan klik tautan yang telah dikirimkan ke email Anda";
    			}else {
					JSONObject requestregisterNib = new JSONObject();
					requestregisterNib.put("username", username);
					requestregisterNib.put("password", username); // requestregisterNib.put("password", "admin1");
					HttpHeaders headersregisterNib = new HttpHeaders();
					headersregisterNib.setContentType(MediaType.APPLICATION_JSON);
					headersregisterNib.add("Authorization", "Bearer "+access_token);
					HttpEntity<String> entityregisterNib = new HttpEntity<String>(requestregisterNib.toString(), headersregisterNib);
	
					// send request and parse result
					ResponseEntity<String> responseregisterNib = restTemplate.exchange(apisUrl+"/sso/registerNib", HttpMethod.POST, entityregisterNib, String.class);
					String jsonregisterNib 		 	= responseregisterNib.getBody();
					JSONObject objregisterNib 		= new JSONObject(jsonregisterNib);
					
					if(objregisterNib.getString("status").toString().equals("1")) {
		        		RestTemplate apiSendNotif2 = new RestTemplate();
		    			HttpHeaders headerSendNotif2 = new HttpHeaders();
		    			headerSendNotif2.setContentType(MediaType.APPLICATION_JSON);
		    			headerSendNotif2.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		    			headerSendNotif2.add("Authorization", "Bearer "+access_token);			
		    			Map<String, Object> mapSendNotif2 = new HashMap<>();
		    			mapSendNotif2.put("username", username);
		    			
		    			Map<String, Object> mapSendNotif2Email = new HashMap<>();
		    			mapSendNotif2Email.put("subject", "Reset Password Akun SEHATI");
		    			mapSendNotif2Email.put("message", "<table><tr><td><img width=\"70\" src=\""+"https://sehati.hubla.dephub.go.id/assets/public/images/logo/logo-kemenhub.png\"></td><td style=\"padding-left:10px;\"><div style=\"font-size:16px;\"><b>SEHATI</b></div><div>Sistem Elektronik Hubla Terintegrasi<div><div style=\"font-size:14px;\">Direktorat Jenderal Perhubungan Laut</div><div style=\"font-size:16px;\">Kementerian Perhubungan Republik Indonesia<div></td><tr></table><hr><div style=\"width:100%; text-align:left;\"><div>Untuk mereset password silahkan klik tautan dibawah ini <br> <a style=\"font-size:20px; font-weight:bolder;\" target=\"_blank\" href=\""+resetpassUrl+"/"+reset_key+"\">Reset Password</a></div>");
		    			mapSendNotif.put("email", mapSendNotif2Email);
		    							    			
		    			HttpEntity<Map<String, Object>> entitySendNotif2 = new HttpEntity<>(mapSendNotif2, headerSendNotif2);
		    			ResponseEntity<String> responseSendNotif2 = apiSendNotif2.postForEntity(apisUrl+"/notif/send", entitySendNotif2, String.class);
		    			String jsonSendNotif2 		 = responseSendNotif2.getBody();
		    			JSONObject objSendNotif2 	 = new JSONObject(jsonSendNotif2);
		    			if(objSendNotif2.getString("status").equals("001")) {
		    				code = "01";
		    				status = "success";
		    				message = "Untuk mereset password akun silahkan klik tautan yang telah dikirimkan ke email Anda";		    				
		    			}						
					}    				
    			}
    		}
		}else {
			code 	= "02";
			status 	= "failed";
			message = "Mohon Maaf. NIP atau NIB tidak ditemukan, silakan registrasi terlebih dahulu";			
		}
		return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";						        	
	}

	@Override
	public String CekActivate(String reset_key) {
		String result = "0";
		
        String sqlUserStatus = "select count(*) reset_status "
        					 + "from portal.tx_reset a where a.reset_status='0' and NOW() AT TIME ZONE 'Asia/Jakarta'<=a.reset_expired and a.reset_key=?";
		@SuppressWarnings("deprecation")
		final int user_status = portalTemplate.queryForObject(sqlUserStatus, new Object[]{reset_key}, Integer.class);
		if(user_status>0) {
			result = "1";
		}
		return result;
	}

	@Override
	public String SetResetPass(HttpServletRequest request) {
		String code = "02";
		String status = "failed";
		String message = "Terjadi kesalahan sistem. Silakan hubungi Administrator.";
		
		String reset_key = request.getParameter("reset_key");
		String password = request.getParameter("password");
		
		if(home.standardPassword(password)) {
	        String sqlUserId = "select a.user_id "
							 + "from portal.tx_reset a where a.reset_key=?";
			@SuppressWarnings("deprecation")
			final int user_id = portalTemplate.queryForObject(sqlUserId, new Object[]{reset_key}, Integer.class);
			
	        String sqlUserName = "select a.user_name "
		 				   	   + "from portal.tm_users a where a.user_id=?";
			@SuppressWarnings("deprecation")
			String user_name = portalTemplate.queryForObject(sqlUserName, new Object[]{user_id}, String.class);
			
	        String sqlUser = "UPDATE portal.tm_users SET user_password=? WHERE user_id=?";
		    int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
								              public void setValues(PreparedStatement ps) throws SQLException {
								            	  ps.setString(1, home.getSecure(password));
								            	  ps.setInt(2, user_id);
								              }
							    });
		
		     if(rowInsertUser>0) {
		        String sqlLog  = "INSERT INTO portal.tx_log "
				        	   + "(user_id, log_desc, log_date, log_ip) "
				        	   + "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta', ?)";
		        portalTemplate.update(sqlLog, new PreparedStatementSetter() {
								              public void setValues(PreparedStatement ps) throws SQLException {
								            	  ps.setInt(1, user_id);
								            	  ps.setString(2, "Reset Password");
								            	  ps.setString(3, home.getClientIp(request));
								              }
							          });
		        
		        String sqlreset = "UPDATE portal.tx_reset SET reset_status='1' WHERE user_id=?";
			    portalTemplate.update(sqlreset, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setInt(1, user_id);
									              }
								    });

		        
		 		RestTemplate restTemplate = new RestTemplate();
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
				headers.add("Authorization", "Basic "+home.getInitByName("api_basic_auth"));
				MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
				map.add("grant_type","client_credentials");
				HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
				ResponseEntity<String> responseToken = restTemplate.exchange(apisUrl+"/token", HttpMethod.POST, entity, String.class);
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
				mapResetPassword.put("username", user_name);					
								    			
				HttpEntity<Map<String, Object>> entityResetPassword = new HttpEntity<>(mapResetPassword, headerResetPassword);
				ResponseEntity<String> responseResetPassword = apiResetPassword.postForEntity(apisUrl+"/sso/resetPassword", entityResetPassword, String.class);
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
					mapChangePassword.put("username", user_name);					
					mapChangePassword.put("password", password);					
					mapChangePassword.put("old_password", new_password);
									    			
					HttpEntity<Map<String, Object>> entityChangePassword = new HttpEntity<>(mapChangePassword, headerChangePassword);
					ResponseEntity<String> responseChangePassword = apiChangePassword.postForEntity(apisUrl+"/sso/changePassword", entityChangePassword, String.class);
					String jsonChangePassword 		 = responseChangePassword.getBody();
					JSONObject objChangePassword 	 = new JSONObject(jsonChangePassword);
					if(objChangePassword.getString("status").equals("1")) {
						code = "01";
						status = "success";
						message = "Selamat password akun Anda berhasil direset, Silakan login ke dalam Aplikasi SEHATI.";								
					}		
				}
				
				
		     }	
		}else {
			code = "02";
			status = "failed";
			message = "Password minimal 8 karakter terdiri dari huruf besar, huruf kecil dan angka.";				
		}
		
		return   "{"+
					"\"code\":\""+code+"\","+
					"\"status\":\""+status+"\","+
					"\"message\":\""+message+"\""+
				 "}";	
	}

	@Override
	public void SetUser(String username, HttpServletRequest request) {
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		headers.add("Authorization", "Basic "+home.getInitByName("api_basic_auth"));
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("grant_type","client_credentials");
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);
		ResponseEntity<String> responseToken = restTemplate.exchange(apisUrl+"/token", HttpMethod.POST, entity, String.class);
		String jsonToken		 = responseToken.getBody();
		JSONObject dataToken 	 = new JSONObject(jsonToken.toString());
		String access_token		 = dataToken.getString("access_token");
		
		//Check Pegawai
		RestTemplate apiPegawai = new RestTemplate();
		HttpHeaders headerPegawai = new HttpHeaders();
		headerPegawai.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headerPegawai.add("Authorization", "Bearer "+access_token);
		HttpEntity<MultiValueMap<String,String>> entityPegawai = new HttpEntity<>(headerPegawai);
		ResponseEntity<String> responsePegawai = apiPegawai.exchange(apisUrl+"/data/pegawai?l=100&o=0&k=nip&q="+username.replace(" ", ""), HttpMethod.GET, entityPegawai, String.class);
		String jsonPegawai 		 = responsePegawai.getBody();
		JSONObject objPegawai 	 = new JSONObject(jsonPegawai);
		JSONArray arrPegawai = objPegawai.getJSONArray("data");
		int jmlPegawai = arrPegawai.length();
		if(jmlPegawai>0) {			
			JSONObject dataPegawai = arrPegawai.getJSONObject(0);
			String nip = dataPegawai.getString("nip");
			String email = dataPegawai.getString("email");
			String nama = dataPegawai.getString("nama");
			String no_telp = ((dataPegawai.has("no_telp") && !dataPegawai.isNull("no_telp")) ? dataPegawai.getString("no_telp") : "");
			String foto = ((dataPegawai.has("foto") && !dataPegawai.isNull("foto")) ? dataPegawai.getString("foto") : "");
			String tanggal_lahir = dataPegawai.getString("tanggal_lahir");
			String jabatan_fungsional = ((dataPegawai.has("jabatan_fungsional") && !dataPegawai.isNull("jabatan_fungsional")) ? dataPegawai.getString("jabatan_fungsional") : "");
			String pangkat = ((dataPegawai.has("pangkat") && !dataPegawai.isNull("pangkat")) ? dataPegawai.getString("pangkat") : "");
			String kode_kantor = dataPegawai.getString("kode_kantor");
			String golongan = ((dataPegawai.has("golongan") && !dataPegawai.isNull("golongan")) ? dataPegawai.getString("golongan") : "");
			String jenis_kelamin = dataPegawai.getString("jenis_kelamin");
			String kantor = dataPegawai.getString("kantor");
			String tmt_golongan = ((dataPegawai.has("tmt_golongan") && !dataPegawai.isNull("tmt_golongan")) ? dataPegawai.getString("tmt_golongan") : "");
			String kode_unit = dataPegawai.getString("kode_unit");
			String jabatan_struktural = ((dataPegawai.has("jabatan_struktural") && !dataPegawai.isNull("jabatan_struktural")) ? dataPegawai.getString("jabatan_struktural") : "");
			String unit = dataPegawai.getString("unit");
	        
			String sql = "select count(user_id) from portal.tm_users a where a.user_name=?";
			@SuppressWarnings("deprecation")
			int pegawai = portalTemplate.queryForObject(sql, new Object[]{nip}, Integer.class);
	        if(pegawai<1) {
	        	
		        String sqlUserId = "select coalesce(max(a.user_id),0) user_id "
				        		 + "from portal.tm_users a";
		        int getuser_id = portalTemplate.queryForObject(sqlUserId, Integer.class);
		        	getuser_id = getuser_id+1;
		        final int user_id = getuser_id;
		        
		        String sqlEmpId = "select coalesce(max(a.emp_id),0) emp_id "
		        				+ "from portal.tm_employee a";			        
		        int getemp_id = portalTemplate.queryForObject(sqlEmpId, Integer.class);
		        	getemp_id = getemp_id+1;
		        final int emp_id = getemp_id;
		        
		        String sqlUser = "INSERT INTO portal.tm_users (user_id, user_name, user_password, user_type, user_fullname, user_hp, user_email, user_photo, user_status, user_create, date_create) "
				        	   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW() AT TIME ZONE 'Asia/Jakarta')";
		        int rowInsertUser = portalTemplate.update(sqlUser, new PreparedStatementSetter() {
								              public void setValues(PreparedStatement ps) throws SQLException {
								            	  ps.setInt(1, user_id);
								            	  ps.setString(2, nip);
								            	  ps.setString(3, home.getSecure(nip));
								            	  ps.setString(4, "1");
								            	  ps.setString(5, nama);
								            	  ps.setString(6, no_telp);
								            	  ps.setString(7, email);
								            	  ps.setString(8, foto);
								            	  ps.setString(9, "1");
								            	  ps.setInt(10, user_id);
								              }
							        });

		        if(rowInsertUser>0) {
			        String sqlRole  = "INSERT INTO portal.td_users_roles "
				        	   		+ "(user_id, role_id) "
				        	   		+ "VALUES(?, ?)";
			        portalTemplate.update(sqlRole, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setInt(1, user_id);
									            	  ps.setInt(2, 2);
									              }
								        });
		        	
			        String sqlEmp  = "INSERT INTO portal.tm_employee "
					        	   + "(emp_id, user_id, emp_name, emp_number, emp_birth_date, emp_gender, unit_code, unit_name, office_code, office_name, group_date, group_name, grade_name, position_name) "
					        	   + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			        portalTemplate.update(sqlEmp, new PreparedStatementSetter() {
									              public void setValues(PreparedStatement ps) throws SQLException {
									            	  ps.setInt(1, emp_id);
									            	  ps.setInt(2, user_id);
									            	  ps.setString(3, nama);
									            	  ps.setString(4, nip);
									            	  ps.setDate(5, Date.valueOf(tanggal_lahir));
									            	  ps.setString(6, jenis_kelamin);
									            	  ps.setString(7, kode_unit);
									            	  ps.setString(8, unit);
									            	  ps.setString(9, kode_kantor);
									            	  ps.setString(10, kantor);
									            	  ps.setDate(11, Date.valueOf(tmt_golongan));
									            	  ps.setString(12, golongan);
									            	  ps.setString(13, pangkat);
									            	  ps.setString(14, (jabatan_fungsional == null || jabatan_fungsional.length()==0 ? jabatan_struktural : jabatan_fungsional));
									              }
								        });
		        }
	        }
		}
	}

}
