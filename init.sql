CREATE DATABASE IF NOT EXISTS ems1;
USE ems1;


CREATE TABLE `ems1`.`user_master` (
  `user_master_id` INT PRIMARY KEY AUTO_INCREMENT,
  `buid` INT,
  `mail_id` VARCHAR(255),
  `password` VARCHAR(255),
  `usr_contact_num` VARCHAR(15),
  `usr_fname` VARCHAR(50),
  `usr_lname` VARCHAR(50),
  `usr_mname` VARCHAR(1),
  `username` VARCHAR(50)
);

INSERT INTO `ems1`.`user_master`
(
  `user_master_id`,
  `buid`,
  `mail_id`,
  `password`,
  `usr_contact_num`,
  `usr_fname`,
  `usr_lname`,
  `usr_mname`,
  `username`
)
VALUES
(
  1,          -- user_master_id
  1,          -- buid
  'admin@example.com', -- mail_id (replace with an appropriate email)
  'admin',   -- password (replace with a hashed password)
  '1234567890',        -- usr_contact_num
  'jay',               -- usr_fname
  'yadav',             -- usr_lname
  'admin',             -- usr_mname
  'admin'              -- username
);


CREATE TABLE `ems1`.`user_master` (
  `user_master_id` INT PRIMARY KEY AUTO_INCREMENT,
  `buid` INT,
  `mail_id` VARCHAR(255),
  `password` VARCHAR(255),
  `usr_contact_num` VARCHAR(15),
  `usr_fname` VARCHAR(50),
  `usr_lname` VARCHAR(50),
  `usr_mname` VARCHAR(1),
  `username` VARCHAR(50)
);
INSERT INTO `ems1`.`user_master`
  (`user_master_id`, `buid`, `mail_id`, `password`, `usr_contact_num`, `usr_fname`, `usr_lname`, `usr_mname`, `username`)
VALUES
  (1, 1, 'Admin@example.com', 'Admin', 'admin', 'Admin', 'Admin', 'A', 'admin');
