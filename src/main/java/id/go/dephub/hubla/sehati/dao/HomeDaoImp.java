package id.go.dephub.hubla.sehati.dao;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Transactional
@Repository
public class HomeDaoImp implements HomeDao{
	private final String LOCALHOST_IPV4 = "127.0.0.1";
	private final String LOCALHOST_IPV6 = "0:0:0:0:0:0:0:1";
	
	@Autowired
	@Qualifier("portalJdbcTemplate")
	private JdbcTemplate portalTemplate;

	@Autowired
	@Qualifier("datalakeJdbcTemplate")
	private JdbcTemplate datalakeTemplate;
	
	@Override
	public String baseUrl(HttpServletRequest request) {
		String baseUrl = String.format("%s://%s", "https", request.getServerName());
//		String baseUrl = String.format("%s://%s:%d",request.getScheme(),  request.getServerName(), request.getServerPort());
		return baseUrl;
	}

	@Override
	public String authorizationRequestBaseUri() {
	    //String authorizationRequestBaseUri = "/signin/oauth2/authorization/wso2";
	    String authorizationRequestBaseUri = "/oauth2/authorization/wso2";		
		return authorizationRequestBaseUri;
	}

	@Override
	public String rootPath() {
		//String rootpath = "/opt/tomcat/apache-tomcat-9.0.41/webapps/ROOT/WEB-INF/upload";
		String rootpath = "/Users/satrinugraha/Projects/Governments/KEMENHUB/sehati-2.0/src/main/resources/static/upload";
		return rootpath;
	}
	
	@Override
	public String root() {
		//String rootpath = "/opt/tomcat/apache-tomcat-9.0.41/webapps/ROOT/WEB-INF/";
		String rootpath = "/Users/satrinugraha/Projects/Governments/KEMENHUB/sehati-2.0/src/main/resources/static/";
		return rootpath;
	}
	
