package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	private int role_id;
	private String role_name, role_status, role_desc;
	
	public int getrole_id() {
        return role_id;
    }

    public void setrole_id(int role_id) {
        this.role_id = role_id;
    }

	public String getrole_name() {
        return role_name;
    }

    public void setrole_name(String role_name) {
        this.role_name = role_name;
    }

	public String getrole_status() {
        return role_status;
    }

    public void setrole_status(String role_status) {
        this.role_status = role_status;
    }
    
	public String getrole_desc() {
        return role_desc;
    }

    public void setrole_desc(String role_desc) {
        this.role_desc = role_desc;
    }
    
}