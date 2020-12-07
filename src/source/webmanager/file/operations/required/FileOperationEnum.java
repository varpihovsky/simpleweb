package webmanager.file.operations.required;

import webmanager.file.operations.*;
import webmanager.security.Checker;

public enum FileOperationEnum {
    CHANGEAVATARNAME {
        {
            this.operation = new ChangeAvatarName();
        }
    },
    CHANGEROOMNAME {
        {
            this.operation = new ChangeRoomName();
        }
    },
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
    };

    FileOperation operation;

    public static FileOperationEnum getInstance(String s) {
        if (Checker.isContainsWrong(s))
            return null;
        return FileOperationEnum.valueOf(s.toUpperCase());
    }

    public FileOperation getOperation() {
        return operation;
    }
}
