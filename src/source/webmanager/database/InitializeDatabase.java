package webmanager.database;

import webmanager.Controller;
import webmanager.database.abstractions.NullDatabaseObject;
import webmanager.interfaces.DatabaseOperation;

import java.sql.SQLException;
import java.sql.Statement;

class InitializeDatabase extends DatabaseOperation<Void, NullDatabaseObject> {
    @Override
    public Void operate(NullDatabaseObject type) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS `room_has_link` CASCADE;");
            statement.executeUpdate("DROP TABLE IF EXISTS `room_members` CASCADE;");
            statement.executeUpdate("DROP TABLE IF EXISTS `room_admins` CASCADE;");
            statement.executeUpdate("DROP TABLE IF EXISTS `account_has_account` CASCADE;");
            statement.executeUpdate("DROP TABLE IF EXISTS `account` CASCADE;");
            statement.executeUpdate("DROP TABLE IF EXISTS `role` CASCADE;");
            statement.executeUpdate("DROP TABLE IF EXISTS `room` CASCADE;");
            statement.executeUpdate("DROP TABLE IF EXISTS `link` CASCADE;");
            statement.execute("DROP FUNCTION IF EXISTS LEVENSHTEIN;");
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `role` (
                      `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                      `type` VARCHAR(45) NULL,
                      PRIMARY KEY (`id`))
                    ENGINE = InnoDB;
                    """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `account` (
                      `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                      `username` VARCHAR(16) NOT NULL,
                      `email` VARCHAR(255) NOT NULL,
                      `password` VARCHAR(32) NOT NULL,
                      `role_id` INT UNSIGNED NOT NULL,
                      PRIMARY KEY (`id`, `username`, `role_id`),
                      INDEX `fk_user_role_idx` (`role_id` ASC) VISIBLE,
                      CONSTRAINT `fk_user_role`
                        FOREIGN KEY (`role_id`)
                        REFERENCES `role` (`id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION);
                        """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `room` (
                      `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                      `name` VARCHAR(45) NOT NULL,
                      `password` VARCHAR(45) NULL,
                      `description` VARCHAR(1024) NULL,
                      `private` VARCHAR(3) NOT NULL,
                      PRIMARY KEY (`id`, `name`))
                    ENGINE = InnoDB;
                    """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `room_admins` (
                      `user_id` INT UNSIGNED NOT NULL,
                      `room_id` INT UNSIGNED NOT NULL,
                      PRIMARY KEY (`user_id`, `room_id`),
                      INDEX `fk_user_has_room_room1_idx` (`room_id` ASC) VISIBLE,
                      INDEX `fk_user_has_room_user1_idx` (`user_id` ASC) VISIBLE,
                      CONSTRAINT `fk_user_has_room_user1`
                        FOREIGN KEY (`user_id`)
                        REFERENCES `account` (`id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION,
                      CONSTRAINT `fk_user_has_room_room1`
                        FOREIGN KEY (`room_id`)
                        REFERENCES `room` (`id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION);
                        """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `room_members` (
                      `user_id` INT UNSIGNED NOT NULL,
                      `room_id` INT UNSIGNED NOT NULL,
                      PRIMARY KEY (`user_id`, `room_id`),
                      INDEX `fk_user_has_room_room2_idx` (`room_id` ASC) VISIBLE,
                      INDEX `fk_user_has_room_user2_idx` (`user_id` ASC) VISIBLE,
                      CONSTRAINT `fk_user_has_room_user2`
                        FOREIGN KEY (`user_id`)
                        REFERENCES `account` (`id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION,
                      CONSTRAINT `fk_user_has_room_room2`
                        FOREIGN KEY (`room_id`)
                        REFERENCES `room` (`id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION);
                        """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `link` (
                      `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
                      `content` VARCHAR(1024) NOT NULL,
                      PRIMARY KEY (`id`))
                    ENGINE = InnoDB;
                    """);
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `room_has_link` (
                      `room_id` INT UNSIGNED NOT NULL,
                      `link_id` INT UNSIGNED NOT NULL,
                      PRIMARY KEY (`room_id`, `link_id`),
                      INDEX `fk_room_has_link_link1_idx` (`link_id` ASC) VISIBLE,
                      INDEX `fk_room_has_link_room1_idx` (`room_id` ASC) VISIBLE,
                      CONSTRAINT `fk_room_has_link_room1`
                        FOREIGN KEY (`room_id`)
                        REFERENCES `room` (`id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION,
                      CONSTRAINT `fk_room_has_link_link1`
                        FOREIGN KEY (`link_id`)
                        REFERENCES `link` (`id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION)
                    ENGINE = InnoDB;
                    """);
            statement.execute("""
                    CREATE FUNCTION LEVENSHTEIN( s1 VARCHAR(255), s2 VARCHAR(255) )
                        RETURNS INT
                        DETERMINISTIC
                        BEGIN
                            DECLARE s1_len, s2_len, i, j, c, c_temp, cost INT;
                            DECLARE s1_char CHAR;
                            DECLARE cv0, cv1 VARBINARY(256);

                            SET s1_len = CHAR_LENGTH(s1), s2_len = CHAR_LENGTH(s2), cv1 = 0x00, j = 1, i = 1, c = 0;

                            IF s1 = s2 THEN
                                RETURN 0;
                            ELSEIF s1_len = 0 THEN
                                RETURN s2_len;
                            ELSEIF s2_len = 0 THEN
                                RETURN s1_len;
                            ELSE
                                WHILE j <= s2_len DO
                                    SET cv1 = CONCAT(cv1, UNHEX(HEX(j))), j = j + 1;
                                END WHILE;
                                WHILE i <= s1_len DO
                                    SET s1_char = SUBSTRING(s1, i, 1), c = i, cv0 = UNHEX(HEX(i)), j = 1;
                                    WHILE j <= s2_len DO
                                        SET c = c + 1;
                                        IF s1_char = SUBSTRING(s2, j, 1) THEN
                                            SET cost = 0; ELSE SET cost = 1;
                                        END IF;
                                        SET c_temp = CONV(HEX(SUBSTRING(cv1, j, 1)), 16, 10) + cost;
                                        IF c > c_temp THEN SET c = c_temp; END IF;
                                        SET c_temp = CONV(HEX(SUBSTRING(cv1, j+1, 1)), 16, 10) + 1;
                                        IF c > c_temp THEN
                                            SET c = c_temp;
                                        END IF;
                                        SET cv0 = CONCAT(cv0, UNHEX(HEX(c))), j = j + 1;
                                    END WHILE;
                                    SET cv1 = cv0, i = i + 1;
                                END WHILE;
                            END IF;
                            RETURN c;
                        END""");
            statement.execute("""
                    CREATE TABLE IF NOT EXISTS `account_has_account` (
                      `user_id` INT UNSIGNED NOT NULL,
                      `user_id1` INT UNSIGNED NOT NULL,
                      PRIMARY KEY (`user_id`, `user_id1`),
                      INDEX `fk_user_has_user_user2_idx` (`user_id1` ASC) VISIBLE,
                      INDEX `fk_user_has_user_user1_idx` (`user_id` ASC) VISIBLE,
                      CONSTRAINT `fk_user_has_user_user1`
                        FOREIGN KEY (`user_id`)
                        REFERENCES `account` (`id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION,
                      CONSTRAINT `fk_user_has_user_user2`
                        FOREIGN KEY (`user_id1`)
                        REFERENCES `account` (`id`)
                        ON DELETE NO ACTION
                        ON UPDATE NO ACTION);
                    """);
            statement.executeUpdate("INSERT INTO role(type) VALUE('admin');");
            statement.executeUpdate("INSERT INTO role(type) VALUE('user');");
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
