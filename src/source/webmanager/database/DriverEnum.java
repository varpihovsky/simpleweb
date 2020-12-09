package webmanager.database;

import webmanager.security.Checker;

import java.sql.Driver;
import java.sql.SQLException;

enum DriverEnum {
    MYSQL {
        {
            try {
                this.driver = new com.mysql.cj.jdbc.Driver();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    },
    MARIADB {
        {
            this.driver = new org.mariadb.jdbc.Driver();
        }
    };
    Driver driver;

    static DriverEnum getInstance(String s) {
        if (Checker.isContainsWrong(s))
            return null;
        return DriverEnum.valueOf(s.toUpperCase());
    }

    Driver getDriver() {
        return driver;
    }
}
