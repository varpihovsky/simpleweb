package webmanager.sender.sends;

import webmanager.CookieManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.file.FileManager;
import webmanager.file.abstractions.PartWriteOperator;
import webmanager.file.abstractions.RenameOperator;
import webmanager.interfaces.InterfaceSend;
import webmanager.interfaces.Operative;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.util.HashMap;

public class ChangeSend implements InterfaceSend, Operative {
    private FileManager fileManager;
    private DatabaseController controller;

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
            fileManager.setOperation(FileManager.USER_AVATAR_LOAD, new PartWriteOperator(filePart, oldUsername)).execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

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

                fileManager.setOperation(FileManager.CHANGE_AVATAR_NAME,
                        new RenameOperator(oldUsername, newUsername)).execute();

                if (!Checker.isContainsWrong(cookieParam) && cookieParam.equals("true"))
                    manager.changeUsername(newUsername, response);
            }
            controller.setOperation(DatabaseController.CHANGE_USER_DATA, user).execute();
        }
        return page;
    }

    @Override
    public void set(HashMap<String, Object> bundle) {
        fileManager = (FileManager) bundle.get("FileManager");
        controller = (DatabaseController) bundle.get("DatabaseController");
    }
}
