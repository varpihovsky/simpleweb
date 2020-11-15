package DataBase;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserLister {
    private static ArrayList<User> userList;

    public static void init() {

        try {
            DataBaseFactory.init();
            userList = DataBaseFactory.sync();
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getErrorCode() + "\nCause: " + e.getCause());
        }
    }

    public static void addUser(String username, String password, String email) {
        userList.add(new User(username, password, email));
        try {
            DataBaseFactory.addUser(username, email, password);
        } catch (SQLException e) {
            System.out.println("Exception: " + e.getErrorCode() + "\nCause: " + e.getCause());
        }
    }

    public static void deleteUser(String username, String password, String email){
        for(int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getPassword().equals(password) &&
                    userList.get(i).getUsername().equals(username)) {
                userList.remove(i);
                try {
                    DataBaseFactory.deleteUser(username, password, email);
                } catch (SQLException e) {
                    System.out.println("Exception: " + e.getErrorCode() + "\nCause: " + e.getCause());
                }
                return;
            }
        }
    }

    public static boolean findUser(String value) {
        boolean email = false;
        if(value.contains("@"))
            email = true;
        for (User user : userList) {
            if (email) {
                if(value.equals(user.getEmail()))
                    return true;
            }
            else {
                if(value.equals(user.getUsername()))
                    return true;
            }
        }
        return false;
    }
}
