package DataBase;

class User {
    private String username;
    private String password;

    User(String username, String password){
        this.username = username;
        this.password = password;
    }

    String getUsername(){
        return username;
    }

    String getPassword(){
        return password;
    }

    void setUsername(String username){
        this.username = username;
    }

    void setPassword(String password){
        this.password = password;
    }
}
