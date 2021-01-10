package webmanager.database.abstractions;

import com.mysql.cj.conf.ConnectionUrlParser;
import webmanager.interfaces.DatabaseObject;

import java.util.ArrayList;

public class Room implements DatabaseObject {
    private final ArrayList<ConnectionUrlParser.Pair<String, Object>>
            additionalData = new ArrayList<>();
    private String description;
    private String name;
    private String password;
    private String isPrivate;
    private int id;


    public Room(String name) {
        this.name = name;
    }

    public Room(int id) {
        this.id = id;
    }

    public Room(String name, String description, String isPrivate, String password) {
        this.name = name;
        this.description = description;
        this.password = password;
        this.isPrivate = isPrivate;
    }

    public Room(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public ArrayList<ConnectionUrlParser.Pair<String, Object>> getAdditionalData() {
        return additionalData;
    }

    public String isPrivate() {
        return isPrivate;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
