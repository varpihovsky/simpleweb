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
        String message = "User is not registered. ";

        if (Checker.isContainsWrong(username) || Checker.isContainsWrong(email) || Checker.isContainsWrong(password) ||
                !Checker.checkLength(username, 4, 20) || !Checker.checkLength(password, 8, 20) ||
                !Checker.checkLength(email, 0, 40)) {
            message += "Contains wrong symbols or has wrong length";
        } else {
            DatabaseController.getDatabaseAccess(new CreateUser(), new User.Builder()
                    .withUsername(username)
                    .withEmail(email)
                    .withPassword(password)
                    .build()).execute();
            //databaseController.setOperation(DatabaseController.CREATE_USER, new User(username, email, password)).execute();
            message = "User registered";
        }
        request.setAttribute("registerMessage", message);
        return Checker.pageReplace(page);
    }
}
