CREATE SCHEMA `storage` DEFAULT CHARACTER SET utf8 ;
CREATE TABLE `storage`.`items` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `name` VARCHAR(225) NOT NULL,
                                   `price` INT NOT NULL,
                                   PRIMARY KEY (`id`));
INSERT INTO `storage`.`items` (`name`, `price`) VALUES ('Christmas carol', 125);
UPDATE storage.items SET name = 'suit', price = 12000 WHERE id = 3;
DELETE FROM storage.items WHERE id = 3;
SELECT * FROM storage.items;


