package webmanager.renderer;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.FindUser;
import webmanager.file.FileManager;
import webmanager.file.abstractions.RenameOperator;
import webmanager.interfaces.InterfaceRenderer;
import webmanager.util.Checker;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

class UsersPageRenderer implements InterfaceRenderer {
    private final static int USERS_NUM = 30;
    private final FileManager fileManager = FileManager.getInstance();

    @Override
    public void render(HttpServletRequest request) {
        String render = "";
        String username = request.getParameter("user");

        ArrayList<User> users;
        User user;

        if (!Checker.isContainsWrong(username)) {
            user = new User.Builder().withUsername(username).build();
        } else {
            user = new User.Builder().withUsername("").build();
        }

        user.setAdditionalData("num", USERS_NUM);
        users = (ArrayList<User>) DatabaseController.getDatabaseAccess(new FindUser(), user).execute();
        //databaseController.setOperation(DatabaseController.FIND_USER, user).execute();

        if (users != null)
            for (User usr : users)
                render += "<a href=\"" + request.getContextPath() + "/controller?page=profile&send=redirect&user=" +
                        usr.getUsername() + "\">\n" +
                        "                <div class=\"user\">\n" +
                        "                    <h4>" + usr.getUsername() + "</h4>\n" +
                        "                    <img src=\"" + fileManager.setOperation(FileManager.GET_USER_AVATAR,
                        new RenameOperator(String.valueOf(usr.getId()))).execute() + "\" alt=\"room logo\"/>\n" +
                        "                </div>\n" +
                        "            </a>\n";


        request.setAttribute("render", render);
    }
}
