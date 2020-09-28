package com.company.admin.repository;

import java.util.List;

import com.company.admin.model.NotificationElement;
import com.company.admin.model.UserById;

public interface AdminRepository {
//public List<User> getUserElementsByUserName(String username);
public int insertNotification(String header,String body);
public List<UserById> getUsersById();
public List<NotificationElement> getNotificationElements(int indexposition);
public int getLastInsert();
public void insertNotificationForEachUser(int notificationId,int userId);
public int insertProject(int userId,String projectName,double price);
public int getUserIdByUsername(String userName);
}
