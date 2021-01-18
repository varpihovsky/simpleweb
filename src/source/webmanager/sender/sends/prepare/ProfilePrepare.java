package webmanager.sender.sends.prepare;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.Room;
import webmanager.database.abstractions.User;
import webmanager.database.operations.GetRoomListByUser;
import webmanager.database.operations.GetUserData;

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

        if (userParameter != null) {
            prepareUserByUsername(userParameter);
        } else {
            prepareCurrentUser();
        }
    }

    private void prepareCurrentUser() {
        User currentUser = (User) session.getAttribute("currentUser");

        request.setAttribute("showSettings", true);

        if (currentUser != null)
            user = prepareUser((User) session.getAttribute("currentUser"));
    }

    private void prepareUserByUsername(String username) {
        User user = new User.Builder()
                .withUsername(username)
                .build();

        this.user = prepareUser(user);
    }

    private User prepareUser(User user) {
        User dbGet =
                new User.Builder()
                        .withUsername(user.getUsername())
                        .build();

        DatabaseController<GetUserData, User> databaseController =
                new DatabaseController<>(GetUserData::new, dbGet);

        return databaseController.execute();
    }

    @Override
    public String prepare() {
        if (user == null)
            return "main";

        request.setAttribute("user", user);

        request.setAttribute("roomList", getRoomList());

        return currentPage;
    }

    private ArrayList<Room> getRoomList() {
        DatabaseController<GetRoomListByUser, User> databaseController =
                new DatabaseController<>(GetRoomListByUser::new, user);

        return databaseController.execute();
    }

}
