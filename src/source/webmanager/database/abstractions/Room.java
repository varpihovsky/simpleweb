package webmanager.database.abstractions;

import com.mysql.cj.conf.ConnectionUrlParser;

import java.util.ArrayList;

public class Room {
    String description;
    String name;
    private final ArrayList<ConnectionUrlParser.Pair<String, Object>> additionalData;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        additionalData = new ArrayList<>();
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
