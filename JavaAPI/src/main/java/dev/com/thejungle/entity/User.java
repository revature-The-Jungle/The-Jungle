package dev.com.thejungle.entity;

import java.util.Objects;

public class User {

    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String userAbout;
    private String userBirthdate;
    private String email;
    private String status;

    public User(){}

    public User(int userId, String firstName, String lastName,  String username,
                      String password, String userAbout, String userBirthdate, String email, String status) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.userAbout = userAbout;
        this.userBirthdate = userBirthdate;
        this.email = email;
        this.status = status;
    }

    @Override
    public String toString() {
        return "JungleUser{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userAbout='" + userAbout + '\'' +
                ", userBirthdate='" + userBirthdate + '\'' +
                ", email='" + email + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User that = (User) o;
        return userId == that.userId && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(userAbout, that.userAbout) && Objects.equals(userBirthdate, that.userBirthdate) && Objects.equals(email, that.email) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, username, password, userAbout, userBirthdate, email, status);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserAbout() {
        return userAbout;
    }

    public void setUserAbout(String userAbout) {
        this.userAbout = userAbout;
    }

    public String getUserBirthdate() {
        return userBirthdate;
    }

    public void setUserBirthdate(String userBirthdate) {
        this.userBirthdate = userBirthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
