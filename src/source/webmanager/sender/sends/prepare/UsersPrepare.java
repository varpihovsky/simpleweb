package webmanager.sender.sends.prepare;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.FindUser;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

public class UsersPrepare extends Preparing {
    private final static int USERS_NUM = 30;

    public UsersPrepare(String currentPage, HttpServletRequest request) {
        super(currentPage, request);
    }

    @Override
    public String prepare() {
        String username = request.getParameter("user");

        User user;
        if (username != null)
            user = new User.Builder().withUsername(username).build();
        else user = new User.Builder().withUsername("").build();

        user.setAdditionalData("num", USERS_NUM);

        DatabaseController<FindUser, User> databaseController =
                new DatabaseController<>(FindUser::new, user);

        ArrayList<User> userList = databaseController.execute();

        request.setAttribute("userList", userList);

        return currentPage;
    }
}
