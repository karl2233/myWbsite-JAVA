package com.company.registration.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.registration.model.User;
import com.company.registration.model.UserInformation;

public class UserInformationRowMapper implements  RowMapper < UserInformation > {


	@Override
	public UserInformation mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserInformation userInformation = new UserInformation();
		userInformation.setUsername(rs.getString("user_name"));
		userInformation.setUserEmail(rs.getString("user_email"));
		userInformation.setDurationActivate(rs.getString("duration_activate"));
        
        return userInformation;
	}
}