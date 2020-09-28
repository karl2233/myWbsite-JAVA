package com.company.user.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class notificationChecker implements  RowMapper < Integer > {

	@Override
	public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt("notifId");
	}
}