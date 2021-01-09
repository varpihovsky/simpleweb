package webmanager.database.abstractions;

import java.util.HashMap;

public class User {
    private String username;
    private String email;
    private String password;
    private final HashMap<String, Object> additionalData = new HashMap<>();

    public User() {
    }

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
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
        return additionalData.get(attributeName);
    }

    public void setAdditionalData(String attributeName, Object data) {
        additionalData.put(attributeName, data);
    }
}
