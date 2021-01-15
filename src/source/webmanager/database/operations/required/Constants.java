package webmanager.database.operations.required;

public class Constants {
    public static final String CHANGE_USER_EMAIL = "UPDATE account SET email=? WHERE username=?";

    public static final String CHANGE_USER_PASSWORD = "UPDATE account SET password=? WHERE username=?";

    public static final String CHANGE_USER_USERNAME = "UPDATE account SET username=? WHERE username=?";

    //TODO: rewrite
    public static final String CREATE_ROOM = "INSERT INTO room(name, description, password, private)\n" +
            "            VALUES (?, ?, ?, ?)";
    public static final String ASSIGN_ADMIN_TO_ROOM =
            "            INSERT INTO room_admins(user_id, room_id) VALUES((SELECT id FROM account WHERE username=?),\n" +
                    "            (SELECT MAX(id) FROM room))";
    public static final String ASSIGN_MEMBER_TO_ROOM =
            "            INSERT INTO room_members(user_id, room_id) VALUES((SELECT id FROM account WHERE username=?),\n" +
                    "            (SELECT MAX(id) FROM room))";

    public static final String CREATE_USER = "INSERT INTO account(username, password, email, role_id) VALUES (?, ?, ?, 2)";

    public static final String FIND_ROOM = "SELECT id, name, description FROM room " +
            "WHERE private='no' ORDER BY LEVENSHTEIN(?, name) ASC LIMIT 0,?";

    public static final String FIND_USER = "SELECT id, username FROM account ORDER BY LEVENSHTEIN(?, username) " +
            "ASC LIMIT 0,?";

    public static final String GET_ROOM_ID_BY_NAME = "SELECT id FROM room WHERE name=?";

    public static final String GET_ROOM_LIST_BY_USER = "SELECT id, name, description FROM room WHERE " +
            "id IN (SELECT room_id FROM room_members WHERE user_id=(SELECT account.id FROM account WHERE username=?))";

    public static final String GET_USER_DATA = "SELECT * FROM account WHERE username=?";

    public static final String GET_USER_ID_BY_USERNAME = "SELECT id FROM account WHERE username=?";

    public static final String IS_USER_EXISTS = "SELECT EXISTS(SELECT username FROM account " +
            "WHERE username=? AND password=?)";

    //TODO: rewrite
    public static final String GET_ROOMSTRING_BY_USER = "SELECT ROOMS FROM user_data WHERE USERNAME=?";

    public static final String ROOM_ADD_TO_USER = "INSERT INTO room_members(user_id, room_id) VALUES(" +
            "SELECT id FROM account WHERE username=?, SELECT id FROM room WHERE name=?)";
}
