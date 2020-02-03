-- -----------------------------------------------------
-- Data for table marketplace.role
-- -----------------------------------------------------
START TRANSACTION;
USE marketplace;
INSERT INTO marketplace.role (role_title) VALUES ('Administrator');
INSERT INTO marketplace.role (role_title) VALUES ('User');
INSERT INTO marketplace.role (role_title) VALUES ('Guest');

COMMIT;


-- -----------------------------------------------------
-- Data for table marketplace.user
-- -----------------------------------------------------
START TRANSACTION;
USE marketplace;
INSERT INTO marketplace.user (login_name, password, full_name, city, email, phone_number, role_id_fk) VALUES ('Todd777', '1w1er', 'Todd Phillips', 'Brooklyn', 'toddsheker@gmail.com', '377377', 2);
INSERT INTO marketplace.user (login_name, password, full_name, city, email, phone_number, role_id_fk) VALUES ('Smiler', 'teror555', 'Joaquin Phoenix', 'New York', 'jokersmoker@rambler.com', '773773', 2);
INSERT INTO marketplace.user (login_name, password, full_name, city, email, phone_number, role_id_fk) VALUES ('Balavart', 'balavart111', 'Vardan Balaian', 'Saratov', 'tro1t@yandex.ru', '9658858885', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table marketplace.status_type
-- -----------------------------------------------------
START TRANSACTION;
USE marketplace;
INSERT INTO marketplace.status_type (status_title) VALUES ('At auction');
INSERT INTO marketplace.status_type (status_title) VALUES ('Sold');

COMMIT;


-- -----------------------------------------------------
-- Data for table marketplace.bidding
-- -----------------------------------------------------
START TRANSACTION;
USE marketplace;
INSERT INTO marketplace.bidding (starting_price, offer_end_date, best_offer, supposed_bidder_id_fk, status_id_fk) VALUES (7777.00, '2021-10-04', 8888.00, 2, 1);
INSERT INTO marketplace.bidding (starting_price, offer_end_date, best_offer, supposed_bidder_id_fk, status_id_fk) VALUES (6574.00, '2021-05-14', 7000.00, 1, 1);
INSERT INTO marketplace.bidding (starting_price, offer_end_date, best_offer, supposed_bidder_id_fk, status_id_fk) VALUES (100.00, '2020-01-09', 500.00, 1, 2);

COMMIT;


-- -----------------------------------------------------
-- Data for table marketplace.product
-- -----------------------------------------------------
START TRANSACTION;
USE marketplace;
INSERT INTO marketplace.product (product_title, description, product_owner_id_fk, bidding_id_fk) VALUES ('Tesla Model S', 'Ultra-Luxury Car', 1, 1);
INSERT INTO marketplace.product (product_title, description, product_owner_id_fk, bidding_id_fk) VALUES ('Chevrolet Impala', 'Large Car', 2, 2);
INSERT INTO marketplace.product (product_title, description, product_owner_id_fk, bidding_id_fk) VALUES ('Samsung A8', 'Smartphone', 3, 3);

COMMIT;
