CREATE SCHEMA 'storage' DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `storage`.`orders_items` (
                                          `order_id` INT NULL,
                                          `item_id` INT NULL);

CREATE TABLE `storage`.`buckets_items` (
                                           `bucket_id` INT NULL,
                                           `item_id` INT NULL);

CREATE TABLE `storage`.`users_roles` (
                                         `user_id` INT NULL,
                                         `role_id` INT NULL DEFAULT 1);

CREATE TABLE `storage`.`buckets` (
                                     `bucket_id` INT NOT NULL AUTO_INCREMENT,
                                     `user_id` INT NOT NULL,
                                     PRIMARY KEY (`bucket_id`));

CREATE TABLE `storage`.`items` (
                                   `item_id` INT NOT NULL AUTO_INCREMENT,
                                   `item_name` VARCHAR(100) NULL,
                                   `price` INT NULL,
                                   PRIMARY KEY (`item_id`));

CREATE TABLE `storage`.`orders` (
                                    `order_id` INT NOT NULL AUTO_INCREMENT,
                                    `user_id` INT NOT NULL,
                                    PRIMARY KEY (`order_id`));

CREATE TABLE `storage`.`roles` (
                                   `role_id` INT NOT NULL AUTO_INCREMENT,
                                   `role_name` VARCHAR(45) NOT NULL,
                                   PRIMARY KEY (`role_id`));

CREATE TABLE `storage`.`users` (
                                   `user_id` INT NOT NULL AUTO_INCREMENT,
                                   `name` VARCHAR(45) NOT NULL,
                                   `surname` VARCHAR(45) NOT NULL,
                                   `email` VARCHAR(45) NOT NULL,
                                   `password` VARCHAR(600) NOT NULL,
                                   PRIMARY KEY (`user_id`));

ALTER TABLE `storage`.`buckets_items`
    ADD INDEX `BucketsItemstoBucketsFK_idx` (`bucket_id` ASC) VISIBLE,
    ADD INDEX `BucketsItemsToItemsFK_idx` (`item_id` ASC) VISIBLE;
;
ALTER TABLE `storage`.`buckets_items`
    ADD CONSTRAINT `BucketsItemsToBucketsFK`
        FOREIGN KEY (`bucket_id`)
            REFERENCES `storage`.`buckets` (`bucket_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    ADD CONSTRAINT `BucketsItemsToItemsFK`
        FOREIGN KEY (`item_id`)
            REFERENCES `storage`.`items` (`item_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE `storage`.`orders_items`
    ADD INDEX `OrdersItemstoOrdersFK_idx` (`order_id` ASC) VISIBLE,
    ADD INDEX `OrdersItemsToItemsFK_idx` (`item_id` ASC) VISIBLE;
;
ALTER TABLE `storage`.`orders_items`
    ADD CONSTRAINT `OrdersItemstoOrdersFK`
        FOREIGN KEY (`order_id`)
            REFERENCES `storage`.`orders` (`order_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    ADD CONSTRAINT `OrdersItemsToItemsFK`
        FOREIGN KEY (`item_id`)
            REFERENCES `storage`.`items` (`item_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

ALTER TABLE `storage`.`users_roles`
    ADD INDEX `UsersRolestoUsersFK_idx` (`user_id` ASC) VISIBLE,
    ADD INDEX `UsersRolesToRolesFK_idx` (`role_id` ASC) VISIBLE;
;
ALTER TABLE `storage`.`users_roles`
    ADD CONSTRAINT `UsersRolestoUsersFK`
        FOREIGN KEY (`user_id`)
            REFERENCES `storage`.`users` (`user_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    ADD CONSTRAINT `UsersRolesToRolesFK`
        FOREIGN KEY (`role_id`)
            REFERENCES `storage`.`roles` (`role_id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

INSERT INTO `storage`.`roles` (`role_id`, `role_name`) VALUES ('1', 'USER');
INSERT INTO `storage`.`roles` (`role_id`, `role_name`) VALUES ('2', 'ADMIN');

ALTER TABLE `storage`.`users`
    ADD COLUMN `salt` VARBINARY(600) NULL AFTER `password`;

UPDATE users_roles SET role_id = 2 WHERE user_id = 1;
