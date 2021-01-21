package webmanager.database.abstractions;

import webmanager.interfaces.DatabaseObject;

import java.util.Objects;

public class User extends WithDataAddition implements DatabaseObject {
    private int id;
    private String username;
    private String email;
    private String password;

    private User() {

    }

    public static class Builder {
        private final User user = new User();

        public Builder withId(int id) {
            user.setId(id);
            return this;
        }

        public Builder withUsername(String username) {
            user.setUsername(username);
            return this;
        }

        public Builder withEmail(String email) {
            user.setEmail(email);
            return this;
        }

        public Builder withPassword(String password) {
            user.setPassword(password);
            return this;
        }

        public Builder addAdditionalData(String attributeName, Object data) {
            user.setAdditionalData(attributeName, data);
            return this;
        }

        public User build() {
            return user;
        }
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(username, user.username) && Objects.equals(email, user.email) && Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, email, password);
    }
}
