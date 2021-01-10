package webmanager.sender.sends;

import webmanager.Controller;
import webmanager.CookieManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.ChangeUserData;
import webmanager.database.operations.GetUserData;
import webmanager.database.operations.GetUserIdByUsername;
import webmanager.file.FileManager;
import webmanager.file.abstractions.PartWriteOperator;
import webmanager.interfaces.InterfaceSend;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class ChangeSend extends Operative implements InterfaceSend {
    @Override
    public String executeSend(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String page = request.getParameter("page");
        CookieManager manager = new CookieManager();
        String cookieParam = (String) session.getAttribute("cookie");

        String newEmail = request.getParameter("newemail");
        String newUsername = request.getParameter("newusername");
        String oldUsername = (String) session.getAttribute("username");
        String newPassword = request.getParameter("newpassword");
        String oldPassword = request.getParameter("oldpassword");

        try {
            Part filePart = request.getPart("file");
            fileManager.setOperation(FileManager.USER_AVATAR_LOAD, new PartWriteOperator(filePart,
                    String.valueOf(((User)
                            DatabaseController.getDatabaseAccess(new GetUserIdByUsername(), new User(oldUsername)).execute()
                            //databaseController.setOperation(DatabaseController.GET_USER_ID_BY_USERNAME, new User(oldUsername)).execute()
                    ).getId()))).execute();
        } catch (Exception e) {
            Controller.logger.severe(e.getMessage());
        }

        if (oldPassword.equals(((User)
                DatabaseController.getDatabaseAccess(new GetUserData(), new User(oldUsername)).execute()
                //databaseController.setOperation(DatabaseController.GET_USER_DATA, new User(oldUsername)).execute()
        ).getPassword())) {
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
            DatabaseController.getDatabaseAccess(new ChangeUserData(), user).execute();
            //databaseController.setOperation(DatabaseController.CHANGE_USER_DATA, user).execute();
        }
        return page;
    }
}
