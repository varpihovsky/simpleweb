package WebManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class RequestManager {

    public static void redirect(HttpServletRequest request, HttpServletResponse response, String page, Controller controller)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = controller.getServletContext().getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }

    public static void register(HttpServletRequest request, HttpServletResponse response, String page, Controller controller)
            throws ServletException, IOException {

    }

    public static void showUserData(HttpServletRequest request, HttpServletResponse response, Controller controller)
    throws ServletException, IOException{
        String page = "/admin.jsp";

        ArrayList<ArrayList<String>> usrdata = new ArrayList<>();
        String userdata = "";

        userdata = userdata +"<table class=\"table\">\n" +
                "  <thead>\n" +
                "    <tr>\n" +
                "      <th scope=\"col\">Username</th>\n" +
                "      <th scope=\"col\">Password</th>\n" +
                "      <th scope=\"col\">Email</th>\n" +
                "    </tr>\n" +
                "  </thead>\n" +
                "  <tbody>\n";
        for(int i = 0; i < usrdata.size(); i++){
            userdata += "<tr>\n";
            for(int j = 0; j < usrdata.get(i).size(); j++){
                userdata += "<td>" + usrdata.get(i).get(j) + "</td>\n";
            }
            userdata += "</tr\n";
        }

        userdata += "</tbody>\n" +
                "</table>\n";

        request.setAttribute("userdata", userdata);
        redirect(request, response, page, controller);
    }
}
