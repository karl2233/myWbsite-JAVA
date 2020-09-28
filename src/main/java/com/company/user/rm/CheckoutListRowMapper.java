package com.company.user.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.user.model.CheckoutListElements;
import com.company.user.model.NotificationElement;

public class CheckoutListRowMapper implements RowMapper < CheckoutListElements > {


	@Override
	public CheckoutListElements mapRow(ResultSet rs, int rowNum) throws SQLException {
		CheckoutListElements notificationElement = new CheckoutListElements();
		notificationElement.setProjectId(rs.getInt("ProjectId"));
		notificationElement.setProjectName(rs.getString("ProjectName"));
		notificationElement.setProjectPayed(rs.getBoolean("ProjectPayed"));
		notificationElement.setProjectPrice(rs.getDouble("ProjectPrice"));
		
		return notificationElement;
		//notificationElement.setNotificationId(rs.getInt("notificationId"));
	}
}