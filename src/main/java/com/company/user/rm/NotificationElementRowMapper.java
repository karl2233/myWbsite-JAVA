package com.company.user.rm;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.company.user.model.NotificationElement;
public class NotificationElementRowMapper implements  RowMapper < NotificationElement > {


	@Override
	public NotificationElement mapRow(ResultSet rs, int rowNum) throws SQLException {
		NotificationElement notificationElement = new NotificationElement();
		notificationElement.setNotificationId(rs.getInt("notificationId"));
		notificationElement.setNotificationHeader(rs.getString("notifiation_header"));
		notificationElement.setNotificationBody(rs.getString("notification_body"));
		notificationElement.setNotificationCheck(rs.getBoolean("notificationCheck"));
		
        return notificationElement;
	}
}