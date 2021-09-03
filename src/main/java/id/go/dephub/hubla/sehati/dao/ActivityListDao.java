package id.go.dephub.hubla.sehati.dao;

import java.util.List;

import id.go.dephub.hubla.sehati.model.ActivityList;

public interface ActivityListDao {
	List<ActivityList> getActivityList(String userid);

}
