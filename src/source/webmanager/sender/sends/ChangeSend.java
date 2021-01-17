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
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

public class ChangeSend implements InterfaceSend {
    FileManager fileManager = FileManager.getInstance();

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

        User userWithOldUsername = new User.Builder().withUsername(oldUsername).build();

        try {
            Part filePart = request.getPart("file");
            fileManager.setOperation(FileManager.USER_AVATAR_LOAD, new PartWriteOperator(filePart,
                    String.valueOf(((User)
                            DatabaseController.getDatabaseAccess
                                    (new GetUserIdByUsername(), userWithOldUsername).execute()).getId())))
                    .execute();
        } catch (Exception e) {
            Controller.logger.severe(e.getMessage());
        }

        if (oldPassword.equals(((User)
                DatabaseController.getDatabaseAccess(new GetUserData(), userWithOldUsername).execute()
        ).getPassword())) {
            if (!Checker.isContainsWrong(newEmail)) {
                userWithOldUsername.setEmail(newEmail);
            }
            if (!Checker.isContainsWrong(newPassword)) {
                userWithOldUsername.setPassword(newPassword);
                session.setAttribute("password", newPassword);
                if (!Checker.isContainsWrong(cookieParam) && cookieParam.equals("true"))
                    manager.changePassword(newPassword, response);
            }
            if (!Checker.isContainsWrong(newUsername)) {
                userWithOldUsername.setAdditionalData("newUsername", newUsername);
                session.setAttribute("username", newUsername);

                if (!Checker.isContainsWrong(cookieParam) && cookieParam.equals("true"))
                    manager.changeUsername(newUsername, response);
            }
            DatabaseController.getDatabaseAccess(new ChangeUserData(), userWithOldUsername).execute();
            //databaseController.setOperation(DatabaseController.CHANGE_USER_DATA, user).execute();
        }
        return page;
    }
}
