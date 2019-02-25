CREATE DATABASE IF NOT EXISTS `todo_app`;
USE `todo_app`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userId` INT(11) NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(35) NOT NULL,
  `password` VARCHAR (32) NOT NULL,
  `email` VARCHAR (45) NOT NULL,
  PRIMARY KEY (`userId`)
);

DROP TABLE IF EXISTS `tasks`;

CREATE TABLE `tasks` (
  `taskId` INT(11) NOT NULL AUTO_INCREMENT,
  `date` date,
  `title` VARCHAR (55) NOT NULL,
  `description` VARCHAR (150) NOT NULL,
  `taskDone` TINYINT(4) NOT NULL DEFAULT '0',
  `usernameId` INT(11) NOT NULL,
  PRIMARY KEY (`taskId`),
  CONSTRAINT `usernameId` FOREIGN KEY (`usernameId`) REFERENCES `users` (`userId`)
);
