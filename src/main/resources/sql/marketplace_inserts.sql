-- -----------------------------------------------------
-- Data for table `marketplace`.`Role`
-- -----------------------------------------------------
START TRANSACTION;
USE `marketplace`;
INSERT INTO `marketplace`.`Role` (`RoleTitle`) VALUES ('Administrator');
INSERT INTO `marketplace`.`Role` (`RoleTitle`) VALUES ('User');
INSERT INTO `marketplace`.`Role` (`RoleTitle`) VALUES ('Guest');

COMMIT;


-- -----------------------------------------------------
-- Data for table `marketplace`.`User`
-- -----------------------------------------------------
START TRANSACTION;
USE `marketplace`;
INSERT INTO `marketplace`.`User` (`LoginName`, `Password`, `FullName`, `City`, `Email`, `PhoneNumber`, `idRole_fk`) VALUES ('Todd777', '1w1er', 'Todd Phillips', 'Brooklyn', 'toddsheker@gmail.com', '377377', 2);
INSERT INTO `marketplace`.`User` (`LoginName`, `Password`, `FullName`, `City`, `Email`, `PhoneNumber`, `idRole_fk`) VALUES ('Smiler', 'teror555', 'Joaquin Phoenix', 'New York', 'jokersmoker@rambler.com', '773773', 2);
INSERT INTO `marketplace`.`User` (`LoginName`, `Password`, `FullName`, `City`, `Email`, `PhoneNumber`, `idRole_fk`) VALUES ('Balavart', 'balavart111', 'Vardan Balaian', 'Saratov', 'tro1t@yandex.ru', '9658858885', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `marketplace`.`StatusType`
-- -----------------------------------------------------
START TRANSACTION;
USE `marketplace`;
INSERT INTO `marketplace`.`StatusType` (`StatusTitle`) VALUES ('At auction');
INSERT INTO `marketplace`.`StatusType` (`StatusTitle`) VALUES ('Sold');

COMMIT;


-- -----------------------------------------------------
-- Data for table `marketplace`.`Bidding`
-- -----------------------------------------------------
START TRANSACTION;
USE `marketplace`;
INSERT INTO `marketplace`.`Bidding` (`StartingPrice`, `OfferendDate`, `BestOffer`, `idSupposedBidder`, `idStatus`) VALUES (7777.00, '2021-10-04', 8888.00, 2, 1);
INSERT INTO `marketplace`.`Bidding` (`StartingPrice`, `OfferendDate`, `BestOffer`, `idSupposedBidder`, `idStatus`) VALUES (6574.00, '2021-05-14', 7000.00, 1, 1);
INSERT INTO `marketplace`.`Bidding` (`StartingPrice`, `OfferendDate`, `BestOffer`, `idSupposedBidder`, `idStatus`) VALUES (100.00, '2020-01-09', 500.00, 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table `marketplace`.`Product`
-- -----------------------------------------------------
START TRANSACTION;
USE `marketplace`;
INSERT INTO `marketplace`.`Product` (`ProductTitle`, `Description`, `idProductOwner_fk`, `idBidding_fk`) VALUES ('Tesla Model S', 'Ultra-Luxury Car', 1, 1);
INSERT INTO `marketplace`.`Product` (`ProductTitle`, `Description`, `idProductOwner_fk`, `idBidding_fk`) VALUES ('Chevrolet Impala', 'Large Car', 2, 2);
INSERT INTO `marketplace`.`Product` (`ProductTitle`, `Description`, `idProductOwner_fk`, `idBidding_fk`) VALUES ('Samsung A8', 'Smartphone', 3, 3);

COMMIT;
