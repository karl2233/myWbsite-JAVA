package com.company.admin.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.admin.model.NotificationElement;
import com.company.admin.model.UserById;

public class NotificationElementRowMapper implements  RowMapper < NotificationElement > {


	@Override
	public NotificationElement mapRow(ResultSet rs, int rowNum) throws SQLException {
		NotificationElement notificationElement = new NotificationElement();
		notificationElement.setNotificationHeader(rs.getString("notifiation_header"));
		notificationElement.setNotificationBody(rs.getString("notification_body"));
		notificationElement.setNotificationId(rs.getInt("notificationId"));
		
        return notificationElement;
	}
}