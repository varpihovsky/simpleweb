package WebManager;

import DataBase.UserLister;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestManager {

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String page, Controller controller)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = controller.getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    public static void register(HttpServletRequest request, HttpServletResponse response, String page, Controller controller)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String message = "User is not registered. ";
        boolean isWrong = false;

        if (username.length() < 4) {
            isWrong = true;
            message = message + "Your username has length less than 4.";
        }
        if (password.length() < 8 || password.length() > 20) {
            isWrong = true;
            message = message + "Wrong password length.";
        }

        if (isWrong) {
            request.setAttribute("message", message);
        } else {
            UserLister.init();
            UserLister.addUser(username, email, password);
            request.setAttribute("message", "Registered");
        }
        RequestManager.redirect(request, response, page, controller);
    }
}
