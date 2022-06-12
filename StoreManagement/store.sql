-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema store
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema store
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `store` ;
USE `store` ;

-- -----------------------------------------------------
-- Table `store`.`regDetails`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`regDetails` (
  `uname` VARCHAR(200) NOT NULL,
  `password` VARCHAR(200) NULL,
  `role` VARCHAR(200) NULL DEFAULT 'user',
  PRIMARY KEY (`uname`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`items`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`items` (
  `itemId` INT NOT NULL,
  `itemName` VARCHAR(200) NULL,
  `itemQty` INT NULL,
  PRIMARY KEY (`itemId`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `store`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `store`.`cart` (
  `cartId` INT NOT NULL AUTO_INCREMENT,
  `itemId` INT NULL,
  `itemName` VARCHAR(200) NULL,
  `itemQty` INT NULL,
  `uname` VARCHAR(200) NULL,
  PRIMARY KEY (`cartId`),
  INDEX `itemId_idx` (`itemId` ASC) VISIBLE,
  INDEX `uname_idx` (`uname` ASC) VISIBLE,
  CONSTRAINT `itemId`
    FOREIGN KEY (`itemId`)
    REFERENCES `store`.`items` (`itemId`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `uname`
    FOREIGN KEY (`uname`)
    REFERENCES `store`.`regDetails` (`uname`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
