package webmanager.database.abstractions;

import com.mysql.cj.conf.ConnectionUrlParser;

import java.util.ArrayList;

public class Room {
    private String description;
    private String name;
    private String password;
    private String user;
    private String isPrivate;

    public Room(String name, String description, String isPrivate, String password, String user) {
        this.name = name;
        this.description = description;
        this.password = password;
        this.user = user;
        this.isPrivate = isPrivate;
        additionalData = new ArrayList<>();
    }

    public String isPrivate() {
        return isPrivate;
    }

    private final ArrayList<ConnectionUrlParser.Pair<String, Object>> additionalData;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        additionalData = new ArrayList<>();
    }

    public void setPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Room(String name) {
        this.name = name;
        additionalData = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getAdditionalData(String attributeName) {
        for (ConnectionUrlParser.Pair pair : additionalData) {
            if (pair.left.equals(attributeName))
                return pair.right;
        }
        return null;
    }

    public void setAdditionalData(String attributeName, Object data) {
        additionalData.add(new ConnectionUrlParser.Pair<>(attributeName, data));
    }
}
