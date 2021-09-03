package id.go.dephub.hubla.sehati.service;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.annot.PdfWidgetAnnotation;
import com.itextpdf.signatures.CRLVerifier;
import com.itextpdf.signatures.CertificateInfo;
import com.itextpdf.signatures.OCSPVerifier;
import com.itextpdf.signatures.PdfPKCS7;
import com.itextpdf.signatures.SignatureUtil;
import com.itextpdf.signatures.VerificationOK;
import org.bouncycastle.cert.ocsp.BasicOCSPResp;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.GeneralSecurityException;
import java.security.Security;
import java.security.cert.CRL;
import java.security.cert.Certificate;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;

import id.go.dephub.hubla.sehati.dao.HomeDao;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
	
	  @Autowired
	  @Qualifier("portalJdbcTemplate")
	  private JdbcTemplate portalTemplate;
		
	  @Autowired
	  @Qualifier("datalakeJdbcTemplate")
	  private JdbcTemplate datalakeTemplate;

	  @Autowired
	  HomeDao home;
	
	  @Override
	  public void init() {
	    try {
	      String rootpath = home.rootPath();
	      Path root = Paths.get(rootpath);
	      Files.createDirectory(root);
	    } catch (IOException e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  } 

	  @Override
	  public String save(String what, MultipartFile file) {
	    try {
	      String result = "";
          Date date = new Date();
          String folderDate= new SimpleDateFormat("yyyyMM").format(date);
          String modifiedDate= new SimpleDateFormat("yyyyMMddHHmmss").format(date);

          if(what.equals("verifypdf")) {
        	  String folder_date = home.rootPath()+"/verifypdf/"+folderDate;
        	  Path newpath = Paths.get(folder_date);
              String newfile = home.getSecure(modifiedDate+home.getRandom(5))+".pdf";
              if (!Files.exists(newpath)) {
            	  Files.createDirectory(newpath);
              }
    	      Files.copy(file.getInputStream(), newpath.resolve(newfile));
    	      result = what+"/"+folderDate+"/"+newfile;
          }
          if(what.equals("ktp")) {
        	  String folder_date = home.rootPath()+"/foto/"+folderDate;
        	  Path newpath = Paths.get(folder_date);
              String newfile = home.getSecure(modifiedDate+home.getRandom(5))+".png";
              if (!Files.exists(newpath)) {
            	  Files.createDirectory(newpath);
              }
    	      Files.copy(file.getInputStream(), newpath.resolve(newfile));
    	      result = "esign/"+folderDate+"/"+newfile;
          }
          if(what.equals("rekom")) {
        	  String folder_date = home.rootPath()+"/foto/"+folderDate;
        	  Path newpath = Paths.get(folder_date);
              String newfile = home.getSecure(modifiedDate+home.getRandom(5))+".pdf";
              if (!Files.exists(newpath)) {
            	  Files.createDirectory(newpath);
              }
    	      Files.copy(file.getInputStream(), newpath.resolve(newfile));
    	      result = "esign/"+folderDate+"/"+newfile;
          }
          if(what.equals("ttd")) {
        	  String folder_date = home.rootPath()+"/signature/"+folderDate;
        	  Path newpath = Paths.get(folder_date);
              String newfile = home.getSecure(modifiedDate+home.getRandom(5))+".png";
              if (!Files.exists(newpath)) {
            	  Files.createDirectory(newpath);
              }
    	      Files.copy(file.getInputStream(), newpath.resolve(newfile));
    	      result = "esign/"+folderDate+"/"+newfile;
          }
          if(what.equals("digisign")) {
        	  String folder_date = home.rootPath()+"/"+what+"/"+folderDate;
        	  Path newpath = Paths.get(folder_date);
              String newfile = home.getSecure(modifiedDate+home.getRandom(5))+".pdf";
              if (!Files.exists(newpath)) {
            	  Files.createDirectory(newpath);
              }
    	      Files.copy(file.getInputStream(), newpath.resolve(newfile));
    	      result = what+"/"+folderDate+"/"+newfile;
          }
          if(what.equals("digisignedited")) {
        	  String folder_date = home.rootPath()+"/digisign/"+folderDate;
        	  Path newpath = Paths.get(folder_date);
              String newfile = home.getSecure(modifiedDate+home.getRandom(5))+".pdf";
              if (!Files.exists(newpath)) {
            	  Files.createDirectory(newpath);
              }
    	      Files.copy(file.getInputStream(), newpath.resolve(newfile));
    	      result = what+"/"+folderDate+"/"+newfile;
          }
          return result; 
	    } catch (Exception e) {
	      return e.getMessage();
	    }
	  }
	  
	  @Override
	  public Map<String, String> verifySign(String path){
		Map<String,String> std = new HashMap<>();
		std.put("signature_name", "");
		try {
			if(path==null) {
				std.put("signature_method", "scan");
			}else {
				std.put("signature_method", "upload");
			}
			std.put("signature_file", path);
			BouncyCastleProvider provider = new BouncyCastleProvider();
	        Security.addProvider(provider);
			PdfDocument pdfDoc = new PdfDocument(new PdfReader(home.rootPath()+"/"+path));
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, false);
			SignatureUtil signUtil = new SignatureUtil(pdfDoc);
			List<String> names = signUtil.getSignatureNames();
	        for (String name : names) {
	        	List<PdfWidgetAnnotation> widgets = form.getField(name).getWidgets();
	        	if (widgets != null && widgets.size() > 0) {
	                Rectangle pos = widgets.get(0).getRectangle().toRectangle();
	                if (pos.getWidth() == 0 || pos.getHeight() == 0) {
	                	std.put("signature_visible", "Invisible signature");
	                } else {
	                	std.put("signature_visible", "Visible signature");
	                }
	            }
	        	PdfPKCS7 pkcs7 = signUtil.readSignatureData(name);  
	        	if(signUtil.signatureCoversWholeDocument(name)) {
	            	std.put("signature_whole_document", "true");        		
	        	}else {
	            	std.put("signature_whole_document", "false");        		
	        	}
	        	std.put("signature_revision", Integer.toString(signUtil.getRevision(name)));
	        	std.put("signature_revision_total", Integer.toString(signUtil.getTotalRevisions()));
	        	try {
	        		if(pkcs7.verifySignatureIntegrityAndAuthenticity()) {
	            		std.put("signature_integrity", "true");        			
	        		}else {
	        			std.put("signature_integrity", "false");  
	        		}
	        		std.put("signature_digest_algorithm", pkcs7.getHashAlgorithm());
	        		std.put("signature_encryption_algorithm", pkcs7.getEncryptionAlgorithm());

					X509Certificate cert = (X509Certificate) pkcs7.getSigningCertificate();
	        		Certificate[] certs = pkcs7.getSignCertificateChain();
					X509Certificate signCert = (X509Certificate) certs[0];
					X509Certificate issuerCert = (certs.length > 1 ? (X509Certificate) certs[1] : null);
					std.put("signature_signer", CertificateInfo.getSubjectFields(cert).getField("CN"));
					if(CertificateInfo.getSubjectFields(cert).getField("OU")!=null) {
			        	std.put("signature_name", CertificateInfo.getSubjectFields(cert).getField("CN"));
						std.put("signature_signer_unit", CertificateInfo.getSubjectFields(cert).getField("OU"));
						std.put("signature_signer_org", CertificateInfo.getSubjectFields(cert).getField("O"));	
						
				        if (pkcs7.getSignName() != null) {
							std.put("signature_signer_alternative", pkcs7.getSignName());
				        }
				        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        if(pkcs7.getReason()==null) {
					        std.put("signature_reason", "-");			        	
				        }else {
				        	std.put("signature_reason", pkcs7.getReason());	
				        }
				        Calendar signedAt = pkcs7.getSignDate();
				        signedAt.add(Calendar.HOUR_OF_DAY, 7);
				        Date signedAtdate = signedAt.getTime(); 
				        
				        std.put("signature_date", date_format.format(signedAtdate));
				        
				        if(pkcs7.getLocation()==null) {
					        std.put("signature_location", "-");	        	
				        }else {
				        	std.put("signature_location", pkcs7.getLocation());	
				        }
				        List<BasicOCSPResp> ocsps = new ArrayList<BasicOCSPResp>();
				        if (pkcs7.getOcsp() != null) {
				            ocsps.add(pkcs7.getOcsp());
				        }
				        OCSPVerifier ocspVerifier = new OCSPVerifier(null, ocsps);
				        Calendar cal = pkcs7.getTimeStampDate();
				        if (cal == null) {
				            cal = Calendar.getInstance();
				        }
				        List<VerificationOK> verification = ocspVerifier.verify(signCert, issuerCert, cal.getTime());
				        if (verification.size() == 0) {
				            List<X509CRL> crls = new ArrayList<X509CRL>();
				            if (pkcs7.getCRLs() != null) {
				                for (CRL crl : pkcs7.getCRLs()) {
				                    crls.add((X509CRL) crl);
				                }
				            }				 
				            CRLVerifier crlVerifier = new CRLVerifier(null, crls);
				            verification.addAll(crlVerifier.verify(signCert, issuerCert, cal.getTime()));
				        }
				        
				        if (pkcs7.getTimeStampDate() != null) {
					        std.put("signature_timestamp", date_format.format(pkcs7.getTimeStampDate().getTime()));
				            if(pkcs7.verifyTimestampImprint()) {
						        std.put("signature_timestamp_verified", "true");		            	
				            }else {
						        std.put("signature_timestamp_verified", "false");		            	
				            }
				        }else {
				        	std.put("signature_timestamp_verified", "false");
				        }
				        		       
				        std.put("signature_issuer", CertificateInfo.getIssuerFields(cert).getField("CN")+", "+CertificateInfo.getIssuerFields(cert).getField("O"));
				        try {
				            cert.checkValidity();
				            std.put("signature_validity_now", "valid");
				        } catch (CertificateExpiredException e) {
				            std.put("signature_validity_now", "expired");
				        } catch (CertificateNotYetValidException e) {
				            std.put("signature_validity_now", "not valid yet");
				        }
					     String sql = "SELECT nama FROM gold.m_pegawai_sik WHERE UPPER(nama) LIKE ? OR email = ?";
					     @SuppressWarnings("deprecation")
						 String pegawai = datalakeTemplate.queryForObject(sql, new Object[]{
										  		"%"+CertificateInfo.getSubjectFields(cert).getField("CN").toString().toUpperCase()+"%",
										  		CertificateInfo.getSubjectFields(cert).getField("E").toString()
										  }, String.class);
					     if(pegawai!=null) {
					    	 std.put("signature_registered", "true");
					     }else {
					    	 if(CertificateInfo.getSubjectFields(cert).getField("OU").toUpperCase().equals("DITJEN HUBLA")) {
						        std.put("signature_registered", "true");
					         }else {
					        	std.put("signature_registered", "false");
					         }
					     }
					}
	        	} catch (GeneralSecurityException e) {
					
				}
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return std;
	  }

	@Override
	public ResponseEntity<InputStreamResource> preview(String path) {
		File file = new File(home.rootPath()+"/"+path);		
        InputStreamResource resource;
        String[] extarray = path.split("\\.");
        String ext = "";
        if(extarray[1].equals("pdf")) {
        	ext = "application/pdf";
        }else {
        	ext = "image/"+extarray[1];        	
        }
		try {
			resource = new InputStreamResource(new FileInputStream(file));
	        return ResponseEntity.ok()
	                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=" + file.getName())
	                .contentType(MediaType.parseMediaType(ext))
	                .contentLength(file.length()) //
	                .body(resource);
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	@Override
	public String encodePDF(String path) {
		String encodedString = "";
		try {
			byte[] input_file = Files.readAllBytes(Paths.get(home.rootPath()+"/"+path));
			byte[] encodedBytes = Base64.getEncoder().encode(input_file);
				   encodedString =  new String(encodedBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encodedString;
	}

	@Override
	public Map<String, String> verifySignScan(String path) {
		
        String sqlUserID = "select count(*) doc_file_sign "
	   	   		   		 + "from portal.tx_esign_doc a where a.doc_file_sign like ?";
		@SuppressWarnings("deprecation")
		int cek_doc_file_sign = portalTemplate.queryForObject(sqlUserID, new Object[]{"%" + path}, Integer.class);
		
		String filepaths = "";
		String signature_file = "";
		if(cek_doc_file_sign>0) {
	        String sqlUserID2 = "select a.doc_file_sign "
  	   		   		 + "from portal.tx_esign_doc a where a.doc_file_sign like ?";
			@SuppressWarnings("deprecation")
			String doc_file_sign = portalTemplate.queryForObject(sqlUserID2, new Object[]{"%" + path}, String.class);
			filepaths = home.rootPath()+"/digisign/"+doc_file_sign;
			signature_file = doc_file_sign;
		}else {
			filepaths = home.rootPath()+"/digisign/"+path;
			signature_file = path;
		}
		Map<String,String> std = new HashMap<>();
		std.put("signature_name", "");
		
		try {
			if(path==null) {
				std.put("signature_method", "scan");
			}else {
				std.put("signature_method", "upload");
			}
			std.put("signature_file", "digisign/"+signature_file);
			BouncyCastleProvider provider = new BouncyCastleProvider();
	        Security.addProvider(provider);
			PdfDocument pdfDoc = new PdfDocument(new PdfReader(filepaths));
			PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, false);
			SignatureUtil signUtil = new SignatureUtil(pdfDoc);
			List<String> names = signUtil.getSignatureNames();
	        for (String name : names) {
	        	List<PdfWidgetAnnotation> widgets = form.getField(name).getWidgets();
	        	if (widgets != null && widgets.size() > 0) {
	                Rectangle pos = widgets.get(0).getRectangle().toRectangle();
	                if (pos.getWidth() == 0 || pos.getHeight() == 0) {
	                	std.put("signature_visible", "Invisible signature");
	                } else {
	                	std.put("signature_visible", "Visible signature");
	                }
	            }
	        	PdfPKCS7 pkcs7 = signUtil.readSignatureData(name);  
	        	if(signUtil.signatureCoversWholeDocument(name)) {
	            	std.put("signature_whole_document", "true");        		
	        	}else {
	            	std.put("signature_whole_document", "false");        		
	        	}
	        	std.put("signature_revision", Integer.toString(signUtil.getRevision(name)));
	        	std.put("signature_revision_total", Integer.toString(signUtil.getTotalRevisions()));
	        	try {
	        		if(pkcs7.verifySignatureIntegrityAndAuthenticity()) {
	            		std.put("signature_integrity", "true");        			
	        		}else {
	        			std.put("signature_integrity", "false");  
	        		}
	        		std.put("signature_digest_algorithm", pkcs7.getHashAlgorithm());
	        		std.put("signature_encryption_algorithm", pkcs7.getEncryptionAlgorithm());

					X509Certificate cert = (X509Certificate) pkcs7.getSigningCertificate();
	        		Certificate[] certs = pkcs7.getSignCertificateChain();
					X509Certificate signCert = (X509Certificate) certs[0];
					X509Certificate issuerCert = (certs.length > 1 ? (X509Certificate) certs[1] : null);
					std.put("signature_signer", CertificateInfo.getSubjectFields(cert).getField("CN"));
					if(CertificateInfo.getSubjectFields(cert).getField("OU")!=null) {
			        	std.put("signature_name", CertificateInfo.getSubjectFields(cert).getField("CN"));
						std.put("signature_signer_unit", CertificateInfo.getSubjectFields(cert).getField("OU"));
						std.put("signature_signer_org", CertificateInfo.getSubjectFields(cert).getField("O"));	
						
				        if (pkcs7.getSignName() != null) {
							std.put("signature_signer_alternative", pkcs7.getSignName());
				        }
				        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				        if(pkcs7.getReason()==null) {
					        std.put("signature_reason", "-");			        	
				        }else {
				        	std.put("signature_reason", pkcs7.getReason());	
				        }
				        Calendar signedAt = pkcs7.getSignDate();
				        signedAt.add(Calendar.HOUR_OF_DAY, 7);
				        Date signedAtdate = signedAt.getTime(); 
				        
				        std.put("signature_date", date_format.format(signedAtdate));
				        
				        if(pkcs7.getLocation()==null) {
					        std.put("signature_location", "-");	        	
				        }else {
				        	std.put("signature_location", pkcs7.getLocation());	
				        }
				        List<BasicOCSPResp> ocsps = new ArrayList<BasicOCSPResp>();
				        if (pkcs7.getOcsp() != null) {
				            ocsps.add(pkcs7.getOcsp());
				        }
				        OCSPVerifier ocspVerifier = new OCSPVerifier(null, ocsps);
				        Calendar cal = pkcs7.getTimeStampDate();
				        if (cal == null) {
				            cal = Calendar.getInstance();
				        }
				        List<VerificationOK> verification = ocspVerifier.verify(signCert, issuerCert, cal.getTime());
				        if (verification.size() == 0) {
				            List<X509CRL> crls = new ArrayList<X509CRL>();
				            if (pkcs7.getCRLs() != null) {
				                for (CRL crl : pkcs7.getCRLs()) {
				                    crls.add((X509CRL) crl);
				                }
				            }				 
				            CRLVerifier crlVerifier = new CRLVerifier(null, crls);
				            verification.addAll(crlVerifier.verify(signCert, issuerCert, cal.getTime()));
				        }
				        
				        if (pkcs7.getTimeStampDate() != null) {
					        std.put("signature_timestamp", date_format.format(pkcs7.getTimeStampDate().getTime()));
				            if(pkcs7.verifyTimestampImprint()) {
						        std.put("signature_timestamp_verified", "true");		            	
				            }else {
						        std.put("signature_timestamp_verified", "false");		            	
				            }
				        }else {
				        	std.put("signature_timestamp_verified", "false");
				        }
				        		       
				        std.put("signature_issuer", CertificateInfo.getIssuerFields(cert).getField("CN")+", "+CertificateInfo.getIssuerFields(cert).getField("O"));
				        try {
				            cert.checkValidity();
				            std.put("signature_validity_now", "valid");
				        } catch (CertificateExpiredException e) {
				            std.put("signature_validity_now", "expired");
				        } catch (CertificateNotYetValidException e) {
				            std.put("signature_validity_now", "not valid yet");
				        }
					     String sql = "SELECT nama FROM gold.m_pegawai_sik WHERE UPPER(nama) LIKE ? OR email = ?";
					     @SuppressWarnings("deprecation")
						 String pegawai = datalakeTemplate.queryForObject(sql, new Object[]{
										  		"%"+CertificateInfo.getSubjectFields(cert).getField("CN").toString().toUpperCase()+"%",
										  		CertificateInfo.getSubjectFields(cert).getField("E").toString()
										  }, String.class);
					     if(pegawai!=null) {
					    	 std.put("signature_registered", "true");
					     }else {
					    	 if(CertificateInfo.getSubjectFields(cert).getField("OU").toUpperCase().equals("DITJEN HUBLA")) {
						        std.put("signature_registered", "true");
					         }else {
					        	std.put("signature_registered", "false");
					         }
					     }
					}
	        	} catch (GeneralSecurityException e) {
					
				}
	        }
		} catch (IOException e) {
			e.printStackTrace();
		}
		return std;
		
	}
}