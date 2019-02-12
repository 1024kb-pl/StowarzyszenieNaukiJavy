CREATE DATABASE IF NOT EXISTS `todo_app`;
USE `todo_app`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(35) NOT NULL,
  `password` VARCHAR (32) NOT NULL,
  `email` VARCHAR (45) NOT NULL,
  PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `tasks`;

CREATE TABLE `tasks` (
  `task_id` INT(11) NOT NULL AUTO_INCREMENT,
  `date` date,
  `title` VARCHAR (55) NOT NULL,
  `description` VARCHAR (150) NOT NULL,
  `task_done` TINYINT(4) NOT NULL DEFAULT '0',
  `username_id` INT(11) NOT NULL,
  PRIMARY KEY (`task_id`),
  CONSTRAINT `username_id` FOREIGN KEY (`username_id`) REFERENCES `users` (`user_id`)
);
