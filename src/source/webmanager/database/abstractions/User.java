package webmanager.database.abstractions;

import com.mysql.cj.conf.ConnectionUrlParser;

import java.util.ArrayList;

public class User {
    private String username;
    private String email;
    private String password;
    private final ArrayList<ConnectionUrlParser.Pair<String, Object>> additionalData;

    public User() {
        additionalData = new ArrayList<>();
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        additionalData = new ArrayList<>();
    }

    public User(String username) {
        this.username = username;
        additionalData = new ArrayList<>();
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        additionalData = new ArrayList<>();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
