package webmanager.util;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.IsUserExists;

public class Checker {

    public boolean checkUserExisting(String username) {
        User user = new User.Builder().withUsername(username).build();

        DatabaseController<IsUserExists, User> databaseController =
                new DatabaseController<>(IsUserExists::new, user);

        return databaseController.execute();
    }

    public static String pageReplace(String page) {
        page = page.replace("/", "");
        page = page.replace(".jsp", "");
        return page;
    }
}
