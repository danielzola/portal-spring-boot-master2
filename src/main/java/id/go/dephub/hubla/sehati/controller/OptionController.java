package id.go.dephub.hubla.sehati.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import id.go.dephub.hubla.sehati.dao.OptionDao;

@Controller
@RequestMapping("option")
public class OptionController {
	
    @Autowired
    OptionDao options;
    
	@GetMapping("/layanan")
	@ResponseBody
	public ResponseEntity<String> getlayanan(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = options.getListLayanan(request);
			return new ResponseEntity<>(result, HttpStatus.OK);		
		}
	}

	@GetMapping("/role/{selected}")
	@ResponseBody
	public ResponseEntity<String> getRole(@PathVariable("selected") String selected, Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = options.getListRole(request,selected);
			return new ResponseEntity<>(result, HttpStatus.OK);		
		}
	}

	@GetMapping("/priviledge/{selected}")
	@ResponseBody
	public ResponseEntity<String> getPriviledge(@PathVariable("selected") String selected, Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = options.getListPriviledge(request,selected);
			return new ResponseEntity<>(result, HttpStatus.OK);		
		}
	}

	@GetMapping("/provinsi/{selected}")
	@ResponseBody
	public ResponseEntity<String> getProvinsi(@PathVariable("selected") String selected, Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = options.getListProvinsi(request,selected);
			return new ResponseEntity<>(result, HttpStatus.OK);		
		}
	}
	
	@GetMapping("/kabkota/{provinsi}/{selected}")
	@ResponseBody
	public ResponseEntity<String> getKabKota(@PathVariable("provinsi") String provinsi, @PathVariable("selected") String selected, Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = options.getListKabKota(request,provinsi,selected);
			return new ResponseEntity<>(result, HttpStatus.OK);		
		}
	}
	
	@GetMapping("/esignuser/{office_code}/{selected}")
	@ResponseBody
	public ResponseEntity<String> getEsignUser(@PathVariable("office_code") String office_code, @PathVariable("selected") String selected, Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = options.getListEsignUser(request, office_code, selected);
			return new ResponseEntity<>(result, HttpStatus.OK);		
		}
	}
	
}
