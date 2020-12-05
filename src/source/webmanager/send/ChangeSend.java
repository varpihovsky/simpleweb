package webmanager.send;

import webmanager.CookieManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.security.Checker;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

class ChangeSend implements InterfaceSend {
    private ServletContext context;

    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response, DatabaseController controller) {
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        CookieManager manager = new CookieManager();
        String cookieParam = (String) session.getAttribute("cookie");

        String newEmail = request.getParameter("newemail");
        String newUsername = request.getParameter("newusername");
        String oldUsername = (String) session.getAttribute("username");
        String newPassword = request.getParameter("newpassword");
        String oldPassword = request.getParameter("oldpassword");

        if (oldPassword.equals(((User) controller.setOperation(DatabaseController.GET_USER_DATA,
                new User(oldUsername)).execute()).getPassword())) {
            User user = new User(oldUsername);
            if (!Checker.isContainsWrong(newEmail)) {
                user.setEmail(newEmail);
            }
            if (!Checker.isContainsWrong(newPassword)) {
                user.setPassword(newPassword);
                session.setAttribute("password", newPassword);
                if (!Checker.isContainsWrong(cookieParam) && cookieParam.equals("true"))
                    manager.changePassword(newPassword, response);
            }
            if (!Checker.isContainsWrong(newUsername)) {
                user.setAdditionalData("newUsername", newUsername);
                session.setAttribute("username", newUsername);
                if (!Checker.isContainsWrong(cookieParam) && cookieParam.equals("true"))
                    manager.changeUsername(newUsername, response);
            }
            controller.setOperation(DatabaseController.CHANGE_USER_DATA, user).execute();
        }
        return page;
    }

    public void setContext(ServletContext context) {
        this.context = context;
    }
}
