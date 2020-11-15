package controller;

import DataBase.UserLister;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
    throws ServletException, IOException{
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException {
        String page = request.getParameter("page");
        String send = request.getParameter("send");

        if(page.equals("/main.jsp") && send.equals("redirect")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
        else if(page.equals("/register.jsp") && send.equals("redirect")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
        else if(page.equals("/main.jsp") && send.equals("signIn")){
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        }
        else if(page.equals("/register.jsp") && send.equals("signUp")){
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String message = "User is not registered. ";
            boolean isWrong = false;

            if(username.length() < 4){
                isWrong = true;
                message = message + "Your username has length less than 4.";
            }
            if (password.length() < 8 || password.length() > 20){
                isWrong = true;
                message = message + "Wrong password length.";
            }

            if(isWrong){
                request.setAttribute("message", message);
            }
            else {
                UserLister.init();
                UserLister.addUser(username, email, password);
                request.setAttribute("message", "Registered");
            }
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
            dispatcher.forward(request, response);
        }
        else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/error.jsp");
            dispatcher.forward(request, response);
        }
    }
}
