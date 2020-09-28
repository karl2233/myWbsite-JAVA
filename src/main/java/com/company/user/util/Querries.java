package com.company.user.util;

public class Querries {
	public static final String getNotificationList = "SELECT notificationId, notifiation_header,notification_body,notoficationvalidator.userid,notoficationvalidator.notificationCheck \n" + 
			"FROM notification \n" + 
			"FULL JOIN notoficationvalidator\n" + 
			"ON notificationId = notoficationvalidator.notifId \n" + 
			"WHERE notoficationvalidator.userid = ?  ORDER BY notificationId DESC LIMIT ?,15;";
	
	public static final String getUserIdByUserName = "SELECT id FROM mywebsite_schema.users WHERE user_name=?;";
	
	public static final String notificationChecker = "SELECT EXISTS(SELECT * FROM mywebsite_schema.notoficationvalidator WHERE userid=? and notificationCheck = true) as checknotification;";
	
	public static final String lastNotificaitionElement = "SELECT notificationId FROM mywebsite_schema.notification ORDER BY notificationId ASC LIMIT 1;";
	
	public static final String getListOfCheckouts = "SELECT * FROM mywebsite_schema.transactionproject WHERE userId  = ? ORDER BY ProjectId DESC ;\n" ; 
	
	public static final String getEmailFromUserName = "SELECT user_email FROM mywebsite_schema.users WHERE user_name= ?";
	
	public static final String getIdFromUserName = "SELECT id FROM mywebsite_schema.users WHERE user_name= ?;";
    
	public static final String projectPayed = "UPDATE transactionproject \n" + 
			"SET ProjectPayed=true \n" + 
			"WHERE ProjectId=? ;";
	
	public static final String checkNotification = "UPDATE notoficationvalidator SET notificationCheck = true \n" + 
			"WHERE notifId = ? and userid = ?;";
}

