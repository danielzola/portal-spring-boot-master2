package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class Home implements Serializable{
	private static final long serialVersionUID = 1L;
	String init_name,init_value;
	
	public String getInitValue() {
        return init_value;
    }
    public void setInitValue(String init_value) {
        this.init_value = init_value;
    }
    
}
