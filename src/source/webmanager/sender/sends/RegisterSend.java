package webmanager.sender.sends;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.interfaces.InterfaceSend;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterSend extends Operative implements InterfaceSend {
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
            databaseController.setOperation(DatabaseController.CREATE_USER, new User(username, email, password)).execute();
            message = "User registered";
        }
        request.setAttribute("registerMessage", message);
        return Checker.pageReplace(page);
    }
}
