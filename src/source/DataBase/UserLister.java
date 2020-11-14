package DataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserLister {
    private static ArrayList<User> userList;
    DataBaseFactory dataBase;

    public UserLister() {

        try {
            dataBase = new DataBaseFactory();
            userList = DataBaseFactory.sync();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getErrorCode() + "\nCause: " + e.getCause());
        }
    }

    public static void addUser(String username, String password) {
        userList.add(new User(username, password));
        try {
            DataBaseFactory.addUser(username, password);
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getErrorCode() + "\nCause: " + e.getCause());
        }
    }

    public static void deleteUser(String username, String password){
        for(int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getPassword().equals(password) &&
                    userList.get(i).getUsername().equals(username)) {
                userList.remove(i);
                try {
                    DataBaseFactory.deleteUser(username, password);
                } catch (SQLException e) {
                    System.out.println("Exception: " + e.getErrorCode() + "\nCause: " + e.getCause());
                }
                return;
            }
        }
    }

    public static boolean findUser(String username, String password) {
        for (User user : userList)
            if (user.getPassword().equals(password) &&
                    user.getUsername().equals(username))
                return true;
        return false;
    }
}
