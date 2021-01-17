package webmanager.sender.sends.prepare;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;
import webmanager.database.operations.GetRoomListByUser;
import webmanager.database.operations.GetUserData;
import webmanager.database.operations.IsUserExists;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

class ProfilePrepare extends Preparing {

    private final HttpSession session;

    private User user;

    public ProfilePrepare(String currentPage, HttpServletRequest request) {
        super(currentPage, request);
        session = request.getSession();

        String userParameter = request.getParameter("user");

        if (userParameter != null && checkUserExisting(userParameter)) {
            prepareUserByUsername(userParameter);
        } else {
            prepareCurrentUser();
        }
    }

    private boolean checkUserExisting(String username) {
        User user = new User.Builder().withUsername(username).build();

        DatabaseController<IsUserExists, User> databaseController =
                DatabaseController.getDatabaseAccess(new IsUserExists(), user);

        return databaseController.execute();
    }

    private void prepareCurrentUser() {
        user = prepareUser((String) session.getAttribute("username"));
    }

    private void prepareUserByUsername(String username) {
        user = prepareUser(username);
    }

    private User prepareUser(String username) {
        User dbGet =
                new User.Builder()
                        .withUsername(username)
                        .build();

        DatabaseController<GetUserData, User> databaseController =
                DatabaseController.getDatabaseAccess(new GetUserData(), dbGet);

        return databaseController.execute();
    }

    @Override
    public String prepare() {
        request.setAttribute("user", user);

        request.setAttribute("roomList", getRoomList());

        return currentPage;
    }

    private ArrayList<Room> getRoomList() {
        DatabaseController<GetRoomListByUser, User> databaseController =
                DatabaseController.getDatabaseAccess(new GetRoomListByUser(), user);

        return databaseController.execute();
    }

}
