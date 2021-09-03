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

import id.go.dephub.hubla.sehati.dao.DashboardDao;
import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.dao.UserDao;
import id.go.dephub.hubla.sehati.model.User;
import id.go.dephub.hubla.sehati.service.RegisterService;

@Controller
public class AppsController {

	@Autowired
	HomeDao home;

	@Autowired
	DashboardDao dashboard;
	
	@Autowired
	UserDao users;
		
	@Autowired
	RegisterService register;
	
    @GetMapping("/")
    public String getMain(Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {
    	
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
        register.SetUser(userDetails.getName(), request);
        
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		
		//Register For The First Session
		String sessionLoginTime=(String)request.getSession().getAttribute("sessionLoginTime");
		if(sessionLoginTime==null || sessionLoginTime.length()<1) {
			users.setLogin(userDetails.getName(), request, session);
		}
        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
        model.addAttribute("sessionLoginTime", sessionLoginTime);
	    model.addAttribute("userData", userdata);
	    model.addAttribute("counter", dashboard.getDsbLayananCounter(request));
	    model.addAttribute("userMenu", users.getMenu(request));
	    
        return "apps/main/main";
    }
    
    @GetMapping("/header")
    public String getHeader(Model model, Authentication authentication, HttpServletRequest request) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		String idToken = userDetails.getIdToken().getTokenValue();
		List<User> userdata = users.getUser(userDetails.getName());

        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
        model.addAttribute("idToken", idToken);
	    model.addAttribute("userData", userdata);
        
        return "apps/main/header";
    }
    
    @GetMapping("/options")
    public String getOptions(Model model, Authentication authentication, HttpServletRequest request) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		String idToken = userDetails.getIdToken().getTokenValue();
		List<User> userdata = users.getUser(userDetails.getName());

        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
        model.addAttribute("idToken", idToken);
	    model.addAttribute("userData", userdata);
        
        return "apps/main/options";
    }    
}