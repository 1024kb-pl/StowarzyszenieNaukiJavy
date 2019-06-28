CREATE DATABASE  IF NOT EXISTS `SIMPLE-TODO-APP`;
USE `SIMPLE-TODO-APP`;

--
-- Table structure for table `USER`
--

DROP TABLE IF EXISTS `USER`;

CREATE TABLE `USER` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(45) DEFAULT NULL, UNIQUE,
  `password` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL, UNIQUE,
  `permision` boolean NOT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

--
-- Table structure for table `TASKS`
--

DROP TABLE IF EXISTS `TASKS`;

CREATE TABLE `TASKS` (
  `taskID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11),
  `taskName` varchar(45) DEFAULT NULL,
  `taskDescription` varchar(45) DEFAULT NULL,
  `taskStatus` boolean NOT NULL,
  `dateOfCompletion` date,
  PRIMARY KEY (`taskID`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

ALTER TABLE TASKS ADD INDEX FKTasks556297 (userID), ADD CONSTRAINT FKTasks556297 FOREIGN KEY (userID) REFERENCES USER (userID);