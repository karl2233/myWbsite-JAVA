package com.company.user.repository;


import java.util.List;

import org.springframework.dao.IncorrectResultSizeDataAccessException;

import com.company.user.model.CheckoutListElements;
import com.company.user.model.NotificationElement;

public interface UserRepositoryImpl {
public List<NotificationElement> getNotificationList(int userId , int index) throws Exception ;
public int getUserIdByUsername(String userName);
public boolean checkNotification(int userId);
public int getLastNotification() throws Exception;
public List<CheckoutListElements> getListCheckout(int userId);
public String getEmailFromUserName(String userNmae);
public int paymentComplete(int projectId);
public int updateNotification(int userId,int notificationId);
public int getIdByUserName(String userName);
}
