package webmanager.file.operations.required;

import webmanager.file.operations.ChangeAvatarName;
import webmanager.file.operations.ChangeRoomName;
import webmanager.file.operations.GetRoomLogo;
import webmanager.file.operations.GetUserAvatar;
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
