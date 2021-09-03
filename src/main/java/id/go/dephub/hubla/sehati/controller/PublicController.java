package id.go.dephub.hubla.sehati.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.dao.UserDao;
import id.go.dephub.hubla.sehati.service.FilesStorageService;
import id.go.dephub.hubla.sehati.service.RegisterService;

@Controller
public class PublicController {

    @Autowired
	HomeDao home;

    @Autowired
	RegisterService register;

    @Autowired
    FilesStorageService storageService;

	@Autowired
	UserDao users;

	@GetMapping("/portal")
    public String getLoginPage(Model model, HttpServletRequest request, HttpSession session) {

		String sessionLoginTime=(String)request.getSession().getAttribute("sessionLoginTime");
		if(sessionLoginTime!=null && sessionLoginTime.length()>0) {
			users.setLogout(request, session);
		}
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");

        model.addAttribute("baseUrl", home.baseUrl(request));
        model.addAttribute("urls", home.authorizationRequestBaseUri());
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);

        return "public/home";
    }

	@GetMapping("/map")
	public String getGisMap(Model model, HttpServletRequest request) {
		model.addAttribute("baseUrl", home.baseUrl(request));
		return "public/dashboard";
	}

	@PostMapping("/verifysign")
    public String getVerifySign(Model model, HttpServletRequest request, @RequestParam("pathPDF") String file) {

		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		Map<String, String> dokumen = storageService.verifySign(file);

        model.addAttribute("baseUrl", home.baseUrl(request));
        model.addAttribute("urls", home.authorizationRequestBaseUri());
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
	    model.mergeAttributes(dokumen);

        return "public/verifysign";
    }

	@GetMapping("/register")
	public String getRegister(Model model, HttpServletRequest request) {
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");

        model.addAttribute("baseUrl", home.baseUrl(request));
        model.addAttribute("urls", home.authorizationRequestBaseUri());
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
		return "public/register";
	}

	@GetMapping("/activate/{key}")
	public String getActivate(@PathVariable("key") String key, Model model, HttpServletRequest request) {
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");

        model.addAttribute("key", key);
        model.addAttribute("validkey", register.CekActivate(key));
        model.addAttribute("baseUrl", home.baseUrl(request));
        model.addAttribute("urls", home.authorizationRequestBaseUri());
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
		return "public/activate";
	}

	@GetMapping("/forgotpass")
	public String getForgotPass(Model model, HttpServletRequest request) {
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");

        model.addAttribute("baseUrl", home.baseUrl(request));
        model.addAttribute("urls", home.authorizationRequestBaseUri());
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
		return "public/forgotpass";
	}

	@GetMapping("/resetpass/{key}")
	public String getResetPass(@PathVariable("key") String key, Model model, HttpServletRequest request) {
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");

        model.addAttribute("key", key);
        model.addAttribute("validkey", register.CekActivate(key));
        model.addAttribute("baseUrl", home.baseUrl(request));
        model.addAttribute("urls", home.authorizationRequestBaseUri());
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
		return "public/resetpass";
	}

	@RequestMapping(value = "/register2", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> setRegister(HttpServletRequest request) {
		String SetRegister = register.SetRegister(request);
		return new ResponseEntity<>(SetRegister, HttpStatus.OK);
	}

	@RequestMapping(value = "/activate", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> setActivate(HttpServletRequest request) {
		String SetActivate = register.SetActivate(request);
		return new ResponseEntity<>(SetActivate, HttpStatus.OK);
	}

	@RequestMapping(value = "/forgotpass", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> setForgotPass(HttpServletRequest request) {
		String SetForgotPass = register.SetForgotPass(request);
		return new ResponseEntity<>(SetForgotPass, HttpStatus.OK);
	}

	@RequestMapping(value = "/resetpass", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> setResetPass(HttpServletRequest request) {
		String SetActivate = register.SetResetPass(request);
		return new ResponseEntity<>(SetActivate, HttpStatus.OK);
	}

	@GetMapping("/index.jsp")
	public String getVerifypdf(Model model, HttpServletRequest request) {
		return "public/test";
	}

	@GetMapping("/verifysign")
	public String getverifypdf(Model model, HttpServletRequest request, @RequestParam(name = "file") String file) {
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		Map<String, String> dokumen = storageService.verifySignScan(file);

        model.addAttribute("baseUrl", home.baseUrl(request));
        model.addAttribute("urls", home.authorizationRequestBaseUri());
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
	    model.mergeAttributes(dokumen);

        return "public/verifysign";
	}

}
