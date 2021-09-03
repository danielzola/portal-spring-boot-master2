package id.go.dephub.hubla.sehati.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import id.go.dephub.hubla.sehati.dao.DashboardDao;
import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.dao.UserDao;
import id.go.dephub.hubla.sehati.model.DashboardKeyValue;
import id.go.dephub.hubla.sehati.model.DashboardPelabuhan;
import id.go.dephub.hubla.sehati.model.User;

@Controller
@RequestMapping("dashboard")
public class DashboardController {
	
	@Autowired
	HomeDao home;

	@Autowired
	UserDao users;

	@Autowired
	DashboardDao dashboard;
	
	@GetMapping("/pelabuhan")
    public String getDsbPelabuhan(Model model, Authentication authentication, HttpServletRequest request) {

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
        
	    home.writelog(request, "Akses : Dashboard > Pelabuhan");
        return "apps/dashboard/pelabuhan";
    }
	
	@RequestMapping(value = "/pelabuhan/jumlah", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> getPelabuhanJumlah(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = dashboard.getDsbPelabuhanJml(request);
			return new ResponseEntity<>(result, HttpStatus.OK);		
		}
	}

	@RequestMapping(value = "/pelabuhan/satker", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public ResponseEntity<Map<String, Object>> getPelabuhanSatker(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardKeyValue> listRest = dashboard.getDsbPelabuhanSatker(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }

	@GetMapping("/pelabuhan/propinsi")
    public ResponseEntity<Map<String, Object>> getPelabuhanPropinsi(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardKeyValue> listRest = dashboard.getDsbPelabuhanPropinsi(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }
	
	@RequestMapping(value = "/pelabuhan/table", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public ResponseEntity<Map<String, Object>> getPelabuhanTable(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardPelabuhan> listRest = dashboard.getDsbPelabuhanTable(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }

	@RequestMapping(value = "/pelabuhan/kapal", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public ResponseEntity<Map<String, Object>> getPelabuhanKapal(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardPelabuhan> listRest = dashboard.getDsbPelabuhanKapal(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }
	
	@GetMapping("/kapal")
    public String getDsbKapal(Model model, Authentication authentication, HttpServletRequest request) {

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
        
	    home.writelog(request, "Akses : Dashboard > Kapal");
        return "apps/dashboard/kapal";
    }
	
	@RequestMapping(value = "/kapal/jumlah", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> getKapalJumlah(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = dashboard.getDsbKapalJml(request);
			return new ResponseEntity<>(result, HttpStatus.OK);		
		}
	}
	
	@RequestMapping(value = "/kapal/bendera", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public ResponseEntity<Map<String, Object>> getKapalBendera(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardKeyValue> listRest = dashboard.getDsbKapalBendera(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }
	
	@GetMapping("/kapal/tahun")
    public ResponseEntity<Map<String, Object>> getKapalTahun(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardKeyValue> listRest = dashboard.getDsbKapalTahun(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }

	@RequestMapping(value = "/kapal/jenis", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public ResponseEntity<Map<String, Object>> getKapalJenis(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardKeyValue> listRest = dashboard.getDsbKapalJenis(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }

	@RequestMapping(value = "/kapal/kategori", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public ResponseEntity<Map<String, Object>> getKapalKategori(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardKeyValue> listRest = dashboard.getDsbKapalKategori(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }

	@GetMapping("/tersustuks")
    public String getDsbTersus(Model model, Authentication authentication, HttpServletRequest request) {

        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String application_name = home.getInitByName("application_name");
		String application_description = home.getInitByName("application_description");
		String application_version = home.getInitByName("application_version");
		List<User> userdata = users.getUser(userDetails.getName());
		//dashboard.snycTersus();
		
        model.addAttribute("baseUrl", home.baseUrl(request));
	    model.addAttribute("application_name", application_name);
	    model.addAttribute("application_description", application_description);
	    model.addAttribute("application_version", application_version);
        model.addAttribute("userName", userDetails.getName());
	    model.addAttribute("userData", userdata);
	    model.addAttribute("userMenu", users.getMenu(request));
        
	    home.writelog(request, "Akses : Dashboard > Tersus & TUKS");
        return "apps/dashboard/tersus";
    }
	
	@RequestMapping(value = "/tersustuks/jumlah", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResponseEntity<String> getTersusJumlah(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return new ResponseEntity<>("", HttpStatus.FORBIDDEN);
		}else {
			String result = dashboard.getDsbTersusJml(request);
			return new ResponseEntity<>(result, HttpStatus.OK);		
		}
	}
	
	@RequestMapping(value = "/tersustuks/satker", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public ResponseEntity<Map<String, Object>> getTersusSatker(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardKeyValue> listRest = dashboard.getDsbPelabuhanSatker(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }

	@GetMapping("/tersustuks/propinsi")
    public ResponseEntity<Map<String, Object>> getTersusPropinsi(Authentication authentication, HttpServletRequest request) {
        DefaultOidcUser userDetails = (DefaultOidcUser) authentication.getPrincipal();
		String sessionUserName=(String)request.getSession().getAttribute("sessionUserName");
		if(userDetails.getName()==null || userDetails.getName().length()<1 || sessionUserName==null || sessionUserName.length()<1) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
		}else {
	        List<DashboardKeyValue> listRest = dashboard.getDsbTersusPropinsi(request);
	        Map<String, Object> map = new HashMap<>();
	        map.put("data", listRest);
	        return ResponseEntity.ok(map);
		}
    }


}
