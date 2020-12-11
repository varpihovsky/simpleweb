package webmanager.database;

import webmanager.Controller;
import webmanager.database.operations.required.DatabaseOperation;

import java.sql.SQLException;
import java.sql.Statement;

class InitializeDatabase implements DatabaseOperation<Void, Void> {
    @Override
    public Void operate(Statement statement, Void type) {
        try {
            statement.executeUpdate("DROP TABLE IF EXISTS user_data");
            statement.executeUpdate("DROP TABLE IF EXISTS room_data");
            statement.execute("DROP FUNCTION IF EXISTS LEVENSHTEIN;");
            statement.executeUpdate(
                    "CREATE TABLE user_data\n" +
                            "(\n" +
                            "  USERNAME VARCHAR(20) not null ,\n" +
                            "  EMAIL VARCHAR(40) not null ,\n" +
                            "  PASS VARCHAR(20) not null ,\n" +
                            "  ISADMIN VARCHAR(3) DEFAULT 'no' not null ,\n" +
                            "  ROOMS text null," +
                            "  UNIQUE KEY (USERNAME),\n" +
                            "  UNIQUE KEY (EMAIL)\n" +
                            ")");
            statement.executeUpdate(
                    "CREATE TABLE room_data\n" +
                            "(\n" +
                            "  ROOMNAME VARCHAR(20) not null ,\n" +
                            "  DESCRIPTION text null," +
                            "  PASSWORD VARCHAR(20) null ,\n" +
                            "  ADMINS text not null ,\n" +
                            "  ISPRIVATE VARCHAR(3) DEFAULT 'no' not null,\n" +
                            "  LINKS MEDIUMTEXT null,\n" +
                            "  UNIQUE KEY (ROOMNAME)" +
                            ")");
            statement.execute(
                    "CREATE FUNCTION LEVENSHTEIN( s1 VARCHAR(255), s2 VARCHAR(255) )\n" +
                            "    RETURNS INT\n" +
                            "    DETERMINISTIC\n" +
                            "    BEGIN\n" +
                            "        DECLARE s1_len, s2_len, i, j, c, c_temp, cost INT;\n" +
                            "        DECLARE s1_char CHAR;\n" +
                            "        -- max strlen=255\n" +
                            "        DECLARE cv0, cv1 VARBINARY(256);\n" +
                            "\n" +
                            "        SET s1_len = CHAR_LENGTH(s1), s2_len = CHAR_LENGTH(s2), cv1 = 0x00, j = 1, i = 1, c = 0;\n" +
                            "\n" +
                            "        IF s1 = s2 THEN\n" +
                            "            RETURN 0;\n" +
                            "        ELSEIF s1_len = 0 THEN\n" +
                            "            RETURN s2_len;\n" +
                            "        ELSEIF s2_len = 0 THEN\n" +
                            "            RETURN s1_len;\n" +
                            "        ELSE\n" +
                            "            WHILE j <= s2_len DO\n" +
                            "                SET cv1 = CONCAT(cv1, UNHEX(HEX(j))), j = j + 1;\n" +
                            "            END WHILE;\n" +
                            "            WHILE i <= s1_len DO\n" +
                            "                SET s1_char = SUBSTRING(s1, i, 1), c = i, cv0 = UNHEX(HEX(i)), j = 1;\n" +
                            "                WHILE j <= s2_len DO\n" +
                            "                    SET c = c + 1;\n" +
                            "                    IF s1_char = SUBSTRING(s2, j, 1) THEN\n" +
                            "                        SET cost = 0; ELSE SET cost = 1;\n" +
                            "                    END IF;\n" +
                            "                    SET c_temp = CONV(HEX(SUBSTRING(cv1, j, 1)), 16, 10) + cost;\n" +
                            "                    IF c > c_temp THEN SET c = c_temp; END IF;\n" +
                            "                    SET c_temp = CONV(HEX(SUBSTRING(cv1, j+1, 1)), 16, 10) + 1;\n" +
                            "                    IF c > c_temp THEN\n" +
                            "                        SET c = c_temp;\n" +
                            "                    END IF;\n" +
                            "                    SET cv0 = CONCAT(cv0, UNHEX(HEX(c))), j = j + 1;\n" +
                            "                END WHILE;\n" +
                            "                SET cv1 = cv0, i = i + 1;\n" +
                            "            END WHILE;\n" +
                            "        END IF;\n" +
                            "        RETURN c;\n" +
                            "    END");
        } catch (SQLException e) {
            e.printStackTrace();
            Controller.logger.warning("SQLException:\n\t" + e.getMessage() + "\n\t" + e.getSQLState() + "\n\t" +
                    e.getCause());
        }
        return null;
    }
}
