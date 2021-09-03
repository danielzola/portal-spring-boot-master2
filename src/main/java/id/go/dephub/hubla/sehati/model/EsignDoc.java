package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class EsignDoc  implements Serializable{
	private static final long serialVersionUID = 1L;
	private int doc_id, doc_by, user_create, user_update;
	private String doc_code, doc_desc, doc_type, doc_number, doc_date_start, doc_date_end, doc_file, doc_file_path, doc_file_sign, doc_file_sign_path;
	private String doc_file_page, doc_file_sign_id, doc_qr_page, doc_qr_position, doc_qr_x, doc_qr_y, doc_qr_size, doc_sign_use, doc_sign_page, doc_sign_size;
	private String doc_sign_x, doc_sign_y, doc_status, date_create, last_update;
	
	public int getdoc_id() { return doc_id; } public void setdoc_id(int doc_id) { this.doc_id = doc_id; }    
	public int getdoc_by() { return doc_by; } public void setdoc_by(int doc_by) { this.doc_by = doc_by; }
	public int getuser_create() { return user_create; } public void setuser_create(int user_create) { this.user_create = user_create; }
	public int getuser_update() { return user_update; } public void setuser_update(int user_update) { this.user_update = user_update; }

	public String getdoc_code() { return doc_code; } public void setdoc_code(String doc_code) { this.doc_code = doc_code; }    
	public String getdoc_desc() { return doc_desc; } public void setdoc_desc(String doc_desc) { this.doc_desc = doc_desc; }    
	public String getdoc_type() { return doc_type; } public void setdoc_type(String doc_type) { this.doc_type = doc_type; }    
	public String getdoc_number() { return doc_number; } public void setdoc_number(String doc_number) { this.doc_number = doc_number; }    
	public String getdoc_date_start() { return doc_date_start; } public void setdoc_date_start(String doc_date_start) { this.doc_date_start = doc_date_start; }    
	public String getdoc_date_end() { return doc_date_end; } public void setdoc_date_end(String doc_date_end) { this.doc_date_end = doc_date_end; }    
	public String getdoc_file() { return doc_file; } public void setdoc_file(String doc_file) { this.doc_file = doc_file; }    
	public String getdoc_file_path() { return doc_file_path; } public void setdoc_file_path(String doc_file_path) { this.doc_file_path = doc_file_path; }    
	public String getdoc_file_sign() { return doc_file_sign; } public void setdoc_file_sign(String doc_file_sign) { this.doc_file_sign = doc_file_sign; }    
	public String getdoc_file_sign_path() { return doc_file_sign_path; } public void setdoc_file_sign_path(String doc_file_sign_path) { this.doc_file_sign_path = doc_file_sign_path; }    
	public String getdoc_file_page() { return doc_file_page; } public void setdoc_file_page(String doc_file_page) { this.doc_file_page = doc_file_page; }    
	public String getdoc_file_sign_id() { return doc_file_sign_id; } public void setdoc_file_sign_id(String doc_file_sign_id) { this.doc_file_sign_id = doc_file_sign_id; }    
	public String getdoc_qr_page() { return doc_qr_page; } public void setdoc_qr_page(String doc_qr_page) { this.doc_qr_page = doc_qr_page; }    
	public String getdoc_qr_position() { return doc_qr_position; } public void setdoc_qr_position(String doc_qr_position) { this.doc_qr_position = doc_qr_position; }    
	public String getdoc_qr_x() { return doc_qr_x; } public void setdoc_qr_x(String doc_qr_x) { this.doc_qr_x = doc_qr_x; }    
	public String getdoc_qr_y() { return doc_qr_y; } public void setdoc_qr_y(String doc_qr_y) { this.doc_qr_y = doc_qr_y; }    
	public String getdoc_qr_size() { return doc_qr_size; } public void setdoc_qr_size(String doc_qr_size) { this.doc_qr_size = doc_qr_size; }    
	public String getdoc_sign_use() { return doc_sign_use; } public void setdoc_sign_use(String doc_sign_use) { this.doc_sign_use = doc_sign_use; }    
	public String getdoc_sign_page() { return doc_sign_page; } public void setdoc_sign_page(String doc_sign_page) { this.doc_sign_page = doc_sign_page; }    
	public String getdoc_sign_size() { return doc_sign_size; } public void setdoc_sign_size(String doc_sign_size) { this.doc_sign_size = doc_sign_size; }    
	public String getdoc_sign_x() { return doc_sign_x; } public void setdoc_sign_x(String doc_sign_x) { this.doc_sign_x = doc_sign_x; }    
	public String getdoc_sign_y() { return doc_sign_y; } public void setdoc_sign_y(String doc_sign_y) { this.doc_sign_y = doc_sign_y; }    
	public String getdoc_status() { return doc_status; } public void setdoc_status(String doc_status) { this.doc_status = doc_status; }    
	public String getdate_create() { return date_create; } public void setdate_create(String date_create) { this.date_create = date_create; }    
	public String getlast_update() { return last_update; } public void setlast_update(String last_update) { this.last_update = last_update; }    
}