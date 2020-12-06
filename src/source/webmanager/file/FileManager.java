package webmanager.file;

import webmanager.file.abstractions.FileOperator;
import webmanager.file.operations.required.FileOperationEnum;

import javax.servlet.ServletContext;
import java.io.File;

public class FileManager {
    public static final String CHANGE_AVATAR_NAME = "ChangeAvatarName";
    public static final String CHANGE_ROOM_NAME = "ChangeRoomName";
    public static final String GET_USER_AVATAR = "GetUserAvatar";
    public static final String GET_ROOM_LOGO = "GetRoomLogo";

    private ServletContext context;
    private FileOperator operator;
    private String operation;

    public FileManager(ServletContext context) {
        this.context = context;
    }

    public void changeAvatarName(String name, String toName) {
        for (Extensions extension : Extensions.values()) {
            File file = new File(context.getRealPath("/img/useravatars/" +
                    name + "." + extension.name().toLowerCase()));
            file.renameTo(new File(context.getRealPath("/img/useravatars/" +
                    toName + "." + extension.name().toLowerCase())));
        }
    }

    public void changeRoomName(String name, String toName) {
        for (Extensions extension : Extensions.values()) {
            File file = new File(context.getRealPath("/img/roomlogos/" +
                    name + extension.name().toLowerCase()));
            file.renameTo(new File(context.getRealPath("/img/roomlogos/" +
                    toName + extension.name().toLowerCase())));
        }
    }

    public FileManager setOperation(String operation, FileOperator operator) {
        this.operation = operation;
        this.operator = operator;
        return this;
    }

    public <T extends Object> T execute() {
        FileOperationEnum fileOperationEnum = FileOperationEnum.getInstance(operation);
        return (T) fileOperationEnum.getOperation().operate(context, operator);
    }
}
