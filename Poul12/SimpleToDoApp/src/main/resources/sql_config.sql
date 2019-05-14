CREATE DATABASE IF NOT EXISTS `todo_app`;
USE `todo_app`;

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
  `user_id`  INT(11)      NOT NULL AUTO_INCREMENT UNIQUE,
  `username` VARCHAR(35)  NOT NULL UNIQUE,
  `password` VARCHAR(128) NOT NULL,
  `email`    VARCHAR(45)  NOT NULL UNIQUE,
  PRIMARY KEY (`user_id`)
);

DROP TABLE IF EXISTS `tasks`;

CREATE TABLE `tasks`
(
  `task_id`     INT(11)      NOT NULL AUTO_INCREMENT,
  `date`        date,
  `title`       VARCHAR(55)  NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `taskDone`    TINYINT(1)   NOT NULL DEFAULT '0',
  `user_id` INT(11)      NOT NULL,
  PRIMARY KEY (`task_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role`
(
  `role_id`     INT(11)      NOT NULL AUTO_INCREMENT,
  `role`       VARCHAR(15)  NOT NULL,
  `description` VARCHAR(150) NOT NULL,
  `user_id` INT(11)      NOT NULL,
  PRIMARY KEY (`role_id`),
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
);
