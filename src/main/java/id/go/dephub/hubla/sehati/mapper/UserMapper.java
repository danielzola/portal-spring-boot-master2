package id.go.dephub.hubla.sehati.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import id.go.dephub.hubla.sehati.model.User;

public class UserMapper implements RowMapper<User>{
	@Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User rest = new User();
        rest.setuser_id(rs.getInt("user_id"));
        rest.setuser_name(rs.getString("user_name"));
        rest.setuser_type(rs.getString("user_type"));
        rest.setuser_fullname(rs.getString("user_fullname"));
        rest.setuser_nik(rs.getString("user_nik"));
        rest.setuser_hp(rs.getString("user_hp"));
        rest.setuser_email(rs.getString("user_email"));
        rest.setuser_photo(rs.getString("user_photo"));
        rest.setuser_last_login(rs.getString("user_last_login"));
        rest.setuser_status(rs.getString("user_status"));
        rest.setemp_name(rs.getString("emp_name"));
        rest.setemp_number(rs.getString("emp_number"));
        rest.setemp_hp(rs.getString("emp_hp"));
        rest.setemp_birth_date(rs.getString("emp_birth_date"));
        rest.setemp_gender(rs.getString("emp_gender"));
        rest.setunit_code(rs.getString("unit_code"));
        rest.setunit_name(rs.getString("unit_name"));
        rest.setoffice_code(rs.getString("office_code"));
        rest.setoffice_name(rs.getString("office_name"));
        rest.setgroup_date(rs.getString("group_date"));
        rest.setgroup_name(rs.getString("group_name"));
        rest.setgrade_name(rs.getString("grade_name"));
        rest.setposition_name(rs.getString("position_name"));
        rest.setrole_id(rs.getString("role_id"));
        rest.setrole_name(rs.getString("role_name"));
        rest.setcompany_id(rs.getInt("company_id"));
        rest.setcompany_nib(rs.getString("company_nib"));
        rest.setcompany_name(rs.getString("company_name"));
        rest.setcompany_address(rs.getString("company_address"));
        rest.setcompany_phone(rs.getString("company_phone"));
        rest.setcompany_email(rs.getString("company_email"));
        rest.setcompany_taxno(rs.getString("company_taxno"));
        rest.setcompany_type(rs.getString("company_type"));
        rest.setcompany_type_name(rs.getString("company_type_name"));
        return rest;
    }
}