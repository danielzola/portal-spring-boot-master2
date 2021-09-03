package id.go.dephub.hubla.sehati.service;

import javax.servlet.http.HttpServletRequest;

public interface RegisterService {
	public String SetRegister(HttpServletRequest request);
	public void SetUser(String username, HttpServletRequest request);
	public String CekActivate(String reset_key);
	public String SetActivate(HttpServletRequest request);
	public String SetForgotPass(HttpServletRequest request);
	public String SetResetPass(HttpServletRequest request);
}
