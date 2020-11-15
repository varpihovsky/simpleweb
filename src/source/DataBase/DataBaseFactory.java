package DataBase;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

class DataBaseFactory {
    private static String url = "jdbc:mysql://localhost/simpleweb?useUnicode=true&serverTimezone=UTC";
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    public static void init() throws SQLException{
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        connection = DriverManager.getConnection(url, "root", "x4vQTu9X");
        statement = connection.createStatement();
    }

    public static void addUser(String username, String password, String email)
            throws SQLException {
        statement.executeUpdate("INSERT INTO simpleweb.user_data(USERNAME, PASS, EMAIL) VALUES (\'" + username +
                "\', \'" + password + "\', \'" + email + "\');");
    }

    public static void deleteUser(String username, String password, String email)
            throws SQLException {
        statement.executeUpdate("DELETE FROM user_data WHERE USERNAME=\'"
                + username + "\' AND PASSWORD=\'" + password + "\' AND EMAIL=\'" + email + "\'");
    }

    public static ArrayList<User> sync() throws SQLException {
        ArrayList<User> userList = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT * FROM user_data");
        while (resultSet.next())
            userList.add(new User(resultSet.getString(1), resultSet.getString(3),
                    resultSet.getString(2)));
        return userList;
    }
}
