package id.go.dephub.hubla.sehati.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import id.go.dephub.hubla.sehati.dao.EsignListDao;
import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.dao.OptionDao;
import id.go.dephub.hubla.sehati.dao.UserDao;
import id.go.dephub.hubla.sehati.model.EsignDoc;
import id.go.dephub.hubla.sehati.model.EsignList;
import id.go.dephub.hubla.sehati.model.User;

@Controller
@RequestMapping("esign")
public class EsignController {
	@Autowired
	HomeDao home;

	@Autowired
	UserDao users;
	
	@Autowired
	OptionDao options;

	@Autowired
	EsignListDao esign;

	@GetMapping("/register/list")
    public String getListEsign(Model model, Authentication authentication, HttpServletRequest request) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		
		String hasAccess = home.hasAccess(request, "menu", "02010000");
		
        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    model.addAttribute("roleActionTop", options.roleAction(request, "02010000", "0"));
	    model.addAttribute("roleActionTable", options.roleAction(request, "02010000", "1"));
        
	    if(hasAccess.equals("1")) {
			esign.setEsignSync(request);
			home.writelog(request, "Akses : Digital Signature > Registrasi");
	        return "apps/esign/register/list";
	    }else {
	    	return "redirect:/";
	    }
    }

	@GetMapping("/register/table")
    public ResponseEntity<Map<String, Object>> getTableEsign(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<EsignList> listRest = esign.getEsignList(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }
	
    @GetMapping("/register/form/{key}")
    public String getFormEsign(@PathVariable("key") String key, Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
        String user_id = String.valueOf((Integer)request.getSession().getAttribute("sessionUserId"));
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		List<EsignList> data = esign.getEsignData(key);
		String hasaccess = home.hasAccess(request, "action", "0201000001");
		String fullaccess = home.hasAccess(request, "action", "0201000002");
		
		esign.setEsignSync(request);
        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version); 
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    model.addAttribute("data", data);
	    model.addAttribute("fullaccess", fullaccess);
	    
	    if(user_id.equals(key)) {
        	if(hasaccess.equals("1")) {
        		if(data.size()<1) {
        			home.writelog(request, "Akses : Digital Signature > Form Registrasi");	
        		}else {
        			home.writelog(request, "Akses : Data registrasi digital signature ("+data.get(0).getesign_nama()+")");				
        		}
        		return "apps/esign/register/form";
        	}else {
        		return "redirect:/";
        	}	    	
	    }else {
        	if(fullaccess.equals("1")) {
        		if(data.size()<1) {
        			home.writelog(request, "Akses : Digital Signature > Form Registrasi");	
        		}else {
        			home.writelog(request, "Akses : Data registrasi digital signature ("+data.get(0).getesign_nama()+")");				
        		}
        		return "apps/esign/register/form";
        	}else {
        		return "redirect:/";
        	}
	    	
	    }
    }
	
	@RequestMapping(value = "/register/save", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> saveEsign(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = esign.saveEsign(request);
			return new ResponseEntity<>(result, HttpStatus.OK);			
		}
	}
    
	@RequestMapping(value = "/register/cek", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> cekEsign(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = esign.cekEsign(request);
			return new ResponseEntity<>(result, HttpStatus.OK);			
		}
	}
	
	@GetMapping("/doc/list")
    public String getListDoc(Model model, Authentication authentication, HttpServletRequest request) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		String hasAccess = home.hasAccess(request, "menu", "02020000");
		
		model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    model.addAttribute("roleActionTop", options.roleAction(request, "02020000", "0"));
	    model.addAttribute("roleActionTable", options.roleAction(request, "02020000", "1"));
        
	    if(hasAccess.equals("1")) {
			home.writelog(request, "Akses : Digital Signature > Dokumen");
	        return "apps/esign/document/list";
	    }else {
	    	return "redirect:/";
	    }

    }

	@GetMapping("/doc/table")
    public ResponseEntity<Map<String, Object>> getTableEsignDoc(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<EsignDoc> listRest = esign.getEsignDocList(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }
	
    @GetMapping("/doc/form/{key}")
    public String getFormEsignDoc(@PathVariable("key") String key, Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		String hasAccess = "0";
		hasAccess = home.hasAccess(request, "action", "0202000001");
		List<EsignDoc> data = esign.getEsignDocData(key);
		
        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version); 
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("data", data);
	    model.addAttribute("userMenu", users.getMenu(request));
	    
	    if(hasAccess.equals("1")) {
			home.writelog(request, "Akses : Digital Signature > Dokumen > Tambah");
	        return "apps/esign/document/form";
	    }else {
	    	return "redirect:/";
	    }
    }
	
	@RequestMapping(value = "/doc/save", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> saveDocument(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = esign.saveEsignDoc(request);
			return new ResponseEntity<>(result, HttpStatus.OK);			
		}
	}

	@RequestMapping(value = "/doc/delete", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> deleteDocument(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = esign.deleteEsignDoc(request);
			return new ResponseEntity<>(result, HttpStatus.OK);			
		}
	}

	@RequestMapping(value = "/doc/sign", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> signDocument(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = esign.signEsignDoc(request);
			return new ResponseEntity<>(result, HttpStatus.OK);			
		}
	}
	
	@GetMapping("/doc/preview/{id}")
	public ResponseEntity<InputStreamResource> previewFile(@PathVariable("id") String id) {
		return esign.preview(id);
	}

}
