package id.go.dephub.hubla.sehati.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.model.FilesResponse;
import id.go.dephub.hubla.sehati.service.FilesStorageService;

@Controller
@RequestMapping("files")
public class FilesController {

  @Autowired
  FilesStorageService storageService;

  @Autowired
  HomeDao home;
  
  @PostMapping("/upload/verifypdf")
  public ResponseEntity<FilesResponse> uploadFile(@RequestParam("file") MultipartFile file) {
		String status	= "000";
		String message	= "";
		String location = "";
		String name = "";
		if(file.getContentType().equalsIgnoreCase("application/pdf")) {
		    try {
		      name = file.getOriginalFilename();
		      status  = "001";
		      message = "success";
		      location = storageService.save("verifypdf",file);
		    } catch (Exception e) {
		      message = "Terjadi kesalahan sistem";
		    }
		}else {
		      message = "Format file tidak diperbolehkan.";
		}
	    return ResponseEntity.status(HttpStatus.OK).body(new FilesResponse(status,message,location,name));
  }
  
  @PostMapping("/upload/ktp")
  public ResponseEntity<FilesResponse> uploadFileKtp(@RequestParam("file") MultipartFile file, Authentication authentication, HttpServletRequest request) {
		String status	= "000";
		String message	= "";
		String location = "";
		String name = "";
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new FilesResponse(status,message,location,name));			
		}else {
			if(file.getContentType().equalsIgnoreCase("image/jpeg")||file.getContentType().equalsIgnoreCase("image/jpg")||file.getContentType().equalsIgnoreCase("image/png")) {
			    try {
			      name = file.getOriginalFilename();
			      status  = "001";
			      message = "success";
			      location = storageService.save("ktp",file);
			    } catch (Exception e) {
			      message = "Terjadi kesalahan sistem";
			    }
			}else {
			      message = "Format file tidak diperbolehkan.";
			}
			return ResponseEntity.status(HttpStatus.OK).body(new FilesResponse(status,message,location,name));
		}
  }
  
  @PostMapping("/upload/rekom")
  public ResponseEntity<FilesResponse> uploadFileRekom(@RequestParam("file") MultipartFile file, Authentication authentication, HttpServletRequest request) {
		String status	= "000";
		String message	= "";
		String location = "";
		String name = "";
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new FilesResponse(status,message,location,name));			
		}else {
			if(file.getContentType().equalsIgnoreCase("application/pdf")) {
			    try {
			      name = file.getOriginalFilename();
			      status  = "001";
			      message = "success";
			      location = storageService.save("rekom",file);
			    } catch (Exception e) {
			      message = "Terjadi kesalahan sistem";
			    }
			}else {
			      message = "Format file tidak diperbolehkan.";
			}
		    return ResponseEntity.status(HttpStatus.OK).body(new FilesResponse(status,message,location,name));
		}
  }
  
  @PostMapping("/upload/ttd")
  public ResponseEntity<FilesResponse> uploadFileTtd(@RequestParam("file") MultipartFile file, Authentication authentication, HttpServletRequest request) {
		String status	= "000";
		String message	= "";
		String location = "";
		String name = "";
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new FilesResponse(status,message,location,name));			
		}else {
			if(file.getContentType().equalsIgnoreCase("image/jpeg")||file.getContentType().equalsIgnoreCase("image/jpg")||file.getContentType().equalsIgnoreCase("image/png")) {
			    try {
			      name = file.getOriginalFilename();
			      status  = "001";
			      message = "success";
			      location = storageService.save("ttd",file);
			    } catch (Exception e) {
			      message = "Terjadi kesalahan sistem";
			    }
			}else {
			      message = "Format file tidak diperbolehkan.";
			}
		    return ResponseEntity.status(HttpStatus.OK).body(new FilesResponse(status,message,location,name));   
		}
  }
  
  @PostMapping("/upload/digisign")
  public ResponseEntity<FilesResponse> uploadFileDigisign(@RequestParam("file") MultipartFile file, Authentication authentication, HttpServletRequest request) {
		String status	= "000";
		String message	= "";
		String location = "";
		String name = "";
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new FilesResponse(status,message,location,name));			
		}else {
			if(file.getContentType().equalsIgnoreCase("application/pdf")) {
			    try {
			      name = file.getOriginalFilename();
			      status  = "001";
			      message = "success";
			      location = storageService.save("digisign",file);
			    } catch (Exception e) {
			      message = "Terjadi kesalahan sistem";
			    }
			}else {
			      message = "Format file tidak diperbolehkan.";
			}
		    return ResponseEntity.status(HttpStatus.OK).body(new FilesResponse(status,message,location,name));
		}
  }
  
  @PostMapping("/upload/digisignedited")
  public ResponseEntity<FilesResponse> uploadFileDigisignEdited(@RequestParam("file") MultipartFile file, Authentication authentication, HttpServletRequest request) {
		String status	= "000";
		String message	= "";
		String location = "";
		String name = "";
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new FilesResponse(status,message,location,name));			
		}else {
		    try {
		      name = file.getOriginalFilename();
		      status  = "001";
		      message = "success";
		      location = storageService.save("digisignedited",file);
		    } catch (Exception e) {
		      message = "Terjadi kesalahan sistem";
		    }
		    return ResponseEntity.status(HttpStatus.OK).body(new FilesResponse(status,message,location,name));
		}
  }
  
  @PostMapping("/upload/encodepdf")
  @ResponseBody
  public ResponseEntity<String> encodePDF(@RequestParam("path") String path) {
	  	String result = storageService.encodePDF(path);
		return new ResponseEntity<>(result, HttpStatus.OK);
  }
  
  @GetMapping("/preview/{folder}/{date}/{file}")
  public ResponseEntity<InputStreamResource> previewFile(@PathVariable("folder") String folder, @PathVariable("date") String date, @PathVariable("file") String file) {
	  return storageService.preview(folder+"/"+date+"/"+file);
  }
  
}