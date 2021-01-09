package webmanager.database;

import webmanager.database.abstractions.DatabaseOperator;
import webmanager.database.operations.required.DatabaseOperationEnum;
import webmanager.interfaces.DatabaseOperation;

import java.sql.Statement;

public class DatabaseController {

    /**
     * This database operation changes password and email by old username.
     * To change username you need to set additional data with newUsername name.
     * Have User parameter and returns void.
     *
     * @author Varpihovsky
     */
    public static final String CHANGE_USER_DATA = "changeUserData";

    /**
     * This database operation creates new room in database.
     * To create room you need to send Room with all fields initialized.
     * Have Room parameter and returns void.
     *
     * @author Varpihovsky
     */
    public static final String CREATE_ROOM = "createRoom";

    /**
     * This database operation creates new user in database.
     * To create user you need to send User with all field initialized.
     * Have User parameter and returns void.
     *
     * @author Varpihovsky
     */
    public static final String CREATE_USER = "createUser";

    /**
     * This database operation returns user list.
     * To get user list you need to send User with username field initialized.
     * Have User parameter and returns ArrayList<User>
     *
     * @author Varpihovsky
     */
    public static final String FIND_USER = "findUser";

    /**
     * This database operation returns room list.
     * To get room list you need to send Room with name field initialized.
     * Have User parameter and returns ArrayList<User>
     *
     * @author Varpihovsky
     */
    public static final String FIND_ROOM = "findRoom";

    /**
     * This database operation returns room list.
     * To get room list you need to send User with username field initialized.
     * Have User parameter and returns ArrayList<Room>
     *
     * @author Varpihovsky
     */
    public static final String GET_ROOM_LIST_BY_USER = "getRoomListByUser";

    /**
     * This database operation returns room.
     * To get room data you need to send Room with name field initialized.
     * Have Room parameter and returns Room
     *
     * @author Varpihovsky
     */
    public static final String GET_ROOM_DATA = "getRoomData";

    /**
     * This database operation returns user.
     * To get user data you need to send User with username field initialized.
     * Have User parameter and returns User
     *
     * @author Varpihovsky
     */
    public static final String GET_USER_DATA = "getUserData";

    /**
     * This database operation returns if user exists and used only for session checking.
     * To check if user exists you need to send User with username and passwords fields initialized.
     * Have user parameter and returns boolean
     *
     * @author Varpihovsky
     */
    public static final String IS_USER_EXISTS = "isUserExists";


    public static final String ROOM_ADD_TO_USER = "roomAddToUser";


    public static final String GET_ROOM = "GetRoom";

    private Statement statement;
    private String operation;
    private DatabaseOperator operator;

    public DatabaseController(String initialize) {
        if (initialize != null && initialize.equals("yes")) {
            DatabaseOperation<Void, Void> tmp = new InitializeDatabase();
            tmp.operate(null);
            return;
        }
    }

    public <T> DatabaseController setOperation(String operation, T operator) {
        this.operation = operation;
        this.operator = new DatabaseOperator(operator);
        return this;
    }

    public <T> T execute() {
        DatabaseOperationEnum databaseOperationEnum = DatabaseOperationEnum.getInstance(operation);
        return (T) databaseOperationEnum.getOperation().operate(operator.getOperator());
    }
}
