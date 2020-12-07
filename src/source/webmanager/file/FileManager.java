package webmanager.file;

import webmanager.file.operations.required.FileOperationEnum;

import javax.servlet.ServletContext;
import java.io.File;

public class FileManager {
    public static final String CHANGE_AVATAR_NAME = "ChangeAvatarName";
    public static final String CHANGE_ROOM_NAME = "ChangeRoomName";
    public static final String GET_USER_AVATAR = "GetUserAvatar";
    public static final String GET_ROOM_LOGO = "GetRoomLogo";
    public static final String USER_AVATAR_LOAD = "UserAvatarLoad";

    private final ServletContext context;
    private Object operator;
    private String operation;

    public FileManager(ServletContext context) {
        this.context = context;
    }

    public FileManager setOperation(String operation, Object operator) {
        this.operation = operation;
        this.operator = operator;
        return this;
    }

    public <T extends Object> T execute() {
        FileOperationEnum fileOperationEnum = FileOperationEnum.getInstance(operation);
        return (T) fileOperationEnum.getOperation().operate(context, operator);
    }
}
