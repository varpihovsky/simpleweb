package webmanager.file.operations.required;

import webmanager.file.operations.GetRoomLogo;
import webmanager.file.operations.GetUserAvatar;
import webmanager.file.operations.RoomLogoLoad;
import webmanager.file.operations.UserAvatarLoad;
import webmanager.interfaces.FileOperation;

public enum FileOperationEnum {
    GETUSERAVATAR {
        {
            this.operation = new GetUserAvatar();
        }
    },
    GETROOMLOGO {
        {
            this.operation = new GetRoomLogo();
        }
    },
    USERAVATARLOAD {
        {
            this.operation = new UserAvatarLoad();
        }
    },
    ROOMLOGOLOAD {
        {
            this.operation = new RoomLogoLoad();
        }
    };

    FileOperation operation;

    public static FileOperationEnum getInstance(String s) {
        if (s == null)
            throw new NullPointerException("Parameter can not be null");
        return FileOperationEnum.valueOf(s.toUpperCase());
    }

    public FileOperation getOperation() {
        return operation;
    }
}
