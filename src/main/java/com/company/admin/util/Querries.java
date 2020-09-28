package com.company.admin.util;

public class Querries {
	public static final String insertInNotification ="INSERT INTO mywebsite_schema.notification ( notifiation_header, notification_body)\n" + 
			"VALUES (?, ?)";
	
	public static final String insertInNotoficationvalidator ="INSERT INTO mywebsite_schema.notoficationvalidator ( notifId, userid) \n" + 
			"VALUES (?,?)";
	
	public static final String getLastInsert = "SELECT  LAST_INSERT_ID();";
	
	public static final String getUsersById = "SELECT id from users;";
	
	public static final String getListofNotification = "SELECT * FROM mywebsite_schema.notification ORDER BY notificationId DESC LIMIT ?,15;";
	
	public static final String insertNotificationValid = "INSERT INTO mywebsite_schema.notoficationvalidator ( notifId, userid,notificationCheck)\n" + 
			"VALUES (?,?,?)";
	
	public static final String insertProject = "INSERT INTO mywebsite_schema.transactionproject (userid,ProjectName,ProjectPayed,ProjectPrice) \n" + 
			"			VALUES (?,?,?,?);";
	public static final String getUserIdByUserName = "SELECT id FROM mywebsite_schema.users WHERE user_name=?;";
}
