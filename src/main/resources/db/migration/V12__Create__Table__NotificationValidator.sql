CREATE TABLE IF NOT EXISTS `NotoficationValidator` (
  `notifId` bigint(20) NOT NULL ,
  `userid` bigint(20) NOT NULL ,
  `notificationCheck` bit(1) DEFAULT NULL,
 CONSTRAINT `fk_notif_Id` FOREIGN KEY (`notifId`) REFERENCES `Notification` (`notificationId`),
 CONSTRAINT `fk_user_Id` FOREIGN KEY (`userid`) REFERENCES `users` (`id`)
);