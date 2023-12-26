CREATE DATABASE IF NOT EXISTS ems1;
USE ems1;




CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `users` WRITE;

INSERT INTO `users` VALUES (1,'Admin','Admin','admin','admin');

UNLOCK TABLES;



CREATE TABLE `user_master` (
  `user_master_id` bigint NOT NULL AUTO_INCREMENT,
  `buid` varchar(255) DEFAULT NULL,
  `mail_id` varchar(255) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `usr_contact_num` varchar(30) DEFAULT NULL,
  `usr_fname` varchar(30) DEFAULT NULL,
  `usr_lname` varchar(30) DEFAULT NULL,
  `usr_mname` varchar(30) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_master_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user_master` WRITE;

INSERT INTO `user_master` VALUES (1,'1','1','admin','123456','jay','yadav',NULL,'admin');
UNLOCK TABLES;