	@Override
	public void qrcode(String content) {
		Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<EncodeHintType, ErrorCorrectionLevel>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);		
		QRCodeWriter writer = new QRCodeWriter();
		BitMatrix bitMatrix = null;
		try {
		    bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 370, 370, hints);
		    MatrixToImageConfig config = new MatrixToImageConfig(0xFF2C166E, MatrixToImageConfig.WHITE);
		    BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix, config);
		    //File file = new File(root()+"classes/static/assets/apps/images/qrlogo.png");
		    File file = new File(root()+"assets/apps/images/qrlogo.png");
		    BufferedImage logoImage = ImageIO.read(file);
		    int deltaHeight = qrImage.getHeight() - logoImage.getHeight();
		    int deltaWidth = qrImage.getWidth() - logoImage.getWidth();
		    BufferedImage combined = new BufferedImage(qrImage.getHeight(), qrImage.getWidth(), BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g = (Graphics2D) combined.getGraphics();
		    g.drawImage(qrImage, 0, 0, null);
		    g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
		    g.drawImage(logoImage, (int) Math.round(deltaWidth / 2), (int) Math.round(deltaHeight / 2), null);
		    //ImageIO.write(combined, "png", new File(root()+"classes/static/assets/apps/images/qrcode.png"));		    
		    ImageIO.write(combined, "png", new File(root()+"assets/apps/images/qrcode.png"));		    
		} catch (Exception e) {
			
		}  
	}


	@SuppressWarnings("deprecation")
	@Override
	public String getInitByName(String init_name) {
	     String sql = "SELECT init_value FROM portal.ts_init WHERE init_name = ?";
	     return portalTemplate.queryForObject(sql, new Object[]{init_name}, String.class);
	}
	
	@Override
	public String getSecure(String str) {
		String message = getSHA256(str);
		String secret = "6232d31397cce369c061bc764bf53f156feef818e7c0a5bbe82c2a65dce09008";
		Mac sha256_HMAC;
		try {
			sha256_HMAC = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
	        try {
				sha256_HMAC.init(secret_key);
		        String hash = Base64.encodeBase64String(sha256_HMAC.doFinal(message.getBytes()));
		        return getSHA256(hash);
			} catch (InvalidKeyException e) {
				return "";
			}
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
	}
	
	@Override
	public String getRandom(int len) {
		String SALTCHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < len) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String generatedString = salt.toString();
        return generatedString;
	}

	@Override
	public boolean standardPassword(String str) {
	    char ch;
	    boolean capitalFlag = false;
	    boolean lowerCaseFlag = false;
	    boolean numberFlag = false;
	    for(int i=0;i < str.length();i++) {
	        ch = str.charAt(i);
	        if( Character.isDigit(ch)) {
	            numberFlag = true;
	        }
	        else if (Character.isUpperCase(ch)) {
	            capitalFlag = true;
	        } else if (Character.isLowerCase(ch)) {
	            lowerCaseFlag = true;
	        }
	        if(numberFlag && capitalFlag && lowerCaseFlag && str.length()>=8)
	            return true;
	    }
	    return false;
	}
	
	@Override
	public String getNow(String format) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        ZoneId zid = ZoneId.of("Asia/Jakarta");
        LocalDateTime now = LocalDateTime.now(zid);
        return dtf.format(now).toString();
        
	}
	
	@Override
	public String dateFormat(String tgl) {
		String startDateString = tgl;
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    return LocalDate.parse(startDateString, formatter).format(formatter2);
	}
	
	@Override
	public String getSHA256(String message) {
		try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(message.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(Exception ex){
            return "";
        }
	}

	@SuppressWarnings("deprecation")
	@Override
	public String getClientIp(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");
		if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		
		if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		
		if(StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if(LOCALHOST_IPV4.equals(ipAddress) || LOCALHOST_IPV6.equals(ipAddress)) {
				try {
					InetAddress inetAddress = InetAddress.getLocalHost();
					ipAddress = inetAddress.getHostAddress();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(!StringUtils.isEmpty(ipAddress) 
				&& ipAddress.length() > 15
				&& ipAddress.indexOf(",") > 0) {
			ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
		}
		
		return ipAddress;
	}

	@Override
	public String hasAccess(HttpServletRequest request, String what, String id) {
		int cek = 0;
		final int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
		if(what.equals("menu")) {
	        String sql = "select count(b.menu_id) menu_id "
	        		 + "from portal.td_users_roles a "
	        		 + "left join portal.ts_role_menu b on a.role_id =b.role_id "
	        		 + "where a.user_id = ? and b.menu_id = ?";
			@SuppressWarnings("deprecation")
			int result = portalTemplate.queryForObject(sql, new Object[]{user_id, id}, Integer.class);		
			cek = result;			
		}
		if(what.equals("action")) {
	        String sql = "select count(b.action_id) action_id "
	        		 + "from portal.td_users_roles a "
	        		 + "left join portal.ts_role_menu_action b on a.role_id =b.role_id "
	        		 + "where a.user_id = ? and b.action_id = ?";
			@SuppressWarnings("deprecation")
			int result = portalTemplate.queryForObject(sql, new Object[]{user_id, id}, Integer.class);		
			cek = result;
		}
		if(cek>0) {
			return "1";
		}else {
			return "0";
		}
	}

	@Override
	public void writelog(HttpServletRequest request, String desc) {
		final int user_id = (Integer)request.getSession().getAttribute("sessionUserId");
        String sqlLog  = "INSERT INTO portal.tx_log "
			           + "(user_id, log_desc, log_date, log_ip) "
			           + "VALUES(?, ?, NOW() AT TIME ZONE 'Asia/Jakarta', ?)";
		portalTemplate.update(sqlLog, new PreparedStatementSetter() {
							              public void setValues(PreparedStatement ps) throws SQLException {
							            	  ps.setInt(1, user_id);
							            	  ps.setString(2, desc);
							            	  ps.setString(3, getClientIp(request));
							              }
						          });
		
	}

}
