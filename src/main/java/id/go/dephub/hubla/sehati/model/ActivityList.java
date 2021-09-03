package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class ActivityList implements Serializable{
	private static final long serialVersionUID = 1L;
	private String log_id, log_desc, log_date, log_ip;
	private Timestamp log_time;
	

	public String getlog_id() {
        return log_id;
    }

    public void setlog_id(String log_id) {
        this.log_id = log_id;
    }

	public Timestamp getlog_time() {
        return log_time;
    }

    public void setlog_time(Timestamp log_time) {
        this.log_time = log_time;
    }

	public String getlog_desc() {
        return log_desc;
    }

    public void setlog_desc(String log_desc) {
        this.log_desc = log_desc;
    }

	public String getlog_date() {
        return log_date;
    }

    public void setlog_date(String log_date) {
        this.log_date = log_date;
    }
    
	public String getlog_ip() {
        return log_ip;
    }

    public void setlog_ip(String log_ip) {
        this.log_ip = log_ip;
    }
    
}