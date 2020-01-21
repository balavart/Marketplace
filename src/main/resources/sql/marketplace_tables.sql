-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema marketplace
-- -----------------------------------------------------
-- Database for web application Marketplace

-- -----------------------------------------------------
-- Schema marketplace
--
-- Database for web application Marketplace
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `marketplace` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `marketplace` ;

-- -----------------------------------------------------
-- Table `marketplace`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace`.`Role` (
  `idRole` INT NOT NULL AUTO_INCREMENT,
  `RoleTitle` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idRole`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketplace`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `LoginName` VARCHAR(20) NOT NULL,
  `Password` VARCHAR(20) NOT NULL,
  `FullName` VARCHAR(40) NOT NULL,
  `City` VARCHAR(30) NOT NULL,
  `Email` VARCHAR(40) NOT NULL,
  `PhoneNumber` VARCHAR(12) NOT NULL,
  `idRole_fk` INT NOT NULL,
  PRIMARY KEY (`idUser`),
  INDEX `idRole_idx` (`idRole_fk` ASC) VISIBLE,
  CONSTRAINT `idRole`
    FOREIGN KEY (`idRole_fk`)
    REFERENCES `marketplace`.`Role` (`idRole`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketplace`.`StatusType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace`.`StatusType` (
  `idStatus` INT NOT NULL AUTO_INCREMENT,
  `StatusTitle` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`idStatus`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketplace`.`Bidding`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace`.`Bidding` (
  `idBidding` INT NOT NULL AUTO_INCREMENT,
  `StartingPrice` DOUBLE NOT NULL,
  `OfferendDate` DATE NOT NULL,
  `BestOffer` DOUBLE NULL,
  `idSupposedBidder` INT NOT NULL,
  `idStatus` INT NOT NULL,
  PRIMARY KEY (`idBidding`),
  UNIQUE INDEX `StartingPrice_UNIQUE` (`StartingPrice` ASC) VISIBLE,
  INDEX `idSupposedBidder_idx` (`idSupposedBidder` ASC) VISIBLE,
  INDEX `idStatus_idx` (`idStatus` ASC) VISIBLE,
  CONSTRAINT `idSupposedBidder`
    FOREIGN KEY (`idSupposedBidder`)
    REFERENCES `marketplace`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `idStatus`
    FOREIGN KEY (`idStatus`)
    REFERENCES `marketplace`.`StatusType` (`idStatus`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `marketplace`.`Product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `marketplace`.`Product` (
  `idProduct` INT NOT NULL AUTO_INCREMENT,
  `ProductTitle` VARCHAR(20) NOT NULL,
  `Description` VARCHAR(150) NOT NULL,
  `idProductOwner_fk` INT NOT NULL,
  `idBidding_fk` INT NOT NULL,
  PRIMARY KEY (`idProduct`),
  INDEX `productOwner_fk_idx` (`idProductOwner_fk` ASC) VISIBLE,
  INDEX `idBidding_idx` (`idBidding_fk` ASC) VISIBLE,
  CONSTRAINT `idProductOwner_fk`
    FOREIGN KEY (`idProductOwner_fk`)
    REFERENCES `marketplace`.`User` (`idUser`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `idBidding`
    FOREIGN KEY (`idBidding_fk`)
    REFERENCES `marketplace`.`Bidding` (`idBidding`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
