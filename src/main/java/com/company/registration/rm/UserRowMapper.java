package com.company.registration.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.registration.model.User;


public class UserRowMapper implements  RowMapper < User > {


	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
        user.setUserName(rs.getString("user_name"));
        
        return user;
	}
}
