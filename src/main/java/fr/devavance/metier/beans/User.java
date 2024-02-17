package fr.devavance.metier.beans;

/**
 *
 * @author marmotton
 */
public class User {

    // properties
    private String userName = "";
    private String password = "";
    private Boolean auth = false; // important !

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAuth() {
        return auth;
    }

    public void setAuth(Boolean auth) {
        this.auth = auth;
    }

    

}