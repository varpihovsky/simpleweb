package webmanager.sender.sends;

import org.apache.log4j.Logger;
import webmanager.CookieManager;
import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.ChangeUserData;
import webmanager.database.operations.GetUserData;
import webmanager.database.operations.GetUserIdByUsername;
import webmanager.file.FileManager;
import webmanager.file.abstractions.PartWriteOperator;
import webmanager.interfaces.InterfaceSend;

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
            fileManager.setOperation(FileManager.USER_AVATAR_LOAD,
                    new PartWriteOperator(filePart, getUserId(userWithOldUsername)))
                    .execute();
        } catch (Exception e) {
            Logger logger = Logger.getLogger(ChangeSend.class);
            logger.warn(e);
        }

        if (oldPassword.hashCode() == getUserPasswordHashCode(userWithOldUsername)) {
            if (newEmail != null) {
                userWithOldUsername.setEmail(newEmail);
            }
            if (newPassword != null) {
                userWithOldUsername.setPassword(newPassword);
                session.setAttribute("password", newPassword);
                if (cookieParam != null && cookieParam.equals("true"))
                    manager.changePassword(newPassword, response);
            }
            if (newUsername != null) {
                userWithOldUsername.setAdditionalData("newUsername", newUsername);
                session.setAttribute("username", newUsername);

                if (cookieParam != null && cookieParam.equals("true"))
                    manager.changeUsername(newUsername, response);
            }
            DatabaseController<ChangeUserData, User> databaseController =
                    new DatabaseController<>(ChangeUserData::new, userWithOldUsername);
            databaseController.execute();
        }
        return page;
    }

    private String getUserId(User user) {
        DatabaseController<GetUserIdByUsername, User> databaseController =
                new DatabaseController<>(GetUserIdByUsername::new, user);
        return databaseController.execute();
    }

    private int getUserPasswordHashCode(User user) {
        DatabaseController<GetUserData, User> databaseController =
                new DatabaseController<>(GetUserData::new, user);
        String password = ((User) databaseController.execute()).getPassword();
        return password.hashCode();
    }
}
