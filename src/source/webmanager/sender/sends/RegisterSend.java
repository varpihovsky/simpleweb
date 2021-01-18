package webmanager.sender.sends;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.CreateUser;
import webmanager.interfaces.InterfaceSend;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterSend implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        String page = request.getParameter("page");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = new User.Builder()
                .withUsername(username)
                .withPassword(password)
                .withEmail(email)
                .build();

        DatabaseController<CreateUser, User> databaseController =
                new DatabaseController<>(CreateUser::new, user).execute();

        return Checker.pageReplace(page);
    }
}
