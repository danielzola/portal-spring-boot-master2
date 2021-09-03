package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class Menu implements Serializable{
	private static final long serialVersionUID = 1L;
	private int role_id;
	private String menu_id, menu_parent, menu_name, menu_url, menu_icon, menu_status, menu_level, menu_counter;
	
	public int getrole_id() {
        return role_id;
    }

    public void setrole_id(int role_id) {
        this.role_id = role_id;
    }
    
	public String getmenu_id() {
        return menu_id;
    }

    public void setmenu_id(String menu_id) {
        this.menu_id = menu_id;
    }
	
	public String getmenu_parent() {
        return menu_parent;
    }

    public void setmenu_parent(String menu_parent) {
        this.menu_parent = menu_parent;
    }
	
	public String getmenu_name() {
        return menu_name;
    }

    public void setmenu_name(String menu_name) {
        this.menu_name = menu_name;
    }
	
	public String getmenu_url() {
        return menu_url;
    }

    public void setmenu_url(String menu_url) {
        this.menu_url = menu_url;
    }
	
	public String getmenu_icon() {
        return menu_icon;
    }

    public void setmenu_icon(String menu_icon) {
        this.menu_icon = menu_icon;
    }
	
	public String getmenu_status() {
        return menu_status;
    }

    public void setmenu_status(String menu_status) {
        this.menu_status = menu_status;
    }
	
	public String getmenu_level() {
        return menu_level;
    }

    public void setmenu_level(String menu_level) {
        this.menu_level = menu_level;
    }
	
	public String getmenu_counter() {
        return menu_counter;
    }

    public void setmenu_counter(String menu_counter) {
        this.menu_counter = menu_counter;
    }
    
        
}