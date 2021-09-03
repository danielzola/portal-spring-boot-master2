package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.ActivityList;

public class ActivityListMapper implements RowMapper<ActivityList>{
	@Override
    public ActivityList mapRow(ResultSet rs, int rowNum) throws SQLException {
		ActivityList rest = new ActivityList();
        rest.setlog_id(rs.getString("log_id"));
        rest.setlog_desc(rs.getString("log_desc"));
        rest.setlog_date(rs.getString("log_date"));
        rest.setlog_time(rs.getTimestamp("log_time"));
        rest.setlog_ip(rs.getString("log_ip"));
        return rest;
    }
}

