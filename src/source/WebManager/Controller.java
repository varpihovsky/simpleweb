package WebManager;

import DataBase.UserLister;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String page = request.getParameter("page");
        String send = request.getParameter("send");

        if (page.equals("/main.jsp") && send.equals("redirect")) {
            RequestManager.redirect(request, response, page, this);
        } else if (page.equals("/register.jsp") && send.equals("redirect")) {
            RequestManager.redirect(request, response, page, this);
        } else if (page.equals("/admin.jsp") && send.equals("redirect")) {
            RequestManager.showUserData(request, response, this);
        } else if (page.equals("/main.jsp") && send.equals("signIn")) {
            RequestManager.redirect(request, response, page, this);
        } else if (page.equals("/register.jsp") && send.equals("signUp")) {
            RequestManager.register(request, response, page, this);
        } else {
            RequestManager.redirect(request, response, page, this);
        }
    }
}
