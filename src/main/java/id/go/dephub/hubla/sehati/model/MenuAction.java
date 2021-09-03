package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class MenuAction implements Serializable{
	private static final long serialVersionUID = 1L;
	private int role_id;
	private String menu_id, action_id, action_name, action_act, action_icon, action_class, action_intable, action_status;
	
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
	
	public String getaction_id() {
        return action_id;
    }

    public void setaction_id(String action_id) {
        this.action_id = action_id;
    }
	
	public String getaction_name() {
        return action_name;
    }

    public void setaction_name(String action_name) {
        this.action_name = action_name;
    }
	
	public String getaction_act() {
        return action_act;
    }

    public void setaction_act(String action_act) {
        this.action_act = action_act;
    }
	
	public String getaction_icon() {
        return action_icon;
    }

    public void setaction_icon(String action_icon) {
        this.action_icon = action_icon;
    }
	
	public String getaction_class() {
        return action_class;
    }

    public void setaction_class(String action_class) {
        this.action_class = action_class;
    }
	
	public String getaction_intable() {
        return action_intable;
    }

    public void setaction_intable(String action_intable) {
        this.action_intable = action_intable;
    }
	
	public String getaction_status() {
        return action_status;
    }

    public void setaction_status(String action_status) {
        this.action_status = action_status;
    }
    
        
}