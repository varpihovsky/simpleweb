package webmanager.database.operations.required;

import webmanager.database.operations.*;
import webmanager.security.Checker;

public enum DatabaseOperationEnum {
    CHANGEUSERDATA {
        {
            operation = new ChangeUserData();
        }
    },
    CREATEROOM {
        {
            operation = new CreateRoom();
        }
    },
    CREATEUSER {
        {
            operation = new CreateUser();
        }
    },
    FINDUSER {
        {
            operation = new FindUser();
        }
    },
    FINDROOM {
        {
            operation = new FindRoom();
        }
    },
    GETROOMDATA {
        {
            operation = new GetRoomData();
        }
    },
    GETROOMLISTBYUSER {
        {
            operation = new GetRoomListByUser();
        }
    },
    GETUSERDATA {
        {
            operation = new GetUserData();
        }
    },
    ISUSEREXISTS {
        {
            operation = new IsUserExists();
        }
    };

    DatabaseOperation operation;

    public static DatabaseOperationEnum getInstance(String s) {
        if (Checker.isContainsWrong(s))
            return null;
        return DatabaseOperationEnum.valueOf(s.toUpperCase());
    }

    public DatabaseOperation getOperation() {
        return operation;
    }
}
