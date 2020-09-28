CREATE TABLE IF NOT EXISTS `Notification` (
  `notificationId` bigint(20) NOT NULL AUTO_INCREMENT,
  `notifiation_header` varchar(255) DEFAULT NULL,
  `notification_body` TEXT DEFAULT NULL,
 PRIMARY KEY (`notificationId`)
);

