package webmanager.jstl;

import webmanager.database.DatabaseController;
import webmanager.database.abstractions.User;
import webmanager.database.operations.GetUserIdByUsername;
import webmanager.file.FileManager;
import webmanager.file.abstractions.RenameOperator;

import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class AvatarTag extends SimpleTagSupport {
    private final FileManager fileManager = FileManager.getInstance();

    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void doTag() throws IOException {
        DatabaseController<GetUserIdByUsername, User> databaseController =
                new DatabaseController<>(GetUserIdByUsername::new, user);

        getJspContext()
                .getOut()
                .write((String) fileManager.setOperation(FileManager.GET_USER_AVATAR,
                        new RenameOperator(String.valueOf(((User) databaseController.execute()).getId()))).execute());
    }
}
