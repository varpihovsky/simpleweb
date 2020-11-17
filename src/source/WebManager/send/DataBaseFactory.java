package WebManager.send;


import java.sql.*;
import java.util.ArrayList;

public class DataBaseFactory {
    private String url = "jdbc:mysql://localhost/simpleweb?useUnicode=true&serverTimezone=UTC";
    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;

    public DataBaseFactory() throws SQLException {
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        connection = DriverManager.getConnection(url, "root", "x4vQTu9X");
        statement = connection.createStatement();
    }

    public void addUser(String username, String password, String email)
            throws SQLException {
        statement.executeUpdate("INSERT INTO simpleweb.user_data(USERNAME, PASS, EMAIL) VALUES (\'" + username +
                "\', \'" + password + "\', \'" + email + "\');");
    }

    public void deleteUser(String username, String password, String email)
            throws SQLException {
        statement.executeUpdate("DELETE FROM user_data WHERE USERNAME=\'"
                + username + "\' AND PASSWORD=\'" + password + "\' AND EMAIL=\'" + email + "\'");
    }

    public ArrayList<ArrayList<String>> getUserList() throws SQLException {
        ArrayList<ArrayList<String>> userList = new ArrayList<>();
        resultSet = statement.executeQuery("SELECT * FROM user_data");
        for (int i = 0; resultSet.next(); i++) {
            userList.add(new ArrayList<>());
            userList.get(i).add(resultSet.getString(1));
            userList.get(i).add(resultSet.getString(3));
            userList.get(i).add(resultSet.getString(2));
        }
        return userList;
    }

    public boolean findUser(String username, String password) throws SQLException {
        resultSet = statement.executeQuery("SELECT EXISTS(SELECT * FROM user_data WHERE USERNAME=\'" + username +
                "\' AND PASS=\'" + password + "\')");
        resultSet.next();
        return resultSet.getBoolean(1);
    }
}
