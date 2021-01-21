package webmanager.database.abstractions;

import webmanager.interfaces.DatabaseObject;

import java.util.Objects;

public class Room extends WithDataAddition implements DatabaseObject {
    private String description;
    private String name;
    private String password;
    private String isPrivate;
    private int id;

    private Room() {

    }

    public static class Builder {
        private final Room room = new Room();

        public Builder withName(String name) {
            room.setName(name);
            return this;
        }

        public Builder withPassword(String password) {
            room.setPassword(password);
            return this;
        }

        public Builder withPrivate(String isPrivate) {
            room.setPrivate(isPrivate);
            return this;
        }

        public Builder withId(int id) {
            room.setId(id);
            return this;
        }

        public Builder withDescription(String description) {
            room.setDescription(description);
            return this;
        }

        public Builder addAdditionalData(String attributeName, Object data) {
            room.setAdditionalData(attributeName, data);
            return this;
        }

        public Room build() {
            return room;
        }
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
        Room room = (Room) o;
        return id == room.id && Objects.equals(description, room.description) && Objects.equals(name, room.name) && Objects.equals(password, room.password) && Objects.equals(isPrivate, room.isPrivate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, name, password, isPrivate, id);
    }
}
