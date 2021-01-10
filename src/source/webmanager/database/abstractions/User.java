package webmanager.database.abstractions;

import webmanager.interfaces.DatabaseObject;

import java.util.HashMap;

public class User implements DatabaseObject {
    private int id;
    private String username;
    private String email;
    private String password;
    private final HashMap<String, Object> additionalData = new HashMap<>();

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String username) {
        this.id = id;
        this.username = username;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
