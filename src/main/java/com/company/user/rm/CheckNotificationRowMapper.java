package com.company.user.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CheckNotificationRowMapper implements  RowMapper < Boolean  > {

	@Override
	public Boolean  mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getBoolean("checknotification");
	}
}