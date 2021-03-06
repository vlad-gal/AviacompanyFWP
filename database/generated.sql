-- MySQL Script generated by MySQL Workbench
-- Wed Nov 18 19:07:18 2020
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema aviacompany
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema aviacompany
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `aviacompany` DEFAULT CHARACTER SET utf8 ;
USE `aviacompany` ;

-- -----------------------------------------------------
-- Table `aviacompany`.`roles`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`roles` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`roles` (
  `roleId` INT NOT NULL,
  `roleName` VARCHAR(30) NOT NULL,
  PRIMARY KEY (`roleId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`aircraftTypes`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`aircraftTypes` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`aircraftTypes` (
  `aircraftTypeId` INT NOT NULL,
  `aircraftType` VARCHAR(15) NOT NULL,
  PRIMARY KEY (`aircraftTypeId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`statuses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`statuses` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`statuses` (
  `statusId` INT NOT NULL,
  `statusName` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`statusId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`aircrafts`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`aircrafts` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`aircrafts` (
  `aircraftId` INT NOT NULL AUTO_INCREMENT,
  `tailNumber` VARCHAR(10) NOT NULL,
  `aircraftName` VARCHAR(40) NOT NULL,
  `aircraftTypeId` INT NOT NULL,
  `statusId` INT NOT NULL,
  PRIMARY KEY (`aircraftId`),
  INDEX `fk_aircrafts_aircraftTypes_idx` (`aircraftTypeId` ASC) INVISIBLE,
  UNIQUE INDEX `tailNumber_UNIQUE` (`tailNumber` ASC) VISIBLE,
  INDEX `fk_aircrafts_statuses1_idx` (`statusId` ASC) VISIBLE,
  CONSTRAINT `fk_aircrafts_aircraftTypes`
    FOREIGN KEY (`aircraftTypeId`)
    REFERENCES `aviacompany`.`aircraftTypes` (`aircraftTypeId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_aircrafts_statuses1`
    FOREIGN KEY (`statusId`)
    REFERENCES `aviacompany`.`statuses` (`statusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`airports`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`airports` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`airports` (
  `airportId` INT NOT NULL AUTO_INCREMENT,
  `airportName` VARCHAR(15) NOT NULL,
  `country` VARCHAR(20) NOT NULL,
  `city` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`airportId`),
  UNIQUE INDEX `airportName_UNIQUE` (`airportName` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`users` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`users` (
  `userId` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(40) NOT NULL,
  `login` VARCHAR(40) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `roleId` INT NOT NULL,
  `statusId` INT NOT NULL,
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE,
  INDEX `fk_users_statuses_idx` (`statusId` ASC) VISIBLE,
  INDEX `fk_users_positions_idx` (`roleId` ASC) VISIBLE,
  CONSTRAINT `fk_users_statuses`
    FOREIGN KEY (`statusId`)
    REFERENCES `aviacompany`.`statuses` (`statusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_users_positions`
    FOREIGN KEY (`roleId`)
    REFERENCES `aviacompany`.`roles` (`roleId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`crews`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`crews` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`crews` (
  `crewId` INT NOT NULL AUTO_INCREMENT,
  `crewName` VARCHAR(15) NOT NULL,
  `dispatcherId` INT NOT NULL,
  `numberOfPilots` INT NOT NULL,
  `numberOfNavigators` INT NOT NULL,
  `numberOfRadioman` INT NOT NULL,
  `numberOfStewardesses` INT NOT NULL,
  `statusId` INT NOT NULL,
  PRIMARY KEY (`crewId`),
  INDEX `fk_flights_has_users_users_idx` (`dispatcherId` ASC) VISIBLE,
  INDEX `fk_crews_statuses1_idx` (`statusId` ASC) VISIBLE,
  UNIQUE INDEX `crewName_UNIQUE` (`crewName` ASC) VISIBLE,
  CONSTRAINT `fk_flights_dispatcher`
    FOREIGN KEY (`dispatcherId`)
    REFERENCES `aviacompany`.`users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_crews_statuses1`
    FOREIGN KEY (`statusId`)
    REFERENCES `aviacompany`.`statuses` (`statusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`flights`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`flights` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`flights` (
  `flightId` INT NOT NULL AUTO_INCREMENT,
  `departureAirportId` INT NOT NULL,
  `destinationAirportId` INT NOT NULL,
  `departTime` BIGINT NOT NULL,
  `arriveTime` BIGINT NOT NULL,
  `aircraftId` INT NOT NULL,
  `crewId` INT NOT NULL,
  `operatorId` INT NOT NULL,
  `statusId` INT NOT NULL,
  PRIMARY KEY (`flightId`),
  UNIQUE INDEX `flightId_UNIQUE` (`flightId` ASC) VISIBLE,
  INDEX `fk_flights_aircrafts_idx` (`aircraftId` ASC) VISIBLE,
  INDEX `fk_flights_crews1_idx` (`crewId` ASC) VISIBLE,
  INDEX `fk_flights_users1_idx` (`operatorId` ASC) VISIBLE,
  INDEX `fk_destination_idx` (`destinationAirportId` ASC) VISIBLE,
  INDEX `fk_departure_idx` (`departureAirportId` ASC) VISIBLE,
  INDEX `fk_flights_statuses1_idx` (`statusId` ASC) VISIBLE,
  CONSTRAINT `fk_flights_aircrafts`
    FOREIGN KEY (`aircraftId`)
    REFERENCES `aviacompany`.`aircrafts` (`aircraftId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_departure`
    FOREIGN KEY (`departureAirportId`)
    REFERENCES `aviacompany`.`airports` (`airportId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_destination`
    FOREIGN KEY (`destinationAirportId`)
    REFERENCES `aviacompany`.`airports` (`airportId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flights_crews`
    FOREIGN KEY (`crewId`)
    REFERENCES `aviacompany`.`crews` (`crewId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flights_users`
    FOREIGN KEY (`operatorId`)
    REFERENCES `aviacompany`.`users` (`userId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_flights_statuses1`
    FOREIGN KEY (`statusId`)
    REFERENCES `aviacompany`.`statuses` (`statusId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`personalData`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`personalData` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`personalData` (
  `userId` INT NOT NULL,
  `firstName` VARCHAR(15) NOT NULL,
  `lastName` VARCHAR(15) NOT NULL,
  `telephoneNumber` BIGINT NOT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `fk_personalData_users`
    FOREIGN KEY (`userId`)
    REFERENCES `aviacompany`.`users` (`userId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `aviacompany`.`crews_has_users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `aviacompany`.`crews_has_users` ;

CREATE TABLE IF NOT EXISTS `aviacompany`.`crews_has_users` (
  `crewId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`crewId`, `userId`),
  INDEX `fk_crews_has_users_users1_idx` (`userId` ASC) VISIBLE,
  INDEX `fk_crews_has_users_crews1_idx` (`crewId` ASC) VISIBLE,
  CONSTRAINT `fk_crews_has_users_crews1`
    FOREIGN KEY (`crewId`)
    REFERENCES `aviacompany`.`crews` (`crewId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_crews_has_users_users1`
    FOREIGN KEY (`userId`)
    REFERENCES `aviacompany`.`users` (`userId`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `aviacompany`.`roles`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`roles` (`roleId`, `roleName`) VALUES (0, 'Admin');
INSERT INTO `aviacompany`.`roles` (`roleId`, `roleName`) VALUES (1, 'Operator');
INSERT INTO `aviacompany`.`roles` (`roleId`, `roleName`) VALUES (2, 'Dispatcher');
INSERT INTO `aviacompany`.`roles` (`roleId`, `roleName`) VALUES (3, 'Pilot');
INSERT INTO `aviacompany`.`roles` (`roleId`, `roleName`) VALUES (4, 'Navigator');
INSERT INTO `aviacompany`.`roles` (`roleId`, `roleName`) VALUES (5, 'Radioman');
INSERT INTO `aviacompany`.`roles` (`roleId`, `roleName`) VALUES (6, 'Stewardess');

COMMIT;


-- -----------------------------------------------------
-- Data for table `aviacompany`.`aircraftTypes`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`aircraftTypes` (`aircraftTypeId`, `aircraftType`) VALUES (0, 'Cargo');
INSERT INTO `aviacompany`.`aircraftTypes` (`aircraftTypeId`, `aircraftType`) VALUES (1, 'Passenger');

COMMIT;


-- -----------------------------------------------------
-- Data for table `aviacompany`.`statuses`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`statuses` (`statusId`, `statusName`) VALUES (0, 'Active');
INSERT INTO `aviacompany`.`statuses` (`statusId`, `statusName`) VALUES (1, 'Inactive');
INSERT INTO `aviacompany`.`statuses` (`statusId`, `statusName`) VALUES (2, 'Fly');
INSERT INTO `aviacompany`.`statuses` (`statusId`, `statusName`) VALUES (3, 'Busy');

COMMIT;


-- -----------------------------------------------------
-- Data for table `aviacompany`.`aircrafts`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (1, 'OEIVN', 'Airbus A320-100/200', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (2, 'OOSNN', 'Airbus A330-300', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (3, 'OOTUK', 'Airbus A350', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (4, 'OOTCV', 'Boeing 787-8 Dreamliner', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (5, 'EIEMN', 'Embraer EMB 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (6, 'CNNMH', 'Airbus A320-100/200', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (7, 'VQBSR', 'McDonnell Douglas MD-11 Freighter', 0, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (8, 'N453PA', 'Boeing 777-200F Freighter', 0, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (9, 'BLXL', 'Boeing 777-200F Freighter', 0, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (10, 'N523FE', 'McDonnell Douglas MD-11 Freighter', 0, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (11, 'N221NN', 'Embraer 170', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (12, 'N127SY', 'Embraer 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (13, 'C-GPHY', 'Boeing 787-8', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (14, 'OE-FSP', 'Cessna 525A Citation CJ2', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (15, 'N110HQ', 'Embraer EMB 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (16, 'N939TW', 'Cessna 560 Citation V', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (17, 'N432YX', 'Embraer 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (18, 'N187WW', 'C310', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (19, 'N127HQ', 'Embraer EMB 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (20, '166376', 'Gulfstream Aerospace V', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (21, 'N929SW', 'Bombardier CRJ', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (22, 'N435YX', 'Embraer 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (23, 'N98136', 'Piper Archer', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (24, 'N136SY', 'Embraer 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (25, 'N718MB', 'Bombardier Challenger 600', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (26, 'N962SW', 'Bombardier CRJ', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (27, 'N501SS', 'C500', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (28, 'N594SW', 'Canadair CRJ 100', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (29, 'N795SK', 'Bombardier CRJ700', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (30, 'N153SY', 'Embraer 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (31, 'N854AS', 'Bombardier CRJ', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (32, 'N998LL', 'Cirrus SR-22', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (33, 'N17409', 'Cessna 172', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (34, 'N152SY', 'Embraer 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (35, 'N52364', 'Cessna 172', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (36, 'N57229', 'M20K', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (37, 'N973SW', 'Bombardier CRJ', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (38, 'N41152', 'M20T', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (39, 'JA879J', 'Boeing 787-9', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (40, 'N787FX', 'Cessna 208 Caravan', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (41, 'N43956', 'Piper Archer', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (42, 'N946SW', 'Bombardier CRJ', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (43, 'N607UX', 'Embraer 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (44, 'N792SK', 'Bombardier CRJ700', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (45, 'N157SY', 'Embraer 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (46, 'N2UV', 'BE9L', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (47, 'N263TA', 'Cessna Model 680 Sovereign', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (48, 'N116SY', 'Embraer 175', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (49, 'VP-BDM', 'Airbus A319', 1, 0);
INSERT INTO `aviacompany`.`aircrafts` (`aircraftId`, `tailNumber`, `aircraftName`, `aircraftTypeId`, `statusId`) VALUES (50, 'N929EV', 'Bombardier CRJ', 1, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `aviacompany`.`airports`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (1, 'Пулково', 'Россия', 'Санкт-Петербург');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (2, 'Домодедово', 'Россия', 'Москва');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (3, 'Могилев', 'Беларусь', 'Могилев');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (4, 'Минск 1', 'Беларусь', 'Минск');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (5, 'Соренто', 'Италия', 'Неаполь');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (6, 'Болзано', 'Италия', 'Болзано');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (7, 'Вена Швехат', 'Австрия', 'Вена');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (8, 'Graz', 'Австрия', 'Грац');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (9, 'Baku Heydar', 'Азербайджан', 'Баку');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (10, 'Yerevan', 'Армения', 'Ереван');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (11, 'Абердин', 'Великобритания', 'Абердин');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (12, 'Бирмингем', 'Великобритания', 'Бирмингем');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (13, 'Бристоль', 'Великобритания', 'Бристоль');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (14, 'Глазго', 'Великобритания', 'Глазго');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (15, 'Ливерпуль', 'Великобритания', 'Ливерпуль');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (16, 'Манчестер', 'Великобритания', 'Манчестер');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (17, 'Лондон Сити', 'Великобритания', 'Лондон');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (18, 'Будапешт', 'Венгрия', 'Будапешт');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (19, 'Афины', 'Греция', 'Афины');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (20, 'Tbilisi', 'Грузия', 'Тбилиси');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (21, 'Batumi', 'Грузия', 'Батуми');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (22, 'Аалборг', 'Дания', 'Аалборг');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (23, 'Копенгаген', 'Дания', 'Копенгаген');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (24, 'Рейкьявик', 'Исландия', 'Рейкьявик');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (25, 'Amman', 'Иордания', 'Amman');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (26, 'Неаполь', 'Италия', 'Неаполь');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (27, 'Рим Фьюмичино', 'Италия', 'Рим');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (28, 'Пиза', 'Италия', 'Пиза');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (29, 'Турин', 'Италия', 'Турин');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (30, 'Грозный', 'Россия ', 'Грозный');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (31, 'Казань', 'Россия ', 'Казань');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (32, 'Внуково', 'Россия ', 'Москва');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (33, 'Шереметьево', 'Россия ', 'Москва');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (34, 'Сочи', 'Россия ', 'Сочи');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (35, 'Новосибирск', 'Россия ', 'Новосибирск');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (36, 'Любляна', 'Словения ', 'Любляна');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (37, 'Донецк', 'Украина', 'Донецк');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (38, 'Одесса', 'Украина', 'Одесса');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (39, 'Борисполь', 'Украина', 'Киев');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (40, 'Прованс', 'Франция', 'Марсель');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (41, 'Ницца', 'Франция', 'Ницца');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (42, 'Шарль де Голль', 'Франция', 'Париж');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (43, 'Прага Рузине', 'Чехия', 'Прага');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (44, 'Гётеборг', 'Швеция ', 'Гётеборг');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (45, 'Мальмё', 'Швеция ', 'Мальмё');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (46, 'Стокгольм', 'Швеция ', 'Стокгольм');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (47, 'Brisbane', 'Австралия', 'Brisbane');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (48, 'Melbourne', 'Австралия', 'Melbourne');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (49, 'Сидней', 'Австралия', 'Сидней');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (50, 'Бургас', 'Болгария ', 'Бургас');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (51, 'Пловдив', 'Болгария ', 'Пловдив');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (52, 'Варна', 'Болгария ', 'Варна');
INSERT INTO `aviacompany`.`airports` (`airportId`, `airportName`, `country`, `city`) VALUES (53, 'София', 'Болгария ', 'София');

COMMIT;


-- -----------------------------------------------------
-- Data for table `aviacompany`.`users`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (1, 'novikov@gmail.com', 'novikmish', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 1, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (2, 'halatsevich@gmail.com', 'vladhala', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 0, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (3, 'blinov@hotmail.com', 'ihorblinov', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 2, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (4, 'kornpetya@gmail.com', 'kornpetr', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 3, 3);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (5, 'tyrovets@hotmail.com', 'nastyatyr', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 5, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (6, 'volkovayana@tut.by', 'yanusik', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 1, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (7, 'tolyansolov@gmail.com', 'tolikanabolik', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 3, 3);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (8, 'hutar@mail.ru', 'hutarki', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 5, 3);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (9, 'hlinsky@yandex.ru', 'ilyahlin', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 3, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (10, 'ksy@mail.ru', 'ksypolosh', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 6, 3);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (11, 'vanyavanech@gmail.com', 'lobanok', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 4, 3);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (12, 'sivchenko@mail.ru', 'sivchenko', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 4, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (13, 'kotol@mail.ru', 'vetakoto', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 6, 3);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (14, 'irynasen@hotmail.com', 'irsenk', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 6, 3);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (15, 'ahmetova@gmail.com', 'mashaaxmet', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 2, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (16, 'kazakate@hotmail.com', 'kataz', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 6, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (17, 'popovanast@mail.ru', 'nastyapop', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 6, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (18, 'asaf@mail.ru', 'asafstas', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 1, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (19, 'nemch@mail.com', 'nemch', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 2, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (20, 'dudy@mail.com', 'dudq', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 3, 0);
INSERT INTO `aviacompany`.`users` (`userId`, `email`, `login`, `password`, `roleId`, `statusId`) VALUES (21, 'qweee@mail.com', 'tera231', 'c4e871eea12df589c6522e5f458df396a5745ea124f805a78a6e6932baa6eb0b', 4, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `aviacompany`.`crews`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`crews` (`crewId`, `crewName`, `dispatcherId`, `numberOfPilots`, `numberOfNavigators`, `numberOfRadioman`, `numberOfStewardesses`, `statusId`) VALUES (1, 'Eagles', 3, 2, 1, 1, 3, 0);
INSERT INTO `aviacompany`.`crews` (`crewId`, `crewName`, `dispatcherId`, `numberOfPilots`, `numberOfNavigators`, `numberOfRadioman`, `numberOfStewardesses`, `statusId`) VALUES (2, 'Stars', 15, 1, 1, 1, 2, 0);
INSERT INTO `aviacompany`.`crews` (`crewId`, `crewName`, `dispatcherId`, `numberOfPilots`, `numberOfNavigators`, `numberOfRadioman`, `numberOfStewardesses`, `statusId`) VALUES (3, 'Air', 19, 2, 1, 1, 4, 0);
INSERT INTO `aviacompany`.`crews` (`crewId`, `crewName`, `dispatcherId`, `numberOfPilots`, `numberOfNavigators`, `numberOfRadioman`, `numberOfStewardesses`, `statusId`) VALUES (4, 'Birds', 3, 1, 2, 2, 4, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `aviacompany`.`flights`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`flights` (`flightId`, `departureAirportId`, `destinationAirportId`, `departTime`, `arriveTime`, `aircraftId`, `crewId`, `operatorId`, `statusId`) VALUES (1, 43, 2, 1606866300000, 1606877400000, 1, 2, 1, 0);
INSERT INTO `aviacompany`.`flights` (`flightId`, `departureAirportId`, `destinationAirportId`, `departTime`, `arriveTime`, `aircraftId`, `crewId`, `operatorId`, `statusId`) VALUES (2, 7, 4, 1607039160000, 1607053800000, 17, 3, 1, 0);
INSERT INTO `aviacompany`.`flights` (`flightId`, `departureAirportId`, `destinationAirportId`, `departTime`, `arriveTime`, `aircraftId`, `crewId`, `operatorId`, `statusId`) VALUES (3, 4, 9, 1607119200000, 1607124300000, 3, 3, 1, 0);
INSERT INTO `aviacompany`.`flights` (`flightId`, `departureAirportId`, `destinationAirportId`, `departTime`, `arriveTime`, `aircraftId`, `crewId`, `operatorId`, `statusId`) VALUES (4, 10, 5, 1607040120000, 1607047440000, 25, 1, 1, 0);
INSERT INTO `aviacompany`.`flights` (`flightId`, `departureAirportId`, `destinationAirportId`, `departTime`, `arriveTime`, `aircraftId`, `crewId`, `operatorId`, `statusId`) VALUES (5, 6, 10, 1606047600000, 1606051560000, 18, 1, 1, 0);
INSERT INTO `aviacompany`.`flights` (`flightId`, `departureAirportId`, `destinationAirportId`, `departTime`, `arriveTime`, `aircraftId`, `crewId`, `operatorId`, `statusId`) VALUES (6, 1, 2, 1606370820000, 1606630020000, 3, 2, 1, 0);
INSERT INTO `aviacompany`.`flights` (`flightId`, `departureAirportId`, `destinationAirportId`, `departTime`, `arriveTime`, `aircraftId`, `crewId`, `operatorId`, `statusId`) VALUES (7, 1, 6, 1605801180000, 1605887700000, 18, 3, 1, 0);

COMMIT;


-- -----------------------------------------------------
-- Data for table `aviacompany`.`personalData`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (1, 'Михаил', 'Новиков', 375291245789);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (2, 'Владислав', 'Галацевич', 375298163245);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (3, 'Игорь', 'Блинов', 375257416134);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (4, 'Петр', 'Корнилюк', 375297821314);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (5, 'Анастасия', 'Туровец', 375297861005);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (6, 'Янина', 'Волкова', 375298763288);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (7, 'Анатолий ', 'Соловьев', 375291499710);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (8, 'Виталий', 'Гутарев', 375294579331);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (9, 'Илья', 'Глинский', 375334518930);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (10, 'Ксения', 'Полошавец', 375330457914);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (11, 'Иван', 'Лобанок', 375334789131);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (12, 'Евгений', 'Сивченко', 375336697811);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (13, 'Елизавета', 'Котоласова', 375334647489);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (14, 'Мария', 'Ахметова', 375258793256);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (15, 'Ирина', 'Сенько', 375256459267);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (16, 'Екатерина', 'Казаченко', 375256587134);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (17, 'Анастасия', 'Попова', 375255972831);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (18, 'Стас', 'Асафьев', 375987545454);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (19, 'Сергей', 'Немчинский', 375296845987);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (20, 'Юрий', 'Дудь', 375298761425);
INSERT INTO `aviacompany`.`personalData` (`userId`, `firstName`, `lastName`, `telephoneNumber`) VALUES (21, 'Татьяна', 'Базыль', 375951842862);

COMMIT;


-- -----------------------------------------------------
-- Data for table `aviacompany`.`crews_has_users`
-- -----------------------------------------------------
START TRANSACTION;
USE `aviacompany`;
INSERT INTO `aviacompany`.`crews_has_users` (`crewId`, `userId`) VALUES (1, 3);
INSERT INTO `aviacompany`.`crews_has_users` (`crewId`, `userId`) VALUES (1, 4);
INSERT INTO `aviacompany`.`crews_has_users` (`crewId`, `userId`) VALUES (1, 7);
INSERT INTO `aviacompany`.`crews_has_users` (`crewId`, `userId`) VALUES (1, 8);
INSERT INTO `aviacompany`.`crews_has_users` (`crewId`, `userId`) VALUES (1, 10);
INSERT INTO `aviacompany`.`crews_has_users` (`crewId`, `userId`) VALUES (1, 11);
INSERT INTO `aviacompany`.`crews_has_users` (`crewId`, `userId`) VALUES (1, 13);
INSERT INTO `aviacompany`.`crews_has_users` (`crewId`, `userId`) VALUES (1, 14);
INSERT INTO `aviacompany`.`crews_has_users` (`crewId`, `userId`) VALUES (3, 20);

COMMIT;

