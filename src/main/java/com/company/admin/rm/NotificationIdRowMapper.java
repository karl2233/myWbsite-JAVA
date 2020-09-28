package com.company.admin.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.admin.model.UserById;

public class NotificationIdRowMapper implements  RowMapper < Integer > {


	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		return rs.getInt("LAST_INSERT_ID()");
		
//		UserById userById = new UserById();
//		userById.setUserId(rs.getInt("id"));
//        return userById;
	}
}