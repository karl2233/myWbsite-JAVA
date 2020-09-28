CREATE TABLE IF NOT EXISTS `TransactionProject` (
  `userId` bigint(20) DEFAULT NULL ,
  `ProjectId` bigint(20) NOT NULL AUTO_INCREMENT ,
  `ProjectName` varchar(255) DEFAULT NULL ,
  `ProjectPayed` bit(1) DEFAULT  NULL,
  `ProjectPrice` DOUBLE DEFAULT  NULL,
   PRIMARY KEY (`ProjectId`)
)ENGINE=InnoDB;
