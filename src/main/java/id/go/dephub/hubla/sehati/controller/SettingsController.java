package id.go.dephub.hubla.sehati.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.dao.UserDao;
import id.go.dephub.hubla.sehati.model.User;

@Controller
@RequestMapping("settings")
public class SettingsController {
	@Autowired
	HomeDao home;

	@Autowired
	UserDao users;

    @GetMapping("/password")
    public String getPassword(Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {
    	
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		
        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
        
		home.writelog(request, "Akses : Edit password");	    
        return "apps/settings/password";
    }

	@RequestMapping(value = "/password/save", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> savePassword(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String savePegawai = users.savePassword(request);
			return new ResponseEntity<>(savePegawai, HttpStatus.OK);			
		}
	}
    
    @GetMapping("/profile")
    public String getProfile(Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {
    	
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		
        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("data", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    
		home.writelog(request, "Akses : Edit profil");	    
        if(userdata.get(0).getemp_number()!=null) {
            return "apps/settings/profile_pegawai";        	
        }else {
            return "apps/settings/profile_perusahaan";        	        	
        }
    }
    
	@RequestMapping(value = "/profile/save", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> saveProfile(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String saveProfile = users.saveProfile(request);
			return new ResponseEntity<>(saveProfile, HttpStatus.OK);			
		}
	}
	
}
