package models;

import com.lambdaworks.crypto.SCryptUtil;

import java.io.Serializable;

public class User implements Serializable {
    private int userID;
    private String username;
    private String email;
    private String password;

    public User() {}
    public User(int userID, String username, String email, String password) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = this.hashPassword(password);
    }

    public boolean userExists(String email, String password) {
        return this.validEmail(email) && this.validPassword(password);
    }

    private boolean validEmail(String email) {
        return this.email.equals(email);
    }

    private boolean validPassword(String password) {
        return SCryptUtil.check(password, this.password);
    }

    private String hashPassword(String password) {
        return SCryptUtil.scrypt(password, 16, 16, 16);
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
