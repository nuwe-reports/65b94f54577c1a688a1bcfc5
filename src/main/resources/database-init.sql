-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema accenture-hospital-db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema accenture-hospital-db
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `accenture-hospital-db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `accenture-hospital-db` ;

-- -----------------------------------------------------
-- Table `accenture-hospital-db`.`doctors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accenture-hospital-db`.`doctors` ;

CREATE TABLE IF NOT EXISTS `accenture-hospital-db`.`doctors` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(250) NOT NULL,
  `lastName` VARCHAR(250) NOT NULL,
  `email` VARCHAR(250) NOT NULL,
  `age` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `accenture-hospital-db`.`patients`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accenture-hospital-db`.`patients` ;

CREATE TABLE IF NOT EXISTS `accenture-hospital-db`.`patients` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NOT NULL,
  `firstName` VARCHAR(255) NOT NULL,
  `lastName` VARCHAR(255) NOT NULL,
  `age` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `accenture-hospital-db`.`rooms`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accenture-hospital-db`.`rooms` ;

CREATE TABLE IF NOT EXISTS `accenture-hospital-db`.`rooms` (
  `roomName` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`roomName`),
  UNIQUE INDEX `roomName_UNIQUE` (`roomName` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `accenture-hospital-db`.`appointments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `accenture-hospital-db`.`appointments` ;

CREATE TABLE IF NOT EXISTS `accenture-hospital-db`.`appointments` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `finishesAt` DATE NOT NULL,
  `startsAt` DATE NOT NULL,
  `patient_id` INT NOT NULL,
  `doctor_id` INT NOT NULL,
  `room_roomName` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_appointment_patient_idx` (`patient_id` ASC) VISIBLE,
  INDEX `fk_appointment_doctors1_idx` (`doctor_id` ASC) VISIBLE,
  INDEX `fk_appointment_room1_idx` (`room_roomName` ASC) VISIBLE,
  CONSTRAINT `fk_appointment_doctors1`
    FOREIGN KEY (`doctor_id`)
    REFERENCES `accenture-hospital-db`.`doctors` (`id`),
  CONSTRAINT `fk_appointment_patient`
    FOREIGN KEY (`patient_id`)
    REFERENCES `accenture-hospital-db`.`patients` (`id`),
  CONSTRAINT `fk_appointment_room1`
    FOREIGN KEY (`room_roomName`)
    REFERENCES `accenture-hospital-db`.`rooms` (`roomName`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
