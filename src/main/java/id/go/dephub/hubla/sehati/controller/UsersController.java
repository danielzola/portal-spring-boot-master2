package id.go.dephub.hubla.sehati.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.dao.OptionDao;
import id.go.dephub.hubla.sehati.dao.UserDao;
import id.go.dephub.hubla.sehati.model.Role;
import id.go.dephub.hubla.sehati.model.User;

@Controller
@RequestMapping("users")
public class UsersController {
	@Autowired
	HomeDao home;

	@Autowired
	UserDao users;

	@Autowired
	OptionDao options;

    @GetMapping("/pegawai/list")
    public String getUsersPegawaiList(Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		String hasAccess = home.hasAccess(request, "menu", "01010100");

        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    model.addAttribute("roleActionTop", options.roleAction(request, "01010100", "0"));
	    model.addAttribute("roleActionTable", options.roleAction(request, "01010100", "1"));

	    if(hasAccess.equals("1")) {
			home.writelog(request, "Akses : User Manajemen > Pengguna > Pegawai");
	        return "apps/users/pegawai/list";
	    }else {
	    	return "redirect:/";
	    }
    }

	@GetMapping("/pegawai/table")
    public ResponseEntity<Map<String, Object>> getUserPegawaiTable(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<User> listRest = users.getPegawaiList();
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }

    @GetMapping("/pegawai/form/{key}")
    public String getUsersPegawaiForm(@PathVariable("key") String key, Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		List<User> data = users.getUser(key);
		String hasAccess = home.hasAccess(request, "action", "0101010001");

        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    model.addAttribute("data", data);

	    if(hasAccess.equals("1")) {
			home.writelog(request, "Akses : Data pegawai "+data.get(0).getuser_fullname()+"");
	        return "apps/users/pegawai/form";
	    }else {
	    	return "redirect:/";
	    }
    }

	@RequestMapping(value = "/pegawai/save", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> savePegawai(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String savePegawai = users.savePegawai(request);
			return new ResponseEntity<>(savePegawai, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/pegawai/delete", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> deletePegawai(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String savePegawai = users.deletePegawai(request);
			return new ResponseEntity<>(savePegawai, HttpStatus.OK);
		}
	}

    @GetMapping("/perusahaan/list")
    public String getUsersPerusahaanList(Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		String hasAccess = home.hasAccess(request, "menu", "01010200");

        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    model.addAttribute("roleActionTop", options.roleAction(request, "01010200", "0"));
	    model.addAttribute("roleActionTable", options.roleAction(request, "01010200", "1"));

	    if(hasAccess.equals("1")) {
			home.writelog(request, "Akses : User Manajemen > Pengguna > Perusahaan");
	        return "apps/users/perusahaan/list";
	    }else {
	    	return "redirect:/";
	    }
    }

	@GetMapping("/perusahaan/table")
    public ResponseEntity<Map<String, Object>> getUserPerusahaanTable(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<User> listRest = users.getPerusahaanList();
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }

    @GetMapping("/perusahaan/form/{key}")
    public String getUsersPerusahaanForm(@PathVariable("key") String key, Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		List<User> data = users.getUser(key);
		String hasAccess = home.hasAccess(request, "action", "0101020001");

        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    model.addAttribute("data", data);

	    if(hasAccess.equals("1")) {
			home.writelog(request, "Akses : Data perusahaan ("+data.get(0).getcompany_name()+")");
	        return "apps/users/perusahaan/form";
	    }else {
	    	return "redirect:/";
	    }
    }


    @GetMapping("/role/list")
    public String getRoleList(Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		String hasAccess = home.hasAccess(request, "menu", "01020000");

        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    model.addAttribute("roleActionTop", options.roleAction(request, "01020000", "0"));
	    model.addAttribute("roleActionTable", options.roleAction(request, "01020000", "1"));

	    if(hasAccess.equals("1")) {
			home.writelog(request, "Akses : User Manajemen > Hak Akses");
	        return "apps/users/role/list";
	    }else {
	    	return "redirect:/";
	    }
    }

	@GetMapping("/role/table")
    public ResponseEntity<Map<String, Object>> getRoleTable(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<Role> listRest = users.getRoleList();
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }

    @GetMapping("/role/form/{key}")
    public String getUsersRoleForm(@PathVariable("key") String key, Model model, Authentication authentication, HttpServletRequest request, HttpSession session) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		List<Role> data = users.getRole(key);
		String hasAccess = "1";
		if(String.valueOf(key).equals("0")) {
			hasAccess = home.hasAccess(request, "action", "0102000001");
		}else {
			hasAccess = home.hasAccess(request, "action", "0102000002");
		}

        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
	    model.addAttribute("key", key);
	    model.addAttribute("data", data);

	    if(hasAccess.equals("1")) {
	    	if(String.valueOf(key).equals("0")) {
				home.writelog(request, "Akses : User Manajemen > Hak Akses > Tambah");
	    	}else {
				home.writelog(request, "Akses : Data hak akses ("+data.get(0).getrole_name()+")");
	    	}
	        return "apps/users/role/form";
	    }else {
	    	return "redirect:/";
	    }
    }

	@RequestMapping(value = "/role/save", method = {RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> saveRole(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String saveRole = users.saveRole(request);
			return new ResponseEntity<>(saveRole, HttpStatus.OK);
		}
	}
}
