package com.company.registration.util;

public class Querries {
public static final String insertIntoUsers = "insert into users (user_name, user_email, password,salt,account_non_locked,token_activate,duration_activate)  "+ "values(?,?,?,?,?,?,?)" ;
public static final String insertIntoUser_Permission = "insert into user_permission (id_user, id_permission) " + "values(?,?)";
public static final String getLastInsert = "SELECT  LAST_INSERT_ID();";
public static final String selectUserByEmail = "select * from users where user_email =?";
public static final String selectUserByUserName ="select * from users where user_name=?";
public static final String selectUserByToken="SELECT * FROM mywebsite_schema.users WHERE token_activate =?";
public static final String activateUserAccount = "UPDATE mywebsite_schema.users \n" + 
		                                         "SET account_non_locked =?\n" + 
		                                         "WHERE token_activate =?";
public static final String  userByEmail ="SELECT users.user_name, users.password, users.salt,permission.description" +"\n"+
		"FROM users " +"\n"+
		"JOIN user_permission ON user_permission.id_user = users.id " +"\n"+ 
		"Join permission ON permission.id = user_permission.id_permission " +"\n"+ 
		"Where users.user_email = ? and account_non_locked=true";

public static final String  userByUsername ="SELECT users.user_name, users.password, users.salt,permission.description" +"\n"+
		"FROM users " +"\n"+
		"JOIN user_permission ON user_permission.id_user = users.id " +"\n"+ 
		"Join permission ON permission.id = user_permission.id_permission " +"\n"+ 
		"Where users.user_name = ?";
}
