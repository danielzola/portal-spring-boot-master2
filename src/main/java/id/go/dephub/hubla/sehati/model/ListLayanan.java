package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class ListLayanan implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id, username, key_, name_;
	private int order_;
	
	public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }
	
	public String getusername() {
        return username;
    }

    public void setusername(String username) {
        this.username = username;
    }
	
	public String getkey_() {
        return key_;
    }

    public void setkey_(String key_) {
        this.key_ = key_;
    }
	
	public String getname_() {
        return name_;
    }

    public void setname_(String name_) {
        this.name_ = name_;
    }
    
	public int getorder_() {
        return order_;
    }

    public void setorder_(int order_) {
        this.order_ = order_;
    }

}
