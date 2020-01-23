SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema marketplace
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS marketplace DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE marketplace ;

-- -----------------------------------------------------
-- Table marketplace.status_type
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS marketplace.status_type (
  status_id INT NOT NULL AUTO_INCREMENT,
  status_title VARCHAR(20) NOT NULL,
  PRIMARY KEY (status_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table marketplace.role
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS marketplace.role (
  role_id INT NOT NULL AUTO_INCREMENT,
  role_title VARCHAR(20) NOT NULL,
  PRIMARY KEY (role_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table marketplace.user
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS marketplace.user (
  user_id INT NOT NULL AUTO_INCREMENT,
  login_name VARCHAR(20) NOT NULL,
  password VARCHAR(20) NOT NULL,
  full_name VARCHAR(40) NOT NULL,
  city VARCHAR(30) NOT NULL,
  email VARCHAR(40) NOT NULL,
  phone_number VARCHAR(12) NOT NULL,
  role_id_fk INT NOT NULL,
  PRIMARY KEY (user_id),
  INDEX role_id_fk_idx (role_id_fk ASC) VISIBLE,
  CONSTRAINT role_id_fk
    FOREIGN KEY (role_id_fk)
    REFERENCES marketplace.role (role_id))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table marketplace.bidding
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS marketplace.bidding (
  bidding_id INT NOT NULL AUTO_INCREMENT,
  starting_price DOUBLE NOT NULL,
  offer_end_date DATE NOT NULL,
  best_offer DOUBLE NULL DEFAULT NULL,
  supposed_bidder_id_fk INT NULL DEFAULT NULL,
  status_id_fk INT NOT NULL,
  PRIMARY KEY (bidding_id),
  INDEX supposed_bidder_id_fk_idx (supposed_bidder_id_fk ASC) VISIBLE,
  INDEX  status_id_fk_idx (status_id_fk ASC) VISIBLE,
  CONSTRAINT status_id_fk
    FOREIGN KEY (status_id_fk)
    REFERENCES marketplace.status_type (status_id),
  CONSTRAINT supposed_bidder_id_fk
    FOREIGN KEY (supposed_bidder_id_fk)
    REFERENCES marketplace.user (user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table marketplace.product
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS marketplace.product (
  product_id INT NOT NULL AUTO_INCREMENT,
  product_title VARCHAR(20) NOT NULL,
  description VARCHAR(150) NOT NULL,
  product_owner_id_fk INT NOT NULL,
  bidding_id_fk INT NOT NULL,
  PRIMARY KEY (product_id),
  INDEX product_owner_id_fk_idx (product_owner_id_fk ASC) VISIBLE,
  INDEX bidding_id_fk_idx (bidding_id_fk ASC) VISIBLE,
  CONSTRAINT bidding_id_fk
    FOREIGN KEY (bidding_id_fk)
    REFERENCES marketplace.bidding (bidding_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT product_owner_id_fk
    FOREIGN KEY (product_owner_id_fk)
    REFERENCES marketplace.user (user_id)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
