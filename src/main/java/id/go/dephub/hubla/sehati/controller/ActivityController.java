package id.go.dephub.hubla.sehati.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import id.go.dephub.hubla.sehati.dao.ActivityListDao;
import id.go.dephub.hubla.sehati.dao.HomeDao;
import id.go.dephub.hubla.sehati.dao.UserDao;
import id.go.dephub.hubla.sehati.model.ActivityList;

@Controller
@RequestMapping("activity")
public class ActivityController {
	@Autowired
	HomeDao home;

	@Autowired
	UserDao users;
	
	@Autowired
	ActivityListDao activitylist;

	@GetMapping("/data/{userid}")
    public ResponseEntity<Map<String, Object>> getActivityList(@PathVariable("userid") String userid, Authentication authentication) {
        List<ActivityList> listRest = activitylist.getActivityList(userid);
        Map<String, Object> map = new HashMap<>();
        map.put("data", listRest);
        return ResponseEntity.ok(map);
    }
}
