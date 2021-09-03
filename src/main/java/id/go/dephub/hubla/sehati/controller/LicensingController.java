package id.go.dephub.hubla.sehati.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.dao.UserDao;
import id.go.dephub.hubla.sehati.model.User;

@Controller
@RequestMapping("licensing")
public class LicensingController {
	@Autowired
	HomeDao home;

	@Autowired
	UserDao users;
	
    @GetMapping("/form")
    public String getLicensingForm(Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {
    	
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
        
        return "apps/licensing/form";
    }
}
