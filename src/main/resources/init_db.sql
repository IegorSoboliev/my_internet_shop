CREATE SCHEMA 'storage' DEFAULT CHARACTER SET utf8 ;
CREATE TABLE 'storage'.'items' (
                                   'item_id' INT NOT NULL AUTO_INCREMENT,
                                   'item_name' VARCHAR(225) NOT NULL,
                                   'price' INT NOT NULL,
                                   PRIMARY KEY ('item_id'));
INSERT INTO storage.items ('item_name', 'price') VALUES ('Christmas carol', 125);
SELECT * FROM storage.items;
CREATE TABLE `storage`.`roles` (
                                   `role_id` INT NOT NULL AUTO_INCREMENT,
                                   `role_name` VARCHAR(25) NOT NULL,
                                   PRIMARY KEY (`role_id`));
CREATE TABLE `storage`.`buckets` (
                                     `bucket_id` INT NOT NULL AUTO_INCREMENT,
                                     `user_id` INT NOT NULL,
                                     `buckets_items_id` INT NULL,
                                     PRIMARY KEY (`bucket_id`));




