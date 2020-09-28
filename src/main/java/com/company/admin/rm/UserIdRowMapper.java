package com.company.admin.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.admin.model.UserById;
public class UserIdRowMapper implements  RowMapper < UserById > {


	@Override
	public UserById mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserById userById = new UserById();
		userById.setUserId(rs.getInt("id"));
        return userById;
	}
}