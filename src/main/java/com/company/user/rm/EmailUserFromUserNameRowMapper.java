package com.company.user.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EmailUserFromUserNameRowMapper implements  RowMapper <String> {

	@Override
	public String  mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getString("user_email");
	}
}