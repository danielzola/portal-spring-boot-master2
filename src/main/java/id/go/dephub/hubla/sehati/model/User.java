package id.go.dephub.hubla.sehati.model;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private int user_id, company_id;
	private String user_name, user_fullname, user_type, user_nik, user_hp, user_email, user_photo, user_last_login, user_status;
	private String emp_name, emp_number, emp_birth_date, emp_gender, office_code, office_name, unit_code, unit_name;
	private String group_date, group_name, grade_name, position_name, emp_hp;
	private String role_id, role_name;
	private String company_nib, company_name, company_address, company_phone, company_email, company_taxno, company_type, company_type_name;
	
	public int getuser_id() {
        return user_id;
    }

    public void setuser_id(int user_id) {
        this.user_id = user_id;
    }

	public int getcompany_id() {
        return company_id;
    }

    public void setcompany_id(int company_id) {
        this.company_id = company_id;
    }

	public String getcompany_type() {
        return company_type;
    }

    public void setcompany_type(String company_type) {
        this.company_type = company_type;
    }


	public String getcompany_type_name() {
        return company_type_name;
    }

    public void setcompany_type_name(String company_type_name) {
        this.company_type_name = company_type_name;
    }

	public String getcompany_taxno() {
        return company_taxno;
    }

    public void setcompany_taxno(String company_taxno) {
        this.company_taxno = company_taxno;
    }

	public String getcompany_email() {
        return company_email;
    }

    public void setcompany_email(String company_email) {
        this.company_email = company_email;
    }

	public String getcompany_phone() {
        return company_phone;
    }

    public void setcompany_phone(String company_phone) {
        this.company_phone = company_phone;
    }

	public String getcompany_address() {
        return company_address;
    }

    public void setcompany_address(String company_address) {
        this.company_address = company_address;
    }


	public String getcompany_name() {
        return company_name;
    }

    public void setcompany_name(String company_name) {
        this.company_name = company_name;
    }


	public String getcompany_nib() {
        return company_nib;
    }

    public void setcompany_nib(String company_nib) {
        this.company_nib = company_nib;
    }

	public String getuser_status() {
        return user_status;
    }

    public void setuser_status(String user_status) {
        this.user_status = user_status;
    }

	public String getemp_hp() {
        return emp_hp;
    }

    public void setemp_hp(String emp_hp) {
        this.emp_hp = emp_hp;
    }

	public String getgrade_name() {
        return grade_name;
    }

    public void setgrade_name(String grade_name) {
        this.grade_name = grade_name;
    }

	public String getgroup_name() {
        return group_name;
    }

    public void setgroup_name(String group_name) {
        this.group_name = group_name;
    }

	public String getgroup_date() {
        return group_date;
    }

    public void setgroup_date(String group_date) {
        this.group_date = group_date;
    }

	public String getunit_code() {
        return unit_code;
    }

    public void setunit_code(String unit_code) {
        this.unit_code = unit_code;
    }

	public String getoffice_code() {
        return office_code;
    }

    public void setoffice_code(String office_code) {
        this.office_code = office_code;
    }
        
	public String getemp_gender() {
        return emp_gender;
    }

    public void setemp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }
        
	public String getemp_birth_date() {
        return emp_birth_date;
    }

    public void setemp_birth_date(String emp_birth_date) {
        this.emp_birth_date = emp_birth_date;
    }
    
	public String getuser_last_login() {
        return user_last_login;
    }

    public void setuser_last_login(String user_last_login) {
        this.user_last_login = user_last_login;
    }
    
	public String getrole_id() {
        return role_id;
    }

    public void setrole_id(String role_id) {
        this.role_id = role_id;
    }
    
	public String getemp_name() {
        return emp_name;
    }

    public void setemp_name(String emp_name) {
        this.emp_name = emp_name;
    }
    
	public String getrole_name() {
        return role_name;
    }

    public void setrole_name(String role_name) {
        this.role_name = role_name;
    }
    
	public String getuser_name() {
        return user_name;
    }

    public void setuser_name(String user_name) {
        this.user_name = user_name;
    }
    
	public String getuser_fullname() {
        return user_fullname;
    }

    public void setuser_fullname(String user_fullname) {
        this.user_fullname = user_fullname;
    }
    
	public String getuser_type() {
        return user_type;
    }

    public void setuser_type(String user_type) {
        this.user_type = user_type;
    }
    
	public String getuser_nik() {
        return user_nik;
    }

    public void setuser_nik(String user_nik) {
        this.user_nik = user_nik;
    }
    
	public String getuser_hp() {
        return user_hp;
    }

    public void setuser_hp(String user_hp) {
        this.user_hp = user_hp;
    }
    
	public String getuser_email() {
        return user_email;
    }

    public void setuser_email(String user_email) {
        this.user_email = user_email;
    }
    
	public String getuser_photo() {
        return user_photo;
    }

    public void setuser_photo(String user_photo) {
        this.user_photo = user_photo;
    }
    
	public String getunit_name() {
        return unit_name;
    }

    public void setunit_name(String unit_name) {
        this.unit_name = unit_name;
    }
    
	public String getoffice_name() {
        return office_name;
    }

    public void setoffice_name(String office_name) {
        this.office_name = office_name;
    }
    
	public String getposition_name() {
        return position_name;
    }

    public void setposition_name(String position_name) {
        this.position_name = position_name;
    }

	public String getemp_number() {
        return emp_number;
    }

    public void setemp_number(String emp_number) {
        this.emp_number = emp_number;
    }
    
    
}
