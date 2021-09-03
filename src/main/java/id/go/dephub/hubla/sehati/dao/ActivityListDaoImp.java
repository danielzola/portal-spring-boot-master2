package id.go.dephub.hubla.sehati.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import id.go.dephub.hubla.sehati.mapper.ActivityListMapper;
import id.go.dephub.hubla.sehati.model.ActivityList;

@Repository
public class ActivityListDaoImp implements ActivityListDao{
	@Autowired
	@Qualifier("portalJdbcTemplate")
	private JdbcTemplate portalTemplate;

	@Autowired
	@Qualifier("workflowJdbcTemplate")
	private JdbcTemplate workflowTemplate;

	@Override
	public List<ActivityList> getActivityList(String userid) {
	    String sql = "select a.log_id::varchar log_id, a.log_desc, TO_CHAR(a.log_date,'DD-MM-YYYY HH24:MI:SS') log_date, a.log_date log_time, a.log_ip "
	    		   + "from portal.tx_log a "
	    		   + "left join portal.tm_users b on a.user_id=b.user_id "
	    		   + "where b.user_name=? order by a.log_date desc limit 100";
	    @SuppressWarnings("deprecation")
		List<ActivityList> rest = portalTemplate.query(sql, new Object[] {userid}, new ActivityListMapper());
	    
	    String sql2 = "select a.id log_id, 'Layanan : '||a.path log_desc, TO_CHAR(a.access_time,'DD-MM-YYYY HH24:MI:SS') log_date, a.access_time log_time, a.client_address log_ip "
	    		    + "from ext_access_trail a "
	    		    + "where a.username=?  order by a.access_time desc limit 100";
	    @SuppressWarnings("deprecation")
		List<ActivityList> rest2 = workflowTemplate.query(sql2, new Object[] {userid}, new ActivityListMapper());
	    
        List<ActivityList> joinedList = new ArrayList<>();
        Stream.of(rest, rest2).forEach(joinedList::addAll);
        return joinedList;
	}

}
