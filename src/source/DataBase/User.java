package DataBase;

class User {
    private String username;
    private String email;
    private String password;

    User(String username, String password, String email){
        this.username = username;
        this.email = email;
        this.password = password;
    }

    String getUsername(){
        return username;
    }

    String getEmail(){return email;}

    String getPassword(){
        return password;
    }

    void setUsername(String username){
        this.username = username;
    }

    void setEmail(String email){this.email = email;}

    void setPassword(String password){
        this.password = password;
    }
}
